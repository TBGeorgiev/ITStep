package com.seeburger.graph;
import java.util.LinkedList;

public class Node {
//	private int id;
	private String id;
	private LinkedList<Node> adjacent = new LinkedList<Node>();
	
	
	public String getId() {
		return this.id;
	}
	
	public LinkedList<Node> getAdjacent() {
		return this.adjacent;
	}

	public Node(String id) {
		this.id = id;
	}
	
	public void addAdjacentToNode(Node node) {
		this.adjacent.add(node);
	}
	
}
