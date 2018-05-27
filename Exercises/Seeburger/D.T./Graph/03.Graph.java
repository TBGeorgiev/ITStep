package com.seeburger.graph2;

import java.util.HashMap;
import java.util.HashSet;

public class Graph {

	private HashMap<Integer, Node> nodeLookup = new HashMap<Integer, Node>();
	
	public HashMap<Integer, Node> getNodes() {
		return this.nodeLookup;
	}

	private Node getNode(int id) {
		Node node = new Node(id);
		nodeLookup.putIfAbsent(id, node);
		return nodeLookup.get(id);
	}

	public void addEdge(int source, int destination) {
		Node sNode = getNode(source);
		Node dNode = getNode(destination);
		sNode.addAdjacentToNode(dNode);
	}

	public boolean hasPathDFS(int source, int destination) {
		Node sNode = getNode(source);
		Node dNode = getNode(destination);
		HashSet<Integer> visited = new HashSet<Integer>();
		return hasPathDFS(sNode, dNode, visited);
	}

	private boolean hasPathDFS(Node sNode, Node dNode, HashSet<Integer> visited) {
		if (visited.contains(sNode.getId())) {
			return false;
		}
		visited.add(sNode.getId());
		if (sNode == dNode) {
			return true;
		}
		for (Node child : sNode.getAdjacent()) {
			if (hasPathDFS(child, dNode, visited)) {
				return true;
			}
		}
		return false;
	}
}
