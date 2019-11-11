public class MainControl {

    public PlayingPiece[][] board;

    public static void main(String[] args) {

        PlayingPiece[][] board = new PlayingPiece[8][8];

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = null;
            }
        }

        ChessGUI gui = new ChessGUI(board);

    }

}
