package com.kkbnart.animalshougi.controller;

import android.content.res.Resources;
import android.graphics.Point;
import android.view.MotionEvent;

import com.kkbnart.animalshougi.board.Board;
import com.kkbnart.animalshougi.controller.PieceArrayCategory.PIECE_ARRAY;
import com.kkbnart.animalshougi.gui.GameDrawSize;
import com.kkbnart.animalshougi.gui.GameViewController;
import com.kkbnart.animalshougi.gui.TouchEnableController;
import com.kkbnart.animalshougi.player.Player;
import com.kkbnart.animalshougi.player.SelectedPiece;

public class GameManager {
	public static final int GAME_NORMAL		= 0;
	public static final int GAME_GOROGORO 	= 1;
	public static final int GAME_LARGE 		= 2;
	
	public static final int FIRST = 0;
	public static final int SECOND = 1;
	
	private int type;	// Game type
	private int turn;	// Turn of which player 0:former 1:latter
	private int countTurn;
	
	public RestorablePieceList pieces;
	public Board board;
	public PlayerList players;
	public GameDrawSize gameDrawSize;
	
	private GameViewController parent;

	public GameManager(GameViewController parent) {
		type = GAME_NORMAL;
		turn = 0;
		countTurn = 0;
		pieces = new RestorablePieceList();
		players = new PlayerList();
		this.parent = parent;
	}

	/* ----------------------------------------------------------- */
	public void registerGameSettings(final int type, final int[] playerTypes, final Resources resources) {
		this.type = type;
		players.registerPlayers(playerTypes);
		pieces.registerPieces(type, resources);
		board = new Board(type, resources);
		gameDrawSize = new GameDrawSize(type);
	}
	
	public void resetPieces(final Resources resources) {
		pieces.registerPieces(type, resources);
	}
	
	/* ----------------------------------------------------------- */
	public boolean startGame() {
		if (checkNull()) {
			return false;
		} else {
			turn = 0;
			countTurn = 0;
			TouchEnableController.getInstance().setIsViewTouchEnabled(true);
			players.switchTurn(turn);
			requestDrawActivity();
			return true;
		}
	}
	
	public void goNext() {
		turn = (turn+1)%2;
		++countTurn;
		players.switchTurn(turn);
		requestDrawActivity();
	}
	
	public void endGame(final int winnerTurn) {
		TouchEnableController.getInstance().setIsViewTouchEnabled(true);
		parent.showGameEndDialog(winnerTurn, players.get(winnerTurn));
	}

	public void resetGame() {
		pieces.clear();
	}
	
	public void cleraGame() {
		pieces.clear();
		board = null;
		players.clear();
	}
	
	private void requestDrawActivity() {
		parent.drawSurfaceView();
	}
	
	private boolean checkNull() {
		return (pieces.isEmpty() || board == null || players.isEmpty());
	}
	
	/* ----------------------------------------------------------- */
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() != MotionEvent.ACTION_DOWN) return false;
		
		SelectedPiece touchedPiece = pieces.getPieceAtPosition(gameDrawSize, (int)event.getX(), (int)event.getY());
		Point p = gameDrawSize.getPiecePosition((int)event.getX(), (int)event.getY());
		
		if (eventSelectPiece(touchedPiece)) {
			requestDrawActivity();
			return true;
		} else if (eventPutPiece(p.x, p.y, touchedPiece)) {
			requestDrawActivity();
			return true;
		}
		
		requestDrawActivity();
		return false;
	}
	
	public boolean eventSelectPiece(final SelectedPiece touchedPiece) {
		// Check if the piece is owned by the player
		if (touchedPiece.isSelected() && touchedPiece.owner == turn) {
			players.get(turn).selectPiece(touchedPiece);
			return true;
		} else {
			return false;
		}
	}
	
	public boolean eventPutPiece (final int x, final int y, final SelectedPiece touchedPiece) {
		SelectedPiece selectedPiece = players.get(turn).getSelectedPiece();
		if (players.get(turn).getIsState(Player.PUT)) {
			if (pieces.get(selectedPiece).tryMoveTo(gameDrawSize.getColumn(), gameDrawSize.getRow(), x, y)) {
				players.get(turn).putPiece();
				eventTakePiece(touchedPiece);
				goNext();
				return true;
			} else {
				return eventErrorPopUp();
			}
		}
		return false;
	}
	
	public boolean eventTakePiece (final SelectedPiece takenPiece) {
		if (takenPiece.isSelected()) {
			boolean isKingPiece = pieces.get(takenPiece).isKingPiece(); // Get king flag before piece move
			pieces.taken(takenPiece, turn, PIECE_ARRAY.OFF_BOARD);
			if (isKingPiece) {
				endGame(turn);
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