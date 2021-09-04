package unisinos.eadgb;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.LinkedList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Tradutor {
	
	

	private ArvoreAVL dicionario;
	
	public Tradutor() {
		super();
		dicionario=new ArvoreAVL();
	}
	
	protected void carregaDicionario(String arc) throws IOException{
		BufferedReader buffer = new BufferedReader(new FileReader(arc));
		String linha;
		
		
		while ((linha = buffer.readLine()) != null) {
			
			String[] TableLine;
			TableLine = linha.split("#");
			String chave=TableLine[0];
			
			LinkedList<String> lista=new LinkedList<String>();
			for (int i = 1; i < TableLine.length; i++) {
				
				lista.add(TableLine[i]);
			}
			
			Dicionario d=new Dicionario(chave,lista);
			
			dicionario.insert(d);
			
		}

		buffer.close(); 
		
		
	}
	
	public LinkedList<String> traduzPalavra(String palavra) throws IOException{
		Dicionario d=dicionario.search(palavra);
		LinkedList<String> retorno=d.getDefinicoes();
		return retorno;
		
	}
	public void insereTraducao(String palavra, LinkedList<String> definicoes) throws IOException {
		Dicionario d=new Dicionario(palavra,definicoes);
		dicionario.insert(d);
		
		
	}
	
	public void salvaDicionario(String arq) throws IOException {
		FileWriter escreve = new FileWriter(arq);
		PrintWriter gravarArq = new PrintWriter(escreve);
		
		LinkedList<String> retorno=dicionario.returnListAVL();
	
		
	    for (int i=0; i<retorno.size(); i++) {
	      String form=retorno.get(i);
	      LinkedList<String> def=traduzPalavra(retorno.get(i));
	      for (int j = 0; j < def.size(); j++) {
			form=form+"#"+def.get(j);
	      }
	      form=form+"\n";
	      gravarArq.printf(form);
	      
	    }

	 
	    escreve.close();
	}
}
