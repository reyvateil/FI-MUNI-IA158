package radar;
/**
 * 
 * @author Gallo, Silhan
 * @version 2018
 * 
 * The main class where everything is started.
 */
public class Application {
	/**
	 * Main method
	 * @param args
	 * @throws InterruptedException
	 */
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
