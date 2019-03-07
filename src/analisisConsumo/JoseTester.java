package analisisConsumo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class JoseTester {

	public static void main(String[] args) {
		String path = "C:/Users/Jose Gmz/Desktop/BASES/Biare/bola.csv";
		Scanner scanner = null;
		
		try {
			scanner = new Scanner( new BufferedReader(new FileReader(path)));
		} catch (FileNotFoundException e) {
			System.out.println("I could not find the 10gpc.csv");
			e.printStackTrace();
			
		}
		
		
		scanner.useDelimiter(",");
		scanner.nextLine();
		
		for(int row=0;row<2;row++){
			scanner.next();
			for(int col=0;col<2;col++){
				double value = (double) scanner.nextDouble();;
				System.out.printf("[%s,%s] = %s", row,col,value);
				//Agent.mincer[row][col] = value;
		}
		//System.out.print("\n");
		}
		scanner.close();
		

	}

}
