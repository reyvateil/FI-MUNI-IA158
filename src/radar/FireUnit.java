package radar;

import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.motor.Motor;
import lejos.hardware.motor.NXTRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.BaseMotor;



public class FireUnit extends Thread {
	
	private DataExchange de;
	private NXTRegulatedMotor motorC;
	private int degree;

	
	
	public FireUnit(DataExchange de) {
		this.de = de;
		this.motorC = Motor.C;
		this.degree = 1000;
		this.motorC.setSpeed(300);
	
		
	}
	
	public void run() {
		/*int degree = 5000;
		while(true) {
				if (de.getStop()) {
					motor.forward();  
				}
			}*/
		while(true) {
			System.out.println(motorC.getTachoCount());
		}
	}
	
	public void fire() {
		//if (de.setFireAtWill){}
		//motor.rotate(this.degree);
		
	}
	
}
