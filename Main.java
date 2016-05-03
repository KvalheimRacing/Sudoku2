import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;

class Main {

private static int antallRader;    // I en boks
private static int antallKolonner; // I en boks
private static int antallBokser;   // dette er også det samme som lengden vertikalt (antall rader totalt) og horisontalt (antall kolonner totalt) mtp antall ruter.
private static int antallRuter;

  public static void main(String[] args) throws Exception {

    // Leser den filen man oppgir naar man kaller paa programmet
    try {
      lesFil(args[0]);
    }
    catch (FileNotFoundException e) {
      System.out.println("Fant ikke filen!");
    }



  }

  // Les inn metoden tar inn verdiene og tester/verifiserer dataene.
  // Den er tilslutt en dobbeltarray ar Ruter som den sender i opprettelsen av et nytt brett.
  public static void lesFil(String ar) throws Exception {

    // Leser inn filnavnet som ble sendt med ved kjoring
    Scanner in = new Scanner(new File(ar));
    ArrayList<Integer> arg = new ArrayList<Integer>();


    // Looper igjennom filen og legger inn verdiene i en vanlig array.
    // Kunne tatt det direkte inn i dobbelarrayen, men jeg ønsker flest
    // mulige trinn så det er enkelt å gripe inn i prosessen ved senere utvidelse av programmet.
    while (in.hasNext()) {
        String s = in.nextLine();
        char[] a = s.toCharArray();
          for(char c: a) {
            if (c == '.') {
              arg.add(0);
            }
            else {
                if ( 0 < Character.getNumericValue(c) && Character.getNumericValue(c) < 10) {
                  arg.add(Character.getNumericValue(c));
                }
                else {
                  throw new Exception("Ulovlig tegn i filen");
                }
            }
          }
      }

      // Sparer paa verdier
      antallRader = arg.get(0);
      antallKolonner = arg.get(1);

      antallBokser = antallRader*antallKolonner;
      antallRuter = antallBokser*antallBokser;

      // Kaster unntak hvis det er for stort brett
      if (antallBokser > 64) {
        throw new Exception("For stort brett");
      }

      // Kaster unntak hvis og antall tegn ikke stemmer
      if (antallRuter != (arg.size()-2)) {
        throw new Exception("Antall tegn stemmer ikke");
      }

        // b = 2 her gjor at man leser inn det tredje elementet i filen,
        // og dermed hopper over de tor forste verdiene som er initieringsverdier
        int b = 2;
        int[][] sudokubrett = new int[antallBokser][antallBokser];
        for(int i=0; i<antallBokser;i++) {
           for(int j=0;j<antallBokser;j++) {
                // Kaster unntak hvis tallet som skal settes inn er uteenfor lovlig intervall
                if (arg.get(b) > antallBokser) {
                  throw new Exception("Tall utenfor lovlig intervall");
                }
                // 1D array til 2D array
                sudokubrett[i][j] = arg.get(b);
                b++;
              }
          }


          // Etter at int arrayen er opprettet kjores den bare inn i objektene
          // Dette gjor jeg bevvist (to trinn) for aa kunne ha en fungerende int array tilgjengelig til senere beregninger
          Rute[][] brettetsRuter = new Rute[antallBokser][antallBokser];
          Rute[][] brettetsRuterOrginal = new Rute[antallBokser][antallBokser];
          Rute[][] brettetsRuterUferdig = new Rute[antallBokser][antallBokser];
            for(int i=0; i<antallBokser;i++) {
                for(int j=0;j<antallBokser;j++) {
                    // Sender med verdien i ruten (tallet mellom 1 og 9), saa x verdien/koordinaten (j), og saa y verdien/koordinaten (i)
                    Rute nyrute = new Rute(sudokubrett[i][j], j, i);
                    Rute nyruteOrginal = new Rute(sudokubrett[i][j], j, i);
                    Rute nyruteUferdig = new Rute(sudokubrett[i][j], j, i);
                    // Legger den nye ruten inn i rutearrayen
                    brettetsRuter[i][j] = nyrute;
                    brettetsRuterOrginal[i][j] = nyruteOrginal;
                    brettetsRuterUferdig[i][j] = nyruteOrginal;

                }
            }
          // Oppretter brettobjektet og sender med ferdige arrayen
          Brett brettet = new Brett(antallRader, antallKolonner, brettetsRuter, brettetsRuterOrginal, brettetsRuterUferdig);

          // Printer brettet for losning
          System.out.println("\nIkke utfylt brett:");
          brettet.printBrett();

          System.out.println("\nOrginalt brett");
          brettet.printBrettOrginal();

          System.out.println("");
          // Loser brettet
          brettet.losBrett();

          // Printer brettet etter losning
          brettet.printBrett();


   }
}
