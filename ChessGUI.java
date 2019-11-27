import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.lang.Math;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.*;



public class ChessGUI {

    private PlayingPiece[][] pieceBoard;
    private JButton[][] buttonBoard;

    private JPanel boardPanel;
    private JFrame guiFrame;
    private boolean active = false;
    private PlayingPiece curr_click;
    private PlayingPiece black_king;
    private PlayingPiece white_king;

    private int row;
    private int col;

    private JLabel lowerIconW;
    private JLabel lowerIconB;
    private JPanel lowerPanel;

    private boolean white_move = true;

    private ImageIcon empty = new ImageIcon("src/piece_textures/empty.png");

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

        updateGUI(true);

        JLabel topIcon = new JLabel(upperPanelImg);

        lowerPanel.add(lowerIconW);

        upperPanel.add(topIcon);

        guiFrame.add(outerPanel);

        guiFrame.setVisible(true);

        guiFrame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                guiFrame.setSize(600, 850);
            }
        });

    }

    private void initializeBoard() {

        //initialize JButton 2D matrix
        //initialize PlayingPiece 2D matrix
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                row = i;
                col = j;
                if (!(i >= 2 && i <= 5)) {

                    boolean white = true;
                    String name;

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
                                    this.white_king = this.pieceBoard[row][col];
                                } else {
                                    name = "blackKing";
                                    white = false;
                                    this.pieceBoard[row][col] = new PlayingPiece(i, j, name, white);
                                    ImageIcon img = new ImageIcon("src/piece_textures/b_king.png");
                                    this.buttonBoard[row][col] = new JButton(img);
                                    this.boardPanel.add(this.buttonBoard[i][j]);
                                    this.black_king = this.pieceBoard[row][col];
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
        // for loop to initialize an alternating color pattern for buttons
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                JButton button = this.buttonBoard[i][j];
                if ((j + i) % 2 == 0) {
                    button.setBackground(Color.WHITE);
                    button.setForeground(Color.WHITE);
                    button.setOpaque(true);

                } else {
                    Color brown = new Color(18 ,12 , 7);
                    button.setForeground(brown);
                    button.setBackground(brown);
                    button.setOpaque(true);
                    button.setBorder(null);
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
                    } else {
                        curr_click = null;
                        active = false;
                    }
                }
                if (!pieceBoard[row][col].getColor()) {
                    if (!white_move) {
                        curr_click = pieceBoard[row][col];
                        active = true;
                    } else {
                        curr_click = null;
                        active = false;
                    }
                }
            }
        } else {
            // declare int coordinates for previously clicked button
            int prev_x = curr_click.getRow();
            int prev_y = curr_click.getColumn();
            //check valid move function, return boolean, add as && in if statement
            if (checkValidMove(row, col) && checkValidEat(row, col)) {

                // set text of previously clicked button to " "
                buttonBoard[prev_x][prev_y].setIcon(empty);
                buttonBoard[prev_x][prev_y].setBorder(null);

                // set text of newly clicked button to the previously clicked
                buttonBoard[row][col].setIcon(getTexturePath(curr_click));
                buttonBoard[row][col].setBorder(null);

                // set newly clicked piece object to previously clicked object in model
                pieceBoard[row][col] = curr_click;
                pieceBoard[row][col].setX(row);
                pieceBoard[row][col].setY(col);

                // set previously clicked object to null
                pieceBoard[prev_x][prev_y] = null;

                active = false;
                curr_click = null;

                updateGUI(false);
                updateTurn();

                checkmate();
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

    public boolean inDanger(int x, int y, boolean white, boolean second) {
        int row = x;
        int col = y;
        //check for pawn
        try {
            if (pieceBoard[row + 1][col + 1] != null || pieceBoard[row + 1][col - 1] != null) {
                if (pieceBoard[row + 1][col + 1] != null) {
                    if (pieceBoard[row + 1][col + 1].getName().equals("blackPawn")) {
                        if (second) {
                            if (white) {
                                return true;
                            }
                        } else {
                            if (!inDanger(row + 1, col + 1, false, true)) {
                                if (white) {
                                    return true;
                                }
                            }
                        }
                    }
                    if (pieceBoard[row + 1][col + 1].getName().equals("whitePawn")) {
                        if (second) {
                            if (!white) {
                                return true;
                            }
                        } else {
                            if (!inDanger(row + 1, col + 1, true, true)) {
                                if (!white) {
                                    return true;
                                }
                            }
                        }
                    }
                }
                if (pieceBoard[row + 1][col - 1] != null) {
                    if (pieceBoard[row + 1][col - 1].getName().equals("blackPawn")) {
                        if (second) {
                            if (white) {
                                return true;
                            }
                        } else {
                            if (!inDanger(row + 1, col - 1, false, true)) {
                                if (white) {
                                    return true;
                                }
                            }
                        }
                    }
                    if (pieceBoard[row + 1][col - 1].getName().equals("whitePawn")) {
                        if (second) {
                            if (!white) {
                                return true;
                            }
                        } else {
                            if (!inDanger(row + 1, col - 1, true, true)) {
                                if (!white) {
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        } catch (IndexOutOfBoundsException e) {
        }

        //check for bishop or queen
        row = x;
        col = y;

        ++row;
        ++col;
        while (row != 8 && col != 8) {
            if (pieceBoard[row][col] != null) {
                if (pieceBoard[row][col].getName().equals("blackBishop")
                        || pieceBoard[row][col].getName().equals("blackQueen")) {
                    if (second) {
                        if (white) {
                            return true;
                        }
                    } else {
                        if (!inDanger(row, col, false, true)) {
                            if (white) {
                                return true;
                            }
                        }
                    }
                } else if (pieceBoard[row][col].getName().equals("whiteBishop")
                        || pieceBoard[row][col].getName().equals("whiteQueen"))  {
                    if (second) {
                        if (!white) {
                            return true;
                        }
                    } else {
                        if (!inDanger(row, col, true, true)) {
                            if (!white) {
                                return true;
                            }
                        }
                    }
                } else {
                    break;
                }
            }
            ++row;
            ++col;
        }

        row = x;
        col = y;

        ++row;
        --col;
        while (row != 8 && col >= 0) {
            if (pieceBoard[row][col] != null) {
                if (pieceBoard[row][col].getName().equals("blackBishop")
                        || pieceBoard[row][col].getName().equals("blackQueen")) {
                    if (second) {
                        if (white) {
                            return true;
                        }
                    } else {
                        if (!inDanger(row, col, false, true)) {
                            if (white) {
                                return true;
                            }
                        }
                    }
                } else if (pieceBoard[row][col].getName().equals("whiteBishop")
                        || pieceBoard[row][col].getName().equals("whiteQueen")) {
                    if (second) {
                        if (!white) {
                            return true;
                        }
                    } else {
                        if (!inDanger(row, col, true, true)) {
                            if (!white) {
                                return true;
                            }
                        }
                    }
                } else {
                    break;
                }
            }
            ++row;
            --col;
        }

        row = x;
        col = y;

        --row;
        ++col;
        while (row >= 0 && col != 8) {
            if (pieceBoard[row][col] != null) {
                if (pieceBoard[row][col].getName().equals("blackBishop")
                        || pieceBoard[row][col].getName().equals("blackQueen")) {
                    if (second) {
                        if (white) {
                            return true;
                        }
                    } else {
                        if (!inDanger(row, col, false, true)) {
                            if (white) {
                                return true;
                            }
                        }
                    }
                } else if (pieceBoard[row][col].getName().equals("whiteBishop")
                        || pieceBoard[row][col].getName().equals("whiteQueen")) {
                    if (second) {
                        if (!white) {
                            return true;
                        }
                    } else {
                        if (!inDanger(row, col, true, true)) {
                            if (!white) {
                                return true;
                            }
                        }
                    }
                } else {
                    break;
                }
            }
            --row;
            ++col;
        }

        row = x;
        col = y;

        --row;
        --col;
        while (row >= 0 && col >= 0) {
            if (pieceBoard[row][col] != null) {
                if (pieceBoard[row][col].getName().equals("blackBishop")
                        || pieceBoard[row][col].getName().equals("blackQueen")) {
                    if (second) {
                        if (white) {
                            return true;
                        }
                    } else {
                        if (!inDanger(row, col, false, true)) {
                            if (white) {
                                return true;
                            }
                        }
                    }
                } else if (pieceBoard[row][col].getName().equals("whiteBishop")
                        || pieceBoard[row][col].getName().equals("whiteQueen")) {
                    if (second) {
                        if (!white) {
                            return true;
                        }
                    } else {
                        if (!inDanger(row, col, true, true)) {
                            if (!white) {
                                return true;
                            }
                        }
                    }
                } else {
                    break;
                }
            }
            --row;
            --col;
        }

        //check for rook or queen
        row = x;
        col = y;

        --row;
        while (row >= 0) {
            if (pieceBoard[row][col] != null) {
                if (pieceBoard[row][col].getName().equals("blackRook")
                        || pieceBoard[row][col].getName().equals("blackQueen")) {
                    if (second) {
                        if (white) {
                            return true;
                        }
                    } else {
                        if (!inDanger(row, col, false, true)) {
                            if (white) {
                                return true;
                            }
                        }
                    }
                } else if (pieceBoard[row][col].getName().equals("whiteRook")
                        || pieceBoard[row][col].getName().equals("whiteQueen")) {
                    if (second) {
                        if (!white) {
                            return true;
                        }
                    } else {
                        if (!inDanger(row, col, true, true)) {
                            if (!white) {
                                return true;
                            }
                        }
                    }
                } else {
                    break;
                }
            }
            --row;
        }

        row = x;
        col = y;

        ++row;
        while (row != 8) {
            if (pieceBoard[row][col] != null) {
                if (pieceBoard[row][col].getName().equals("blackRook")
                        || pieceBoard[row][col].getName().equals("blackQueen")) {
                    if (second) {
                        if (white) {
                            return true;
                        }
                    } else {
                        if (!inDanger(row, col, false, true)) {
                            if (white) {
                                return true;
                            }
                        }
                    }
                } else if (pieceBoard[row][col].getName().equals("whiteRook")
                        || pieceBoard[row][col].getName().equals("whiteQueen")) {
                    if (second) {
                        if (!white) {
                            return true;
                        }
                    } else {
                        if (!inDanger(row, col, true, true)) {
                            if (!white) {
                                return true;
                            }
                        }
                    }
                } else {
                    break;
                }
            }
            ++row;
        }

        row = x;
        col = y;

        --col;
        while (col >= 0) {
            if (pieceBoard[row][col] != null) {
                if (pieceBoard[row][col].getName().equals("blackRook")
                        || pieceBoard[row][col].getName().equals("blackQueen")) {
                    if (second) {
                        if (white) {
                            return true;
                        }
                    } else {
                        if (!inDanger(row, col, false, true)) {
                            if (white) {
                                return true;
                            }
                        }
                    }
                } else if (pieceBoard[row][col].getName().equals("whiteRook")
                        || pieceBoard[row][col].getName().equals("whiteQueen")) {
                    if (second) {
                        if (!white) {
                            return true;
                        }
                    } else {
                        if (!inDanger(row, col, true, true)) {
                            if (!white) {
                                return true;
                            }
                        }
                    }
                } else {
                    break;
                }
            }
            --col;
        }

        row = x;
        col = y;

        ++col;
        while (col != 8) {
            if (pieceBoard[row][col] != null) {
                if (pieceBoard[row][col].getName().equals("blackRook")
                        || pieceBoard[row][col].getName().equals("blackQueen")) {
                    if (second) {
                        if (white) {
                            return true;
                        }
                    } else {
                        if (!inDanger(row, col, false, true)) {
                            if (white) {
                                return true;
                            }
                        }
                    }
                } else if (pieceBoard[row][col].getName().equals("whiteRook")
                        || pieceBoard[row][col].getName().equals("whiteQueen")) {
                    if (second) {
                        if (!white) {
                            return true;
                        }
                    } else {
                        if (!inDanger(row, col, true, true)) {
                            if (!white) {
                                return true;
                            }
                        }
                    }
                } else {
                    break;
                }
            }
            ++col;
        }

        //check for knight
        row = x;
        col = y;
        if (row + 2 <= 7) {
            if (col - 1 >= 0) {
                if (pieceBoard[row + 2][col - 1] != null) {
                    if (pieceBoard[row + 2][col - 1].getName().equals("blackKnight")) {
                        if (second) {
                            if (white) {
                                return true;
                            }
                        } else {
                            if (!inDanger(row + 2, col - 1, false, true)) {
                                if (white) {
                                    return true;
                                }
                            }
                        }
                    }
                    if (pieceBoard[row + 2][col - 1].getName().equals("whiteKnight")) {
                        if (second) {
                            if (!white) {
                                return true;
                            }
                        } else {
                            if (!inDanger(row + 2, col - 1, true, true)) {
                                if (!white) {
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
            if (col + 1 <= 7) {
                if (pieceBoard[row + 2][col + 1] != null) {
                    if (pieceBoard[row + 2][col + 1].getName().equals("blackKnight")) {
                        if (second) {
                            if (white) {
                                return true;
                            }
                        } else {
                            if (!inDanger(row + 2,col + 1, false, true)) {
                                if (white) {
                                    return true;
                                }
                            }
                        }
                    }
                    if (pieceBoard[row + 2][col + 1].getName().equals("whiteKnight")) {
                        if (second) {
                            if (!white) {
                                return true;
                            }
                        } else {
                            if (!inDanger(row + 2, col + 1, true, true)) {
                                if (!white) {
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }

        if (row + 1 <= 7) {
            if (col - 2 >= 0) {
                if (pieceBoard[row + 1][col - 2] != null) {
                    if (pieceBoard[row + 1][col - 2].getName().equals("blackKnight")) {
                        if (second) {
                            if (white) {
                                return true;
                            }
                        } else {
                            if (!inDanger(row + 1, col - 2, false, true)) {
                                if (white) {
                                    return true;
                                }
                            }
                        }
                    }
                    if (pieceBoard[row + 1][col - 2].getName().equals("whiteKnight")) {
                        if (second) {
                            if (!white) {
                                return true;
                            }
                        } else {
                            if (!inDanger(row + 1, col - 2, true, true)) {
                                if (!white) {
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
            if (col + 2 <= 7) {
                if (pieceBoard[row + 1][col + 2] != null) {
                    if (pieceBoard[row + 1][col + 2].getName().equals("blackKnight")) {
                        if (second) {
                            if (white) {
                                return true;
                            }
                        } else {
                            if (!inDanger(row + 1, col + 2, false, true)) {
                                if (white) {
                                    return true;
                                }
                            }
                        }
                    }
                    if (pieceBoard[row + 1][col + 2].getName().equals("whiteKnight")) {
                        if (second) {
                            if (!white) {
                                return true;
                            }
                        } else {
                            if (!inDanger(row + 1, col + 2, true, true)) {
                                if (!white) {
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }

        if (row - 1 >= 0) {
            if (col - 2 >= 0) {
                if (pieceBoard[row - 1][col - 2] != null) {
                    if (pieceBoard[row - 1][col - 2].getName().equals("blackKnight")) {
                        if (second) {
                            if (white) {
                                return true;
                            }
                        } else {
                            if (!inDanger(row - 1, col - 2, false, true)) {
                                if (white) {
                                    return true;
                                }
                            }
                        }
                    }
                    if (pieceBoard[row - 1][col - 2].getName().equals("whiteKnight")) {
                        if (second) {
                            if (!white) {
                                return true;
                            }
                        } else {
                            if (!inDanger(row - 1, col - 2, true, true)) {
                                if (!white) {
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
            if (col + 2 <= 7) {
                if (pieceBoard[row - 1][col + 2] != null) {
                    if (pieceBoard[row - 1][col + 2].getName().equals("blackKnight")) {
                        if (second) {
                            if (white) {
                                return true;
                            }
                        } else {
                            if (!inDanger(row - 1, col + 2, false, true)) {
                                if (white) {
                                    return true;
                                }
                            }
                        }
                    }
                    if (pieceBoard[row - 1][col + 2].getName().equals("whiteKnight")) {
                        if (second) {
                            if (!white) {
                                return true;
                            }
                        } else {
                            if (!inDanger(row - 1, col + 2, true, true)) {
                                if (!white) {
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }

        if (row - 2 >= 0) {
            if (col - 1 >= 0) {
                if (pieceBoard[row - 2][col - 1] != null) {
                    if (pieceBoard[row - 2][col - 1].getName().equals("blackKnight")) {
                        if (second) {
                            if (white) {
                                return true;
                            }
                        } else {
                            if (!inDanger(row - 2, col - 1, false, true)) {
                                if (white) {
                                    return true;
                                }
                            }
                        }
                    }
                    if (pieceBoard[row - 2][col - 1].getName().equals("whiteKnight")) {
                        if (second) {
                            if (!white) {
                                return true;
                            }
                        } else {
                            if (!inDanger(row - 2, col - 1, true, true)) {
                                if (!white) {
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
            if (col + 1 <= 7) {
                if (pieceBoard[row - 2][col + 1] != null) {
                    if (pieceBoard[row - 2][col + 1].getName().equals("blackKnight")) {
                        if (second) {
                            if (white) {
                                return true;
                            }
                        } else {
                            if (!inDanger(row - 2, col + 1, false, true)) {
                                if (white) {
                                    return true;
                                }
                            }
                        }
                    }
                    if (pieceBoard[row - 2][col + 1].getName().equals("whiteKnight")) {
                        if (second) {
                            if (!white) {
                                return true;
                            }
                        } else {
                            if (!inDanger(row - 2, col + 1, true, true)) {
                                if (!white) {
                                    return true;
                                }
                            }
                        }
                    }
                }
            }

        }

        return false;
    }


    public void checkmate() {
        //check to see if black king is under checkmate
        if (inDanger(black_king.getRow(), black_king.getColumn(), false, false)) {
            int row = black_king.getRow();
            int col = black_king.getColumn();

            for (int i = -1; i < 2; i++) {
                for (int j = -1; j < 2; j++) {
                    if (i != black_king.getRow() && j != black_king.getColumn()) {
                        if (!(row + i <= 7 && row + i >= 0 && col + j <= 7 && col + j >= 0)) {
                            continue;
                        } else {
                            if (pieceBoard[row + i][col + j] == null) {
                                if (!inDanger(row + i, col + j, false, false)) {
                                    return;
                                }
                            }
                        }
                    }
                }
            }

            // WHITE WINS
            System.out.println("white wins");
            updateCheckmateGUI(true);
        }

        //check to see if white king is under checkmate
        if (inDanger(white_king.getRow(), white_king.getColumn(), true, false)) {
            int row = white_king.getRow();
            int col = white_king.getColumn();

            for (int i = -1; i < 2; i++) {
                for (int j = -1; j < 2; j++) {
                    if (i != white_king.getRow() && j != white_king.getColumn()) {
                        if (!(row + i <= 7 && row + i >= 0 && col + j <= 7 && col + j >= 0)) {
                            continue;
                        } else {
                            if (pieceBoard[row + i][col + j] == null) {
                                if (!inDanger(row + i, col + j, true, false)) {
                                    return;
                                }
                            }
                        }
                    }
                }
            }

            // BLACK WINS
            System.out.println("black wins");
            updateCheckmateGUI(false);
        }
    }

    public void updateCheckmateGUI(boolean white) {
        if (white) {
            lowerPanel.removeAll();
            ImageIcon checkmate_white = new ImageIcon("src/gui_textures/checkmate_white.png");
            lowerIconW = new JLabel(checkmate_white);
            lowerPanel.add(lowerIconW);
        } else {
            lowerPanel.removeAll();
            ImageIcon checkmate_black = new ImageIcon("src/gui_textures/checkmate_black.png");
            lowerIconB = new JLabel(checkmate_black);
            lowerPanel.add(lowerIconB);
        }

    }

    public void updateGUI(boolean first) {
        if (first) {
            ImageIcon lowerPanelImgWhite = new ImageIcon("src/gui_textures/lower_white.png");
            lowerIconW = new JLabel(lowerPanelImgWhite);
            lowerPanel.add(lowerIconW);
            return;
        }
        System.out.println("updating gui");
        //if the previous move was a white turn -> change GUI to say black turn
        if (white_move) {
            lowerPanel.removeAll();
            ImageIcon lowerPanelImgBlack = new ImageIcon("src/gui_textures/lower_black.png");
            lowerIconB = new JLabel(lowerPanelImgBlack);
            lowerPanel.add(lowerIconB);
            System.out.println("updated the gui to black");
        } else {
            // if the previous move was a black turn -> change GUI to say white turn
            lowerPanel.removeAll();
            ImageIcon lowerPanelImgWhite = new ImageIcon("src/gui_textures/lower_white.png");
            lowerIconW = new JLabel(lowerPanelImgWhite);
            lowerPanel.add(lowerIconW);
            System.out.println("updated the gui to white");
        }
    }

    public void updateTurn() {
        if (white_move) {
            white_move = false;
        } else {
            white_move = true;
        }
    }

    public void playMoveSound() {
        File move_wav = new File("src/sound_effects/move.wav");
        try {
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(move_wav);
            Clip move_clip = AudioSystem.getClip();
            move_clip.open(audioInput);
            move_clip.start();
        } catch (Exception e) {
            System.out.println("Audio output failed");
        }
    }

    public void playEatSound() {
        File eat_wav = new File("src/sound_effects/eat.wav");
        try {
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(eat_wav);
            Clip eat_clip = AudioSystem.getClip();
            eat_clip.open(audioInput);
            eat_clip.start();
        } catch (Exception e) {
            System.out.println("Audio output failed");
        }
    }


    public boolean checkValidEat(int x, int y) {
        // Validating specific eating function for ONLY the pawn piece
        if (curr_click.getName().equals("whitePawn") || curr_click.getName().equals("blackPawn")) {
            if (pieceBoard[x][y] != null) {
                if (y != curr_click.getColumn() + 1 && y != curr_click.getColumn() - 1) {
                    return false;
                }
            } else {
                if (y != curr_click.getColumn()) {
                    return false;
                }
            }
        }

        if (pieceBoard[x][y] == null) {
            playMoveSound();
            return true;
        }

        if (pieceBoard[x][y].getColor()) {
            if (curr_click.getColor()) {
                return false;
            } else {
                playEatSound();
                return true;
            }
        } else if (!pieceBoard[x][y].getColor()) {
            if (!curr_click.getColor()) {
                return false;
            } else {
                playEatSound();
                return true;
            }
        }

        return false;
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
                if (y!= prev_y && y != prev_y + 1 && y != prev_y - 1) {
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
