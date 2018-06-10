package radar;

import lejos.hardware.motor.Motor;
import lejos.hardware.motor.NXTRegulatedMotor;
/**
 * @author Gallo, Silhan
 * @version 2018
 * 
 * Motor which moving with the IR sensor 
 * 
 */
public class Scanner extends Thread {
	
	private DataExchange de;
	private NXTRegulatedMotor motor;
	private int degree;
	
	/**
	 * Constructor of moving motor
	 * @param de DataExchange object
	 */
	public Scanner(DataExchange de) {
		this.de = de;
		this.motor = Motor.A;
		motor.setSpeed(15);
		de.setScannerMotorA(motor);
		this.degree = 70;
		this.setPriority(10);
	}
	
	/**
	 * It's turning the whole scanner in range (-degree, + degree)
	 */
	public void run() {
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
