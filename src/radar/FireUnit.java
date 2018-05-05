package radar;

import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.motor.Motor;
import lejos.hardware.motor.NXTRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.BaseMotor;
import java.math.*;


public class FireUnit extends Thread {
	
	private DataExchange de;
	private NXTRegulatedMotor motorC;
	private NXTRegulatedMotor motorA;
	private int degree;

	
	public FireUnit(DataExchange de) {
		this.de = de;
		this.motorC = Motor.C;
		this.degree = 400;
		this.motorC.setSpeed(300);
		//set the ready position
		this.motorC.rotateTo(degree,true);
		
		this.motorA = de.getScannerMotorA();
	}
	
	public void run() {
		/*int degree = 5000;
		while(true) {
				if (de.getStop()) {
					motor.forward();  
				}
			}*/
		while(true) {
			if (motorA != null) {
				System.out.println(motorA.getTachoCount() + " Position of object:" + de.getPosOfScanWhenFindTarget());		
				if(de.isFireAtWill() && (motorA.getTachoCount() >= de.getPosOfScanWhenFindTarget() - 5) && (motorA.getTachoCount() <= de.getPosOfScanWhenFindTarget() + 5)) {
					fire();
					de.setFireAtWill(false);
					de.setPosOfScanWhenFindTarget(Integer.MIN_VALUE);
				}
						
			}
		}
	}
	
	public void fire() {
		//if (de.setFireAtWill){}
		//motor.rotate(this.degree);
		motorC.rotateTo(degree+100,true);
		motorC.waitComplete();
		resetFireUnit();
	}
	
	public void resetFireUnit() {
		motorC.rotateTo(0,true);
		motorC.waitComplete();
		motorC.rotateTo(degree,true);
		motorC.waitComplete();
	}
	
	
	
	
}
