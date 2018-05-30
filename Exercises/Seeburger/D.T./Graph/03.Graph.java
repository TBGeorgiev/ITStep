package com.seeburger.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Queue;

public class Graph {

	private HashMap<String, Node> nodeLookup = new HashMap<String, Node>();
	
	public HashMap<String, Node> getNodes() {
		return this.nodeLookup;
	}

	private Node getNode(String id) {
		Node node = new Node(id);
		nodeLookup.putIfAbsent(id, node);
		return nodeLookup.get(id);
	}

	public void addEdge(String source, String destination) {
		Node sNode = getNode(source);
		Node dNode = getNode(destination);
		dNode.addAdjacentToNode(sNode);
		sNode.addAdjacentToNode(dNode);
	}
	
	public void bredthFirstSearch(String source, String destination) {
		LinkedHashSet<String> visited = new LinkedHashSet<String>();
		
		Queue<String> queue = new LinkedList<String>();
		visited.add(source);
		queue.add(source);
		
		while (queue.size() != 0) {
			source = queue.poll();
			System.out.println(source + " ");
			if (source == destination) {
				break;
			}
			
			Iterator<Node> iterator = nodeLookup.get(source).getAdjacent().listIterator();
			while (iterator.hasNext()) {
				String nString = iterator.next().getId();
				if (!visited.contains(nString)) {
					visited.add(nString);
					queue.add(nString);
				}
			}		
		}		
	}

	public boolean hasPathDFS(String source, String destination) {
		Node sNode = getNode(source);
		Node dNode = getNode(destination);
		LinkedHashSet<String> visited = new LinkedHashSet<String>();
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

	private boolean hasPathDFS(Node sNode, Node dNode, HashSet<String> visited) {
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
