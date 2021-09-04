package unisinos.eadgb;

public class AvlTest {

	public static void main(String[] args) {
		Dicionario d = new Dicionario("A");
		ArvoreAVL avl = new ArvoreAVL();
		avl.insert(d);
		d = new Dicionario("A");
		avl.insert(d);
		d = new Dicionario("B");
		avl.insert(d);
		d = new Dicionario("E");
		avl.insert(d);
		d = new Dicionario("C");
		avl.insert(d);
		d = new Dicionario("D");
		avl.insert(d);
		d = new Dicionario("F");
		avl.insert(d);
		d = new Dicionario("G");
		avl.insert(d);
		d = new Dicionario("Z");
		avl.insert(d);
		avl.percorreAVL();
		avl.searchToExclude("Z");
		avl.percorreAVL();
		avl.searchToExclude("B");
		avl.percorreAVL();
		Dicionario s = avl.search("C");
	}

}
