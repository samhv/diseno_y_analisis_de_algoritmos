package algoritmos;

public class FuerzaBruta extends TextSearcher {
	
	public FuerzaBruta(){
		
	}

	@Override
	protected int search(String pattern, String text) {
		
		int cantidad = 0;
		int m = text.length();  
        int n = pattern.length();  
        
        int punteroGlobal = 0;
        
		while(punteroGlobal + (n - 1) < m){
			
			for (int indexInPattern = 0; indexInPattern < n ; indexInPattern++) { 
				int indexInText = punteroGlobal + indexInPattern;
				this.numberOfComparations++;
				if (indexInText >= m) break; 
				 char x = text.charAt(indexInText);  
		         char y = pattern.charAt(indexInPattern); 
				 
		         /*if(indexInPattern == (n-1) && x==y){
		        	 cantidad ++;
		        	 punteroGlobal++;
		        	 break;
		         }else if( x != y){
		        	 punteroGlobal++;
		        	// alignedAt = indexInText + 1; 
		        	 break;  
		         }*/
		         this.numberOfComparations++;
		         if( x != y){
		        	 punteroGlobal++;
		        	// alignedAt = indexInText + 1; 
		        	 break;
		         }else{
		        	 this.numberOfComparations++;
		        	 if (indexInPattern == (n-1)){
			        	 cantidad ++;
			        	 punteroGlobal++;
			        	 break; 
		        	 } 
		         }
			}
			
			
			
		}
		
		return cantidad;
	}
	
//	public static void main(String args []){
//		FuerzaBruta m = new FuerzaBruta();
//		System.out.println(m.search("aa", "aaaa"));
//	}
	
}
