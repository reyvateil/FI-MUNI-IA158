package radar;

import lejos.hardware.motor.NXTRegulatedMotor;
/**
 * @author Gallo, Silhan
 * @version 2018
 * 
 * Utility Class to exchange information between threads.
 */
public class DataExchange {
	
	Status status = null;
	// Toto je len testovacia premenna, ktora sa nastavi na true
	// vtedy ak sa nejaky objekt nachadza 30-50cm vzdialenosti
	// Vo finalnom produkte by sa nemala nachadzat
	private boolean stopRadar;


	// Uhol theta (v stupnoch), ktory sa ma nastavit na motore B
	private int weaponAngle;
	
	// Vzdialenost objektu od dela
	private int x;
	
	private NXTRegulatedMotor scannerMotorA;
	
	/**
	 * Get scanner motor
	 * @return NXTRegulatedMotor Scanner motor
	 */
	public NXTRegulatedMotor getScannerMotorA() {
		return scannerMotorA;
	}


	/**
	 * Set scanner motor
	 * @param nxtRegulatedMotor NXTRegulatedMotor motor for moving with the scanner
	 */
	public void setScannerMotorA(NXTRegulatedMotor nxtRegulatedMotor) {
		this.scannerMotorA = nxtRegulatedMotor;
	}


	/**
	 * Constructor of DataExchange
	 */
	public DataExchange() {
		stopRadar = false;
		status = Status.MEASURE;
	}
	
	/**
	 * Get the distance to target
	 * @return x int distance to target
	 */
	public int getX() {
		return x;
	}

	/**
	 * Set the distance to target
	 * @param x int distance to target
	 */
	public void setX(int x) {
		this.x = x;
	}
	
	
	public boolean getStop() {
		return stopRadar;
	}
	
	public void setStop(boolean val) {
		stopRadar = val;
	}
	
	


	

	

}
