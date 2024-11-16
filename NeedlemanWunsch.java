package needleman.wunsch.algorithm;

/**
 *
 * @author LEILA
 */
public class NeedlemanWunsch {
    private int match;
    private int mismatch;
    private int gap;
    
    private  String seq1;
    private  String seq2;
    
    private  int[][] F;

    private int s;
    private String[][] traceMatrix;
    
    public NeedlemanWunsch(int match, int mismatch, int gap, String seq1, String seq2) {
        this.match = match;
        this.mismatch = mismatch;
        this.gap = gap;
        this.seq1 = seq1;
        this.seq2 = seq2; 
        initializationTableF();
        fillingTheMatrix();
        traceback();
    }
    private void initializationTableF(){
        
        F = new int[seq2.length()+1][seq1.length()+1];
        F[0][0] = 0;
        int gapX = gap;
        for(int j=1; j<seq1.length()+1; j++){
            F[0][j]= gapX;
            gapX += gap;
        }
        gapX = gap;
        for(int i=1; i<seq2.length()+1; i++){
            F[i][0]= gapX;
            gapX += gap;
        }
    }

     /**
      * F(i,j) = max {
      *              F(i-1,j-1) + S(i,j) ===> match OR mismutch
      *              F(i-1,j) + Gap
      *              F(i,j-1) + gap
      * }
     */
    
    
    private void fillingTheMatrix(){
        
        System.out.println("\n Filling the matrix ");
        this.traceMatrix = new String[seq2.length() + 1][seq1.length() + 1];
        for(int i=1; i<seq2.length()+1; i++){
            for(int j=1; j<seq1.length()+1; j++){
                if (seq1.charAt(j-1)==seq2.charAt(i-1)) {
                    s=match;
                } else {
                    s=mismatch;
                }
                int diagonal = F[i-1][j-1] + s;
                int vertical = F[i-1][j] + gap;
                int horizontal = F[i][j-1] + gap;
                F[i][j]=max(diagonal, vertical, horizontal);
                 if (F[i][j] == diagonal) {
                    traceMatrix[i][j]="D";
                 } else if (F[i][j] == vertical) {
                    traceMatrix[i][j]="V";
                 } else if (F[i][j] == horizontal) {
                     traceMatrix[i][j]="H";
                 }
            }
        }
        System.out.println(printMatrixF());
    }
    
    private StringBuilder trace1= new StringBuilder();
    private StringBuilder trace2= new StringBuilder();
    
    private void traceback(){    
        int i = seq2.length();
        int j = seq1.length();
        while (i > 0 && j > 0) {
            if (traceMatrix[i][j] == "D") {
                trace1.append(seq1.charAt(j-1));
                trace2.append(seq2.charAt(i-1));
                i--;
                j--;
            } else if (traceMatrix[i][j] == "V") {
                trace1.append("_");
                trace2.append(seq2.charAt(i-1));
                i--;
            } else if (traceMatrix[i][j] == "H") {
                trace1.append(seq1.charAt(j-1));
                trace2.append("_");
                j--;
            }
        }
        
        System.out.println("********************************");
        System.out.println("Global Alignment");
        trace1.reverse();
        trace2.reverse();
        System.out.println(trace1);
        System.out.println(trace2);
        
        System.out.println("\nScore = " + F[seq2.length()][seq1.length()]);
    }
    
    
    public String printMatrixF() {
        StringBuilder sb = new StringBuilder();
        sb.append(", F=\n");
      
        for (int[] row : F) {
            for (int cell : row) {
                sb.append(cell).append("\t");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
    


    private int max(int a, int b, int c) {
        return Math.max(Math.max(a, b), c);
    }
    
}
