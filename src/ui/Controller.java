package ui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.util.Pair;
import logic.*;

import java.util.Optional;

public class Controller {
    private static int gameMode;
    private static int clicksCount = 0;
    private static Location firstClick;
    private static int playerTurn = 1;
    @FXML
    private GridPane mygrid;

    static void setGameMode(int gameMode) {
        Controller.gameMode = gameMode;
    }

    private static final double[][] pawn = new double[][]{{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0}, {5.0, 5.0, 5.0, 5.0, 5.0, 5.0, 5.0, 5.0}, {1.0, 1.0, 2.0, 3.0, 3.0, 2.0, 1.0, 1.0}, {0.5, 0.5, 1.0, 2.5, 2.5, 1.0, 0.5, 0.5}, {0.0, 0.0, 0.0, 2.0, 2.0, 0.0, 0.0, 0.0}, {0.5, -0.5, -1.0, 0.0, 0.0, -1.0, -0.5, 0.5}, {0.5, 1.0, 1.0, -2.0, -2.0, 1.0, 1.0, 0.5}, {2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0}};
    private static final double[][] knight = new double[][]{{-5.0, -4.0, -3.0, -3.0, -3.0, -3.0, -4.0, -5.0}, {-4.0, -2.0, 0.0, 0.0, 0.0, 0.0, -2.0, -4.0}, {-3.0, 0.0, 1.0, 1.5, 1.5, 1.0, 0.0, -3.0}, {-3.0, 0.5, 1.5, 2.0, 2.0, 1.5, 0.5, -3.0}, {-3.0, 0.0, 1.5, 2.0, 2.0, 1.5, 0.0, -3.0}, {-3.0, 0.5, 1.0, 1.5, 1.5, 1.0, 0.5, -3.0}, {-4.0, -2.0, 0.0, 0.5, 0.5, 0.0, -2.0, -4.0}, {-5.0, -4.0, -3.0, -3.0, -3.0, -3.0, -4.0, -5.0}};
    private static final double[][] bishop = new double[][]{{-2.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -2.0}, {-1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, -1.0}, {-1.0, 0.0, 0.5, 1.0, 1.0, 0.5, 0.0, -1.0}, {-1.0, 0.5, 0.5, 1.0, 1.0, 0.5, 0.5, -1.0}, {-1.0, 0.0, 1.0, 1.0, 1.0, 1.0, 0.0, -1.0}, {-1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, -1.0}, {-1.0, 0.5, 0.0, 0.0, 0.0, 0.0, 0.5, -1.0}, {-2.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -2.0}};
    private static final double[][] rook = new double[][]{{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0}, {0.5, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.5}, {-0.5, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, -0.5}, {-0.5, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, -0.5}, {-0.5, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, -0.5}, {-0.5, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, -0.5}, {-0.5, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, -0.5}, {0.0, 0.0, 0.0, 0.5, 0.5, 0.0, 0.0, 0.0}};
    private static final double[][] queen = new double[][]{{-2.0, -1.0, -1.0, -0.5, -0.5, -1.0, -1.0, -2.0}, {-1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, -1.0}, {-1.0, 0.0, 0.5, 0.5, 0.5, 0.5, 0.0, -1.0}, {-0.5, 0.0, 0.5, 0.5, 0.5, 0.5, 0.0, -0.5}, {0.0, 0.0, 0.5, 0.5, 0.5, 0.5, 0.0, -0.5}, {-1.0, 0.5, 0.5, 0.5, 0.5, 0.5, 0.0, -1.0}, {-1.0, 0.0, 0.5, 0.0, 0.0, 0.0, 0.0, -1.0}, {-2.0, -1.0, -1.0, -0.5, -0.5, -1.0, -1.0, -2.0}};
    private static final double[][] king = new double[][]{{-3.0, -4.0, -4.0, -5.0, -5.0, -4.0, -4.0, -3.0}, {-3.0, -4.0, -4.0, -5.0, -5.0, -4.0, -4.0, -3.0}, {-3.0, -4.0, -4.0, -5.0, -5.0, -4.0, -4.0, -3.0}, {-3.0, -4.0, -4.0, -5.0, -5.0, -4.0, -4.0, -3.0}, {-2.0, -3.0, -3.0, -4.0, -4.0, -3.0, -3.0, -2.0}, {-1.0, -2.0, -2.0, -2.0, -2.0, -2.0, -2.0, -1.0}, {2.0, 2.0, 0.0, 0.0, 0.0, 0.0, 2.0, 2.0}, {2.0, 3.0, 1.0, 0.0, 0.0, 1.0, 3.0, 2.0}};
    @FXML
    private FlowPane white_cap, black_cap;

