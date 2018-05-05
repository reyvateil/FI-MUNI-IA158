package radar;

import lejos.hardware.sensor.EV3IRSensor;
import lejos.robotics.SampleProvider;
import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import lejos.hardware.port.SensorPort;

public class MeasureProximity extends Thread {
	
	//private float[] distances;
	private float[] sampleData;
	private EV3IRSensor sensor;
	private DataExchange de;

	private SampleProvider sp;
	private int beaconHeading;
	private int beaconDistance;
	private int maxRange = 75;
		
	public MeasureProximity(DataExchange de) {
		this.sensor = new EV3IRSensor(SensorPort.S1);
		//this.distances = new float[sensor.sampleSize()];
		//this.sp = sensor.getSeekMode();
		this.sampleData = new float[sensor.getSeekMode().sampleSize()];
		this.de = de;
		this.setPriority(8);
	
	}
	// Measure proximity of a general object
	/*
	public void run() {
		while(true) {
			
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
	}*/
	
	// Distance from beacon
	public void run() {
		while(true) {
			
			
			sensor.getSeekMode().fetchSample(sampleData, 0);
			
			//if (de.getPosOfScanWhenFindTarget() == Integer.MIN_VALUE) {
				//Measure the distance from beacon when the head is in 0° +- 2
			if (((sampleData[1] >= 10) && (sampleData[1] <= 80)) && ((sampleData[0] >= -2)&&(sampleData[0] <= 2))) {

				de.setStop(true);
				de.setDistance((int)sampleData[1]*2);
				de.setX(((int)sampleData[1]*2));
				de.setY(25);
				de.setAimTarget(true);
				//de.setPosOfScanWhenFindTarget(de.getScannerMotorA().getTachoCount());
			}else {
				de.setAimTarget(false);
				de.setStop(false);
				//System.out.println("d = " + distances[0]);
			}
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}
