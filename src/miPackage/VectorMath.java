package miPackage;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Locale;
import java.util.Scanner;

public class VectorMath {

	private int dimension;
	private double[] valor;

	public int getDimension() {
		return dimension;
	}

	public double getValor(int coordenada) {
		return valor[coordenada];
	}

	public void setValor(int coordenada, double valor) {
		this.valor[coordenada] = valor;
	}

	public VectorMath(int dimension) {
		this.dimension = dimension;
		valor = new double[dimension];
	}

	public VectorMath(String path) {
		try {
			FileReader file = new FileReader(path);
			Scanner scan = new Scanner(file);
			scan.useLocale(Locale.ENGLISH);

			dimension = scan.nextInt();
			valor = new double[dimension];

			for (int i = 0; i < dimension; i++) {
				valor[i] = scan.nextDouble();
			}
			scan.close();
		} catch (FileNotFoundException e) {
			System.out.println("No se pudo abrir el archivo");
		}
	}

	public VectorMath sumar(VectorMath vector) throws VectorMathException {

		if (this.dimension != vector.dimension) {
			throw new VectorMathException("Los vectores tienen diferentes dimensiones");
		}

		VectorMath suma = new VectorMath(this.dimension);
		for (int i = 0; i < this.dimension; i++) {
			suma.valor[i] = this.valor[i] + vector.valor[i];
		}
		return suma;
	}

	public VectorMath restar(VectorMath vector) throws VectorMathException {

		if (this.dimension != vector.dimension) {
			throw new VectorMathException("Los vectores tienen diferentes dimensiones");
		}

		VectorMath resta = new VectorMath(this.dimension);
		for (int i = 0; i < this.dimension; i++) {
			resta.valor[i] = this.valor[i] - vector.valor[i];
		}
		return resta;
	}

	public double multiplicar(VectorMath vector) throws VectorMathException {

		if (this.dimension != vector.dimension) {
			throw new VectorMathException("Los vectores tienen diferentes dimensiones");
		}

		double producto = 0;
		for (int i = 0; i < this.dimension; i++) {
			producto += this.valor[i] * vector.valor[i];
		}
		return producto;
	}

	public VectorMath multiplicar(MatrizMath matriz) throws VectorMathException {

		if (this.dimension != matriz.getFilas()) {
			throw new VectorMathException("Las dimensiones no permiten multiplicar");
		}

		VectorMath producto = new VectorMath(matriz.getColumnas());
		for (int j = 0; j < matriz.getColumnas(); j++) {
			for (int i = 0; i < matriz.getFilas(); i++) {
				producto.valor[j] += this.valor[i] * matriz.getValor(i, j);
			}
		}
		return producto;
	}

	public VectorMath multiplicar(double escalar) {

		VectorMath producto = new VectorMath(this.dimension);
		for (int i = 0; i < this.dimension; i++) {
			producto.valor[i] = this.valor[i] * escalar;
		}
		return producto;
	}

	public double norma1() {

		double norma = 0;
		for (int i = 0; i < this.dimension; i++) {
			norma += Math.abs(this.valor[i]);
		}
		return norma;
	}

	public double norma2() {

		double norma = 0;
		for (int i = 0; i < this.dimension; i++) {
			norma += this.valor[i] * this.valor[i];
		}
		return Math.sqrt(norma);
	}

	public double normaInfinita() {
		double norma = Math.abs(this.valor[0]);
		for (int i = 1; i < this.dimension; i++) {
			if (Math.abs(this.valor[i]) > norma) {
				norma = Math.abs(this.valor[i]);
			}
		}
		return norma;
	}

	@Override
	protected VectorMath clone() {

		VectorMath clon = new VectorMath(this.dimension);
		for (int i = 0; i < this.dimension; i++) {
			clon.valor[i] = this.valor[i];
		}
		return clon;
	}

	public boolean equals(VectorMath vector) {

		if (this.dimension != vector.dimension) {
			return false;
		}

		for (int i = 0; i < this.dimension; i++) {
			if (this.valor[i] != vector.valor[i]) {
				return false;
			}
		}
		return true;
	}

	public void mostrar() {
		for (int i = 0; i < dimension; i++) {
			System.out.print(valor[i] + "  ");
		}
		System.out.println();
	}
}
