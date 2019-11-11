public class Panel {

    private int x;
    private int y;
    private boolean isFilled;
    private PlayingPiece piece;

    public Panel(int x, int y, boolean isFilled, PlayingPiece piece) {
        this.x = x;
        this.y = y;
        this.isFilled = isFilled;
        this.piece = piece;
    }

    public String getCoordinates() {
        return String.format("%d,%d",x,y);
    }

    public boolean isFilled() {
        return isFilled;
    }

    public String getPiece() {
        return piece.getName();
    }

}
