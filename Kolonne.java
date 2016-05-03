class Kolonne {
  private Rute[] kolonnen;
  private int kolonnenummer;

  Kolonne(int ant) {
      kolonnen = new Rute[ant];
  }

  public void settInn(Rute r, int index, int kolonnenr) {
    kolonnen[index] = r;
    kolonnenummer = kolonnenr;
  }

  // Sjekker om kolonnen inneholder en gitt verdi
  public boolean sjekkVerdi(int verdiSomSkalSjekkes) {
    for (int i = 0; i<kolonnen.length; i++) {
        if ( kolonnen[i].getVerdi() == verdiSomSkalSjekkes) {
          return true;
        }
      }
    return false;
  }

  // Sjekker hvor mange ganger et tall forekommer
  public int sjekkAntallForekomster(int i){
    int teller = 0;
    for (int k = 0; k<kolonnen.length; k++) {
      if ( i == kolonnen[k].getVerdi() ) {
        teller++;
      }
    }
    return teller;
  }

  // Printer ut verdiene i kolonnen
  public void printVerdier() {

    System.out.println("Verdier for kolonne nr " + kolonnenummer);

    for (int i = 0; i<kolonnen.length; i++) {
          System.out.println(kolonnen[i].getVerdi());
    }
  }


  public int getSize() {
    return kolonnen.length;
  }

}
