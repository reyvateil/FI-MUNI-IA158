package radar;

import lejos.hardware.sensor.EV3IRSensor;
import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import lejos.hardware.port.SensorPort;

public class MeasureProximity extends Thread {
	
	private float[] distances;
	private EV3IRSensor sensor;
	private DataExchange de;

		
	public MeasureProximity(DataExchange de) {
		this.sensor = new EV3IRSensor(SensorPort.S1);
		this.distances = new float[sensor.sampleSize()];
		this.de = de;
	
	}
	
	public void run() {
		while(true) {
			//sensor.getSeekMode().fetchSample(distances, 0);
			sensor.getDistanceMode().fetchSample(distances, 0);
			
			//zkontroluje zda jiz nemame zamereny jiny objekt
			if (de.getPosOfScanWhenFindTarget() == Integer.MIN_VALUE){
				if (distances[0] <= 20 && distances[0] >= 5) {
					//System.out.println("d = " + distances[0] + "!!!");
					System.out.println(distances[0]);
					de.setStop(true);
					de.setAimTarget(true);
					de.setPosOfScanWhenFindTarget(de.getScannerMotorA().getTachoCount());
					//Sound.twoBeeps();
					//fireunit.fire();
				} else {
					de.setAimTarget(false);
					de.setStop(false);
					//System.out.println("d = " + distances[0]);
			
				}
			}
			
		}
	}
}
