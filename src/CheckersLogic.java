/**
 * The CheckersLogic class has all the algorithms
 * It includes algorithms for validating move, making moves, and determining if game is over
 *
 * @author Rajvirsinh Jadeja
 * @version Version 0.1                                                                       
 */

public class CheckersLogic 
{
    private Board board;
    private boolean playerXTurn;
    private char winner;
    @SuppressWarnings("unused")
	private boolean changePlayer;
    public String valid;
    private int startRow, startCol, endRow, endCol;
    
    /**
     * Initializes the board object with an empty game board and sets the player x's turn.
     */
    public CheckersLogic(Board board) 
    {
        this.board = board;
        playerXTurn = true;
    }
    
    /**
    * Checks whether a move is valid based on the current game state.
    *
    * @param move The move to be validated in the format "startRowstartCol-endRowendCol".
    * @return True if the move is valid, otherwise false.
    */
    public boolean isMoveValid(String move) 
    {
    	try 
    	{
    		startRow = 8 - Character.getNumericValue(move.charAt(0));
        	startCol = move.charAt(1) - 'a';
            endRow = 8 - Character.getNumericValue(move.charAt(3));
            endCol = move.charAt(4) - 'a';
    	}
    	catch (Exception e) 
    	{
            valid = "Invalid move format!";
            return false;
        }
    	
            for(int row = 0; row < 8; row++)
        	{
        		for(int col = 0; col < 8; col++)
        		{
        			if (playerXTurn == true && row>=2 && col<=1)
        			{
        				if(board.getCell(row, col).getPiece() == 'x' && board.getCell(row-1, col+1).getPiece() == 'o' && board.getCell(row-2, col+2).getPiece() == '_')
        				{
        					if(endRow == row-1 && board.getCell(endRow, endCol).getPiece() == 'o' && board.getCell(endRow-1, endCol+(endCol-startCol)).getPiece() == '_' && endCol == col+1)
        					{
        						return true;
        					}
        					valid = "You can take an enemy piece!";
        					return false;
        				}
        			}
        			else if (playerXTurn == true && row>=2 && col>=6)
        			{
        				if(board.getCell(row, col).getPiece() == 'x' && board.getCell(row-1, col-1).getPiece() == 'o' && board.getCell(row-2, col-2).getPiece() == '_')
        				{
	        				if(playerXTurn == true && board.getCell(row-1, col-1).getPiece() == 'o' && board.getCell(row-2, col-2).getPiece() == '_')
	        				{
	        					if(endRow == row-1 && board.getCell(endRow, endCol).getPiece() == 'o' && board.getCell(endRow-1, endCol+(endCol-startCol)).getPiece() == '_' && endCol == col-1)
	        					{
	        						return true;
	        					}
	        					valid = "You can take an enemy piece!";
	        					return false;
	        				}
        				}
        			}
        			else if(playerXTurn == true && row>=2 && col>=2 && col <= 5)
        			{
        				if(board.getCell(row, col).getPiece() == 'x' && ((board.getCell(row-1, col+1).getPiece() == 'o' && board.getCell(row-2, col+2).getPiece() == '_') || (board.getCell(row-1, col-1).getPiece() == 'o' && board.getCell(row-2, col-2).getPiece() == '_')))
        				{
        					if(endRow == row-1 && board.getCell(endRow, endCol).getPiece() == 'o' &&board.getCell(endRow-1, endCol+(endCol-startCol)).getPiece() == '_' && (endCol == col+1 || endCol == col-1))
        					{
        						return true;
        					}
        					valid = "You can take an enemy piece!";
        					return false;
        				}
        			}
        			if (playerXTurn == false && row<=5 && col<=1)
        			{
        				if(playerXTurn == false && board.getCell(row, col).getPiece() == 'o' && board.getCell(row+1, col+1).getPiece() == 'x' && board.getCell(row+2, col+2).getPiece() == '_')
        				{
        					if(endRow == row+1 && board.getCell(endRow, endCol).getPiece() == 'x' && board.getCell(endRow+1, endCol+(endCol-startCol)).getPiece() == '_' && endCol == col+1)
        					{
        						return true;
        					}
        					valid = "You can take an enemy piece!";
        					return false;
        				}
        			}
        			else if (playerXTurn == false && row<=5 && col>=6)
        			{
        				if(playerXTurn == false && board.getCell(row, col).getPiece() == 'o' && board.getCell(row+1, col-1).getPiece() == 'x' && board.getCell(row+2, col-2).getPiece() == '_')
        				{
        					if(endRow == row+1 && board.getCell(endRow, endCol).getPiece() == 'x' && board.getCell(endRow+1, endCol+(endCol-startCol)).getPiece() == '_' && endCol == col-1)
        					{
        						return true;
        					}
        					valid = "You can take an enemy piece!";
        					return false;
        				}
        			}
        			else if (playerXTurn == false && row<=5 && col>=2 && col <= 5)
        			{
        				if(playerXTurn == false && board.getCell(row, col).getPiece() == 'o' && (board.getCell(row+1, col+1).getPiece()== 'x'|| board.getCell(row+1, col-1).getPiece() == 'x') && (board.getCell(row+2, col+2).getPiece() == '_'|| board.getCell(row+2, col-2).getPiece() == '_'))
        				{
        					if(endRow == row+1 && board.getCell(endRow, endCol).getPiece() == 'x' && board.getCell(endRow+1, endCol+(endCol-startCol)).getPiece() == '_' && (endCol == col+1 || endCol == col-1))
        					{
        						return true;
        					}
        					valid = "You can take an enemy piece!";
        					return false;
        				
        				}
        			}
        		}
        	}
            
            if (playerXTurn == true && board.getCell(startRow, startCol).getPiece() == 'x' && board.getCell(endRow, endCol).getPiece() != 'x' && endRow - startRow == -1 && (endCol - startCol == 1 || endCol - startCol == -1) && 0 <= endCol && endCol <= 7 && 0 <= endRow && endRow <= 7) 
            {
                if (startRow >= 2 && startCol >= 2 && startCol <= 5) 
                {
                    if (board.getCell(endRow, endCol).getPiece() == 'o' && board.getCell(endRow + (endRow - startRow), endCol + (endCol - startCol)).getPiece() == '_') 
                    {
                    	valid = "Nice Move!";
                        return true;
                    }
                }
                if (board.getCell(endRow, endCol).getPiece() == '_') 
                {
                	valid = "Nice Move!";
                    return true;
                }
            } 
            else if (playerXTurn == false && board.getCell(startRow, startCol).getPiece() == 'o' && board.getCell(endRow, endCol).getPiece() != 'o' && endRow - startRow == 1 && (endCol - startCol == 1 || endCol - startCol == -1) && 0 <= endCol && endCol <= 7 && 0 <= endRow && endRow <= 7) 
            {
                if (startRow <= 5 && startCol >= 2 && startCol <= 5) 
                {
                    if (board.getCell(endRow, endCol).getPiece() == 'x' && board.getCell(endRow + (endRow - startRow), endCol + (endCol - startCol)).getPiece() == '_') 
                    {
                    	valid = "Nice Move!";
                        return true;
                    }
                }
                if (board.getCell(endRow, endCol).getPiece() == '_') 
                {
                	valid = "Nice Move!";
                    return true;
                }
            }
            valid = "Move is Invalid!";
        	return false;
    }
    
