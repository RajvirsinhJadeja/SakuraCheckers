import java.util.Random;

/**
 * The CheckersComputerPlayer class represents a computer player for a checkers game.
 * It generates computer moves based on the current game board.
 * 
 * @author Rajvirsinh Jadeja
 * @version Version 0.1
 */

public class CheckersComputerPlayer 
{
	private Board board;
	private Cell[] pieces;
	private int moves[];
	Random ranPiece, ranMove;
	
	/**
     * Initialized variables which will be used later.
     * 
     * @param board The game board on which the computer player will make moves.
     */
	public CheckersComputerPlayer(Board board) 
    {
        this.board = board;
        pieces = new Cell[12];
        moves = new int[48];
        this.ranPiece = new Random();
        this.ranMove = new Random();
    }
	
	/**
     * Generates a computer move based on the current game board.
     */
	public void generateComputerMove()
	{
		int count = 0;
		int moveNum = 0;
		boolean check = false;
		boolean tookPiece = false;
		// Finds all Pieces which can move
		for(int row = 0; row < 7; row++)
		{
			for(int col = 0; col < 8; col++)
			{
					if(board.getCell(row, col).getPiece() == 'o')
					{
						if(col != 0 && board.getCell(row+1, col-1).getPiece() == '_')
						{
							check = true;
						}
						if(row <= 5 && col >= 2 && board.getCell(row+1, col-1).getPiece() == 'x' && board.getCell(row+2, col-2).getPiece() == '_')
						{
							executeTakeMove(2, row, col);
							 tookPiece = true;
							break;
						}
						if(col != 7 && board.getCell(row+1, col+1).getPiece() == '_')
						{
							check = true;
						}
						if(row <= 5 && col <= 5 && board.getCell(row+1, col+1).getPiece() == 'x' && board.getCell(row+2, col+2).getPiece() == '_')
						{
							executeTakeMove(4, row, col);
							 tookPiece = true;
							break;
						}
					
					if(check == true)
					{
						pieces[count] = board.getCell(row, col);
						count++;
						if(col != 0 && board.getCell(row+1, col-1).getPiece() == '_')
						{
							moves[moveNum] = 1;
							moveNum++;
							check = true;
						}
						else
						{
							moves[moveNum] = 0;
							moveNum++;
						}
						
						if(row <= 5 && col >= 3 && board.getCell(row+1, col-1).getPiece() == 'x' && board.getCell(row+2, col-2).getPiece() == '_')
						{
							moves[moveNum] = 2;
							moveNum++;
							check = true;
						}
						else
						{
							moves[moveNum] = 0;
							moveNum++;
						}
						
						if(col != 7 && board.getCell(row+1, col+1).getPiece() == '_')
						{
							moves[moveNum] = 3;
							moveNum++;
							check = true;
						}
						else
						{
							moves[moveNum] = 0;
							moveNum++;
						}
						
						if(row <= 5 && col <= 5 && board.getCell(row+1, col+1).getPiece() == 'x' && board.getCell(row+2, col+2).getPiece() == '_')
						{
							moves[moveNum] = 4;
							moveNum++;
							check = true;
						}
						else
						{
							moves[moveNum] = 0;
							moveNum++;
						}
					}
					check = false;
				}
				
			}
		}
		int ranP = ranPiece.nextInt(count);
		int movesPos = 0;
		int fMove = 0;
		
		if( tookPiece == false)
		{
			// Count the valid moves for the selected piece.
			for(int i = ranP*4; i < (ranP*4)+4; i++)
			{
				if(moves[i] != 0)
				{
					movesPos++;
				}
			}
			
			// Randomly selects on of those moves
			int ranM = ranMove.nextInt(movesPos)+1;
			for(int i = ranP*4; i < (ranP*4)+4; i++)
			{
				if(moves[i] != 0)
				{
					ranM--;
				}
				if(ranM == 0)
				{
					fMove = moves[i];
					break;
				}
			}
			
			// Execute the selected move with the selected piece.
			switch(fMove)
			{
				case 1: 
					board.setCell(pieces[ranP].getRow(), pieces[ranP].getCol(), '_');
					board.setCell(pieces[ranP].getRow()+1, pieces[ranP].getCol()-1, 'o');
					break;
				case 3: 
					board.setCell(pieces[ranP].getRow(), pieces[ranP].getCol(), '_');
					board.setCell(pieces[ranP].getRow()+1, pieces[ranP].getCol()+1, 'o');
					break;
			}
		}
		
	}
	/**
     * Used for when the computer finds a piece that can take another piece
     */
	public void executeTakeMove(int fMove, int row, int col)
	{
		switch(fMove)
		{
			case 2: 
				board.setCell(row, col, '_');
				board.setCell(row+1, col-1, '_');
				board.setCell(row+2, col-2, 'o');
				break;
			case 4: 
				board.setCell(row, col, '_');
				board.setCell(row+1, col+1, '_');
				board.setCell(row+2, col+2, 'o');
				break;
		}
	}
}


