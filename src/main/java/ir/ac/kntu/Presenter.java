package ir.ac.kntu;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class Presenter {

    @FXML
    private TextField boardWidth;

    @FXML
    private TextField boardHeight;

    @FXML
    private TextField boardDensity;
    @FXML
    private Pane gridContainer;
    private Board board;
    private int width, height;
    private GreedyAlgorithm greedyAlgorithm;
    private AStarAlgorithm aStarAlgorithm;
    private AnimationTimer timer = null;

    @FXML
    void generateBoard(ActionEvent event) {
        cancelTimer();
        this.width = castToInt(boardWidth.getText(), 20);
        this.height = castToInt(boardHeight.getText(), 20);
        double density = castToDouble(boardDensity.getText(), 0.2);
        board = new Board(width, height, density);
        drawBoard();
    }

    public int castToInt(String value, int defaultValue) {
        try {
            return Integer.parseInt(value.trim());
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public double castToDouble(String value, double defaultValue) {
        try {
            return Double.parseDouble(value.trim());
        } catch (Exception e) {
            return defaultValue;
        }
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
        cancelTimer();
        board.resetCurrentLocation();
        aStarAlgorithm = new AStarAlgorithm(board);

        timer = new AnimationTimer() {
            private Long time;
            private boolean running = true;

            @Override
            public void stop() {
                super.stop();
                running = false;
                event.consume();
            }

            @Override
            public void handle(long now) {
                if (!running) {
                    return;
                }
                if (time == null) {
                    time = now;
                }

                if (now - time < 0.2 * 1e9) {
                    return;
                }

                time = now;
                if (aStarAlgorithm.getNextState() == null && !board.isInStart()) {
                    drawBoard("brown");
                    return;
                }
                drawBoard();
            }
        };
        timer.start();
    }

    public void cancelTimer() {
        if (timer != null) {
            timer.stop();
        }
    }

    @FXML
    void runGreedy(ActionEvent event) {
        cancelTimer();
        board.resetCurrentLocation();
        greedyAlgorithm = new GreedyAlgorithm(board);
        timer = new AnimationTimer() {
            private Long time;
            private boolean running = true;

            @Override
            public void stop() {
                super.stop();
                running = false;
            }

            @Override
            public void handle(long now) {
                if (!running) {
                    event.consume();
                    return;
                }
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
