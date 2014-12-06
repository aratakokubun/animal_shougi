package com.kkbnart.animalshougi.piecearray;

import java.util.ArrayList;
import java.util.ListIterator;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

import com.kkbnart.animalshougi.board.BoardSize;
import com.kkbnart.animalshougi.piece.AnimalPiece;

public abstract class PieceArray<T extends AnimalPiece> extends ArrayList<T> {
	private static final long serialVersionUID = 1L; // What does this function for? Have to survey why this suppress warning.
	
	protected int owner;
	
	public PieceArray(int owner) {
		super();
		this.owner = owner;
	}
	
	public int getOwner() {
		return owner;
	}

	public boolean onTouch(final BoardSize boardSize, final int x, final int y) {
		ListIterator<T> it = this.listIterator();
		while (it.hasNext()) {
			if (boardRect(boardSize, it.nextIndex()).contains(x, y)) {
				return true;
			}
			it.next();
		}
		return false;
	}
	
	public boolean exist(final int x, final int y) {
		ListIterator<T> it = this.listIterator();
		while (it.hasNext()) {
			if (it.next().exist(x, y)) {
				return true;
			}
		}
		return false;
	}
	
	public abstract Rect boardRect(final BoardSize boardSize, final int index);
	
	public void drawImages(final BoardSize boardSize, Canvas canvas) {
		ListIterator<T> it = this.listIterator();
		while (it.hasNext()) {
			Rect rect = boardRect(boardSize, it.nextIndex());
			it.next().drawPieceImage(rect, canvas);
		}
	}
	
	public void drawHighLights(final BoardSize boardSize, final int index, Canvas canvas, Paint paint) {
		// Highlight this piece
		canvas.drawRect(boardRect(boardSize, index), paint);
	}
	
	public ArrayList<Point> getNextMoves(final int boardColumn, final int boardRow, final int index) {
		return get(index).getNextMoves(boardColumn, boardRow);
	}
	
	public T getPieceAtPosition(final BoardSize boardSize, final int x, final int y) {
		ListIterator<T> it  = this.listIterator();
		while (it.hasNext()) {
			T piece = it.next();
			if (boardRect(boardSize, it.previousIndex()).contains(x, y)) {
				return piece;
			}
		}
		return null;
	}
	
	public int getPieceIndexAtPosition(final BoardSize boardSize, final int x, final int y) {
		ListIterator<T> it  = this.listIterator();
		while (it.hasNext()) {
			it.next();
			if (boardRect(boardSize, it.previousIndex()).contains(x, y)) {
				return it.previousIndex();
			}
		}
		return -1;
	}
}
