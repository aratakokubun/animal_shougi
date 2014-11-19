package com.kkbnart.animalshougi.model;

import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;

public abstract class Piece implements PieceOperate {
	// State of on/off board of the piece
	public static final int ON_BOARD = 1;
	public static final int OFF_BOARD = 2;
	
	protected int state;	// State of on/off board
	protected int owner;	// Which player own this piece
	protected int x, y;		// Position on board

	public Piece(final int state, final int owner, final int x, final int y) {
		this.state = state;
		this.owner = owner;
		this.x = x;
		this.y = y;
	}
	
	public int getState() {
		return state;
	}
	
	public int getOwner() {
		return owner;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public boolean getIsPlayerPiece(final int x, final int y, final int owner) {
		return (this.x == x && this.y == y && this.owner == owner && this.state == ON_BOARD);
	}
	
	public boolean getIsOnBoardPiece(final Rect rect, final int column, final int row, final int x, final int y) {
		return (getPieceRect(rect, column, row).contains(x, y) && this.state == ON_BOARD);
	}
	
	/* ------------------------------------------------------ */
	public void moveTo(final int x, final int y) {
		this.x = x;
		this.y = y;
	}
	
	public void taken(final int takenBy) {
		this.state = OFF_BOARD;
		this.owner = takenBy;
	}
	
	public void put(final int x, final int y) {
		this.state = ON_BOARD;
		this.x = x;
		this.y = y;
	}

	/* ------------------------------------------------------ */
	@Override
	public boolean tryMoveTo(final int x, final int y, final int column, final int row) {
		if (getNextMoves(column, row).contains(new Point(x, y))) {
			moveTo(x, y);
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public Rect getPieceRect(final Rect rect, final int column, final int row) {
		// TODO
		// OFF BOARD
		// Have change position with the type of piece
		return new Rect(
				rect.left + (int)(rect.width()  * ((float)x/(float)column)),
				rect.top  + (int)(rect.height() * ((float)y/(float)row)),
				rect.left + (int)(rect.width()  * ((float)(x+1)/(float)column)),
				rect.top  + (int)(rect.height() * ((float)(y+1)/(float)row)) );
	}
	
	@Override
	public void drawPieceImage(final Rect rect, final int column, final int row, Canvas canvas) {
		if (state == ON_BOARD) {
			getImage(rect, column, row).draw(canvas);
		}
	}
	
	/* ------------------------------------------------------ */
	// To get draw rectangle for generated piece position
	public static Rect getPieceRect(final Rect rect, final int x, final int y, final int column, final int row) {
		return new Rect(
				rect.left + (int)(rect.width()  * ((float)x/(float)column)),
				rect.top  + (int)(rect.height() * ((float)y/(float)row)),
				rect.left + (int)(rect.width()  * ((float)(x+1)/(float)column)),
				rect.top  + (int)(rect.height() * ((float)(y+1)/(float)row)) );
	}
	
	// To get board column and row from touch position
	public static Point getPiecePosition(final Rect rect, final int x, final int y, final int column, final int row) {
		return new Point(
				(int)(((float)(x - rect.left)/(float)rect.width() )*column),
				(int)(((float)(y - rect.top )/(float)rect.height())*row));
	}
}
