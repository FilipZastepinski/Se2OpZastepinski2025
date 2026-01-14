package business;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AutoTest { //E-AR-234;Opel;Mokka;66;20251213-20251216_20251218-20251223
	
	private Auto auto;

	@BeforeEach
	void setUp() throws Exception {
		this.auto = new Auto("E-AR-234", "Opel", "Mokka", 66, new String[] {"20251213-20251216","20251218-20251223"});
	}

	@AfterEach
	void tearDown() throws Exception {
		this.auto = null;
	}

	@Test
	void test() {
		assertTrue(() -> this.auto.getTyp() == "Opel", "Autotyp nicht Opel!");
		
		Throwable exc = assertThrows(
				IllegalArgumentException.class, 
				() -> new Auto("E-AR-234", "Opel", "Mokka", 66, null));
		
		assertEquals("Keine Vermietungsdaten!",exc.getMessage());
		//assertNotEquals("null",exc.getMessage());
	}

}
