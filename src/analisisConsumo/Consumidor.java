package analisisConsumo;

import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.engine.schedule.ScheduledMethod;
import repast.simphony.parameter.Parameters;

public class Consumidor {
	//double pphog; //integrantes en el hogar.
	double gca; //cantidad total designada al consumo
	int pos; //percentil en la distribución del gasto total anual de consumo.
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

	
	
	
	public Consumidor( int pos, double gca ) {
		
		//this.pphog = pphog;
		this.gca = gca;
		this.pos = pos;
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
		
		
		
	}
	
	 Parameters params = RunEnvironment.getInstance().getParameters();
	int enfoque = params.getInteger("enfoque");//esto será un parámetro o una variable contadora que haga que se optimice según todos los enfoques
	double lbm = params.getDouble("bienmin");
	//double lbm = 5000; 
	//methods

	/**Método mediante los consumidores deciden su proporción de consumo*/
	 @ScheduledMethod(start=1,interval=1,shuffle=true,priority=90)
	public void consumo() {
		switch (enfoque) {
		/**Caso Funcion de utilidad, Cobb Douglas, 1928*/
		case 1 : 
			clase1 = gca*0.3703; 
			clase2 = gca*0.0793; 
			clase3 = gca*0.2035; 
			clase4 = gca*0.0344; 
			clase5 = gca*0.0847; 
			clase6 = gca*0.0261; 
			clase7 = gca*0.0692; 
			clase8 = gca*0.043; 
			clase9 = gca*0.031; 
			clase10 = (gca-clase1 - clase3 - clase6 - clase8 - clase5 -clase4-clase2-clase7-clase9) ; break;	
			
			
		/**Jerarquía de necesidades, Maslow,1943*/
		case 2 : if (gca > lbm){
			
		
			clase1 = lbm + (gca-lbm)*0.2124; 
			if(gca-clase1 > 0) { 
				clase3= (gca-clase1)*0.3389;
				clase5 = (gca-clase1)*0.1408;
				clase6 = (gca-clase1)*0.0438;
				clase8 = (gca-clase1)*0.0713; 
					if(gca-clase1-clase3-clase5-clase6-clase8 > 0) {
						clase2 =(gca-clase1-clase3-clase5-clase6-clase8)*0.1215; 
						clase10 = (gca-clase1-clase3-clase5-clase6-clase8)*0.0903;
							if (gca - clase1 - clase3 - clase5- clase2-clase6-clase8-clase10 > 0 ){ 
								clase4 = (gca - clase1 - clase3 - clase5- clase2 - clase6 - clase8 - clase10)*0.2557; 
								clase7 = (gca - clase1 - clase3 - clase5- clase2 - clase6 - clase8 - clase10)*0.5148; 
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
			clase1 = lbm*0.5299 + (gca-lbm)*.3503; //Alimentos en el hogar
			clase3 = lbm*0.2035 + (gca-lbm)*0.2035;  //hogar y vestido
			clase6 = lbm*0.0515 + (gca-lbm)*0.0261; // seguridad
			clase8 = lbm*0.0711 + (gca-lbm)*0.043; //transporte público. Consumo minimo vital
				if(gca-clase1 - clase3 - clase6 - clase8 > 0) {
					clase5 = (gca-clase1 - clase3 - clase6 - clase8)*0.2985; //consumo Salud (anterior .2585)
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
		clase6=gca*0.0249; 
		clase8=gca*0.0511;
		clase5 = (gca-clase1-clase3-clase6-clase8)*(0.4681);
		clase9 = (gca-clase1-clase3-clase6-clase8)*(0.1053);
		clase2 = (gca-clase1-clase3-clase6-clase8)*0.1111;
		clase4 = (gca-clase1-clase3-clase6-clase8)*0.14;
		clase10 = gca-clase1-clase2-clase3-clase4-clase5-clase6-clase8-clase9 ; //.1755

			
		}; break;
		
		/**Valores de consumo, Sheth et al (1991)  */
		case 4 :  
			
			if (gca > lbm) { //valor funcional mas valor social
			clase1 = gca*0.5299 -pos*gca*.00000747*0.5299; 			
			clase2 = gca*0.74*0.0792 + gca*pos*0.00001697*0.1192 ; 
			clase3 = gca*0.66*0.2035 + gca*0.1423*0.2035; 
			clase4 = gca*0.0344*0.5 + gca*pos*0.000008347*0.0344; 
			clase5 = gca*0.0847*0.61+ gca*0.0847*0.0385; 
			clase6 = gca*0.0261*(0.63); 
			
			clase8 = gca*0.043*(0.69 + 0.0346); 
			clase9 = gca*0.031*(0.68+0.70);
			clase10 = gca*0.038 + gca*pos*0.000008347*0.0581 ;
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
