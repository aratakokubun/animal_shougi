package com.kkbnart.animalshougi.board;

import java.util.ArrayList;

import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;

public class BoardSize {

	// Draw board rectangle
	private Rect boardImageRect = null;
	// Draw piece rectangle
	private BoardRectBase boardRect = null;
	// Taken piece rectangle
	private ArrayList<BoardRectBase> offBoardRects = new ArrayList<BoardRectBase>();
	
	public Rect getBoardImageRect() {
		return boardImageRect;
	}

	public BoardRectBase getBoardRect() {
		return boardRect;
	}
	
	public ArrayList<BoardRectBase> getOffBoardRects() {
		return offBoardRects;
	}
	
	public BoardRectBase getOffBoardRect(final int index) {
		return offBoardRects.get(index);
	}
	
	public int getColumn() {
		return boardRect.column;
	}
	
	public int getRow() {
		return boardRect.row;
	}
	
	public int getPlayers() {
		return offBoardRects.size();
	}
	
	public void setSizes(final Rect boardImageRect, final BoardRectBase boardRect, final ArrayList<BoardRectBase> offBoardRects) {
		this.boardImageRect = boardImageRect;
		this.boardRect = boardRect;
		this.offBoardRects = offBoardRects;
	}
	
	public void setSizes(final BoardSize boardSize) {
		this.boardImageRect = boardSize.boardImageRect;
		this.boardRect = boardSize.boardRect;
		this.offBoardRects = boardSize.offBoardRects;
	}
	
	public RectF getPieceRect(final int x, final int y) {
		return boardRect.getPieceRect(x, y);
	}
	
	public Point getPiecePosition(final int x, final int y) {
		return boardRect.getPiecePosition(x, y);
	}
}
