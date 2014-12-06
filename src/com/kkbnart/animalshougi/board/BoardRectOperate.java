package com.kkbnart.animalshougi.board;

import android.graphics.Point;
import android.graphics.RectF;

public interface BoardRectOperate {
	
	// To get draw rectangle for generated piece position
	public abstract RectF getPieceRect(final int x, final int y);
	
	// To get board column and row from touch position
	public abstract Point getPiecePosition(final int x, final int y);
}
