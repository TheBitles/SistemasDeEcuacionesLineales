package testsPackage;

import org.junit.Assert;
import org.junit.Test;

import miPackage.MatrizMath;
import miPackage.VectorMath;

public class testVectorMath {

	@Test
	public void testSeInstanciaConDimension() {
		@SuppressWarnings("unused")
		VectorMath vector = new VectorMath(6);
	}

	@Test
	public void testLevantaArchivo() {
		@SuppressWarnings("unused")
		VectorMath vector = new VectorMath("test/VectorMath/vectorA.in");
	}

	@Test
	public void testSuma() {
		VectorMath vectorA = new VectorMath("test/VectorMath/vectorA.in");
		VectorMath vectorB = new VectorMath("test/VectorMath/vectorB.in");

		VectorMath suma = vectorA.sumar(vectorB);
		VectorMath sumaAB = new VectorMath("test/VectorMath/sumaAB.in");
		Assert.assertTrue(suma.equals(sumaAB));
	}

	@Test
	public void testResta() {
		VectorMath vectorA = new VectorMath("test/VectorMath/vectorA.in");
		VectorMath vectorB = new VectorMath("test/VectorMath/vectorB.in");

		VectorMath resta = vectorA.restar(vectorB);
		VectorMath restaAB = new VectorMath("test/VectorMath/restaAB.in");
		Assert.assertTrue(resta.equals(restaAB));
	}

	@Test
	public void testMultiplicacionVectores() {
		VectorMath vectorA = new VectorMath("test/VectorMath/vectorA.in");
		VectorMath vectorB = new VectorMath("test/VectorMath/vectorB.in");

		Double producto = vectorA.multiplicar(vectorB);
		Double productoAB = 11464.8459;
		Assert.assertEquals(producto, productoAB);
	}

	@Test
	public void testMultiplicacionPorUnEscalar() {
		VectorMath vectorA = new VectorMath("test/VectorMath/vectorA.in");

		VectorMath producto = vectorA.multiplicar(2);
		VectorMath vectorAx2 = new VectorMath("test/VectorMath/vectorAx2.in");
		Assert.assertTrue(producto.equals(vectorAx2));
	}
	
	@Test
	public void testMultiplicionVectorMatriz() {
		VectorMath vectorA = new VectorMath("test/VectorMath/vectorA.in");
		MatrizMath identidad = MatrizMath.identidad(6);
		
		VectorMath producto = vectorA.multiplicar(identidad);
		Assert.assertTrue(vectorA.equals(producto));
	}

	@Test
	public void testNorma1() {
		VectorMath vectorA = new VectorMath("test/VectorMath/vectorA.in");

		Double norma = vectorA.norma1();
		Double normaA = 2325.23;
		Assert.assertEquals(norma, normaA);
	}

	@Test
	public void testNorma2() {
		VectorMath vectorA = new VectorMath("test/VectorMath/vectorA.in");

		Double norma = vectorA.norma2();
		Double normaA = 2300.0372851108305;
		Assert.assertEquals(norma, normaA);
	}

	@Test
	public void testNormaInfinita() {
		VectorMath vectorA = new VectorMath("test/VectorMath/vectorA.in");

		Double norma = vectorA.normaInfinita();
		Double normaA = 2300.0;
		Assert.assertEquals(norma, normaA);
	}

	@Test
	public void testsCloneEquals() {
		VectorMath vectorA = new VectorMath("test/VectorMath/vectorA.in");
		VectorMath clon = vectorA.clone();

		Assert.assertTrue(vectorA.equals(clon));
	}
}
