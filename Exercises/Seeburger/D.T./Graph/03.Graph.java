package com.seeburger.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;

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
		LinkedHashSet<Integer> visited = new LinkedHashSet<Integer>();
		if (hasPathDFS(sNode, dNode, visited)) {
			Object[] intArray = visited.toArray();
			for (int i = 0; i < intArray.length; i++)
			{
				System.out.print(intArray[i] + " ");
			}
			System.out.println();
			return true;
		} else {
			return false;
		}
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
