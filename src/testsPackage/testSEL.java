package testsPackage;

import org.junit.Test;

import miPackage.SEL;

public class testSEL {

	// Todos los métodos de la clase SEL son testeados con el caso del SCI
	@Test
	public void testLevantaArchivo() {
		@SuppressWarnings("unused")
		SEL sel = new SEL("test/SEL/01_SistemaCompatibleDeterminado/entrada/sel.in");
	}

	@Test
	public void testResuelveSistema() {
		SEL sel = new SEL("test/SEL/01_SistemaCompatibleDeterminado/entrada/sel.in");
		sel.resolver();
	}

	@Test
	public void testCalculaErrorSolucion() {
		SEL sel = new SEL("test/SEL/01_SistemaCompatibleDeterminado/entrada/sel.in");
		sel.resolver();
		sel.calcularErrorSolucion();
	}
	
	@Test
	public void testMostrarSolucion() {
		SEL sel = new SEL("test/SEL/01_SistemaCompatibleDeterminado/entrada/sel.in");
		sel.resolver();
		sel.mostrarSolucion();
	}

	@Test
	public void testEscribeSolucionEnArchivo() {
		SEL sel = new SEL("test/SEL/01_SistemaCompatibleDeterminado/entrada/sel.in");
		sel.resolver();
		sel.escribirSolucionEnArchivo("test/SEL/01_SistemaCompatibleDeterminado/salida/solucion.out");
	}
	
	// Con estos dos métodos se testean los casos de los sistemas SCI y SI
	@Test
	public void testSistemaCompatibleIndeterminado() {
		SEL sel = new SEL("test/SEL/02_SistemaCompatibleIndeterminado/entrada/sel.in");
		sel.resolver();
		sel.escribirSolucionEnArchivo("test/SEL/02_SistemaCompatibleIndeterminado/salida/solucion.out");
	}
	
	@Test
	public void testSistemaIncompatible() {
		SEL sel = new SEL("test/SEL/03_SistemaIncompatible/entrada/sel.in");
		sel.resolver();
		sel.escribirSolucionEnArchivo("test/SEL/03_SistemaIncompatible/salida/solucion.out");
	}
}
