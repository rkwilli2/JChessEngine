package chess.engine.board;

import chess.engine.pieces.Piece;

/**
 * Move - Represents a move in the game
 * @since 1/8/2021
 */
public abstract class Move {

	final Board board;
	final Piece movedPiece;
	final int destination;
	
	/**
	 * Constructs a move
	 * @param board - the board the move is on
	 * @param movedPiece - the piece to be moved
	 * @param destination - where the piece will be moved
	 */
	Move(final Board board, final Piece movedPiece,
			final int destination) {
		this.board = board;
		this.movedPiece = movedPiece;
		this.destination = destination;
	}
	
	/**
	 * MajorMove - Represents a passive, strategic move
	 */
	public static class MajorMove extends Move {

		public MajorMove(final Board board, final Piece movedPiece, 
				final int destination) {
			super(board, movedPiece, destination);
		}
		
	}

	/**
	 * AttackMove - Represents a move that takes another piece
	 */
	public static class AttackMove extends Move {

		final Piece attackedPiece;
		
		public AttackMove(final Board board, final Piece movedPiece,
				final int destination, final Piece attackedPiece) {
			super(board, movedPiece, destination);
			this.attackedPiece = attackedPiece;
		}
		
	}
}
