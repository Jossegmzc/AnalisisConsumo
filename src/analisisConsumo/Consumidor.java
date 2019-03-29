package analisisConsumo;

import java.util.HashMap;
import java.util.HashSet;

import cern.colt.Arrays;
import repast.simphony.context.Context;
import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.engine.schedule.ScheduledMethod;
import repast.simphony.parameter.Parameters;
import repast.simphony.random.RandomHelper;
import repast.simphony.util.ContextUtils;

public class Consumidor {
	//double pphog; //integrantes en el hogar.
	double gca; //cantidad total designada al consumo
	/**percentil en la distribución del gasto total anual de consumo.*/
	int pos; 
	/** Influenciabilidad*/
	double inf;
	/**consumo de Alimentos en el hogar*/
	double clase1; 
	/**Alimentos fuera del hogar*/
	double clase2; 
	/** Hogar y vestido */
	double clase3; 
	/**Educacion*/
	double clase4; 
	/**Salud*/
	double clase5; 
	/**Seguridad*/
	double clase6; 
	/**Transporte privado*/	
	double clase7; //
	/**Transporte publico*/
	double clase8; //
	/**Plantas, mascotas, recreación fuera del hogar, alcohol y vicios */
	double clase9; //
	/**Comunicación, tecnología, juguetes y artículos de entretenimiento*/
	double clase10; //
	
	
	HashMap<Integer,Double> consumoPromedioVecinos = new HashMap<Integer,Double>();
	
	int integrantes;
	
	int menores ;
	int persona7a59;
	int mayor60;
	int enfermo;
	
	
	
	
	public Consumidor( int pos, double gca, int integrantes, int menores, int persona7a59, int mayor60, int enfermo ) {
		
		//this.pphog = pphog;
		this.gca = gca;
		this.pos = pos;
		this.inf = RandomHelper.createUniform(0, 1).nextDouble();
		this.clase1 = 0;
		this.clase2 = 0;
		this.clase3 = 0;
		this.clase4 = 0;
		this.clase5 = 0;
		this.clase6 = 0;
		this.clase7 = 0;
		this.clase8 = 0;
		this.clase9 = 0;
		this.clase10 = 0;
		this.integrantes = 1;
		this.menores = 0;
		this.persona7a59 = 0;
		this.mayor60 = 0;
		this.enfermo= 0;
		
		
		
		
		
		
	}
	
	 Parameters params = RunEnvironment.getInstance().getParameters();
	int enfoque = params.getInteger("enfoque");//esto será un parámetro o una variable contadora que haga que se optimice según todos los enfoques
	double lbm = params.getDouble("bienmin");
	int rango = params.getInteger("rango");
	int nivelSimulacion = params.getInteger("nivel"); //1 para percapita, 2 para nivel hogar
	
	//double lbm = 5000; 
	//methods

		
	@ScheduledMethod(start=1,interval=1,shuffle=true,priority=95)
	public void stepPromedioVecinos() {
		
		//FIXME: you might want to add an if-clause here to limit the execution of this script to 'enfoques' where it is actually required
		// Define range of pos
		int maxPos = this.pos + rango; //FIXME: en lugar de poner 10, sería bueno ligar eso a un parámetro. Pueden ser 2, uno para arriba y otro para abajo
		int minPos = this.pos - rango;
		System.out.printf("Individual %s is computing the average of others\n",this.pos);
		
		double[] sum = new double[11];
		int n = 0;
		
		Context<?> myContext = ContextUtils.getContext(this);
		for(Object candidate : myContext.getObjects(Consumidor.class)) {
			Consumidor c = (Consumidor)candidate;
			
			if(c.pos>=minPos && c.pos>0 && c.pos<58898 && c.pos<=maxPos && c!=this) {
				n++;
				sum[1] += c.clase1;
				sum[2] += c.clase2;
				sum[3] += c.clase3;
				sum[4] += c.clase4;
				sum[5] += c.clase5;
				sum[6] += c.clase6;
				sum[7] += c.clase7;
				sum[8] += c.clase8;
				sum[9] += c.clase9;
				sum[10] += c.clase10;
				//System.out.printf("After pos %s: %s\n",c.pos,Arrays.toString(sum));
			}
		} //end of loop over all candidates
		
		// Compute the average and store it directly in the local variable
		for(int i=1;i<=10;i++){
			this.consumoPromedioVecinos.put(i,sum[i]/n);
			//System.out.printf("%s,",sum[i]/n);
		}
		//System.out.println("End of method"); 
		
	}
	
