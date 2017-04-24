package miPackage;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Main {
	
	public static void resolverSistema() {
		
		System.out.println("SISTEMAS DE ECUACIONES LINEALES");
		
		System.out.println("El sistema se levanta de un archivo entrada.in en la ra�z del proyecto");
		System.out.println("La soluci�n se graba en un archivo salida.out en la ra�z del proyecto");
		
		SEL sel = new SEL("entrada.in");
		sel.resolver();
		sel.escribirSolucionEnArchivo("salida.out");
		
		System.out.println("Soluci�n del sistema");
		sel.mostrarSolucion();
		
		System.out.println("Error cometido en la resoluci�n del sistema: " + sel.calcularErrorSolucion());
	
	}

	public static void main(String[] args) {
		
		Calendar tiempoInicial = new GregorianCalendar();

		resolverSistema();
		
		Calendar tiempoFinal = new GregorianCalendar();
		
		long variacionTiempo = tiempoFinal.getTimeInMillis() - tiempoInicial.getTimeInMillis();
		System.out.println("Rendimiento del algoritmo: " + variacionTiempo);
	}

}
