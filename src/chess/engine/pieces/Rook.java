package chess.engine.pieces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import chess.engine.board.Board;
import chess.engine.board.BoardUtils;
import chess.engine.board.Move;
import chess.engine.board.Tile;
import chess.engine.pieces.Piece.PieceType;
import chess.engine.Team;

/**
 * Rook - Represents the Rook piece
 * @since 1/8/2020
 */
public class Rook extends Piece {
	
	private final static int[] MOVE_VECTORS = {-8, -1, 1, 8};
	
	/**
	 * Constructs a Rook piece
	 * @param pieceTeam - the team of the piece
	 * @param piecePosition - location on the board
	 */
	public Rook(final Team pieceTeam, final int piecePosition) {
		super(pieceTeam, piecePosition);
	}

	@Override
	public Collection<Move> getLegalMoves(final Board board) {

		final List<Move> legalMoves = new ArrayList<Move>();
		
		for(final int candidateOffset : MOVE_VECTORS) {
			
			int candidateCoord = this.piecePosition;
		
			while (BoardUtils.isValidCoordinate(candidateCoord)) {
				
				if (checkFirstCol(candidateCoord, candidateOffset) ||
						checkEighthCol(candidateCoord, candidateOffset)) {
					break;
				}
				
				candidateCoord += candidateOffset;
				
				if(BoardUtils.isValidCoordinate(candidateCoord)) {
					final Tile candidateTile = board.getTile(candidateCoord);
					
					if (!candidateTile.isTileOccupied()) {
						legalMoves.add(new Move.MajorMove(board, this, candidateCoord));
					} else {
						final Piece candidatePiece = candidateTile.getPiece();
						final Team candidatePieceTeam = candidatePiece.getPieceTeam();
						
						if(this.pieceTeam != candidatePieceTeam) {
							legalMoves.add(new Move.AttackMove(board, this, candidateCoord, candidatePiece));
						}
						break;
					}
				}
			}
		}
		
		return Collections.unmodifiableList(legalMoves);
	}

	@Override
	public String toString() {
		return PieceType.ROOK.toString();
	}
	
	/**
	 * @param currentPos
	 * @param candidateOffset
	 * @return whether or not the first column exception applies
	 */
	private static boolean checkFirstCol(final int currentPos, final int candidateOffset) {
		return BoardUtils.FIRST_COLUMN[currentPos] && ((candidateOffset == -1));
	}
	
	/**
	 * @param currentPos
	 * @param candidateOffset
	 * @return whether or not the eighth column exception applies
	 */
	private static boolean checkEighthCol(final int currentPos, final int candidateOffset) {
		return BoardUtils.EIGHTH_COLUMN[currentPos] && ((candidateOffset == 1));
	}
}
