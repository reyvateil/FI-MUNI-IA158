package radar;

import lejos.hardware.sensor.EV3IRSensor;
import lejos.robotics.SampleProvider;
import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import lejos.hardware.port.SensorPort;
/**
 * @author Gallo, Silhan
 * @version 2018
 * 
 * Class representing a task, that measures distance to the
 * IR beacon.
 */
public class MeasureProximity {
	
	private float[] sampleData;
	private EV3IRSensor sensor;
	private DataExchange de;
	
	/**
	 * Constructor
	 * @param de DataExchange object
	 */
	public MeasureProximity(DataExchange de) {
		this.sensor = new EV3IRSensor(SensorPort.S1);
		this.sampleData = new float[sensor.getSeekMode().sampleSize()];
		this.de = de;
	}

	/**
	 * Gets the distance to the IR beacon if in range, otherwise outputs infinity.
	 * If the IR beacon is within specified distance and angle (to reduce inaccurate
	 * readings) this distance is then passed via DataExchange to calculate an angle.
	 */
	public void run()  {
			sensor.getSeekMode().fetchSample(sampleData, 0);
			// 10 -- 80
			if (((sampleData[1] >= 10) && (sampleData[1] <= 75)) && 
				((sampleData[0] >= -2) && (sampleData[0] <= 2 ))) {

				de.setX(((int)sampleData[1]*2));
				de.status = Status.ALIGN;		
			}
	}
}
