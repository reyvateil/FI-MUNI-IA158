package radar;

import lejos.hardware.motor.Motor;
import lejos.hardware.motor.NXTRegulatedMotor;

public class FireUnit {
	
	private DataExchange de;
	private NXTRegulatedMotor motorC;
	private int degree;

	
	public FireUnit(DataExchange de) {
		this.de = de;
		this.motorC = Motor.C;
		this.degree = 350;
		this.motorC.setSpeed(300);
		this.motorC.rotateTo(degree,true);
		
	}
	
	public void run() {
		fire();
		System.out.println("!!! FIRE !!!");
		de.status = Status.MEASURE;
	}
	
	public void fire() {
		motorC.setSpeed(400);
		motorC.rotateTo(degree+300, false);
		resetFireUnit();
	}
	
	public void resetFireUnit() {
		motorC.setSpeed(500);
		motorC.rotateTo(0,false);
		motorC.rotateTo(degree,false);
		motorC.setSpeed(300);
	}
	
	
	
	
}
