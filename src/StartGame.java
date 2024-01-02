import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Provides the main menu to the program.
 * @author Rajvirsinh Jadeja
 * @version Version 0.1
 */
public class StartGame extends JFrame 
{
	private static final long serialVersionUID = 1L;

	/**
	    * Constructs all the GUI for the main menu and handles actions.
	*/
   public StartGame()
   {
	   BufferedImage imageIcon = null;
	   BufferedImage imageBackground = null;
	   	try 
	   	{
	   		imageIcon = ImageIO.read(getClass().getResource("CheckersBoardGameLogoWindows.png"));
	   		imageBackground = ImageIO.read(getClass().getResource("cherry.png"));
	   	}
	   	catch (IOException e) 
	   	{
	   	    e.printStackTrace();
	   	}
   		setResizable(false);
		getContentPane().setFont(new Font("Yu Gothic Light", Font.PLAIN, 11));
		setFont(new Font("Yu Gothic Light", Font.PLAIN, 12));
		setTitle("Sakura Checkers");
		setIconImage(imageIcon);
		setBackground(Color.BLACK);
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Sakura Checkers");
		lblNewLabel.setBounds(0, 0, 434, 41);
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Yu Gothic Light", Font.PLAIN, 25));
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\t\t\t\t");
		lblNewLabel_1.setBounds(0, 41, 96, 206);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Version 0.1   ");
		lblNewLabel_2.setBounds(0, 247, 434, 14);
		lblNewLabel_2.setBackground(Color.WHITE);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setFont(new Font("Yu Gothic Bold", Font.PLAIN, 12));
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("\t\t\t\t");
		lblNewLabel_3.setBounds(338, 41, 96, 206);
		getContentPane().add(lblNewLabel_3);
		
		JPanel panel = new JPanel();
		panel.setBounds(96, 41, 242, 206);
		panel.setForeground(Color.WHITE);
		panel.setBackground(Color.WHITE);
		panel.setBorder(null);
		getContentPane().add(panel);
		panel.setLayout(new GridLayout(4,1));
		
		JButton localBattle = new JButton("Local Battle");
		localBattle.setForeground(Color.BLACK);
		localBattle.setBackground(Color.WHITE);
		localBattle.setFont(new Font("Yu Gothic Light", Font.PLAIN, 15));
		localBattle.setFocusPainted(false);
		panel.add(localBattle);
		
		JButton computerBattle = new JButton("Computer Battle");
		computerBattle.setBackground(Color.WHITE);
		computerBattle.setFont(new Font("Yu Gothic Light", Font.PLAIN, 15));
		computerBattle.setFocusPainted(false);
		panel.add(computerBattle);
		
		JButton howToPlayButton = new JButton("How to Play");
		howToPlayButton.setBackground(Color.WHITE);
		howToPlayButton.setFont(new Font("Yu Gothic Light", Font.PLAIN, 15));
		howToPlayButton.setFocusPainted(false);
		panel.add(howToPlayButton);
		
		JButton exit = new JButton("Exit");
		exit.setBackground(Color.WHITE);
		exit.setFont(new Font("Yu Gothic Light", Font.PLAIN, 15));
		exit.setFocusPainted(false);
		panel.add(exit);
		
		JLabel lblNewLabel_4 = new JLabel(" ");
		lblNewLabel_4.setIcon(new ImageIcon(imageBackground));
		lblNewLabel_4.setBounds(0, 0, 448, 261);
		getContentPane().add(lblNewLabel_4);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		setLocationRelativeTo(null);
		
		localBattle.addActionListener(new ActionListener()
		{
           @Override
           public void actionPerformed(ActionEvent e)
           {
               CheckersGUI gui = new CheckersGUI();
               Board board = new Board();
               CheckersLogic logic = new CheckersLogic(board);
               gui.initizalizeGui(logic.getBoard());
               gui.checkersMoveGui("P");
               dispose();
           }
       });
		
		computerBattle.addActionListener(new ActionListener()
		{
           @Override
           public void actionPerformed(ActionEvent e)
           {
               CheckersGUI gui = new CheckersGUI();
               Board board = new Board();
               CheckersLogic logic = new CheckersLogic(board);
               gui.initizalizeGui(logic.getBoard());
               gui.checkersMoveGui("C");
               dispose();
           }
       });
      
       howToPlayButton.addActionListener(new ActionListener()
       {
       	
       	String instructions = "<html><body style='width: 700px;'>"
                   + "<b>How to Play Sakura Checkers:</b><br>"
                   + "Welcome to the exciting world of Sakura Checkers! This classic board game, inspired by Japanese cherry blossoms, is simple to learn but offers strategic depth that can challenge even the most seasoned players. Here's a step-by-step guide on how to play the game:<br><br>"
                   + "<b>Objective:</b><br>The goal of Sakura Checkers is to capture all of your opponent's pieces or block them in such a way that they cannot make a legal move.<br><br>"
                   + "<b>Game Setup:</b><br>1. The game board consists of 64 squares arranged in an 8x8 grid. Each player starts with 12 pieces, either X or O.<br>"
                   + "2. Players place their pieces on the pink squares of the three rows closest to them. The pieces are arranged on the pink squares of the three rows closest to each player.<br><br>"
                   + "<b>Piece Movement:</b><br>Players take turns moving their pieces diagonally forward to an adjacent, empty pink square.<br>"
                   + "1. To move a piece, enter the starting coordinates (row then column) and the ending coordinates (row then column) of the destination square. For example, to move a piece from A3 to B4, you would type \"3A-4B\".<br>"
                   + "2. Normal pieces move forward only. There is no backward movement.<br><br>"
                   + "<b>Capturing Opponent's Pieces:</b><br>1. To capture an opponent's piece, enter the coordinates of the capturing piece and the coordinates of the enemy piece being captured. The move should be typed in the format \"StartRow StartColumn-EnemyRow EnemyColumn\". For example, if your piece is on 4B and the opponent's piece which you want to take is on 5C, you would type \"4B-5C\".<br>"
                   + "2. You must jump over an opponent's piece to capture it. The jumped piece is then removed from the board.<br><br>"
                   + "<b>Winning the Game:</b><br>1. The game continues until one player captures all of their opponent's pieces or blocks them from making a legal move. If a player has no legal moves left, they lose the game.<br><br>"
                   + "<b>Coordinate System:</b><br>1. Columns are labeled A to H from left to right. Rows are labeled 8 to 1 from top to bottom.<br><br>"
                   + "<font color='red'><b>!Important:</b> Kinging is currently not available, but will be implemented in the next update!</font></body></html>";
       	JLabel label = new JLabel(instructions);
           @Override
           public void actionPerformed(ActionEvent e)
           {
           	label.setFont(new Font("Yu Gothic Light", Font.PLAIN, 15));
               JOptionPane.showMessageDialog(null, label, "Sakura Checkers Instructions", JOptionPane.INFORMATION_MESSAGE);
           }
       });
      
       exit.addActionListener(new ActionListener()
       {
           @Override
           public void actionPerformed(ActionEvent e)
           {
           	System.exit(0);
           }
       });
   }
}

