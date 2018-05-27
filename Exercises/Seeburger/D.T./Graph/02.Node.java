package com.seeburger.graph2;

import java.util.LinkedList;

public class Node {
	private int id;
	private LinkedList<Node> adjacent = new LinkedList<Node>();
	
	
	public int getId() {
		return this.id;
	}
	
	public LinkedList<Node> getAdjacent() {
		return this.adjacent;
	}

	public Node(int id) {
		this.id = id;
	}
	
	public void addAdjacentToNode(Node node) {
		this.adjacent.add(node);
	}
	
}
