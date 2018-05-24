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
	
	final static private int maxAngle 		=  45;
	final static private int minAngle 		= -30;
	final static private int v 	   			= 4000;  // cm/s
	final static private int gravConstant 	= 200; // cm/s^2
	final static private double vSquared = v*v;
	final static private int y	= 25; // asi?
	
	private static boolean isWithinAllowedAngle(int angle) {
		return (angle >= minAngle && angle <= maxAngle);
	}
	
	public static int ballisticAngle(int x) {
		double squareRoot = Math.sqrt( (vSquared*vSquared) - gravConstant*(gravConstant*x*x + 2*y*v*v));
		
		int theta1 = (int) (Math.round(Math.toDegrees(Math.atan((vSquared + squareRoot) / (gravConstant*x)))));
		int theta2 = (int) (Math.round(Math.toDegrees(Math.atan((vSquared - squareRoot) / (gravConstant*x)))));
		
		System.out.println(x + " > " +theta1 + "  --  " + theta2);
		
		int theta = theta2;
		if (Math.abs(theta1) < Math.abs(theta2)) {
			theta = theta1;
		}
		
		if(Wanad.isWithinAllowedAngle(theta)) {
			return(theta);
		} else {
			System.out.println("Range!");
		}
		return Integer.MIN_VALUE;

	}
}