	/**Método mediante los consumidores deciden su proporción de consumo*/
	@ScheduledMethod(start=1,interval=1,shuffle=true,priority=90)
	public void consumo() {
	switch (enfoque) {
	
		/**Caso Funcion de utilidad, Cobb Douglas, 1928*/
		case 1 : 
			
			clase1 = gca*0.3739; 
			clase2 = gca*0.0781; 
			clase3 = gca*0.2037; 
			clase4 = gca*0.0341; 
			clase5 = gca*0.0846; 
			clase6 = gca*0.0260; 
			clase7 = gca*0.0681; 
			clase8 = gca*0.0431; 
			clase9 = gca*0.0309; 
			clase10 = (gca-clase1 - clase3 - clase6 - clase8 - clase5 -clase4-clase2-clase7-clase9) ; break;	
			
			
		/**Jerarquía de necesidades, Maslow,1943*/
		case 2 : if (gca > lbm){
			
		
			clase1 = integrantes*gca*(1-(gca-lbm)/gca) + gca*0.18; 
			if(gca-clase1 > 0) { 
				clase3= integrantes*(gca-clase1)*0.3389;
				clase5 = integrantes*(gca-clase1)*0.1408;
				clase6 = integrantes*(gca-clase1)*0.0438;
				clase8 = integrantes*(gca-clase1)*0.0713; 
					if(gca-clase1-clase3-clase5-clase6-clase8 > 0) {
						clase2 =integrantes*(gca-clase1-clase3-clase5-clase6-clase8)*0.1215; 
						clase10 = integrantes*(gca-clase1-clase3-clase5-clase6-clase8)*0.1503;
							if (gca - clase1 - clase3 - clase5- clase2-clase6-clase8-clase10 > 0 ){ 
								clase4 = integrantes*(gca - clase1 - clase3 - clase5- clase2 - clase6 - clase8 - clase10)*0.2057; 
								clase7 = integrantes*(gca - clase1 - clase3 - clase5- clase2 - clase6 - clase8 - clase10)*0.5148; 
								clase9=(gca - clase1 - clase2 - clase3- clase4 - clase5 - clase6 - clase7- clase8 - clase10); 
							}
					
					}	
			
			
			}
		}
		else {
			clase1=gca;
		};   break;
			/**Enfoque de prioridades, Chávez-Juárez 2018*/
		case 3 : 
		if (gca>lbm) {
			clase1 = integrantes*lbm*0.5299 + integrantes*(gca-lbm)*.3503; //Alimentos en el hogar
			clase3 = lbm*0.2035 + (gca-lbm)*0.2035;  //hogar y vestido
			clase6 = lbm*0.02 + (gca-lbm)*0.0261; // seguridad
			clase8 = lbm*0.0511 + (gca-lbm)*0.043; //transporte público. Consumo minimo vital
				if(gca-clase1 - clase3 - clase6 - clase8 > 0) {
					clase5 = (gca-clase1 - clase3 - clase6 - clase8)*0.2585; //consumo Salud (anterior .2585)
					if (gca-clase1 - clase3 - clase6 - clase8 - clase5 > 0) {
						clase4 = (gca-clase1 - clase3 - clase6 - clase8 - clase5)*0.1696; //consumo Educación
						if(gca-clase1 - clase3 - clase6 - clase8 - clase5 -clase4 > 0) {  //consumo adicional
							clase2 = (gca-clase1 - clase3 - clase6 - clase8 - clase5 -clase4)*0.3334;  
							clase10 = (gca-clase1 - clase3 - clase6 - clase8 - clase5 -clase4)*0.2076;
							clase9 = (gca-clase1 - clase3 - clase6 - clase8 - clase5 -clase4)*0.1308;
							clase7 = (gca-clase1 - clase3 - clase6 - clase8 - clase5 -clase4-clase2-clase10-clase9);	
						
						}
					
					}
			
				}
		}
		else { clase1 = gca*0.5299;
		clase3 = gca*0.2235;
		clase6=gca*0.02; 
		clase8=gca*0.045;
		clase5 = (gca-clase1-clase3-clase6-clase8)*(0.58);
		clase9 = (gca-clase1-clase3-clase6-clase8)*(0.1053);
		clase2 = (gca-clase1-clase3-clase6-clase8)*0.1111;
		clase4 = (gca-clase1-clase3-clase6-clase8)*0.14;
		clase10 = gca-clase1-clase2-clase3-clase4-clase5-clase6-clase8-clase9 ; //.1755

			
		}; break;
		
		/**Valores de consumo, Sheth et al (1991)  */
		case 4 :  
			
			if (gca > lbm && consumoPromedioVecinos.get(1)>0 ) { //valor funcional mas valor social
			clase1 = (1-inf)*(gca*0.5299 -pos*gca*.00000747*0.5299) + inf*consumoPromedioVecinos.get(1) ; 			
			clase2 = (1-inf)*(gca*0.74*0.0792 + gca*pos*0.00001697*0.1192) + inf*consumoPromedioVecinos.get(2) ; 
			clase3 = (1-inf)*(gca*0.66*0.2035 + gca*0.1423*0.2035)+ inf*consumoPromedioVecinos.get(3); 
			clase4 = (1-inf)*(gca*0.0344*0.5 + gca*pos*0.000008347*0.0344)+ inf*consumoPromedioVecinos.get(4); 
			clase5 = (1-inf)*(gca*0.0847*0.61+ gca*0.0847*0.0385)+ inf*consumoPromedioVecinos.get(5); 
			clase6 = (1-inf)*(gca*0.0261*(0.63)) + inf*consumoPromedioVecinos.get(6); 			
			clase8 = (1-inf)*(gca*0.043*(0.69 + 0.0346))+ inf*consumoPromedioVecinos.get(8); 
			clase9 = (1-inf)*(gca*0.031*(0.68+0.70)) + inf*consumoPromedioVecinos.get(9);
			clase10 = (1-inf)*(gca*0.038 + gca*pos*0.000008347*0.0581) + inf*consumoPromedioVecinos.get(10);
			clase7 = gca - clase10- clase2 - clase3 - clase4 - clase5 - clase6 - clase1 - clase8 - clase9 ; break;//
			}
			else if (gca > lbm && consumoPromedioVecinos.get(1)==0) {
				clase1 = (gca*0.5299 -pos*gca*.00000747*0.5299)  ; 			
				clase2 = (gca*0.74*0.0792 + gca*pos*0.00001697*0.1192)  ; 
				clase3 = (gca*0.66*0.2035 + gca*0.1423*0.2035) ; 
				clase4 = (gca*0.0344*0.5 + gca*pos*0.000008347*0.0344); 
				clase5 = (gca*0.0847*0.61+ gca*0.0847*0.0385); 
				clase6 = (gca*0.0261*(0.63)) ; 			
				clase8 = (gca*0.043*(0.69 + 0.0346)); 
				clase9 = (gca*0.031*(0.68+0.70));
				clase10 = (gca*0.038 + gca*pos*0.000008347*0.0581) ;
				clase7 = gca - clase10- clase2 - clase3 - clase4 - clase5 - clase6 - clase1 - clase8 - clase9 ; break;//
				
			}
		
			
			else { //valor funcional
				clase1 = gca*0.5299  ;
				clase2= gca*0.74*0.0793;
				clase3 = gca*0.2035; 
				clase4 = gca*0.5*0.0344; 
				clase5 = gca*0.61*0.0847; 
				clase6 = gca*0.63*0.0261; 
				clase7 = gca*0.69*0.0692; 
				clase8 = gca*0.69*0.043; 
				clase9 = gca*0.68*0.031; 
				clase10 = (gca-clase1 - clase3 - clase6 - clase8 - clase5 -clase4-clase2-clase7-clase9) ; break;
			}
		}
		
	}
	
	 
	 //metodo promedio vecinos
	
	 
	 
	 
	
	 public int countConsumidor() { 
         return 1; 
	 }
	 
	 
	 public int getEnfoque() { return this.enfoque;	 }
	 public int getPos() { return this.pos;	 }
	 public double getTotal() { return this.gca; }	 
	 public double getClase1() { return this.clase1; }
	 public double getClase2() { return this.clase2; }
	 public double getClase3() { return this.clase3; }
	 public double getClase4() { return this.clase4; }
	 public double getClase5() { return this.clase5; }
	 public double getClase6() { return this.clase6; }
	 public double getClase7() { return this.clase7; }
	 public double getClase8() { return this.clase8; }
	 public double getClase9() { return this.clase9; }
	 public double getClase10() { return this.clase10; }
	 
	

}
