package es.upm.aled.tareas2;

import java.util.concurrent.Callable;

public class CuadradoNumero implements Callable<Integer>{
	/*
	 * Calcular el cuadrado de un número (básico)

	- Conceptos: uso de Callable, Future y ExecutorService.
	 */
	
	private int num;
	
	public CuadradoNumero(int num) {
		
		this.num = num;
	}

	@Override
	public Integer call() throws Exception {
		System.out.println("Calculando el cuadrado de " + num + " ...");
		Thread.sleep(1500);
		return num * num;
	}
}
