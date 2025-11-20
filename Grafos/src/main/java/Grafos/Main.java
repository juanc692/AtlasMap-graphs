package Grafos;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        Node node1 = new Node("1");
        Node node2 = new Node("2");
        Node node3 = new Node("3");
        Node node4 = new Node("4");
        Node node5 = new Node("5");
        Node node6 = new Node("6");
        Node node7 = new Node("7");

        node1.addDestination(node2, 20, 0);
        node1.addDestination(node3, 40, 1);
        node1.addDestination(node7, 40, 1);

        node2.addDestination(node4, 50, 1);
        node2.addDestination(node1, 20, 0);

        node3.addDestination(node6, 100, 3);
        node3.addDestination(node1, 40, 1);

        node4.addDestination(node5, 10, 0);
        node4.addDestination(node2, 50, 1);

        node5.addDestination(node4, 10, 0);

        node6.addDestination(node7, 30, 2);
        node6.addDestination(node3, 100, 3);

        node7.addDestination(node6, 30, 2);
        node7.addDestination(node1, 40, 1);

        Graph graph = new Graph();
        graph.addNode(node1);
        graph.addNode(node2);
        graph.addNode(node3);
        graph.addNode(node4);
        graph.addNode(node5);
        graph.addNode(node6);
        graph.addNode(node7);

        boolean usarDistancia = true;  // true = distancia, false = peajes

        graph = calculateShortestPath(graph, node3, usarDistancia);

        Node destinoFinal = node6;

        System.out.println("Costo total: " + destinoFinal.getDistance());
        System.out.println("Ruta: " + destinoFinal.getShortestPath());
    }

    private static Node getLowestDistanceNode(Set<Node> unsettledNodes) {
        Node lowestDistanceNode = null;
        int lowestDistance = Integer.MAX_VALUE;

        for (Node node : unsettledNodes) {
            int nodeDistance = node.getDistance();
            if (nodeDistance < lowestDistance) {
                lowestDistance = nodeDistance;
                lowestDistanceNode = node;
            }
        }

        return lowestDistanceNode;
    }

    private static void calculateMinimumDistance(Node evaluationNode, Integer weight, Node sourceNode) {
        Integer sourceDistance = sourceNode.getDistance();

        if (sourceDistance + weight < evaluationNode.getDistance()) {
            evaluationNode.setDistance(sourceDistance + weight);

            LinkedList<Node> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
            shortestPath.add(sourceNode);
            evaluationNode.setShortestPath(shortestPath);
        }
    }

    public static Graph calculateShortestPath(Graph graph, Node source, boolean useDistance) {

        source.setDistance(0);

        Set<Node> settledNodes = new HashSet<>();
        Set<Node> unsettledNodes = new HashSet<>();

        unsettledNodes.add(source);

        while (!unsettledNodes.isEmpty()) {
            Node currentNode = getLowestDistanceNode(unsettledNodes);
            unsettledNodes.remove(currentNode);

            for (Map.Entry<Node, Edge> adjacencyPair : currentNode.getAdjacentNodes().entrySet()) {

                Node adjacent = adjacencyPair.getKey();
                Edge edge = adjacencyPair.getValue();

                int weight = useDistance ? edge.getDistance() : edge.getTolls();

                if (!settledNodes.contains(adjacent)) {
                    calculateMinimumDistance(adjacent, weight, currentNode);
                    unsettledNodes.add(adjacent);
                }
            }

            settledNodes.add(currentNode);
        }

        return graph;
    }
}
