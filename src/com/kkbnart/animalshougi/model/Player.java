package com.kkbnart.animalshougi.model;

import android.os.Handler;
import android.os.Message;

import com.kkbnart.animalshougi.controller.GameManager;

public abstract class Player {
	public static final int WAIT = 0;
	public static final int SELECT = 1;
	public static final int PUT = 2;
	public static final int FINISH = 3;
	
	protected int state;
	protected int selectedPieceIndex;	// Selected piece to move or put
	protected int selectedX, selectedY;	// Position to move or put the selected piece
	protected Handler handler = null;	// Handler to handle message to GameManager
	
	public Player(Handler handler) {
		state = WAIT;
		selectedPieceIndex = -1;
		selectedX = selectedY = -1;
		this.handler = handler;
	}
	
	public int getState() {
		return state;
	}
	
	public int getSelectedPieceIndex() {
		return selectedPieceIndex;
	}

	/* ------------------------------------------------------------------------- */
	public void selectPiece(final int selectedPieceIndex) {
		// If the piece is already selected, de-flag it
		// If not, change selected index
		if (this.selectedPieceIndex == selectedPieceIndex) {
			this.selectedPieceIndex = -1;
			changeState(SELECT);
		} else {
			this.selectedPieceIndex = selectedPieceIndex;
			changeState(PUT);
		}
	}
	
	public void putPiece() {
		changeState(FINISH);
	}
	
	public void changeState(final int nextState) {
		if (state != nextState) {
			state = nextState;
			switch(state) {
			case WAIT:
				onWait();
				break;
			case SELECT:
				onSelect();
				break;
			case PUT:
				onPut();
				break;
			case FINISH:
				onFinish();
				break;
			}
		}
	}
	
	public abstract void onWait();
	
	public abstract void onSelect();
	
	public abstract void onPut();
	
	public void onFinish() {
		try {
			informFinishAction();
		} catch (Exception e) {
			alertTimeout();
		}
	}
	
	public boolean informFinishAction() {
    	Message msg = new Message();
    	msg.what = GameManager.REQUEST_NEXT;
    	handler.sendMessage(msg);
		return true;
	}
	
	public void alertTimeout() {
		// Alert Dialog
		// yes -> changeState(WAIT)
		// no  -> finish game and close connection
		// TODO
	}
}
