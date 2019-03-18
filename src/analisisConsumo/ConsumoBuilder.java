package analisisConsumo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

import repast.simphony.context.Context;
import repast.simphony.dataLoader.ContextBuilder;
import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.parameter.Parameters;

public class ConsumoBuilder implements ContextBuilder<Object> {
	
	@Override
    public Context build(Context<Object> context) {
        context.setId ("AnalisisConsumo");
        Parameters params = RunEnvironment.getInstance().getParameters();
        
        HashMap<Integer,Double> datos= getHM();
        System.out.printf("The size of the hashmap is %s\n",datos.size());
        
        for (HashMap.Entry<Integer, Double> entry : datos.entrySet()) {
        //	System.out.printf("key: %s, value: %s", entry.getKey(), entry.getValue());
        	//crear consumidores
        	context.add(new Consumidor(entry.getKey(),entry.getValue()));
        	
        }
        
        System.out.printf("Finished context of size %s\n", context.size());
                
        return context;  
    }
	
	 public  HashMap<Integer,Double> getHM(){
 		//String fileNameDefined = "C:/Users/Jose Gmz/Desktop/BASES/Biare/bola100.csv";
		 String fileNameDefined = "C:/Users/Jose Gmz/Desktop/BASES/Biare/basepercapitatotal.csv";
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
             	// System.out.printf("%s, %s \n", pos, gca);
             	  datos.put(pos, gca);          	
             	 //System.out.println(pos);
             	 //System.out.println(gca);
             	     	 
             	 //	datos.put(Integer.parseInt(data.split(",")[0]), Double.parseDouble(data.split(",")[1]));

             	// System.out.println(data);
             	 //System.out.println();
             }
             
             // after loop, close scanner
             scanner.close();


         }catch (FileNotFoundException e){

             e.printStackTrace();
         }
         return datos;
 	}

	
	
	

}
