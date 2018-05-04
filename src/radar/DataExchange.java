package radar;

public class DataExchange {
	
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
	
	
	public DataExchange() {
		stopRadar = false;
		aimTarget = false;
		fireAtWill = false;
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
	
	public int getY() {
		return y;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public boolean getStop() {
		return stopRadar;
	}
	
	public void setStop(boolean val) {
		stopRadar = val;
	}
	
	public int getDistance() {
		return distanceToTarget;
	}
	
	public void setDistance(int distance) {
		distanceToTarget = distance;
	}
	
	public int getAngle() {
		return weaponAngle;
	}
	
	public void setAngle(int angle) {
		weaponAngle = angle;
	}
}
