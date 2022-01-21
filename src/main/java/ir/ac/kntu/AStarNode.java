package ir.ac.kntu;

import javafx.util.Pair;

public class AStarNode {
    private AStarNode previousNode;
    private final Pair<Integer, Integer> location;
    private double shortestDistanceFromStart;
    private double heuristicDistanceToGoal;

    public AStarNode(int x, int y) {
        this.location = new Pair<>(x, y);
    }

    public AStarNode(Pair<Integer, Integer> pair) {
        this.location = pair;
    }

    public int getX() {
        return location.getKey();
    }

    public int getY() {
        return location.getValue();
    }

    public AStarNode getPreviousNode() {
        return previousNode;
    }

    public AStarNode setPreviousNode(AStarNode previousNode) {
        this.previousNode = previousNode;
        return this;
    }

    public Pair<Integer, Integer> getLocation() {
        return location;
    }

    public double getShortestDistanceFromStart() {
        return shortestDistanceFromStart;
    }

    public AStarNode setShortestDistanceFromStart(double shortestDistanceFromStart) {
        this.shortestDistanceFromStart = shortestDistanceFromStart;
        return this;
    }

    public double getHeuristicDistanceToGoal() {
        return heuristicDistanceToGoal;
    }

    public AStarNode setHeuristicDistanceToGoal(double heuristicDistanceToGoal) {
        this.heuristicDistanceToGoal = heuristicDistanceToGoal;
        return this;
    }
    // f(n) = g(n) + h(n)
    public double getTotalDistance() {
        return heuristicDistanceToGoal + shortestDistanceFromStart;
    }

}
