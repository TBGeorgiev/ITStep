package com.seeburger.graph2;

import java.util.HashMap;
import java.util.Map;

public class Main {

	public static void main(String[] args) {
		Graph graph = new Graph();
		
		graph.addEdge(1, 3);
		graph.addEdge(1, 6);
		graph.addEdge(3, 6);
		graph.addEdge(6, 1);
		
		HashMap<Integer, Node> nodes = graph.getNodes();
		
		for (Map.Entry<Integer, Node> map : nodes.entrySet()) {
			System.out.println("Parent:");
			System.out.println(map.getKey());
			System.out.println("Children:");
			for (int i = 0; i < map.getValue().getAdjacent().size(); i++) {
				System.out.println(map.getValue().getAdjacent().get(i).getId());
			}
		}
		
		System.out.println();
		
		
		System.out.println(graph.hasPathDFS(3, 1));

	}

}
