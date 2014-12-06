package com.kkbnart.animalshougi.piece;


public class Piece {
	public int x, y;		// Position on board

	public Piece(final int x, final int y) {
		this.x = x;
		this.y = y;
	}
	
	public boolean exist(final int x, final int y) {
		return (this.x == x && this.y == y);
	}
	
	public void moveTo(final int x, final int y) {
		this.x = x;
		this.y = y;
	}
}
