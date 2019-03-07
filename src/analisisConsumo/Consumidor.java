package analisisConsumo;

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
	int enfoque = 1;//esto será un parámetro o una variable contadora que haga que se optimice según todos los enfoques
	//methods

	
	
	/**Método mediante los consumidores deciden su proporción de consumo*/
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
			clase10 = gca*0.0585 ; break;	
			
			
		/**Jerarquía de necesidades, Maslow,1943*/
		case 2 : clase1 = 962.5 + gca*0.2124; 
			if(gca-clase1 > 0) { clase3= gca*0.3389; clase5 = gca*0.1408 ;clase6 = gca*0.0438 ;clase8 = gca*0.0713; 
				if(gca-clase1-clase3-clase5-clase6-clase8 > 0) {clase2 =gca*0.0613; clase10 = gca*0.0455;
					if (gca - clase1 - clase3 - clase5- clase2-clase6-clase8-clase10 > 0 ){ 
					clase4 = (gca - clase1 - clase3 - clase5- clase2 - clase6 - clase8 - clase10)*0.0442; clase7 = (gca - clase1 - clase3 - clase5- clase2 - clase6 - clase8 - clase10)*0.0889 ; clase10 = (gca - clase1 - clase3 - clase5- clase2 - clase6 - clase8 - clase10)*0.0396;
					}
					
				}
			
			
			}  ;   break;
			/**Enfoque de prioridades, Chávez-Juárez 2018*/
		case 3 : clase1 = 555.41 + gca*0.3703; clase3 = 303.92 + gca*0.2035; clase6 = 39.27 + gca*0.0261; clase8 = 63.90 + gca*0.043; //consumo minimo vital
			if(gca-clase1 - clase3 - clase6 - clase8 > 0) {clase5 = (gca-clase1 - clase3 - clase6 - clase8)*0.2385; //consumo Salud
				if (gca-clase1 - clase3 - clase6 - clase8 - clase5 > 0) {clase4 = (gca-clase1 - clase3 - clase6 - clase8 - clase5)*0.1267; //consumo Educación
					if(gca-clase1 - clase3 - clase6 - clase8 - clase5 -clase4 > 0) {  //consumo adicional
						clase2 = (gca-clase1 - clase3 - clase6 - clase8 - clase5 -clase4)*0.3314;
						clase7 = (gca-clase1 - clase3 - clase6 - clase8 - clase5 -clase4)*0.2922;
						clase9 = (gca-clase1 - clase3 - clase6 - clase8 - clase5 -clase4)*0.1302;
						clase10 = (gca-clase1 - clase3 - clase6 - clase8 - clase5 -clase4)*0.2462;	
						
					}
					
				}
			
			}; break;
		
		/**Valores de consumo, Sheth et al (1991)  */
		case 4 :  
			clase1 = gca*0.1306;
			clase2 = gca*0.1162; 
			clase3 = gca*0.1082; 
			clase4 = gca*0.1120; 
			clase5 = gca*0.0745; 
			clase6 = gca*0.0612; 
			clase7 = gca*0.1050; 
			clase8 = gca*0.0717; 
			clase9 = gca*0.1051; 
			clase10 = gca - clase1- clase2 - clase3 - clase4 - clase5 - clase6 - clase7 - clase8 - clase9 ; break;//
		}
		
	}
	

}
