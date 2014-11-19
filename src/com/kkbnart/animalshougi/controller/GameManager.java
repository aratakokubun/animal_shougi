package com.kkbnart.animalshougi.controller;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
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
		players.registerPlayers(playerTypes, handler);
	}
	
	public void registerPieces(final Resources resources) {
		pieces.registerPieces(type, resources);
	}
	
	public void registerBoard(final Resources resources) {
		board = new Board(type, resources);
	}
	
	/* ----------------------------------------------------------- */
	public static final int REQUEST_START = 0;
	public static final int REQUEST_NEXT  = 1;
	public static final int REQUEST_END	  = 2;
	public static final int REQUEST_RESET = 3;
    @SuppressLint("HandlerLeak")
	private Handler handler = new Handler() {
    	public void handleMessage(Message msg) {
    		switch (msg.what) {
    		case REQUEST_START:
    			startGame();
    			break;
    		case REQUEST_NEXT:
    			goNext();
    			break;
    		case REQUEST_END:
    			endGame();
    			break;
    		case REQUEST_RESET:
    			resetGame();
    			break;
    		}
	}};
	
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
		
		// Touch position is on other pieces or not
		if (touchPieceIndex < 0) {
			if (isAlreadySelected) {
				// There are no pieces on touch position, but on board
				Point p = Piece.getPiecePosition(pieceRect, (int)event.getX(), (int)event.getY(), boardColumn, boardRow);
				if (pieces.get(selectedPieceIndex)
						.tryMoveTo(p.x, p.y, boardColumn, boardRow)) {
					players.get(turn).putPiece();
					return true;
				} else {
					// TODO
				}
			}
		} else {
			Piece piece = pieces.get(touchPieceIndex);
			// User can select pieces which is owned by turn player
			if (piece.getOwner() == turn) {
				players.get(turn).selectPiece(touchPieceIndex);
				return true;
			} else if (isAlreadySelected) {
				// Take the other player's piece
				if (pieces.get(selectedPieceIndex)
						.tryMoveTo(piece.getX(), piece.getY(), boardColumn, boardRow)) {
					pieces.get(touchPieceIndex).taken(turn);
					players.get(turn).putPiece();
				} else {
					// TODO
				}
				return true;
			}
			
			// TODO
			// OFF BOARD
		}
		
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