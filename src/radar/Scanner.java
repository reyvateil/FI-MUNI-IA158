package radar;

import lejos.hardware.motor.Motor;
import lejos.hardware.motor.NXTRegulatedMotor;
/*
 * Moving with the IR sensor 
 * 
 */
public class Scanner extends Thread {
	
	private DataExchange de;
	private NXTRegulatedMotor motor;
	private int degree;
	
	
	public Scanner(DataExchange de) {
		this.de = de;
		this.motor = Motor.A;
		
		de.setScannerMotorA(motor);
		//this.degree = 135;
		this.degree = 50;
	}
	
	public void run() {
		//motor.setSpeed(200);
		motor.setSpeed(100);
		while(true) {
			/*if(de.getStop()) {
				waitUntilClear();
				
			} else {
				if (!motor.isMoving() && !motor.isStalled()) {
					//odtud jsem vzal kod nize
			}*/
			System.out.println("Moving");
			motor.rotateTo(degree, true);
			motor.waitComplete();
			degree = -degree;
		}
	}
	
	private void waitUntilClear() {
		System.out.println("Waiting...");
		if(motor.isMoving() && !motor.isStalled()) {
			this.degree = motor.getLimitAngle();
			motor.stop();
		}
		try {
			while(de.getStop()) 
				Thread.sleep(500);
		} catch(InterruptedException ie) {
			System.exit(1);
		}
	}

	public NXTRegulatedMotor getMotor() {
		return motor;
	}

	public void setMotor(NXTRegulatedMotor motor) {
		this.motor = motor;
	}
	
	
}
