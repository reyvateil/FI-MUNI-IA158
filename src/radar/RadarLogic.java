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
		this.de = de;
	}
	
	public void run() {
		while(true) {
			
			switch(de.status) {
				case ALIGN:
					System.out.println(" ALIGN -- angle motor");
					am.run();
					break;
				case FIRE:
					System.out.println(" FIRE ");
					fu.run();
					am.defaultPosition();
					break;
				case MEASURE:
				default:
					System.out.println(" MEASURE -- measure proximity");
					mp.run();
					break;
			}
			
			try {
				Thread.sleep(500);
			}catch (InterruptedException e) {
				System.out.println("error");
			}
		}
		
	}
	
}
