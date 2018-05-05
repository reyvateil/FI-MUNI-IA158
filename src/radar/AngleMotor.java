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
	private int defaultPosition = (downTreshold - topTreshold)/2;
	private int angle;
	
	
	public AngleMotor(DataExchange de) {
		this.de = de;
		readyToAim = false;
		speed = 500;
	}
	/*	
	public AngleMotor(DataExchange de,int speed) {
		this.de = de;
		this.speed = speed;
	}
	
	public AngleMotor(DataExchange de,int speed, int angle) {
		this.de = de;
		this.speed = speed;
		this.angle = angle;
	}
	*/
	
	public void run(){
		// > 0 zneamena jit s motorem do plusu, coz je pohyb dolu
		int direction = downTreshold;
		while(true) {
			/*System.out.println("position" + motorB.getTachoCount());
			if ((motorB.getTachoCount() == topTreshold) && (direction < 0)) {
				motorB.rotate(topTreshold + step);
			}else if ((motorB.getTachoCount() == downTreshold) && (direction > 0)) {
				motorB.rotate(topTreshold - step);
			}else if ((motorB.getTachoCount() >= topTreshold) && (motorB.getTachoCount() <= downTreshold) && (direction > 0) ) {
				//motorB.rotate(motorB.getTachoCount()+step);
				motorB.rotateTo(downTreshold);
			}else if(((motorB.getTachoCount() >= topTreshold) && (motorB.getTachoCount() <= downTreshold) && (direction < 0) )) {
				//motorB.rotate(motorB.getTachoCount()-step);
				motorB.rotateTo(topTreshold);
			}else if ((motorB.getTachoCount() == topTreshold) && (direction > 0)){
				motorB.rotate(motorB.getTachoCount()+step);*/
			
			/*
			if ((motorB.getTachoCount() >= topTreshold) && ((motorB.getTachoCount() <= downTreshold))) {
				System.out.println("Moving");
				motorB.rotateTo(direction, true);
				motorB.waitComplete();
				}
			if (direction == downTreshold) {
				direction = topTreshold;
			}else {
				direction = downTreshold;
			}*/
			
			if(de.isAimTarget() && isReadyToAim()) {
				//motorB.rotateTo(angle,true);
				motorB.rotateTo(getAngle(),true);
				motorB.waitComplete();
				de.setAimTarget(false);
				de.setFireAtWill(true);
				setReadyToAim(false);
			}
			
			
			if(! de.isFireAtWill()) {
				motorB.rotateTo(defaultPosition,true);
			}
		}
		
			
		
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
		/*
		 * 
		 * tady to bude chtit prepocitat ty uhly
		 */
		this.angle = angle;
		this.setReadyToAim(true);
	
	}
	
	public int getAngle() {
		return angle;
	}
}
