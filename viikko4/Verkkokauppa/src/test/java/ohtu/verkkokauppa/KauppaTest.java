package ohtu.verkkokauppa;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class KauppaTest {

    Pankki pankki;
    Viitegeneraattori viite;
    Varasto varasto;
    Kauppa k;

    @Before
    public void setUp() {
      pankki = mock(Pankki.class);

      viite = mock(Viitegeneraattori.class);
      // määritellään että viitegeneraattori palauttaa viitten 42
      when(viite.uusi()).thenReturn(42);

      varasto = mock(Varasto.class);
      // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
      when(varasto.saldo(1)).thenReturn(10); 
      when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

      // sitten testattava kauppa 
      k = new Kauppa(varasto, pankki, viite);    
    }

    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaan() {
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(anyString(), anyInt(), anyString(), anyString(),anyInt());   
        // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
    }

    @Test
    public void aloitetaanAsiointiaLisataanTuoteJaOstetaan() {
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("akka", "11211");

        verify(pankki).tilisiirto("akka", 42, "11211", "33333-44455", 5);
    }

    @Test
    public void aloitetaanAsiointiKaksiOstostaJaOnnistuu() {
        when(varasto.saldo(2)).thenReturn(6);
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "kaakao", 3));

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(2);
        k.tilimaksu("testi", "123321");
        verify(pankki).tilisiirto("testi", 42, "123321", "33333-44455", 8);
    }

    @Test
    public void ostetaanKaksiSamaaTuotetta() {
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(1);
        k.tilimaksu("testi", "123321");
        verify(pankki).tilisiirto("testi", 42, "123321", "33333-44455", 10);
    }

    @Test
    public void ostetaanOlevaaJaLoppunutta() {
      when(varasto.saldo(2)).thenReturn(0);
      when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "kaakao", 3));

      k.aloitaAsiointi();
      k.lisaaKoriin(1);
      k.lisaaKoriin(2);
      k.tilimaksu("testi", "123321");
      verify(pankki).tilisiirto("testi", 42, "123321", "33333-44455", 5);
    }
}