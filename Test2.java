public class Test2 {
    static int brettetsRuterOrginal[][]=new int[][]
           {{0,2,0,0,0,0,0,0,0},
            {0,0,3,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,7,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0}};
    static int brettetsRuter[][]=new int[9][9];

    public static void main(String[] args) {
        for (int j=0;j<9;j++) {
            for (int k=0;k<9;k++) {
                brettetsRuter[j][k]=brettetsRuterOrginal[j][k];
            }
        }
        printDritten(brettetsRuter);
        printDritten(fyllUtDenneRuteOgResten(0, 0, brettetsRuter));
    }

    public static int[][] fyllUtDenneRuteOgResten(int i, int j, int[][] brettetsRuter) {

        while(!enkelValid(8, 8, brettetsRuter) || brettetsRuter[8][8]==0)
        {
            if (brettetsRuterOrginal[i][j]!=0) {
                int ii, jj;
                if (j==8) {ii=i+1; jj=0;} else {ii=i; jj=j+1;}
                fyllUtDenneRuteOgResten(ii, jj, brettetsRuter);
            } else {
                if (brettetsRuter[i][j]<9) {
                    brettetsRuter[i][j]++;
                    if (enkelValid(i, j, brettetsRuter)) {
                        int ii, jj;
                        if (j==8) {ii=i+1; jj=0;} else {ii=i; jj=j+1;}
                        fyllUtDenneRuteOgResten(ii, jj, brettetsRuter);
                    }
                } else {
                    brettetsRuter[i][j]=0;
                    break;
                }
            }
        }
        return brettetsRuter;
    }
    public static boolean enkelValid(int i, int j, int[][] brettetsRuter) {
        String temp="";
        for (int k=0;k<9;k++) {
            temp+=Integer.toString(brettetsRuter[k][j]);
            temp+=Integer.toString(brettetsRuter[i][k]);
            temp+=Integer.toString(brettetsRuter[(i/3)*3+k/3][(j/3)*3+k%3]);
        }
        int teller1=0, teller2=0;
        while ((teller2=temp.indexOf(Integer.toString(brettetsRuter[i][j]), teller2))!=-1)
        {teller2++; teller1++;}
        return teller1==3;
    }

    public static void printDritten(int[][] brettetsRuter) {
        System.out.println();
        for (int i=0;i<9;i++) {
            for (int j=0;j<9;j++) {
                System.out.print(brettetsRuter[i][j]);
            }
            System.out.println();
        }
    }
}
