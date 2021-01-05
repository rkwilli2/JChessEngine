package chessModel;

import java.util.Arrays;

/**
 * ChessBoard - Represents a chess board
 */
public class ChessBoard {

	public static final int HEIGHT = 8;
	public static final int WIDTH = 8;
	
	public ChessPiece[][] value;
	
	/**
	 * No-args constructor, creates a newly setup board
	 */
	public ChessBoard() {
		value = getNewBoard();
	}
	
	/**
	 * @return a newly set up chess board
	 */
	private ChessPiece[][] getNewBoard() {
		ChessPiece[][] board = new ChessPiece[HEIGHT][WIDTH];
		
		// Fill in the 1st and 6th rows with pawns
		for (int i = 0; i < WIDTH; i++) {
			board[1][i] = new ChessPiece(ChessPiece.PAWN, ChessPiece.BLACK);
			board[6][i] = new ChessPiece(ChessPiece.PAWN, ChessPiece.WHITE);
		}
		
		// Eigth Rank
		board[0] = new ChessPiece[] {
				new ChessPiece(ChessPiece.ROOK, 	ChessPiece.BLACK),
				new ChessPiece(ChessPiece.KNIGHT, 	ChessPiece.BLACK),
				new ChessPiece(ChessPiece.BISHOP, 	ChessPiece.BLACK),
				new ChessPiece(ChessPiece.QUEEN, 	ChessPiece.BLACK),
				new ChessPiece(ChessPiece.KING, 	ChessPiece.BLACK),
				new ChessPiece(ChessPiece.BISHOP, 	ChessPiece.BLACK),
				new ChessPiece(ChessPiece.KNIGHT, 	ChessPiece.BLACK),
				new ChessPiece(ChessPiece.ROOK, 	ChessPiece.BLACK),
		};
		
		// First Rank
		board[7] = new ChessPiece[] {
				new ChessPiece(ChessPiece.ROOK, 	ChessPiece.WHITE),
				new ChessPiece(ChessPiece.KNIGHT, 	ChessPiece.WHITE),
				new ChessPiece(ChessPiece.BISHOP, 	ChessPiece.WHITE),
				new ChessPiece(ChessPiece.QUEEN, 	ChessPiece.WHITE),
				new ChessPiece(ChessPiece.KING, 	ChessPiece.WHITE),
				new ChessPiece(ChessPiece.BISHOP, 	ChessPiece.WHITE),
				new ChessPiece(ChessPiece.KNIGHT, 	ChessPiece.WHITE),
				new ChessPiece(ChessPiece.ROOK, 	ChessPiece.WHITE),
		};
		
		return board;
	}
	
	/**
	 * Converts the board to a readable format
	 */
	public String toString() {
		String board = "";
		
		for (int i = 0; i < HEIGHT - 1; i++)
			board += Arrays.toString(value[i]) + "\n";
		board += Arrays.toString(value[HEIGHT - 1]);
		
		return board;
	}
}
