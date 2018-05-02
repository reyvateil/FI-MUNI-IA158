package radar;

import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.MotorPort;

/**
 * Weapon Angle Adjustor
 */
public class Wanad extends Thread {

	private EV3MediumRegulatedMotor motor = new EV3MediumRegulatedMotor(MotorPort.B);
	private DataExchange de;
	
	final private int maxAngle =  75;
	final private int minAngle = -30;
	final private int v = 3; // m/s
	final private int gravConstant = 10; // m/s^2
	
	public Wanad(DataExchange de) {
		this.de = de;
		motor.setSpeed(100);
	}
	
	private boolean isWithinAllowedAngle(int angle) {
		return (angle >= minAngle && angle <= maxAngle);
	}
	
	private int ballisticAngle(int x, int y) {
		double vSquared = v*v;
		double squareRoot = Math.sqrt( (vSquared*vSquared) - gravConstant*(gravConstant*x*x + 2*y*v*v));
		int theta1 = Math.toIntExact(Math.round(Math.toDegrees(Math.atan((vSquared + squareRoot)/(gravConstant*x)))));
		int theta2 = Math.toIntExact(Math.round(Math.toDegrees(Math.atan((vSquared - squareRoot) / (gravConstant*x)))));
		
		if(isWithinAllowedAngle(theta1)) {
			return(theta1);
		} else if (isWithinAllowedAngle(theta2)) {
			return(theta2);
		} else {
			// do nothing
		}
		return 0;
	}
	
	public void run() {
		while(true) {
			if(de.isAimTarget()) {			
				int x = de.getX();
				int y = de.getY();
				motor.rotate(ballisticAngle(x, y), true);
				de.setAimTarget(false);
				de.setFireAtWill(true);
				Thread.yield();
			}
		}
	}
}
