package Grafos.codigo;

import java.util.*;

public class Main {
    private List<String> aristas;
    private String costo;
    public static void main(String[] args) {

        Node node1 = new Node("Funza");
        Node node2 = new Node("Medellin");
        Node node3 = new Node("Villao");
        Node node4 = new Node("Cali");
        Node node5 = new Node("Tunja");
        Node node6 = new Node("Pereira");
        Node node7 = new Node("Bogota");

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

        calculateShortestPath(graph, node2, usarDistancia); //aca se define el nodo de inicio

        Node destinoFinal = node6;

        System.out.println("Costo total: " + destinoFinal.getDistance());

        System.out.println("Ruta: " + obtenerRuta(destinoFinal));

        System.out.println("Aristas recorridas: " + obtenerAristas(destinoFinal));
    }

    public Main(String partida, String destino,boolean peaje)
    {
        Node node1 = new Node("funza");
        Node node2 = new Node("medellin");
        Node node3 = new Node("villao");
        Node node4 = new Node("cali");
        Node node5 = new Node("tunja");
        Node node6 = new Node("pereira");
        Node node7 = new Node("bogota");

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

        boolean usarDistancia = !peaje;  // true = distancia, false = peajes

        Node nodePartida = null,nodeDestino = null;
        for(Node node : Arrays.asList(node1,node2,node3,node4,node5,node6,node7))
        {
            if(node.getName().equalsIgnoreCase(partida))
            {
                nodePartida = node;
            }
            if(node.getName().equalsIgnoreCase(destino))
            {
                nodeDestino = node;
            }
        }

        calculateShortestPath(graph, nodePartida, usarDistancia); //aca se define el nodo de inicio

        Node destinoFinal = nodeDestino;

        //System.out.println("Costo total: " + destinoFinal.getDistance());
        setCosto(destinoFinal.getDistance().toString());
        //System.out.println("Ruta: " + obtenerRuta(destinoFinal));

        setAristas(obtenerAristas(destinoFinal));
        //System.out.println("Aristas recorridas: " + getAristas());

    }

    private void setCosto(String costo)
    {
        this.costo = costo;
    }
    public String getCosto()
    {
        return costo;
    }

    private void setAristas(List<String> aristas)
    {
        this.aristas = aristas;
    }
    public List<String> getAristas()
    {
        return aristas;
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
    private static List<String> obtenerAristas(Node destino) {
        List<String> edges = new ArrayList<>();

        List<Node> path = destino.getShortestPath();
        if (path.isEmpty()) return edges;

        Node prev = path.get(0);
        for (int i = 1; i < path.size(); i++) {
            Node curr = path.get(i);
            edges.add(prev.getName() + "_" + curr.getName());
            prev = curr;
        }

        // última arista hacia el destino final
        edges.add(prev.getName() + "_" + destino.getName());

        return edges;
    }

    private static String obtenerRuta(Node destino) {
        List<Node> path = destino.getShortestPath();
        StringBuilder sb = new StringBuilder();

        for (Node n : path) {
            sb.append(n.getName()).append(" -> ");
        }
        sb.append(destino.getName());

        return sb.toString();
    }

}

