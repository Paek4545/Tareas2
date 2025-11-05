package es.upm.aled.tareas2;

import java.util.concurrent.Callable;


public class SumaDeArrays implements Callable<Integer> {
		private int inicio;
		private int fin;
		private int[] numeros;
		
		public SumaDeArrays(int inicio, int fin, int[] numeros) {
			this.inicio = inicio;
			this.fin = fin;
			this.numeros = numeros;
		}

		@Override
		public Integer call() throws Exception {
			int suma = 0;
			for (int i = inicio; i<fin; i++) {
				suma += numeros[i];
			}
			System.out.println(Thread.currentThread().getName() + " sumó de " + inicio + " a " + (fin - 1));
			return suma;
		}
		
		
}	
	

