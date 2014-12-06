package com.kkbnart.animalshougi.piece;

import java.util.ArrayList;

import android.content.res.Resources;
import android.graphics.Point;

public class Chicken extends EvolvePiece {

	public Chicken(final int x, final int y, final int direction, final int[] pieceImageIds, final Resources resources) {
		super(x, y, direction, pieceImageIds, resources);
	}

	@Override
	public ArrayList<Point> getNextMoves(final int boardColumn, final int boardRow) {
		ArrayList<Point> nextMoves = new ArrayList<Point>();
		switch (evolveState) {
		case CHILD:
			// Child can move front
			int dy = 0;
			switch (direction) {
			case DIREC_TOP:
				dy = -1;
				break;
			case DIREC_BOTTOM:
				dy = +1;
				break;
			}
			if (Math.abs(dy) > 0 && 0 <= y+dy && y+dy < boardRow) {
				nextMoves.add(new Point(x, y+dy));
			}
			break;
		case EVOLVED:
			// Evolved can move left/right/front/back/diagonal forward
			for (int i = -1; i <= 1; i++) {
				for (int j = -1; j <= 1; j++) {
					if ((i != 0 || j != 0) && (0 <= x+i && x+i < boardColumn) && (0 <= y+j && y+j < boardRow) 
							&& !(direction == DIREC_TOP && i != 0 && j == -1) 
							&& !(direction == DIREC_BOTTOM && i != 0 && j == 1)) {
						nextMoves.add(new Point(x+i, y+j));
					}
				}
			}
			break;
		}
		return nextMoves;
	}
	
	@Override
	public boolean isKingPiece() {
		return false;
	}
}
