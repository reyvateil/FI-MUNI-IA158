package radar;

public class Application {

	public synchronized static void main(String[] args) throws InterruptedException {
		
		DataExchange de = new DataExchange();
		MeasureProximity mp = new MeasureProximity(de);
		Scanner sc = new Scanner(de);
		
		FireUnit fu = new FireUnit(de);
		
		mp.start();
		sc.start();
		
		//fu.start();
		
		
		System.out.println("START");
		/* test branch update*/
		while(true) {

		}
	}

}
