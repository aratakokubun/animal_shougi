package com.kkbnart.animalshougi.piece;

import java.util.ArrayList;

import android.content.res.Resources;
import android.graphics.Point;

public class Elephant extends NoEvolvePiece {
 	public Elephant(final int x, final int y, final int direction, final int pieceImageId, final Resources resources) {
		super(x, y, direction, pieceImageId, resources);
	}

	@Override
	public ArrayList<Point> getNextMoves(final int column, final int row) {
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
	
	@Override
	public boolean isKingPiece() {
		return false;
	}
}
