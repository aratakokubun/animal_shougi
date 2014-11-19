package com.kkbnart.animalshougi.controller;

import java.util.ArrayList;

import android.os.Handler;

import com.kkbnart.animalshougi.model.BluetoothMan;
import com.kkbnart.animalshougi.model.Com;
import com.kkbnart.animalshougi.model.Man;
import com.kkbnart.animalshougi.model.Player;

public class PlayerList extends ArrayList<Player> {
	private static final long serialVersionUID = 1L; // What does this function for? Have to survey why this suppress warning.

	public static final int MAN = 0;
	public static final int COM = 1;
	public static final int BT_MAN = 2;
	
	public void registerPlayers(final int[] playerTypes, Handler handler) {
		for (int i = 0; i < playerTypes.length; i++) {
			switch (playerTypes[i]) {
			case MAN:
				add(new Man(handler));
				break;
			case COM:
				add(new Com(handler));
				break;
			case BT_MAN:
				add(new BluetoothMan(handler));
				break;
			}
		}
	}
	
	public void switchTurn(final int turn) {
		get(turn).changeState(Player.SELECT);
		get(1-turn).changeState(Player.WAIT);
	}
	
	public int getPlayerState(final int turn) {
		return get(turn).getState();
	}
}