    private static void resetClicksCount() {
        Controller.clicksCount = 0;
    }

    private static Board mainBoard = new Board();

    public void handle(MouseEvent e) {
        if (clicksCount < 1) {
            if (!getMainBoard().isEmpty(new Location(GridPane.getRowIndex((StackPane) e.getSource()), GridPane.getColumnIndex((StackPane) e.getSource()))) && getMainBoard().getGrid()[GridPane.getRowIndex((StackPane) e.getSource())][GridPane.getColumnIndex((StackPane) e.getSource())].getColor() == playerTurn) {
                firstClick = new Location(GridPane.getRowIndex((StackPane) e.getSource()), GridPane.getColumnIndex((StackPane) e.getSource()));
                clicksCount++;
            }
        } else if (clicksCount == 1) {
            if (!new Location(GridPane.getRowIndex((StackPane) e.getSource()), GridPane.getColumnIndex((StackPane) e.getSource())).equals(firstClick)) {
                Location secondClick = new Location(GridPane.getRowIndex((StackPane) e.getSource()), GridPane.getColumnIndex((StackPane) e.getSource()));
                takeAction(secondClick, firstClick);

            }
        }
    }

    private void takeAction(Location to, Location from) {
        if (mainBoard.getGrid()[from.getX()][from.getY()].move(mainBoard, from, to) && !willBeChecked(to, from, mainBoard.getGrid()[from.getX()][from.getY()].getColor())) {
            play(to, from,1);
            playerTurn = playerTurn == 1 ? 2 : 1;
            if (isChecked(getKing(playerTurn))) {
                if (isCheckmate(playerTurn)) {
                    showEnd((playerTurn == 1 ? "BLACK " : "WHITE ") + "PLAYER WON !");
                    return;
                }
            } else {
                if (isStalemate(playerTurn)) {
                    showEnd("Stalemate !!");
                    return;
                }
            }
            if (gameMode == 1) {
                Pair<Double, Pair<Location, Location>> machineMove = bestMove(0, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, getBoardValue());
                play(machineMove.getValue().getValue(), machineMove.getValue().getKey(),0);
                playerTurn = playerTurn == 1 ? 2 : 1;
                if (isChecked(getKing(playerTurn))) {
                    if (isCheckmate(playerTurn)) {
                        showEnd((playerTurn == 1 ? "BLACK " : "WHITE ") + "PLAYER WON !");
                        return;
                    }
                } else {
                    if (isStalemate(playerTurn)) {
                        showEnd("Stalemate  !!");
                        return;
                    }
                }
            }
        }
        resetClicksCount();

    }

    private Board getMainBoard() {
        return mainBoard;
    }

    private void play(Location to, Location from,int who) {
        if(mainBoard.getGrid()[from.getX()][from.getY()] instanceof Pawn && ((to.getX()==0)||(to.getX()==7))){
            flipPawn(from,who);
        }
        if (mainBoard.isEmpty(to)) {
            move(to, from, mygrid);
        } else {
            capture(to, white_cap, black_cap);
            move(to, from, mygrid);
        }


    }

