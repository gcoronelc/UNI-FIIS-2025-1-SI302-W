package pe.edu.uni.proyecto1coronel.service;

import java.util.Arrays;
import java.util.Random;
import pe.edu.uni.proyecto1coronel.dto.DemoDto;

/**
 *
 * @author Eric Gustavo Coronel Castillo
 * @blog www.desarrollasoftware.com
 * @email gcoronelc@gmail.com
 * @youtube www.youtube.com/DesarrollaSoftware
 * @facebook www.facebook.com/groups/desarrollasoftware/
 * @cursos gcoronelc.github.io
 */
public class DemoService {
	
	public DemoDto procesar(DemoDto bean){
		// Variables
		int[] arregloOrigen;
		int suma;
		int[] arregloInvertido;
		String resultado;
		// Proceso
		arregloOrigen = generaArreglo(bean.getN());
		suma = sumar(arregloOrigen);
		arregloInvertido = invertirArreglo(arregloOrigen);
		resultado = switch (bean.getOpcion()) {
			case 1 -> String.valueOf(suma);
			case 2 -> Arrays.toString(arregloInvertido);
			default -> "Opción invalida";
		};
		// Reporte
		bean.setVectorOrigen(arregloOrigen);
		bean.setResultado(resultado);
		return bean;
	}

	private int[] generaArreglo(int n) {
		// Variables
		int[] arreglo;
		// Proceso
		Random random = new Random();
		arreglo = new int[n];
		for (int i = 0; i < arreglo.length; i++) {
			arreglo[i] = random.nextInt(0, 100) + 1;
		}
		// Reporte
		return arreglo;		
	}

	private int sumar(int[] arregloOrigen) {
		// Variables
		int suma;
		// Proceso
		suma = 0;
		for (int dato : arregloOrigen) {
			suma += dato;
		}
		// Reporte
		return suma;
	}

	private int[] invertirArreglo(int[] arregloOrigen) {
		// Variables
		int[] invertido;
		// Proceso
		int n = arregloOrigen.length;
		invertido = new int[n];
		for (int dato : arregloOrigen) {
			n--;
			invertido[n] = dato;
		}
		// Reporte
		return invertido;
	}

}
