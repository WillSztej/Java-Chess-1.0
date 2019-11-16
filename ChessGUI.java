import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.lang.Math;
import javax.imageio.*;
import java.awt.image.BufferedImage;
import java.io.*;


public class ChessGUI {

    PlayingPiece[][] pieceBoard;
    JButton[][] buttonBoard;

    private JPanel boardPanel;
    private JFrame guiFrame;
    static boolean active = false;
    static PlayingPiece curr_click;
    static int row = 0;
    static int col = 0;

    static JLabel lowerIconW;
    static JLabel lowerIconB;
    static JPanel lowerPanel;

    static boolean white_move = true;

    ImageIcon empty = new ImageIcon("src/piece_textures/empty.png");

    public ChessGUI(PlayingPiece[][] board) {

        PlayingPiece[][] pieceBoard = board;
        this.pieceBoard = pieceBoard;

        JButton[][] buttonBoard = new JButton[8][8];
        this.buttonBoard = buttonBoard;

        JFrame guiFrame = new JFrame("Chess Game");
        guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        guiFrame.setSize(600,800);


        JPanel outerPanel = new JPanel();
        outerPanel.setSize(600, 300);
        JPanel boardPanel = new JPanel(new GridLayout(8,8));
        boardPanel.setSize(600, 300);
        boardPanel.setBackground(Color.black);

        this.boardPanel = boardPanel;
        this.guiFrame = guiFrame;

        this.initializeBoard();

        JPanel upperPanel = new JPanel();
        upperPanel.setSize(600, 100);
        lowerPanel = new JPanel();
        lowerPanel.setSize(600, 150);


        outerPanel.add(upperPanel);
        outerPanel.add(boardPanel);
        outerPanel.add(lowerPanel);

        ImageIcon upperPanelImg = new ImageIcon("src/gui_textures/upper_panel.png");
        ImageIcon lowerPanelImgWhite = new ImageIcon("src/gui_textures/lower_white.png");
        ImageIcon lowerPanelImgBlack = new ImageIcon("src/gui_textures/lower_black.png");

        lowerIconW = new JLabel(lowerPanelImgWhite);
        lowerIconB = new JLabel(lowerPanelImgBlack);
        JLabel topIcon = new JLabel(upperPanelImg);

        lowerPanel.add(lowerIconW);

        upperPanel.add(topIcon);


        guiFrame.add(outerPanel);

        //guiFrame.getContentPane().add("Center", outerPanel);
        //guiFrame.add(outerPanel);
        guiFrame.setVisible(true);

        guiFrame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                guiFrame.setSize(600, 850);
            }
        });

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
                        this.pieceBoard[row][col] = new PlayingPiece(i, j, name, white, true);
                        ImageIcon img = new ImageIcon("src/piece_textures/w_pawn.png");
                        this.buttonBoard[row][col] = new JButton(img);
                        this.boardPanel.add(this.buttonBoard[i][j]);
                    }

                    if (i == 6) {
                        name = "blackPawn";
                        white = false;
                        this.pieceBoard[row][col] = new PlayingPiece(i, j, name, white, true);
                        ImageIcon img = new ImageIcon("src/piece_textures/b_pawn.png");
                        this.buttonBoard[row][col] = new JButton(img);
                        this.boardPanel.add(this.buttonBoard[i][j]);
                    }

                    // assign names for the first row
                    if (i == 0 || i == 7) {
                        switch (j) {
                            case 0:
                            case 7:
                                if (i == 0) {
                                    name = "whiteRook";
                                    this.pieceBoard[row][col] = new PlayingPiece(i, j, name, white);
                                    ImageIcon img = new ImageIcon("src/piece_textures/w_rook.png");
                                    this.buttonBoard[row][col] = new JButton(img);
                                    this.boardPanel.add(this.buttonBoard[i][j]);
                                } else {
                                    name = "blackRook";
                                    white = false;
                                    this.pieceBoard[row][col] = new PlayingPiece(i, j, name, white);
                                    ImageIcon img = new ImageIcon("src/piece_textures/b_rook.png");
                                    this.buttonBoard[row][col] = new JButton(img);
                                    this.boardPanel.add(this.buttonBoard[i][j]);
                                }
                                break;
                            case 1:
                            case 6:
                                if (i == 0) {
                                    name = "whiteKnight";
                                    this.pieceBoard[row][col] = new PlayingPiece(i, j, name, white);
                                    ImageIcon img = new ImageIcon("src/piece_textures/w_knight.png");
                                    this.buttonBoard[row][col] = new JButton(img);
                                    this.boardPanel.add(this.buttonBoard[i][j]);
                                } else {
                                    name = "blackKnight";
                                    white = false;
                                    this.pieceBoard[row][col] = new PlayingPiece(i, j, name, white);
                                    ImageIcon img = new ImageIcon("src/piece_textures/b_knight.png");
                                    this.buttonBoard[row][col] = new JButton(img);
                                    this.boardPanel.add(this.buttonBoard[i][j]);
                                }
                                break;
                            case 2:
                            case 5:
                                if (i == 0) {
                                    name = "whiteBishop";
                                    this.pieceBoard[row][col] = new PlayingPiece(i, j, name, white);
                                    ImageIcon img = new ImageIcon("src/piece_textures/w_bishop.png");
                                    this.buttonBoard[row][col] = new JButton(img);
                                    this.boardPanel.add(this.buttonBoard[i][j]);
                                } else {
                                    name = "blackBishop";
                                    white = false;
                                    this.pieceBoard[row][col] = new PlayingPiece(i, j, name, white);
                                    ImageIcon img = new ImageIcon("src/piece_textures/b_bishop.png");
                                    this.buttonBoard[row][col] = new JButton(img);
                                    this.boardPanel.add(this.buttonBoard[i][j]);
                                }
                                break;
                            case 4:
                                if (i == 0) {
                                    name = "whiteQueen";
                                    this.pieceBoard[row][col] = new PlayingPiece(i, j, name, white);
                                    ImageIcon img = new ImageIcon("src/piece_textures/w_queen.png");
                                    this.buttonBoard[row][col] = new JButton(img);
                                    this.boardPanel.add(this.buttonBoard[i][j]);
                                } else {
                                    name = "blackQueen";
                                    white = false;
                                    this.pieceBoard[row][col] = new PlayingPiece(i, j, name, white);
                                    ImageIcon img = new ImageIcon("src/piece_textures/b_queen.png");
                                    this.buttonBoard[row][col] = new JButton(img);
                                    this.boardPanel.add(this.buttonBoard[i][j]);
                                }
                                break;
                            case 3:
                                if (i == 0) {
                                    name = "whiteKing";
                                    this.pieceBoard[row][col] = new PlayingPiece(i, j, name, white);
                                    ImageIcon img = new ImageIcon("src/piece_textures/w_king.png");
                                    this.buttonBoard[row][col] = new JButton(img);
                                    this.boardPanel.add(this.buttonBoard[i][j]);
                                } else {
                                    name = "blackKing";
                                    white = false;
                                    this.pieceBoard[row][col] = new PlayingPiece(i, j, name, white);
                                    ImageIcon img = new ImageIcon("src/piece_textures/b_king.png");
                                    this.buttonBoard[row][col] = new JButton(img);
                                    this.boardPanel.add(this.buttonBoard[i][j]);
                                }
                                break;
                            default:
                                break;
                        }
                    }

                } else {
                    this.pieceBoard[row][col] = null;
                    this.buttonBoard[row][col] = new JButton(" ");
                    this.boardPanel.add(this.buttonBoard[i][j]);
                }

            }
        }

        ActivateActionListeners();
    }

    private void ActivateActionListeners() {

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

                    // declare int coordinates for previously clicked button
                    int prev_x = curr_click.getRow();
                    int prev_y = curr_click.getColumn();

                    //check valid move function, return boolean, add as && in if statement
                    if (checkValidMove(0 ,0)) {

                        if (pieceBoard[0][0] == null || checkValidEat(0, 0)) {

                            // set text of previously clicked button to " "
                            buttonBoard[prev_x][prev_y].setIcon(empty);

                            // set text of newly clicked button to the previously clicked
                            buttonBoard[0][0].setIcon(getTexturePath(curr_click));

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
                    } else {
                        curr_click = null;
                        active = false;
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

                    if (checkValidMove(0, 1)) {
                        if (pieceBoard[0][1] == null || checkValidEat(0, 1)) {
                            int prev_x = curr_click.getRow();
                            int prev_y = curr_click.getColumn();
                            buttonBoard[prev_x][prev_y].setIcon(empty);
                            buttonBoard[0][1].setIcon(getTexturePath(curr_click));
                            pieceBoard[0][1] = curr_click;
                            pieceBoard[0][1].setX(0);
                            pieceBoard[0][1].setY(1);
                            pieceBoard[prev_x][prev_y] = null;
                            active = false;
                            curr_click = null;
                        }
                    } else {
                        curr_click = null;
                        active = false;
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

                    if (checkValidMove(0, 2)) {
                        //check valid move function, return boolean, add as && in if statement
                        if (pieceBoard[0][2] == null || checkValidEat(0, 2)) {
                            int prev_x = curr_click.getRow();
                            int prev_y = curr_click.getColumn();
                            buttonBoard[prev_x][prev_y].setIcon(empty);
                            buttonBoard[0][2].setIcon(getTexturePath(curr_click));
                            pieceBoard[0][2] = curr_click;
                            pieceBoard[0][2].setX(0);
                            pieceBoard[0][2].setY(2);
                            pieceBoard[prev_x][prev_y] = null;
                            active = false;
                            curr_click = null;
                        }
                    } else {
                        curr_click = null;
                        active = false;
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

                    if (checkValidMove(0, 3)) {
                        if (pieceBoard[0][3] == null || checkValidEat(0, 3)) {
                            int prev_x = curr_click.getRow();
                            int prev_y = curr_click.getColumn();
                            buttonBoard[prev_x][prev_y].setIcon(empty);
                            buttonBoard[0][3].setIcon(getTexturePath(curr_click));
                            pieceBoard[0][3] = curr_click;
                            pieceBoard[0][3].setX(0);
                            pieceBoard[0][3].setY(3);
                            pieceBoard[prev_x][prev_y] = null;
                            active = false;
                            curr_click = null;
                        }

                    } else {
                        curr_click = null;
                        active = false;
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

                    if (checkValidMove(0, 4)) {
                        if (pieceBoard[0][4] == null || checkValidEat(0, 4)) {
                            int prev_x = curr_click.getRow();
                            int prev_y = curr_click.getColumn();
                            buttonBoard[prev_x][prev_y].setIcon(empty);
                            buttonBoard[0][4].setIcon(getTexturePath(curr_click));
                            pieceBoard[0][4] = curr_click;
                            pieceBoard[0][4].setX(0);
                            pieceBoard[0][4].setY(4);
                            pieceBoard[prev_x][prev_y] = null;
                            active = false;
                            curr_click = null;
                        }

                    } else {
                        curr_click = null;
                        active = false;
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

                    if (checkValidMove(0, 5)) {
                        if (pieceBoard[0][5] == null || checkValidEat(0, 5)) {
                            int prev_x = curr_click.getRow();
                            int prev_y = curr_click.getColumn();
                            buttonBoard[prev_x][prev_y].setIcon(empty);
                            buttonBoard[0][5].setIcon(getTexturePath(curr_click));
                            pieceBoard[0][5] = curr_click;
                            pieceBoard[0][5].setX(0);
                            pieceBoard[0][5].setY(5);
                            pieceBoard[prev_x][prev_y] = null;
                            active = false;
                            curr_click = null;
                        }
                    } else {
                        curr_click = null;
                        active = false;
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

                    if (checkValidMove(0, 6)) {
                        if (pieceBoard[0][6] == null || checkValidEat(0, 6)) {
                            int prev_x = curr_click.getRow();
                            int prev_y = curr_click.getColumn();
                            buttonBoard[prev_x][prev_y].setIcon(empty);
                            buttonBoard[0][6].setIcon(getTexturePath(curr_click));
                            pieceBoard[0][6] = curr_click;
                            pieceBoard[0][6].setX(0);
                            pieceBoard[0][6].setY(6);
                            pieceBoard[prev_x][prev_y] = null;
                            active = false;
                            curr_click = null;
                        }
                    } else {
                        curr_click = null;
                        active = false;
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

                    if (checkValidMove(0, 7)) {
                        if (pieceBoard[0][7] == null || checkValidEat(0, 7)) {
                            int prev_x = curr_click.getRow();
                            int prev_y = curr_click.getColumn();
                            buttonBoard[prev_x][prev_y].setIcon(empty);
                            buttonBoard[0][7].setIcon(getTexturePath(curr_click));
                            pieceBoard[0][7] = curr_click;
                            pieceBoard[0][7].setX(0);
                            pieceBoard[0][7].setY(7);
                            pieceBoard[prev_x][prev_y] = null;
                            active = false;
                            curr_click = null;
                        }
                    } else {
                        curr_click = null;
                        active = false;
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

                    if (checkValidMove(1, 0)) {
                        if (pieceBoard[1][0] == null || checkValidEat(1, 0)) {
                            int prev_x = curr_click.getRow();
                            int prev_y = curr_click.getColumn();
                            buttonBoard[prev_x][prev_y].setIcon(empty);
                            buttonBoard[1][0].setIcon(getTexturePath(curr_click));
                            pieceBoard[1][0] = curr_click;
                            pieceBoard[1][0].setX(1);
                            pieceBoard[1][0].setY(0);
                            pieceBoard[prev_x][prev_y] = null;
                            active = false;
                            curr_click = null;
                        }
                    } else {
                        curr_click = null;
                        active = false;
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
                    if (checkValidMove(1, 1)) {
                        if (pieceBoard[1][1] == null || checkValidEat(1, 1)) {
                            int prev_x = curr_click.getRow();
                            int prev_y = curr_click.getColumn();
                            buttonBoard[prev_x][prev_y].setIcon(empty);
                            buttonBoard[1][1].setIcon(getTexturePath(curr_click));
                            pieceBoard[1][1] = curr_click;
                            pieceBoard[1][1].setX(1);
                            pieceBoard[1][1].setY(1);
                            pieceBoard[prev_x][prev_y] = null;
                            active = false;
                            curr_click = null;
                        }
                    } else {
                        curr_click = null;
                        active = false;
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

                    if (checkValidMove(1, 2)) {
                        if (pieceBoard[1][2] == null || checkValidEat(1, 2)) {
                            int prev_x = curr_click.getRow();
                            int prev_y = curr_click.getColumn();
                            buttonBoard[prev_x][prev_y].setIcon(empty);
                            buttonBoard[1][2].setIcon(getTexturePath(curr_click));
                            pieceBoard[1][2] = curr_click;
                            pieceBoard[1][2].setX(1);
                            pieceBoard[1][2].setY(2);
                            pieceBoard[prev_x][prev_y] = null;
                            active = false;
                            curr_click = null;
                        }
                    } else {
                        curr_click = null;
                        active = false;
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
                    if (checkValidMove(1, 3)) {
                        if (pieceBoard[1][3] == null || checkValidEat(1, 3)) {
                            int prev_x = curr_click.getRow();
                            int prev_y = curr_click.getColumn();
                            buttonBoard[prev_x][prev_y].setIcon(empty);
                            buttonBoard[1][3].setIcon(getTexturePath(curr_click));
                            pieceBoard[1][3] = curr_click;
                            pieceBoard[1][3].setX(1);
                            pieceBoard[1][3].setY(3);
                            pieceBoard[prev_x][prev_y] = null;
                            active = false;
                            curr_click = null;
                        }
                    } else {
                        curr_click = null;
                        active = false;
                    }
                }

            }

        });

        this.buttonBoard[1][4].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (!active) {
                    if (pieceBoard[1][4] != null) {
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
                    if (checkValidMove(1, 4)) {
                        if (pieceBoard[1][4] == null || checkValidEat(1, 4)) {
                            int prev_x = curr_click.getRow();
                            int prev_y = curr_click.getColumn();
                            buttonBoard[prev_x][prev_y].setIcon(empty);
                            buttonBoard[1][4].setIcon(getTexturePath(curr_click));
                            pieceBoard[1][4] = curr_click;
                            pieceBoard[1][4].setX(1);
                            pieceBoard[1][4].setY(4);
                            pieceBoard[prev_x][prev_y] = null;
                            active = false;
                            curr_click = null;
                        }
                    } else {
                        curr_click = null;
                        active = false;
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

                    if (checkValidMove(1,5)) {
                        if (pieceBoard[1][5] == null || checkValidEat(1, 5)) {
                            int prev_x = curr_click.getRow();
                            int prev_y = curr_click.getColumn();
                            buttonBoard[prev_x][prev_y].setIcon(empty);
                            buttonBoard[1][5].setIcon(getTexturePath(curr_click));
                            pieceBoard[1][5] = curr_click;
                            pieceBoard[1][5].setX(1);
                            pieceBoard[1][5].setY(5);
                            pieceBoard[prev_x][prev_y] = null;
                            active = false;
                            curr_click = null;
                        }
                    } else {
                        curr_click = null;
                        active = false;
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
                    if (checkValidMove(1, 6)) {
                        if (pieceBoard[1][6] == null || checkValidEat(1, 6)) {
                            int prev_x = curr_click.getRow();
                            int prev_y = curr_click.getColumn();
                            buttonBoard[prev_x][prev_y].setIcon(empty);
                            buttonBoard[1][6].setIcon(getTexturePath(curr_click));
                            pieceBoard[1][6] = curr_click;
                            pieceBoard[1][6].setX(1);
                            pieceBoard[1][6].setY(6);
                            pieceBoard[prev_x][prev_y] = null;
                            active = false;
                            curr_click = null;
                        }
                    } else {
                        curr_click = null;
                        active = false;
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

                    if (checkValidMove(1, 7)) {
                        if (pieceBoard[1][7] == null || checkValidEat(1, 7)) {
                            int prev_x = curr_click.getRow();
                            int prev_y = curr_click.getColumn();
                            buttonBoard[prev_x][prev_y].setIcon(empty);
                            buttonBoard[1][7].setIcon(getTexturePath(curr_click));
                            pieceBoard[1][7] = curr_click;
                            pieceBoard[1][7].setX(1);
                            pieceBoard[1][7].setY(7);
                            pieceBoard[prev_x][prev_y] = null;
                            active = false;
                            curr_click = null;
                        }
                    } else {
                        curr_click = null;
                        active = false;
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

                    if (checkValidMove(2, 0)) {
                        if (pieceBoard[2][0] == null || checkValidEat(2, 0)) {
                            int prev_x = curr_click.getRow();
                            int prev_y = curr_click.getColumn();
                            buttonBoard[prev_x][prev_y].setIcon(empty);
                            buttonBoard[2][0].setIcon(getTexturePath(curr_click));
                            pieceBoard[2][0] = curr_click;
                            pieceBoard[2][0].setX(2);
                            pieceBoard[2][0].setY(0);
                            pieceBoard[prev_x][prev_y] = null;
                            active = false;
                            curr_click = null;
                        }
                    } else {
                        curr_click = null;
                        active = false;
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

                    if (checkValidMove(2, 1)) {
                        if (pieceBoard[2][1] == null || checkValidEat(2, 1)) {
                            int prev_x = curr_click.getRow();
                            int prev_y = curr_click.getColumn();
                            buttonBoard[prev_x][prev_y].setIcon(empty);
                            buttonBoard[2][1].setIcon(getTexturePath(curr_click));
                            pieceBoard[2][1] = curr_click;
                            pieceBoard[2][1].setX(2);
                            pieceBoard[2][1].setY(1);
                            pieceBoard[prev_x][prev_y] = null;
                            active = false;
                            curr_click = null;
                        }
                    } else {
                        curr_click = null;
                        active = false;
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

                    if (checkValidMove(2,2)) {
                        if (pieceBoard[2][2] == null || checkValidEat(2, 2)) {
                            int prev_x = curr_click.getRow();
                            int prev_y = curr_click.getColumn();
                            buttonBoard[prev_x][prev_y].setIcon(empty);
                            buttonBoard[2][2].setIcon(getTexturePath(curr_click));
                            pieceBoard[2][2] = curr_click;
                            pieceBoard[2][2].setX(2);
                            pieceBoard[2][2].setY(2);
                            pieceBoard[prev_x][prev_y] = null;
                            active = false;
                            curr_click = null;
                        }
                    } else {
                        curr_click = null;
                        active = false;
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

                    if (checkValidMove(2, 3)) {
                        if (pieceBoard[2][3] == null || checkValidMove(2, 3)) {
                            int prev_x = curr_click.getRow();
                            int prev_y = curr_click.getColumn();
                            buttonBoard[prev_x][prev_y].setIcon(empty);
                            buttonBoard[2][3].setIcon(getTexturePath(curr_click));
                            pieceBoard[2][3] = curr_click;
                            pieceBoard[2][3].setX(2);
                            pieceBoard[2][3].setY(3);
                            pieceBoard[prev_x][prev_y] = null;
                            active = false;
                            curr_click = null;
                        }
                    } else {
                        curr_click = null;
                        active = false;
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
                    if (checkValidMove(2, 4)) {
                        if (pieceBoard[2][4] == null || checkValidMove(2, 4)) {
                            int prev_x = curr_click.getRow();
                            int prev_y = curr_click.getColumn();
                            buttonBoard[prev_x][prev_y].setIcon(empty);
                            buttonBoard[2][4].setIcon(getTexturePath(curr_click));
                            pieceBoard[2][4] = curr_click;
                            pieceBoard[2][4].setX(2);
                            pieceBoard[2][4].setY(4);
                            pieceBoard[prev_x][prev_y] = null;
                            active = false;
                            curr_click = null;
                        }
                    } else {
                        curr_click = null;
                        active = false;
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
                    if (checkValidMove(2 ,5)) {
                        if (pieceBoard[2][5] == null || checkValidMove(2, 5)) {
                            int prev_x = curr_click.getRow();
                            int prev_y = curr_click.getColumn();
                            buttonBoard[prev_x][prev_y].setIcon(empty);
                            buttonBoard[2][5].setIcon(getTexturePath(curr_click));
                            pieceBoard[2][5] = curr_click;
                            pieceBoard[2][5].setX(2);
                            pieceBoard[2][5].setY(5);
                            pieceBoard[prev_x][prev_y] = null;
                            active = false;
                            curr_click = null;
                        }
                    } else {
                        curr_click = null;
                        active = false;
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
                    if (checkValidMove(2, 6)) {
                        if (pieceBoard[2][6] == null || checkValidEat(2, 6)) {
                            int prev_x = curr_click.getRow();
                            int prev_y = curr_click.getColumn();
                            buttonBoard[prev_x][prev_y].setIcon(empty);
                            buttonBoard[2][6].setIcon(getTexturePath(curr_click));
                            pieceBoard[2][6] = curr_click;
                            pieceBoard[2][6].setX(2);
                            pieceBoard[2][6].setY(6);
                            pieceBoard[prev_x][prev_y] = null;
                            active = false;
                            curr_click = null;
                        }
                    } else {
                        curr_click = null;
                        active = false;
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

                    if (checkValidMove(2, 7)) {
                        if (pieceBoard[2][7] == null || checkValidEat(2, 7)) {
                            int prev_x = curr_click.getRow();
                            int prev_y = curr_click.getColumn();
                            buttonBoard[prev_x][prev_y].setIcon(empty);
                            buttonBoard[2][7].setIcon(getTexturePath(curr_click));
                            pieceBoard[2][7] = curr_click;
                            pieceBoard[2][7].setX(2);
                            pieceBoard[2][7].setY(7);
                            pieceBoard[prev_x][prev_y] = null;
                            active = false;
                            curr_click = null;
                        }
                    } else {
                        curr_click = null;
                        active = false;
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
                    if (checkValidMove(3, 0)) {
                        if (pieceBoard[3][0] == null || checkValidMove(3, 0)) {
                            int prev_x = curr_click.getRow();
                            int prev_y = curr_click.getColumn();
                            buttonBoard[prev_x][prev_y].setIcon(empty);
                            buttonBoard[3][0].setIcon(getTexturePath(curr_click));
                            pieceBoard[3][0] = curr_click;
                            pieceBoard[3][0].setX(3);
                            pieceBoard[3][0].setY(0);
                            pieceBoard[prev_x][prev_y] = null;
                            active = false;
                            curr_click = null;
                        }
                    } else {
                        curr_click = null;
                        active = false;
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
                    if (checkValidMove(3,1)) {
                        if (pieceBoard[3][1] == null || checkValidEat(3, 1)) {
                            int prev_x = curr_click.getRow();
                            int prev_y = curr_click.getColumn();
                            buttonBoard[prev_x][prev_y].setIcon(empty);
                            buttonBoard[3][1].setIcon(getTexturePath(curr_click));
                            pieceBoard[3][1] = curr_click;
                            pieceBoard[3][1].setX(3);
                            pieceBoard[3][1].setY(1);
                            pieceBoard[prev_x][prev_y] = null;
                            active = false;
                            curr_click = null;
                        }
                    } else {
                        curr_click = null;
                        active = false;
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
                    if (checkValidMove(3, 2)) {
                        if (pieceBoard[3][2] == null || checkValidEat(3, 2)) {
                            int prev_x = curr_click.getRow();
                            int prev_y = curr_click.getColumn();
                            buttonBoard[prev_x][prev_y].setIcon(empty);
                            buttonBoard[3][2].setIcon(getTexturePath(curr_click));
                            pieceBoard[3][2] = curr_click;
                            pieceBoard[3][2].setX(3);
                            pieceBoard[3][2].setY(2);
                            pieceBoard[prev_x][prev_y] = null;
                            active = false;
                            curr_click = null;
                        }
                    } else {
                        curr_click = null;
                        active = false;
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
                    if (checkValidMove(3,3)) {
                        if (pieceBoard[3][3] == null || checkValidEat(3, 3)) {
                            int prev_x = curr_click.getRow();
                            int prev_y = curr_click.getColumn();
                            buttonBoard[prev_x][prev_y].setIcon(empty);
                            buttonBoard[3][3].setIcon(getTexturePath(curr_click));
                            pieceBoard[3][3] = curr_click;
                            pieceBoard[3][3].setX(3);
                            pieceBoard[3][3].setY(3);
                            pieceBoard[prev_x][prev_y] = null;
                            active = false;
                            curr_click = null;
                        }
                    } else {
                        curr_click = null;
                        active = false;
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
                    if (checkValidMove(3, 4)) {
                        if (pieceBoard[3][4] == null || checkValidEat(3, 4)) {
                            int prev_x = curr_click.getRow();
                            int prev_y = curr_click.getColumn();
                            buttonBoard[prev_x][prev_y].setIcon(empty);
                            buttonBoard[3][4].setIcon(getTexturePath(curr_click));
                            pieceBoard[3][4] = curr_click;
                            pieceBoard[3][4].setX(3);
                            pieceBoard[3][4].setY(4);
                            pieceBoard[prev_x][prev_y] = null;
                            active = false;
                            curr_click = null;
                        }
                    } else {
                        curr_click = null;
                        active = false;
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
                    if (checkValidMove(3, 5)) {
                        if (pieceBoard[3][5] == null || checkValidEat(3, 5)) {
                            int prev_x = curr_click.getRow();
                            int prev_y = curr_click.getColumn();
                            buttonBoard[prev_x][prev_y].setIcon(empty);
                            buttonBoard[3][5].setIcon(getTexturePath(curr_click));
                            pieceBoard[3][5] = curr_click;
                            pieceBoard[3][5].setX(3);
                            pieceBoard[3][5].setY(5);
                            pieceBoard[prev_x][prev_y] = null;
                            active = false;
                            curr_click = null;
                        }
                    } else {
                        curr_click = null;
                        active = false;
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

                    if (checkValidMove(3, 6)) {
                        if (pieceBoard[3][6] == null || checkValidEat(3, 6)) {
                            int prev_x = curr_click.getRow();
                            int prev_y = curr_click.getColumn();
                            buttonBoard[prev_x][prev_y].setIcon(empty);
                            buttonBoard[3][6].setIcon(getTexturePath(curr_click));
                            pieceBoard[3][6] = curr_click;
                            pieceBoard[3][6].setX(3);
                            pieceBoard[3][6].setY(6);
                            pieceBoard[prev_x][prev_y] = null;
                            active = false;
                            curr_click = null;
                        }
                    } else {
                        curr_click = null;
                        active = false;
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

                    if (checkValidMove(3, 7)) {
                        if (pieceBoard[3][7] == null || checkValidEat(3, 7)) {
                            int prev_x = curr_click.getRow();
                            int prev_y = curr_click.getColumn();
                            buttonBoard[prev_x][prev_y].setIcon(empty);
                            buttonBoard[3][7].setIcon(getTexturePath(curr_click));
                            pieceBoard[3][7] = curr_click;
                            pieceBoard[3][7].setX(3);
                            pieceBoard[3][7].setY(7);
                            pieceBoard[prev_x][prev_y] = null;
                            active = false;
                            curr_click = null;
                        }
                    } else {
                        curr_click = null;
                        active = false;
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

                    if (checkValidMove(4, 0)) {
                        if (pieceBoard[4][0] == null || checkValidEat(4, 0)) {
                            int prev_x = curr_click.getRow();
                            int prev_y = curr_click.getColumn();
                            buttonBoard[prev_x][prev_y].setIcon(empty);
                            buttonBoard[4][0].setIcon(getTexturePath(curr_click));
                            pieceBoard[4][0] = curr_click;
                            pieceBoard[4][0].setX(4);
                            pieceBoard[4][0].setY(0);
                            pieceBoard[prev_x][prev_y] = null;
                            active = false;
                            curr_click = null;
                        }
                    } else {
                        curr_click = null;
                        active = false;
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

                    if (checkValidMove(4 ,1)) {
                        if (pieceBoard[4][1] == null || checkValidEat(4, 1)) {
                            int prev_x = curr_click.getRow();
                            int prev_y = curr_click.getColumn();
                            buttonBoard[prev_x][prev_y].setIcon(empty);
                            buttonBoard[4][1].setIcon(getTexturePath(curr_click));
                            pieceBoard[4][1] = curr_click;
                            pieceBoard[4][1].setX(4);
                            pieceBoard[4][1].setY(1);
                            pieceBoard[prev_x][prev_y] = null;
                            active = false;
                            curr_click = null;
                        }
                    } else {
                        curr_click = null;
                        active = false;
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

                    if (checkValidMove(4 , 2)) {
                        if (pieceBoard[4][2] == null || checkValidEat(4, 2)) {
                            int prev_x = curr_click.getRow();
                            int prev_y = curr_click.getColumn();
                            buttonBoard[prev_x][prev_y].setIcon(empty);
                            buttonBoard[4][2].setIcon(getTexturePath(curr_click));
                            pieceBoard[4][2] = curr_click;
                            pieceBoard[4][2].setX(4);
                            pieceBoard[4][2].setY(2);
                            pieceBoard[prev_x][prev_y] = null;
                            active = false;
                            curr_click = null;
                        }
                    } else {
                        curr_click = null;
                        active = false;
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

                    if (checkValidMove(4, 3)) {
                        if (pieceBoard[4][3] == null || checkValidEat(4, 3)) {
                            int prev_x = curr_click.getRow();
                            int prev_y = curr_click.getColumn();
                            buttonBoard[prev_x][prev_y].setIcon(empty);
                            buttonBoard[4][3].setIcon(getTexturePath(curr_click));
                            pieceBoard[4][3] = curr_click;
                            pieceBoard[4][3].setX(4);
                            pieceBoard[4][3].setY(3);
                            pieceBoard[prev_x][prev_y] = null;
                            active = false;
                            curr_click = null;
                        }
                    } else {
                        curr_click = null;
                        active = false;
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

                    if (checkValidMove(4,4)) {
                        if (pieceBoard[4][4] == null || checkValidEat(4, 4)) {
                            int prev_x = curr_click.getRow();
                            int prev_y = curr_click.getColumn();
                            buttonBoard[prev_x][prev_y].setIcon(empty);
                            buttonBoard[4][4].setIcon(getTexturePath(curr_click));
                            pieceBoard[4][4] = curr_click;
                            pieceBoard[4][4].setX(4);
                            pieceBoard[4][4].setY(4);
                            pieceBoard[prev_x][prev_y] = null;
                            active = false;
                            curr_click = null;
                        }
                    } else {
                        curr_click = null;
                        active = false;
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

                    if (checkValidMove(4, 5)) {
                        if (pieceBoard[4][5] == null || checkValidEat(4, 5)) {
                            int prev_x = curr_click.getRow();
                            int prev_y = curr_click.getColumn();
                            buttonBoard[prev_x][prev_y].setIcon(empty);
                            buttonBoard[4][5].setIcon(getTexturePath(curr_click));
                            pieceBoard[4][5] = curr_click;
                            pieceBoard[4][5].setX(4);
                            pieceBoard[4][5].setY(5);
                            pieceBoard[prev_x][prev_y] = null;
                            active = false;
                            curr_click = null;
                        }
                    } else {
                        curr_click = null;
                        active = false;
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

                    if (checkValidMove(4, 6)) {
                        if (pieceBoard[4][6] == null || checkValidEat(4, 6)) {
                            int prev_x = curr_click.getRow();
                            int prev_y = curr_click.getColumn();
                            buttonBoard[prev_x][prev_y].setIcon(empty);
                            buttonBoard[4][6].setIcon(getTexturePath(curr_click));
                            pieceBoard[4][6] = curr_click;
                            pieceBoard[4][6].setX(4);
                            pieceBoard[4][6].setY(6);
                            pieceBoard[prev_x][prev_y] = null;
                            active = false;
                            curr_click = null;
                        }
                    } else {
                        curr_click = null;
                        active = false;
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

                    if (checkValidMove(4 ,7)) {
                        if (pieceBoard[4][7] == null || checkValidEat(4, 7)) {
                            int prev_x = curr_click.getRow();
                            int prev_y = curr_click.getColumn();
                            buttonBoard[prev_x][prev_y].setIcon(empty);
                            buttonBoard[4][7].setIcon(getTexturePath(curr_click));
                            pieceBoard[4][7] = curr_click;
                            pieceBoard[4][7].setX(4);
                            pieceBoard[4][7].setY(7);
                            pieceBoard[prev_x][prev_y] = null;
                            active = false;
                            curr_click = null;
                        }
                    } else {
                        curr_click = null;
                        active = false;
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

                    if (checkValidMove(5, 0)) {
                        if (pieceBoard[5][0] == null || checkValidEat(5, 0)) {
                            int prev_x = curr_click.getRow();
                            int prev_y = curr_click.getColumn();
                            buttonBoard[prev_x][prev_y].setIcon(empty);
                            buttonBoard[5][0].setIcon(getTexturePath(curr_click));
                            pieceBoard[5][0] = curr_click;
                            pieceBoard[5][0].setX(5);
                            pieceBoard[5][0].setY(0);
                            pieceBoard[prev_x][prev_y] = null;
                            active = false;
                            curr_click = null;
                        }
                    } else {
                        curr_click = null;
                        active = false;
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

                    if (checkValidMove(5, 1)) {
                        if (pieceBoard[5][1] == null || checkValidEat(5, 1)) {
                            int prev_x = curr_click.getRow();
                            int prev_y = curr_click.getColumn();
                            buttonBoard[prev_x][prev_y].setIcon(empty);
                            buttonBoard[5][1].setIcon(getTexturePath(curr_click));
                            pieceBoard[5][1] = curr_click;
                            pieceBoard[5][1].setX(5);
                            pieceBoard[5][1].setY(1);
                            pieceBoard[prev_x][prev_y] = null;
                            active = false;
                            curr_click = null;
                        }
                    } else {
                        curr_click = null;
                        active = false;
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

                    if (checkValidMove(5, 2)) {
                        if (pieceBoard[5][2] == null || checkValidEat(5, 2)) {
                            int prev_x = curr_click.getRow();
                            int prev_y = curr_click.getColumn();
                            buttonBoard[prev_x][prev_y].setIcon(empty);
                            buttonBoard[5][2].setIcon(getTexturePath(curr_click));
                            pieceBoard[5][2] = curr_click;
                            pieceBoard[5][2].setX(5);
                            pieceBoard[5][2].setY(2);
                            pieceBoard[prev_x][prev_y] = null;
                            active = false;
                            curr_click = null;
                        }
                    } else {
                        curr_click = null;
                        active = false;
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

                    if (checkValidMove(5, 3)) {
                        if (pieceBoard[5][3] == null || checkValidEat(5, 3)) {
                            int prev_x = curr_click.getRow();
                            int prev_y = curr_click.getColumn();
                            buttonBoard[prev_x][prev_y].setIcon(empty);
                            buttonBoard[5][3].setIcon(getTexturePath(curr_click));
                            pieceBoard[5][3] = curr_click;
                            pieceBoard[5][3].setX(5);
                            pieceBoard[5][3].setY(3);
                            pieceBoard[prev_x][prev_y] = null;
                            active = false;
                            curr_click = null;
                        }
                    } else {
                        curr_click = null;
                        active = false;
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

                    if (checkValidMove(5, 4)) {
                        if (pieceBoard[5][4] == null || checkValidEat(5, 4)) {
                            int prev_x = curr_click.getRow();
                            int prev_y = curr_click.getColumn();
                            buttonBoard[prev_x][prev_y].setIcon(empty);
                            buttonBoard[5][4].setIcon(getTexturePath(curr_click));
                            pieceBoard[5][4] = curr_click;
                            pieceBoard[5][4].setX(5);
                            pieceBoard[5][4].setY(4);
                            pieceBoard[prev_x][prev_y] = null;
                            active = false;
                            curr_click = null;
                        }
                    } else {
                        curr_click = null;
                        active = false;
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

                    if (checkValidMove(5 ,5)) {
                        if (pieceBoard[5][5] == null || checkValidEat(5, 5)) {
                            int prev_x = curr_click.getRow();
                            int prev_y = curr_click.getColumn();
                            buttonBoard[prev_x][prev_y].setIcon(empty);
                            buttonBoard[5][5].setIcon(getTexturePath(curr_click));
                            pieceBoard[5][5] = curr_click;
                            pieceBoard[5][5].setX(5);
                            pieceBoard[5][5].setY(5);
                            pieceBoard[prev_x][prev_y] = null;
                            active = false;
                            curr_click = null;
                        }
                    } else {
                        curr_click = null;
                        active = false;
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
                    if (checkValidMove(5, 6)) {
                        if (pieceBoard[5][6] == null || checkValidEat(5, 6)) {
                            int prev_x = curr_click.getRow();
                            int prev_y = curr_click.getColumn();
                            buttonBoard[prev_x][prev_y].setIcon(empty);
                            buttonBoard[5][6].setIcon(getTexturePath(curr_click));
                            pieceBoard[5][6] = curr_click;
                            pieceBoard[5][6].setX(5);
                            pieceBoard[5][6].setY(6);
                            pieceBoard[prev_x][prev_y] = null;
                            active = false;
                            curr_click = null;
                        }
                    } else {
                        curr_click = null;
                        active = false;
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

                    if (checkValidMove(5, 7)) {
                        if (pieceBoard[5][7] == null || checkValidEat(5, 7)) {
                            int prev_x = curr_click.getRow();
                            int prev_y = curr_click.getColumn();
                            buttonBoard[prev_x][prev_y].setIcon(empty);
                            buttonBoard[5][7].setIcon(getTexturePath(curr_click));
                            pieceBoard[5][7] = curr_click;
                            pieceBoard[5][7].setX(5);
                            pieceBoard[5][7].setY(7);
                            pieceBoard[prev_x][prev_y] = null;
                            active = false;
                            curr_click = null;
                        }
                    } else {
                        curr_click = null;
                        active = false;
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

                    if (checkValidMove(6,0)) {
                        //check valid move function, return boolean, add as && in if statement
                        if (pieceBoard[6][0] == null || checkValidEat(6, 0)) {
                            int prev_x = curr_click.getRow();
                            int prev_y = curr_click.getColumn();
                            buttonBoard[prev_x][prev_y].setIcon(empty);
                            buttonBoard[6][0].setIcon(getTexturePath(curr_click));
                            pieceBoard[6][0] = curr_click;
                            pieceBoard[6][0].setX(6);
                            pieceBoard[6][0].setY(0);
                            pieceBoard[prev_x][prev_y] = null;
                            active = false;
                            curr_click = null;
                        }
                    } else {
                        curr_click = null;
                        active = false;
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

                    if (checkValidMove(6, 1)) {
                        if (pieceBoard[6][1] == null || checkValidEat(6, 1)) {
                            int prev_x = curr_click.getRow();
                            int prev_y = curr_click.getColumn();
                            buttonBoard[prev_x][prev_y].setIcon(empty);
                            buttonBoard[6][1].setIcon(getTexturePath(curr_click));
                            pieceBoard[6][1] = curr_click;
                            pieceBoard[6][1].setX(6);
                            pieceBoard[6][1].setY(1);
                            pieceBoard[prev_x][prev_y] = null;
                            active = false;
                            curr_click = null;
                        }
                    } else {
                        curr_click = null;
                        active = false;
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

                    if (checkValidMove(6, 2)) {
                        if (pieceBoard[6][2] == null || checkValidEat(6, 2)) {
                            int prev_x = curr_click.getRow();
                            int prev_y = curr_click.getColumn();
                            buttonBoard[prev_x][prev_y].setIcon(empty);
                            buttonBoard[6][2].setIcon(getTexturePath(curr_click));
                            pieceBoard[6][2] = curr_click;
                            pieceBoard[6][2].setX(6);
                            pieceBoard[6][2].setY(2);
                            pieceBoard[prev_x][prev_y] = null;
                            active = false;
                            curr_click = null;
                        }
                    } else {
                        curr_click = null;
                        active = false;
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

                    if (checkValidMove(6 ,3)) {
                        if (pieceBoard[6][3] == null || checkValidEat(6, 3)) {
                            int prev_x = curr_click.getRow();
                            int prev_y = curr_click.getColumn();
                            buttonBoard[prev_x][prev_y].setIcon(empty);
                            buttonBoard[6][3].setIcon(getTexturePath(curr_click));
                            pieceBoard[6][3] = curr_click;
                            pieceBoard[6][3].setX(6);
                            pieceBoard[6][3].setY(3);
                            pieceBoard[prev_x][prev_y] = null;
                            active = false;
                            curr_click = null;
                        }
                    } else {
                        curr_click = null;
                        active = false;
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

                    if (checkValidMove(6 ,4)) {
                        if (pieceBoard[6][4] == null || checkValidEat(6, 4)) {
                            int prev_x = curr_click.getRow();
                            int prev_y = curr_click.getColumn();
                            buttonBoard[prev_x][prev_y].setIcon(empty);
                            buttonBoard[6][4].setIcon(getTexturePath(curr_click));
                            pieceBoard[6][4] = curr_click;
                            pieceBoard[6][4].setX(6);
                            pieceBoard[6][4].setY(4);
                            pieceBoard[prev_x][prev_y] = null;
                            active = false;
                            curr_click = null;
                        }
                    } else {
                        curr_click = null;
                        active = false;
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

                    if (checkValidMove(6, 5)) {
                        if (pieceBoard[6][5] == null || checkValidEat(6, 5)) {
                            int prev_x = curr_click.getRow();
                            int prev_y = curr_click.getColumn();
                            buttonBoard[prev_x][prev_y].setIcon(empty);
                            buttonBoard[6][5].setIcon(getTexturePath(curr_click));
                            pieceBoard[6][5] = curr_click;
                            pieceBoard[6][5].setX(6);
                            pieceBoard[6][5].setY(5);
                            pieceBoard[prev_x][prev_y] = null;
                            active = false;
                            curr_click = null;
                        }
                    } else {
                        curr_click = null;
                        active = false;
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

                    if (checkValidMove(6, 6)) {
                        if (pieceBoard[6][6] == null || checkValidEat(6, 6)) {
                            int prev_x = curr_click.getRow();
                            int prev_y = curr_click.getColumn();
                            buttonBoard[prev_x][prev_y].setIcon(empty);
                            buttonBoard[6][6].setIcon(getTexturePath(curr_click));
                            pieceBoard[6][6] = curr_click;
                            pieceBoard[6][6].setX(6);
                            pieceBoard[6][6].setY(6);
                            pieceBoard[prev_x][prev_y] = null;
                            active = false;
                            curr_click = null;
                        }
                    } else {
                        curr_click = null;
                        active = false;
                    }
                }

            }

        });

        this.buttonBoard[6][7].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (!active) {
                    if (pieceBoard[6][7] != null) {
                        if (pieceBoard[6][7].getColor()) {
                            if (white_move) {
                                curr_click = pieceBoard[7][7];
                                active = true;
                            }
                        }
                        if (!pieceBoard[6][7].getColor()) {
                            if (!white_move) {
                                curr_click = pieceBoard[6][7];
                                active = true;
                            }
                        }
                    }
                } else {
                    //check valid move function, return boolean, add as && in if statement

                    if (checkValidMove(6,7 )) {
                        if (pieceBoard[6][7] == null || checkValidEat(6, 7)) {
                            int prev_x = curr_click.getRow();
                            int prev_y = curr_click.getColumn();
                            buttonBoard[prev_x][prev_y].setIcon(empty);
                            buttonBoard[6][7].setIcon(getTexturePath(curr_click));
                            pieceBoard[6][7] = curr_click;
                            pieceBoard[6][7].setX(6);
                            pieceBoard[6][7].setY(7);
                            pieceBoard[prev_x][prev_y] = null;
                            active = false;
                            curr_click = null;
                        }
                    } else {
                        curr_click = null;
                        active = false;
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

                    if (checkValidMove(7, 0)) {
                        if (pieceBoard[7][0] == null || checkValidEat(7, 0)) {
                            int prev_x = curr_click.getRow();
                            int prev_y = curr_click.getColumn();
                            buttonBoard[prev_x][prev_y].setIcon(empty);
                            buttonBoard[7][0].setIcon(getTexturePath(curr_click));
                            pieceBoard[7][0] = curr_click;
                            pieceBoard[7][0].setX(7);
                            pieceBoard[7][0].setY(0);
                            pieceBoard[prev_x][prev_y] = null;
                            active = false;
                            curr_click = null;
                        }
                    } else {
                        curr_click = null;
                        active = false;
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

                    if (checkValidMove(7, 1)) {
                        if (pieceBoard[7][1] == null || checkValidEat(7, 1)) {
                            int prev_x = curr_click.getRow();
                            int prev_y = curr_click.getColumn();
                            buttonBoard[prev_x][prev_y].setIcon(empty);
                            buttonBoard[7][1].setIcon(getTexturePath(curr_click));
                            pieceBoard[7][1] = curr_click;
                            pieceBoard[7][1].setX(7);
                            pieceBoard[7][1].setY(1);
                            pieceBoard[prev_x][prev_y] = null;
                            active = false;
                            curr_click = null;
                        }
                    } else {
                        curr_click = null;
                        active = false;
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

                    if (checkValidMove(7, 2)) {
                        if (pieceBoard[7][2] == null  || checkValidEat(7, 2)) {
                            int prev_x = curr_click.getRow();
                            int prev_y = curr_click.getColumn();
                            buttonBoard[prev_x][prev_y].setIcon(empty);
                            buttonBoard[7][2].setIcon(getTexturePath(curr_click));
                            pieceBoard[7][2] = curr_click;
                            pieceBoard[7][2].setX(7);
                            pieceBoard[7][2].setY(2);
                            pieceBoard[prev_x][prev_y] = null;
                            active = false;
                            curr_click = null;
                        }
                    } else {
                        curr_click = null;
                        active = false;
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

                    if (checkValidMove(7, 3)) {
                        if (pieceBoard[7][3] == null || checkValidEat(7, 3)) {
                            int prev_x = curr_click.getRow();
                            int prev_y = curr_click.getColumn();
                            buttonBoard[prev_x][prev_y].setIcon(empty);
                            buttonBoard[7][3].setIcon(getTexturePath(curr_click));
                            pieceBoard[7][3] = curr_click;
                            pieceBoard[7][3].setX(7);
                            pieceBoard[7][3].setY(3);
                            pieceBoard[prev_x][prev_y] = null;
                            active = false;
                            curr_click = null;
                        }
                    } else {
                        curr_click = null;
                        active = false;
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

                    if (checkValidMove(7, 4)) {
                        if (pieceBoard[7][4] == null || checkValidEat(7, 4)) {
                            int prev_x = curr_click.getRow();
                            int prev_y = curr_click.getColumn();
                            buttonBoard[prev_x][prev_y].setIcon(empty);
                            buttonBoard[7][4].setIcon(getTexturePath(curr_click));
                            pieceBoard[7][4] = curr_click;
                            pieceBoard[7][4].setX(7);
                            pieceBoard[7][4].setY(4);
                            pieceBoard[prev_x][prev_y] = null;
                            active = false;
                            curr_click = null;
                        }
                    } else {
                        curr_click = null;
                        active = false;
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

                    if (checkValidMove(7, 5)) {
                        if (pieceBoard[7][5] == null || checkValidEat(7, 5)) {
                            int prev_x = curr_click.getRow();
                            int prev_y = curr_click.getColumn();
                            buttonBoard[prev_x][prev_y].setIcon(empty);
                            buttonBoard[7][5].setIcon(getTexturePath(curr_click));
                            pieceBoard[7][5] = curr_click;
                            pieceBoard[7][5].setX(7);
                            pieceBoard[7][5].setY(5);
                            pieceBoard[prev_x][prev_y] = null;
                            active = false;
                            curr_click = null;
                        }
                    } else {
                        curr_click = null;
                        active = false;
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

                    if (checkValidMove(7, 6)) {
                        if (pieceBoard[7][6] == null || checkValidEat(7, 6)) {
                            int prev_x = curr_click.getRow();
                            int prev_y = curr_click.getColumn();
                            buttonBoard[prev_x][prev_y].setIcon(empty);
                            buttonBoard[7][6].setIcon(getTexturePath(curr_click));
                            pieceBoard[7][6] = curr_click;
                            pieceBoard[7][6].setX(7);
                            pieceBoard[7][6].setY(6);
                            pieceBoard[prev_x][prev_y] = null;
                            active = false;
                            curr_click = null;
                        }
                    } else {
                        curr_click = null;
                        active = false;
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

                    if (checkValidMove(7, 7)) {
                        if (pieceBoard[7][7] == null || checkValidEat(7, 7)) {
                            int prev_x = curr_click.getRow();
                            int prev_y = curr_click.getColumn();
                            buttonBoard[prev_x][prev_y].setIcon(empty);
                            buttonBoard[7][7].setIcon(getTexturePath(curr_click));
                            pieceBoard[7][7] = curr_click;
                            pieceBoard[7][7].setX(7);
                            pieceBoard[7][7].setY(7);
                            pieceBoard[prev_x][prev_y] = null;
                            active = false;
                            curr_click = null;
                        }
                    } else {
                        curr_click = null;
                        active = false;
                    }
                }
            }
        });
    }

    public ImageIcon getTexturePath(PlayingPiece piece) {
        switch (piece.getName()) {
            case "whitePawn":
                return new ImageIcon("src/piece_textures/w_pawn.png");
            case "whiteRook":
                return new ImageIcon("src/piece_textures/w_rook.png");
            case "whiteKnight":
                return new ImageIcon("src/piece_textures/w_knight.png");
            case "whiteBishop":
                return new ImageIcon("src/piece_textures/w_bishop.png");
            case "whiteQueen":
                return new ImageIcon("src/piece_textures/w_queen.png");
            case "whiteKing":
                return new ImageIcon("src/piece_textures/w_king.png");
            case "blackPawn":
                return new ImageIcon("src/piece_textures/b_pawn.png");
            case "blackRook":
                return new ImageIcon("src/piece_textures/b_rook.png");
            case "blackKnight":
                return new ImageIcon("src/piece_textures/b_knight.png");
            case "blackBishop":
                return new ImageIcon("src/piece_textures/b_bishop.png");
            case "blackQueen":
                return new ImageIcon("src/piece_textures/b_queen.png");
            case "blackKing":
                return new ImageIcon("src/piece_textures/b_king.png");
            default:
                break;
        }

        return null;

    }

    public boolean checkMate() {
        return false;
    }

    public void updateGUI() {
        if (white_move) {
            lowerPanel.remove(lowerIconB);

            ImageIcon lowerPanelImgWhite = new ImageIcon("src/gui_textures/lower_white.png");
            lowerIconW = new JLabel(lowerPanelImgWhite);

            lowerPanel.add(lowerIconW);
        } else {
            lowerPanel.remove(lowerIconW);

            ImageIcon lowerPanelImgBlack = new ImageIcon("src/gui_textures/lower_black.png");
            lowerIconB = new JLabel(lowerPanelImgBlack);

            lowerPanel.add(lowerIconB);
        }

    }

    public boolean checkValidEat(int x, int y) {
        if (pieceBoard[x][y].getColor()) {
            if (curr_click.getColor()) {
                return false;
            } else {
                updateGUI();
                return true;
            }
        } else if (!pieceBoard[x][y].getColor()) {
            if (!curr_click.getColor()) {
                return false;
            } else {
                updateGUI();
                return true;
            }
        }
        return true;
    }

    public boolean checkValidMove(int x, int y) {

        int prev_x = curr_click.getRow();
        int prev_y = curr_click.getColumn();

        //Checking for valid move intended for white pawn playing piece

        if (curr_click.getName().equals("whitePawn")) {

            if (curr_click.isPawn()) {

                if (x > prev_x + 2 || x <= prev_x) {
                    return false;
                }
                if (y < prev_y - 1 || y > prev_y + 1) {
                    return false;
                }

                curr_click.removeFirstPawnMove();
                white_move = false;
                updateGUI();
                return true;

            }

            if (x > prev_x + 1 || x <= prev_x) {
                return false;
            }
            if (y < prev_y - 1 || y > prev_y + 1) {
                return false;
            }

            white_move = false;
            updateGUI();
            return true;
        }

        //Checking for valid move intended for black pawn playing piece

        if (curr_click.getName().equals("blackPawn")) {

            if (curr_click.isPawn()) {

                if (x < prev_x - 2 || x >= prev_x) {
                    return false;
                }
                if (y < prev_y - 1 || y > prev_y + 1) {
                    return false;
                }

                curr_click.removeFirstPawnMove();
                white_move = true;
                updateGUI();
                return true;

            }

            if (x < prev_x - 1 || x >= prev_x) {
                return false;
            }
            if (y < prev_y - 1 || y > prev_y + 1) {
                return false;
            }

            white_move = true;
            updateGUI();
            return true;

        }

        //Checking for valid move intended for rook playing piece

        if (curr_click.getName().equals("whiteRook") || curr_click.getName().equals("blackRook")) {

            if (x != prev_x) {
                if (y != prev_y) {
                    return false;
                }
            }

            if (y != prev_y) {
                if (x != prev_x) {
                    return false;
                }
            }

            int i = x;
            int j = y;

            while (i != prev_x || j != prev_y) {
                if (x == prev_x) {
                    --j;
                }

                if (y == prev_y) {
                    --i;
                }

                if (pieceBoard[i][j] != null && (i != prev_x) || (j != prev_y)) {
                    System.out.println("piece in way of intended rook move");
                    return false;
                }
            }

            System.out.println("hee");

            if (white_move) {
                white_move = false;
            } else if (!white_move){
                white_move = true;
            }

            updateGUI();
            return true;
        }

        //Checking for valid move intended for knight playing piece

        if (curr_click.getName().equals("whiteKnight") || curr_click.getName().equals("blackKnight")) {

            if (y == prev_y || x == prev_x) {
                return false;
            }

            if (x != prev_x + 2 && x != prev_x - 2 && x != prev_x + 1 && x != prev_x -1) {
                return false;
            }

            if (x == prev_x + 2 || x == prev_x - 2) {
                if (y != prev_y - 1 && y != prev_y + 1) {
                    return false;
                }
            }

            if (x == prev_x + 1 || x == prev_x - 1) {
                if (y != prev_y - 2 && y != prev_y + 2) {
                    return false;
                }
            }

            if (white_move) {
                white_move = false;
            } else if (!white_move){
                white_move = true;
            }

            updateGUI();
            return true;
        }

        if (curr_click.getName().equals("whiteBishop") || curr_click.getName().equals("blackBishop")) {

            if (x == prev_x || y == prev_y) {
                return false;
            }

            if (Math.abs((x - prev_x) / (y - prev_y)) != 1) {
                return false;
            }

            int i = x;
            int j = y;

            System.out.printf("\nStarting point: (%d, %d)", prev_x, prev_y);

            while (i != prev_x && j != prev_y) {
                if (x < prev_x) {
                    if (y < prev_y) {
                        ++i;
                        ++j;
                    }
                    if (y > prev_y) {
                        ++i;
                        --j;
                    }
                }

                if (x > prev_x) {
                    if (y < prev_y) {
                        --i;
                        ++j;
                    }
                    if (y > prev_y) {
                        --i;
                        --j;
                    }
                }

                System.out.printf("\n(%d, %d)", i, j);

                if (pieceBoard[i][j] != null && (i != prev_x) && (j != prev_y)) {
                    System.out.printf("\npiece is blocking path for bishop");
                    return false;
                }

            }

            System.out.println("\npath for bishop is valid");

            if (white_move) {
                white_move = false;
            } else if (!white_move){
                white_move = true;
            }

            updateGUI();
            return true;

        }

        if (curr_click.getName().equals("whiteQueen") || curr_click.getName().equals("blackQueen")) {

            if (y - prev_y != 0) {
                if (Math.abs((x - prev_x) / (y - prev_y)) == 1) {

                    int i = x;
                    int j = y;

                    while (i != prev_x && j != prev_y) {
                        if (x < prev_x) {
                            if (y < prev_y) {
                                ++i;
                                ++j;
                            }
                            if (y > prev_y) {
                                ++i;
                                --j;
                            }
                        }

                        if (x > prev_x) {
                            if (y < prev_y) {
                                --i;
                                ++j;
                            }
                            if (y > prev_y) {
                                --i;
                                --j;
                            }
                        }

                        if (pieceBoard[i][j] != null && (i != prev_x) && (j != prev_y)) {
                            return false;
                        }

                    }

                }

            } else {

                if (x != prev_x) {
                    if (y != prev_y) {
                        return false;
                    }
                }

                if (y != prev_y) {
                        if (x != prev_x) {
                            return false;
                        }
                }
            }

            if (white_move) {
                white_move = false;
            } else if (!white_move){
                white_move = true;
            }

            updateGUI();
            return true;
        }

        if (curr_click.getName().equals("whiteKing") || curr_click.getName().equals("blackKing")) {

            if (x == prev_x || x == prev_x + 1 || x == prev_x - 1) {
                if (y != prev_y + 1 && y != prev_y - 1) {
                    return false;
                }

            } else {
                return false;
            }
            
            if (white_move) {
                white_move = false;
            } else if (!white_move){
                white_move = true;
            }

            updateGUI();
            return true;

        }



        return false;
    }


}
