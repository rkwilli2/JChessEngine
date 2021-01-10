package chess.engine.pieces;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import chess.engine.board.Board;
import chess.engine.board.BoardUtils;
import chess.engine.board.Move;
import chess.engine.board.Move.AttackMove;
import chess.engine.board.Move.MajorMove;
import chess.engine.pieces.Piece.PieceType;
import chess.engine.board.Tile;
import chess.engine.Team;

/**
 * Knight - represents the Knight piece
 * @since 1/8/20201
 */
public class Knight extends Piece {

	private final static int[] POSSIBLE_MOVES = {-17, -15, -10, -6, 6, 10, 15, 17};
	
	/**
	 * Constructs a Knight piece
	 * @param pieceTeam - the team of the piece
	 * @param piecePosition - location on the board
	 */
	public Knight(final Team pieceTeam, final int piecePosition) {
		super(pieceTeam, piecePosition);
	}

	@Override
	public List<Move> getLegalMoves(final Board board) {
		
		List<Move> legalMoves = new ArrayList<Move>();
		
		for(final int candidateOffset : POSSIBLE_MOVES) {
			final int candidateCoord = this.piecePosition + candidateOffset;
			
			if (BoardUtils.isValidCoordinate(candidateCoord)) {
				
				if (checkFirstCol(this.piecePosition, candidateOffset) ||
						checkSecondCol(this.piecePosition, candidateOffset) ||
						checkSeventhCol(this.piecePosition, candidateOffset) ||
						checkEighthCol(this.piecePosition, candidateOffset)) {
					continue;
				}

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
		return PieceType.KNIGHT.toString();
	}
	
	/**
	 * Checks the first column exception
	 * @param currentPos - the current position of the piece
	 * @param candidateOffset
	 * @return whether or not the piece has the first column exception
	 */
	private static boolean checkFirstCol(final int currentPos, final int candidateOffset) {
		return BoardUtils.FIRST_COLUMN[currentPos] && ((candidateOffset == -17) || (candidateOffset == -10) ||
				(candidateOffset == 6) || (candidateOffset == 15));
	}
	
	/**
	 * Checks the second column exception
	 * @param currentPos - the current position of the piece
	 * @param candidateOffset
	 * @return whether or not the piece has the second column exception
	 */
	private static boolean checkSecondCol(final int currentPos, final int candidateOffset) {
		return BoardUtils.SECOND_COLUMN[currentPos] && ((candidateOffset == -10) || (candidateOffset == 6));
	}

	/**
	 * Checks the seventh column exception
	 * @param currentPos - the current position of the piece
	 * @param candidateOffset
	 * @return whether or not the piece has the seventh column exception
	 */
	private static boolean checkSeventhCol(final int currentPos, final int candidateOffset) {
		return BoardUtils.SEVENTH_COLUMN[currentPos] && ((candidateOffset == -6) || (candidateOffset == 10));
	}

	/**
	 * Checks the eighth column exception
	 * @param currentPos - the current position of the piece
	 * @param candidateOffset
	 * @return whether or not the piece has the eighth column exception
	 */
	private static boolean checkEighthCol(final int currentPos, final int candidateOffset) {
		return BoardUtils.EIGHTH_COLUMN[currentPos] && ((candidateOffset == -15) || (candidateOffset == -6) ||
				(candidateOffset == 10) || (candidateOffset == 17));
	}
}
