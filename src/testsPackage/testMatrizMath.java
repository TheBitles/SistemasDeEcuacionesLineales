package testsPackage;

import org.junit.Assert;
import org.junit.Test;

import miPackage.MatrizMath;
import miPackage.VectorMath;

public class testMatrizMath {

	@Test
	public void testSeInstanciaConDimensiones() {
		@SuppressWarnings("unused")
		MatrizMath matriz = new MatrizMath(4, 3);
	}

	@Test
	public void testLevantaArchivo() {
		@SuppressWarnings("unused")
		MatrizMath matriz = new MatrizMath("test/MatrizMath/matrizA.in");
	}

	@Test
	public void testSuma() {
		MatrizMath matrizA = new MatrizMath("test/MatrizMath/matrizA.in");
		MatrizMath matrizB = new MatrizMath("test/MatrizMath/matrizB.in");

		MatrizMath suma = matrizA.sumar(matrizB);
		MatrizMath sumaAB = new MatrizMath("test/MatrizMath/sumaAB.in");
		Assert.assertTrue(suma.equals(sumaAB));
	}

	@Test
	public void testResta() {
		MatrizMath matrizA = new MatrizMath("test/MatrizMath/matrizA.in");
		MatrizMath matrizB = new MatrizMath("test/MatrizMath/matrizB.in");

		MatrizMath resta = matrizA.restar(matrizB);
		MatrizMath restaAB = new MatrizMath("test/MatrizMath/restaAB.in");
		Assert.assertTrue(resta.equals(restaAB));
	}

	@Test
	public void testMultiplicionMatrices() {
		MatrizMath matrizA = new MatrizMath("test/MatrizMath/matrizA.in");
		MatrizMath matrizB = new MatrizMath("test/MatrizMath/matrizB.in");
		
		MatrizMath producto = matrizA.multiplicar(matrizB);
		MatrizMath productoAB = new MatrizMath("test/MatrizMath/matrizAxB.in");
		Assert.assertTrue(producto.equals(productoAB));
	}
	
	@Test
	public void testMultiplicionPorUnEscalar() {
		MatrizMath matrizA = new MatrizMath("test/MatrizMath/matrizA.in");
		
		MatrizMath producto = matrizA.multiplicar(2);
		MatrizMath matrizAx2 = new MatrizMath("test/MatrizMath/matrizAx2.in");
		Assert.assertTrue(producto.equals(matrizAx2));
	}
	
	@Test
	public void testMultiplicionMatrizVector() {
		MatrizMath identidad = MatrizMath.identidad(6);
		VectorMath vectorA = new VectorMath("test/VectorMath/vectorA.in");
		
		VectorMath producto = identidad.multiplicar(vectorA);
		Assert.assertTrue(vectorA.equals(producto));
	}
	
	@Test
	public void norma1() {
		MatrizMath matrizA = new MatrizMath("test/MatrizMath/matrizA.in");
		
		Double norma = matrizA.norma1();
		Double normaA = 20.0;
		Assert.assertEquals(norma, normaA);
	}
	
	@Test
	public void norma2() {
		MatrizMath matrizA = new MatrizMath("test/MatrizMath/matrizA.in");
		
		Double norma = matrizA.norma2();
		Double normaA = 126.0;
		Assert.assertEquals(norma, normaA);
	}
	
	@Test
	public void normaInfinita() {
		MatrizMath matrizA = new MatrizMath("test/MatrizMath/matrizA.in");
		
		Double norma = matrizA.normaInfinita();
		Double normaA = 9.0;
		Assert.assertEquals(norma, normaA);
	}
	
	public void testCloneEquals() {
		MatrizMath matrizA = new MatrizMath("test/MatrizMath/matrizA.in");
		MatrizMath clon = matrizA.clone();
		
		Assert.assertTrue(matrizA.equals(clon));
	}
	
	public void testIdentidad() {
		MatrizMath identidad = MatrizMath.identidad(3);
		MatrizMath identidadOrden3 = new MatrizMath("test/MatrizMath/identidadOrden3.in");
		
		Assert.assertEquals(identidad, identidadOrden3);
	}
	
	public void testDeterminante() {
		MatrizMath matrizA = new MatrizMath("test/MatrizMath/matrizA.in");
		
		Double determinante = matrizA.determinante();
		Double determinanteA = -2.0;
		
		Assert.assertEquals(determinante, determinanteA);
	}
	
	public void testInversa() {
		MatrizMath matrizA = new MatrizMath("test/MatrizMath/matrizA.in");

		MatrizMath inversa = matrizA.inversa();
		MatrizMath inversaA = new MatrizMath("test/MatrizMath/matrizAinversa.in");
		Assert.assertEquals(inversa, inversaA);
	}
	
	public void testCalcularErrorAlInvertir() {
		MatrizMath matrizA = new MatrizMath("test/MatrizMath/matrizA.in");
		matrizA.errorAlInvertir();
	}
}
