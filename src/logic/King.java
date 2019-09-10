package logic;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class King extends Piece {
    public King(int color, ImageView imgView) {
        super(color, imgView);
    }

    public boolean move(Board p, Location from, Location to) {
        if (isSameColor(p, to))
            return false;
        return ((Math.abs(from.getX() - to.getX()) == 0 || Math.abs(from.getX() - to.getX()) == 1) && (Math.abs(from.getY() - to.getY()) == 0 || Math.abs(from.getY() - to.getY()) == 1));

    }
}
