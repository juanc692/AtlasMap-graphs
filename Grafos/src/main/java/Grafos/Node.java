package Grafos;

import java.util.*;

public class Node {

    private String name;

    private List<Node> shortestPath = new LinkedList<>();

    private Integer distance = Integer.MAX_VALUE;

    // Ahora usamos Edge como peso
    Map<Node, Edge> adjacentNodes = new HashMap<>();

    public void addDestination(Node destination, int distance, int tolls) {
        adjacentNodes.put(destination, new Edge(distance, tolls));
    }

    public Node(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public String getName() {
        return name;
    }

    public List<Node> getShortestPath() {
        return shortestPath;
    }

    public void setShortestPath(List<Node> shortestPath) {
        this.shortestPath = shortestPath;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public Map<Node, Edge> getAdjacentNodes() {
        return adjacentNodes;
    }
}
