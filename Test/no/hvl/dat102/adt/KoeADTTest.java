package no.hvl.dat102.adt;

import static org.junit.Assert.assertEquals;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import no.hvl.dat102.exceptions.EmptyCollectionException;

public abstract class KoeADTTest {

	private KoeADT<Integer> koe;

	private Integer e0 = 1;
	private Integer e1 = 2;
	private Integer e2 = 3;
	private Integer e3 = 4;
	// private Integer e4 = 5;

	protected abstract KoeADT<Integer> reset();

	@BeforeEach
	public void setup() {
		koe = reset();
	}
	
	@Test
	public void nyKoeErTom() {
		try {
		assertTrue(koe.erTom());
		} catch (Exception e) {
			System.out.println("feil");
		}
	}
	
	@Test
	public void innOgUt() {
		koe.innKoe(e0);
		koe.innKoe(e1);
		koe.innKoe(e2);
		koe.innKoe(e3);
		try {
			assertEquals(e0, koe.utKoe());
			assertEquals(e1, koe.utKoe());
			assertEquals(e2, koe.utKoe());
			assertEquals(e3, koe.utKoe());
		} catch (no.hvl.dat102.exceptions.EmptyCollectionException e) {
			fail("uventet feil" + e.getMessage());
		}
	}
	
	@Test
	public void innUtOgTomIgjen() {
		koe.innKoe(e0);
		koe.utKoe();
		assertTrue(koe.erTom());
	}
	
	@Test
	public void ikkeTom() {
		koe.innKoe(e0);
		koe.innKoe(e1);
		assertFalse(koe.erTom());
	}
	
	@Test
	public void innUtInnInnUtFoerste() {
		koe.innKoe(e0);
		koe.utKoe();
		koe.innKoe(e1);
		koe.innKoe(e2);
		koe.utKoe();
		assertEquals(koe.foerste(), e2);
	}
	
	@Test
	public void innOgUtMedDuplikater() {

		koe.innKoe(e0);
		koe.innKoe(e1);
		koe.innKoe(e1);
		koe.innKoe(e2);

		try {
			assertEquals(e0, koe.utKoe());
			assertEquals(e1, koe.utKoe());
			assertEquals(e1, koe.utKoe());
			assertEquals(e2, koe.utKoe());
		} catch (no.hvl.dat102.exceptions.EmptyCollectionException e) {
			fail("uventet feil" + e.getMessage());
		}
	}
	
	@Test
	public void utAvTomFeil() {
		/*
		 * Assertions.assertThrows(EmptyCollectionException.class, new Executable() {
		 * 
		 * @Override public void execute() throws Throwable { stabel.pop(); } });
		 */

		Assertions.assertThrows(EmptyCollectionException.class, () -> {
			koe.utKoe();
		});
	}
	
}