    /**
     * Makes a move on the game board based on the current player's turn and the their move selection.
     *
     * @return True if the move was successfully made, otherwise false.
     */
    public boolean makeMove() 
    {
    	if(playerXTurn == true && board.getCell(endRow, endCol).getPiece() == '_')
    	{
    	    board.setCell(endRow, endCol, 'x');
    	    board.setCell(startRow, startCol, '_');
    	    return changePlayer = true;
    	}
    	else if(playerXTurn == true && board.getCell(endRow, endCol).getPiece() == 'o')
    	{
    	    board.setCell(startRow, startCol, '_');
    	    board.setCell(endRow, endCol, '_');
    	    board.setCell(endRow+(endRow-startRow), endCol+(endCol-startCol), 'x');
    	    if(endRow+(endRow-startRow) >= 2 && (endCol+(endCol-startCol)-2 < 0))
    	    {
    	        if((board.getCell(endRow+(endRow-startRow)-1, endCol+(endCol-startCol)+1).getPiece() == 'o' && board.getCell(endRow+(endRow-startRow)-2, endCol+(endCol-startCol)+2).getPiece() == '_'))
    	        {
    	            return changePlayer = false;
    	        }
    	    }
    	    if(endRow+(endRow-startRow) >= 2 && (endCol+(endCol-startCol)+2 > 7))
    	    {
    	        if((board.getCell(endRow+(endRow-startRow)-1, endCol+(endCol-startCol)-1).getPiece() == 'o' && board.getCell(endRow+(endRow-startRow)-2, endCol+(endCol-startCol)-2).getPiece() == '_'))
    	        {
    	            return changePlayer = false;
    	        }
    	    }
    	    if(endRow+(endRow-startRow) >= 2 && (endCol+(endCol-startCol)-2 >= 0) && (endCol+(endCol-startCol)+2 <= 7))
    	    {
    	        if((board.getCell(endRow+(endRow-startRow)-1, endCol+(endCol-startCol)-1).getPiece() == 'o' && board.getCell(endRow+(endRow-startRow)-2, endCol+(endCol-startCol)-2).getPiece() == '_') || (board.getCell(endRow+(endRow-startRow)-1, endCol+(endCol-startCol)+1).getPiece() == 'o' && board.getCell(endRow+(endRow-startRow)-2, endCol+(endCol-startCol)+2).getPiece() == '_'))
    	        {
    	            return changePlayer = false;
    	        }
    	    }
    	}
    	else if(playerXTurn == false && board.getCell(endRow, endCol).getPiece() == '_')
    	{
    	    board.setCell(endRow, endCol, 'o');
    	    board.setCell(startRow, startCol, '_');
    	    return changePlayer = true;
    	}
    	else if(playerXTurn == false && board.getCell(endRow, endCol).getPiece() == 'x')
    	{
    	    board.setCell(startRow, startCol, '_');
    	    board.setCell(endRow, endCol, '_');
    	    board.setCell(endRow+(endRow-startRow), endCol+(endCol-startCol), 'o');
    	    if(endRow+(endRow-startRow) <= 7 && (endCol+(endCol-startCol)-2 < 0))
    	    {
    	        if((board.getCell(endRow+(endRow-startRow)+1, endCol+(endCol-startCol)+1).getPiece() == 'o' && board.getCell(endRow+(endRow-startRow)+2, endCol+(endCol-startCol)+2).getPiece() == '_'))
    	        {
    	            return changePlayer = false;
    	        }
    	    }
    	    if(endRow+(endRow-startRow) <= 7 && (endCol+(endCol-startCol)+2 > 7))
    	    {
    	        if((board.getCell(endRow+(endRow-startRow)+1, endCol+(endCol-startCol)-1).getPiece() == 'o' && board.getCell(endRow+(endRow-startRow)+2, endCol+(endCol-startCol)-2).getPiece() == '_'))
    	        {
    	            return changePlayer = false;
    	        }
    	    }
    	    if(endRow+(endRow-startRow) <= 7 && (endCol+(endCol-startCol)-2 >= 0) && (endCol+(endCol-startCol)+2 <= 7))
    	    {
    	        if((board.getCell(endRow+(endRow-startRow)+1, endCol+(endCol-startCol)-1).getPiece() == 'o' && board.getCell(endRow+(endRow-startRow)+2, endCol+(endCol-startCol)-2).getPiece() == '_') || (board.getCell(endRow+(endRow-startRow)+1, endCol+(endCol-startCol)+1).getPiece() == 'o' && board.getCell(endRow+(endRow-startRow)+2, endCol+(endCol-startCol)+2).getPiece() == '_'))
    	        {
    	            return changePlayer = false;
    	        }
    	    }
    	}
    	return changePlayer=true;
    }
    
