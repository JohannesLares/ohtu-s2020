package ohtu.verkkokauppa;

interface Bank {
  public boolean tilisiirto(String nimi, int viitenumero, String tililta, String tilille, int summa);
}