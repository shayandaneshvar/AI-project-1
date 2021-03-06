package ir.ac.kntu;

import javafx.util.Pair;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class Board {
    private final BoardCell[][] boardCells;
    private Pair<Integer, Integer> currentLocation;
    private final Pair<Integer, Integer> goalLocation;
    private final int width, height;

    public Set<Pair<Integer, Integer>> getFreeCells() {
        HashSet<Pair<Integer, Integer>> cells = new HashSet<>();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (boardCells[j][i].equals(BoardCell.OBSTACLE)) {
                    continue;
                }
                cells.add(new Pair<>(i, j));
            }
        }
        return cells;
    }

    public Board(int width, int height, double density) {
        this.width = width;
        this.height = height;
        boardCells = new BoardCell[height][width];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                boardCells[j][i] = ThreadLocalRandom.current().nextDouble(0, 1) > density ?
                        BoardCell.EMPTY : BoardCell.OBSTACLE;
            }
        }

        boardCells[height - 1][0] = BoardCell.START;
        boardCells[0][width - 1] = BoardCell.END;
        resetCurrentLocation();
        goalLocation = new Pair<>(width - 1, 0);
    }

    public void resetCurrentLocation() {
        currentLocation = new Pair<>(0, height - 1);
    }

    public BoardCell getBoardCell(int row, int column) {
        return boardCells[row][column];
    }

    public Pair<Integer, Integer> getCurrentLocation() {
        return currentLocation;
    }

    public Pair<Integer, Integer> getGoalLocation() {
        return goalLocation;
    }

    public void setCurrentLocation(Pair<Integer, Integer> location) {
        this.currentLocation = location;
    }

    public boolean hasReachedGoal() {
        return goalLocation.getKey().equals(currentLocation.getKey()) &&
                goalLocation.getValue().equals(currentLocation.getValue());
    }

    public boolean isCurrentLocation(int row, int column) {
        return currentLocation.getKey().equals(column) && currentLocation.getValue().equals(row);
    }

    public List<Pair<Integer, Integer>> getNeighbourCells() {
        return List.of(new Pair<>(currentLocation.getKey() - 1, currentLocation.getValue()),
                new Pair<>(currentLocation.getKey(), currentLocation.getValue() - 1),
                new Pair<>(currentLocation.getKey(), currentLocation.getValue() + 1),
                new Pair<>(currentLocation.getKey() + 1, currentLocation.getValue()))
                .stream()
                .filter(z -> z.getKey() >= 0)
                .filter(z -> z.getValue() >= 0)
                .filter(z -> z.getKey() < width)
                .filter(z -> z.getValue() < height)
                .filter(z -> !boardCells[z.getValue()][z.getKey()].equals(BoardCell.OBSTACLE))
                .collect(Collectors.toList());
    }

    public boolean isInStart() {
        return currentLocation.getKey().equals(0) && currentLocation.getValue().equals(height - 1);
    }
}
