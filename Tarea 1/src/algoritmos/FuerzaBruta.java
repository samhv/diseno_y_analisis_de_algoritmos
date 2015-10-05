package algoritmos;

public class FuerzaBruta extends TextSearcher {
	
	public FuerzaBruta(){
		
	}

	@Override
	protected int search(String pattern, String text) {
		
		int cantidad = 0;
		int m = text.length();  
        int n = pattern.length();  
        
        int alignedAt = 0;
        
		while(alignedAt + (n - 1) < m){
			
			for (int indexInPattern = 0; indexInPattern < n ; indexInPattern++) { 
				int indexInText = alignedAt + indexInPattern;  
				 if (indexInText >= m) break; 
				 
				 char x = text.charAt(indexInText);  
		         char y = pattern.charAt(indexInPattern); 
		         
		         if(indexInPattern == (n-1)){
		        	 cantidad ++;
		        	 alignedAt++;
		         }else if( x != y){
		        	 alignedAt = indexInText + 1; 
		        	 break;  
		         }
			}
			
		}
		
		return cantidad;
	}
	
	
}