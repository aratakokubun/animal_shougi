package com.kkbnart.animalshougi.model;

import java.util.ArrayList;

import android.content.res.Resources;
import android.graphics.Point;

import com.kkbnart.animalshougi.controller.GameManager;

public class Chicken extends EvolvePiece {

	public Chicken(final int state, final int owner, final int x, final int y, final int[] pieceImageIds, final Resources resources) {
		super(state, owner, x, y, pieceImageIds, resources);
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
		switch (evolveState) {
		case CHILD:
			// Child can move front
			int dy = (owner == GameManager.FIRST ? -1 : +1);
			if (0 <= y+dy && y+dy < row) {
				nextMoves.add(new Point(x, y+dy));
			}
			break;
		case EVOLVED:
			// Evolved can move left/right/front/back/diagonal forward
			for (int i = -1; i <= 1; i++) {
				for (int j = -1; j <= 1; j++) {
					if ((i != 0 || j != 0) && (0 <= x+i && x+i < column) && (0 <= y+j && y+j < row) 
							&& !(owner == GameManager.FIRST && i != 0 && j == 1) 
							&& !(owner == GameManager.SECOND && i != 0 && j == -1)) {
						nextMoves.add(new Point(x+i, y+j));
					}
				}
			}
			break;
		}
		return nextMoves;
	}
}
