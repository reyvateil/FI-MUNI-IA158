package radar;

import lejos.hardware.motor.NXTRegulatedMotor;
/**
 * @author Gallo, Silhan
 * @version 2018
 * 
 * Class which is used to save needed data.
 */
public class DataExchange {
	
	Status status = null;
	
	// Toto je len testovacia premenna, ktora sa nastavi na true
	// vtedy ak sa nejaky objekt nachadza 30-50cm vzdialenosti
	// Vo finalnom produkte by sa nemala nachadzat
	private boolean stopRadar;
	
	// Premenna, ktora da triede Wanad vediet, ze ma vypocitat uhol theta
	// a nastavit tento uhol na motore
	private boolean aimTarget;
	
	// Premenna, ktora da triede FireUnit vediet, ze moze strielat
	// blizsie nespecifikovane -- bud sa nastavi v okamih ked su
	// motory v spravnej pozicii alebo predtym, s tym, ze nevystreli
	// skor/neskor 
	private boolean fireAtWill;
	
	// Vzdialenost merana IR senzorom
	private int distanceToTarget;
	
	// Uhol theta (v stupnoch), ktory sa ma nastavit na motore B
	private int weaponAngle;
	
	// Vzdialenost objektu od dela
	private int x;
	
	// Relativna vertikalna vzdialenost voci delu (delo je v [0,0])
	private int y;
	
	// Pozice motoru kdyz objevil cil
	private int positionOfScanMotorWhenFindTarget = Integer.MIN_VALUE;
	
	private NXTRegulatedMotor scannerMotorA;
	
	public NXTRegulatedMotor getScannerMotorA() {
		return scannerMotorA;
	}



	public void setScannerMotorA(NXTRegulatedMotor nxtRegulatedMotor) {
		this.scannerMotorA = nxtRegulatedMotor;
	}



	public DataExchange() {
		stopRadar = false;
		aimTarget = false;
		fireAtWill = false;
		status = Status.MEASURE;
	}
	
	
	
	public boolean isStopRadar() {
		return stopRadar;
	}



	public void setStopRadar(boolean stopRadar) {
		this.stopRadar = stopRadar;
	}



	public boolean isAimTarget() {
		return aimTarget;
	}



	public void setAimTarget(boolean aimTarget) {
		this.aimTarget = aimTarget;
	}



	public boolean isFireAtWill() {
		return fireAtWill;
	}


	public void setFireAtWill(boolean fireAtWill) {
		this.fireAtWill = fireAtWill;
	}



	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	
	public boolean getStop() {
		return stopRadar;
	}
	
	public void setStop(boolean val) {
		stopRadar = val;
	}
	
	public int getAngle() {
		return weaponAngle;
	}
	
	public void setAngle(int angle) {
		weaponAngle = angle;
	}



	public int getPosOfScanWhenFindTarget() {
		return positionOfScanMotorWhenFindTarget;
	}



	public void setPosOfScanWhenFindTarget(int positionOfScanMotorWhenFindTarget) {
		this.positionOfScanMotorWhenFindTarget = positionOfScanMotorWhenFindTarget;
	}
	
	public void resetMeasuredValues() {
		x = 0;
		y = 0;
		distanceToTarget = -1;
		setAimTarget(false);
	}

}
