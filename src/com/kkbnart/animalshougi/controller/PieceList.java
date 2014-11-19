package com.kkbnart.animalshougi.controller;

import java.util.ArrayList;
import java.util.ListIterator;

import android.content.res.Resources;
import android.graphics.Rect;

import com.kkbnart.animalshougi.R;
import com.kkbnart.animalshougi.model.Chicken;
import com.kkbnart.animalshougi.model.Elephant;
import com.kkbnart.animalshougi.model.Giraffe;
import com.kkbnart.animalshougi.model.Lion;
import com.kkbnart.animalshougi.model.Piece;

public class PieceList extends ArrayList<Piece> {
	private static final long serialVersionUID = 1L; // What does this function for? Have to survey why this suppress warning.

	public void registerPieces(final int type, final Resources resources) {
		switch (type) {
		case GameManager.GAME_NORMAL:
			registerNormal(resources);
			break;
		case GameManager.GAME_GOROGORO:
			registerGoroGoro(resources);
			break;
		case GameManager.GAME_LARGE:
			registerLarge(resources);
			break;
		}
	}
	
	public void registerNormal(final Resources resources) {
		// 2 Chicken [1,1], [1,2]
		int[] chickenImageIds = {R.drawable.chick, R.drawable.chicken};
		add(new Chicken(Piece.ON_BOARD, GameManager.SECOND, 	1, 1, chickenImageIds, resources));
		add(new Chicken(Piece.ON_BOARD, GameManager.FIRST, 1, 2, chickenImageIds, resources));
		// 2 elephant [2,0], [0,3]
		add(new Elephant(Piece.ON_BOARD, GameManager.SECOND,  2, 0, R.drawable.elephant, resources));
		add(new Elephant(Piece.ON_BOARD, GameManager.FIRST, 0, 3, R.drawable.elephant, resources));
		// 2 giraffe [0,0], [2,3]
		add(new Giraffe(Piece.ON_BOARD, GameManager.SECOND,  0, 0, R.drawable.giraffe, resources));
		add(new Giraffe(Piece.ON_BOARD, GameManager.FIRST, 2, 3, R.drawable.giraffe, resources));
		// 2 lion [1,0], [1,3]
		add(new Lion(Piece.ON_BOARD, GameManager.SECOND,  1, 0, R.drawable.lion, resources));
		add(new Lion(Piece.ON_BOARD, GameManager.FIRST, 1, 3, R.drawable.lion, resources));
	}
	
	public void registerGoroGoro(final Resources resources) {
		// TODO
	}
	
	public void registerLarge(final Resources resources) {
		// TODO
	}
	
	public boolean isPlayerPieceExist(final int x, final int y, final int turn) {
		ListIterator<Piece> it = listIterator();
		while (it.hasNext()) {
			if (it.next().getIsPlayerPiece(x, y, turn)) {
				return true;
			}
		}
		return false;
	}
	
	// If piece not exist, return -1
	public int getPieceIndexAtPosition(final Rect pieceRect, final int x, final int y, final int column, final int row) {
		ListIterator<Piece> it = listIterator();
		while (it.hasNext()) {
			// If piece rectangle contains touch position
			if (it.next().getIsOnBoardPiece(pieceRect, column, row, x, y)) {
				return it.previousIndex();
			}
		}
		return -1;
	}
}
