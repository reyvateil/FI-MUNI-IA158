package radar;

import lejos.hardware.motor.Motor;
import lejos.hardware.motor.NXTRegulatedMotor;

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
	
	
	public AngleMotor(DataExchange de) {
		this.de = de;
		readyToAim = false;
		motorB.setSpeed(50);
		motorB.rotateTo(defaultPosition, true);
		motorB.waitComplete();
		motorB.resetTachoCount();
		this.defaultPosition = 0;
		
		motorB.setSpeed(500);
		
		//defaultPosition();
	}
	
	
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
	
	public boolean defaultPosition() {
		if ((motorB.getTachoCount() >= 1) || ( motorB.getTachoCount() <= - 1)){
			motorB.rotateTo(defaultPosition,true);
			motorB.waitComplete();
		}
		return true;
	}
	
}
