package com.kkbnart.animalshougi.controller;

public interface PieceArrayCategory {
	public static enum PIECE_ARRAY {
		NONE(0),
		PLAYER(1),
		OFF_BOARD(2);
		
		final int index;
		public int getIndex() {return index;}
		private PIECE_ARRAY(int index) {this.index = index;}
	}
}
