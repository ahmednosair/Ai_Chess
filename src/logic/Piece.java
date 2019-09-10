package logic;

import javafx.scene.image.ImageView;

public abstract class Piece {
    public Piece(int color, ImageView imgView) {
        this.color = color;
        this.imgView = imgView;
    }

    private int color;
    public ImageView imgView;
    public abstract boolean move(Board p,Location from,Location to);

    public int getColor() {
        return color;
    }

    public ImageView getImgView() {
        return imgView;
    }
    public boolean isSameColor(Board p,Location to){
        return !p.isEmpty(to)&&p.getGrid()[to.getX()][to.getY()].getColor()==this.getColor();
    }
}
