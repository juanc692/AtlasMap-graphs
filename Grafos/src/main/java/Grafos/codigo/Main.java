package Grafos.codigo;

import java.util.*;

// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Node node1 = new Node("1");
        Node node2 = new Node("2");
        Node node3 = new Node("3");
        Node node4 = new Node("4");
        Node node5 = new Node("5");
        Node node6 = new Node("6");
        Node node7 = new Node("7");

        node1.addDestination(node2, 20);
        node1.addDestination(node3, 40);
        node1.addDestination(node7, 40);

        node2.addDestination(node4, 50);
        node2.addDestination(node1, 20);

        node3.addDestination(node6, 100);
        node3.addDestination(node1, 40);

        node4.addDestination(node5, 10);
        node4.addDestination(node5, 20);

        node5.addDestination(node4, 10);

        node6.addDestination(node7, 30);
        node6.addDestination(node3, 100);

        node7.addDestination(node6, 30);
        node7.addDestination(node1, 40);

        Graph graph = new Graph();

        graph.addNode(node1);
        graph.addNode(node2);
        graph.addNode(node3);
        graph.addNode(node4);
        graph.addNode(node5);
        graph.addNode(node6);

        graph = calculateShortestPathFromSource(graph, node1);

        Node destinoFinal = node6;

        System.out.println("Distancia de 1 a " + destinoFinal.getName() + ": " + destinoFinal.getDistance());
        // Imprime la lista de nodos guardada en el campo shortestPath de node6
        System.out.println("Ruta: " + destinoFinal.getShortestPath());

    }

    private static Node getLowestDistanceNode(Set < Node > unsettledNodes) {
        Node lowestDistanceNode = null;
        int lowestDistance = Integer.MAX_VALUE;
        for (Node node: unsettledNodes) {
            int nodeDistance = node.getDistance();
            if (nodeDistance < lowestDistance) {
                lowestDistance = nodeDistance;
                lowestDistanceNode = node;
            }
        }
        return lowestDistanceNode;
    }
    private static void calculateMinimumDistance(Node evaluationNode,
                                                 Integer edgeWeigh, Node sourceNode) {
        Integer sourceDistance = sourceNode.getDistance();
        if (sourceDistance + edgeWeigh < evaluationNode.getDistance()) {
            evaluationNode.setDistance(sourceDistance + edgeWeigh);
            LinkedList<Node> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
            shortestPath.add(sourceNode);
            evaluationNode.setShortestPath(shortestPath);
        }
    }

    public static Graph calculateShortestPathFromSource(Graph graph, Node source) {
        source.setDistance(0);

        Set<Node> settledNodes = new HashSet<>();
        Set<Node> unsettledNodes = new HashSet<>();

        unsettledNodes.add(source);

        while (unsettledNodes.size() != 0) {
            Node currentNode = getLowestDistanceNode(unsettledNodes);
            unsettledNodes.remove(currentNode);
            for (Map.Entry< Node, Integer> adjacencyPair:
                    currentNode.getAdjacentNodes().entrySet()) {
                Node adjacentNode = adjacencyPair.getKey();
                Integer edgeWeight = adjacencyPair.getValue();
                if (!settledNodes.contains(adjacentNode)) {
                    calculateMinimumDistance(adjacentNode, edgeWeight, currentNode);
                    unsettledNodes.add(adjacentNode);
                }
            }
            settledNodes.add(currentNode);
        }
        return graph;
    }


}


