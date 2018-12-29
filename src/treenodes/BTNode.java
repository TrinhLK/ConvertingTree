package treenodes;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class BTNode {
	private String data; 
	private String name;
	private String inforNode;
	
	BTNode leftNode, rightNode; 
	
	
	public BTNode(TNode<String> mulTree) {
		if (mulTree != null) {
			
			this.data = mulTree.getData();
			this.name = mulTree.getName();
			this.inforNode = mulTree.getInfor();
			if (mulTree.getChildren().size() > 0) {
				this.leftNode = new BTNode(mulTree.getFirstChild());
				//System.out.println(this.data + "\tl" + this.leftNode.data);				
			}else {
				TNode<String> tempLeft = mulTree.parent.getNextChild();
				if (tempLeft != null) {
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
	
    public BTNode(String name, String data) 
    { 
    	this.name = name;
        this.data = data; 
        leftNode = rightNode = null; 
    } 
    
    public String printXml(String rs) {
		//if (this.data != null) 
    	rs += "<cons>" + "<cons><tok>" + this.inforNode + "</tok></cons>";
    	//System.out.println("<cons>");
		//System.out.println("<cons>\n<tok>" + this.inforNode + "</tok>\n</cons>");
    	if (this.leftNode != null)
			rs = this.leftNode.printXml(rs);
		if (this.rightNode != null)
			rs = this.rightNode.printXml(rs);
		
		
		rs += "</cons>";
		//System.out.println("</cons>");
		
		return rs;
	}
    
    public String getSequence(String rs) {
    	rs += this.inforNode + " ";
    	if (this.leftNode != null)
			rs = this.leftNode.getSequence(rs);
		if (this.rightNode != null)
			rs = this.rightNode.getSequence(rs);
    	return rs;
    }
    
    public void printTree(String indent) {
		//if (this.data != null)
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

	
    public static void main (String[] args) {
    	
    }
    
    
}
