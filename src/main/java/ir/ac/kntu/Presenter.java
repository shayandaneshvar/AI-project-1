package ir.ac.kntu;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class Presenter {


    @FXML
    private Pane gridContainer;
    private Board board;
    private static final int width = 20, height = 20;
    private GreedyAlgorithm greedyAlgorithm;
    private AnimationTimer timer = null;

    @FXML
    void generateBoard(ActionEvent event) {
        cancelTimer();
        board = new Board(20, 20);
        greedyAlgorithm = new GreedyAlgorithm(board);
        drawBoard();
    }

    void drawBoard() {
        drawBoard(null);
    }

    void drawBoard(String failureColor) {
        GridPane gridPane = new GridPane();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Node gridItem;
                if (board.isCurrentLocation(j, i)) {
                    if (board.hasReachedGoal()) {
                        gridItem = generateLabel("yellowgreen");
                    } else if (failureColor != null) {
                        gridItem = generateLabel(failureColor);
                    } else {
                        gridItem = generateLabel("green");
                    }
                } else {
                    gridItem = generateGridItem(board.getBoardCell(j, i));

                }
                gridPane.add(gridItem, i, j, 1, 1);
            }
        }
        gridContainer.getChildren().clear();
        gridContainer.getChildren().add(gridPane);
    }

    private Node generateGridItem(BoardCell cell) {
        return generateLabel(cell.getColor());
    }

    public Label generateLabel(String color) {
        Label label = new Label(" ");
        label.setStyle("-fx-label-padding: 4px 10px; -fx-background-color: " + color + "; ");
        return label;
    }


    @FXML
    void runAStar(ActionEvent event) {

    }

    public void cancelTimer() {
        if (timer != null) {
            timer.stop();
        }
    }

    @FXML
    void runGreedy(ActionEvent event) {
        cancelTimer();

        timer = new AnimationTimer() {
            private Long time;

            @Override
            public void handle(long now) {
                if (time == null) {
                    time = now;
                }

                if (now - time < 0.2 * 1e9) {
                    return;
                }

                time = now;
                if (greedyAlgorithm.getNextState() == null) {
                    drawBoard("brown");
                    return;
                }
                drawBoard();
            }
        };
        timer.start();
    }


}
