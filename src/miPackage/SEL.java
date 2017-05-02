package miPackage;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class SEL {

	private MatrizMath a;
	private VectorMath b;
	private VectorMath x;

	public MatrizMath getA() {
		return a;
	}

	public VectorMath getB() {
		return b;
	}

	public VectorMath getX() {
		return x;
	}

	public SEL(MatrizMath a, VectorMath b) {
		this.a = a;
		this.b = b;
		this.x = null;
	}

	public SEL(String path) {
		try {
			FileReader file = new FileReader(path);
			Scanner scan = new Scanner(file);
			scan.useLocale(Locale.ENGLISH);

			int ecuaciones = scan.nextInt();
			a = new MatrizMath(ecuaciones, ecuaciones);
			b = new VectorMath(ecuaciones);
			x = null;

			for (int i = 0; i < a.getFilas() * a.getColumnas(); i++) {
				int fila = scan.nextInt();
				int columna = scan.nextInt();

				a.setValor(fila, columna, scan.nextDouble());
			}

			for (int i = 0; i < b.getDimension(); i++) {
				b.setValor(i, scan.nextDouble());
			}

			scan.close();
		} catch (FileNotFoundException e) {
			System.out.println("Ha fallado la apertura del archivo de entrada con path: " + path);
		}
	}

	public void resolver() {
		x = a.inversa().multiplicar(b);
	}

	public double calcularErrorSolucion() throws SELException {
		if (x == null) {
			throw new SELException("Aún no se ha resuelto el sistema de ecuaciones lineales");
		}

		VectorMath bPrima = a.multiplicar(x);
		VectorMath errores = b.restar(bPrima);
		return errores.norma2();
	}

	public void mostrarSolucion() {
		if (x == null) {
			throw new SELException("Aún no se ha resuelto el sistema de ecuaciones lineales");
		}

		x.mostrar();
	}

	public void escribirSolucionEnArchivo(String path) throws SELException {
		if (x == null) {
			throw new SELException("Aún no se ha resuelto el sistema de ecuaciones lineales");
		}

		try {
			FileWriter file = new FileWriter(path);
			BufferedWriter buffer = new BufferedWriter(file);

			buffer.write(String.valueOf(x.getDimension()));
			for (int i = 0; i < x.getDimension(); i++) {
				buffer.newLine();
				buffer.write(String.valueOf(x.getValor(i)));
			}

			buffer.newLine();
			buffer.newLine();
			buffer.write(String.valueOf(this.calcularErrorSolucion()));

			buffer.close();
		} catch (IOException e) {
			System.out.println("Ha fallado la creación del archivo de salida con path: " + path);
		}
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
				String linea = String.valueOf(fil) + ' ' + String.valueOf(col) + ' '
						+ (String.valueOf(numeroAleatorio.nextInt((24 + 25) + 1) - 25));
				buffer.write(linea);
				buffer.newLine();
				if (col < dim)
					col++;
				if (col == dim) {
					fil++;
					col = 0;
				}
			}
			for (int i = 0; i < dim; i++) {
				buffer.write(String.valueOf(numeroAleatorio.nextInt((24 + 25) + 1) - 25));
				buffer.newLine();
			}
			buffer.close();
		} catch (IOException e) {
			System.out.println("Ha fallado la creaciï¿½n del archivo de entrada");
		}
	}
}
