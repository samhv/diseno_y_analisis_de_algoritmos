package experimentos;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class Main2 {

	public static void main(String[] args) throws IOException {

		    String name_file = "english";
			int size= 1048576*2;
			FileReader fr = new FileReader(name_file);
			BufferedReader br = new BufferedReader(fr);
			char c;
			PrintWriter p = new PrintWriter(name_file + "_short", "UTF-8");
			
			for (int i=0; i < size; i++) {
				c = (char) br.read();	
				p.print(c);
			}	
			p.close();
		
	}

}