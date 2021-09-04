package unisinos.eadgb;

import java.util.LinkedList;

public class ArvoreAVL {

	private Node root;
	
	public int high(Node current) {
		if (current == null)
			return -1;
		else if (current.getLeftSon() == null && current.getRightSon() == null)
			return 0;
		
		else if (current.getRightSon() == null) {
				return 1 + high(current.getLeftSon());
		}
		else if (current.getLeftSon() == null) {
				return 1 + high(current.getRightSon()); 
		}
		else 
			return 1 + Math.max(high(current.getLeftSon()), high(current.getRightSon()));
	}

	public void calcBalanceFactor(Node current) {
		if (current != null);
		current.setBalanceFactor(high(current.getLeftSon()) - high(current.getRightSon()));
	}

	public void rotacaoSimplesDireita(Node current) {
		
		current.getLeftSon().setFather(current.getFather());
		if (current.getFather() == null)
			root = current.getLeftSon();
		else {
			if (current.getKey().palavra.equalsIgnoreCase(current.getFather().getLeftSon().getKey().palavra))
				current.getFather().setLeftSon(current.getLeftSon());
			else
				current.getFather().setRightSon(current.getLeftSon());
		}
		current.setFather(current.getLeftSon());
		
		if (current.getLeftSon().getRightSon() != null) {
			current.getLeftSon().getRightSon().setFather(current);
			current.setLeftSon(current.getLeftSon().getRightSon());
			current.getFather().setRightSon(current);
		}
		else {
			current.getLeftSon().setRightSon(current);
			current.setLeftSon(null);
		}
		calcBalanceFactor(current);
	}
	
	public void rotacaoSimplesEsquerda(Node current) {
		
		current.getRightSon().setFather(current.getFather());
		if (current.getFather() == null)
			root = current.getRightSon();
		else {
			if (current.getKey().palavra.equalsIgnoreCase(current.getFather().getLeftSon().getKey().palavra))
				current.getFather().setLeftSon(current.getRightSon());
			else
				current.getFather().setRightSon(current.getRightSon());
		}
		current.setFather(current.getRightSon());
		
		if (current.getRightSon().getLeftSon() != null) {
			current.getRightSon().getLeftSon().setFather(current);
			current.setRightSon(current.getRightSon().getLeftSon());
			current.getFather().setLeftSon(current);
		}
		else {
			current.getRightSon().setLeftSon(current);
			current.setRightSon(null);
		}
		calcBalanceFactor(current);
	}
	
	public void rotacaoDuplaDireita(Node current) {
		rotacaoSimplesEsquerda(current.getLeftSon());
		rotacaoSimplesDireita(current);
		
	}
	
	public void rotacaoDuplaEsquerda(Node current) {
		rotacaoSimplesDireita(current.getRightSon());
		rotacaoSimplesEsquerda(current);
		
	}
	
	public void insert(Dicionario d) {
		Node in = new Node (d);
		if (root == null)
			root = in;
		else
			insertSearch(in, root);
		}
	
	public void insertSearch(Node in, Node current) {
		if(current == null)
			current = in;
		else if (in.getKey().palavra.compareToIgnoreCase(current.getKey().palavra)== 0)
			in =null;
		else if (in.getKey().palavra.compareToIgnoreCase(current.getKey().palavra)< 0) {
			if(current.getLeftSon() != null)
			insertSearch(in, current.getLeftSon());
			else {
				current.setLeftSon(in);
				in.setFather(current);
			}
	}
		else {
			if(current.getRightSon() != null)
			insertSearch(in, current.getRightSon());
			else {
				current.setRightSon(in);
				in.setFather(current);
			}
		}
		balanceAVL(current);	
	}
	
	public void balanceAVL(Node current) {
		if(current !=null) {
			calcBalanceFactor(current);
		if(current.getBalanceFactor() < -1) {
		 if(current.getRightSon().getBalanceFactor() < 0) { 
			 rotacaoSimplesEsquerda(current);
			calcBalanceFactor(current.getFather());
		 }
		 else {
			 rotacaoDuplaEsquerda(current);
			 calcBalanceFactor(current.getFather());
		 }
		}
		 else if(current.getBalanceFactor() > 1) {
			 if(current.getLeftSon().getBalanceFactor() > -1) {
				 rotacaoSimplesDireita(current);
				 calcBalanceFactor(current.getFather());
			 }
			 else {
				 rotacaoDuplaDireita(current);
				 calcBalanceFactor(current.getFather());
			 }
		 }
		 
	 }
		
  }
	
	public void searchToExclude(String out) {
		searchToExcludeR(out, root);
}
	
