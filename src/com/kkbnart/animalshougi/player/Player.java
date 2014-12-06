package com.kkbnart.animalshougi.player;



public abstract class Player implements PlayerOperate {
	public static final int WAIT = 0;
	public static final int SELECT = 1;
	public static final int PUT = 2;
	public static final int FINISH = 3;
	
	protected int state;
	
	public Player() {
		state = WAIT;
	}
	
	public int getState() {
		return state;
	}
	
	public boolean getIsState(final int state) {
		return (this.state == state);
	}

	/* ------------------------------------------------------------------------- */	
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
}
