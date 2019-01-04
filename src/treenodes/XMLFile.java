package treenodes;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.*;

import org.w3c.dom.*;

public class XMLFile {

	private ArrayList<TNode<String>> listMethods = new ArrayList<TNode<String>>();
	private ArrayList<BTNode> listBiMethods = new ArrayList<BTNode>();
	private ArrayList<String> listTreeMethodsString = new ArrayList<String>();
	private ArrayList<String> listMethodsString = new ArrayList<String>();
	private ArrayList<ArrayList<String>> listMethodsSequenceString = new ArrayList<ArrayList<String>>();
	
	
	public static void main(String[] args) {
		XMLFile file = new XMLFile();
		
		file.readXMLFile("MethodBody-0-1.xml");
		//file.readXMLFile("inputAST.xml");
		//Chuyen cay AST thanh sequence theo pre-order va luu vao arraylist
		for (int i=0 ; i<file.listMethods.size() ; i++) {
			//file.listBiMethods.add(new BTNode(file.listMethods.get(i), 1));
			ArrayList<String> temp = new ArrayList<String>();
			temp = file.listMethods.get(i).getSentence(temp);
			file.listMethodsSequenceString.add(temp);
		}
		
		//doc tung sequence, cho vao arraylist 2-tree
		for (int i=0 ; i<file.listMethodsSequenceString.size() ; i++) {
			BTNode temp = new BTNode(file.listMethodsSequenceString.get(i), 0);
			file.listBiMethods.add(temp);
		}
		//file.listBiMethods.add(new BTNode(file.listMethods.get(1), 1));
		System.out.println("PRINTING Gen TREE");
		file.listMethods.get(1).printTree("   ");
		
		System.out.println("PRINTING Gen SEQUENCE");
		System.out.println(file.listMethodsSequenceString.get(1));
		
		System.out.println("PRINTING Binary TREE");
		file.listBiMethods.get(1).printTree("   ");
		
		String rs = "";
		for (int i = 0 ; i < file.listBiMethods.size() ; i++) {
			//rs = "<sentence parse_status=\"success\">";
			String content = "<sentence parse_status=\"success\">";
			content += file.listBiMethods.get(i).printXml("");
			content += ".</sentence>";
			file.listTreeMethodsString.add(content);
			//rs = "";
		}
		
		rs = "";
		for (int i = 0 ; i < file.listBiMethods.size() ; i++) {
			rs += file.listBiMethods.get(i).getSequence(rs);
			rs += ".";
			file.listMethodsString.add(rs);
			rs = "";
		}

		System.out.println("PRINTING body TREE enju file");
		//for (int i=0 ; i<file.listTreeMethodsString.size() ; i++) {
		//	System.out.println(file.listTreeMethodsString.get(i));
		//}
		System.out.println(file.listTreeMethodsString.get(1));
		file.writeFile("train.enju", file.listTreeMethodsString);
		
		System.out.println("PRINTING body Sequence en file");
		//for (int i=0 ; i<file.listMethodsString.size() ; i++) {
		//	System.out.println(file.listMethodsString.get(i));
		//}
		System.out.println(file.listMethodsString.get(1));
		file.writeFile("train.en", file.listMethodsString);
		
	}
	
	
	/**
	 * write File
	 * */
	public void writeFile(String fileName, ArrayList<String> listInput) {
		BufferedWriter bw = null;
		FileWriter fw = null;

		try {

			System.out.println("Starting to write into file\n" + fileName);
			//String content = "This is the content to write into file\n";

			fw = new FileWriter(fileName);
			bw = new BufferedWriter(fw);
			
			for (String s : listInput) {
				bw.write(s + "\n");
			}
			//bw.write(content);

			System.out.println("Done");

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (bw != null)
					bw.close();

				if (fw != null)
					fw.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}

	}
	
	public void readXMLFile(String fileName) {
		
		try {
			File xmlFile = new File(fileName);
			DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance()
                    .newDocumentBuilder();

			Document doc = dBuilder.parse(xmlFile);
			
			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
			
			//generalTree = new TNode<String>(doc.getDocumentElement().getNodeName());
			NodeList nList = doc.getElementsByTagName("MethodDeclaration");
			System.out.println(nList.getLength());
			for (int i=0 ; i<nList.getLength() ; i++) {
				Node curNode = nList.item(i);
				TNode<String> aTree = new TNode<String>("MethodDeclaration");
				if (curNode.hasChildNodes()) {
					createTree(aTree, curNode.getChildNodes());
					System.out.println("\n\n");
				}
				aTree.assignInfor();
				System.out.println("Number of methods" + i);
				listMethods.add(aTree);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void createTree(TNode<String> curNode, NodeList nodeList) {
		
	    for (int count = 0; count < nodeList.getLength(); count++) {
			Node tempNode = nodeList.item(count);
			
			
			TNode<String> tempTNode = null;
			if (!tempNode.getNodeName().equals("#text")) {
				String tempText = tempNode.getTextContent();
				tempText = tempText.replace("\n", "");
				tempText = tempText.replace(" ", "");
				tempTNode = curNode.addChild(new TNode<String>(tempNode.getNodeName(), tempText));
			}
			
			// make sure it's element node.
			if (tempNode.getNodeType() == Node.ELEMENT_NODE) {
				if (tempNode.hasChildNodes()) {
					// loop again if has child nodes
					createTree(tempTNode, tempNode.getChildNodes());
	
				} 	
			}
	    }
    }
	
	
}
