package logic;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Bishop extends Piece {
    public Bishop(int color, ImageView imgView) {
        super(color, imgView);
    }

    public boolean move(Board p, Location from, Location to) {
        if  (isSameColor(p,to))
            return false;
        if (Math.abs(from.getX() - to.getX()) != Math.abs(from.getY() - to.getY()))
            return false;
        if (to.getX() > from.getX() && to.getY() > from.getY()) {
            for (int i = 1; i < to.getX() - from.getX(); i++) {
                if (!p.isEmpty(new Location(from.getX() + i, from.getY() + i)))
                    return false;
            }
        }
        else if (to.getX() < from.getX() && to.getY() > from.getY()) {
            for (int i = 1; i < from.getX() - to.getX(); i++) {
                if (!p.isEmpty(new Location(from.getX() - i, from.getY() + i)))
                    return false;
            }
        }
       else if (to.getX() > from.getX() && to.getY() < from.getY()) {
            for (int i = 1; i < to.getX() - from.getX(); i++) {
                if (!p.isEmpty(new Location(from.getX() + i, from.getY() - i)))
                    return false;
            }
        }
        else {
            for (int i = 1; i < from.getX() - to.getX(); i++) {
                if (!p.isEmpty(new Location(from.getX() - i, from.getY() - i)))
                    return false;
            }
        }
        return true;
    }

}
