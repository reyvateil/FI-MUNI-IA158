package radar;

import lejos.hardware.sensor.EV3IRSensor;
import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import lejos.hardware.port.SensorPort;

public class MeasureProximity extends Thread {
	
	private float[] distances;
	private EV3IRSensor sensor;
	private DataExchange de;
	private FireUnit fireunit;
		
	public MeasureProximity(DataExchange de) {
		this.sensor = new EV3IRSensor(SensorPort.S1);
		this.distances = new float[sensor.sampleSize()];
		this.de = de;
		this.fireunit = new FireUnit(de);
	}
	
	public void run() {
		while(true) {
			sensor.getDistanceMode().fetchSample(distances, 0);
			if (distances[0] <= 50 && distances[0] >= 30) {
				//System.out.println("d = " + distances[0] + "!!!");
				de.setStop(true);
				//Sound.twoBeeps();
				fireunit.fire();
				//LCD.drawInt((int)distances[0], 10, 10);
			} else {
				de.setStop(false);
				//System.out.println("d = " + distances[0]);
			}
		}
	}
}
