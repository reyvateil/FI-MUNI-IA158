package radar;

public class RadarLogic extends Thread {

	FireUnit fu = null;
	AngleMotor am = null;
	MeasureProximity mp = null;
	DataExchange de = null;
	
	public RadarLogic(DataExchange de) {
		this.fu = new FireUnit(de);
		this.am = new AngleMotor(de);
		this.mp = new MeasureProximity(de);
	}
	
	public void run() {
		while(true) {
			
			switch(de.status) {
				case ALIGN:
					try {
						am.join();
					} catch(InterruptedException ie) {
						System.out.println("Angle Motor Fail");
					}
					break;
				case FIRE:
					try {
						fu.join();
					} catch(InterruptedException ie) {
						System.out.println("Fire Unit Fail");
					}
					break;
				case MEASURE:
				default:
					try {
						mp.join();
					} catch(InterruptedException ie) {
						System.out.println("Measure Proximity Fail");
					}
					break;
			}
			
		}
	}
	
}