    private void capture(Location to, FlowPane white_cap, FlowPane black_cap) {
        if (mainBoard.getGrid()[to.getX()][to.getY()].getColor() == 1) {
            white_cap.getChildren().add(mainBoard.getGrid()[to.getX()][to.getY()].getImgView());
        } else {
            black_cap.getChildren().add(mainBoard.getGrid()[to.getX()][to.getY()].getImgView());
        }

    }
    private void flipPawn(Location from,int who){
        if (who==1){
            Alert endAlert = new Alert(Alert.AlertType.CONFIRMATION);
            ButtonType knight = new ButtonType("Knight");
            ButtonType bishop = new ButtonType("Bishop");
            ButtonType rook = new ButtonType("Rook");
            ButtonType queen = new ButtonType("Queen");

            endAlert.setTitle("Pawn promotion");
            endAlert.setHeaderText(null);
            endAlert.setContentText("Please choose a piece as a promotion for your pawn");
            endAlert.getButtonTypes().setAll(knight,bishop,rook,queen);
            Optional<ButtonType> outcome = endAlert.showAndWait();
            if (outcome.isPresent() && outcome.get() == knight) {
                mainBoard.getGrid()[from.getX()][from.getY()]=new Knight(mainBoard.getGrid()[from.getX()][from.getY()].getColor(),new ImageView(new Image(getClass().getResourceAsStream(("piecesImgs/"+(mainBoard.getGrid()[from.getX()][from.getY()].getColor()==1?"White":"Black")+"_Knight.png")))));
            } else if (outcome.isPresent() && outcome.get() == bishop) {
                mainBoard.getGrid()[from.getX()][from.getY()]=new Rook(mainBoard.getGrid()[from.getX()][from.getY()].getColor(),new ImageView(new Image(getClass().getResourceAsStream(("piecesImgs/"+(mainBoard.getGrid()[from.getX()][from.getY()].getColor()==1?"White":"Black")+"_Rook.png")))));
            } else if (outcome.isPresent() && outcome.get() == rook) {
                mainBoard.getGrid()[from.getX()][from.getY()]=new Bishop(mainBoard.getGrid()[from.getX()][from.getY()].getColor(),new ImageView(new Image(getClass().getResourceAsStream(("piecesImgs/"+(mainBoard.getGrid()[from.getX()][from.getY()].getColor()==1?"White":"Black")+"_Bishop.png")))));
            }else if (outcome.isPresent() && outcome.get() == queen) {
                mainBoard.getGrid()[from.getX()][from.getY()]=new Queen(mainBoard.getGrid()[from.getX()][from.getY()].getColor(),new ImageView(new Image(getClass().getResourceAsStream(("piecesImgs/"+(mainBoard.getGrid()[from.getX()][from.getY()].getColor()==1?"White":"Black")+"_Queen.png")))));
            }
        }
        else
            mainBoard.getGrid()[from.getX()][from.getY()]=new Queen(mainBoard.getGrid()[from.getX()][from.getY()].getColor(),new ImageView("piecesImgs/Black_Queen.png"));

    }
    private void setTile(GridPane mygrid, Location l, ImageView v) {
        clearTile(mygrid, l);
        ((StackPane) (mygrid.getChildren().get(l.getX() * 8 + l.getY()))).getChildren().add(v);
    }

    private void clearTile(GridPane mygrid, Location l) {
        if (((StackPane) (mygrid.getChildren().get(l.getX() * 8 + l.getY()))).getChildren().size() == 2)
            ((StackPane) (mygrid.getChildren().get(l.getX() * 8 + l.getY()))).getChildren().remove(1);
    }

    private void move(Location to, Location from, GridPane mygrid) {
        setTile(mygrid, to, mainBoard.getGrid()[from.getX()][from.getY()].getImgView());
        clearTile(mygrid, from);
        mainBoard.getGrid()[to.getX()][to.getY()] = mainBoard.getGrid()[from.getX()][from.getY()];
        mainBoard.getGrid()[from.getX()][from.getY()] = null;
    }

