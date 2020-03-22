package test;

import main.domeinLaag.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

public class VluchtTest {

	static LuchtvaartMaatschappij lvm;
	static Fabrikant f1;
	static VliegtuigType vtt1;
	static Vliegtuig vt1;
	static Luchthaven lh1, lh2;
	static Vlucht vl1, vl2;

	@BeforeEach
	public void initialize() {
		try {
			lvm = new LuchtvaartMaatschappij("NLM");
			f1 = new Fabrikant("Airbus", "G. Dejenelle");
			vtt1 = f1.creeervliegtuigtype("A-200", 140);
			Calendar datum = Calendar.getInstance();
			datum.set(2000, 01, 01);
			vt1 = new Vliegtuig(lvm, vtt1, "Luchtbus 100", datum);
			Land l1 = new Land("Nederland", 31);
			Land l2 = new Land("België", 32);
			lh1 = new Luchthaven("Schiphol", "ASD", true, l1);
			lh2 = new Luchthaven("Tegel", "TEG", true, l2);
			Calendar vertr = Calendar.getInstance();
			vertr.set(2020, 03, 30, 14, 15, 0);
			Calendar aank = Calendar.getInstance();
			aank.set(2020, 03, 30, 15, 15, 0);
			vl1 = new Vlucht(vt1, lh1, lh2, vertr, aank);
			vertr.set(2020, 4, 1, 8, 15, 0);
			aank.set(2020, 4, 1, 9, 15, 0);
			vl2 = new Vlucht(vt1, lh1, lh2, vertr, aank);
		} catch (Exception e) {
			String errorMessage = "Exception: " + e.getMessage();
			System.out.println(errorMessage);
		}
	}

	/**
	 * Business rule:
	 * De bestemming moet verschillen van het vertrekpunt van de vlucht.
	 */

	@Test
	public void testBestemmingMagNietGelijkZijnAanVertrek_False() {
		Vlucht vlucht = new Vlucht();
		try {
			vlucht.zetVliegtuig(vt1);
			vlucht.zetVertrekpunt(lh1);
			Luchthaven bestemming = vlucht.getBestemming();
			assertTrue(bestemming == null);
			vlucht.zetBestemming(lh1);
			// De test zou niet verder mogen komen: er moet al een exception gethrowd zijn.
			bestemming = vlucht.getBestemming();
			assertFalse(bestemming.equals(lh1));
		} catch (IllegalArgumentException e) {
			Luchthaven bestemming = vlucht.getBestemming();
			assertFalse(bestemming.equals(lh1));
		}
	}

	/**
	 * Business rule:
	 * xxx
	 */




//	Van 15 t/m 20

	@Test
	public void testVliegtuigMagNietGelijkAanNull() {
		Vlucht vlucht = new Vlucht();
		Luchthaven vertrek;
		Calendar vertrektijd = Calendar.getInstance();
		Calendar aankomsttijd = Calendar.getInstance();
		aankomsttijd.add(Calendar.MINUTE, 1);
		try {
			vlucht.zetVliegtuig(null);
			vlucht.zetVertrekpunt(lh2);
			vlucht.zetBestemming(lh1);
			vlucht.bewaar();
			Vliegtuig vliegtuig = vlucht.getVliegtuig();
			assertNotEquals(null, vliegtuig, "Vliegtuig Ongeldig");
		} catch (VluchtException e) {
			assertEquals("Geen geldige vliegtuig.", e.getMessage());
		}


	}


	@Test
	public void testVertrekpuntMagNietGelijkZijnAanNull() {
		Vlucht vlucht = new Vlucht();
		Luchthaven vertrek;
		Calendar vertrektijd = Calendar.getInstance();
		Calendar aankomsttijd = Calendar.getInstance();
		aankomsttijd.add(Calendar.MINUTE, 1);
		try {
			vlucht.zetVliegtuig(vt1);
			vlucht.zetVertrekpunt(null);
			vlucht.zetBestemming(lh1);

			vlucht.zetVertrekTijd(vertrektijd);
			vlucht.zetAankomstTijd(aankomsttijd);
			
			vlucht.bewaar();
			vertrek = vlucht.getVertrekPunt();
			assertNotEquals(null, vertrek, "Vertrekpunt Ongeldig");
		} catch (VluchtException e) {
			assertEquals("Geen geldig vertrekpunt.", e.getMessage());
		}


	}

