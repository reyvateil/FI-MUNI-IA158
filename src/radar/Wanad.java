package radar;

import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.motor.Motor;
import lejos.hardware.motor.NXTRegulatedMotor;
import lejos.hardware.port.MotorPort;
import java.math.*;

/**
 * @author Gallo, Silhan
 * @version 2018
 * 
 * Weapon Angle Adjustor
 */
public class Wanad extends Thread {
	
	final static private int maxAngle 		=  45;
	final static private int minAngle 		= -30;
	final static private int v 	   			= 400; 
	final static private int gravConstant 	= 100; 
	final static private double vSquared = v*v;
	final static private int y	= 25;
	
	private static boolean isWithinAllowedAngle(int angle) {
		return (angle >= minAngle && angle <= maxAngle);
	}
	
	/**
	 * Implementation of ballistic functions
	 * 
	 * @param x int - distance from target
	 * @return int angle that should be set to AngleMotor
	 */
	public static int ballisticAngle(int x) {
		double squareRoot = Math.sqrt( (vSquared*vSquared) - gravConstant*(gravConstant*x*x + 2*y*v*v));
		
		int theta1 = (int) (Math.round(Math.toDegrees(Math.atan((vSquared + squareRoot) / (gravConstant*x)))));
		int theta2 = (int) (Math.round(Math.toDegrees(Math.atan((vSquared - squareRoot) / (gravConstant*x)))));
		
		System.out.println(x + " > " +theta1 + "  --  " + theta2);
		
		int theta = theta2;
		if(Wanad.isWithinAllowedAngle(theta1) && Wanad.isWithinAllowedAngle(theta2)) {
			if (Math.abs(theta1) < Math.abs(theta2)) {
				theta = theta1;
			}
			return(theta);
		}
		else if(Wanad.isWithinAllowedAngle(theta1)) {
			return(theta1);
		}
		else if(Wanad.isWithinAllowedAngle(theta2)) {
			return(theta2);
		} else {
			System.out.println("Range!");
		}
		return Integer.MIN_VALUE;


	}
}
