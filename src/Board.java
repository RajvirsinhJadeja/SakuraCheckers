

/**
 * The Board class represents checkers game board consisting of cells.
 * Each cell can contain a game piece ('x', 'o', or '_').
 * 
 * @author Rajvirsinh Jadeja
 * @version Version 0.1
 */
public class Board 
{
    private Cell[][] boardCell;

    /**
     * Constructs a new game board with 8x8 cells and initializes the board with
     * game pieces.
     */
    public Board() 
    {
        boardCell = new Cell[8][8];
        initializeBoard();
    }

    /**
     * Initializes the game board with appropriate game pieces.
     */
    public void initializeBoard() 
    {
        for (int row = 0; row < 8; row++) 
        {
            for (int col = 0; col < 8; col++) 
            {
                if ((row + col) % 2 == 1) 
                {
                    if (row < 3) 
                    {
                        boardCell[row][col] = new Cell('o', row, col);
                    } 
                    else if (row > 4) 
                    {
                        boardCell[row][col] = new Cell('x', row, col);
                    } 
                    else 
                    {
                        boardCell[row][col] = new Cell('_', row, col);
                    }
                } 
                else 
                {
                    boardCell[row][col] = new Cell('_', row, col);
                }
            }
        }
    }

    /**
     * Retrieves the cell at the specified row and column.
     * 
     * @param row The row index of the cell.
     * @param col The column index of the cell.
     * @return The cell object at the specified position.
     */
    public Cell getCell(int row, int col) 
    {
        return boardCell[row][col];
    }

    /**
     * Sets the game piece of the cell at the specified row and column.
     * 
     * @param row    The row index of the cell.
     * @param col    The column index of the cell.
     * @param piece  The game piece to be set in the cell ('x', 'o', or '_').
     */
    public void setCell(int row, int col, char piece) 
    {
        boardCell[row][col].setPiece(piece);
    }
}
