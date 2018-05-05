package radar;

import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.motor.Motor;
import lejos.hardware.motor.NXTRegulatedMotor;
import lejos.hardware.port.MotorPort;
import java.math.*;

/**
 * Weapon Angle Adjustor
 */
public class Wanad extends Thread {

	//private EV3MediumRegulatedMotor motor = new EV3MediumRegulatedMotor(MotorPort.B);
	//private NXTRegulatedMotor motor;
	private AngleMotor motor;
	private DataExchange de;
	
	final private int maxAngle =  45;
	final private int minAngle = -30;
	final private int v = 210; // cm/s
	final private int gravConstant = 1000; // cm/s^2
	
	public Wanad(DataExchange de) {
		this.de = de;
		this.motor = new AngleMotor(de);
		this.motor.setSpeed(100);
	}
	
	private boolean isWithinAllowedAngle(int angle) {
		return (angle >= minAngle && angle <= maxAngle);
	}
	
	private int ballisticAngle(int x, int y) {
		//https://en.wikipedia.org/wiki/Projectile_motion#Angle_'"`UNIQ--postMath-0000003A-QINU`"'_required_to_hit_coordinate_(x,y)
		double vSquared = v*v;
		double squareRoot = Math.sqrt( (vSquared*vSquared) - gravConstant*(gravConstant*x*x + 2*y*v*v));
		int theta1 =(int) (Math.round(Math.toDegrees(Math.atan((vSquared + squareRoot)/(gravConstant*x)))));
		int theta2 = (int) (Math.round(Math.toDegrees(Math.atan((vSquared - squareRoot) / (gravConstant*x)))));
		System.out.println(theta1 + "  --  " +theta2);
		if(isWithinAllowedAngle(theta1)) {
			return(theta1);
		} else if (isWithinAllowedAngle(theta2)) {
			return(theta2);
		} else {
			// do nothing
			System.out.println("Object out of range! ABORT");
			de.resetMeasuredValues();
		}
		return 0;
		
		//return 60;
	}
	
	public void run() {
		motor.start();
		
		while(true) {
			
			if(de.isAimTarget()) {
				int x = de.getX();
				int y = de.getY();
				//motor.rotate(ballisticAngle(x, y), true); predani uhlu tride fireunit 
				motor.setAngle(ballisticAngle(x, y));
				//de.setAimTarget(false);
				//de.setFireAtWill(true);
				Thread.yield();
			}
		}
	}
}
