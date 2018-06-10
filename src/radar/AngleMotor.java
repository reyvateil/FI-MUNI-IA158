package radar;

import lejos.hardware.motor.Motor;
import lejos.hardware.motor.NXTRegulatedMotor;
/**
 * @author Gallo, Silhan
 * @version 2018
 * 
 * Classes used to adjusting a launcher angle to a correct position before shooting.
 */
public class AngleMotor{
	private NXTRegulatedMotor motorB = Motor.B;
	private int speed;
	private DataExchange de;
	private boolean readyToAim;
	
	//maximalni uhel nahoru
	private int topTreshold = motorB.getTachoCount();
	//maximalni uhel dolu
	private int downTreshold = motorB.getTachoCount()+50;
	private int defaultPosition = 45;
	private int angle;
	
	/**
	 * Constructor
	 * @param de DataExchange object
	 */
	public AngleMotor(DataExchange de) {
		this.de = de;
		readyToAim = false;
		motorB.setSpeed(50);
		motorB.rotateTo(defaultPosition, true);
		motorB.waitComplete();
		motorB.resetTachoCount();
		this.defaultPosition = 0;
		motorB.setSpeed(500);
	}
	
	/**
	 * Rotate the launcher to a correct angle
	 */
	public void run(){
		// > 0 zneamena jit s motorem do plusu, coz je pohyb dolu
		int theta = Wanad.ballisticAngle(de.getX());
		if(theta != Integer.MIN_VALUE) {
			motorB.rotateTo(-theta, false);
			de.status = Status.FIRE;
		} else {
			de.status = Status.MEASURE;
		}
	}
	/**
	 * Set motor to a default angle
	 * @return boolean True if the motor is set to defaul position
	 */
	public boolean defaultPosition() {
		if ((motorB.getTachoCount() >= 1) || ( motorB.getTachoCount() <= - 1)){
			motorB.rotateTo(defaultPosition,true);
			motorB.waitComplete();
		}
		return true;
	}
	
}
