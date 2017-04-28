package miPackage;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Locale;
import java.util.Scanner;

public class MatrizMath {

	private int filas;
	private int columnas;
	private double valor[][];

	public int getFilas() {
		return filas;
	}

	public int getColumnas() {
		return columnas;
	}

	public double getValor(int fila, int columna) {
		return this.valor[fila][columna];
	}

	public void setValor(int fila, int columna, double valor) {
		this.valor[fila][columna] = valor;
	}

	public MatrizMath(int fila, int columna) {
		this.filas = fila;
		this.columnas = columna;
		valor = new double[fila][columna];
	}

	public MatrizMath(String path) {

		try {
			FileReader file = new FileReader(path);
			Scanner scan = new Scanner(file);
			scan.useLocale(Locale.ENGLISH);

			filas = scan.nextInt();
			columnas = scan.nextInt();

			valor = new double[filas][columnas];

			for (int i = 0; i < filas * columnas; i++) {
				int fila = scan.nextInt();
				int columna = scan.nextInt();
				valor[fila][columna] = scan.nextDouble();
			}
			scan.close();
		} catch (FileNotFoundException e) {
			System.out.println("No se pudo abrir el archivo");
		}
	}

	private MatrizMath sumar(MatrizMath matriz, int signo) throws MatrizMathException {

		if (this.filas != matriz.filas || this.columnas != matriz.columnas) {
			throw new MatrizMathException("Las matrices tienen diferentes dimensiones");
		}

		MatrizMath suma = new MatrizMath(this.filas, this.columnas);
		for (int i = 0; i < this.filas; i++) {
			for (int j = 0; j < this.columnas; j++) {
				suma.valor[i][j] = this.valor[i][j] + matriz.valor[i][j] * signo;
			}
		}
		return suma;
	}

	public MatrizMath sumar(MatrizMath matriz) throws MatrizMathException {
		return this.sumar(matriz, 1);
	}
	
	public MatrizMath restar(MatrizMath matriz) throws MatrizMathException {
		return this.sumar(matriz, -1);
	}

	public MatrizMath multiplicar(MatrizMath matriz) throws MatrizMathException {

		if (this.columnas != matriz.columnas) {
			throw new MatrizMathException("Las dimensiones de las matrices no permiten multiplicarlas");
		}

		MatrizMath producto = new MatrizMath(matriz.columnas, this.filas);
		for (int i = 0; i < this.filas; i++) {
			for (int j = 0; j < matriz.columnas; j++) {
				for (int k = 0; k < this.columnas; k++) {
					producto.valor[i][j] += this.valor[i][k] * matriz.valor[k][j];
				}
			}
		}
		return producto;
	}

	public VectorMath multiplicar(VectorMath vector) throws VectorMathException {

		if (this.columnas != vector.getDimension()) {
			throw new VectorMathException("Las dimensiones no permiten multiplicar");
		}

		VectorMath producto = new VectorMath(this.filas);
		for (int i = 0; i < this.filas; i++) {
			for (int j = 0; j < this.columnas; j++) {
				producto.setValor(i, producto.getValor(i) + this.valor[i][j] * vector.getValor(j));
			}
		}
		return producto;
	}

	public MatrizMath multiplicar(double escalar) {

		MatrizMath producto = new MatrizMath(this.filas, this.columnas);
		for (int i = 0; i < this.filas; i++) {
			for (int j = 0; j < this.columnas; j++) {
				producto.valor[i][j] = this.valor[i][j] * escalar;
			}
		}
		return producto;
	}

	public double norma1() {

		double norma = 0;
		for (int i = 0; i < this.filas; i++) {
			for (int j = 0; j < this.columnas; j++) {
				norma += Math.abs(this.valor[i][j]);
			}
		}
		return norma;
	}

	public double norma2() {

		double norma = 0;
		for (int i = 0; i < this.filas; i++) {
			for (int j = 0; j < this.columnas; j++) {
				norma += this.valor[i][j] * this.valor[i][j];
			}
		}
		return norma;
	}

