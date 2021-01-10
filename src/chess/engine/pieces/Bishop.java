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
import chess.engine.board.Tile;

/**
 * Bishop - represents the bishop piece
 * @since 1/8/2021
 */
public class Bishop extends Piece {
	
	private final static int[] MOVE_VECTORS = {-9, -7, 7, 9};

	/**
	 * Constructs a bishop piece
	 * @param pieceTeam - the team of the piece
	 * @param piecePosition - location on the board
	 */
	public Bishop(final Team pieceTeam, final int piecePosition) {
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
						legalMoves.add(new MajorMove(board, this, candidateCoord));
					} else {
						final Piece candidatePiece = candidateTile.getPiece();
						final Team candidatePieceTeam = candidatePiece.getPieceTeam();
						
						if(this.pieceTeam != candidatePieceTeam) {
							legalMoves.add(new AttackMove(board, this, candidateCoord, candidatePiece));
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
		return PieceType.BISHOP.toString();
	}
	
	/**
	 * @param currentPos
	 * @param candidateOffset
	 * @return whether or not the first column exception applies
	 */
	private static boolean checkFirstCol(final int currentPos, final int candidateOffset) {
		return BoardUtils.FIRST_COLUMN[currentPos] && ((candidateOffset == -9) || (candidateOffset == 7));
	}
	
	/**
	 * @param currentPos
	 * @param candidateOffset
	 * @return whether or not the eighth column exception applies
	 */
	private static boolean checkEighthCol(final int currentPos, final int candidateOffset) {
		return BoardUtils.EIGHTH_COLUMN[currentPos] && ((candidateOffset == -7) || (candidateOffset == 9));
	}
}
