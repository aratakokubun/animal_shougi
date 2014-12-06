package com.kkbnart.animalshougi.piece;

import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;

public abstract class AnimalPiece extends RotatablePiece implements PieceOperate {

	public AnimalPiece(int x, int y, int direction) {
		super(x, y, direction);
	}

	protected void moveTo(final int boardColumn, final int boardRow, final int x, final int y) {
		moveTo(x, y);
	}
	
	@Override
	public boolean tryMoveTo(final int boardColumn, final int boardRow, final int x, final int y) {
		if (getNextMoves(boardColumn, boardRow).contains(new Point(x, y))) {
			moveTo(boardColumn, boardRow, x, y);
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public void drawPieceImage(final Rect rect, Canvas canvas) {
		getImage(rect).draw(canvas);
	}
}
