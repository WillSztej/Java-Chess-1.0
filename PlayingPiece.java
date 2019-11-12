public class PlayingPiece {

    private int x;
    private int y;
    private String name;
    private boolean white;

    public PlayingPiece(int x, int y, String name, boolean white) {
        this.x = x;
        this.y = y;
        this.name = name;
        this.white = white;
    }

    public String getName() {
        return this.name;
    }

    public int getRow() {
        return this.x;
    }

    public int getColumn() {
        return this.y;
    }

    public boolean getColor() {
        return white;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

}