	public double normaInfinita() {
		double norma = Math.abs(this.valor[0][0]);
		for (int i = 0; i < this.filas; i++) {
			for (int j = 0; j < this.columnas; j++) {
				if (Math.abs(this.valor[i][j]) > norma) {
					norma = Math.abs(this.valor[i][j]);
				}
			}
		}
		return norma;
	}

	@Override
	public MatrizMath clone() {

		MatrizMath clon = new MatrizMath(this.filas, this.columnas);
		for (int i = 0; i < this.filas; i++) {
			for (int j = 0; j < this.columnas; j++) {
				clon.valor[i][j] = this.valor[i][j];
			}
		}
		return clon;
	}

	public boolean equals(MatrizMath matriz) {

		if (this.filas != matriz.filas || this.columnas != matriz.columnas) {
			return false;
		}

		for (int i = 0; i < this.filas; i++) {
			for (int j = 0; j < this.columnas; j++) {
				if (this.valor[i][j] != matriz.valor[i][j]) {
					return false;
				}
			}
		}
		return true;
	}

	public boolean cuadrada() {
		return filas == columnas;
	}

	public double determinante() throws MatrizMathException {

		if (!cuadrada()) {
			throw new MatrizMathException("El determinante solo puede calcularse para matrices cuadradas");
		}

		MatrizMath matriz = clone();
		for (int k = 0; k < filas - 1; k++) {
			for (int i = k + 1; i < filas; i++) {
				for (int j = k + 1; j < filas; j++) {
					matriz.valor[i][j] -= matriz.valor[i][k] * matriz.valor[k][j] / matriz.valor[k][k];
				}
			}
		}

		double det = 1.0;
		for (int i = 0; i < filas; i++) {
			det *= matriz.valor[i][i];
		}
		return det;
	}

	public MatrizMath inversa() throws MatrizMathException {

		if (!cuadrada()) {
			throw new MatrizMathException("El determinante solo puede calcularse para matrices cuadradas");
		}
		if (determinante() == 0) {
			throw new MatrizMathException("La matriz no tiene inversa ya que su determinante es cero");
		}

		MatrizMath a = clone();
		MatrizMath b = new MatrizMath(filas, columnas);
		MatrizMath c = new MatrizMath(filas, columnas);

		for (int i = 0; i < filas; i++) {
			b.valor[i][i] = 1.0;
		}

		for (int k = 0; k < filas - 1; k++) {
			for (int i = k + 1; i < filas; i++) {
				for (int s = 0; s < filas; s++) {
					b.valor[i][s] -= a.valor[i][k] * b.valor[k][s] / a.valor[k][k];
				}
				for (int j = k + 1; j < filas; j++) {
					a.valor[i][j] -= a.valor[i][k] * a.valor[k][j] / a.valor[k][k];
				}
			}
		}

		for (int s = 0; s < filas; s++) {
			c.valor[filas - 1][s] = b.valor[filas - 1][s] / a.valor[filas - 1][filas - 1];
			for (int i = filas - 2; i >= 0; i--) {
				c.valor[i][s] = b.valor[i][s] / a.valor[i][i];
				for (int k = filas - 1; k > i; k--) {
					c.valor[i][s] -= a.valor[i][k] * c.valor[k][s] / a.valor[i][i];
				}
			}
		}

		return c;
	}

	public double errorAlInvertir() throws MatrizMathException {
		if (!cuadrada()) {
			throw new MatrizMathException("El determinante solo puede calcularse para matrices cuadradas");
		}
		if (determinante() == 0) {
			throw new MatrizMathException("La matriz no tiene inversa ya que su determinante es cero");
		}

		MatrizMath inversa = this.inversa();
		MatrizMath identidadPrima = this.multiplicar(inversa);
		MatrizMath errores = MatrizMath.identidad(this.filas).restar(identidadPrima);
		return errores.norma2();
	}

	public static MatrizMath identidad(int orden) {
		MatrizMath identidad = new MatrizMath(orden, orden);
		for (int i = 0; i < orden; i++) {
			identidad.valor[i][i] = 1.0;
		}
		return identidad;
	}

	public void mostrar() {
		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				System.out.print(valor[i][j] + "  ");
			}
			System.out.println();
		}
	}
}
