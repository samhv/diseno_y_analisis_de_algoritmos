package algoritmos;


public class KnuthMorrisPrattV2 extends TextSearcher{
	
    private final int R;       // the radix
    private int[][] dfa;       // the KMP automoton

    private String pat;        // or the pattern string

    /**
     * Preprocesses the pattern string.
     *
     * @param pat the pattern string
     */
    public KnuthMorrisPrattV2() {
        this.R = 256;
//        this.pat = pat;
//
//        // build DFA from pattern
//        int M = pat.length();
//        dfa = new int[R][M]; 
//        dfa[pat.charAt(0)][0] = 1; 
//        for (int X = 0, j = 1; j < M; j++) {
//            for (int c = 0; c < R; c++) 
//                dfa[c][j] = dfa[c][X];     // Copy mismatch cases. 
//            dfa[pat.charAt(j)][j] = j+1;   // Set match case. 
//            X = dfa[pat.charAt(j)][X];     // Update restart state. 
//        } 
    } 

    public int[][] getDfa(){
    	return dfa;
    }

    /**
     * Returns the index of the first occurrrence of the pattern string
     * in the text string.
     *
     * @param  txt the text string
     * @return the index of the first occurrence of the pattern string
     *         in the text string; N if no such match
     */
    public int search(String txt) {
    	
    	int cantidad = 0;
    	
        // simulate operation of DFA on text
        int largoPatron = pat.length();
        int largoTexto = txt.length();
        int i, j;
        
        for(int k = 0 ; k < largoTexto ; k++){
        	
        	 for (i = k, j = 0; i < largoTexto && j < largoPatron; i++) {
                 j = dfa[txt.charAt(i)][j];
             }
        	 k = i+1;
             if (j == largoPatron) {
       
            	 cantidad ++ ;
//            	 return i - largoPatron;    // found
             }
             
                  
        }
        
        return cantidad;
                   // not found
    }

   


    /** 
     * Takes a pattern string and an input string as command-line arguments;
     * searches for the pattern string in the text string; and prints
     * the first occurrence of the pattern string in the text string.
     */
    public static void main(String[] args) {
        String pat = args[0];
        String txt = args[1];       

        KnuthMorrisPrattV2 kmp1 = new KnuthMorrisPrattV2();
        
        System.out.println(kmp1.search(pat, txt));
//        int offset1 = kmp1.search(txt);
//
//        // print results
//        System.out.println("text:    " + txt);
//
//        System.out.print("pattern: ");
//        for (int i = 0; i < offset1; i++)
//        	 System.out.print(" ");
//        System.out.println(pat);

    }


	@Override
	protected int search(String pattern, String text) {
        this.pat = pattern;

        // build DFA from pattern
        int M = pat.length();
        dfa = new int[R][M]; 
        dfa[pat.charAt(0)][0] = 1; 
        for (int X = 0, j = 1; j < M; j++) {
            for (int c = 0; c < R; c++) 
                dfa[c][j] = dfa[c][X];     // Copy mismatch cases.
            
            dfa[pat.charAt(j)][j] = j+1;   // Set match case. 
            X = dfa[pat.charAt(j)][X];     // Update restart state. 
        } 
			
		
		return search(text);
	}
	
	
}
