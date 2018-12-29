package multi2binary;

public class BinaryTree {

	private String data;
	private BinaryTree leftNode;
	private BinaryTree rightNode;
	
	public BinaryTree(MultipleTree mulTree) {
		// TODO Auto-generated constructor stub
		if (mulTree != null) {
			
			this.data = mulTree.getData();

			//System.out.println(this.data);
			if (mulTree.getListNode().size() > 0) {
				this.leftNode = new BinaryTree(mulTree.getFirstChild());
				//System.out.println(this.data + "\tl" + this.leftNode.data);				
			}else {
				MultipleTree tempLeft = mulTree.parent.getNextChild();
				if (tempLeft != null) {
					this.leftNode = new BinaryTree(tempLeft);
					//System.out.println(this.data + "\t1l" + this.leftNode.data);
				}
				
			}
			
			if (mulTree.parent != null) {

				for (int i = mulTree.parent.getInterator() ; i < mulTree.parent.getListNode().size()-1 ; i++) {
					MultipleTree tempRight = mulTree.parent.getNextChild();
					if (tempRight != null) {
						this.rightNode = new BinaryTree(tempRight);
						//System.out.println(this.data + "\tr" + this.rightNode.data);
					}
				}
			}
		}
	}
	
	public void printTree(String indent) {
		if (this.data != null)
			System.out.println(indent+ this.data);
		if (this.leftNode != null)
			this.leftNode.printTree("  " + indent);
		if (this.rightNode != null)
			this.rightNode.printTree("  " + indent);
		
	}
	
	public static void main(String[] args) {
//		MultipleTree root = new MultipleTree("A");
//		MultipleTree nB = root.appendChildNode("B");
//		MultipleTree nC = root.appendChildNode("C");
//		MultipleTree nD = root.appendChildNode("D");
//		MultipleTree nE = nB.appendChildNode("E");
//		MultipleTree nF = nB.appendChildNode("F");
//		MultipleTree nG = nB.appendChildNode("G");
//		MultipleTree nH = nD.appendChildNode("H");
//		MultipleTree nI = nF.appendChildNode("I");
//		MultipleTree nJ = nF.appendChildNode("J");
		
		MultipleTree root = new MultipleTree("ForStatement");
		MultipleTree nB = root.appendChildNode("initialization");
		MultipleTree nC = root.appendChildNode("condition");
		MultipleTree nD = root.appendChildNode("increasement");
		MultipleTree nK = root.appendChildNode("body");
		
		MultipleTree nE = nB.appendChildNode("i = 1");
		MultipleTree nF = nB.appendChildNode("j = 0");
		MultipleTree nG = nD.appendChildNode("i++");
		MultipleTree nH = nK.appendChildNode("statements");
		
		System.out.println("Multiple tree: ");
		root.printTree("   ");
		
		System.out.println("Binary tree: ");
		BinaryTree bina = new BinaryTree(root);
		
		System.out.println("Print Binary tree: ");
		bina.printTree("  ");
	}
}
