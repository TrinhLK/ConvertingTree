package treenodes;

import java.util.*;

public class TNode<T> {
	 
	private T data = null;
	private T name = null;
	private T infor = null;
	 
	private List<TNode<T>> children = new ArrayList<>();
	protected TNode<T> parent = null;
	private int iterator = 0;
	 
	public TNode(T name) {
		this.name = name;
	}
	 
	public TNode(T name, T data) {
		this.data = data;
		this.name = name;
	}
	
	public TNode<T> getFirstChild() {
		if (children.size() > 0) {
			iterator = 0;
			return children.get(iterator);
		}
		return null;
	}
	
	public int getInterator() {
		return iterator;
	}

	public void setInterator(int interator) {
		this.iterator = interator;
	}

	public TNode<T> getNextChild() {
		if (iterator < children.size()-1) {
			iterator++;
			return children.get(iterator);
		}
		return null;
	}
	
	public TNode<T> getKChildNode(int k) {
		iterator = k;
		if (iterator < children.size())
			return children.get(k);
		return null;
	}
	
	public TNode<T> addChild(TNode<T> child) {
		child.setParent(this);
		this.children.add(child);
		return child;
	}
	 
	public void addChildren(List<TNode<T>> children) {
		children.forEach(each -> each.setParent(this));
		this.children.addAll(children);
	}
	
	public void printTree(String indent) {
		
		if (children.size() > 0) {
			this.infor = this.getName();
			System.out.println(indent + infor);
			for (int i = 0 ; i < children.size() ; i++) {
				children.get(i).printTree("  " + indent);
			}
		}else {
			this.infor = this.getData();
			System.out.println(indent + infor);
		}
		
	}
	
	public void assignInfor() {
		
		if (children.size() > 0) {
			this.infor = this.getName();
			//System.out.println(indent + infor); 
			for (int i = 0 ; i < children.size() ; i++) {
				children.get(i).assignInfor();
			}
		}else {
			this.infor = this.getData();
		}
		
	}
	 
	public T getInfor() {
		return infor;
	}

	public void setInfor(T infor) {
		this.infor = infor;
	}

	/**
	 * Getters & Setters
	 * --------------------------------------------------------
	 * */
	public List<TNode<T>> getChildren() {
		return children;
	}
	 
	public T getData() {
		return data;
	}
	 
	public void setData(T data) {
		this.data = data;
	}
	 
	private void setParent(TNode<T> parent) {
		this.parent = parent;
	}
	 
	public TNode<T> getParent() {
		return parent;
	}
	 
	public T getName() {
		return name;
	}
	
	public void setName(T name) {
		this.name = name;
	}
	 
}
