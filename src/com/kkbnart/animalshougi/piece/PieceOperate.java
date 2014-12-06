package com.kkbnart.animalshougi.piece;

import java.util.ArrayList;

import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

public interface PieceOperate {
	public abstract boolean tryMoveTo(final int boardColumn, final int boardRow, final int x, final int y);
	public abstract boolean exist(final int x, final int y);
	public abstract Drawable getImage(final Rect rect);
	public abstract void drawPieceImage(final Rect rect, Canvas canvas);
	public abstract ArrayList<Point> getNextMoves(final int boardColumn, final int boardRow);
	public abstract boolean isKingPiece();
}
