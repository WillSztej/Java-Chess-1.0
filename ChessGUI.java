import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChessGUI {

    PlayingPiece[][] pieceBoard;
    JButton[][] buttonBoard;

    private JPanel guiPanel;
    private JFrame guiFrame;

    static boolean active = false;
    static PlayingPiece curr_click;
    static int row = 0;
    static int col = 0;

    static boolean white_move = true;

    public ChessGUI(PlayingPiece[][] board) {

        PlayingPiece[][] pieceBoard = board;
        this.pieceBoard = pieceBoard;

        JButton[][] buttonBoard = new JButton[8][8];
        this.buttonBoard = buttonBoard;

        JFrame guiFrame = new JFrame("Chess Game");
        guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        guiFrame.setSize(300,300);

        JPanel guiPanel = new JPanel(new GridLayout(8,8));

        this.guiPanel = guiPanel;
        this.guiFrame = guiFrame;

        this.initializeBoard();

        guiFrame.add(guiPanel);
        guiFrame.pack();
        guiFrame.setVisible(true);


    }

    public void initializeBoard() {

        //intialize JButton 2D matrix
        //initialize PlayingPiece 2D matrix

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {

                row = i;
                col = j;

                if (!(i >= 2 && i <= 5)) {

                    boolean white = true;

                    String name = "";

                    //assign names for pawns, which take up all of row 1 and 6

                    if (i == 1) {
                        name = "whitePawn";
                        this.pieceBoard[row][col] = new PlayingPiece(i, j, name, white);
                        this.buttonBoard[row][col] = new JButton(name);
                        this.guiPanel.add(this.buttonBoard[i][j]);
                    }

                    if (i == 6) {
                        name = "blackPawn";
                        white = false;
                        this.pieceBoard[row][col] = new PlayingPiece(i, j, name, white);
                        this.buttonBoard[row][col] = new JButton(name);
                        this.guiPanel.add(this.buttonBoard[i][j]);
                    }

                    // assign names for the first row
                    if (i == 0 || i == 7) {
                        switch (j) {
                            case 0:
                            case 7:
                                if (i == 0) {
                                    name = "whiteRook";
                                    this.pieceBoard[row][col] = new PlayingPiece(i, j, name, white);
                                    this.buttonBoard[row][col] = new JButton(name);
                                    this.guiPanel.add(this.buttonBoard[i][j]);
                                } else {
                                    name = "blackRook";
                                    white = false;
                                    this.pieceBoard[row][col] = new PlayingPiece(i, j, name, white);
                                    this.buttonBoard[row][col] = new JButton(name);
                                    this.guiPanel.add(this.buttonBoard[i][j]);
                                }
                                break;
                            case 1:
                            case 6:
                                if (i == 0) {
                                    name = "whiteKnight";
                                    this.pieceBoard[row][col] = new PlayingPiece(i, j, name, white);
                                    this.buttonBoard[row][col] = new JButton(name);
                                    this.guiPanel.add(this.buttonBoard[i][j]);
                                } else {
                                    name = "blackKnight";
                                    white = false;
                                    this.pieceBoard[row][col] = new PlayingPiece(i, j, name, white);
                                    this.buttonBoard[row][col] = new JButton(name);
                                    this.guiPanel.add(this.buttonBoard[i][j]);
                                }
                                break;
                            case 2:
                            case 5:
                                if (i == 0) {
                                    name = "whiteBishop";
                                    this.pieceBoard[row][col] = new PlayingPiece(i, j, name, white);
                                    this.buttonBoard[row][col] = new JButton(name);
                                    this.guiPanel.add(this.buttonBoard[i][j]);
                                } else {
                                    name = "blackKnight";
                                    white = false;
                                    this.pieceBoard[row][col] = new PlayingPiece(i, j, name, white);
                                    this.buttonBoard[row][col] = new JButton(name);
                                    this.guiPanel.add(this.buttonBoard[i][j]);
                                }
                                break;
                            case 4:
                                if (i == 0) {
                                    name = "whiteQueen";
                                    this.pieceBoard[row][col] = new PlayingPiece(i, j, name, white);
                                    this.buttonBoard[row][col] = new JButton(name);
                                    this.guiPanel.add(this.buttonBoard[i][j]);
                                } else {
                                    name = "blackQueen";
                                    white = false;
                                    this.pieceBoard[row][col] = new PlayingPiece(i, j, name, white);
                                    this.buttonBoard[row][col] = new JButton(name);
                                    this.guiPanel.add(this.buttonBoard[i][j]);
                                }
                                break;
                            case 3:
                                if (i == 0) {
                                    name = "whiteKing";
                                    this.pieceBoard[row][col] = new PlayingPiece(i, j, name, white);
                                    this.buttonBoard[row][col] = new JButton(name);
                                    this.guiPanel.add(this.buttonBoard[i][j]);
                                } else {
                                    name = "blackKing";
                                    white = false;
                                    this.pieceBoard[row][col] = new PlayingPiece(i, j, name, white);
                                    this.buttonBoard[row][col] = new JButton(name);
                                    this.guiPanel.add(this.buttonBoard[i][j]);
                                }
                                break;
                            default:
                                break;
                        }
                    }

                } else {
                    this.pieceBoard[row][col] = null;
                    this.buttonBoard[row][col] = new JButton(" ");
                    this.guiPanel.add(this.buttonBoard[i][j]);
                }

            }
        }

        ActionListenersButtons();
    }

    private void ActionListenersButtons() {

        this.buttonBoard[0][0].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!active) {
                    if (pieceBoard[0][0] != null) {
                        // checks to see if button clicked is available to current player
                        if (pieceBoard[0][0].getColor()) {
                            if (white_move) {
                                curr_click = pieceBoard[0][0];
                                active = true;
                            }
                        }
                        if (!pieceBoard[0][0].getColor()) {
                            if (!white_move) {
                                curr_click = pieceBoard[0][0];
                                active = true;
                            }
                        }
                    }
                } else {
                    //check valid move function, return boolean, add as && in if statement
                    if (pieceBoard[0][0] == null && checkValidMove(0 ,2)) {

                        // declare int coordinates for previously clicked button
                        int prev_x = curr_click.getRow();
                        int prev_y = curr_click.getColumn();

                        // set text of previously clicked button to " "
                        buttonBoard[prev_x][prev_y].setText(" ");

                        // set text of newly clicked button to the previously clicked
                        buttonBoard[0][0].setText(curr_click.getName());

                        // set newly clicked piece object to previously clicked object in model
                        pieceBoard[0][0] = curr_click;
                        pieceBoard[0][0].setX(0);
                        pieceBoard[0][0].setY(0);

                        // set previously clicked object to null
                        pieceBoard[prev_x][prev_y] = null;

                        active = false;
                        curr_click = null;

                        System.out.printf("Previous coordinate: (%d, %d)", prev_x, prev_y);
                        System.out.printf("\nNew coordinate: (%d, %d)", pieceBoard[0][0].getRow(),
                                pieceBoard[0][0].getColumn());

                    }
                }
            }
        });

        this.buttonBoard[0][1].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (!active) {
                    if (pieceBoard[0][1] != null) {
                        if (pieceBoard[0][1].getColor()) {
                            if (white_move) {
                                curr_click = pieceBoard[0][1];
                                active = true;
                            }
                        }
                        if (!pieceBoard[0][1].getColor()) {
                            if (!white_move) {
                                curr_click = pieceBoard[0][1];
                                active = true;
                            }
                        }
                    }
                } else {
                    //check valid move function, return boolean, add as && in if statement
                    if (pieceBoard[0][1] == null && checkValidMove(0 ,1)) {
                        int prev_x = curr_click.getRow();
                        int prev_y = curr_click.getColumn();
                        buttonBoard[prev_x][prev_y].setText(" ");
                        buttonBoard[0][1].setText(curr_click.getName());
                        pieceBoard[0][1] = curr_click;
                        pieceBoard[0][1].setX(0);
                        pieceBoard[0][1].setY(1);
                        pieceBoard[prev_x][prev_y] = null;
                        active = false;
                        curr_click = null;
                    }
                }

            }

        });

        this.buttonBoard[0][2].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (!active) {
                    if (pieceBoard[0][2] != null) {
                        if (pieceBoard[0][2].getColor()) {
                            if (white_move) {
                                curr_click = pieceBoard[0][2];
                                active = true;
                            }
                        }
                        if (!pieceBoard[0][2].getColor()) {
                            if (!white_move) {
                                curr_click = pieceBoard[0][2];
                                active = true;
                            }
                        }
                    }
                } else {

                    //check valid move function, return boolean, add as && in if statement
                    if (pieceBoard[0][2] == null && checkValidMove(0 ,2)) {
                        int prev_x = curr_click.getRow();
                        int prev_y = curr_click.getColumn();
                        buttonBoard[prev_x][prev_y].setText(" ");
                        buttonBoard[0][2].setText(curr_click.getName());
                        pieceBoard[0][2] = curr_click;
                        pieceBoard[0][2].setX(0);
                        pieceBoard[0][2].setY(2);
                        pieceBoard[prev_x][prev_y] = null;
                        active = false;
                        curr_click = null;
                    }
                }

            }

        });

        this.buttonBoard[0][3].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (!active) {
                    if (pieceBoard[0][3] != null) {
                        if (pieceBoard[0][3].getColor()) {
                            if (white_move) {
                                curr_click = pieceBoard[0][3];
                                active = true;
                            }
                        }
                        if (!pieceBoard[0][3].getColor()) {
                            if (!white_move) {
                                curr_click = pieceBoard[0][3];
                                active = true;
                            }
                        }
                    }
                } else {
                    //check valid move function, return boolean, add as && in if statement
                    if (pieceBoard[0][3] == null && checkValidMove(0 ,3)) {
                        int prev_x = curr_click.getRow();
                        int prev_y = curr_click.getColumn();
                        buttonBoard[prev_x][prev_y].setText(" ");
                        buttonBoard[0][3].setText(curr_click.getName());
                        pieceBoard[0][3] = curr_click;
                        pieceBoard[0][3].setX(0);
                        pieceBoard[0][3].setY(3);
                        pieceBoard[prev_x][prev_y] = null;
                        active = false;
                        curr_click = null;
                    }
                }

            }

        });

        this.buttonBoard[0][4].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (!active) {
                    if (pieceBoard[0][4] != null) {
                        if (pieceBoard[0][4].getColor()) {
                            if (white_move) {
                                curr_click = pieceBoard[0][4];
                                active = true;
                            }
                        }
                        if (!pieceBoard[0][4].getColor()) {
                            if (!white_move) {
                                curr_click = pieceBoard[0][4];
                                active = true;
                            }
                        }
                    }
                } else {
                    //check valid move function, return boolean, add as && in if statement
                    if (pieceBoard[0][4] == null && checkValidMove(0 ,4)) {
                        int prev_x = curr_click.getRow();
                        int prev_y = curr_click.getColumn();
                        buttonBoard[prev_x][prev_y].setText(" ");
                        buttonBoard[0][4].setText(curr_click.getName());
                        pieceBoard[0][4] = curr_click;
                        pieceBoard[0][4].setX(0);
                        pieceBoard[0][4].setY(4);
                        pieceBoard[prev_x][prev_y] = null;
                        active = false;
                        curr_click = null;
                    }
                }

            }

        });

        this.buttonBoard[0][5].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (!active) {
                    if (pieceBoard[0][5] != null) {
                        if (pieceBoard[0][5].getColor()) {
                            if (white_move) {
                                curr_click = pieceBoard[0][5];
                                active = true;
                            }
                        }
                        if (!pieceBoard[0][5].getColor()) {
                            if (!white_move) {
                                curr_click = pieceBoard[0][5];
                                active = true;
                            }
                        }
                    }
                } else {
                    //check valid move function, return boolean, add as && in if statement
                    if (pieceBoard[0][5] == null && checkValidMove(0 ,5)) {
                        int prev_x = curr_click.getRow();
                        int prev_y = curr_click.getColumn();
                        buttonBoard[prev_x][prev_y].setText(" ");
                        buttonBoard[0][5].setText(curr_click.getName());
                        pieceBoard[0][5] = curr_click;
                        pieceBoard[0][5].setX(0);
                        pieceBoard[0][5].setY(5);
                        pieceBoard[prev_x][prev_y] = null;
                        active = false;
                        curr_click = null;
                    }
                }

            }

        });

        this.buttonBoard[0][6].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (!active) {
                    if (pieceBoard[0][6] != null) {
                        if (pieceBoard[0][6].getColor()) {
                            if (white_move) {
                                curr_click = pieceBoard[0][6];
                                active = true;
                            }
                        }
                        if (!pieceBoard[0][6].getColor()) {
                            if (!white_move) {
                                curr_click = pieceBoard[0][6];
                                active = true;
                            }
                        }
                    }
                } else {
                    //check valid move function, return boolean, add as && in if statement
                    if (pieceBoard[0][6] == null && checkValidMove(0 ,6)) {
                        int prev_x = curr_click.getRow();
                        int prev_y = curr_click.getColumn();
                        buttonBoard[prev_x][prev_y].setText(" ");
                        buttonBoard[0][6].setText(curr_click.getName());
                        pieceBoard[0][6] = curr_click;
                        pieceBoard[0][6].setX(0);
                        pieceBoard[0][6].setY(6);
                        pieceBoard[prev_x][prev_y] = null;
                        active = false;
                        curr_click = null;
                    }
                }

            }

        });

        this.buttonBoard[0][7].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (!active) {
                    if (pieceBoard[0][7] != null) {
                        if (pieceBoard[0][7].getColor()) {
                            if (white_move) {
                                curr_click = pieceBoard[0][7];
                                active = true;
                            }
                        }
                        if (!pieceBoard[0][7].getColor()) {
                            if (!white_move) {
                                curr_click = pieceBoard[0][7];
                                active = true;
                            }
                        }
                    }
                } else {
                    //check valid move function, return boolean, add as && in if statement
                    if (pieceBoard[0][7] == null && checkValidMove(0 ,7)) {
                        int prev_x = curr_click.getRow();
                        int prev_y = curr_click.getColumn();
                        buttonBoard[prev_x][prev_y].setText(" ");
                        buttonBoard[0][7].setText(curr_click.getName());
                        pieceBoard[0][7] = curr_click;
                        pieceBoard[0][7].setX(0);
                        pieceBoard[0][7].setY(7);
                        pieceBoard[prev_x][prev_y] = null;
                        active = false;
                        curr_click = null;
                    }
                }

            }

        });

        this.buttonBoard[1][0].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!active) {
                    if (pieceBoard[1][0] != null) {
                        if (pieceBoard[1][0].getColor()) {
                            if (white_move) {
                                curr_click = pieceBoard[1][0];
                                active = true;
                            }
                        }
                        if (!pieceBoard[1][0].getColor()) {
                            if (!white_move) {
                                curr_click = pieceBoard[1][0];
                                active = true;
                            }
                        }
                    }
                } else {
                    //check valid move function, return boolean, add as && in if statement
                    if (pieceBoard[1][0] == null && checkValidMove(1 ,0)) {
                        int prev_x = curr_click.getRow();
                        int prev_y = curr_click.getColumn();
                        buttonBoard[prev_x][prev_y].setText(" ");
                        buttonBoard[1][0].setText(curr_click.getName());
                        pieceBoard[1][0] = curr_click;
                        pieceBoard[1][0].setX(1);
                        pieceBoard[1][0].setY(0);
                        pieceBoard[prev_x][prev_y] = null;
                        active = false;
                        curr_click = null;
                    }
                }
            }
        });

        this.buttonBoard[1][1].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (!active) {
                    if (pieceBoard[1][1] != null) {
                        if (pieceBoard[1][1].getColor()) {
                            if (white_move) {
                                curr_click = pieceBoard[1][1];
                                active = true;
                            }
                        }
                        if (!pieceBoard[1][1].getColor()) {
                            if (!white_move) {
                                curr_click = pieceBoard[1][1];
                                active = true;
                            }
                        }
                    }
                } else {
                    //check valid move function, return boolean, add as && in if statement
                    if (pieceBoard[1][1] == null && checkValidMove(1 ,1)) {
                        int prev_x = curr_click.getRow();
                        int prev_y = curr_click.getColumn();
                        buttonBoard[prev_x][prev_y].setText(" ");
                        buttonBoard[1][1].setText(curr_click.getName());
                        pieceBoard[1][1] = curr_click;
                        pieceBoard[1][1].setX(1);
                        pieceBoard[1][1].setY(1);
                        pieceBoard[prev_x][prev_y] = null;
                        active = false;
                        curr_click = null;
                    }
                }

            }

        });

        this.buttonBoard[1][2].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (!active) {
                    if (pieceBoard[1][2] != null) {
                        if (pieceBoard[1][2].getColor()) {
                            if (white_move) {
                                curr_click = pieceBoard[1][2];
                                active = true;
                            }
                        }
                        if (!pieceBoard[1][2].getColor()) {
                            if (!white_move) {
                                curr_click = pieceBoard[1][2];
                                active = true;
                            }
                        }
                    }
                } else {
                    //check valid move function, return boolean, add as && in if statement
                    if (pieceBoard[1][2] == null && checkValidMove(1 ,2)) {
                        int prev_x = curr_click.getRow();
                        int prev_y = curr_click.getColumn();
                        buttonBoard[prev_x][prev_y].setText(" ");
                        buttonBoard[1][2].setText(curr_click.getName());
                        pieceBoard[1][2] = curr_click;
                        pieceBoard[1][2].setX(1);
                        pieceBoard[1][2].setY(2);
                        pieceBoard[prev_x][prev_y] = null;
                        active = false;
                        curr_click = null;
                    }
                }

            }

        });

        this.buttonBoard[1][3].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (!active) {
                    if (pieceBoard[1][3] != null) {
                        if (pieceBoard[1][3].getColor()) {
                            if (white_move) {
                                curr_click = pieceBoard[1][3];
                                active = true;
                            }
                        }
                        if (!pieceBoard[1][3].getColor()) {
                            if (!white_move) {
                                curr_click = pieceBoard[1][3];
                                active = true;
                            }
                        }
                    }
                } else {
                    //check valid move function, return boolean, add as && in if statement
                    if (pieceBoard[1][3] == null && checkValidMove(1 ,3)) {
                        int prev_x = curr_click.getRow();
                        int prev_y = curr_click.getColumn();
                        buttonBoard[prev_x][prev_y].setText(" ");
                        buttonBoard[1][3].setText(curr_click.getName());
                        pieceBoard[1][3] = curr_click;
                        pieceBoard[1][3].setX(1);
                        pieceBoard[1][3].setY(3);
                        pieceBoard[prev_x][prev_y] = null;
                        active = false;
                        curr_click = null;
                    }
                }

            }

        });

        this.buttonBoard[1][4].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                System.out.print("1, 4");

                if (!active) {
                    if (pieceBoard[1][4] != null && checkValidMove(1 ,4)) {
                        if (pieceBoard[1][4].getColor()) {
                            if (white_move) {
                                curr_click = pieceBoard[1][4];
                                active = true;
                            }
                        }
                        if (!pieceBoard[1][4].getColor()) {
                            if (!white_move) {
                                curr_click = pieceBoard[1][4];
                                active = true;
                            }
                        }
                    }
                } else {
                    //check valid move function, return boolean, add as && in if statement
                    if (pieceBoard[1][4] == null && checkValidMove(1, 4)) {
                        int prev_x = curr_click.getRow();
                        int prev_y = curr_click.getColumn();
                        buttonBoard[prev_x][prev_y].setText(" ");
                        buttonBoard[1][4].setText(curr_click.getName());
                        pieceBoard[1][4] = curr_click;
                        pieceBoard[1][4].setX(1);
                        pieceBoard[1][4].setY(4);
                        pieceBoard[prev_x][prev_y] = null;
                        active = false;
                        curr_click = null;
                    }
                }

            }

        });

        this.buttonBoard[1][5].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (!active) {
                    if (pieceBoard[1][5] != null) {
                        if (pieceBoard[1][5].getColor()) {
                            if (white_move) {
                                curr_click = pieceBoard[1][5];
                                active = true;
                            }
                        }
                        if (!pieceBoard[1][5].getColor()) {
                            if (!white_move) {
                                curr_click = pieceBoard[1][5];
                                active = true;
                            }
                        }
                    }
                } else {
                    //check valid move function, return boolean, add as && in if statement
                    if (pieceBoard[1][5] == null && checkValidMove(1 ,5)) {
                        int prev_x = curr_click.getRow();
                        int prev_y = curr_click.getColumn();
                        buttonBoard[prev_x][prev_y].setText(" ");
                        buttonBoard[1][5].setText(curr_click.getName());
                        pieceBoard[1][5] = curr_click;
                        pieceBoard[1][5].setX(1);
                        pieceBoard[1][5].setY(5);
                        pieceBoard[prev_x][prev_y] = null;
                        active = false;
                        curr_click = null;
                    }
                }

            }

        });

        this.buttonBoard[1][6].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (!active) {
                    if (pieceBoard[1][6] != null) {
                        if (pieceBoard[1][6].getColor()) {
                            if (white_move) {
                                curr_click = pieceBoard[1][6];
                                active = true;
                            }
                        }
                        if (!pieceBoard[1][6].getColor()) {
                            if (!white_move) {
                                curr_click = pieceBoard[1][6];
                                active = true;
                            }
                        }
                    }
                } else {
                    //check valid move function, return boolean, add as && in if statement
                    if (pieceBoard[1][6] == null && checkValidMove(1 ,6)) {
                        int prev_x = curr_click.getRow();
                        int prev_y = curr_click.getColumn();
                        buttonBoard[prev_x][prev_y].setText(" ");
                        buttonBoard[1][6].setText(curr_click.getName());
                        pieceBoard[1][6] = curr_click;
                        pieceBoard[1][6].setX(1);
                        pieceBoard[1][6].setY(6);
                        pieceBoard[prev_x][prev_y] = null;
                        active = false;
                        curr_click = null;
                    }
                }

            }

        });

        this.buttonBoard[1][7].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (!active) {
                    if (pieceBoard[1][7] != null) {
                        if (pieceBoard[1][7].getColor()) {
                            if (white_move) {
                                curr_click = pieceBoard[1][7];
                                active = true;
                            }
                        }
                        if (!pieceBoard[1][7].getColor()) {
                            if (!white_move) {
                                curr_click = pieceBoard[1][7];
                                active = true;
                            }
                        }
                    }
                } else {
                    //check valid move function, return boolean, add as && in if statement
                    if (pieceBoard[1][7] == null && checkValidMove(1 ,7)) {
                        int prev_x = curr_click.getRow();
                        int prev_y = curr_click.getColumn();
                        buttonBoard[prev_x][prev_y].setText(" ");
                        buttonBoard[1][7].setText(curr_click.getName());
                        pieceBoard[1][7] = curr_click;
                        pieceBoard[1][7].setX(1);
                        pieceBoard[1][7].setY(7);
                        pieceBoard[prev_x][prev_y] = null;
                        active = false;
                        curr_click = null;
                    }
                }

            }

        });

        this.buttonBoard[2][0].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!active) {
                    if (pieceBoard[2][0] != null) {
                        if (pieceBoard[2][0].getColor()) {
                            if (white_move) {
                                curr_click = pieceBoard[2][0];
                                active = true;
                            }
                        }
                        if (!pieceBoard[2][0].getColor()) {
                            if (!white_move) {
                                curr_click = pieceBoard[2][0];
                                active = true;
                            }
                        }
                    }
                } else {
                    //check valid move function, return boolean, add as && in if statement
                    if (pieceBoard[2][0] == null && checkValidMove(2 ,0)) {
                        int prev_x = curr_click.getRow();
                        int prev_y = curr_click.getColumn();
                        buttonBoard[prev_x][prev_y].setText(" ");
                        buttonBoard[2][0].setText(curr_click.getName());
                        pieceBoard[2][0] = curr_click;
                        pieceBoard[2][0].setX(2);
                        pieceBoard[2][0].setY(0);
                        pieceBoard[prev_x][prev_y] = null;
                        active = false;
                        curr_click = null;
                    }
                }
            }
        });

        this.buttonBoard[2][1].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (!active) {
                    if (pieceBoard[2][1] != null) {
                        if (pieceBoard[2][1].getColor()) {
                            if (white_move) {
                                curr_click = pieceBoard[2][1];
                                active = true;
                            }
                        }
                        if (!pieceBoard[2][1].getColor()) {
                            if (!white_move) {
                                curr_click = pieceBoard[2][1];
                                active = true;
                            }
                        }
                    }
                } else {
                    //check valid move function, return boolean, add as && in if statement
                    if (pieceBoard[2][1] == null && checkValidMove(2 ,1)) {
                        int prev_x = curr_click.getRow();
                        int prev_y = curr_click.getColumn();
                        buttonBoard[prev_x][prev_y].setText(" ");
                        buttonBoard[2][1].setText(curr_click.getName());
                        pieceBoard[2][1] = curr_click;
                        pieceBoard[2][1].setX(2);
                        pieceBoard[2][1].setY(1);
                        pieceBoard[prev_x][prev_y] = null;
                        active = false;
                        curr_click = null;
                    }
                }

            }

        });

        this.buttonBoard[2][2].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (!active) {
                    if (pieceBoard[2][2] != null) {
                        if (pieceBoard[2][2].getColor()) {
                            if (white_move) {
                                curr_click = pieceBoard[2][2];
                                active = true;
                            }
                        }
                        if (!pieceBoard[2][2].getColor()) {
                            if (!white_move) {
                                curr_click = pieceBoard[2][2];
                                active = true;
                            }
                        }
                    }
                } else {
                    //check valid move function, return boolean, add as && in if statement
                    if (pieceBoard[2][2] == null && checkValidMove(2, 2)) {
                        int prev_x = curr_click.getRow();
                        int prev_y = curr_click.getColumn();
                        buttonBoard[prev_x][prev_y].setText(" ");
                        buttonBoard[2][2].setText(curr_click.getName());
                        pieceBoard[2][2] = curr_click;
                        pieceBoard[2][2].setX(2);
                        pieceBoard[2][2].setY(2);
                        pieceBoard[prev_x][prev_y] = null;
                        active = false;
                        curr_click = null;
                    }
                }

            }

        });

        this.buttonBoard[2][3].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (!active) {
                    if (pieceBoard[2][3] != null) {
                        if (pieceBoard[2][3].getColor()) {
                            if (white_move) {
                                curr_click = pieceBoard[2][3];
                                active = true;
                            }
                        }
                        if (!pieceBoard[2][3].getColor()) {
                            if (!white_move) {
                                curr_click = pieceBoard[2][3];
                                active = true;
                            }
                        }
                    }
                } else {
                    //check valid move function, return boolean, add as && in if statement
                    if (pieceBoard[2][3] == null && checkValidMove(2, 3)) {
                        int prev_x = curr_click.getRow();
                        int prev_y = curr_click.getColumn();
                        buttonBoard[prev_x][prev_y].setText(" ");
                        buttonBoard[2][3].setText(curr_click.getName());
                        pieceBoard[2][3] = curr_click;
                        pieceBoard[2][3].setX(2);
                        pieceBoard[2][3].setY(3);
                        pieceBoard[prev_x][prev_y] = null;
                        active = false;
                        curr_click = null;
                    }
                }

            }

        });

        this.buttonBoard[2][4].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (!active) {
                    if (pieceBoard[2][4] != null) {
                        if (pieceBoard[2][4].getColor()) {
                            if (white_move) {
                                curr_click = pieceBoard[2][4];
                                active = true;
                            }
                        }
                        if (!pieceBoard[2][4].getColor()) {
                            if (!white_move) {
                                curr_click = pieceBoard[2][4];
                                active = true;
                            }
                        }
                    }
                } else {
                    //check valid move function, return boolean, add as && in if statement
                    if (pieceBoard[2][4] == null && checkValidMove(2, 4)) {
                        int prev_x = curr_click.getRow();
                        int prev_y = curr_click.getColumn();
                        buttonBoard[prev_x][prev_y].setText(" ");
                        buttonBoard[2][4].setText(curr_click.getName());
                        pieceBoard[2][4] = curr_click;
                        pieceBoard[2][4].setX(2);
                        pieceBoard[2][4].setY(4);
                        pieceBoard[prev_x][prev_y] = null;
                        active = false;
                        curr_click = null;
                    }
                }

            }

        });

        this.buttonBoard[2][5].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (!active) {
                    if (pieceBoard[2][5] != null) {
                        if (pieceBoard[2][5].getColor()) {
                            if (white_move) {
                                curr_click = pieceBoard[2][5];
                                active = true;
                            }
                        }
                        if (!pieceBoard[2][5].getColor()) {
                            if (!white_move) {
                                curr_click = pieceBoard[2][5];
                                active = true;
                            }
                        }
                    }
                } else {
                    //check valid move function, return boolean, add as && in if statement
                    if (pieceBoard[2][5] == null && checkValidMove(2, 5)) {
                        int prev_x = curr_click.getRow();
                        int prev_y = curr_click.getColumn();
                        buttonBoard[prev_x][prev_y].setText(" ");
                        buttonBoard[2][5].setText(curr_click.getName());
                        pieceBoard[2][5] = curr_click;
                        pieceBoard[2][5].setX(2);
                        pieceBoard[2][5].setY(5);
                        pieceBoard[prev_x][prev_y] = null;
                        active = false;
                        curr_click = null;
                    }
                }

            }

        });

        this.buttonBoard[2][6].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (!active) {
                    if (pieceBoard[2][6] != null) {
                        if (pieceBoard[2][6].getColor()) {
                            if (white_move) {
                                curr_click = pieceBoard[2][6];
                                active = true;
                            }
                        }
                        if (!pieceBoard[2][6].getColor()) {
                            if (!white_move) {
                                curr_click = pieceBoard[2][6];
                                active = true;
                            }
                        }
                    }
                } else {
                    //check valid move function, return boolean, add as && in if statement
                    if (pieceBoard[2][6] == null && checkValidMove(2, 6)) {
                        int prev_x = curr_click.getRow();
                        int prev_y = curr_click.getColumn();
                        buttonBoard[prev_x][prev_y].setText(" ");
                        buttonBoard[2][6].setText(curr_click.getName());
                        pieceBoard[2][6] = curr_click;
                        pieceBoard[2][6].setX(2);
                        pieceBoard[2][6].setY(6);
                        pieceBoard[prev_x][prev_y] = null;
                        active = false;
                        curr_click = null;
                    }
                }

            }

        });

        this.buttonBoard[2][7].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (!active) {
                    if (pieceBoard[2][7] != null) {
                        if (pieceBoard[2][7].getColor()) {
                            if (white_move) {
                                curr_click = pieceBoard[2][7];
                                active = true;
                            }
                        }
                        if (!pieceBoard[2][7].getColor()) {
                            if (!white_move) {
                                curr_click = pieceBoard[2][7];
                                active = true;
                            }
                        }
                    }
                } else {
                    //check valid move function, return boolean, add as && in if statement
                    if (pieceBoard[2][7] == null && checkValidMove(2 ,7)) {
                        int prev_x = curr_click.getRow();
                        int prev_y = curr_click.getColumn();
                        buttonBoard[prev_x][prev_y].setText(" ");
                        buttonBoard[2][7].setText(curr_click.getName());
                        pieceBoard[2][7] = curr_click;
                        pieceBoard[2][7].setX(2);
                        pieceBoard[2][7].setY(7);
                        pieceBoard[prev_x][prev_y] = null;
                        active = false;
                        curr_click = null;
                    }
                }

            }

        });

        this.buttonBoard[3][0].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!active) {
                    if (pieceBoard[3][0] != null) {
                        if (pieceBoard[3][0].getColor()) {
                            if (white_move) {
                                curr_click = pieceBoard[3][0];
                                active = true;
                            }
                        }
                        if (!pieceBoard[3][0].getColor()) {
                            if (!white_move) {
                                curr_click = pieceBoard[3][0];
                                active = true;
                            }
                        }
                    }
                } else {
                    //check valid move function, return boolean, add as && in if statement
                    if (pieceBoard[3][0] == null && checkValidMove(3 ,0)) {
                        int prev_x = curr_click.getRow();
                        int prev_y = curr_click.getColumn();
                        buttonBoard[prev_x][prev_y].setText(" ");
                        buttonBoard[3][0].setText(curr_click.getName());
                        pieceBoard[3][0] = curr_click;
                        pieceBoard[3][0].setX(3);
                        pieceBoard[3][0].setY(0);
                        pieceBoard[prev_x][prev_y] = null;
                        active = false;
                        curr_click = null;
                    }
                }
            }
        });

        this.buttonBoard[3][1].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (!active) {
                    if (pieceBoard[3][1] != null) {
                        if (pieceBoard[3][1].getColor()) {
                            if (white_move) {
                                curr_click = pieceBoard[3][1];
                                active = true;
                            }
                        }
                        if (!pieceBoard[3][1].getColor()) {
                            if (!white_move) {
                                curr_click = pieceBoard[3][1];
                                active = true;
                            }
                        }
                    }
                } else {
                    //check valid move function, return boolean, add as && in if statement
                    if (pieceBoard[3][1] == null && checkValidMove(3 ,1)) {
                        int prev_x = curr_click.getRow();
                        int prev_y = curr_click.getColumn();
                        buttonBoard[prev_x][prev_y].setText(" ");
                        buttonBoard[3][1].setText(curr_click.getName());
                        pieceBoard[3][1] = curr_click;
                        pieceBoard[3][1].setX(3);
                        pieceBoard[3][1].setY(1);
                        pieceBoard[prev_x][prev_y] = null;
                        active = false;
                        curr_click = null;
                    }
                }

            }

        });

        this.buttonBoard[3][2].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (!active) {
                    if (pieceBoard[3][2] != null) {
                        if (pieceBoard[3][2].getColor()) {
                            if (white_move) {
                                curr_click = pieceBoard[3][2];
                                active = true;
                            }
                        }
                        if (!pieceBoard[3][2].getColor()) {
                            if (!white_move) {
                                curr_click = pieceBoard[3][2];
                                active = true;
                            }
                        }
                    }
                } else {
                    //check valid move function, return boolean, add as && in if statement
                    if (pieceBoard[3][2] == null && checkValidMove(3 ,2)) {
                        int prev_x = curr_click.getRow();
                        int prev_y = curr_click.getColumn();
                        buttonBoard[prev_x][prev_y].setText(" ");
                        buttonBoard[3][2].setText(curr_click.getName());
                        pieceBoard[3][2] = curr_click;
                        pieceBoard[3][2].setX(3);
                        pieceBoard[3][2].setY(2);
                        pieceBoard[prev_x][prev_y] = null;
                        active = false;
                        curr_click = null;
                    }
                }

            }

        });

        this.buttonBoard[3][3].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (!active) {
                    if (pieceBoard[3][3] != null) {
                        if (pieceBoard[3][3].getColor()) {
                            if (white_move) {
                                curr_click = pieceBoard[3][3];
                                active = true;
                            }
                        }
                        if (!pieceBoard[3][3].getColor()) {
                            if (!white_move) {
                                curr_click = pieceBoard[3][3];
                                active = true;
                            }
                        }
                    }
                } else {
                    //check valid move function, return boolean, add as && in if statement
                    if (pieceBoard[3][3] == null && checkValidMove(3, 3)) {
                        int prev_x = curr_click.getRow();
                        int prev_y = curr_click.getColumn();
                        buttonBoard[prev_x][prev_y].setText(" ");
                        buttonBoard[3][3].setText(curr_click.getName());
                        pieceBoard[3][3] = curr_click;
                        pieceBoard[3][3].setX(3);
                        pieceBoard[3][3].setY(3);
                        pieceBoard[prev_x][prev_y] = null;
                        active = false;
                        curr_click = null;
                    }
                }

            }

        });

        this.buttonBoard[3][4].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (!active) {
                    if (pieceBoard[3][4] != null) {
                        if (pieceBoard[3][4].getColor()) {
                            if (white_move) {
                                curr_click = pieceBoard[3][4];
                                active = true;
                            }
                        }
                        if (!pieceBoard[3][4].getColor()) {
                            if (!white_move) {
                                curr_click = pieceBoard[3][4];
                                active = true;
                            }
                        }
                    }
                } else {
                    //check valid move function, return boolean, add as && in if statement
                    if (pieceBoard[3][4] == null && checkValidMove(3, 4)) {
                        int prev_x = curr_click.getRow();
                        int prev_y = curr_click.getColumn();
                        buttonBoard[prev_x][prev_y].setText(" ");
                        buttonBoard[3][4].setText(curr_click.getName());
                        pieceBoard[3][4] = curr_click;
                        pieceBoard[3][4].setX(3);
                        pieceBoard[3][4].setY(4);
                        pieceBoard[prev_x][prev_y] = null;
                        active = false;
                        curr_click = null;
                    }
                }

            }

        });

        this.buttonBoard[3][5].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (!active) {
                    if (pieceBoard[3][5] != null) {
                        if (pieceBoard[3][5].getColor()) {
                            if (white_move) {
                                curr_click = pieceBoard[3][5];
                                active = true;
                            }
                        }
                        if (!pieceBoard[3][5].getColor()) {
                            if (!white_move) {
                                curr_click = pieceBoard[3][5];
                                active = true;
                            }
                        }
                    }
                } else {
                    //check valid move function, return boolean, add as && in if statement
                    if (pieceBoard[3][5] == null && checkValidMove(3, 5)) {
                        int prev_x = curr_click.getRow();
                        int prev_y = curr_click.getColumn();
                        buttonBoard[prev_x][prev_y].setText(" ");
                        buttonBoard[3][5].setText(curr_click.getName());
                        pieceBoard[3][5] = curr_click;
                        pieceBoard[3][5].setX(3);
                        pieceBoard[3][5].setY(5);
                        pieceBoard[prev_x][prev_y] = null;
                        active = false;
                        curr_click = null;
                    }
                }

            }

        });

        this.buttonBoard[3][6].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (!active) {
                    if (pieceBoard[3][6] != null) {
                        if (pieceBoard[3][6].getColor()) {
                            if (white_move) {
                                curr_click = pieceBoard[3][6];
                                active = true;
                            }
                        }
                        if (!pieceBoard[3][6].getColor()) {
                            if (!white_move) {
                                curr_click = pieceBoard[3][6];
                                active = true;
                            }
                        }
                    }
                } else {
                    //check valid move function, return boolean, add as && in if statement
                    if (pieceBoard[3][6] == null && checkValidMove(3 ,6)) {
                        int prev_x = curr_click.getRow();
                        int prev_y = curr_click.getColumn();
                        buttonBoard[prev_x][prev_y].setText(" ");
                        buttonBoard[3][6].setText(curr_click.getName());
                        pieceBoard[3][6] = curr_click;
                        pieceBoard[3][6].setX(3);
                        pieceBoard[3][6].setY(6);
                        pieceBoard[prev_x][prev_y] = null;
                        active = false;
                        curr_click = null;
                    }
                }

            }

        });

        this.buttonBoard[3][7].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (!active) {
                    if (pieceBoard[3][7] != null) {
                        if (pieceBoard[3][7].getColor()) {
                            if (white_move) {
                                curr_click = pieceBoard[3][7];
                                active = true;
                            }
                        }
                        if (!pieceBoard[3][7].getColor()) {
                            if (!white_move) {
                                curr_click = pieceBoard[3][7];
                                active = true;
                            }
                        }
                    }
                } else {
                    //check valid move function, return boolean, add as && in if statement
                    if (pieceBoard[3][7] == null && checkValidMove(3 ,7)) {
                        int prev_x = curr_click.getRow();
                        int prev_y = curr_click.getColumn();
                        buttonBoard[prev_x][prev_y].setText(" ");
                        buttonBoard[3][7].setText(curr_click.getName());
                        pieceBoard[3][7] = curr_click;
                        pieceBoard[3][7].setX(3);
                        pieceBoard[3][7].setY(7);
                        pieceBoard[prev_x][prev_y] = null;
                        active = false;
                        curr_click = null;
                    }
                }

            }

        });

        this.buttonBoard[4][0].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!active) {
                    if (pieceBoard[4][0] != null) {
                        if (pieceBoard[4][0].getColor()) {
                            if (white_move) {
                                curr_click = pieceBoard[4][0];
                                active = true;
                            }
                        }
                        if (!pieceBoard[4][0].getColor()) {
                            if (!white_move) {
                                curr_click = pieceBoard[4][0];
                                active = true;
                            }
                        }
                    }
                } else {
                    //check valid move function, return boolean, add as && in if statement
                    if (pieceBoard[4][0] == null && checkValidMove(4 ,0)) {
                        int prev_x = curr_click.getRow();
                        int prev_y = curr_click.getColumn();
                        buttonBoard[prev_x][prev_y].setText(" ");
                        buttonBoard[4][0].setText(curr_click.getName());
                        pieceBoard[4][0] = curr_click;
                        pieceBoard[4][0].setX(4);
                        pieceBoard[4][0].setY(0);
                        pieceBoard[prev_x][prev_y] = null;
                        active = false;
                        curr_click = null;
                    }
                }
            }
        });

        this.buttonBoard[4][1].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (!active) {
                    if (pieceBoard[4][1] != null) {
                        if (pieceBoard[4][1].getColor()) {
                            if (white_move) {
                                curr_click = pieceBoard[4][1];
                                active = true;
                            }
                        }
                        if (!pieceBoard[4][1].getColor()) {
                            if (!white_move) {
                                curr_click = pieceBoard[4][1];
                                active = true;
                            }
                        }
                    }
                } else {
                    //check valid move function, return boolean, add as && in if statement
                    if (pieceBoard[4][1] == null && checkValidMove(4 ,1)) {
                        int prev_x = curr_click.getRow();
                        int prev_y = curr_click.getColumn();
                        buttonBoard[prev_x][prev_y].setText(" ");
                        buttonBoard[4][1].setText(curr_click.getName());
                        pieceBoard[4][1] = curr_click;
                        pieceBoard[4][1].setX(4);
                        pieceBoard[4][1].setY(1);
                        pieceBoard[prev_x][prev_y] = null;
                        active = false;
                        curr_click = null;
                    }
                }

            }

        });

        this.buttonBoard[4][2].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (!active) {
                    if (pieceBoard[4][2] != null) {
                        if (pieceBoard[4][2].getColor()) {
                            if (white_move) {
                                curr_click = pieceBoard[4][2];
                                active = true;
                            }
                        }
                        if (!pieceBoard[4][2].getColor()) {
                            if (!white_move) {
                                curr_click = pieceBoard[4][2];
                                active = true;
                            }
                        }
                    }
                } else {
                    //check valid move function, return boolean, add as && in if statement
                    if (pieceBoard[4][2] == null && checkValidMove(4 ,2)) {
                        int prev_x = curr_click.getRow();
                        int prev_y = curr_click.getColumn();
                        buttonBoard[prev_x][prev_y].setText(" ");
                        buttonBoard[4][2].setText(curr_click.getName());
                        pieceBoard[4][2] = curr_click;
                        pieceBoard[4][2].setX(4);
                        pieceBoard[4][2].setY(2);
                        pieceBoard[prev_x][prev_y] = null;
                        active = false;
                        curr_click = null;
                    }
                }

            }

        });

        this.buttonBoard[4][3].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (!active) {
                    if (pieceBoard[4][3] != null) {
                        if (pieceBoard[4][3].getColor()) {
                            if (white_move) {
                                curr_click = pieceBoard[4][3];
                                active = true;
                            }
                        }
                        if (!pieceBoard[4][3].getColor()) {
                            if (!white_move) {
                                curr_click = pieceBoard[4][3];
                                active = true;
                            }
                        }
                    }
                } else {
                    //check valid move function, return boolean, add as && in if statement
                    if (pieceBoard[4][3] == null && checkValidMove(4 ,3 )) {
                        int prev_x = curr_click.getRow();
                        int prev_y = curr_click.getColumn();
                        buttonBoard[prev_x][prev_y].setText(" ");
                        buttonBoard[4][3].setText(curr_click.getName());
                        pieceBoard[4][3] = curr_click;
                        pieceBoard[4][3].setX(4);
                        pieceBoard[4][3].setY(3);
                        pieceBoard[prev_x][prev_y] = null;
                        active = false;
                        curr_click = null;
                    }
                }

            }

        });

        this.buttonBoard[4][4].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (!active) {
                    if (pieceBoard[4][4] != null) {
                        if (pieceBoard[4][4].getColor()) {
                            if (white_move) {
                                curr_click = pieceBoard[4][4];
                                active = true;
                            }
                        }
                        if (!pieceBoard[4][4].getColor()) {
                            if (!white_move) {
                                curr_click = pieceBoard[4][4];
                                active = true;
                            }
                        }
                    }
                } else {
                    //check valid move function, return boolean, add as && in if statement
                    if (pieceBoard[4][4] == null && checkValidMove(4 ,4)) {
                        int prev_x = curr_click.getRow();
                        int prev_y = curr_click.getColumn();
                        buttonBoard[prev_x][prev_y].setText(" ");
                        buttonBoard[4][4].setText(curr_click.getName());
                        pieceBoard[4][4] = curr_click;
                        pieceBoard[4][4].setX(4);
                        pieceBoard[4][4].setY(4);
                        pieceBoard[prev_x][prev_y] = null;
                        active = false;
                        curr_click = null;
                    }
                }

            }

        });

        this.buttonBoard[4][5].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (!active) {
                    if (pieceBoard[4][5] != null) {
                        if (pieceBoard[4][5].getColor()) {
                            if (white_move) {
                                curr_click = pieceBoard[4][5];
                                active = true;
                            }
                        }
                        if (!pieceBoard[4][5].getColor()) {
                            if (!white_move) {
                                curr_click = pieceBoard[4][5];
                                active = true;
                            }
                        }
                    }
                } else {
                    //check valid move function, return boolean, add as && in if statement
                    if (pieceBoard[4][5] == null && checkValidMove(4 ,5)) {
                        int prev_x = curr_click.getRow();
                        int prev_y = curr_click.getColumn();
                        buttonBoard[prev_x][prev_y].setText(" ");
                        buttonBoard[4][5].setText(curr_click.getName());
                        pieceBoard[4][5] = curr_click;
                        pieceBoard[4][5].setX(4);
                        pieceBoard[4][5].setY(5);
                        pieceBoard[prev_x][prev_y] = null;
                        active = false;
                        curr_click = null;
                    }
                }

            }

        });

        this.buttonBoard[4][6].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (!active) {
                    if (pieceBoard[4][6] != null) {
                        if (pieceBoard[4][6].getColor()) {
                            if (white_move) {
                                curr_click = pieceBoard[4][6];
                                active = true;
                            }
                        }
                        if (!pieceBoard[4][6].getColor()) {
                            if (!white_move) {
                                curr_click = pieceBoard[4][6];
                                active = true;
                            }
                        }
                    }
                } else {
                    //check valid move function, return boolean, add as && in if statement
                    if (pieceBoard[4][6] == null && checkValidMove(4 ,6)) {
                        int prev_x = curr_click.getRow();
                        int prev_y = curr_click.getColumn();
                        buttonBoard[prev_x][prev_y].setText(" ");
                        buttonBoard[4][6].setText(curr_click.getName());
                        pieceBoard[4][6] = curr_click;
                        pieceBoard[4][6].setX(4);
                        pieceBoard[4][6].setY(6);
                        pieceBoard[prev_x][prev_y] = null;
                        active = false;
                        curr_click = null;
                    }
                }

            }

        });

        this.buttonBoard[4][7].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (!active) {
                    if (pieceBoard[4][7] != null) {
                        if (pieceBoard[4][7].getColor()) {
                            if (white_move) {
                                curr_click = pieceBoard[4][7];
                                active = true;
                            }
                        }
                        if (!pieceBoard[4][7].getColor()) {
                            if (!white_move) {
                                curr_click = pieceBoard[4][7];
                                active = true;
                            }
                        }
                    }
                } else {
                    //check valid move function, return boolean, add as && in if statement
                    if (pieceBoard[4][7] == null && checkValidMove(4 ,7)) {
                        int prev_x = curr_click.getRow();
                        int prev_y = curr_click.getColumn();
                        buttonBoard[prev_x][prev_y].setText(" ");
                        buttonBoard[4][7].setText(curr_click.getName());
                        pieceBoard[4][7] = curr_click;
                        pieceBoard[4][7].setX(4);
                        pieceBoard[4][7].setY(7);
                        pieceBoard[prev_x][prev_y] = null;
                        active = false;
                        curr_click = null;
                    }
                }

            }

        });

        this.buttonBoard[5][0].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!active) {
                    if (pieceBoard[5][0] != null) {
                        if (pieceBoard[5][0].getColor()) {
                            if (white_move) {
                                curr_click = pieceBoard[5][0];
                                active = true;
                            }
                        }
                        if (!pieceBoard[5][0].getColor()) {
                            if (!white_move) {
                                curr_click = pieceBoard[5][0];
                                active = true;
                            }
                        }
                    }
                } else {
                    //check valid move function, return boolean, add as && in if statement
                    if (pieceBoard[5][0] == null && checkValidMove(5 ,0)) {
                        int prev_x = curr_click.getRow();
                        int prev_y = curr_click.getColumn();
                        buttonBoard[prev_x][prev_y].setText(" ");
                        buttonBoard[5][0].setText(curr_click.getName());
                        pieceBoard[5][0] = curr_click;
                        pieceBoard[5][0].setX(5);
                        pieceBoard[5][0].setY(0);
                        pieceBoard[prev_x][prev_y] = null;
                        active = false;
                        curr_click = null;
                    }
                }
            }
        });

        this.buttonBoard[5][1].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (!active) {
                    if (pieceBoard[5][1] != null) {
                        if (pieceBoard[5][1].getColor()) {
                            if (white_move) {
                                curr_click = pieceBoard[5][1];
                                active = true;
                            }
                        }
                        if (!pieceBoard[5][1].getColor()) {
                            if (!white_move) {
                                curr_click = pieceBoard[5][1];
                                active = true;
                            }
                        }
                    }
                } else {
                    //check valid move function, return boolean, add as && in if statement
                    if (pieceBoard[5][1] == null && checkValidMove(5 ,1)) {
                        int prev_x = curr_click.getRow();
                        int prev_y = curr_click.getColumn();
                        buttonBoard[prev_x][prev_y].setText(" ");
                        buttonBoard[5][1].setText(curr_click.getName());
                        pieceBoard[5][1] = curr_click;
                        pieceBoard[5][1].setX(5);
                        pieceBoard[5][1].setY(1);
                        pieceBoard[prev_x][prev_y] = null;
                        active = false;
                        curr_click = null;
                    }
                }

            }

        });

        this.buttonBoard[5][2].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (!active) {
                    if (pieceBoard[5][2] != null) {
                        if (pieceBoard[5][2].getColor()) {
                            if (white_move) {
                                curr_click = pieceBoard[5][2];
                                active = true;
                            }
                        }
                        if (!pieceBoard[5][2].getColor()) {
                            if (!white_move) {
                                curr_click = pieceBoard[5][2];
                                active = true;
                            }
                        }
                    }
                } else {
                    //check valid move function, return boolean, add as && in if statement
                    if (pieceBoard[5][2] == null && checkValidMove(5 ,2)) {
                        int prev_x = curr_click.getRow();
                        int prev_y = curr_click.getColumn();
                        buttonBoard[prev_x][prev_y].setText(" ");
                        buttonBoard[5][2].setText(curr_click.getName());
                        pieceBoard[5][2] = curr_click;
                        pieceBoard[5][2].setX(5);
                        pieceBoard[5][2].setY(2);
                        pieceBoard[prev_x][prev_y] = null;
                        active = false;
                        curr_click = null;
                    }
                }

            }

        });

        this.buttonBoard[5][3].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (!active) {
                    if (pieceBoard[5][3] != null) {
                        if (pieceBoard[5][3].getColor()) {
                            if (white_move) {
                                curr_click = pieceBoard[5][3];
                                active = true;
                            }
                        }
                        if (!pieceBoard[5][3].getColor()) {
                            if (!white_move) {
                                curr_click = pieceBoard[5][3];
                                active = true;
                            }
                        }
                    }
                } else {
                    //check valid move function, return boolean, add as && in if statement
                    if (pieceBoard[5][3] == null && checkValidMove(5 ,3)) {
                        int prev_x = curr_click.getRow();
                        int prev_y = curr_click.getColumn();
                        buttonBoard[prev_x][prev_y].setText(" ");
                        buttonBoard[5][3].setText(curr_click.getName());
                        pieceBoard[5][3] = curr_click;
                        pieceBoard[5][3].setX(5);
                        pieceBoard[5][3].setY(3);
                        pieceBoard[prev_x][prev_y] = null;
                        active = false;
                        curr_click = null;
                    }
                }

            }

        });

        this.buttonBoard[5][4].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (!active) {
                    if (pieceBoard[5][4] != null) {
                        if (pieceBoard[5][4].getColor()) {
                            if (white_move) {
                                curr_click = pieceBoard[5][4];
                                active = true;
                            }
                        }
                        if (!pieceBoard[5][4].getColor()) {
                            if (!white_move) {
                                curr_click = pieceBoard[5][4];
                                active = true;
                            }
                        }
                    }
                } else {
                    //check valid move function, return boolean, add as && in if statement
                    if (pieceBoard[5][4] == null && checkValidMove(5 ,4)) {
                        int prev_x = curr_click.getRow();
                        int prev_y = curr_click.getColumn();
                        buttonBoard[prev_x][prev_y].setText(" ");
                        buttonBoard[5][4].setText(curr_click.getName());
                        pieceBoard[5][4] = curr_click;
                        pieceBoard[5][4].setX(5);
                        pieceBoard[5][4].setY(4);
                        pieceBoard[prev_x][prev_y] = null;
                        active = false;
                        curr_click = null;
                    }
                }

            }

        });

        this.buttonBoard[5][5].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (!active) {
                    if (pieceBoard[5][5] != null) {
                        if (pieceBoard[5][5].getColor()) {
                            if (white_move) {
                                curr_click = pieceBoard[5][5];
                                active = true;
                            }
                        }
                        if (!pieceBoard[5][5].getColor()) {
                            if (!white_move) {
                                curr_click = pieceBoard[5][5];
                                active = true;
                            }
                        }
                    }
                } else {
                    //check valid move function, return boolean, add as && in if statement
                    if (pieceBoard[5][5] == null && checkValidMove(5 ,5)) {
                        int prev_x = curr_click.getRow();
                        int prev_y = curr_click.getColumn();
                        buttonBoard[prev_x][prev_y].setText(" ");
                        buttonBoard[5][5].setText(curr_click.getName());
                        pieceBoard[5][5] = curr_click;
                        pieceBoard[5][5].setX(5);
                        pieceBoard[5][5].setY(5);
                        pieceBoard[prev_x][prev_y] = null;
                        active = false;
                        curr_click = null;
                    }
                }

            }

        });

        this.buttonBoard[5][6].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (!active) {
                    if (pieceBoard[5][6] != null) {
                        if (pieceBoard[5][6].getColor()) {
                            if (white_move) {
                                curr_click = pieceBoard[5][6];
                                active = true;
                            }
                        }
                        if (!pieceBoard[5][6].getColor()) {
                            if (!white_move) {
                                curr_click = pieceBoard[5][6];
                                active = true;
                            }
                        }
                    }
                } else {
                    //check valid move function, return boolean, add as && in if statement
                    if (pieceBoard[5][6] == null && checkValidMove(5 ,6)) {
                        int prev_x = curr_click.getRow();
                        int prev_y = curr_click.getColumn();
                        buttonBoard[prev_x][prev_y].setText(" ");
                        buttonBoard[5][6].setText(curr_click.getName());
                        pieceBoard[5][6] = curr_click;
                        pieceBoard[5][6].setX(5);
                        pieceBoard[5][6].setY(6);
                        pieceBoard[prev_x][prev_y] = null;
                        active = false;
                        curr_click = null;
                    }
                }

            }

        });

        this.buttonBoard[5][7].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (!active) {
                    if (pieceBoard[5][7] != null) {
                        if (pieceBoard[5][7].getColor()) {
                            if (white_move) {
                                curr_click = pieceBoard[5][7];
                                active = true;
                            }
                        }
                        if (!pieceBoard[5][7].getColor()) {
                            if (!white_move) {
                                curr_click = pieceBoard[5][7];
                                active = true;
                            }
                        }
                    }
                } else {
                    //check valid move function, return boolean, add as && in if statement
                    if (pieceBoard[5][7] == null && checkValidMove(5 ,7)) {
                        int prev_x = curr_click.getRow();
                        int prev_y = curr_click.getColumn();
                        buttonBoard[prev_x][prev_y].setText(" ");
                        buttonBoard[5][7].setText(curr_click.getName());
                        pieceBoard[5][7] = curr_click;
                        pieceBoard[5][7].setX(5);
                        pieceBoard[5][7].setY(7);
                        pieceBoard[prev_x][prev_y] = null;
                        active = false;
                        curr_click = null;
                    }
                }

            }

        });

        this.buttonBoard[6][0].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!active) {
                    if (pieceBoard[6][0] != null) {
                        if (pieceBoard[6][0].getColor()) {
                            if (white_move) {
                                curr_click = pieceBoard[6][0];
                                active = true;
                            }
                        }
                        if (!pieceBoard[6][0].getColor()) {
                            if (!white_move) {
                                curr_click = pieceBoard[6][0];
                                active = true;
                            }
                        }
                    }
                } else {
                    //check valid move function, return boolean, add as && in if statement
                    if (pieceBoard[6][0] == null && checkValidMove(6 ,0)) {
                        int prev_x = curr_click.getRow();
                        int prev_y = curr_click.getColumn();
                        buttonBoard[prev_x][prev_y].setText(" ");
                        buttonBoard[6][0].setText(curr_click.getName());
                        pieceBoard[6][0] = curr_click;
                        pieceBoard[6][0].setX(6);
                        pieceBoard[6][0].setY(0);
                        pieceBoard[prev_x][prev_y] = null;
                        active = false;
                        curr_click = null;
                    }
                }
            }
        });

        this.buttonBoard[6][1].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (!active) {
                    if (pieceBoard[6][1] != null) {
                        if (pieceBoard[6][1].getColor()) {
                            if (white_move) {
                                curr_click = pieceBoard[6][1];
                                active = true;
                            }
                        }
                        if (!pieceBoard[6][1].getColor()) {
                            if (!white_move) {
                                curr_click = pieceBoard[6][1];
                                active = true;
                            }
                        }
                    }
                } else {
                    //check valid move function, return boolean, add as && in if statement
                    if (pieceBoard[6][1] == null && checkValidMove(6 ,1)) {
                        int prev_x = curr_click.getRow();
                        int prev_y = curr_click.getColumn();
                        buttonBoard[prev_x][prev_y].setText(" ");
                        buttonBoard[6][1].setText(curr_click.getName());
                        pieceBoard[6][1] = curr_click;
                        pieceBoard[6][1].setX(6);
                        pieceBoard[6][1].setY(1);
                        pieceBoard[prev_x][prev_y] = null;
                        active = false;
                        curr_click = null;
                    }
                }

            }

        });

        this.buttonBoard[6][2].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (!active) {
                    if (pieceBoard[6][2] != null) {
                        if (pieceBoard[6][2].getColor()) {
                            if (white_move) {
                                curr_click = pieceBoard[6][2];
                                active = true;
                            }
                        }
                        if (!pieceBoard[6][2].getColor()) {
                            if (!white_move) {
                                curr_click = pieceBoard[6][2];
                                active = true;
                            }
                        }
                    }
                } else {
                    //check valid move function, return boolean, add as && in if statement
                    if (pieceBoard[6][2] == null && checkValidMove(6 ,2)) {
                        int prev_x = curr_click.getRow();
                        int prev_y = curr_click.getColumn();
                        buttonBoard[prev_x][prev_y].setText(" ");
                        buttonBoard[6][2].setText(curr_click.getName());
                        pieceBoard[6][2] = curr_click;
                        pieceBoard[6][2].setX(6);
                        pieceBoard[6][2].setY(2);
                        pieceBoard[prev_x][prev_y] = null;
                        active = false;
                        curr_click = null;
                    }
                }

            }

        });

        this.buttonBoard[6][3].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (!active) {
                    if (pieceBoard[6][3] != null) {
                        if (pieceBoard[6][3].getColor()) {
                            if (white_move) {
                                curr_click = pieceBoard[6][3];
                                active = true;
                            }
                        }
                        if (!pieceBoard[6][3].getColor()) {
                            if (!white_move) {
                                curr_click = pieceBoard[6][3];
                                active = true;
                            }
                        }
                    }
                } else {
                    //check valid move function, return boolean, add as && in if statement
                    if (pieceBoard[6][3] == null && checkValidMove(6 ,3)) {
                        int prev_x = curr_click.getRow();
                        int prev_y = curr_click.getColumn();
                        buttonBoard[prev_x][prev_y].setText(" ");
                        buttonBoard[6][3].setText(curr_click.getName());
                        pieceBoard[6][3] = curr_click;
                        pieceBoard[6][3].setX(6);
                        pieceBoard[6][3].setY(3);
                        pieceBoard[prev_x][prev_y] = null;
                        active = false;
                        curr_click = null;
                    }
                }

            }

        });

        this.buttonBoard[6][4].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (!active) {
                    if (pieceBoard[6][4] != null) {
                        if (pieceBoard[6][4].getColor()) {
                            if (white_move) {
                                curr_click = pieceBoard[6][4];
                                active = true;
                            }
                        }
                        if (!pieceBoard[6][4].getColor()) {
                            if (!white_move) {
                                curr_click = pieceBoard[6][4];
                                active = true;
                            }
                        }
                    }
                } else {
                    //check valid move function, return boolean, add as && in if statement
                    if (pieceBoard[6][4] == null && checkValidMove(6 ,4)) {
                        int prev_x = curr_click.getRow();
                        int prev_y = curr_click.getColumn();
                        buttonBoard[prev_x][prev_y].setText(" ");
                        buttonBoard[6][4].setText(curr_click.getName());
                        pieceBoard[6][4] = curr_click;
                        pieceBoard[6][4].setX(6);
                        pieceBoard[6][4].setY(4);
                        pieceBoard[prev_x][prev_y] = null;
                        active = false;
                        curr_click = null;
                    }
                }

            }

        });

        this.buttonBoard[6][5].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (!active) {
                    if (pieceBoard[6][5] != null) {
                        if (pieceBoard[6][5].getColor()) {
                            if (white_move) {
                                curr_click = pieceBoard[6][5];
                                active = true;
                            }
                        }
                        if (!pieceBoard[6][5].getColor()) {
                            if (!white_move) {
                                curr_click = pieceBoard[6][5];
                                active = true;
                            }
                        }
                    }
                } else {
                    //check valid move function, return boolean, add as && in if statement
                    if (pieceBoard[6][5] == null && checkValidMove(6 ,5)) {
                        int prev_x = curr_click.getRow();
                        int prev_y = curr_click.getColumn();
                        buttonBoard[prev_x][prev_y].setText(" ");
                        buttonBoard[6][5].setText(curr_click.getName());
                        pieceBoard[6][5] = curr_click;
                        pieceBoard[6][5].setX(6);
                        pieceBoard[6][5].setY(5);
                        pieceBoard[prev_x][prev_y] = null;
                        active = false;
                        curr_click = null;
                    }
                }

            }

        });

        this.buttonBoard[6][6].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (!active) {
                    if (pieceBoard[6][6] != null) {
                        if (pieceBoard[6][6].getColor()) {
                            if (white_move) {
                                curr_click = pieceBoard[6][6];
                                active = true;
                            }
                        }
                        if (!pieceBoard[6][6].getColor()) {
                            if (!white_move) {
                                curr_click = pieceBoard[6][6];
                                active = true;
                            }
                        }
                    }
                } else {
                    //check valid move function, return boolean, add as && in if statement
                    if (pieceBoard[6][6] == null && checkValidMove(6 ,6)) {
                        int prev_x = curr_click.getRow();
                        int prev_y = curr_click.getColumn();
                        buttonBoard[prev_x][prev_y].setText(" ");
                        buttonBoard[6][6].setText(curr_click.getName());
                        pieceBoard[6][6] = curr_click;
                        pieceBoard[6][6].setX(6);
                        pieceBoard[6][6].setY(6);
                        pieceBoard[prev_x][prev_y] = null;
                        active = false;
                        curr_click = null;
                    }
                }

            }

        });

        this.buttonBoard[6][7].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (!active) {
                    if (pieceBoard[6][7] != null) {
                        if (pieceBoard[0][0].getColor()) {
                            if (white_move) {
                                curr_click = pieceBoard[0][0];
                                active = true;
                            }
                        }
                        if (!pieceBoard[0][0].getColor()) {
                            if (!white_move) {
                                curr_click = pieceBoard[0][0];
                                active = true;
                            }
                        }
                    }
                } else {
                    //check valid move function, return boolean, add as && in if statement
                    if (pieceBoard[6][7] == null && checkValidMove(6 ,7)) {
                        int prev_x = curr_click.getRow();
                        int prev_y = curr_click.getColumn();
                        buttonBoard[prev_x][prev_y].setText(" ");
                        buttonBoard[6][7].setText(curr_click.getName());
                        pieceBoard[6][7] = curr_click;
                        pieceBoard[6][7].setX(6);
                        pieceBoard[6][7].setY(7);
                        pieceBoard[prev_x][prev_y] = null;
                        active = false;
                        curr_click = null;
                    }
                }

            }

        });

        this.buttonBoard[7][0].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!active) {
                    if (pieceBoard[7][0] != null) {
                        if (pieceBoard[7][0].getColor()) {
                            if (white_move) {
                                curr_click = pieceBoard[7][0];
                                active = true;
                            }
                        }
                        if (!pieceBoard[7][0].getColor()) {
                            if (!white_move) {
                                curr_click = pieceBoard[7][0];
                                active = true;
                            }
                        }
                    }
                } else {
                    //check valid move function, return boolean, add as && in if statement
                    if (pieceBoard[7][0] == null && checkValidMove(7 ,0)) {
                        int prev_x = curr_click.getRow();
                        int prev_y = curr_click.getColumn();
                        buttonBoard[prev_x][prev_y].setText(" ");
                        buttonBoard[7][0].setText(curr_click.getName());
                        pieceBoard[7][0] = curr_click;
                        pieceBoard[7][0].setX(7);
                        pieceBoard[7][0].setY(0);
                        pieceBoard[prev_x][prev_y] = null;
                        active = false;
                        curr_click = null;
                    }
                }
            }
        });

        this.buttonBoard[7][1].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (!active) {
                    if (pieceBoard[7][1] != null) {
                        if (pieceBoard[7][1].getColor()) {
                            if (white_move) {
                                curr_click = pieceBoard[7][1];
                                active = true;
                            }
                        }
                        if (!pieceBoard[7][1].getColor()) {
                            if (!white_move) {
                                curr_click = pieceBoard[7][1];
                                active = true;
                            }
                        }
                    }
                } else {
                    //check valid move function, return boolean, add as && in if statement
                    if (pieceBoard[7][1] == null && checkValidMove(7 ,1)) {
                        int prev_x = curr_click.getRow();
                        int prev_y = curr_click.getColumn();
                        buttonBoard[prev_x][prev_y].setText(" ");
                        buttonBoard[7][1].setText(curr_click.getName());
                        pieceBoard[7][1] = curr_click;
                        pieceBoard[7][1].setX(7);
                        pieceBoard[7][1].setY(1);
                        pieceBoard[prev_x][prev_y] = null;
                        active = false;
                        curr_click = null;
                    }
                }

            }

        });

        this.buttonBoard[7][2].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (!active) {
                    if (pieceBoard[7][2] != null) {
                        if (pieceBoard[7][2].getColor()) {
                            if (white_move) {
                                curr_click = pieceBoard[7][2];
                                active = true;
                            }
                        }
                        if (!pieceBoard[7][2].getColor()) {
                            if (!white_move) {
                                curr_click = pieceBoard[7][2];
                                active = true;
                            }
                        }
                    }
                } else {
                    //check valid move function, return boolean, add as && in if statement
                    if (pieceBoard[7][2] == null && checkValidMove(7 ,2)) {
                        int prev_x = curr_click.getRow();
                        int prev_y = curr_click.getColumn();
                        buttonBoard[prev_x][prev_y].setText(" ");
                        buttonBoard[7][2].setText(curr_click.getName());
                        pieceBoard[7][2] = curr_click;
                        pieceBoard[7][2].setX(7);
                        pieceBoard[7][2].setY(2);
                        pieceBoard[prev_x][prev_y] = null;
                        active = false;
                        curr_click = null;
                    }
                }

            }

        });

        this.buttonBoard[7][3].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (!active) {
                    if (pieceBoard[7][3] != null) {
                        if (pieceBoard[7][3].getColor()) {
                            if (white_move) {
                                curr_click = pieceBoard[7][3];
                                active = true;
                            }
                        }
                        if (!pieceBoard[7][3].getColor()) {
                            if (!white_move) {
                                curr_click = pieceBoard[7][3];
                                active = true;
                            }
                        }
                    }
                } else {
                    //check valid move function, return boolean, add as && in if statement
                    if (pieceBoard[7][3] == null && checkValidMove(7 ,3)) {
                        int prev_x = curr_click.getRow();
                        int prev_y = curr_click.getColumn();
                        buttonBoard[prev_x][prev_y].setText(" ");
                        buttonBoard[7][3].setText(curr_click.getName());
                        pieceBoard[7][3] = curr_click;
                        pieceBoard[7][3].setX(7);
                        pieceBoard[7][3].setY(3);
                        pieceBoard[prev_x][prev_y] = null;
                        active = false;
                        curr_click = null;
                    }
                }

            }

        });

        this.buttonBoard[7][4].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (!active) {
                    if (pieceBoard[7][4] != null) {
                        if (pieceBoard[7][4].getColor()) {
                            if (white_move) {
                                curr_click = pieceBoard[7][4];
                                active = true;
                            }
                        }
                        if (!pieceBoard[7][4].getColor()) {
                            if (!white_move) {
                                curr_click = pieceBoard[7][4];
                                active = true;
                            }
                        }
                    }
                } else {
                    //check valid move function, return boolean, add as && in if statement
                    if (pieceBoard[7][4] == null && checkValidMove(7 ,4)) {
                        int prev_x = curr_click.getRow();
                        int prev_y = curr_click.getColumn();
                        buttonBoard[prev_x][prev_y].setText(" ");
                        buttonBoard[7][4].setText(curr_click.getName());
                        pieceBoard[7][4] = curr_click;
                        pieceBoard[7][4].setX(7);
                        pieceBoard[7][4].setY(4);
                        pieceBoard[prev_x][prev_y] = null;
                        active = false;
                        curr_click = null;
                    }
                }

            }

        });

        this.buttonBoard[7][5].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (!active) {
                    if (pieceBoard[7][5] != null) {
                        if (pieceBoard[7][5].getColor()) {
                            if (white_move) {
                                curr_click = pieceBoard[7][5];
                                active = true;
                            }
                        }
                        if (!pieceBoard[7][5].getColor()) {
                            if (!white_move) {
                                curr_click = pieceBoard[7][5];
                                active = true;
                            }
                        }
                    }
                } else {
                    //check valid move function, return boolean, add as && in if statement
                    if (pieceBoard[7][5] == null && checkValidMove(7 ,5)) {
                        int prev_x = curr_click.getRow();
                        int prev_y = curr_click.getColumn();
                        buttonBoard[prev_x][prev_y].setText(" ");
                        buttonBoard[7][5].setText(curr_click.getName());
                        pieceBoard[7][5] = curr_click;
                        pieceBoard[7][5].setX(7);
                        pieceBoard[7][5].setY(5);
                        pieceBoard[prev_x][prev_y] = null;
                        active = false;
                        curr_click = null;
                    }
                }

            }

        });

        this.buttonBoard[7][6].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (!active) {
                    if (pieceBoard[7][6] != null) {
                        if (pieceBoard[7][6].getColor()) {
                            if (white_move) {
                                curr_click = pieceBoard[7][6];
                                active = true;
                            }
                        }
                        if (!pieceBoard[7][6].getColor()) {
                            if (!white_move) {
                                curr_click = pieceBoard[7][6];
                                active = true;
                            }
                        }
                    }
                } else {
                    //check valid move function, return boolean, add as && in if statement
                    if (pieceBoard[7][6] == null && checkValidMove(7 ,6)) {
                        int prev_x = curr_click.getRow();
                        int prev_y = curr_click.getColumn();
                        buttonBoard[prev_x][prev_y].setText(" ");
                        buttonBoard[7][6].setText(curr_click.getName());
                        pieceBoard[7][6] = curr_click;
                        pieceBoard[7][6].setX(7);
                        pieceBoard[7][6].setY(6);
                        pieceBoard[prev_x][prev_y] = null;
                        active = false;
                        curr_click = null;
                    }
                }

            }

        });

        this.buttonBoard[7][7].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (!active) {
                    if (pieceBoard[7][7] != null) {
                        if (pieceBoard[7][7].getColor()) {
                            if (white_move) {
                                curr_click = pieceBoard[7][7];
                                active = true;
                            }
                        }
                        if (!pieceBoard[7][7].getColor()) {
                            if (!white_move) {
                                curr_click = pieceBoard[7][7];
                                active = true;
                            }
                        }
                    }
                } else {
                    //check valid move function, return boolean, add as && in if statement
                    if (pieceBoard[7][7] == null && checkValidMove(7 ,7)) {
                        int prev_x = curr_click.getRow();
                        int prev_y = curr_click.getColumn();
                        buttonBoard[prev_x][prev_y].setText(" ");
                        buttonBoard[7][7].setText(curr_click.getName());
                        pieceBoard[7][7] = curr_click;
                        pieceBoard[7][7].setX(7);
                        pieceBoard[7][7].setY(7);
                        pieceBoard[prev_x][prev_y] = null;
                        active = false;
                        curr_click = null;
                    }
                }
            }
        });
    }

    public boolean checkValidMove(int x, int y) {


        int prev_x = curr_click.getRow();
        int prev_y = curr_click.getColumn();

        if (curr_click.getName().equals("whitePawn")) {

            if (!white_move) {
                return false;
            }

            if (x > prev_x + 1 || x <= prev_x) {
                return false;
            }
            if (y < prev_y - 1 || y > prev_y + 1) {
                return false;
            }

            white_move = false;
            return true;
        }

        if (curr_click.getName().equals("blackPawn")) {

            if (white_move) {
                return false;
            }

            if (x < prev_x - 1 || x >= prev_x) {
                return false;
            }
            if (y < prev_y - 1 || y > prev_y + 1) {
                return false;
            }

            white_move = true;
            return true;

        }

        return true;
    }
}
