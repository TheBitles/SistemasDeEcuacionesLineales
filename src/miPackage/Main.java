package miPackage;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Main {
	
	public static void resolverSistema() {
		
		System.out.println("SISTEMAS DE ECUACIONES LINEALES");
		
		System.out.println("El sistema se levanta de un archivo entrada.in en la raíz del proyecto");
		System.out.println("La solución se graba en un archivo salida.out en la raíz del proyecto");
		
		SEL sel = new SEL("entrada.in");
		sel.resolver();
		sel.escribirSolucionEnArchivo("salida.out");
		
		System.out.println("Solución del sistema");
		sel.mostrarSolucion();
		
		System.out.println("Error cometido en la resolución del sistema: " + sel.calcularErrorSolucion());
	
	}

	public static void main(String[] args) {
		
		Calendar tiempoInicial = new GregorianCalendar();

		resolverSistema();
		
		Calendar tiempoFinal = new GregorianCalendar();
		
		long variacionTiempo = tiempoFinal.getTimeInMillis() - tiempoInicial.getTimeInMillis();
		System.out.println("Rendimiento del algoritmo: " + variacionTiempo);
	}

}
