package com.kkbnart.animalshougi.controller;

import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;

import com.kkbnart.animalshougi.gui.GameDrawSize;
import com.kkbnart.animalshougi.gui.TouchEnableController;
import com.kkbnart.animalshougi.model.Board;
import com.kkbnart.animalshougi.model.Piece;
import com.kkbnart.animalshougi.model.Player;

public class GameManager {
	public static final int GAME_NORMAL		= 0;
	public static final int GAME_GOROGORO 	= 1;
	public static final int GAME_LARGE 		= 2;
	
	public static final int FIRST = 0;
	public static final int SECOND = 1;
	
	private int type;	// Game type
	private int turn;	// Turn of which player 0:former 1:latter
	private int countTurn;
	
	public PieceList pieces;
	public Board board;
	public PlayerList players;

	public GameManager() {
		type = GAME_NORMAL;
		turn = 0;
		countTurn = 0;
		pieces = new PieceList();
		players = new PlayerList();
	}

	/* ----------------------------------------------------------- */
	public void setGameType(final int type) {
		this.type = type;
	}
	
	public void registerPlayers(final int[] playerTypes) {
		players.registerPlayers(playerTypes);
	}
	
	public void registerPieces(final Resources resources) {
		pieces.registerPieces(type, resources);
	}
	
	public void registerBoard(final Resources resources) {
		board = new Board(type, resources);
	}
	
	/* ----------------------------------------------------------- */
	public boolean startGame() {
		if (checkNull()) {
			return false;
		} else {
			turn = 0;
			countTurn = 0;
			TouchEnableController.getInstance().setIsViewTouchEnabled(false);
			players.switchTurn(turn);
			return true;
		}
	}
	
	public void goNext() {
		turn = (turn+1)%2;
		++countTurn;
		players.switchTurn(turn);
	}
	
	public void endGame() {
		// TODO
	}

	public void resetGame() {
		pieces.clear();
		board = null;
		players.clear();
	}
	
	/* ----------------------------------------------------------- */
	public boolean checkNull() {
		return (pieces.isEmpty() || board == null || players.isEmpty());
	}
	
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() != MotionEvent.ACTION_DOWN) return false;
		
		// Calculate with board and piece size
		Rect pieceRect = GameDrawSize.getInstance().getPieceAreaRect();
		int boardColumn = board.getBoardColumn(), boardRow = board.getBoardRow();
		int selectedPieceIndex = players.get(turn).getSelectedPieceIndex();
		int touchPieceIndex = pieces.getPieceIndexAtPosition(pieceRect, (int)event.getX(), (int)event.getY(), boardColumn, boardRow);
		boolean isAlreadySelected = (players.get(turn).getState() == Player.PUT);
		
		Point p = Piece.getPiecePosition(pieceRect, (int)event.getX(), (int)event.getY(), boardColumn, boardRow);
		if (eventSelectPiece(touchPieceIndex)) {
			return true;
		} else if (eventPutPiece(isAlreadySelected, selectedPieceIndex, p.x, p.y, boardColumn, boardRow, touchPieceIndex)) {
			// TODO
			// if Taken piece is Lion, finish game
			// Turn finished
			goNext();
			return true;
		} else {
			// TODO
			// OFF_BOARD
		}
		
		return false;
	}
	
	public boolean eventSelectPiece(final int touchPieceIndex) {
		if (touchPieceIndex >= 0 && pieces.get(touchPieceIndex).getOwner() == turn) {
			players.get(turn).selectPiece(touchPieceIndex);
			return true;
		} else {
			return false;
		}
	}
	
	public boolean eventPutPiece(final boolean isAlreadySelected, final int selectedPieceIndex, final int x, final int y, final int column, final int row, final int touchPieceIndex) {
		if (isAlreadySelected) {
			if (pieces.get(selectedPieceIndex).tryMoveTo(x, y, column, row)) {
				if (touchPieceIndex >= 0) {
					pieces.get(touchPieceIndex).taken(turn);
				}
				players.get(turn).putPiece();
				return true;
			} else {
				return eventErrorPopUp();
			}
		}
		return false;
	}
	
	public boolean eventErrorPopUp() {
		// TODO
		// pop up message
		return false;
	}
	
	/* ----------------------------------------------------------- */
	public int getTurn() {
		return turn;
	}
	
	public int getCountTurn() {
		return countTurn;
	}
}