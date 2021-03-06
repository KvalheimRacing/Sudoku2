class Rute {

   private int verdi = 0;
   private Kolonne minkolonne;
   private Boks minboks;
   private Rad minrad;
   private int xKoordinat;
   private int yKoordinat;

   // Oppretter ruter uavhengig av aa vite om rad, kolonne og boks
   public Rute(int v, int xKoordinat, int yKoordinat) {

       verdi = v;
       this.xKoordinat = xKoordinat;
       this.yKoordinat = yKoordinat;

   }

   // Legger til rad kolonne og boks naar programmet kommer til dette senere
   public void leggTilData(Rad raden, Kolonne kolonnen, Boks boksen) {

       minrad = raden;
       minkolonne = kolonnen;
       minboks = boksen;

   }

   // Returnerer toString verdien
   public String toString() {
      return verdi + " paa plass x" + xKoordinat + " y" + yKoordinat;
   }


   // Finner alle mulige tall til ruten
   public int[] finnAlleMuligeTall() {

     int teller = 0;
     int[] alleverdier = new int[minkolonne.getSize()];


     //minrad.printVerdier();
     //minkolonne.printVerdier();
     //minboks.printVerdier();

        for (int i=1; i<(minkolonne.getSize()+1); i++) {
            // Sjekker at verdien ikke fins noen andre steder
            // Lager en arry med alle mulige verdier;
            if ( !minrad.sjekkVerdi(i) && !minkolonne.sjekkVerdi(i) && !minboks.sjekkVerdi(i) ) {
              alleverdier[teller] = i;
              //System.out.println("La til verdien " + i + " i arrayen til finnAlleMuligeTall");
              teller++;
            }
        }

      // Sjekker om den naavaerende verdien finnes flere ganger enn 1 i rad eller kolonne eller boks
      // Hvis verdien kun fins en gang i minrad, minkolonne og minboks, legg den til i arrayen
      if (minrad.sjekkAntallForekomster(verdi) == 1 && minkolonne.sjekkAntallForekomster(verdi) == 1 && minboks.sjekkAntallForekomster(verdi) == 1) {
        alleverdier[minkolonne.getSize()-1] = verdi;
      }

      if (alleverdier[0] < 1 && alleverdier[minkolonne.getSize()-1] < 1) {
        System.out.println("Ingen verdier passer");
        return null;
      }

      return alleverdier;
   }

   public int getVerdi() {
     return verdi;
   }

   public boolean setVerdi(int i) {
     verdi = i;
     if (verdi > 0) {return true;}
     else {return false;}
   }

   public int getxKoordinat() {
     return xKoordinat;
   }

   public int getyKoordinat() {
     return yKoordinat;
   }

   public Kolonne getminkolonne() {
     return minkolonne;
   }

   public Boks getminboks() {
     return minboks;
   }

   public Rad getminrad() {
    return minrad;
   }

}
