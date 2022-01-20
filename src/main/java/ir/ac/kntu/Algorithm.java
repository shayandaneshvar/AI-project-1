package ir.ac.kntu;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class Algorithm<T> {
    private final Set<T> visitedNodes = new HashSet<>();
    private final Board board;

    public Algorithm(Board board) {
        this.board = board;
    }

    protected Set<T> getVisitedNodes() {
        return visitedNodes;
    }
    protected Board getBoard(){
        return board;
    }
    public abstract T getNextState();

}
