package com.kkbnart.animalshougi.model;

import java.util.ArrayList;

import android.content.res.Resources;
import android.graphics.Point;

public class Lion extends NoEvolvePiece {
	
	public Lion(final int state, final int owner, final int x, final int y, final int pieceImageId, final Resources resources) {
		super(state, owner, x, y, pieceImageId, resources);
	}

	@Override
	public boolean tryPut(final int x, final int y) {
		// Lion can not be off board
		return false;
	}
	
	@Override
	public void taken(final int takenBy) {
		// TODO
		// Game Finish
		super.taken(takenBy);
	}

	@Override
	public ArrayList<Point> getNextMoves(final int column, final int row) {
		// TODO
		// OFF BOARD
		ArrayList<Point> nextMoves = new ArrayList<Point>();
		// Lion can move 8 cells around it
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				if ((i != 0 || j != 0) && (0 <= x+i && x+i < column) && (0 <= y+j && y+j < row)){
					nextMoves.add(new Point(x+i, y+j));
				}
			}
		}
		return nextMoves;
	}
}
