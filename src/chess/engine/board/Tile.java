package chess.engine.board;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import chess.engine.pieces.Piece;

/**
 * Tile - Represents a single space on the board 
 * @since 1/8/2021
 */
public abstract class Tile {

	protected final int tileCoordinate;
	
	private static final Map<Integer, EmptyTile> EMPTY_TILES = createEmptyTiles();
	
	/**
	 * Constructs a tile
	 * @param tileCoordinate - location on the board
	 */
	private Tile(final int tileCoordinate) {
		this.tileCoordinate = tileCoordinate;
	}
	
	/**
	 * @return whether or not the tile is occupied
	 */
	public abstract boolean isTileOccupied();
	
	/**
	 * @return the piece on the current tile
	 */
	public abstract Piece getPiece();
	
	/**
	 * @return All possible empty tiles
	 */
	private static Map<Integer, EmptyTile> createEmptyTiles() {
		
		final Map<Integer, EmptyTile> emptyTiles = new HashMap<>();
		
		for(int i = 0; i < BoardUtils.NUM_TILES; i++)
			emptyTiles.put(i, new EmptyTile(i));
		
		return Collections.unmodifiableMap(emptyTiles);
	}
	
	/**
	 * An accessor method to create tiles outside of the class
	 * @param tileCoordinate - the position on the board
	 * @param piece - the piece on the tile, null if there is none
	 * @return the constructed tile
	 */
	public static Tile createTile(final int tileCoordinate, final Piece piece) {
		return piece != null ? new OccupiedTile(tileCoordinate, piece) : 
			EMPTY_TILES.get(tileCoordinate);
	}
	
	/**
	 * EmptyTile - Represents an empty tile
	 */
 	public static final class EmptyTile extends Tile {
		
		/**
		 * Constructs an empty tile
		 * @param coordinate - location on the board
		 */
		private EmptyTile(final int coordinate) {
			super(coordinate);
		}

		@Override
		public String toString() {
			return "-";
		}
		
		@Override
		public boolean isTileOccupied() {
			return false;
		}

		@Override
		public Piece getPiece() {
			return null;
		}
	}
	
	/**
	 * OccupiedTile - represents an occupied tile
	 */
	public static final class OccupiedTile extends Tile {

		private final Piece pieceOnTile;
		
		/**
		 * Constructs an occupied tile
		 * @param tileCoordinate - location on the board
		 * @param pieceOnTile - the piece occupying the location
		 */
		private OccupiedTile(final int tileCoordinate, final Piece pieceOnTile) {
			super(tileCoordinate);
			this.pieceOnTile = pieceOnTile;
		}
		
		@Override
		public String toString() {
			return getPiece().getPieceTeam().isBlack() ? getPiece().toString().toLowerCase() :
				getPiece().toString();
		}
		
		@Override
		public boolean isTileOccupied() {
			return true;
		}

		@Override
		public Piece getPiece() {
			return pieceOnTile;
		}
	}
}
