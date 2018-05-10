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
	final static private int v 	   			= 210;  // cm/s
	final static private int gravConstant 	= 1000; // cm/s^2
	final static private int y	= 25; // asi?
	
	private static boolean isWithinAllowedAngle(int angle) {
		return (angle >= minAngle && angle <= maxAngle);
	}
	
	public static int ballisticAngle(int x) {
		double vSquared = v*v;
		double squareRoot = Math.sqrt( (vSquared*vSquared) - gravConstant*(gravConstant*x*x + 2*y*v*v));
		
		int theta1 = (int) (Math.round(Math.toDegrees(Math.atan((vSquared + squareRoot)/(gravConstant*x)))));
		int theta2 = (int) (Math.round(Math.toDegrees(Math.atan((vSquared - squareRoot) / (gravConstant*x)))));
		
		System.out.println(theta1 + "  --  " + theta2);
		
		if(Wanad.isWithinAllowedAngle(theta1)) {
			return(theta1);
		} else if (Wanad.isWithinAllowedAngle(theta2)) {
			return(theta2);
		} else {
			System.out.println("Object out of range! ABORT");
		}
		return 0;

	}
}
