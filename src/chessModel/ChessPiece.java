package chessModel;

/**
 * ChessPiece - Represents a chess piece
 * @author Richard Williams
 */
public class ChessPiece {

	private char type;
	private float pointValue;
	private boolean isBlack;
	
	public static final char PAWN = 'p';
	public static final char ROOK = 'r';
	public static final char KNIGHT = 'n';
	public static final char BISHOP = 'b';
	public static final char QUEEN = 'q';
	public static final char KING = 'k';
	
	public static boolean BLACK = true;
	public static boolean WHITE = false;
	
	/**
	 * Constructs a chess piece
	 * @param type - the type of chess piece, use the constants in the class
	 * @param isBlack - the color of the piece, use the constants of the class
	 */
	public ChessPiece(char type, boolean isBlack) {
		this.type = type;
		this.isBlack = isBlack;
		
		switch (type) {
			case ChessPiece.PAWN:
				this.pointValue = 1;
				break;
			case ChessPiece.ROOK:
				this.pointValue = 5;
				break;
			case ChessPiece.KNIGHT:
			case ChessPiece.BISHOP:
				this.pointValue = 3;
				break;
			case ChessPiece.QUEEN:
				this.pointValue = 9;
				break;
			case ChessPiece.KING:
				this.pointValue = 100;
				break;
		}
	}
	
	/**
	 * @return the type of the chess piece in question
	 */
	public char getType() {
		return this.type;
	}
	
	/**
	 * @return the point value of the chess piece in question
	 */
	public float getPointValue() {
		return this.pointValue;
	}
	
	/**
	 * @return the color of the chess piece in question
	 */
	public boolean getColor() {
		return this.isBlack;
	}
	
	public String toString() {
		String piece = "";
		
		switch (getType()) {
			case ChessPiece.PAWN:
				piece += "Pawn - ";
				break;
			case ChessPiece.ROOK:
				piece += "Rook - ";
				break;
			case ChessPiece.KNIGHT:
				piece += "Knight - ";
				break;
			case ChessPiece.BISHOP:
				piece += "Bishop - ";
				break;
			case ChessPiece.QUEEN:
				piece += "Queen - ";
				break;
			case ChessPiece.KING:
				piece += "King - ";
				break;
		}
		
		if (getColor() == ChessPiece.BLACK)
			piece += "B";
		else
			piece += "W";
		
		return piece;
	}
}
