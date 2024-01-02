import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * Provides a graphical user interface for the Checkers game.
 * It allows players to make moves, displays the game board, and shows the game result.
 * 
 * @author Rajvirsinh Jadeja
 * @version Version 0.1
 */
public class CheckersGUI 
{
	CheckersLogic logic;
	CheckersComputerPlayer computer;
	
	private boolean gameOver;
	private JLabel whoTurn;
	private JLabel moveSuc;
	private JTextField textF;
	private JButton confirmMove, backToMenu;
	private JPanel checkersBoard;
    JFrame frame;
	
	public CheckersGUI()
	{
		Board board = new Board();
        logic = new CheckersLogic(board);
        computer = new CheckersComputerPlayer(logic.getBoard());
        gameOver = false;
        confirmMove = new JButton("Enter Move");
        checkersBoard = new JPanel(new GridLayout(8, 8));
    }
	
	/**
     * Initializes the graphical user interface for the Checkers game.
     *
     * @param board The initial game board to display.
     */
    public void initizalizeGui(Board board)
    {
        frame = new JFrame("Sakura Checkers");
        frame.setSize(1200, 800);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 	   	BufferedImage imageLogo = null;
 	   	try 
 	   	{
 	   		imageLogo = ImageIO.read(getClass().getResource("CheckersBoardGameLogoWindows.png"));
 	   	}
 	   	catch (IOException e) 
 	   	{
 	   	    e.printStackTrace();
 	   	}
        frame.setIconImage(imageLogo);
        
        JLabel topCheckersLabel = new JLabel("       A         B          C         D         E          F         G         H");
        JLabel bottomCheckersLabel = new JLabel("       A         B          C         D         E          F         G         H");
        
        JPanel leftCheckersPabel = new JPanel(new GridLayout(8, 1));
        for (int i = 8; i >= 1; i--) 
        {
            JLabel leftLabel = new JLabel(Integer.toString(i));
            leftLabel.setFont(new Font("", Font.PLAIN, 30));
            leftCheckersPabel.add(leftLabel);
        }
        
        JPanel rightCheckersLabel = new JPanel(new BorderLayout());
        rightCheckersLabel.setBackground(new Color(220, 220, 220));
        JPanel input = new JPanel(new GridLayout(2, 1));
        rightCheckersLabel.add(input, BorderLayout.CENTER);
        
        JLabel spaceL = new JLabel("");
        rightCheckersLabel.add(spaceL, BorderLayout.WEST);
        spaceL.setPreferredSize(new Dimension(145,100));
        
        JLabel spaceR = new JLabel("");
        rightCheckersLabel.add(spaceR, BorderLayout.EAST);
        spaceR.setPreferredSize(new Dimension(145,100));
        
        whoTurn = new JLabel("playerX's Turn!");
        whoTurn.setFont(new Font("", Font.PLAIN, 30));
        rightCheckersLabel.add(whoTurn, BorderLayout.NORTH);
        whoTurn.setHorizontalAlignment(JLabel.CENTER);
        
        moveSuc = new JLabel(logic.valid);
        moveSuc.setFont(new Font("", Font.PLAIN, 30));
        rightCheckersLabel.add(moveSuc, BorderLayout.SOUTH);
        moveSuc.setHorizontalAlignment(JLabel.CENTER);
        
        textF = new JTextField();
        textF.setText("");
        textF.setHorizontalAlignment(JLabel.CENTER);
        textF.setPreferredSize(new Dimension(115, 10));
        input.add(textF);
        
        confirmMove = new JButton("Enter Move");
        confirmMove.setBackground(Color.PINK);
        confirmMove.setPreferredSize(new Dimension(115, 10));
        confirmMove.setFocusPainted(false);
        input.add(confirmMove);

        
        JPanel backMenu = new JPanel(new GridLayout(9, 1));
        rightCheckersLabel.add(backMenu, BorderLayout.EAST);
        backMenu.setBackground(new Color(220, 220, 220));
        JLabel fill = new JLabel("");
        JLabel fill2 = new JLabel("");
        JLabel fill3 = new JLabel("");
        JLabel fill4 = new JLabel("");
        JLabel fill5 = new JLabel("");
        JLabel fill6 = new JLabel("");
        JLabel fill7 = new JLabel("");
        JLabel fill8 = new JLabel("");
        backToMenu = new JButton("Back to Menu");
        backToMenu.setBackground(Color.PINK);
        backToMenu.setPreferredSize(new Dimension(150, 0));
        backToMenu.setFocusPainted(false);
        backMenu.add(fill);
        backMenu.add(fill2);
        backMenu.add(fill3);
        backMenu.add(fill4);
        backMenu.add(backToMenu);
        backMenu.add(fill5);
        backMenu.add(fill6);
        backMenu.add(fill7);
        backMenu.add(fill8);
        
        checkersBoard.setPreferredSize(new Dimension(100,100));
        topCheckersLabel.setFont(new Font("", Font.PLAIN, 30));
        bottomCheckersLabel.setFont(new Font("", Font.PLAIN, 30));
        frame.add(topCheckersLabel, BorderLayout.NORTH);
        frame.add(bottomCheckersLabel, BorderLayout.SOUTH);
        frame.add(leftCheckersPabel, BorderLayout.WEST);
        frame.add(checkersBoard, BorderLayout.CENTER);
        frame.add(rightCheckersLabel, BorderLayout.EAST);

        for (int row = 0; row < 8; row++) 
        {
            for (int col = 0; col < 8; col++) 
            {
                JPanel square = new JPanel(new BorderLayout());
                char cellValue = board.getCell(row, col).getPiece();
                if ((row + col) % 2 == 0) 
                {
                    square.setBackground(Color.WHITE);
                } 
                else 
                {
                    square.setBackground(Color.PINK);
                }
                JLabel coordinateLabel = new JLabel((8 - row)+ Character.toString((char)('a' + col)));
                coordinateLabel.setHorizontalAlignment(JLabel.CENTER);
                coordinateLabel.setVerticalAlignment(JLabel.BOTTOM);
                square.add(coordinateLabel, BorderLayout.CENTER);

                if(board.getCell(row, col).getPiece() != '_')
                {
	                JLabel pieceLabel = new JLabel(Character.toString(cellValue));
	                pieceLabel.setHorizontalAlignment(JLabel.CENTER);
	                pieceLabel.setVerticalAlignment(JLabel.CENTER);
	                pieceLabel.setFont(new Font("", Font.PLAIN, 55));
	                square.add(pieceLabel, BorderLayout.NORTH);
                }

                checkersBoard.add(square);
            }
        }
        frame.setResizable(false);
        frame.setVisible(true);
        frame.getContentPane().setBackground(new Color(220, 220, 220));
    }
    
