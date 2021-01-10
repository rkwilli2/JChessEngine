package chess.engine.pieces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import chess.engine.board.Board;
import chess.engine.board.BoardUtils;
import chess.engine.board.Move;
import chess.engine.board.Move.AttackMove;
import chess.engine.board.Move.MajorMove;
import chess.engine.pieces.Piece.PieceType;
import chess.engine.Team;

/**
 * Pawn - Represents the pawn piece
 * @since 1/10/2020
 */
public class Pawn extends Piece {

	public static final int[] POSSIBLE_MOVES = {16, 9, 8, 7};
	
	/**
	 * Constructs a pawn piece
	 * @param pieceTeam - the team of the piece
	 * @param piecePosition - location on the board
	 */
	public Pawn(final Team pieceTeam, final int piecePosition) {
		super(pieceTeam, piecePosition);
	}

	@Override
	public Collection<Move> getLegalMoves(final Board board) {
		
		final List<Move> legalMoves = new ArrayList<Move>();
		
		for(final int candidateOffset : POSSIBLE_MOVES) {
			
			final int candidateCoordinate = this.piecePosition + (this.getPieceTeam().getDirection() * candidateOffset);
			
			if (!BoardUtils.isValidCoordinate(candidateCoordinate)) {
				continue;
			}
			
			if (candidateOffset == 8 && board.getTile(candidateCoordinate).isTileOccupied()) {
				//TODO promotion
				legalMoves.add(new MajorMove(board, this, candidateCoordinate));
			} else if (candidateOffset == 16 && this.isFirstMove() && 
					(BoardUtils.SECOND_ROW[this.piecePosition] && this.getPieceTeam().isBlack()) || 
					(BoardUtils.SEVENTH_ROW[this.piecePosition] && this.getPieceTeam().isWhite() )) {
				
				final int behindCandidateCoordinate = this.piecePosition + (this.pieceTeam.getDirection() * 8);
				if (!board.getTile(behindCandidateCoordinate).isTileOccupied() && !board.getTile(candidateCoordinate).isTileOccupied()) {
					legalMoves.add(new MajorMove(board, this, candidateCoordinate));
				}
			} else if (candidateOffset == 7 &&
					!((BoardUtils.EIGHTH_COLUMN[this.piecePosition] && this.getPieceTeam().isWhite() || 
					 (BoardUtils.FIRST_COLUMN[this.piecePosition] && this.getPieceTeam().isBlack() )))) {
				
				if (board.getTile(candidateCoordinate).isTileOccupied()) {
					final Piece candidatePiece = board.getTile(candidateCoordinate).getPiece();
					if (this.pieceTeam != candidatePiece.getPieceTeam()) {
						//TODO
						legalMoves.add(new MajorMove(board, this, candidateCoordinate));
					}
				}
				
			} else if (candidateOffset == 9 &&
					!((BoardUtils.FIRST_COLUMN[this.piecePosition] && this.getPieceTeam().isWhite() || 
					 (BoardUtils.EIGHTH_COLUMN[this.piecePosition] && this.getPieceTeam().isBlack() )))) {
				
				if (board.getTile(candidateCoordinate).isTileOccupied()) {
					final Piece candidatePiece = board.getTile(candidateCoordinate).getPiece();
					if (this.pieceTeam != candidatePiece.getPieceTeam()) {
						//TODO
						legalMoves.add(new MajorMove(board, this, candidateCoordinate));
					}
				}
			}
		}
		
		return Collections.unmodifiableList(legalMoves);
	}
	
	@Override
	public String toString() {
		return PieceType.PAWN.toString();
	}

}
