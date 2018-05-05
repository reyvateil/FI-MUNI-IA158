package radar;

import lejos.hardware.motor.Motor;
import lejos.hardware.motor.NXTRegulatedMotor;

public class Application {

	public synchronized static void main(String[] args) throws InterruptedException {
		
		DataExchange de = new DataExchange();
		MeasureProximity mp = new MeasureProximity(de);
		Scanner sc = new Scanner(de);
	
		Wanad wa = new Wanad(de);
		FireUnit fu = new FireUnit(de);
		
		
		
		mp.start();
		sc.start();
		wa.start();
		fu.start();
		//anglemotor.start();
		
		
		
		System.out.println("START");
		/* test branch update*/
		while(true) {
			
		}
	}

}
