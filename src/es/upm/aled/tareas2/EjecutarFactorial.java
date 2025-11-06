package es.upm.aled.tareas2;


import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class EjecutarFactorial {

	public static void main(String[] args) {
		int cores = Runtime.getRuntime().availableProcessors();
		ExecutorService ex = Executors.newFixedThreadPool(cores);
		
		Callable<Integer> tarea1 = new FactorialTareas(5);
		Callable<Integer> tarea2 = new FactorialTareas(3);
		Callable<Integer> tarea3 = new FactorialTareas(7);

		Future<Integer> futuro1 = ex.submit(tarea1);
		Future<Integer> futuro2 = ex.submit(tarea2);
		Future<Integer> futuro3 = ex.submit(tarea3);
		
		try {
			int resultado1 = futuro1.get();
			int resultado2 = futuro2.get();
			int resultado3 = futuro3.get();
			System.out.println("Imprimimos todos los resultados de las 3 tareas: ");
			System.out.println(resultado1);
			System.out.println(resultado2);
			System.out.println(resultado3);
			
		} catch (InterruptedException | ExecutionException e) {
	
		}
	}


}
