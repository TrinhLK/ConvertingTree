package treenodes;

import java.util.ArrayList;

public class BTNode {
	private String data; 
	private String name;
	private String inforNode;
	
	BTNode leftNode, rightNode; 
	BTNode parent;
	
	public BTNode(TNode<String> mulTree) {
		if (mulTree != null) {
			
			this.data = mulTree.getData();
			this.name = mulTree.getName();
			String temp = mulTree.getInfor();
			temp = temp.replace("<", "");
			temp = temp.replace(">", "");
			this.inforNode = temp;
			
			if (mulTree.getChildren().size() > 0) {
				this.leftNode = new BTNode(mulTree.getFirstChild());
				//System.out.println(this.data + "\tl" + this.leftNode.data);				
			}else {
				//System.out.println("HERE:\t" + mulTree.parent.getNextChild().getInfor());
				TNode<String> tempLeft = mulTree.parent.getNextChild();
				if (tempLeft != null) {
					System.out.println("HERE");
					this.leftNode = new BTNode(tempLeft);
					//System.out.println(this.data + "\t1l" + this.leftNode.data);
				}
			}
			
			if (mulTree.parent != null) {

				for (int i = mulTree.parent.getInterator() ; i < mulTree.parent.getChildren().size()-1 ; i++) {
					TNode<String> tempRight = mulTree.parent.getNextChild();
					if (tempRight != null) {
						this.rightNode = new BTNode(tempRight);
						//System.out.println(this.data + "\tr" + this.rightNode.data);
					}	
				}
			}
		}
	}
	
	public BTNode(TNode<String> mulTree, int type) {
		if (mulTree != null) {
			this.leftNode = new BTNode(mulTree.getInfor());
			this.rightNode = new BTNode();
			if (mulTree.getChildren().size() > 0) {
				this.rightNode.leftNode = new BTNode(mulTree.getFirstChild(), type);
			}else {
				TNode<String> next = mulTree.parent.getNextChild();
				if (next != null) {
					this.rightNode.leftNode = new BTNode(next, type);
				}
			}
		}
		
		if (mulTree.parent != null) {

			for (int i = mulTree.parent.getInterator() ; i < mulTree.parent.getChildren().size()-1 ; i++) {
				TNode<String> tempRight = mulTree.parent.getNextChild();
				if (tempRight != null) {
					this.rightNode.rightNode = new BTNode(tempRight, type);
					//System.out.println(this.data + "\tr" + this.rightNode.data);
				}	
			}
		}
	}
	
	public BTNode(ArrayList<String> sequence, int index) {
		if (index < sequence.size()-1) {
			//System.out.println(index);
			this.leftNode = new BTNode(sequence.get(index));
			index = index + 1;
			//System.out.println(index);
			this.rightNode = new BTNode(sequence, index);
		} else if (index == sequence.size()-1){
			System.out.println("Right:\t" + sequence.get(index));
			this.inforNode = sequence.get(index);
		}
	}
	
	public BTNode() 
    { 
    	this.name = null;
        this.data = null;
        this.inforNode = null; 
        leftNode = rightNode = null; 
    } 
	
    public BTNode(String name, String data) 
    { 
    	this.name = name;
        this.data = data; 
        leftNode = rightNode = null; 
    } 
    
    public BTNode(String infor) 
    { 
    	this.inforNode = infor;
        leftNode = rightNode = null; 
    } 
    
    
    public String printXml(String rs) {
//		//if (this.data != null) 
//    	if (this.inforNode != null) {
//    		rs += "<cons>" + "<cons><tok>" + this.inforNode + "</tok></cons>";
//    		//System.out.println("<cons>");
//    		//System.out.println("<cons>\n<tok>" + this.inforNode + "</tok>\n</cons>");
//    	}
//    	else {
//    		rs += "<cons>";
//    	}
//    	//System.out.println("<cons>");
//		//System.out.println("<cons>\n<tok>" + this.inforNode + "</tok>\n</cons>");
//    	if (this.leftNode != null)
//			rs = this.leftNode.printXml(rs);
//		if (this.rightNode != null)
//			rs = this.rightNode.printXml(rs);
//		
//		
//		rs += "</cons>";
//		//System.out.println("</cons>");
//		
    	rs += "<cons>";
    	if (this.leftNode != null) {
    		rs += "<cons><tok>" + this.leftNode.inforNode + "</tok></cons> ";
    	}
    	if (this.rightNode != null) {
    		if (this.rightNode.inforNode != null) {
    			rs += "<cons><tok>" + this.rightNode.inforNode + "</tok></cons> ";
    		}else {
    			rs = this.rightNode.printXml(rs);
    		}
    	}
    	rs += "</cons>";
		return rs;
	}
    
    public String getSequence(String rs) {
    	if (this.inforNode != null)
    		rs += this.inforNode + " ";
    	else{
    		rs += "";
    	}
    	if (this.leftNode != null)
			rs = this.leftNode.getSequence(rs);
		if (this.rightNode != null)
			rs = this.rightNode.getSequence(rs);
    	return rs;
    }
    
    public void printTree(String indent) {
		//if (this.data != null)
    	if (this.inforNode != null)
    		System.out.println(indent+ this.inforNode);
		if (this.leftNode != null)
			this.leftNode.printTree("  " + indent);
		if (this.rightNode != null)
			this.rightNode.printTree("  " + indent);
		
	}
    
    // Traverses tree in level order 
    public void traverseTree(BTNode root) 
    { 
        if(root == null) 
            return; 
        while(root != null) 
        { 
            System.out.print(root.data + " " + name + " "); 
            if(root.rightNode != null) 
                traverseTree(root.rightNode); 
            root = root.leftNode; 
        } 
    } 
    
    
    public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInforNode() {
		return inforNode;
	}

	public void setInforNode(String inforNode) {
		this.inforNode = inforNode;
	}
    
    
}
