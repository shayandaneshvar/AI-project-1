package ir.ac.kntu;

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

    @FXML
    void generateBoard(ActionEvent event) {
        board = new Board(20, 20);
        drawBoard();
    }

    void drawBoard() {
        GridPane gridPane = new GridPane();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Node gridItem;
                if (board.isCurrentLocation(j, i)) {
                    gridItem = generateLabel("green");
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

    @FXML
    void runGreedy(ActionEvent event) {

    }


}