	@Test
	public void testBestemmingpuntMagNietGelijkZijnAanNull() {
		Vlucht vlucht = new Vlucht();
		Luchthaven bestemming;
		Calendar vertrektijd = Calendar.getInstance();
		Calendar aankomsttijd = Calendar.getInstance();
		aankomsttijd.add(Calendar.MINUTE, 1);
		try {
			vlucht.zetVliegtuig(vt1);
			vlucht.zetVertrekpunt(lh2);
			vlucht.zetBestemming(null);

			vlucht.zetVertrekTijd(vertrektijd);
			vlucht.zetAankomstTijd(aankomsttijd);

			vlucht.bewaar();
			bestemming = vlucht.getBestemming();
			assertNotEquals(null, bestemming, "Bestemming Ongeldig");
		} catch (VluchtException e) {
			assertEquals("Geen geldige bestemming.", e.getMessage());
		}
	}


	@Test
	public void testVertrekTijdNietGelijkMagZijnAanNull() {
		Vlucht vlucht = new Vlucht();
		Calendar vertrektijd = null;
		Calendar aankomsttijd = Calendar.getInstance();
		aankomsttijd.add(Calendar.MINUTE, 1);

		try {
			vlucht.zetVliegtuig(vt1);
			vlucht.zetVertrekpunt(lh2);
			vlucht.zetBestemming(lh1);

			vlucht.zetVertrekTijd(vertrektijd);
			vlucht.zetAankomstTijd(aankomsttijd);

			vlucht.bewaar();

			Calendar vertrek = vlucht.getVertrekTijd();

			assertNotEquals(null, vertrek, "Vertrektijd ongeldig");


		} catch (VluchtException e) {
			assertEquals("Geen geldige vertrektijd.", e.getMessage());
		}
	}

	@Test
	public void testAankomstTijdNietGelijkMagZijnAanNull() {
		Vlucht vlucht = new Vlucht();
		Calendar vertrektijd = Calendar.getInstance();
		Calendar aankomsttijd = Calendar.getInstance();
		aankomsttijd.add(Calendar.MINUTE, 1);
		try {
			vlucht.zetVliegtuig(vt1);
			vlucht.zetVertrekpunt(lh2);
			vlucht.zetBestemming(lh1);


			vlucht.zetVertrekTijd(vertrektijd);
			vlucht.zetAankomstTijd(aankomsttijd);

			vlucht.bewaar();

			Calendar aankomst = vlucht.getVertrekTijd();

			assertNotEquals(null, aankomst, "Aanlomsttijd ongeldig");

		} catch (VluchtException e) {
			assertEquals("Geen geldige aankomsttijd.", e.getMessage());
		}
	}


	@Test
	public void testenBewarenHappyFlow() {
		Vlucht vlucht = new Vlucht();
		Calendar vertrektijd = Calendar.getInstance();

		Calendar aankomsttijd = null;
		try {
			vlucht.zetVliegtuig(vt1);
			vlucht.zetVertrekpunt(lh2);
			vlucht.zetBestemming(lh1);



			vlucht.zetVertrekTijd(vertrektijd);
			vlucht.zetAankomstTijd(aankomsttijd);

			vlucht.bewaar();

			assertEquals(vt1, vlucht.getVliegtuig());
			assertEquals(lh2, vlucht.getVertrekPunt());
			assertEquals(lh1, vlucht.getBestemming());
			assertEquals(vertrektijd, vlucht.getVertrekTijd());
			assertEquals(aankomsttijd, vlucht.getAankomstTijd());


		} catch (Exception e) {
			e.getMessage();
		}
	}










}