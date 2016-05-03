class Boks {
  private Rute[][] mineRuter;
  private int minRadLengde;
  private int minKolonneLengde;
  private int minBoksIndeks;
  private int tellerAntallLagtInn = 0;
  private int tellerHorisontalt = 0;
  private int tellerVertikalt = 0;


  Boks(int indeks, int antallKolonner, int antallRader) {

    // Oppretter Rute arrayen i formatet [vertikal][horisontal]
    mineRuter = new Rute[antallRader][antallKolonner];
    minRadLengde = antallKolonner;
    minKolonneLengde = antallRader;
    minBoksIndeks = indeks;

  }

  // Setter inn ruter i den 2dimmensjonale Rute arrayen, og plasserer dem paa riktig sted i boksen.
  public void settInn(Rute r) {


    // Setter inn ruter saafremt det er plass i boksen
    if ( tellerAntallLagtInn < (minRadLengde*minKolonneLengde) ) {

            // Legger inn rute paa riktig plass
            mineRuter[tellerVertikalt][tellerHorisontalt] = r;

            // Oppdaterer hva som er neste riktige ledige plass
            if ( tellerHorisontalt < (minRadLengde-1) ) {
              tellerHorisontalt++;
            }
            else if ( tellerHorisontalt == (minRadLengde-1) ) {
              tellerVertikalt++;
              tellerHorisontalt = 0;
            }
            tellerAntallLagtInn++;
    }

  }

  // Sjekker om boksen inneholder en gitt verdi
  public boolean sjekkVerdi(int verdiSomSkalSjekkes) {
    for (int i = 0; i<minKolonneLengde; i++) {
      for (int j = 0; j<minRadLengde; j++) {
        if ( mineRuter[i][j].getVerdi() == verdiSomSkalSjekkes) {
          // System.out.println("i " + i + " j " + j);
          // System.out.println(mineRuter[i][j].getVerdi());
          return true;
        }
      }
    }
    return false;
  }

  // Sjekker hvor mange ganger et tall forekommer
  public int sjekkAntallForekomster(int i){
    int teller = 0;
    for (int k = 0; k<minKolonneLengde; k++) {
      for (int l = 0; l<minRadLengde; l++) {
        if ( i == mineRuter[k][l].getVerdi() ) {
          teller++;
        }
      }
    }
    return teller;
  }

  // Printer ut verdiene i boksen
  public void printVerdier() {

    System.out.println("Verdier for boks nr " + minBoksIndeks);

    for (int i = 0; i<minKolonneLengde; i++) {
      for (int j = 0; j<minRadLengde; j++) {
          System.out.println(mineRuter[i][j].getVerdi());
      }
    }
  }


}
