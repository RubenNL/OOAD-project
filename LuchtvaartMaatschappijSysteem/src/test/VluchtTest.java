package test;

import main.domeinLaag.*;

import main.userInterfaceLaag.Main;
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
			new Main().initializeDomainObjects();
			lvm=LuchtvaartMaatschappij.getCurrentLuchtvaartMaatschappij();
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
	@Test
	public void test2_DatumsGelijkAanNullMogenGeenErrorsOpleveren() {
		Vlucht vlucht = new Vlucht();

		Calendar vertrektijd = null;
		Calendar aankomstijd = null;
		assertDoesNotThrow(
				() -> vlucht.zetVertrekTijd(vertrektijd),
				"Zou geen foutmelding moeten geven bij geldige datum"
		);
		assertDoesNotThrow(
				() -> vlucht.zetAankomstTijd(aankomstijd),
				"Zou geen foutmelding moeten geven bij geldige datum"
		);


	}


	@Test
	public void test3_OngeldigeVertrekDatum() {
		Vlucht vlucht = new Vlucht();
		Calendar vertrektijd = Calendar.getInstance();
		vertrektijd.set(2025, 8, 31, 24, 00, 0);

		assertThrows(
				VluchtException.class,
				() -> vlucht.zetVertrekTijd(vertrektijd),
				"Geen foutmelding bij ongeldige datum/tijd"
		);
	}

	@Test
	public void test4_OngeldigeAankomstTijd(){
		Vlucht vlucht = new Vlucht();
		Calendar aankomsttijd = Calendar.getInstance();
		aankomsttijd.set(2025, 8, 31, 24, 01, 0);

		assertThrows(
				VluchtException.class,
				() -> vlucht.zetAankomstTijd(aankomsttijd),
				"Geen foutmelding bij ongeldige datum/tijd"
		);
	}

	@Test
	public void test5_GeldigeVertrektijdEnAankomstTijd(){
		Vlucht vlucht = new Vlucht();

		Calendar vertrektijd = Calendar.getInstance();
		vertrektijd.set(2025, 8, 12, 12, 00, 0);

		assertDoesNotThrow(
				() -> vlucht.zetVertrekTijd(vertrektijd),
				"Zou geen foutmelding moeten geven bij geldige datum"
		);

		Calendar aankomsttijd = Calendar.getInstance();
		aankomsttijd.set(2025, 8, 12, 12, 01, 0);

		assertDoesNotThrow(
				() -> vlucht.zetAankomstTijd(aankomsttijd),
				"Zou geen foutmelding moeten geven bij geldige datum"
		);
	}

	@Test
	// Test 6
	public void test6_GeldigeVertrekpuntEnBestemming(){
		Vlucht vlucht = new Vlucht();
		Calendar vertrektijd = Calendar.getInstance();
		vertrektijd.add(Calendar.MINUTE, -1);

		assertThrows(
				VluchtException.class,
				() -> vlucht.zetAankomstTijd(vertrektijd),
				"Geen foutmelding bij ongeldige datum/tijd"
		);
	}


	@Test
	public void test7_VertrekTijdInHetVerledenMagNiet() {
		Vlucht vlucht = new Vlucht();
		Calendar vertrektijd = Calendar.getInstance();
		vertrektijd.add(Calendar.MINUTE, -1);

		try {
			vlucht.zetVliegtuig(vt1);
			vlucht.zetVertrekpunt(lh2);
			vlucht.zetBestemming(lh1);

			vlucht.zetVertrekTijd(vertrektijd);

			vlucht.bewaar();

			Calendar tijdVlucht = vlucht.getVertrekTijd();

			assertEquals(null, tijdVlucht, "Geen geldige vertrekTijd");

		} catch (VluchtException e) {
			assertEquals("Geen geldige aankomsttijd.", e.getMessage());
		}
	}
	@Test
	public void test9_Vertrektijd_voor_Begintijd() {
		Vlucht vlucht=new Vlucht();
		Calendar vertrektijd=Calendar.getInstance();
		assertDoesNotThrow(() -> vlucht.zetVertrekTijd(vertrektijd),
				"Zou geen foutmelding bij invullen vertrektijd");
		Calendar aankomsttijd=Calendar.getInstance();
		aankomsttijd.add(Calendar.MINUTE,-1);
		VluchtException exception=assertThrows(
				VluchtException.class,
				() -> vlucht.zetAankomstTijd(aankomsttijd),
				"Geen foutmelding bij aankomsttijd voor vertrektijd"
		);
		assertEquals(exception.getMessage(),"Aankomsttijd voor vertrektijd","Foutmelding klopt niet");
	}

	@Test
	public void test8_10_aankomstTijd_1_minuut_Na_VertrekTijd() {
		Vlucht vlucht=new Vlucht();
		Calendar vertrektijd=Calendar.getInstance();
		assertDoesNotThrow(() -> vlucht.zetVertrekTijd(vertrektijd),"Zou geen foutmelding bij invullen vertrektijd");
		Calendar aankomsttijd=Calendar.getInstance();
		aankomsttijd.add(Calendar.MINUTE,+1);
		assertDoesNotThrow(() -> vlucht.zetAankomstTijd(aankomsttijd),"Geen foutmelding bij aankomsttijd 1 minuut na vertrektijd");
	}

	@Test
	public void test11_zelfde_vertrektijd_als_andere_aankomsttijd() {
		Vlucht vlucht=new Vlucht();
		vlucht.zetVliegtuig(lvm.geefVliegtuigen().get("Fokke"));
		Calendar vertrektijd=Calendar.getInstance();
		vertrektijd.set(2025,Calendar.JULY,1,15,36);
		VluchtException exception=assertThrows(
				VluchtException.class,
				() -> vlucht.zetVertrekTijd(vertrektijd),
				"Geen melding bij dubbelgeboekt vliegtuig."
		);
		assertTrue(exception.getMessage().contains("Vliegtuig reeds bezet op"),"Foutmelding klopt niet");
	}
	@Test
	public void test12_aankomstTijd_in_andere_vlucht() {
		Vlucht vlucht=new Vlucht();
		vlucht.zetVliegtuig(lvm.geefVliegtuigen().get("Fokke"));
		Calendar vertrektijd=Calendar.getInstance();
		vertrektijd.set(2025,Calendar.JULY,1,11,36);
		assertDoesNotThrow(() -> vlucht.zetVertrekTijd(vertrektijd),"geeft een foutmelding bij aankomsttijd ruim voor vertrektijd nieuwe vlucht");
		Calendar aankomstTijd=Calendar.getInstance();
		aankomstTijd.set(2025,Calendar.JULY,1,12,43);
		VluchtException exception=assertThrows(
				VluchtException.class,
				() -> vlucht.zetAankomstTijd(aankomstTijd),
				"Geen melding bij dubbelgeboekt vliegtuig."
		);
		assertTrue(exception.getMessage().contains("Vliegtuig reeds bezet op"),"Foutmelding klopt niet");
	}
	@Test
	public void test13_alles_dubbel() {
		Vlucht vlucht=new Vlucht();
		vlucht.zetVliegtuig(lvm.geefVliegtuigen().get("Fokke"));
		Calendar vertrektijd=Calendar.getInstance();
		vertrektijd.set(2025,Calendar.JULY,1,12,42);
		VluchtException exception=assertThrows(
				VluchtException.class,
				() -> vlucht.zetVertrekTijd(vertrektijd),
				"Geen melding bij dubbelgeboekt vliegtuig."
		);
		assertTrue(exception.getMessage().contains("Vliegtuig reeds bezet op"),"Foutmelding klopt niet");
		Calendar aankomstTijd=Calendar.getInstance();
		aankomstTijd.set(2025,Calendar.JULY,1,15,37);
		VluchtException exception2=assertThrows(
				VluchtException.class,
				() -> vlucht.zetAankomstTijd(aankomstTijd),
				"Geen melding bij dubbelgeboekt vliegtuig."
		);
		assertTrue(exception2.getMessage().contains("Vliegtuig reeds bezet op"),"Foutmelding klopt niet");
	}
	@Test
	public void test14_geen_dubbele_vlucht() {
		Vlucht vlucht=new Vlucht();
		vlucht.zetVliegtuig(lvm.geefVliegtuigen().get("Fokke"));
		Calendar vertrektijd=Calendar.getInstance();
		vertrektijd.set(2025,Calendar.JULY,1,15,37);
		assertDoesNotThrow(() -> vlucht.zetVertrekTijd(vertrektijd),"geeft een foutmelding waar dat niet nodig is.");
		Calendar aankomstTijd=Calendar.getInstance();
		aankomstTijd.set(2025,Calendar.JULY,1,16,37);
		assertDoesNotThrow(() -> vlucht.zetAankomstTijd(aankomstTijd),"geeft een foutmelding waar dat niet nodig is.");
	}

	@Test
	public void test15_VliegtuigMagNietGelijkAanNull() {
		Vlucht vlucht = new Vlucht();
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
	public void test16_VertrekpuntMagNietGelijkZijnAanNull() {
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
	public void test17_BestemmingpuntMagNietGelijkZijnAanNull() {
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
	public void test18_VertrekTijdNietGelijkMagZijnAanNull() {
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
	public void test19_AankomstTijdNietGelijkMagZijnAanNull() {
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
	public void test20_BewarenHappyFlow() {
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
