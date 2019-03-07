package analisisConsumo;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

import com.jogamp.common.util.LongLongHashMap;

public class tester2 {

	 public static void main(String[] args) {
	        // -define .csv file in app
	        String fileNameDefined = "C:/Users/Jose Gmz/Desktop/BASES/Biare/bola.csv";
	        // -File class needed to turn stringName to actual file
	        File file = new File(fileNameDefined);
	        HashMap<Integer,Double> datos = new HashMap<Integer,Double>();

	        try{
	        	
	        	
	            // -read from filePooped with Scanner class
	            Scanner scanner = new Scanner(file);
	            
	            //scanner.useDelimiter(",");
	    		scanner.nextLine();
	            // hashNext() loops line-by-line
	            while(scanner.hasNext()){
	                //read single line, put in string
	            	 String data = scanner.next();
	            	 
	            	 String[] parts = data.split(",");
	            	 int pos = Integer.parseInt(parts[0]); // 123 Integer.parseInt(parts[0])
	            	 double gca = Double.parseDouble(parts[1]); // 654321 double value = Double.parseDouble(parts);
	            	 System.out.printf("%s, %s \n", pos, gca);
	            	 //System.out.println(pos);
	            	 //System.out.println(gca);
	            	     	 
	            //	datos.put(Integer.parseInt(data.split(",")[0]), Double.parseDouble(data.split(",")[1]));

	            	// System.out.println(data);
	            	 //System.out.println();
	            }
	            for (HashMap.Entry<Integer, Double> entry : datos.entrySet()) {
	            	System.out.printf("key: %s, value: %s", entry.getKey(), entry.getValue());

	            }
	            // after loop, close scanner
	            scanner.close();


	        }catch (FileNotFoundException e){

	            e.printStackTrace();
	        }

	    }
	}


