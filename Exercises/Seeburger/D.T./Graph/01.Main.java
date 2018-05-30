package com.seeburger.graph2;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.lang.model.element.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Main {

	public static void main(String[] args) {
		Graph graph = new Graph();

		// graph.addEdge("Sofia", "Plovdiv");
		// graph.addEdge("Sofia", "Pleven");
		// graph.addEdge("Sofia", "Blagoevgrad");
		// graph.addEdge("Plovdiv", "Stara Zagora");
		// graph.addEdge("Plovdiv", "Smolian");
		// graph.addEdge("Stara Zagora", "Pleven");
		// graph.addEdge("Stara Zagora", "Burgas");
		// graph.addEdge("Smolian", "Blagoevgrad");
		// graph.addEdge("Smolian", "Kurdzhali");
		// graph.addEdge("Kurdzhali", "Stara Zagora");
		// graph.addEdge("Kurdzhali", "Burgas");

		try {
			File xmlFileLoc = new File(
					"C:\\Users\\Georgiev\\eclipse-workspace\\BurgerChallenges1\\src\\com\\seeburger\\graph2\\GraphLocations.xml");
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document = documentBuilder.parse(xmlFileLoc);

			document.getDocumentElement().normalize();

			NodeList nodeList = document.getElementsByTagName("city");

			for (int i = 0; i < nodeList.getLength(); i++) {
				
				NodeList adjacent = nodeList.item(i).getChildNodes();

				org.w3c.dom.Node node = nodeList.item(i);

				org.w3c.dom.Element element = (org.w3c.dom.Element) node;
				String currentCity = element.getAttribute("id");
				System.out.println(currentCity);
				for (int j = 0; j < adjacent.getLength(); j++) {
					String adjacentCity = adjacent.item(j).getTextContent();
					if (adjacentCity.matches("[a-zA-z]+")) {
						System.out.println("\t" + adjacentCity);						
					}
//					System.out.println(
//							"\t" + element.getElementsByTagName("connection" + (j + 1)).item(i).getTextContent());
				}
			}

		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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

		// System.out.println(graph.hasPathDFS("Pleven", "Blagoevgrad"));
		// graph.bredthFirstSearch("Pleven", "Blagoevgrad");

	}

}
