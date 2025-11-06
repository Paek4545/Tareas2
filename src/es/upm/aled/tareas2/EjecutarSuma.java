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
		// Nos creamos un array con 100 valores posibles
		int[] numeros = new int[100];
        for (int i = 0; i < numeros.length; i++) {
        // Cada valor del array será igual a su posición
            numeros[i] = i + 1;
        }
        // Dividimos el trabajo según el número de núcleos que tengamos (hilos, en mi caso 16 núcleos)
        int cores = Runtime.getRuntime().availableProcessors();
		System.out.println("Número de núcleos: " + cores);
		// Dividimos el trabajo en función del número de núcleos disponibles
        int tareas = numeros.length / cores;
        
        // Nos creamos el ejecutor de tareas
        ExecutorService executor = Executors.newFixedThreadPool(cores); //Utilizamos los 16 núcleos de la CPU
        
       // Nos creamos la lista futura
        List<Future<Integer>> resultadoFuturo = new ArrayList<Future<Integer>>();
        
        // Repartimos el número de tareas en los hilos que tenemos (4 en este caso):
        for (int i = 0; i<cores; i++) {
     // El inicio será el número que calcula desde qué posición del array debe empezar a trabajar el hilo número i 
        	int inicio = i * tareas;
        	int fin;
      // En el caso del último índice del hilo
        	if (i == cores - 1) {
        		fin = numeros.length; // El último hilo será en este caso toda la longitud del array (el último hilo se procesa hasta el final)
        	} else {	// Por otro lado, si no es el hilo del final
        		fin = (i + 1) * tareas; // Marca el final del rango para el hilo que se esté ejecutando en ese momento.
        	}
        	
        	// Nos creamos nuestra tarea llamando al constructor de la clase que nos interesa
        	Callable<Integer> tarea = new SumaDeArrays(inicio, fin, numeros);
        	// Nos creamos un valor futuro, donde obtenemos los valores que nos ha dado la tarea
        	Future<Integer> futuro = executor.submit(tarea);
        	// Añadimos a la lista futura creada antes los valores futuros que hemos obtenido de la tarea realizada por call de la clase SumaDeArrays
        	resultadoFuturo.add(futuro);
        	
        }
        // Queremos la suma total del resultadoFuturo y lo obtenemos --> Se podría bien también crear 16 tareas
        // diferentes en caso de no haber hecho este bucle
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