    /**
     * Handles user and computer moves and updates the game state.
     *
     * @param who The current player's turn ("P" for player, "C" for computer).
     */
    public void checkersMoveGui(String who)
    {
    	backToMenu.addActionListener(e -> 
    	{
    		frame.dispose();
    		StartGame newMenu = new StartGame();
    		newMenu.setVisible(true);
    	});
    	confirmMove.addActionListener(e -> 
    	{
    		if (who.equals("P")) 
    		{
    		    if (logic.isMoveValid(textF.getText())) 
    		    {
    		        logic.makeMove();
    		        moveSuc.setText(logic.valid);
    		        if (logic.isGameOver()) 
    		        {
    		        	if(logic.getWinner() == 'X')
    		        		whoTurn.setText("PlayerX you Won!");
    		        	else if(logic.getWinner() == 'O')
    		        		whoTurn.setText("PlayerO you Won!");
    		            moveSuc.setText("Game Over!");
    		            confirmMove.setEnabled(false);
    		            gameOver = true;
    		        }
    		        else 
    		        {
        		        checkersBoard.removeAll();
        		        for (int row = 0; row < 8; row++) 
        		        {
        		            for (int col = 0; col < 8; col++) 
        		            {
        		                JPanel square = new JPanel(new BorderLayout());
        		                char cellValue = logic.getBoard().getCell(row, col).getPiece();
        		                if ((row + col) % 2 == 0) 
        		                {
        		                    square.setBackground(Color.WHITE);
        		                } 
        		                else 
        		                {
        		                    square.setBackground(Color.PINK);
        		                }
        		                JLabel coordinateLabel = new JLabel((8 - row) + Character.toString((char) ('a' + col)));
        		                coordinateLabel.setHorizontalAlignment(JLabel.CENTER);
        		                coordinateLabel.setVerticalAlignment(JLabel.BOTTOM);
        		                square.add(coordinateLabel, BorderLayout.CENTER);

        		                if (logic.getBoard().getCell(row, col).getPiece() != '_')
        		                {
        		                    JLabel pieceLabel = new JLabel(Character.toString(cellValue));
        		                    pieceLabel.setHorizontalAlignment(JLabel.CENTER);
        		                    pieceLabel.setVerticalAlignment(JLabel.CENTER);
        		                    pieceLabel.setFont(new Font("", Font.PLAIN, 55));
        		                    square.add(pieceLabel, BorderLayout.NORTH);
        		                }

        		                checkersBoard.add(square);
        		            }
        		        }
        		        checkersBoard.revalidate();
        		        checkersBoard.repaint();
        		    }
    		        logic.switchPlayerTurn();
    		        moveSuc.setText(logic.valid);
    		        whoTurn.setText("player" + Character.toString(logic.getCurrentPlayer()) + "'s Turn!");
    		    }
    		    else
    		    	moveSuc.setText(logic.valid);
    		} 
    		else if (who.equals("C")) 
    		{
    		    if (logic.isMoveValid(textF.getText()) == true) 
    		    {
    		        moveSuc.setText(logic.valid);
    		        logic.makeMove();
    		        if (logic.isGameOver()) 
    		        {
    		        	if(logic.getWinner() == 'X')
    		        		whoTurn.setText("PlayerX you Won!");
    		        	else if(logic.getWinner() == 'O')
    		        		whoTurn.setText("The Computer Won!");
    		            moveSuc.setText("Game Over!");
    		            confirmMove.setEnabled(false);
    		            gameOver = true;
    		        }
    		        if (gameOver != true) 
    		        {
    		            computer.generateComputerMove();
    		        }
    		        if (logic.isGameOver()) {
    		        	if(logic.getWinner() == 'X')
    		        		whoTurn.setText("PlayerX you Won!");
    		        	else if(logic.getWinner() == 'O')
    		        		whoTurn.setText("The Computer Won!");
    		            moveSuc.setText("Game Over!");
    		            confirmMove.setEnabled(false);
    		            gameOver = true;
    		        }
    		        else 
    		        {
    		            checkersBoard.removeAll();
    		            for (int row = 0; row < 8; row++) 
    		            {
    		                for (int col = 0; col < 8; col++) 
    		                {
    		                    JPanel square = new JPanel(new BorderLayout());
    		                    char cellValue = logic.getBoard().getCell(row, col).getPiece();
    		                    if ((row + col) % 2 == 0) 
    		                    {
    		                        square.setBackground(Color.WHITE);
    		                    } 
    		                    else 
    		                    {
    		                        square.setBackground(Color.PINK);
    		                    }
    		                    JLabel coordinateLabel = new JLabel((8 - row) + Character.toString((char) ('a' + col)));
    		                    coordinateLabel.setHorizontalAlignment(JLabel.CENTER);
    		                    coordinateLabel.setVerticalAlignment(JLabel.BOTTOM);
    		                    square.add(coordinateLabel, BorderLayout.CENTER);

    		                    if (logic.getBoard().getCell(row, col).getPiece() != '_') 
    		                    {
    		                        JLabel pieceLabel = new JLabel(Character.toString(cellValue));
    		                        pieceLabel.setHorizontalAlignment(JLabel.CENTER);
    		                        pieceLabel.setVerticalAlignment(JLabel.CENTER);
    		                        pieceLabel.setFont(new Font("", Font.PLAIN, 55));
    		                        square.add(pieceLabel, BorderLayout.NORTH);
    		                    }

    		                    checkersBoard.add(square);
    		                }
    		            }
    		            checkersBoard.revalidate();
    		            checkersBoard.repaint();
    		        }
    		    } 
    		    else 
    		    {
    		        moveSuc.setText(logic.valid);
    		    }
    		}
    	});
    }
}   