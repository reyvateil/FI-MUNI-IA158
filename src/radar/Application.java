package radar;

public class Application {

	public static void main(String[] args) throws InterruptedException {
		
		DataExchange de = new DataExchange();
		RadarLogic rl = new RadarLogic(de);
		Scanner sc = new Scanner(de);
		
		sc.start();
		rl.start();
		
		System.out.println("START");
		
		/* test branch update*/
		while(true) {
			
		}
	}

}
