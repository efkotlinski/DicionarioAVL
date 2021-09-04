package unisinos.eadgb;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class Teste {

	public static void main(String[] args) throws IOException  {
		Scanner ler = new Scanner(System.in);
		Tradutor t=new Tradutor();
		try{			
			t.carregaDicionario("dicionario.dat");
		}catch(IOException e){
			System.out.println("arquivo inválido ou não encontrado!");
		}
		
		int test=0;
		while(test==0) {		 
		    String nome;	
		    String nomeEntrada="";
		    System.out.printf("Informe uma palavra ou 0 para sair:\n");
		    nome = ler.nextLine();
		    if(nome.compareToIgnoreCase("0")==0){
		    	test=1;
		    }else{	
			    nomeEntrada=nome;
			    try{
				    LinkedList<String> teste=t.traduzPalavra(nome);
				    for (int i = 0; i < teste.size(); i++) {
						System.out.println("Definição "+(i+1)+": "+teste.get(i));
				}
			    }catch(NullPointerException e){
					int cont=0;
					
					LinkedList<String> definicoes=new LinkedList<>();
					System.out.println("Palavra não encontrada no dicionário, digite uma definição da palavra: ");
					nome = ler.nextLine();
					
					definicoes.add(nome);
					while(cont==0){
						System.out.println("Digite outra definição ou 0 para retornar ao menu anterior: ");
						nome = ler.nextLine();
						if(nome.compareTo("0")==0){
							cont=1;
						}
						else{
							definicoes.add(nome);
							
						}
						
					}
					t.insereTraducao(nomeEntrada, definicoes);
					
				}catch(IOException e){
					System.out.println("arquivo inválido ou não encontrado!");
			}	
		}
		t.salvaDicionario("dicionario.dat");
		}
	}

}
