package logic;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Pawn extends Piece {

    public Pawn(int color, ImageView imgView) {
        super(color, imgView);
    }

    private boolean isFirstMove(Location from) {
        return from.getX() == (this.getColor() == 1 ? 6 : 1);
    }

    public boolean move(Board p, Location from, Location to) {
        if (isSameColor(p, to))
            return false;
        if (this.isFirstMove(from)) {
            if (p.isEmpty(to)) {
                return (this.getColor()==1?p.isEmpty(new Location(from.getX()-1,from.getY())):p.isEmpty(new Location(from.getX()+1,from.getY())))&&((to.getX() - from.getX() == (this.getColor() == 1 ? -1 : 1) || to.getX() - from.getX() == (this.getColor() == 1 ? -2 : 2)) && (to.getY() - from.getY() == 0));
            } else {
                return (Math.abs(to.getY() - from.getY()) == 1 && (to.getX() - from.getX() == (this.getColor() == 1 ? -1 : 1)));
            }
        } else {
            if (p.isEmpty(to)) {
                return ((to.getX() - from.getX() == (this.getColor() == 1 ? -1 : 1)) && (to.getY() - from.getY() == 0));
            } else {
                return (Math.abs(to.getY() - from.getY()) == 1 && (to.getX() - from.getX() == (this.getColor() == 1 ? -1 : 1)));
            }
        }
    }
}

