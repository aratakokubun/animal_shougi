package com.kkbnart.animalshougi.board;

import android.graphics.RectF;

public abstract class BoardRectBase extends RectF implements BoardRectOperate {

	public int column, row; // Size of board cells

	public BoardRectBase(final RectF rect, final int column, final int row) {
		super(rect);
		this.column = column;
		this.row = row;
	}
}
