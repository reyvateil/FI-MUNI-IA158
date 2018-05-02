package radar;

public class DataExchange {
	private boolean stopRadar;
	private boolean aimTarget;
	private boolean fireAtWill;
	
	
	private int distanceToTarget;
	private int weaponAngle;
	private int x;
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
