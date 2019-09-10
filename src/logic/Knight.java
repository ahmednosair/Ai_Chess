package logic;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Knight extends Piece {
    public Knight(int color, ImageView imgView) {
        super(color, imgView);
    }

    public boolean move(Board p, Location from, Location to) {
        if (isSameColor(p, to))
            return false;
        return (Math.abs(from.getX() - to.getX()) == 1 && Math.abs(from.getY() - to.getY()) == 2) || (Math.abs(from.getY() - to.getY()) == 1 && Math.abs(from.getX() - to.getX()) == 2);
    }
}
