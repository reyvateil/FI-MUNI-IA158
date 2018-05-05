package radar;

import lejos.hardware.motor.Motor;
import lejos.hardware.motor.NXTRegulatedMotor;

public class AngleMotor extends Thread {
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
		motorB.rotateTo(defaultPosition,true);
		motorB.waitComplete();
		motorB.resetTachoCount();
		this.defaultPosition = 0;
		
		setSpeed(500);
		
		defaultPosition();
	}
	
	
	public void run(){
		// > 0 zneamena jit s motorem do plusu, coz je pohyb dolu
	
		while(true) {

			if(de.isAimTarget() && isReadyToAim()) {
				//motorB.rotateTo(angle,true);
				motorB.rotateTo(-de.getAngle(),true);
				motorB.waitComplete();
			
				de.setAimTarget(false);
				de.setFireAtWill(true);
				setReadyToAim(false);
				
			}

		}
		
			
		
	}
	
	public boolean defaultPosition() {
		if ((motorB.getTachoCount() >= 1) || ( motorB.getTachoCount() <= - 1)){
			motorB.rotateTo(defaultPosition,true);
			motorB.waitComplete();
		}
		return true;
	}
	
		
	public boolean isReadyToAim() {
		return readyToAim;
	}

	public void setReadyToAim(boolean readyToAim) {
		this.readyToAim = readyToAim;
	}

	
	public void setSpeed(int speed) {
		this.speed = speed;
		this.motorB.setSpeed(speed);
	}
	
	public void setAngle(int angle) {
		this.angle = -angle;
		this.setReadyToAim(true);
	
	}
	
	public int getAngle() {
		return angle;
	}
}
