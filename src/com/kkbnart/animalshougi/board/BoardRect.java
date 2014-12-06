package com.kkbnart.animalshougi.board;

import android.graphics.Point;
import android.graphics.RectF;


public class BoardRect extends BoardRectBase {
	
	public BoardRect(final RectF rect, final int column, final int row) {
		super(rect, column, row);
	}
	
	@Override
	public RectF getPieceRect(final int x, final int y) {
		return new RectF(
				left + (int)(width()  * ((float)x/(float)column)),
				top  + (int)(height() * ((float)y/(float)row)),
				left + (int)(width()  * ((float)(x+1)/(float)column)),
				top  + (int)(height() * ((float)(y+1)/(float)row)) );
	}
	
	@Override
	public Point getPiecePosition(final int x, final int y) {
		return new Point(
				(int)(((x - left)/width() )*column),
				(int)(((y - top )/height())*row));
	}
}
