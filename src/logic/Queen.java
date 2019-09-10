package logic;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Queen extends Piece {
    public Queen(int color, ImageView imgView) {
        super(color, imgView);
    }

    public boolean move(Board p, Location from, Location to) {
        return new Bishop(this.getColor(),null).move(p,from,to) || new Rook(this.getColor(),null).move(p,from,to);
    }
}
