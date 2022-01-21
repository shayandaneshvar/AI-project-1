package ir.ac.kntu;

import javafx.util.Pair;

public class AStarNode {
    private AStarNode previousNode;
    private final Pair<Integer, Integer> location;
    private double shortestDistanceFromStart;
    private double heuristicDistanceToGoal;
    private double totalDistance;

    public AStarNode(int x, int y) {
        this.location = new Pair<>(x, y);
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

    public void setPreviousNode(AStarNode previousNode) {
        this.previousNode = previousNode;
    }

    public Pair<Integer, Integer> getLocation() {
        return location;
    }

    public double getShortestDistanceFromStart() {
        return shortestDistanceFromStart;
    }

    public void setShortestDistanceFromStart(double shortestDistanceFromStart) {
        this.shortestDistanceFromStart = shortestDistanceFromStart;
    }

    public double getHeuristicDistanceToGoal() {
        return heuristicDistanceToGoal;
    }

    public void setHeuristicDistanceToGoal(double heuristicDistanceToGoal) {
        this.heuristicDistanceToGoal = heuristicDistanceToGoal;
    }

    public double getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(double totalDistance) {
        this.totalDistance = totalDistance;
    }
}
