package com.kkbnart.animalshougi.model;

import java.util.ArrayList;

import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

public interface PieceOperate {
	public abstract boolean tryMoveTo(final int x, final int y, final int column, final int row);
	public abstract boolean tryPut(final int x, final int y);
	public abstract int getImageId();
	public abstract Drawable getImage(final Rect rect, final int column, final int row);
	public abstract void drawPieceImage(final Rect rect, final int column, final int row, Canvas canvas);
	public abstract Rect getPieceRect(final Rect rect, final int column, final int row);
	public abstract ArrayList<Point> getNextMoves(final int column, final int row);
}
