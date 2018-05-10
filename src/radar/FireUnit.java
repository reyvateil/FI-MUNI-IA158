package radar;

import lejos.hardware.motor.Motor;
import lejos.hardware.motor.NXTRegulatedMotor;

public class FireUnit extends Thread {
	
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
			
		if(de.isFireAtWill() && de.getStop()){
			fire();
			System.out.println("!!! FIRE !!!");
			de.resetMeasuredValues();
			de.setPosOfScanWhenFindTarget(Integer.MIN_VALUE);
		}			
	}
	
	public void fire() {
		motorC.rotateTo(degree+200,true);
		motorC.waitComplete();
		de.setFireAtWill(false);
		resetFireUnit();	
	}
	
	public void resetFireUnit() {
		motorC.setSpeed(500);
		motorC.rotateTo(0,true);
		motorC.waitComplete();
		motorC.rotateTo(degree,true);
		motorC.waitComplete();
		motorC.setSpeed(300);
	}
	
	
	
	
}