	private void searchToExcludeR(String out, Node current) {
		if(current != null) {
			Node aux = current.getFather();
			if(out.compareToIgnoreCase(current.getKey().palavra) <0)
				searchToExcludeR(out, current.getLeftSon());
			else if (out.compareToIgnoreCase(current.getKey().palavra) > 0)
				searchToExcludeR(out, current.getRightSon());
			else if (out.compareToIgnoreCase(current.getKey().palavra) == 0) {
				exclude(current);
				
		}
			else
				System.out.println("Valor não encontrado!");
			balanceAVL(aux);
		}		
	}
	
	private void exclude(Node current) {
		if (current.getLeftSon() == null && current.getRightSon() == null) {
			if (current.getFather().getLeftSon() != null && current.getFather().getLeftSon().getKey().equals(current.getKey())){
				current.getFather().setLeftSon(null);
				balanceAVL(current.getFather());
				current.setFather(null);
			}
			else {
				current.getFather().setRightSon(null);
				balanceAVL(current.getFather());
				current.setFather(null);
			}
		}
		else if (current.getLeftSon() == null) {
			if (current.getFather().getLeftSon() != null && current.getFather().getLeftSon().getKey().equals(current.getKey())){
				current.getFather().setLeftSon(current.getRightSon());
				current.getRightSon().setFather(current.getFather()); 
				current.setRightSon(null);
				balanceAVL(current.getFather());
				current.setFather(null);
			}
			else {
				current.getFather().setRightSon(current.getRightSon());
				current.getRightSon().setFather(current.getFather()); 
				current.setRightSon(null);
				balanceAVL(current.getFather());
				current.setFather(null);
			}
		}
		else if (current.getRightSon() == null) {
			if (current.getFather().getLeftSon().getKey().equals(current.getKey())){
				current.getFather().setLeftSon(current.getLeftSon());
				current.getLeftSon().setFather(current.getFather()); 
				current.setLeftSon(null);
				balanceAVL(current.getFather());
				current.setFather(null);
			}
			else {
				current.getFather().setRightSon(current.getLeftSon());
				current.getLeftSon().setFather(current.getFather()); 
				current.setLeftSon(null);
				balanceAVL(current.getFather());
				current.setFather(null);
			}
		}
		else {
			if(current.getLeftSon().getRightSon() != null) {
				Node aux = new Node(current.getLeftSon().getRightSon().getKey());
				current.setKey(aux.getKey());
				exclude(current.getLeftSon().getRightSon());
				balanceAVL(current);
			}
			else if(current.getRightSon().getLeftSon() != null) {
				Node aux = new Node(current.getRightSon().getLeftSon().getKey());
				aux.setKey(current.getRightSon().getLeftSon().getKey());
				current.setKey(aux.getKey());
				exclude(current.getRightSon().getLeftSon());
				balanceAVL(current);
			}
			else {
				Node aux = new Node(current.getLeftSon().getKey());
				current.setKey(aux.getKey());
				exclude(current.getLeftSon());
				balanceAVL(current);
			}
			
		}
	}
	
	public void toTheTop(Node current) {
		if (current != null) {
			balanceAVL(current);
			toTheTop(current.getFather());
		}
	}
	
	public Dicionario search (String s) {
		if(s != null && root != null)
			return searchR(s, root);
		else return null;
	}
	
	private Dicionario searchR (String s, Node current) {
		if (current != null && current.getKey().palavra.compareToIgnoreCase(s) == 0)
			return current.getKey();
		else if (current.getLeftSon() != null && s.compareToIgnoreCase(current.getKey().palavra)< 0)
			return searchR (s, current.getLeftSon());
		else if (current.getRightSon() != null && s.compareToIgnoreCase(current.getKey().palavra)> 0)
			return searchR (s, current.getRightSon());
		else 
			return null;
					
	}
	
	public void percorreAVL() {
		if (root != null)
			percorreAVLR(root);
	}
	
	private void percorreAVLR(Node r) {
		if (r.getLeftSon() != null) 
			percorreAVLR (r.getLeftSon());
		System.out.println(r.getKey().palavra);
		
		if (r.getRightSon() != null)
			percorreAVLR(r.getRightSon());
	}
	
	public void percorreAVLBalance() {
		if (root != null)
			percorreAVLBalanceR(root);
	}
	
	private void percorreAVLBalanceR(Node r) {
		if (r.getLeftSon() != null)  
			percorreAVLBalanceR (r.getLeftSon());
		
		balanceAVL(r);
		
		if (r.getRightSon() != null)
			percorreAVLBalanceR(r.getRightSon());
	}
	
	public LinkedList<String> returnListAVL() {
		if (root != null) {
			LinkedList<String> l = new LinkedList<String>();
			return returnListAVLR(root, l);
		}
		else 
			return null;
	}
	
	private LinkedList<String> returnListAVLR(Node r, LinkedList<String> l) {

		
		if (r.getLeftSon() != null) 
			l = returnListAVLR(r.getLeftSon(), l);
		
		l.addLast(r.getKey().palavra);
		
		
		if (r.getRightSon() != null)
			l = returnListAVLR(r.getRightSon(), l);
		return l;
  }
}

