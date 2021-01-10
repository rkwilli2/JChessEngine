package chess.engine.pieces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import chess.engine.Team;
import chess.engine.board.Board;
import chess.engine.board.BoardUtils;
import chess.engine.board.Move;
import chess.engine.board.Move.AttackMove;
import chess.engine.board.Move.MajorMove;
import chess.engine.pieces.Piece.PieceType;
import chess.engine.board.Tile;

/**
 * King - Represents the King piece
 * @since 1/10/2020
 */
public class King extends Piece {
	
	public static final int[] POSSIBLE_MOVES = {-9, -8, -7, -1, 1, 7, 8, 9};

	/**
	 * Constructs a King piece
	 * @param pieceTeam - the team of the piece
	 * @param piecePosition - location on the board
	 */
	public King(final Team pieceTeam, final int piecePosition) {
		super(pieceTeam, piecePosition);
	}

	@Override
	public Collection<Move> getLegalMoves(final Board board) {
		
		final List<Move> legalMoves = new ArrayList<Move>();
		
		for (final int candidateOffset : POSSIBLE_MOVES) {
			final int candidateCoord = this.piecePosition + candidateOffset;
			
			if (checkFirstCol(this.piecePosition, candidateOffset) ||
				checkEighthCol(this.piecePosition, candidateOffset)) {
				continue;
			}
			
			if (BoardUtils.isValidCoordinate(candidateCoord)) {
				final Tile candidateTile = board.getTile(candidateCoord);
				if (!candidateTile.isTileOccupied()) {
					legalMoves.add(new MajorMove(board, this, candidateCoord));
				} else {
					final Piece candidatePiece = candidateTile.getPiece();
					final Team candidatePieceTeam = candidatePiece.getPieceTeam();
					
					if(this.pieceTeam != candidatePieceTeam) {
						legalMoves.add(new AttackMove(board, this, candidateCoord, candidatePiece));
					}
				}
			}
		}
		
		return Collections.unmodifiableList(legalMoves);
	}
	
	@Override
	public String toString() {
		return PieceType.KING.toString();
	}
	
	/**
	 * Checks the first column exception
	 * @param currentPos - the current position of the piece
	 * @param candidateOffset
	 * @return whether or not the piece has the first column exception
	 */
	private static boolean checkFirstCol(final int currentPos, final int candidateOffset) {
		return BoardUtils.FIRST_COLUMN[currentPos] && ((candidateOffset == -9) || (candidateOffset == -1) ||
				(candidateOffset == 7));
	}
	
	/**
	 * Checks the eighth column exception
	 * @param currentPos - the current position of the piece
	 * @param candidateOffset
	 * @return whether or not the piece has the eighth column exception
	 */
	private static boolean checkEighthCol(final int currentPos, final int candidateOffset) {
		return BoardUtils.EIGHTH_COLUMN[currentPos] && ((candidateOffset == -7) || (candidateOffset == 1) ||
				(candidateOffset == 9));
	}

}
