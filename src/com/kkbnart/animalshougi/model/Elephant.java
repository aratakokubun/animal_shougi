package com.kkbnart.animalshougi.model;

import java.util.ArrayList;

import android.content.res.Resources;
import android.graphics.Point;

public class Elephant extends NoEvolvePiece {
 	public Elephant(final int state, final int owner, final int x, final int y, final int pieceImageId, final Resources resources) {
		super(state, owner, x, y, pieceImageId, resources);
	}

	@Override
	public boolean tryPut(final int x, final int y) {
		return true;
	}

	@Override
	public ArrayList<Point> getNextMoves(final int column, final int row) {
		// TODO
		// OFF BOARD
		ArrayList<Point> nextMoves = new ArrayList<Point>();
		// Elephant can move diagonal direction
		for (int i = -1; i <= 1; i+=2) {
			for (int j = -1; j <= 1; j+=2) {
				if ((i != 0 || j != 0) && (0 <= x+i && x+i < column) && (0 <= y+j && y+j < row)){
					nextMoves.add(new Point(x+i, y+j));
				}
			}
		}
		return nextMoves;
	}
}
