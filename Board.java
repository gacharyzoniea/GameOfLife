import java.util.Scanner;

/**
 * 
 * @author TODO: Your name here
 * A snapshot of a Game of Life board at one point in time
 */
public class Board {
	// TODO: declare additional member variables
	
	// The two-dimensional grid of board curBoard.
	// curBoard[r][c] is true if the cell at row r and column c is alive.
	private boolean[][] curBoard;
	private int rows;
	private int cols;
	

	
	/**
	 * Creates a Board with no live curBoard
	 * @param numberOfRows - number of rows in the board
	 * @param numberOfColumns - number of columns in the board
	 */
	public Board(int numberOfRows, int numberOfColumns){
		// TODO: complete this method
		rows = numberOfRows;
		cols = numberOfColumns;
		 curBoard = new boolean[rows][cols];
		 for (int i = 0; i < rows; i++) {
				for (int j = 0; j < cols; j++) {
					curBoard[i][j] = false;
				}
			}
	}
	
	
	/**
	 * Constructs a Board from the given String.
	 * @param boardInfo: A String in the format specified in the instructions under "File Format"
	 *     The string should contain all of the lines, so it will have newlines in it.
	 * Example:
"Glider\n" +
"7\n" +
"8\n" +
"        \n" +
"  X     \n" +
"   X    \n" +
" XXX    \n" +
"        \n" +
"        \n" +
"        \n"
	 */
	public Board(String boardInfo){
		// TODO: complete this method
		Scanner sc = new Scanner(boardInfo);
		sc.nextLine();
		rows = sc.nextInt();
		cols = sc.nextInt();
		curBoard = new boolean[rows][cols];
		sc.useDelimiter("\n");
		for (int i = 0; i < rows; i++) {
			String line = sc.next();
			for (int j = 0; j < cols; j++) {
				if (line.charAt(j) == ' ') {
					curBoard[i][j] = false;
				} else if (line.charAt(j) == 'X') {
					curBoard[i][j] = true;
				}

			}
		}
	}

	/**
	 * @return number of rows in the board
	 */
	public int getNumRows(){
		// TODO: complete this method
		return rows;
	}
	
	/**
	 * @return number of columns in this board
	 */
	public int getNumCols(){
		// TODO: complete this method
		return cols;
	}
	
	/**
	 * @return value of the cell at the given @param row and @param col
	 * @throws IllegalArgumentException if row or col is out of bounds.
	 */
	public boolean getCell(int row, int col) throws IllegalArgumentException {
		// TODO: complete this method
		if ((row < 0 || row >= this.rows) || (col < 0 || col >= this.cols)) {
			throw new IllegalArgumentException("stop dum dum");
		}
		else {
		boolean status = false;
		if(curBoard[row][col] == true)
			status = true;
		else if(curBoard[row][col] == false){
			status = false;
		}
		return status;
	}
}
	
	/** 
	 * @param row
	 * @param col
	 * @return The status of the cell in the next generation
	 */
	private boolean futureGen(int row, int col) {
		boolean futureGen = false;
		int val = 0;
		for (int tempRow = row - 1; tempRow <= row + 1; tempRow++) {
			for (int tempCol = col - 1; tempCol <= col + 1; tempCol++) {
				try {
					if (curBoard[tempRow][tempCol] == true && (row != tempRow || col != tempCol)) {
						val += 1;
					}
				} catch (ArrayIndexOutOfBoundsException ex) {
				}
			}
		} 		
				if (curBoard[row][col]) {
					if (val == 2 || val == 3) {
						futureGen = true;
					} else if (val > 3 || val < 2) {
						futureGen = false;
					}
				} else if (curBoard[row][col] == false) {
					if (val == 3) {
						futureGen = true;
					}
				}			
	return futureGen;
	}
	
	/**
	 * @return A new Board for the next generation (i.e., after this).
	 *    at the next time slice.
	 *    The cell values for the next generation
	 *    are determined by the rules of Game of Life.
	 */
	public Board nextBoard() {
		// TODO: complete this method
		Board nextBoard = new Board(rows, cols);
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (futureGen(i, j)) {
					nextBoard.curBoard[i][j] = true;
				} else {
					nextBoard.curBoard[i][j] = false;
				}
			}
		}
		return nextBoard;
	}
	

	/**
	 * @return true if other is the same size as this board
	 *   AND all of the curBoard of other have the same
	 *   values as the curBoard in this board
	 */
	public boolean isSame(final Board other){
		// TODO: complete this method
		boolean isSame = false;
		if (other.getNumRows() == rows && other.getNumCols() == cols) {
			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < cols; j++) {
					if (other.getCell(i, j) == this.getCell(i, j)) {
						isSame = true;
					} else {
						return false;
					}
				}
			}
		}
//			isSame = true;
//		}
//		else if (!other.equals(this)) {
//			isSame = false;
//		}
		return isSame;
	}
	
	/**
	 * @return a String representation of the current board state
	 * Example:
	 *    ......\n
	 *    ......\n
	 *    .X....\n
	 *    ..X...\n
	 *    .XX...\n
	 *    ......\n
	 *    
	 *    Each row of the board should be printed on its own line.
	 *    The \n above represents a newline 
	 *    (which will not appear when the string is printed).
	 *    . characters are for dead curBoard.
	 *    X characters are for live curBoard.
	 *    The String should end with a newline.
	 */
	@Override
	public String toString(){
		// TODO: complete this method
		String total = "";
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (getCell(i, j) == true) {
					total += "X";
				} else if (getCell(i, j) == false){
					total += ".";
				}
			}
			total += "\n";
		}
	return total;
	}
}
