package ir.ac.kntu;

import java.util.PriorityQueue;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicReference;

public class AStarAlgorithm extends Algorithm<AStarNode> {
    private final Stack<AStarNode> path = new Stack<>();
    private final PriorityQueue<AStarNode> unVisitedNodes;

    {
        unVisitedNodes = new PriorityQueue<>((x, y) ->
                (int) Math.round(x.getTotalDistance() - y.getTotalDistance()));
    }


    public AStarAlgorithm(Board board) {
        super(board);
        getVisitedNodes().add(new AStarNode(board.getCurrentLocation())
                .setShortestDistanceFromStart(0)
                .setPreviousNode(null)
                .setHeuristicDistanceToGoal(heuristic(board.getCurrentLocation())));

//        board.getFreeCells().stream()
//                .filter(z -> !z.equals(board.getCurrentLocation()))
//                .forEach(z -> unVisitedNodes.add(new AStarNode(z)
//                        .setPreviousNode(null)
//                        .setShortestDistanceFromStart(Double.MAX_VALUE)
//                        .setHeuristicDistanceToGoal(heuristic(z))));
        run();
    }

    public void run() {
        while (true) {
            AStarNode current = getVisitedNodes()
                    .stream()
                    .filter(z -> z.getLocation().equals(getBoard().getCurrentLocation()))
                    .findFirst()
                    .orElseThrow();

            getBoard().getNeighbourCells()
                    .stream()
                    .filter(z -> getVisitedNodes().stream().noneMatch(x -> x.getLocation().equals(z)))
                    .forEach(z -> addNodeToUnVisited(new AStarNode(z)
                            .setPreviousNode(current)
                            .setHeuristicDistanceToGoal(heuristic(z))
                            .setShortestDistanceFromStart(cost(current))));

            AStarNode selectedNode = unVisitedNodes.poll();
            if (selectedNode == null) {
                break;
            }
            getVisitedNodes().add(selectedNode);
            getBoard().setCurrentLocation(selectedNode.getLocation());
            if (getBoard().hasReachedGoal()) {
                buildPath(selectedNode);
                getBoard().resetCurrentLocation();
                break;
            }
        }
    }

    // G(n)
    private double cost(AStarNode current) {
        return current.getShortestDistanceFromStart() + 1;
    }

    private void buildPath(AStarNode lastNode) {
        AtomicReference<AStarNode> currentNode = new AtomicReference<>(lastNode);
        while (currentNode.get().getPreviousNode() != null) {
            path.push(currentNode.get());
            currentNode.set(getVisitedNodes().stream()
                    .filter(z -> z.getLocation().equals(currentNode.get().getPreviousNode().getLocation()))
                    .findFirst().orElseThrow());
        }
    }

    private void addNodeToUnVisited(AStarNode node) {
        unVisitedNodes.stream()
                .filter(z -> z.getLocation().equals(node.getLocation()))
                .findFirst()
                .ifPresentOrElse(z -> {
                    if (node.getTotalDistance() < z.getTotalDistance()) {
                        unVisitedNodes.remove(z);
                        unVisitedNodes.add(node);
                    }
                }, () -> unVisitedNodes.add(node));
    }


    @Override
    public AStarNode getNextState() {
        try {
            AStarNode next = path.pop();
            getBoard().setCurrentLocation(next.getLocation());
            return next;
        } catch (Exception e) {
            return null;
        }
    }


}
