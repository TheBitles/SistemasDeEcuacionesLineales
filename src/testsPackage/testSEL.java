package testsPackage;

import org.junit.Test;

import miPackage.SEL;

public class testSEL {

	@Test
	public void testLevantaArchivo() {
		@SuppressWarnings("unused")
		SEL sel = new SEL("test/SEL/01_Ejemplo/entrada/sel.in");
	}

	@Test
	public void testResuelveSistema() {
		SEL sel = new SEL("test/SEL/01_Ejemplo/entrada/sel.in");
		sel.resolver();
	}

	@Test
	public void testCalculaErrorSolucion() {
		SEL sel = new SEL("test/SEL/01_Ejemplo/entrada/sel.in");
		sel.resolver();
		sel.calcularErrorSolucion();
	}

	@Test
	public void testEscribeSolucionEnArchivo() {
		SEL sel = new SEL("test/SEL/01_Ejemplo/entrada/sel.in");
		sel.resolver();
		sel.escribirSolucionEnArchivo("test/SEL/00_Ejemplo/salida/solucion.out");
	}
}
