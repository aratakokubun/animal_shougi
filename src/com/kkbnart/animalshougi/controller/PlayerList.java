package com.kkbnart.animalshougi.controller;

import java.util.ArrayList;

import com.kkbnart.animalshougi.player.AnimalShougiPlayer;
import com.kkbnart.animalshougi.player.BluetoothMan;
import com.kkbnart.animalshougi.player.Com;
import com.kkbnart.animalshougi.player.Man;
import com.kkbnart.animalshougi.player.Player;

public class PlayerList extends ArrayList<AnimalShougiPlayer> {
	private static final long serialVersionUID = 1L; // What does this function for? Have to survey why this suppress warning.

	public static final int MAN = 0;
	public static final int COM = 1;
	public static final int BT_MAN = 2;
	
	public void registerPlayers(final int[] playerTypes) {
		for (int i = 0; i < playerTypes.length; i++) {
			switch (playerTypes[i]) {
			case MAN:
				add(new Man());
				break;
			case COM:
				add(new Com());
				break;
			case BT_MAN:
				add(new BluetoothMan());
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
