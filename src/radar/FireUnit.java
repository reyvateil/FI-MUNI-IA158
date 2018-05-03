package radar;

import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.motor.Motor;
import lejos.hardware.motor.NXTRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.BaseMotor;



public class FireUnit extends Thread {
	
	private DataExchange de;
	private NXTRegulatedMotor motor;
	private int degree;

	
	
	public FireUnit(DataExchange de) {
		this.de = de;
		this.motor = Motor.C;
		this.degree = 1000;
		this.motor.setSpeed(300);
		
	}
	
	/*public void run() {
		int degree = 5000;
		while(true) {
				if (de.getStop()) {
					motor.forward();  
				}
			}
			
		
	}*/
	
	public void fire() {
		motor.rotate(this.degree);
	}
	
}
