package ir.ac.kntu;

import javafx.util.Pair;

import java.util.concurrent.ThreadLocalRandom;

public class Board {
    private final BoardCell[][] boardCells;
    private Pair<Integer, Integer> currentLocation;

    public Board(int width, int height) {
        boardCells = new BoardCell[height][width];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                boardCells[j][i] = ThreadLocalRandom.current().nextDouble(0, 1) > 0.15 ?
                        BoardCell.EMPTY : BoardCell.OBSTACLE;
            }
        }

        boardCells[height - 1][0] = BoardCell.START;
        boardCells[0][width - 1] = BoardCell.END;
        currentLocation = new Pair<>(0, height - 1);
    }

    public BoardCell getBoardCell(int row, int column) {
        return boardCells[row][column];
    }

    public Pair<Integer,Integer> getCurrentLocation(){
        return currentLocation;
    }

    public boolean isCurrentLocation(int row,int column){
        return currentLocation.getKey().equals(column) && currentLocation.getValue().equals(row);
    }

}
