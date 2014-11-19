package com.kkbnart.animalshougi.gui;

import android.graphics.Rect;

import com.kkbnart.animalshougi.controller.GameManager;
import com.kkbnart.animalshougi.model.Board;

public class GameDrawSize {
	private static final float RATE_WIDTH_IN_VIEW = .96f;
	private static final float RATE_HEIGHT_IN_VIEW = .75f;
	
	private static final float PIECE_IN_BOARD = .84f;
	
	private static final float TAKEN_RATE_WIDTH = .96f;
	private static final float TAKEN_RATE_HEIGHT = .9f;
	
	// Singleton instance
	private static GameDrawSize instance = null;
	
	// Draw view size
	private int viewWidth, viewHeight;
	// Draw board rectangle
	private Rect boardRect;
	// Draw piece rectangle
	private Rect pieceRect;
	// Taken piece rectangle
	private Rect[] takenPieceRect;

	public static synchronized GameDrawSize getInstance() {
		if (instance == null) {
			instance = new GameDrawSize();
		}
		return instance;
	}
	
	public GameDrawSize() {
		updateSize(0, 0);
		takenPieceRect = new Rect[2];
	}
	
	public void updateSize(int width, int height) {
		this.viewWidth = width;
		this.viewHeight = height;
	}
	
	public void calcBoardSize(Board board) {
		float imageSizeRate = ((float)(viewHeight*RATE_HEIGHT_IN_VIEW)/(float)(viewWidth*RATE_WIDTH_IN_VIEW));
		float boardSizeRate = ((float)board.getBoardRow()/(float)board.getBoardColumn());

		// Rectangle to put board
		float boardWidth, boardHeight;
		if (imageSizeRate > boardSizeRate) {
			boardWidth = (int)(viewWidth*RATE_WIDTH_IN_VIEW);
			boardHeight = (int)(boardWidth*boardSizeRate);
		} else {
			boardHeight = (int)(viewHeight*RATE_HEIGHT_IN_VIEW);
			boardWidth = (int)(boardHeight/boardSizeRate);
		}
		boardRect = new Rect(
				(int)((viewWidth -boardWidth )/2.f),
				(int)((viewHeight-boardHeight)/2.f),
				(int)((viewWidth -boardWidth )/2.f) + (int)boardWidth,
				(int)((viewHeight-boardHeight)/2.f) + (int)boardHeight);
		
		// Rectangle to put pieces
		float pieceAreaWidth = boardWidth*PIECE_IN_BOARD;
		float pieceAreaHeight = boardHeight*PIECE_IN_BOARD;
		pieceRect = new Rect(
				(int)((viewWidth -pieceAreaWidth )/2.f),
				(int)((viewHeight-pieceAreaHeight)/2.f),
				(int)((viewWidth -pieceAreaWidth )/2.f) + (int)pieceAreaWidth,
				(int)((viewHeight-pieceAreaHeight)/2.f) + (int)pieceAreaHeight);
		
		// Rectangle to put taken pieces
		float takenAreaWidth = boardWidth*TAKEN_RATE_WIDTH;
		float takenAreaHeight = (viewHeight-boardHeight)*TAKEN_RATE_HEIGHT;
		// TODO
		// コマの数に応じてサイズ（縦横比）を変えてもいい？もしくは描画時に調整する
		// First player front(bottom) of view
		takenPieceRect[GameManager.FIRST] = new Rect(
				(int)((viewWidth -takenAreaWidth )/2.f),
				(int)((viewHeight-boardHeight-takenAreaHeight)/4.f + boardRect.bottom),
				(int)((viewWidth -takenAreaWidth )/2.f) + (int)takenAreaWidth,
				(int)((viewHeight-boardHeight-takenAreaHeight)/4.f + boardRect.bottom + takenAreaHeight/2.f));
		// Second player back(top) of view
		takenPieceRect[GameManager.SECOND] = new Rect(
				(int)((viewWidth -takenAreaWidth )/2.f),
				(int)((viewHeight-boardHeight-takenAreaHeight)/4.f),
				(int)((viewWidth -takenAreaWidth )/2.f) + (int)takenAreaWidth,
				(int)((viewHeight-boardHeight-takenAreaHeight)/4.f + takenAreaHeight/2.f));
	}
	
	public Rect getBoardRect() {
		return boardRect;
	}
	
	public Rect getPieceAreaRect() {
		return pieceRect;
	}
	
	public Rect getTakenPieceRect(int turn) {
		// TODO
		return new Rect();
	}
}