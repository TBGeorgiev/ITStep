package com.seeburger.graph;

import java.util.HashMap;
import java.util.Map;

public class Main {

	public static void main(String[] args) {
		Graph graph = new Graph();
		
		graph.addEdge("Sofia", "Plovdiv");
		graph.addEdge("Sofia", "Pleven");
		graph.addEdge("Sofia", "Blagoevgrad");
		graph.addEdge("Plovdiv", "Stara Zagora");
		graph.addEdge("Plovdiv", "Smolian");
		graph.addEdge("Stara Zagora", "Pleven");
		graph.addEdge("Stara Zagora", "Burgas");
		graph.addEdge("Smolian", "Blagoevgrad");
		graph.addEdge("Smolian", "Kurdzhali");
		graph.addEdge("Kurdzhali", "Stara Zagora");
		graph.addEdge("Kurdzhali", "Burgas");
		
		
		HashMap<String, Node> nodes = graph.getNodes();
		
		for (Map.Entry<String, Node> map : nodes.entrySet()) {
			System.out.println("Parent:");
			System.out.println(map.getKey());
			System.out.println("Children:");
			for (int i = 0; i < map.getValue().getAdjacent().size(); i++) {
				System.out.println(map.getValue().getAdjacent().get(i).getId());
			}
		}
		
		System.out.println();
		
		
//		System.out.println(graph.hasPathDFS("Pleven", "Blagoevgrad"));
		graph.bredthFirstSearch("Pleven", "Blagoevgrad");

	}

}
