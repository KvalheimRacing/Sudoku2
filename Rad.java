class Rad {
  private Rute[] raden;
  private int radnummer;

  Rad(int ant) {
      raden = new Rute[ant];
  }

  public void settInn(Rute r, int index, int radnr) {
    raden[index] = r;
    radnummer = radnr;
  }

  // Sjekker om raden inneholder en gitt verdi
  public boolean sjekkVerdi(int verdiSomSkalSjekkes) {
    for (int i = 0; i<raden.length; i++) {
        if ( raden[i].getVerdi() == verdiSomSkalSjekkes ) {
          return true;
        }
      }
    return false;
  }

  // Sjekker hvor mange ganger et tall forekommer
  public int sjekkAntallForekomster(int i){
    int teller = 0;
    for (int k = 0; k<raden.length; k++) {
      if ( i == raden[k].getVerdi() ) {
        teller++;
      }
    }
    return teller;
  }

  // Printer ut verdiene i raden
  public void printVerdier() {

    System.out.println("Verdier for rad nr " + radnummer);

    for (int i = 0; i<raden.length; i++) {
          System.out.println(raden[i].getVerdi());
    }
  }

  public int getSize() {
    return raden.length;
  }


}
