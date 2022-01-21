package ir.ac.kntu;

import javafx.util.Pair;

public class GreedyAlgorithm extends Algorithm<Pair<Integer, Integer>> {

    public GreedyAlgorithm(Board board) {
        super(board);
    }

    @Override
    public Pair<Integer, Integer> getNextState() {
        if (getBoard().hasReachedGoal()) {
            return null;
        }
        getVisitedNodes().add(super.getBoard().getCurrentLocation());
        Pair<Integer, Integer> next = getBoard().getNeighbourCells()
                .stream()
                .filter(z -> !getVisitedNodes().contains(z))
                .min((x, y) -> (int) Math.round(heuristic(x) - heuristic(y)))
                .orElse(null);
        if (next != null) {
            getBoard().setCurrentLocation(next);
        }
        return next;
    }

}
