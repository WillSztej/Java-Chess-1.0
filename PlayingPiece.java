public class PlayingPiece {

    private int x;
    private int y;
    private String name;
    private boolean white;
    private boolean pawn_first_move;

    public PlayingPiece(int x, int y, String name, boolean white) {
        this.x = x;
        this.y = y;
        this.name = name;
        this.white = white;
        this.pawn_first_move = false;
    }

    public PlayingPiece(int x, int y, String name, boolean white, boolean pawn_first_move) {
        this.x = x;
        this.y = y;
        this.name = name;
        this.white = white;
        this.pawn_first_move = pawn_first_move;
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

    public boolean getColor() { return this.white; }

    public boolean isPawn() { return this.pawn_first_move;}

    public void removeFirstPawnMove() { this.pawn_first_move = false; }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

}
