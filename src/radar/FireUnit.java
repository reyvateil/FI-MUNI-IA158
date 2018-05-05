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
	//private NXTRegulatedMotor motorA;
	private int degree;

	
	public FireUnit(DataExchange de) {
		this.de = de;
		this.motorC = Motor.C;
		//this.degree = 400;
		this.degree = 350;
		this.motorC.setSpeed(300);
		//set the ready position
		this.motorC.rotateTo(degree,true);
		
		//this.motorA = de.getScannerMotorA();
		
	}
	
	public void run() {
		/*int degree = 5000;
		while(true) {
				if (de.getStop()) {
					motor.forward();  
				}
			}*/
		while(true) {
			
				//System.out.println(motorA.getTachoCount() + " Position of object:" + de.getPosOfScanWhenFindTarget());		
				/*if(de.isFireAtWill() && (motorA.getTachoCount() >= de.getPosOfScanWhenFindTarget() - 2) && (motorA.getTachoCount() <= de.getPosOfScanWhenFindTarget() + 2)) {
					fire();
					System.out.println("!!! FIRE !!!");
					de.setFireAtWill(false);
					de.setPosOfScanWhenFindTarget(Integer.MIN_VALUE);
				}*/
			if(de.isFireAtWill() && de.getStop()){
				fire();
				System.out.println("!!! FIRE !!!");
				de.resetMeasuredValues();
				de.setPosOfScanWhenFindTarget(Integer.MIN_VALUE);
			}
				
						
			
		}
	}
	
	public void fire() {
		//if (de.setFireAtWill){}
		//motor.rotate(this.degree);
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
