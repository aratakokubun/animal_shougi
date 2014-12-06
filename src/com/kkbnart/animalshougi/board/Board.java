package com.kkbnart.animalshougi.board;


import android.content.res.Resources;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

import com.kkbnart.animalshougi.R;
import com.kkbnart.animalshougi.controller.GameManager;

public class Board {
	private int boardImageId;
	private Drawable boardImage;

	public Board(final int type, Resources resources) {
		registerBoardImageId(type, resources);
	}
	
	public void registerBoardImageId(final int type, final Resources resources) {
		switch (type) {
		case GameManager.GAME_NORMAL:
			this.boardImageId = R.drawable.board_normal;
			break;
		case GameManager.GAME_GOROGORO:
			// TODO
			// this.boardImageId = R.drawable.board_gorogoro;
			this.boardImageId = R.drawable.board_normal;
			break;
		case GameManager.GAME_LARGE:
			// TODO
			// this.boardImageId = R.drawable.board_large;
			this.boardImageId = R.drawable.board_normal;
			break;
		}
		boardImage = resources.getDrawable(this.boardImageId);
	}

	public int getImageId() {
		return boardImageId;
	}

	public Drawable getImage(Rect rect) {
		boardImage.setBounds(rect);
		return boardImage;
	}
}
