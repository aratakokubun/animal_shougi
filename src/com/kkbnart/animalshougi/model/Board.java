package com.kkbnart.animalshougi.model;


import android.content.res.Resources;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

import com.kkbnart.animalshougi.R;
import com.kkbnart.animalshougi.controller.GameManager;

public class Board {
	public static final int[] NORMAL_SIZE = {3, 4};
	public static final int[] GOROGORO_SIZE = {5, 6};
	public static final int[] LARGE_SIZE = {9, 9};
	
	private int boardColumn, boardRow;
	private int boardImageId;
	private Drawable boardImage;

	public Board(final int type, Resources resources) {
		registerBoardboardSize(type);
		registerBoardImageId(type, resources);
	}
	
	public void registerBoardboardSize(final int type) {
		switch (type) {
		case GameManager.GAME_NORMAL:
			boardColumn = NORMAL_SIZE[0];
			boardRow = NORMAL_SIZE[1];
			break;
		case GameManager.GAME_GOROGORO:
			boardColumn = GOROGORO_SIZE[0];
			boardRow = GOROGORO_SIZE[1];
			break;
		case GameManager.GAME_LARGE:
			boardColumn = LARGE_SIZE[0];
			boardRow = LARGE_SIZE[1];
			break;
		}
	}
	
	public void registerBoardImageId(final int type, final Resources resources) {
		switch (type) {
		case GameManager.GAME_NORMAL:
			this.boardImageId = R.drawable.board_normal;
			break;
		case GameManager.GAME_GOROGORO:
			// TODO
			// this.boardImageId = R.drawable.board_gorogoro;
			break;
		case GameManager.GAME_LARGE:
			// TODO
			// this.boardImageId = R.drawable.board_large;
			break;
		}
		boardImage = resources.getDrawable(this.boardImageId);
	}
	
	public int getBoardColumn() {
		return boardColumn;
	}

	public int getBoardRow() {
		return boardRow;
	}

	public int getImageId() {
		return boardImageId;
	}

	public Drawable getImage(Rect rect) {
		boardImage.setBounds(rect);
		return boardImage;
	}
}
