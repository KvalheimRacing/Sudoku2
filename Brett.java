class Brett {

private Kolonne[] brettetsKolonner;
private Rad[] brettetsRader;
private Boks[] brettetsBokser;
private Rute[][] brettetsRuter;
private Rute[][] brettetsRuterOrginal;
private Rute[][] brettetsRuterUferdig;
private int antallRader;         // I en boks
private int antallKolonner;      // I en boks
private int antallBokser;        // Dette er også det samme som lengden vertikalt og horisontalt mtp antall ruter.
private int antallRuter;         // Totalt paa brettet
private boolean etTallBleUtfyldt; //verdien0ErSatt;



  Brett(int antRader, int antKolonner, Rute[][] brettetsRuter, Rute[][] brettetsRuterOrginal, Rute[][] brettetsRuterUferdig) {
    this.brettetsRuter = brettetsRuter;
    this.brettetsRuterOrginal = brettetsRuterOrginal;
    this.brettetsRuterUferdig = brettetsRuterUferdig;
    opprettDatastruktur(antRader,antKolonner);
  }


  // Indekserer boksene paa brettet fra 0 og oppover, og starter aa telle overst til venstre,
  // og går mot hoyre, deretter ned en rad og saa mot hoyre igjen osv
  public void opprettDatastruktur(int antallRader, int antallKolonner) {

    this.antallRader = antallRader;
    this.antallKolonner = antallKolonner;
    antallBokser = antallRader*antallKolonner;
    antallRuter = antallBokser*antallBokser;
    brettetsRader = new Rad[antallBokser];
    brettetsKolonner = new Kolonne[antallBokser];
    brettetsBokser = new Boks[antallBokser];

    // Oppretter alle radene, kolonnene og boksene i en array av dem
    for(int i = 0; i < antallBokser; i++) {
        brettetsRader[i] = new Rad(antallBokser);                         // Legger inn rader med riktig lengde inn i rad arrayen på riktig indeks
        brettetsKolonner[i] = new Kolonne(antallBokser);                  // Legger inn kolonner med riktig lengde inn i kolonne arrayen på riktig indeks
        brettetsBokser[i] = new Boks(i, antallKolonner, antallRader);     // Legger inn indekserte bokser med riktig verdier for x og y sorrelse
    }
    for (int j = 0; j < antallBokser; j++) {
        for (int k = 0; k < antallBokser; k++) {
          // k verdien forteller hvilken kolonne ( x verdi )
          // j verdien forteller hvilken rad     ( y verdi )
          // brettetsRuter[j][k] er en bestemt rute gitt av koordinatene j og k
          brettetsRader[j].settInn(brettetsRuter[j][k], k, j);
          brettetsKolonner[k].settInn(brettetsRuter[j][k], j, k);
          // Finner ut av hvilken boks ruten er i, og hva som skal inn i settInn()
          // System.out.println("Boks indeks er " + finnBoksIndeksUtIfraKoordinater(j,k) + " og verdien er " + brettetsRuter[j][k].getVerdi());
          brettetsBokser[finnBoksIndeksUtIfraKoordinater(j,k)].settInn(brettetsRuter[j][k]);
          brettetsRuter[j][k].leggTilData(brettetsRader[j],brettetsKolonner[k],brettetsBokser[finnBoksIndeksUtIfraKoordinater(j,k)]);
          brettetsRuterOrginal[j][k].leggTilData(brettetsRader[j],brettetsKolonner[k],brettetsBokser[finnBoksIndeksUtIfraKoordinater(j,k)]);
          brettetsRuterUferdig[j][k].leggTilData(brettetsRader[j],brettetsKolonner[k],brettetsBokser[finnBoksIndeksUtIfraKoordinater(j,k)]);

        }
    }
  }


  // Disse er kanskje ikke nodvendige, men har disse i tilfelle det er behov for dette tallet ved utvidelse
  private int r(int yKoordinat) {
    return (yKoordinat/antallRader);
  }

  // Disse er kanskje ikke nodvendige, men har disse i tilfelle det er behov for dette tallet ved utvidelse
  private int k(int xKoordinat) {
    return (xKoordinat/antallKolonner);
  }

  // Ref metodenavn
  // Dette gir som nevnt en indeks hvor man starter paa 0 og beveger seg bortover raden mot venstre,
  // og deretter ned og samme prosedyre. Altsa man starter i overste hjorne til venstre.
  private int finnBoksIndeksUtIfraKoordinater(int yKoordinat, int xKoordinat){
    return (k(xKoordinat) + (r(yKoordinat)*antallRader));
  }




  public Rute[][] fyllUtDenneRuteOgResten(int i, int j, Rute[][] brettetsRuter) {

      //|| brettetsRuter[antallBokser-1][antallBokser-1].getVerdi()!=0 || !valid((antallBokser-1), (antallBokser-1), brettetsRuter)
      // Kjor saa lenge det finnes en tom rute
      // || valid((antallBokser-1), (antallBokser-1), brettetsRuter)
      while(finnTomRute()) {

        // Hvis det er et allerde bestemt et tall der, gaa til neste nummer
        if (brettetsRuterUferdig[i][j].getVerdi() != 0) { //  && !verdien0ErSatt
                System.out.println("Brettet har en allerede bestemt verdi: " + brettetsRuterUferdig[i][j].getVerdi() + "  i rute  x:" + brettetsRuterUferdig[i][j].getxKoordinat() + "  y:" + brettetsRuterOrginal[i][j].getyKoordinat());
                int ii,jj;
                //  Hvis ruten er i enden, gaa ned og start paa x=0
                if (j==(antallBokser-1)) { ii = i+1; jj = 0; }
                // Hvis ikke, gaa en til siden
                else { ii = i; jj = j+1; }
                // Kaller rekursivt paa metoden
                fyllUtDenneRuteOgResten(ii,jj,brettetsRuter);
        }
        else {
                // Sjekker om verdien er mindre enn den stoerste verdien
                if (brettetsRuter[i][j].getVerdi() < antallBokser) {

                        // Ok verdien med 1
                        brettetsRuter[i][j].setVerdi((brettetsRuter[i][j].getVerdi()+1));
                        System.out.println("\nInsetting nr.1 vellykket! Satt inn " + brettetsRuter[i][j].getVerdi() + " paa plass    x:" + brettetsRuter[i][j].getxKoordinat() + "  y:" + brettetsRuter[i][j].getyKoordinat());
                        //verdien0ErSatt = false;
                        printBrett();

                        // Oppdaterer brettets kolonner, rader og bokser
                        brettetsKolonner[brettetsRuter[i][j].getxKoordinat()] = brettetsRuter[i][j].getminkolonne();
                        brettetsRader[brettetsRuter[i][j].getyKoordinat()] = brettetsRuter[i][j].getminrad();
                        brettetsBokser[finnBoksIndeksUtIfraKoordinater(brettetsRuter[i][j].getyKoordinat(), brettetsRuter[i][j].getxKoordinat())] = brettetsRuter[i][j].getminboks();

                        // Oppdaterer rutens minkolonne minrad og minboks med hva brettets kolonner rader og bokser har av verdier
                        brettetsRuter[i][j].leggTilData(brettetsRader[brettetsRuter[i][j].getyKoordinat()], brettetsKolonner[brettetsRuter[i][j].getxKoordinat()], brettetsBokser[finnBoksIndeksUtIfraKoordinater(brettetsRuter[i][j].getyKoordinat(), brettetsRuter[i][j].getxKoordinat())]);

                        // Her sjekkes om den nye vedien er valid
                        if(valid(i , j , brettetsRuter)) {
                                int ii,jj;
                                //  Hvis ruten er i enden, gaa ned og start paa x=0
                                if (j==(antallBokser-1)) { ii = i+1; jj = 0; }
                                // Hvis ikke, gaa en til siden
                                else { ii = i; jj = j+1; }// Kaller rekursivt paa metoden
                                fyllUtDenneRuteOgResten(ii,jj,brettetsRuter);
                        }
                }
                // hvis verdien er den stoerste verdien, sett til 0 og gaa tilbake en
                //else if (brettetsRuter[i][j].getVerdi() == antallBokser)
                else {
                        brettetsRuter[i][j].setVerdi(0);
                        System.out.println("\nInsetting nr.2 vellykket! Satt inn " + brettetsRuter[i][j].getVerdi() + " paa plass    x:" + brettetsRuter[i][j].getxKoordinat() + "  y:" + brettetsRuter[i][j].getyKoordinat());
                        //verdien0ErSatt = true;
                        printBrett();

                        // Oppdaterer brettets kolonner, rader og bokser
                        brettetsKolonner[brettetsRuter[i][j].getxKoordinat()] = brettetsRuter[i][j].getminkolonne();
                        brettetsRader[brettetsRuter[i][j].getyKoordinat()] = brettetsRuter[i][j].getminrad();
                        brettetsBokser[finnBoksIndeksUtIfraKoordinater(brettetsRuter[i][j].getyKoordinat(), brettetsRuter[i][j].getxKoordinat())] = brettetsRuter[i][j].getminboks();

                        // Oppdaterer rutens minkolonne minrad og minboks med hva brettets kolonner rader og bokser har av verdier
                        brettetsRuter[i][j].leggTilData(brettetsRader[brettetsRuter[i][j].getyKoordinat()], brettetsKolonner[brettetsRuter[i][j].getxKoordinat()], brettetsBokser[finnBoksIndeksUtIfraKoordinater(brettetsRuter[i][j].getyKoordinat(), brettetsRuter[i][j].getxKoordinat())]);

                        break;
                }
        }
      }
    return brettetsRuter;
  }

  // Fyller inn ruter som har obious losning forst. Dette er slik man loser det for haand, og det vil kunne fore til flere losninger med et svar
  public void fyllInnRuterMedBareEttMuligTall() {

      etTallBleUtfyldt = false;
      for (int y = 0; y < antallBokser; y++) {
          for (int x = 0; x < antallBokser; x++) {
              if (brettetsRuter[y][x].getVerdi()==0) {
                if (brettetsRuter[y][x].finnAlleMuligeTall().length == 1) {
                    brettetsRuterUferdig[y][x].setVerdi(brettetsRuter[y][x].finnAlleMuligeTall()[0]);
                    System.out.println("Satt inn verdien " + brettetsRuter[y][x].finnAlleMuligeTall()[0] + " paa plass x:" + brettetsRuter[y][x].getxKoordinat() + "  y:" + brettetsRuter[y][x].getyKoordinat());
                    etTallBleUtfyldt = true;
                }
              }
          }
      }
  }

  // Sjekker om verdien er blant de mulige som kan vaere utifra de andre tallene i raden/kolonnen/boksen
  public boolean valid( int i, int j, Rute[][] brettetsRuter){
      System.out.println("Valid sjekk: Rutens koordinater er:               x:" + brettetsRuter[i][j].getxKoordinat() + "  y:" + brettetsRuter[i][j].getyKoordinat());
      System.out.println("Valid sjekk: Verdien er " + brettetsRuter[i][j].getVerdi());
      try {
          System.out.println("Mulige verdier er:");
          for (int k = 0; k<brettetsRuter[i][j].finnAlleMuligeTall().length; k++) {
            // Det skal vaere kulighet for aa returnere true kun hvis tallet ikke er 0
            if (brettetsRuter[i][j].finnAlleMuligeTall()[k] !=0 ) {
              // Skriver ut mulige verdier
              System.out.println(brettetsRuter[i][j].finnAlleMuligeTall()[k]);
              if (brettetsRuter[i][j].getVerdi() == brettetsRuter[i][j].finnAlleMuligeTall()[k]) {
                System.out.println("Verdien er valid");
                return true;
              }
            }
          }
      }
      catch (Exception e) { System.out.println("Verdien er ikke valid koden krasjet"); return false; }
      System.out.println("Verdien er ikke valid");
      return false;
  }




  // Sjekker om det fins ruter med sifferet 0
  public boolean finnTomRute() {
    for (int i = 0; i<antallBokser; i++) {
      for (int j = 0; j<antallBokser; j++) {
        if (brettetsRuter[i][j].getVerdi() == 0) {
          return true;
        }
      }
    }
    return false;
  }



  // Fyller ut rutene som er tomme og loser brettet
  public void losBrett() {
    double tidStart = System.currentTimeMillis();
    // fyller ut rutene

    fyllInnRuterMedBareEttMuligTall();
    // Kjorer dette for det kan fore til nye losninger
    while (etTallBleUtfyldt) {
      fyllInnRuterMedBareEttMuligTall();
    }
    fyllUtDenneRuteOgResten( 0, 0, brettetsRuter);
    double tidSlutt = System.currentTimeMillis();
    System.out.println("\nDet tok " + (tidSlutt-tidStart) + " millisek aa gjennomfore");
  }



  // Printer ut sodukobrettet
  public void printBrett() {

    int horisontalteller = 0;
    int vertikalteller = 0;
    System.out.println();
    for (int i=0;i<antallBokser;i++) {

      if ( vertikalteller != 0 && vertikalteller % antallRader == 0 ) {
          while (horisontalteller < antallBokser) {
              for (int k = 0; k < antallKolonner; k++) {
                System.out.print("-");
                horisontalteller++;
              }
              if ( horisontalteller != 0 && horisontalteller != antallBokser && horisontalteller % antallKolonner == 0 ) {
                System.out.print("+");
              }
          }
          System.out.println();
          horisontalteller = 0;
      }



      for (int j=0;j<antallBokser;j++) {
            // for hver antallKolonner skal det være en strek, forutastt at vi ikke er på enden
            if ( horisontalteller != 0 && horisontalteller % antallKolonner == 0 ) {
              System.out.print("|");
            }
            if ( brettetsRuter[i][j].getVerdi() == 0 ){
              System.out.print(" ");
            }
            else {
              System.out.print(brettetsRuter[i][j].getVerdi());
            }
            horisontalteller++;
      }

      System.out.println();
      horisontalteller = 0;
      vertikalteller++;
    }
  }

  // Printer ut sodukobrettet
  public void printBrettOrginal() {

    int horisontalteller = 0;
    int vertikalteller = 0;
    System.out.println();
    for (int i=0;i<antallBokser;i++) {

      if ( vertikalteller != 0 && vertikalteller % antallRader == 0 ) {
          while (horisontalteller < antallBokser) {
              for (int k = 0; k < antallKolonner; k++) {
                System.out.print("-");
                horisontalteller++;
              }
              if ( horisontalteller != 0 && horisontalteller != antallBokser && horisontalteller % antallKolonner == 0 ) {
                System.out.print("+");
              }
          }
          System.out.println();
          horisontalteller = 0;
      }



      for (int j=0;j<antallBokser;j++) {
            // for hver antallKolonner skal det være en strek, forutastt at vi ikke er på enden
            if ( horisontalteller != 0 && horisontalteller % antallKolonner == 0 ) {
              System.out.print("|");
            }
            if ( brettetsRuterOrginal[i][j].getVerdi() == 0 ){
              System.out.print(" ");
            }
            else {
              System.out.print(brettetsRuterOrginal[i][j].getVerdi());
            }
            horisontalteller++;
      }

      System.out.println();
      horisontalteller = 0;
      vertikalteller++;
    }
  }
}
