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
        outerPanel.setBackground(Color.black);
        outerPanel.setSize(600, 300);
        JPanel boardPanel = new JPanel(new GridLayout(8,8));
        boardPanel.setSize(600, 300);
        boardPanel.setBackground(Color.black);

        this.boardPanel = boardPanel;
        this.guiFrame = guiFrame;

        this.initializeBoard();

        JPanel upperPanel = new JPanel();
        upperPanel.setBackground(Color.black);
        upperPanel.setSize(600, 100);
        lowerPanel = new JPanel();
        lowerPanel.setSize(600, 150);
        lowerPanel.setBackground(Color.black);


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

    public void checkMove(int row, int col) {

        if (!active) {
            if (pieceBoard[row][col] != null) {
                // checks to see if button clicked is available to current player
                if (pieceBoard[row][col].getColor()) {
                    if (white_move) {
                        curr_click = pieceBoard[row][col];
                        active = true;
                    }
                }
                if (!pieceBoard[row][col].getColor()) {
                    if (!white_move) {
                        curr_click = pieceBoard[row][col];
                        active = true;
                    }
                }
            }
        } else {

            // declare int coordinates for previously clicked button
            int prev_x = curr_click.getRow();
            int prev_y = curr_click.getColumn();

            //check valid move function, return boolean, add as && in if statement
            if (checkValidMove(row ,col) && (pieceBoard[row][col] == null || checkValidEat(row, col))) {

                updateGUI();

                // set text of previously clicked button to " "
                buttonBoard[prev_x][prev_y].setIcon(empty);

                // set text of newly clicked button to the previously clicked
                buttonBoard[row][col].setIcon(getTexturePath(curr_click));

                // set newly clicked piece object to previously clicked object in model
                pieceBoard[row][col] = curr_click;
                pieceBoard[row][col].setX(row);
                pieceBoard[row][col].setY(col);

                // set previously clicked object to null
                pieceBoard[prev_x][prev_y] = null;

                active = false;
                curr_click = null;

                updateTurn();

            } else {
                curr_click = null;
                active = false;
            }
        }

    }


    private void ActivateActionListeners() {

        /*
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                this.buttonBoard[i][j].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        checkMove(i, j);
                    }
                });

            }
        }
        */

        this.buttonBoard[0][0].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkMove(0, 0);
            }
        });

        this.buttonBoard[0][1].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkMove(0, 1);
            }
        });

        this.buttonBoard[0][2].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkMove(0, 2);
            }
        });

        this.buttonBoard[0][3].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkMove(0, 3);
            }
        });

        this.buttonBoard[0][4].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkMove(0, 4);
            }
        });

        this.buttonBoard[0][5].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkMove(0, 5);
            }
        });

        this.buttonBoard[0][6].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkMove(0, 6);
            }
        });

        this.buttonBoard[0][7].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkMove(0, 7);
            }
        });

        this.buttonBoard[1][0].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkMove(1, 0);
            }
        });

        this.buttonBoard[1][1].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkMove(1, 1);
            }
        });

        this.buttonBoard[1][2].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkMove(1, 2);
            }
        });

        this.buttonBoard[1][3].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkMove(1, 3);
            }
        });

        this.buttonBoard[1][4].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkMove(1, 4);
            }
        });

        this.buttonBoard[1][5].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkMove(1, 5);
            }
        });

        this.buttonBoard[1][6].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkMove(1, 6);
            }
        });

        this.buttonBoard[1][7].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkMove(1, 7);
            }
        });

        this.buttonBoard[2][0].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkMove(2, 0);
            }
        });

        this.buttonBoard[2][1].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkMove(2, 1);
            }
        });

        this.buttonBoard[2][2].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkMove(2, 2);
            }
        });

        this.buttonBoard[2][3].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkMove(2, 3);
            }
        });

        this.buttonBoard[2][4].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkMove(2, 4);
            }
        });

        this.buttonBoard[2][5].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkMove(2, 5);
            }
        });

        this.buttonBoard[2][6].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkMove(2, 6);
            }
        });

        this.buttonBoard[2][7].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkMove(2, 7);
            }
        });

        this.buttonBoard[3][0].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkMove(3, 0);
            }
        });

        this.buttonBoard[3][1].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkMove(3, 1);
            }
        });

        this.buttonBoard[3][2].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkMove(3, 2);
            }
        });

        this.buttonBoard[3][3].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkMove(3, 3);
            }
        });

        this.buttonBoard[3][4].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkMove(3, 4);
            }
        });

        this.buttonBoard[3][5].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkMove(3, 5);
            }
        });

        this.buttonBoard[3][6].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkMove(3, 6);
            }
        });

        this.buttonBoard[3][7].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkMove(3, 7);
            }
        });

        this.buttonBoard[4][0].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkMove(4, 0);
            }
        });

        this.buttonBoard[4][1].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkMove(4, 1);
            }
        });

        this.buttonBoard[4][2].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkMove(4, 2);
            }
        });

        this.buttonBoard[4][3].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkMove(4, 3);
            }
        });

        this.buttonBoard[4][4].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkMove(4, 4);
            }
        });

        this.buttonBoard[4][5].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkMove(4, 5);
            }
        });

        this.buttonBoard[4][6].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkMove(4, 6);
            }
        });

        this.buttonBoard[4][7].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkMove(4, 7);
            }
        });

        this.buttonBoard[5][0].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkMove(5, 0);
            }
        });

        this.buttonBoard[5][1].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkMove(5, 1);
            }
        });

        this.buttonBoard[5][2].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkMove(5, 2);
            }
        });

        this.buttonBoard[5][3].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkMove(5, 3);
            }
        });

        this.buttonBoard[5][4].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkMove(5, 4);
            }
        });

        this.buttonBoard[5][5].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkMove(5, 5);
            }
        });

        this.buttonBoard[5][6].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkMove(5, 6);
            }
        });

        this.buttonBoard[5][7].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkMove(5, 7);
            }
        });

        this.buttonBoard[6][0].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkMove(6, 0);
            }
        });

        this.buttonBoard[6][1].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkMove(6, 1);
            }
        });

        this.buttonBoard[6][2].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkMove(6, 2);
            }
        });

        this.buttonBoard[6][3].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkMove(6, 3);
            }
        });

        this.buttonBoard[6][4].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkMove(6, 4);
            }
        });

        this.buttonBoard[6][5].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkMove(6, 5);
            }
        });

        this.buttonBoard[6][6].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkMove(6, 6);
            }
        });

        this.buttonBoard[6][7].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkMove(6, 7);
            }
        });

        this.buttonBoard[7][0].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkMove(7, 0);
            }
        });

        this.buttonBoard[7][1].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkMove(7, 1);
            }
        });

        this.buttonBoard[7][2].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkMove(7, 2);
            }
        });

        this.buttonBoard[7][3].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkMove(7, 3);
            }
        });

        this.buttonBoard[7][4].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkMove(7, 4);
            }
        });

        this.buttonBoard[7][5].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkMove(7, 5);
            }
        });

        this.buttonBoard[7][6].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkMove(7, 6);
            }
        });

        this.buttonBoard[7][7].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkMove(7, 7);
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

    public boolean checkmate() {
        return false;
    }

    public void updateTurn() {
        if (white_move) {
            white_move = false;
        } else if (!white_move){
            white_move = true;
        }
    }

    public void updateGUI() {

        //if the previous move was a white turn -> change GUI to say black turn
        if (white_move) {

            lowerPanel.remove(lowerIconW);

            ImageIcon lowerPanelImgBlack = new ImageIcon("src/gui_textures/lower_black.png");
            lowerIconB = new JLabel(lowerPanelImgBlack);

            lowerPanel.add(lowerIconB);
        } else {

            // if the previous move was a black turn -> change GUI to say white turn

            lowerPanel.remove(lowerIconB);

            ImageIcon lowerPanelImgWhite = new ImageIcon("src/gui_textures/lower_white.png");
            lowerIconW = new JLabel(lowerPanelImgWhite);

            lowerPanel.add(lowerIconW);
        }

    }

    public boolean checkValidEat(int x, int y) {
        if (pieceBoard[x][y].getColor()) {
            if (curr_click.getColor()) {
                return false;
            } else {
                return true;
            }
        } else if (!pieceBoard[x][y].getColor()) {
            if (!curr_click.getColor()) {
                return false;
            } else {
                return true;
            }
        }
        return true;
    }

    public boolean checkValidMove(int x, int y) {

        if (white_move)
            System.out.println("It is a white turn");
        else
            System.out.println("It is a black turn");

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
                return true;

            }

            if (x > prev_x + 1 || x <= prev_x) {
                return false;
            }
            if (y < prev_y - 1 || y > prev_y + 1) {
                return false;
            }

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
                return true;

            }

            if (x < prev_x - 1 || x >= prev_x) {
                return false;
            }
            if (y < prev_y - 1 || y > prev_y + 1) {
                return false;
            }

            return true;

        }

        //Checking for valid move intended for rook playing piece

        if (curr_click.getName().equals("whiteRook") || curr_click.getName().equals("blackRook")) {

            if (x != prev_x) {
                if (y != prev_y) {
                    return false;
                }
            }

            int i = x;
            int j = y;

            while ((i != prev_x) || (j != prev_y)) {

                if (i != x && i != y) {
                    if (pieceBoard[i][j] != null) {
                        System.out.println("piece in way of intended rook move");
                        return false;
                    }
                }

                if (x == prev_x) {

                    if (y < prev_y) {
                        ++j;
                    } else {
                        --j;
                    }
                }

                if (y == prev_y) {
                    if (x < prev_x) {
                        ++i;
                    } else {
                        --i;
                    }
                }
            }

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

            }

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

            return true;

        }

        return false;
    }


}
