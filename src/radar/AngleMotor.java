package radar;

import lejos.hardware.motor.Motor;
import lejos.hardware.motor.NXTRegulatedMotor;

public class AngleMotor extends Thread {
	private NXTRegulatedMotor motorB = Motor.B;
	private final int step = 1; 

	//maximalni uhel nahoru
	private int topTreshold = motorB.getTachoCount();
	//maximalni uhel dolu
	private int downTreshold = motorB.getTachoCount()+50;
	
	public AngleMotor() {
		motorB.setSpeed(100);
			
	}
		
	
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
			
			
			if ((motorB.getTachoCount() >= topTreshold) && ((motorB.getTachoCount() <= downTreshold))) {
				System.out.println("Moving");
				motorB.rotateTo(direction, true);
				motorB.waitComplete();
				}
			if (direction == downTreshold) {
				direction = topTreshold;
			}else {
				direction = downTreshold;
			}
			
			
		}
		
			
		
	}
}
