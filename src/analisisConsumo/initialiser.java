package analisisConsumo;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Scanner;



public class initialiser {

	public static void main(String[] args) {
		String path = "C:/Users/Jose Gmz/Desktop/BASES/Biare/bola.csv";
		Scanner scanner = null;
		
		try {
			scanner = new Scanner(new BufferedReader(new FileReader(path)));
		} catch (FileNotFoundException e) {
			System.out.println("I could not find the file ");
			e.printStackTrace();
			
		}
		
	
		scanner.useDelimiter(",");
		scanner.nextLine();
		int pos;
		double gca;
		HashMap<Integer,Double> distribucion = new HashMap<Integer,Double>();
		
		while(scanner.hasNextLine()){
			
			pos = (int) Integer.parseInt(scanner.next());
			gca = (double) Double.parseDouble(scanner.next());
			
			Consumidor consumidor = new Consumidor(pos, gca);
			
		}
		//System.out.print("\n");
		
		scanner.close();
		
		

	}

}
