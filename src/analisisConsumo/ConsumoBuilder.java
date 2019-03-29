package analisisConsumo;

import java.io.File;
import java.io.FileNotFoundException;
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
        
      //  String fileNameDefined = "C:/Users/Jose Gmz/Desktop/BASES/Biare/basepercapitatotal.csv";// per capita
        String fileNameDefined = "C:/Users/Jose Gmz/Desktop/BASES/Biare/baseHogInteg.csv"; //nivel hogar
     //   fileNameDefined = "C:/Users/Florian/Desktop/baseHogInteg.csv"; //nivel hogar

        // -File class needed to turn stringName to actual file
        File file = new File(fileNameDefined);

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
            	 int integrantes = Integer.parseInt(parts[2]); //quitar para analisis percapita
            	 int menores= Integer.parseInt(parts[3]); //hay menores de 6 años en el hogar?
            	 int per7a59 = Integer.parseInt(parts[4]); //hay personas de 7a59?
            	 int mayor60 = Integer.parseInt(parts[5]); //hay Mayores de 60 años?
            	 int enfermo = Integer.parseInt(parts[6]); // hay enfermos?
            	 
            	 //FIXME: aqui habria que agregar un dato mas y tener una variable mas en el builder
            	// System.out.printf("%s, %s \n", pos, gca);
            	           
            	  context.add(new Consumidor(pos,gca,integrantes, menores, per7a59, mayor60, enfermo));
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
    
    
    System.out.printf("Finished context of size %s\n", context.size());
            
    return context;  
}

 





}

        