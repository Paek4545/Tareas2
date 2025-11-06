package es.upm.aled.tareas2;

import java.util.concurrent.Callable;

public class FactorialTareas implements Callable<Integer> {
	
	private int numero;
	
	public FactorialTareas(int numero) {
		this.numero = numero;
	}
	@Override
	public Integer call() throws Exception {
		int resultado = 1;
		for (int i = 1; i <= numero; i++) {
			resultado *= i;
		}
		return resultado;
	}
	
}
