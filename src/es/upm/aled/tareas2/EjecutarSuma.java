package es.upm.aled.tareas2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class EjecutarSuma {

	public static void main(String[] args) {
		int[] numeros = new int[100];
        for (int i = 0; i < numeros.length; i++) {
            numeros[i] = i + 1;
        }
        int cores = Runtime.getRuntime().availableProcessors();
		System.out.println("Número de núcleos disponibles: " + cores);
        int tareas = numeros.length / cores;
        
        // Nos creamos el ejecutor de tareas
        ExecutorService executor = Executors.newFixedThreadPool(cores); //Utilizamos los 16 núcleos de la CPU
       // Nos creamos la lista futura
        List<Future<Integer>> resultadoFuturo = new ArrayList<Future<Integer>>();
        
        // Repartimos el número de tareas en los hilos que tenemos (4 en este caso):
        for (int i = 0; i<cores; i++) {
        	int inicio = i * tareas;
        	int fin;
        	// En el caso del último índice del hilo
        	if (i == cores - 1) {
        		fin = numeros.length; // El último hilo será en este caso toda la longitud del array (el último hilo se procesa hasta el final)
        	} else {	// Por otro lado, si no es el hilo del final
        		fin = (i + 1) * tareas; // Marca el final del rango para el hilo que se esté ejecutando en ese momento.
        	}
        	Callable<Integer> tarea = new SumaDeArrays(inicio, fin, numeros);
        	Future<Integer> futuro = executor.submit(tarea);
        	resultadoFuturo.add(futuro);
        	
        }
        // Queremos la suma total del resultadoFuturo y lo obtenemos:
        int total = 0;
        for (Future<Integer> f : resultadoFuturo) {
        	try {
				total += f.get();
			} catch (InterruptedException | ExecutionException  e) {
				e.printStackTrace();
			}
        	
        }
        System.out.println("Suma total: " + total);
        // Cerramos el ejecutor de tareas:
        executor.shutdown();
	}

}
