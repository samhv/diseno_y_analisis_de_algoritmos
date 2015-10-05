package experimentos;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import algoritmos.TextSearcher;

public class Experimento {

	TextSearcher algoritm;
	String text;
	PrintWriter output_file;
	String file_path;
	Integer pattern;
	Integer error_permitido;
	
	public Experimento(TextSearcher algoritm, String text, PrintWriter output_file, String file_path, Integer pattern, Integer error_permitido) {
		this.algoritm=algoritm;
		this.text=text;
		this.output_file=output_file;
		this.file_path=file_path;
		this.pattern=pattern;
		this.error_permitido=error_permitido;
	}	
	
	public ArrayList<Integer> muestras_comparaciones;
	public ArrayList<Integer> muestras_tiempo;
	
	public void go(){	
		muestras_tiempo = new ArrayList<Integer>();
		muestras_comparaciones = new ArrayList<Integer>();
		Patterns the_patterns;
		Integer matchs;
		String to_print;
		float prom;
		float sigma;
		for ( int l = Patterns.l_i; l <= Patterns.l_f; l++){
			muestras_tiempo = new ArrayList<Integer>();
			muestras_comparaciones = new ArrayList<Integer>();
			while(calcular_error(muestras_comparaciones)>error_permitido){
				if(pattern==1){
					the_patterns=new TextPatterns(100,text);					
				}
				else{
					the_patterns=new BinaryPatterns(100);
				}
				String[] patterns = the_patterns.getPatternsFromLarge(l);
				for ( String pattern : patterns){
					matchs = algoritm.doSearch(pattern, text);
					muestras_tiempo.add((int) algoritm.getTimeOfExecutionInNano());
					muestras_comparaciones.add(matchs);
				}
			}	
			while(calcular_error(muestras_tiempo)>error_permitido){
				if(pattern==1){
					the_patterns=new TextPatterns(100,text);					
				}
				else{
					the_patterns=new BinaryPatterns(100);
				}
				String[] patterns = the_patterns.getPatternsFromLarge(l);
				for ( String pattern : patterns){
					matchs = algoritm.doSearch(pattern, text);
					muestras_comparaciones.add(matchs);
				}
			}
			prom = calcular_promedio(muestras_tiempo);
			sigma = calcular_varianza_estimada(muestras_tiempo);
			to_print=  "t:\t" + (prom-2*sigma) + "\t" + (prom+2*sigma) + "\t" + 400*prom/sigma + "\t" + l + "\t" + file_path;
			output_file.println(to_print);
			prom = calcular_promedio(muestras_comparaciones);
			sigma = calcular_varianza_estimada(muestras_comparaciones);
			to_print=  "c:\t" + (prom-2*sigma) + "\t" + (prom+2*sigma) + "\t" + 400*prom/sigma + "\t" + l + "\t" + file_path;
			output_file.println(to_print);
		}
		
	}
	
	public Integer calcular_error(ArrayList<Integer> muestra){
		return (int) (400*calcular_varianza_estimada(muestra)/calcular_promedio(muestra));
	}
	
	public float calcular_varianza_estimada(ArrayList<Integer> muestra){
		return 0;
	}
	
	public float calcular_promedio(ArrayList<Integer> muestra){
		return 0;
	}

}
