package logic;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Rook extends Piece {
    public Rook(int color, ImageView imgView) {
        super(color, imgView);
    }

    public boolean move(Board p, Location from, Location to) {
        if (isSameColor(p, to))
            return false;
        if (!((Math.abs(to.getX() - from.getX()) > 0 && Math.abs(to.getY() - from.getY()) == 0) || (Math.abs(to.getX() - from.getX()) == 0 && Math.abs(to.getY() - from.getY()) > 0)))
        return false;
        if(to.getX() - from.getX()>0){
            for (int i = 1; i < to.getX() - from.getX(); i++) {
                if (!p.isEmpty(new Location(from.getX() + i, from.getY())))
                    return false;
            }
        }
        else if(to.getX() - from.getX()<0){
            for (int i = 1; i < from.getX() - to.getX(); i++) {
                if (!p.isEmpty(new Location(from.getX() - i, from.getY())))
                    return false;
            }
        }
        else if(to.getY() - from.getY()>0){
            for (int i = 1; i < to.getY() - from.getY(); i++) {
                if (!p.isEmpty(new Location(from.getX(), from.getY() + i)))
                    return false;
            }
        }
        else {
            for (int i = 1; i < from.getY() - to.getY(); i++) {
                if (!p.isEmpty(new Location(from.getX(), from.getY() - i)))
                    return false;
            }
        }
        return true;
    }
}
