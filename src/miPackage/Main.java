package miPackage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;

public class Main {
	
	public static void resolverSistema() {
		
		System.out.println("SISTEMAS DE ECUACIONES LINEALES");
		
		System.out.println("El sistema se levanta de un archivo entrada.in en la ra�z del proyecto");
		System.out.println("La soluci�n se graba en un archivo salida.out en la ra�z del proyecto");
		
		SEL sel = new SEL("sel.in");
		borrarSolucionAnterior();
		sel.resolver();
		sel.escribirSolucionEnArchivo("solucion.out");
		
		System.out.println("Soluci�n del sistema");
		sel.mostrarSolucion();
		
		System.out.println("Error cometido en la resoluci�n del sistema: " + sel.calcularErrorSolucion());
	
	}

	public static void borrarSolucionAnterior() {
		File arch = new File("solucion.out");
		arch.delete();
	}

	public static void generarSELAleatorio(int dim) {
		
		int fil, col;
		fil = col = 0;
		Random numeroAleatorio = new Random();

		try {
			FileWriter file = new FileWriter("sel.in");
			BufferedWriter buffer = new BufferedWriter(file);
			buffer.write(String.valueOf(dim));
			buffer.newLine();
			for (int i = 0; i < dim * dim; i++) {
				String linea = String.valueOf(fil) + ' ' + String.valueOf(col) + ' ' + (String.valueOf(numeroAleatorio.nextInt((24 + 25) + 1) - 25));
				buffer.write(linea);
				buffer.newLine();
				if(col < dim)
					col++;
				if(col == dim) {
					fil++;
					col = 0;
				}
			}
			for (int i = 0 ; i < dim ; i++) {
				buffer.write(String.valueOf(numeroAleatorio.nextInt((24 + 25) + 1) - 25));
				buffer.newLine();
			}
			buffer.close();
		} catch (IOException e) {
			System.out.println("Ha fallado la creaci�n del archivo de entrada");
		}
	}
	
	public static void main(String[] args) {
		
		Calendar tiempoInicial = new GregorianCalendar();

		//generarSELAleatorio(1000);
		borrarSolucionAnterior();
		resolverSistema();
		
		Calendar tiempoFinal = new GregorianCalendar();
		
		long variacionTiempo = tiempoFinal.getTimeInMillis() - tiempoInicial.getTimeInMillis();
		System.out.println("Rendimiento del algoritmo: " + variacionTiempo);
	}

}
