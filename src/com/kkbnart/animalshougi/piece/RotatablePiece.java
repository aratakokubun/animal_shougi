package com.kkbnart.animalshougi.piece;

public abstract class RotatablePiece extends Piece {
	public static final int DIREC_TOP    = 0;
	public static final int DIREC_RIGHT  = 1;
	public static final int DIREC_BOTTOM = 2;
	public static final int DIREC_LEFT   = 3;
	
	public int direction; // Direction of the piece head

	public RotatablePiece(final int x, final int y, final int direction) {
		super(x, y);
		this.direction = direction;
	}
	
	public void changeDirection(int direction) {
		this.direction = direction;
	}
}
