package com.kkbnart.animalshougi.gui;

import com.kkbnart.animalshougi.board.BoardRectFactory;
import com.kkbnart.animalshougi.board.BoardSize;
import com.kkbnart.animalshougi.controller.GameManager;

public class GameDrawSize extends BoardSize {
	private static final float RATE_WIDTH_IN_VIEW = .77f;
	private static final float RATE_HEIGHT_IN_VIEW = .60f;
	
	private static final float PIECE_IN_BOARD = .84f;
	
	private static final float OFF_BOARD_WIDTH = .96f;
	private static final float OFF_BOARD_HEIGHT = .9f;
	
	public static final int[] NORMAL_SIZE = {3, 4, 3};
	public static final int[] GOROGORO_SIZE = {5, 6, 6};
	public static final int[] LARGE_SIZE = {9, 9, 10};
	
	// Draw view size
	private int viewWidth, viewHeight;
	
	public GameDrawSize() {
		super();
		updateSize(0, 0);
	}
	
	public GameDrawSize(int type) {
		super();
		setBoardSize(type);
	}
	
	public void updateSize(int width, int height) {
		this.viewWidth = width;
		this.viewHeight = height;
		if (getOffBoardRects().size() > 0) {
			calcBoardSize(getPlayers(), getBoardRect().column, getBoardRect().row, getOffBoardRect(0).column);
		}
	}
	
	public void setBoardSize(final int type) {
		int boardColumn = 0, boardRow = 0, offBoardColumn = 0;
		switch (type) {
		case GameManager.GAME_NORMAL:
			boardColumn = NORMAL_SIZE[0];
			boardRow = NORMAL_SIZE[1];
			offBoardColumn = NORMAL_SIZE[2];
			break;
		case GameManager.GAME_GOROGORO:
			boardColumn = GOROGORO_SIZE[0];
			boardRow = GOROGORO_SIZE[1];
			offBoardColumn = GOROGORO_SIZE[2];
			break;
		case GameManager.GAME_LARGE:
			boardColumn = LARGE_SIZE[0];
			boardRow = LARGE_SIZE[1];
			offBoardColumn = LARGE_SIZE[2];
			break;
		}
		calcBoardSize(2/*Always 2 players in this game*/, boardColumn, boardRow, offBoardColumn);
	}
	
	private void calcBoardSize(final int players, final int column, final int row, final int offBoardColumn) {
		BoardRectFactory factory = new BoardRectFactory();
		setSizes(factory.getBoardSize(players, column, row, offBoardColumn, viewWidth, viewHeight,
				RATE_WIDTH_IN_VIEW, RATE_HEIGHT_IN_VIEW, PIECE_IN_BOARD, PIECE_IN_BOARD,
				OFF_BOARD_WIDTH, OFF_BOARD_HEIGHT));
	}
}