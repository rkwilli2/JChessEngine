package chess.engine.board;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import chess.engine.Team;
import chess.engine.pieces.Bishop;
import chess.engine.pieces.King;
import chess.engine.pieces.Knight;
import chess.engine.pieces.Pawn;
import chess.engine.pieces.Piece;
import chess.engine.pieces.Queen;
import chess.engine.pieces.Rook;

/**
 * Board - Represents a Board
 */
public class Board {

	private final List<Tile> gameBoard;
	private final Collection<Piece> whitePieces;
	private final Collection<Piece> blackPieces;
	
	/**
	 * Constructs an immutable Board
	 * @param builder
	 */
	private Board(Builder builder) {
		this.gameBoard = createGameBoard(builder);
		this.whitePieces = calculateActivePieces(this.gameBoard, Team.WHITE);
		this.blackPieces = calculateActivePieces(this.gameBoard, Team.BLACK);
	
		final Collection<Move> whiteLegalMoves = calculateLegalMoves(this.whitePieces);
		final Collection<Move> blackLegalMoves = calculateLegalMoves(this.blackPieces);
	}
	
	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		for (int i = 0; i < BoardUtils.NUM_TILES; i++) {
			final String tileText = this.gameBoard.get(i).toString();
			builder.append(String.format("%3s", tileText));
			if ((i + 1) % BoardUtils.NUM_TILES_PER_ROW == 0) {
				builder.append("\n");
			}
		}
		return builder.toString();
	}
	
	/**
	 * @param coordinate
	 * @return the tile at a particular coordinate
	 */
	public Tile getTile(final int coordinate) {
		return gameBoard.get(coordinate);
	}
	
	/**
	 * @param builder
	 * @return an immutable game board from the builder
	 */
	private static List<Tile> createGameBoard(final Builder builder) {
		final List<Tile> tiles = new ArrayList<Tile>();
		
		for (int i = 0; i < BoardUtils.NUM_TILES; i++)
			tiles.add(Tile.createTile(i, builder.boardData.get(i)));
		
		return Collections.unmodifiableList(tiles);
	}
	
	/**
	 * @param gameBoard
	 * @param team
	 * @return a collection of all pieces for a team
	 */
	private Collection<Piece> calculateActivePieces(final List<Tile> gameBoard, final Team team) {
		final List<Piece> pieces = new ArrayList<Piece>();
		
		for (final Tile tile : gameBoard) {
			if (tile.isTileOccupied()) {
				final Piece piece = tile.getPiece();
				if(piece.getPieceTeam() == team) {
					pieces.add(piece);
				}
			}
		}
		
		return Collections.unmodifiableList(pieces);
	}
	
	/**
	 * @param pieces - a set of pieces to calculate moves for
	 * @return the set of all legal moves that a set of pieces can do
	 */
	private Collection<Move> calculateLegalMoves(final Collection<Piece> pieces) {
		
		final List<Move> legalMoves = new ArrayList<Move>();
		
		for (final Piece piece : pieces) {
			legalMoves.addAll(piece.getLegalMoves(this));
		}
		
		return Collections.unmodifiableList(legalMoves);
	}
	
	/**
	 * @return a board in its initial state
	 */
	public static Board createStandardBoard() {
		final Builder builder = new Builder();
		// Black
		builder.setPiece(new Rook(Team.BLACK, 0));
		builder.setPiece(new Knight(Team.BLACK, 1));
		builder.setPiece(new Bishop(Team.BLACK, 2));
		builder.setPiece(new Queen(Team.BLACK, 3));
		builder.setPiece(new King(Team.BLACK, 4));
		builder.setPiece(new Bishop(Team.BLACK, 5));
		builder.setPiece(new Knight(Team.BLACK, 6));
		builder.setPiece(new Rook(Team.BLACK, 7));
		for (int i = 8; i <= 15; i++)
			builder.setPiece(new Pawn(Team.BLACK, i));
		// White
		for (int i = 48; i <= 55; i++)
			builder.setPiece(new Pawn(Team.WHITE, i));
		builder.setPiece(new Rook(Team.WHITE, 56));
		builder.setPiece(new Knight(Team.WHITE, 57));
		builder.setPiece(new Bishop(Team.WHITE, 58));
		builder.setPiece(new Queen(Team.WHITE, 59));
		builder.setPiece(new King(Team.WHITE, 60));
		builder.setPiece(new Bishop(Team.WHITE, 61));
		builder.setPiece(new Knight(Team.WHITE, 62));
		builder.setPiece(new Rook(Team.WHITE, 63));
		// White moves first
		builder.setTurn(Team.WHITE);
		
		return builder.build();
	}
	
	/**
	 * Builder - A helper class for building a board
	 */
	public static class Builder {
		
		Map<Integer, Piece> boardData;
		Team turn;
		
		public Builder() {
			this.boardData = new HashMap<>();
		}
		
		/**
		 * Inserts a piece in its piecePosition
		 * @param piece - a given piece
		 * @return this
		 */
		public Builder setPiece(final Piece piece) {
			this.boardData.put(piece.getPiecePosition(), piece);
			return this;
		}
		
		/**
		 * Sets whose turn it is
		 * @param team - whose turn it is
		 * @return this
		 */
		public Builder setTurn(final Team team) {
			this.turn = team;
			return this;
		}
		
		public Board build() {
			return new Board(this);
		}
		
	}
}
