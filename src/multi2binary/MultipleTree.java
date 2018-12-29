package multi2binary;

import java.util.ArrayList;

public class MultipleTree {

	private String data;
	MultipleTree parent;
	private ArrayList<MultipleTree> listNode  = new ArrayList<MultipleTree>();
	private int interator = 0;
	//private Iterator<MultipleTree> it;
	
	public MultipleTree(String _data) {
		data = _data;
		parent = null;
		//listNode = new ArrayList<MultipleTree>();
	}
	
	public MultipleTree getFirstChild() {
		if (listNode.size() > 0) {
			interator = 0;
			return listNode.get(interator);
		}
		return null;
	}
	
	public int getInterator() {
		return interator;
	}

	public void setInterator(int interator) {
		this.interator = interator;
	}

	public MultipleTree getNextChild() {
		if (interator < listNode.size()-1) {
			interator++;
			return listNode.get(interator);
		}
		return null;
	}
	
	public MultipleTree getKChildNode(int k) {
		interator = k;
		if (interator < listNode.size())
			return listNode.get(k);
		return null;
	}
	
	public MultipleTree appendChildNode(String data) {
		MultipleTree pNode = new MultipleTree(data);
		pNode.setParent(this);
		listNode.add(pNode);
		return pNode;
	}
	
	public MultipleTree getParent() {
		return parent;
	}

	public void setParent(MultipleTree parent) {
		this.parent = parent;
	}

	public void printTree(String indent) {
//		MultipleTree pNode;
//		int it = 0;
//		
//		System.out.println(indent + data);
//		goToFirstChildNode(it);
//		if (listNode.size() == 0) goToNextChildNode(it);
//		pNode = listNode.get(it);
//		//System.out.println(listNode.get(it).data);
//		while (getChildNode(it, pNode)) {
//			pNode.printTree("   " + indent);
//			goToNextChildNode(it);
//		}
		if (listNode.size() > 0) {
			System.out.println(indent + this.data);
			for (int i = 0 ; i < listNode.size() ; i++) {
				listNode.get(i).printTree("  " + indent);
			}
		}else {
			System.out.println(indent + this.getData());
		}
	}
	/**
	 * Getters and Setters
	 * */
	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public ArrayList<MultipleTree> getListNode() {
		return listNode;
	}

	public void setListNode(ArrayList<MultipleTree> listNode) {
		this.listNode = listNode;
	}

	
	public static void main (String[] args) {
		
		MultipleTree root = new MultipleTree("ForStatement");
		MultipleTree nB = root.appendChildNode("initialization");
		MultipleTree nC = root.appendChildNode("condition");
		MultipleTree nD = root.appendChildNode("increasement");
		MultipleTree nK = root.appendChildNode("body");
		
		MultipleTree nE = nB.appendChildNode("i = 1");
		MultipleTree nF = nB.appendChildNode("j = 0");
		MultipleTree nG = nD.appendChildNode("i++");
		MultipleTree nH = nK.appendChildNode("statements");
		//MultipleTree nI = nF.appendChildNode("I");
		//MultipleTree nJ = nF.appendChildNode("J");
		
		System.out.println("Multiple tree: ");
		root.printTree("   ");
	}
}
