package radar;

import lejos.hardware.sensor.EV3IRSensor;
import lejos.robotics.SampleProvider;
import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import lejos.hardware.port.SensorPort;

public class MeasureProximity {
	
	private float[] sampleData;
	private EV3IRSensor sensor;
	private DataExchange de;
		
	public MeasureProximity(DataExchange de) {
		this.sensor = new EV3IRSensor(SensorPort.S1);
		this.sampleData = new float[sensor.getSeekMode().sampleSize()];
		this.de = de;
	}

	// Distance from beacon
	public void run()  {
			sensor.getSeekMode().fetchSample(sampleData, 0);
			
			if (((sampleData[1] >= 10) && (sampleData[1] <= 80)) && 
				((sampleData[0] >= -2) && (sampleData[0] <= 2 ))) {

				de.setX(((int)sampleData[1]*2));
				de.status = Status.ALIGN;
				Thread.yield();			
			}
	}
}
