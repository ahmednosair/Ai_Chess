package logic;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Board {
    private Piece[][] grid;

    public Board() {
        this.grid = new Piece[8][8];
        grid[0][0] = new Rook(2,new ImageView (new Image(getClass().getResourceAsStream("piecesImgs/Black_Rook.png"))));
        grid[0][1] = new Knight(2,new ImageView(new Image(getClass().getResourceAsStream("piecesImgs/Black_Knight.png"))));
        grid[0][2] = new Bishop(2,new ImageView(new Image(getClass().getResourceAsStream("piecesImgs/Black_Bishop.png"))));
        grid[0][3] = new Queen(2,new ImageView(new Image(getClass().getResourceAsStream("piecesImgs/Black_Queen.png"))));
        grid[0][4] = new King(2,new ImageView(new Image(getClass().getResourceAsStream("piecesImgs/Black_King.png"))));
        grid[0][5] = new Bishop(2,new ImageView(new Image(getClass().getResourceAsStream("piecesImgs/Black_Bishop.png"))));
        grid[0][6] = new Knight(2,new ImageView(new Image(getClass().getResourceAsStream("piecesImgs/Black_Knight.png"))));
        grid[0][7] = new Rook(2,new ImageView(new Image(getClass().getResourceAsStream("piecesImgs/Black_Rook.png"))));
        grid[1][0] = new Pawn(2,new ImageView(new Image(getClass().getResourceAsStream("piecesImgs/Black_Pawn.png"))));
        grid[1][1] = new Pawn(2,new ImageView(new Image(getClass().getResourceAsStream("piecesImgs/Black_Pawn.png"))));
        grid[1][2] = new Pawn(2,new ImageView(new Image(getClass().getResourceAsStream("piecesImgs/Black_Pawn.png"))));
        grid[1][3] = new Pawn(2,new ImageView(new Image(getClass().getResourceAsStream("piecesImgs/Black_Pawn.png"))));
        grid[1][4] = new Pawn(2,new ImageView(new Image(getClass().getResourceAsStream("piecesImgs/Black_Pawn.png"))));
        grid[1][5] = new Pawn(2,new ImageView(new Image(getClass().getResourceAsStream("piecesImgs/Black_Pawn.png"))));
        grid[1][6] = new Pawn(2,new ImageView(new Image(getClass().getResourceAsStream("piecesImgs/Black_Pawn.png"))));
        grid[1][7] = new Pawn(2,new ImageView(new Image(getClass().getResourceAsStream("piecesImgs/Black_Pawn.png"))));
        for (int i = 2; i < 6; i++)
            for (int j = 0; j < 8; j++)
                grid[i][j] = null;
        grid[7][0] = new Rook(1,new ImageView(new Image(getClass().getResourceAsStream("piecesImgs/White_Rook.png"))));
        grid[7][1] = new Knight(1,new ImageView(new Image(getClass().getResourceAsStream("piecesImgs/White_Knight.png"))));
        grid[7][2] = new Bishop(1,new ImageView(new Image(getClass().getResourceAsStream("piecesImgs/White_Bishop.png"))));
        grid[7][3] = new Queen(1,new ImageView(new Image(getClass().getResourceAsStream("piecesImgs/White_Queen.png"))));
        grid[7][4] = new King(1,new ImageView(new Image(getClass().getResourceAsStream("piecesImgs/White_King.png"))));
        grid[7][5] = new Bishop(1,new ImageView(new Image(getClass().getResourceAsStream("piecesImgs/White_Bishop.png"))));
        grid[7][6] = new Knight(1,new ImageView(new Image(getClass().getResourceAsStream("piecesImgs/White_Knight.png"))));
        grid[7][7] = new Rook(1,new ImageView(new Image(getClass().getResourceAsStream("piecesImgs/White_Rook.png"))));
        grid[6][0] = new Pawn(1,new ImageView(new Image(getClass().getResourceAsStream("piecesImgs/White_Pawn.png"))));
        grid[6][1] = new Pawn(1,new ImageView(new Image(getClass().getResourceAsStream("piecesImgs/White_Pawn.png"))));
        grid[6][2] = new Pawn(1,new ImageView(new Image(getClass().getResourceAsStream("piecesImgs/White_Pawn.png"))));
        grid[6][3] = new Pawn(1,new ImageView(new Image(getClass().getResourceAsStream("piecesImgs/White_Pawn.png"))));
        grid[6][4] = new Pawn(1,new ImageView(new Image(getClass().getResourceAsStream("piecesImgs/White_Pawn.png"))));
        grid[6][5] = new Pawn(1,new ImageView(new Image(getClass().getResourceAsStream("piecesImgs/White_Pawn.png"))));
        grid[6][6] = new Pawn(1,new ImageView(new Image(getClass().getResourceAsStream("piecesImgs/White_Pawn.png"))));
        grid[6][7] = new Pawn(1,new ImageView(new Image(getClass().getResourceAsStream("piecesImgs/White_Pawn.png"))));

    }

    public Piece[][] getGrid() {
        return grid;
    }

    public boolean isEmpty(Location p) {
        return (this.grid)[p.getX()][p.getY()] == null;
    }
}
