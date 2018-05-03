package radar;

import lejos.hardware.motor.Motor;
import lejos.hardware.motor.NXTRegulatedMotor;

public class Scanner extends Thread {
	
	private DataExchange de;
	private NXTRegulatedMotor motor;
	private int degree;
	
	public Scanner(DataExchange de) {
		this.de = de;
		this.motor = Motor.A;
		//this.degree = 135;
		this.degree = 90;
	}
	
	public void run() {
		//motor.setSpeed(200);
		motor.setSpeed(100);
		while(true) {
			if(de.getStop()) {
				waitUntilClear();
			} else {
				if (!motor.isMoving() && !motor.isStalled()) {
					System.out.println("Moving");
					motor.rotateTo(degree, true);
					degree = -degree;
				}
			}
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
}
