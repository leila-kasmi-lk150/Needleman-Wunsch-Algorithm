package needleman.wunsch.algorithm;

import java.util.Scanner;

/**
 *
 * @author LEILA
 */
public class NeedlemanWunschAlgorithm {

    public static void main(String[] args) {
        int match;
        int mismatch;
        int gap;
        String seq1;
        String seq2;
        
        Scanner s = new Scanner(System.in);
        
        System.out.println("Enter the Sequence 1: ");
        seq1 = s.nextLine();
        
        System.out.println("Enter the Sequence 2: ");
        seq2 = s.nextLine();
        
        System.out.println("Enter match: ");
        match = s.nextInt();
        
        System.out.println("Enter mismatch: ");
        mismatch = s.nextInt();
        
        System.out.println("Enter gap: ");
        gap = s.nextInt();
        
        System.out.println("\n ********************************* \n");
        
        
        NeedlemanWunsch F = new NeedlemanWunsch(match, mismatch, gap, seq1, seq2);
        
    }
    
}
