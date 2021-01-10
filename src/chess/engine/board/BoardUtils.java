package chess.engine.board;

/**
 * BoardUtils - A utility class that contains misc. functions
 *  			that aren't exclusive to any particular class
 * @since 1/8/2020
 */
public class BoardUtils {

	public static final boolean[] FIRST_COLUMN = initColumn(0);
	public static final boolean[] SECOND_COLUMN = initColumn(1);
	public static final boolean[] SEVENTH_COLUMN = initColumn(6);
	public static final boolean[] EIGHTH_COLUMN = initColumn(7);
	
	public static final boolean[] SECOND_ROW = initRow(8);
	public static final boolean[] SEVENTH_ROW = initRow(48);
	
	public static final int NUM_TILES = 64;
	public static final int NUM_TILES_PER_ROW = 8;
	
	/**
	 * Prevents intialization of class
	 */
	private BoardUtils() {
		throw new RuntimeException("Cannot instantiate BoardUtils class");
	}
	
	/**
	 * @param colNum - the index of the column being checked
	 * @return boolean array of booleans for each position if they are in a certain column
	 */
	private static final boolean[] initColumn(int colNum) {
		
		final boolean[] column = new boolean[NUM_TILES];
		
		do {
			column[colNum] = true;
			colNum += NUM_TILES_PER_ROW;
		} while(colNum < NUM_TILES);
		
		return column;
	}
	
	/**
	 * @param rowNum - the position intializing the row
	 * @return boolean array of booleans for each position if they are in a certain row
	 */
	private static final boolean[] initRow(int rowNum) {
		final boolean[] row = new boolean[NUM_TILES];
		
		do {
			row[rowNum] = true;
			rowNum++;
		} while(rowNum % NUM_TILES_PER_ROW != 0);
		
		return row;
	}
	
	/**
	 * Determines whether or not a move is actually valid
	 * @param coordinate - the position on the board
	 * @return the move's validity
	 */
	public static boolean isValidCoordinate(final int coordinate) {
		return coordinate >= 0 && coordinate < NUM_TILES;
	}
}