    private boolean willBeChecked(Location to, Location from, int color) {
        Piece temp = applyVirtually(to, from);
        if (isChecked(getKing(color))) {
            undoVirtually(to, from, temp);
            return true;
        }
        undoVirtually(to, from, temp);
        return false;
    }

    private boolean isChecked(Location king) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (!mainBoard.isEmpty(new Location(i, j)) && mainBoard.getGrid()[king.getX()][king.getY()].getColor() != mainBoard.getGrid()[i][j].getColor() && mainBoard.getGrid()[i][j].move(mainBoard, new Location(i, j), king))
                    return true;
            }
        }
        return false;
    }

    private Location getKing(int color) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (mainBoard.getGrid()[i][j] instanceof King && mainBoard.getGrid()[i][j].getColor() == color)
                    return new Location(i, j);
            }
        }
        return null;
    }

    private boolean isCheckmate(int color) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (!mainBoard.isEmpty(new Location(i, j)) && mainBoard.getGrid()[i][j].getColor() == color) {
                    for (int k = 0; k < 8; k++) {
                        for (int l = 0; l < 8; l++) {
                            if (mainBoard.getGrid()[i][j].move(mainBoard, new Location(i, j), new Location(k, l))) {
                                Piece temp = applyVirtually(new Location(k, l), new Location(i, j));
                                if (!isChecked(getKing(color))) {
                                    undoVirtually(new Location(k, l), new Location(i, j), temp);
                                    return false;
                                }
                                undoVirtually(new Location(k, l), new Location(i, j), temp);
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    private Piece applyVirtually(Location to, Location from) {
        Piece temp = mainBoard.getGrid()[to.getX()][to.getY()];
        mainBoard.getGrid()[to.getX()][to.getY()] = mainBoard.getGrid()[from.getX()][from.getY()];
        mainBoard.getGrid()[from.getX()][from.getY()] = null;
        return temp;
    }

    private void undoVirtually(Location to, Location from, Piece temp) {
        mainBoard.getGrid()[from.getX()][from.getY()] = mainBoard.getGrid()[to.getX()][to.getY()];
        mainBoard.getGrid()[to.getX()][to.getY()] = temp;
    }

    private void showEnd(String str) {
        Alert endAlert = new Alert(Alert.AlertType.CONFIRMATION);
        ButtonType newSingle = new ButtonType("New game (Single Player)");
        ButtonType newMulti = new ButtonType("New game (Multi Player)");
        ButtonType exit = new ButtonType("Exit");
        endAlert.setTitle("Game Over");
        endAlert.setHeaderText(null);
        endAlert.setContentText(str);
        endAlert.getButtonTypes().setAll(newSingle, newMulti, exit);
        Optional<ButtonType> outcome = endAlert.showAndWait();
        if (outcome.isPresent() && outcome.get() == newSingle) {
            Controller.setGameMode(1);
            newGame();
        } else if (outcome.isPresent() && outcome.get() == newMulti) {
            Controller.setGameMode(2);
            newGame();
        } else if (outcome.isPresent() && outcome.get() == exit) {
            Platform.exit();
        }

    }


    private void newGame() {
        clicksCount = 0;
        playerTurn = 1;
        white_cap.getChildren().clear();
        black_cap.getChildren().clear();
        mainBoard = new Board();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                clearTile(mygrid, new Location(i, j));
                if (!mainBoard.isEmpty(new Location(i, j))) {
                    setTile(mygrid, new Location(i, j), mainBoard.getGrid()[i][j].getImgView());
                }
            }
        }
    }

    private double relativeValue(Piece p, Location l) {
        if (p instanceof Pawn)
            return p.getColor() == 2 ? 10 + pawn[7 - l.getX()][l.getY()] : -10 - pawn[l.getX()][l.getY()];
        else if (p instanceof Knight)
            return p.getColor() == 2 ? 30 + knight[7 - l.getX()][l.getY()] : -30 - knight[l.getX()][l.getY()];
        else if (p instanceof Bishop)
            return p.getColor() == 2 ? 30 + bishop[7 - l.getX()][l.getY()] : -30 - bishop[l.getX()][l.getY()];
        else if (p instanceof Rook)
            return p.getColor() == 2 ? 50 + rook[7 - l.getX()][l.getY()] : -50 - rook[l.getX()][l.getY()];
        else if (p instanceof Queen)
            return p.getColor() == 2 ? 90 + queen[7 - l.getX()][l.getY()] : -90 - queen[l.getX()][l.getY()];
        else
            return p.getColor() == 2 ? 900 + king[7 - l.getX()][l.getY()] : -900 - king[l.getX()][l.getY()];
    }


    private double getBoardValue() {
        double sum = 0;
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
                if (!mainBoard.isEmpty(new Location(i, j)))
                    sum += relativeValue(mainBoard.getGrid()[i][j], new Location(i, j));
        return sum;
    }

    private double upadateBoardValue(Location to, Location from, double intial) {
        intial -= relativeValue(mainBoard.getGrid()[from.getX()][from.getY()], from);
        intial += relativeValue(mainBoard.getGrid()[from.getX()][from.getY()], to);
        if (!mainBoard.isEmpty(to)) {
            intial -= relativeValue(mainBoard.getGrid()[to.getX()][to.getY()], to);
        }
        return intial;
    }

    private Pair<Double, Pair<Location, Location>> bestMove(int depth, double alpha, double beta, double currentValue) {
        if (depth == 5) {
            return new Pair<>(currentValue, new Pair<>(null, null));

        }
        Pair<Double, Pair<Location, Location>> myreturn = new Pair<>(depth % 2 == 0 ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY, new Pair<>(null, null));
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (!mainBoard.isEmpty(new Location(i, j)) && mainBoard.getGrid()[i][j].getColor() == 2 - depth % 2) {
                    for (int k = 0; k < 8; k++) {
                        for (int l = 0; l < 8; l++) {
                            if (mainBoard.getGrid()[i][j].move(mainBoard, new Location(i, j), new Location(k, l)) && !willBeChecked(new Location(k, l), new Location(i, j), mainBoard.getGrid()[i][j].getColor())) {
                                double updatedValue = upadateBoardValue(new Location(k, l), new Location(i, j), currentValue);
                                Piece temp = applyVirtually(new Location(k, l), new Location(i, j));
                                Pair<Double, Pair<Location, Location>> mytemp = new Pair<>(bestMove(depth + 1, alpha, beta, updatedValue).getKey(), new Pair<>(new Location(i, j), new Location(k, l)));
                                if (depth % 2 == 0) {
                                    if (mytemp.getKey() >= myreturn.getKey()) {
                                        myreturn = mytemp;
                                        alpha = myreturn.getKey();
                                    }
                                } else {
                                    if (mytemp.getKey() <= myreturn.getKey()) {
                                        myreturn = mytemp;
                                        beta = myreturn.getKey();
                                    }
                                }
                                undoVirtually(new Location(k, l), new Location(i, j), temp);
                                if (alpha >= beta) {
                                    return myreturn;
                                }

                            }
                        }
                    }
                }
            }

        }
        return myreturn;
    }

    private boolean isStalemate(int color) {
        return isCheckmate(color);
    }
    public void exit(){
        Platform.exit();
    }

    public void howToPlay(){
        Alert howToPlayAlert = new Alert(Alert.AlertType.CONFIRMATION);
        howToPlayAlert.setTitle("How to play");
        howToPlayAlert.setHeaderText(null);
        howToPlayAlert.setContentText("Single player mode:\nMachine plays with the black player.\nWhite player starts first.\nOne click to select the piece to move, another click on the destination location to move it.\n\nMulti player mode:\nWhite player starts first.\nOne click to select the piece to move, another click on the destination location to move it.");
        ButtonType ok = ButtonType.OK;
        howToPlayAlert.getButtonTypes().setAll(ok);
        howToPlayAlert.show();
    }
}

