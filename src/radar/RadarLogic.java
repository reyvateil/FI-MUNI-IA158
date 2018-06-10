package radar;
/**
 * @author Gallo, Silhan
 * @version 2018
 * 
 * The main logic of radar. It switches between defined modes. 
 * 
 */
public class RadarLogic extends Thread {

	FireUnit fu = null;
	AngleMotor am = null;
	MeasureProximity mp = null;
	DataExchange de = null;
	/**
	 * Constructor of RadarLogic
	 * @param de DataExchange object
	 */
	public RadarLogic(DataExchange de) {
		this.fu = new FireUnit(de);
		this.am = new AngleMotor(de);
		this.mp = new MeasureProximity(de);
		this.de = de;
	}
	/**
	 * It continuously checks the status and switches between modes of operation.
	 */
	public void run() {
		while(true) {
			switch(de.status) {
				case ALIGN:
					System.out.println(">ALIGN");
					am.run();
					break;
				case FIRE:
					System.out.println(">FIRE");
					fu.run();
					break;
				case MEASURE:
				default:
					//System.out.println(">MEASURE");
					mp.run();
					break;
			}
			
		}
		
	}
	
}
