package es.upm.aled.tareas2;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;



public class LanzadorTareas {

	public static void main(String[] args) {
		try {
			//Creo el servicio (programa que se ofrece a hacer una tarea concreta) de ejercución de Threads
			int cores = Runtime.getRuntime().availableProcessors();
			ExecutorService executor = Executors.newFixedThreadPool(cores); // Permite lanzar hebras tantas como permite tu ordenador
			// Par aejecutar un Thread, hay que crearse primero tareas
			Callable<Integer> tarea1 = new CuadradoNumero(9); //Lamar al constructor de la clase CuadradoNumero
			// Envía las tareas al servicio de ejecución
			Future<Integer> resultadoFuturo = executor.submit(tarea1); // "Promesa" de un entero cuando termina 
			// la tarea; submit --> le pasas objetos Callable<Integer> (en nuestro caso la tarea)
			int resultado = resultadoFuturo.get(); //.get() método bloqueante
			System.out.println(resultado);
			Thread.sleep(500);
			System.out.println("Hemos obtenido el resultado.");
			}catch (InterruptedException | ExecutionException e) {
				
			}
		}

	}


