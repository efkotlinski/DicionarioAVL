package unisinos.eadgb;

public class Node {
	
	private Dicionario key;
	private Node father;
	private Node leftSon;
	private Node rightSon;
	private int balanceFactor;

	public Node (Dicionario key){
		this.key = key;
		father = null;
		leftSon = null;
		rightSon = null;
		balanceFactor = 0;
	}
	public Dicionario getKey() {return key;}
	
	public void setKey(Dicionario key){this.key = key;}
	
	public Node getFather() {return father;}
	
	public void setFather(Node father){this.father = father;}
	
	public Node getLeftSon() {return leftSon;}
	
	public void setLeftSon(Node leftSon){this.leftSon = leftSon;}
	
	public Node getRightSon() {return rightSon;}
	
	public void setRightSon(Node rightSon){this.rightSon = rightSon;}
	
	public int getBalanceFactor () {return balanceFactor;}
	
	public void setBalanceFactor(int balanceFactor) { this.balanceFactor = balanceFactor;}
}
