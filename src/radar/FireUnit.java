package radar;

import lejos.hardware.motor.Motor;
import lejos.hardware.motor.NXTRegulatedMotor;
/**
 * @author Gallo, Silhan
 * @version 2018
 * 
 * Class which represents a canon
 */
public class FireUnit {
	
	private DataExchange de;
	private NXTRegulatedMotor motorC;
	private int degree;

	/**
	 * Constructor of canon
	 * @param de DataExchange object
	 */
	public FireUnit(DataExchange de) {
		this.de = de;
		this.motorC = Motor.C;
		this.degree = 350;
		this.motorC.setSpeed(300);
		this.motorC.rotateTo(degree,true);
		
	}
	/**
	 * Canon shoots
	 */
	public void run() {
		motorC.setSpeed(400);
		motorC.rotateTo(degree+300, false);
		resetFireUnit();
		System.out.println("!!! FIRE !!!");
		de.status = Status.MEASURE;
	}
	
	/**
	 * It sets the canon back to ready position.
	 */
	public void resetFireUnit() {
		motorC.setSpeed(500);
		motorC.rotateTo(0,false);
		motorC.rotateTo(degree,false);
		motorC.setSpeed(300);
	}
	
	
	
	
}
