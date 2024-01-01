/**
 * The Cell class represents a cell on a game board. 
 * 
 * @author Rajvirsinh Jadeja
 * @version Version 0.1
 */
public class Cell 
{
    private char piece;
    private int row, col;

    /**
     * Constructs a new `Cell` object with the specified piece, row, and column coordinates.
     * 
     * @param piece The game piece (character) to be placed in the cell.
     * @param row The row coordinate of the cell.
     * @param col The column coordinate of the cell.
     */
    public Cell(char piece, int row, int col) 
    {
        this.piece = piece;
        this.row = row;
        this.col = col;
    }
    
    /**
     * Retrieves the game piece (character) stored in the cell.
     * 
     * @return The game piece (character) stored in the cell.
     */
    public char getPiece() 
    {
        return piece;
    }
    
    /**
     * Retrieves the row coordinate of the cell.
     * 
     * @return The row coordinate of the cell.
     */
    public int getRow()
    {
    	return row;
    }
    
    /**
     * Retrieves the column coordinate of the cell.
     * 
     * @return The column coordinate of the cell.
     */
    public int getCol()
    {
    	return col;
    }
    
    /**
     * Sets the game piece (character) in the cell.
     * 
     * @param piece The game piece (character) to be set in the cell.
     */
    public void setPiece(char piece) 
    {
        this.piece = piece;
    }
}
