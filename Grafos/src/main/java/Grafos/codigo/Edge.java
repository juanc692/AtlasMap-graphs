package Grafos.codigo;

public class Edge {
    private int distance;
    private int tolls;

    public Edge(int distance, int tolls) {
        this.distance = distance;
        this.tolls = tolls;
    }

    public int getDistance() {
        return distance;
    }

    public int getTolls() {
        return tolls;
    }
}