    /**
     * Checks whether the game is over by analyzing the current state of the game board.
     * Looks through each cell and finds pieces which can move. If no piece can move/no pieces left, game over
     * @return True if the game is over, otherwise false.
     */
    public boolean isGameOver() 
    {
    	boolean Xcheck = false, Ocheck = false;
    	int Xcount = 0, Ocount = 0;
    	for(int row = 0; row < 8; row++)
    	{
    	    for(int col = 0; col < 8; col++) 
    	    {
    	        if(row != 0 && board.getCell(row, col).getPiece() == 'x') 
    	        {
    	            if(row >= 2 && col <= 2) 
    	            {
    	                if((board.getCell(row-1, col+1).getPiece() == '_' || (board.getCell(row-1, col+1).getPiece() == 'o') && board.getCell(row-2, col+2).getPiece() == '_')) 
    	                {
    	                    Xcount++;
    	                    Xcheck = false;
    	                }
    	            }
    	            else if(row >= 2 && col >= 5) 
    	            {
    	                if((board.getCell(row-1, col-1).getPiece() == '_' || (board.getCell(row-1, col-1).getPiece() == 'o') && board.getCell(row-2, col-2).getPiece() == '_')) 
    	                {
    	                    Xcount++;
    	                    Xcheck = false;
    	                }	
    	            }
    	            else if(row >= 2 && col >= 2 && col <= 5)
    	            {
    	                if((board.getCell(row-1, col-1).getPiece() == '_' || board.getCell(row-1, col+1).getPiece() == '_') || ((board.getCell(row-1, col+1).getPiece() == 'o'|| board.getCell(row-1, col-1).getPiece() == 'o') && (board.getCell(row-2, col+2).getPiece() == '_'|| board.getCell(row-2, col-2).getPiece() == '_'))) 
    	                {
    	                    Xcount++;
    	                    Xcheck = false;
    	                }
    	            }
    	            else if(row >= 1 && col <= 1) 
    	            {
    	                if(board.getCell(row-1, col+1).getPiece() == '_')
    	                {
    	                    Xcount++;
    	                    Xcheck = false;
    	                }
    	            }
    	            else if(row >= 1 && col >= 6) 
    	            {
    	                if(board.getCell(row-1, col-1).getPiece() == '_') 
    	                {
    	                    Xcount++;
    	                    Xcheck = false;
    	                }
    	            }
    	            else if(row >= 1 && col >= 2 && col <= 5) 
    	            {
    	                if(board.getCell(row-1, col-1).getPiece() == '_' || board.getCell(row-1, col+1).getPiece() == '_') 
    	                {
    	                    Xcount++;
    	                    Xcheck = false;
    	                }
    	            }
    	            else 
    	            {
    	                winner = 'O';
    	                Xcheck = true;
    	            }
    	        }
    	        
    	        if(row != 7 && board.getCell(row, col).getPiece() == 'o') 
    	        {
    	            if(row <= 5 && col <= 1) 
    	            {
    	                if(board.getCell(row+1, col+1).getPiece() == '_' || (board.getCell(row+1, col+1).getPiece() == 'x' && board.getCell(row+2, col+2).getPiece() == '_')) 
    	                {
    	                    Ocount++;
    	                    Ocheck = false;
    	                }
    	            }
    	            else if(row <= 5 && col >= 6)
    	            {
    	                if(board.getCell(row+1, col-1).getPiece() == '_' || (board.getCell(row+1, col-1).getPiece() == 'x' && board.getCell(row+2, col-2).getPiece() == '_')) 
    	                {
    	                    Ocount++;
    	                    Ocheck = false;
    	                }	
    	            }
    	            else if(row <= 5 && col >= 2 && col <= 5) 
    	            {
    	                if((board.getCell(row+1, col-1).getPiece() == '_' || board.getCell(row+1, col+1).getPiece() == '_') || ((board.getCell(row+1, col+1).getPiece() == 'x'|| board.getCell(row+1, col-1).getPiece() == 'x') && (board.getCell(row+2, col+2).getPiece() == '_'|| board.getCell(row+2, col-2).getPiece() == '_'))) 
    	                {
    	                    Ocount++;
    	                    Ocheck = false;
    	                }
    	            }
    	            if(row <= 6 && col <= 1) 
    	            {
    	                if(board.getCell(row+1, col+1).getPiece() == '_') 
    	                {
    	                    Ocount++;
    	                    Ocheck = false;
    	                }
    	            }
    	            else if(row <= 6 && col >= 6) 
    	            {
    	                if(board.getCell(row+1, col-1).getPiece() == '_') 
    	                {
    	                    Ocount++;
    	                    Ocheck = false;
    	                }
    	            }
    	            else if(row <= 6 && col >= 2 && col <= 5) 
    	            {
    	                if(board.getCell(row+1, col-1).getPiece() == '_' || board.getCell(row+1, col+1).getPiece() == '_') 
    	                {
    	                    Ocount++;
    	                    Ocheck = false;
    	                }
    	            }
    	            else 
    	            {
    	                winner = 'X';
    	                Ocheck = true;
    	            }
    	        }
    	    }
    	}

    	if(Xcount == 0)
    	{
    		winner = 'O';
    		return true;
    	}
    	if(Ocount == 0)
    	{
    		winner = 'X';
    		return true;
    	}
    	if(Xcheck == false && Ocheck == false)
    		return false;
    	else if(Xcheck == true && Ocheck == false)
    	{
    		winner = 'O';
    		return true;
    	}
    	else if(Xcheck == false && Ocheck == true)
    	{
    		winner = 'X';
    		return true;
    	}
    	else if(Xcheck == true && Ocheck == true)
    	{
    		winner = 'D';
    		return true;
    	}
    	return true;
    }
    
    /**
     * Retrieves the current game board.
     *
     * @return The 2D character array representing the game board.
     */
    public Board getBoard() 
    {
        return board;
    }

    /**
     * Retrieves the symbol of the current player whose turn it is.
     *
     * @return 'X' if it's player X's turn, 'O' if it's player O's turn.
     */
    public char getCurrentPlayer() 
    {
        return playerXTurn ? 'X' : 'O';
    }

    /**
     * Switches the turn to the other player.
     */
    public void switchPlayerTurn() 
    {
    		playerXTurn = !playerXTurn;
    }

    /**
     * Retrieves the symbol of the winner of the game.
     *
     * @return 'X' if player X wins, 'O' if player O wins, 'D' for a draw, or '\0' if no winner yet.
     */
    public char getWinner() 
    {
        return winner;
    }
}

