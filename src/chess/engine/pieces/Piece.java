package chess.engine.pieces;

import java.util.Collection;

import chess.engine.Team;
import chess.engine.board.Board;
import chess.engine.board.Move;

/**
 * Piece - Represents a chess piece
 * @since 1/8/2021
 */
public abstract class Piece {

	protected final int piecePosition;
	protected final Team pieceTeam;
	protected final boolean isFirstMove;
	
	/**
	 * Constructs a piece 
	 * @param piecePosition - the position on the board
	 * @param pieceTeam - the team the piece is on
	 */
	public Piece(final Team pieceTeam, final int piecePosition) {
		this.piecePosition = piecePosition;
		this.pieceTeam = pieceTeam;
		this.isFirstMove = false;
	}
	
	/**
	 * @return the position the piece is in
	 */
	public int getPiecePosition() {
		return this.piecePosition;
	}
	
	/**
	 * @return the team the piece is on
	 */
	public Team getPieceTeam() {
		return this.pieceTeam;
	}
	
	/**
	 * @return whether or not it's the piece's first move
	 */
	public boolean isFirstMove() {
		return this.isFirstMove;
	}
	
	/**
	 * @param board
	 * @return a list of all legal moves the piece can make
	 */
	public abstract Collection<Move> getLegalMoves(final Board board);
	
	/**
	 * PieceType - Represents the different types of pieces
	 */
	public enum PieceType {
		
		PAWN("P"),
		KNIGHT("N"),
		BISHOP("B"),
		ROOK("R"),
		QUEEN("Q"),
		KING("K");
		
		private String pieceName;
		
		PieceType(final String pieceName) {
			this.pieceName = pieceName;
		}
		
		@Override
		public String toString() {
			return this.pieceName;
		}
	}
}
