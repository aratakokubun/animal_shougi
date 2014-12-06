package com.kkbnart.animalshougi.player;

import com.kkbnart.animalshougi.controller.PieceArrayCategory.PIECE_ARRAY;

public class SelectedPiece {
	public PIECE_ARRAY selectFrom; 	// Array from which player select piece
	public int selectedPieceIndex;	// Selected piece to move or put
	public int selectedX, selectedY;	// Position to move or put the selected piece
	public int owner; // same mean as turn
	
	public SelectedPiece() {
		clearIndex();
	}
	
	public SelectedPiece(final PIECE_ARRAY from, final int selectedPieceIndex, final int selectedX, final int selectedY, final int turn) {
		this.selectFrom = from;
		this.selectedPieceIndex = selectedPieceIndex;
		this.selectedX = selectedX;
		this.selectedY = selectedY;
		this.owner = turn;
	}
	
	public boolean matchWith (final PIECE_ARRAY from, final int selectedPieceIndex, final int turn) {
		return (this.selectFrom == from && this.selectedPieceIndex == selectedPieceIndex && this.owner == turn);
	}
	
	public boolean matchWith (final SelectedPiece other) {
		return matchWith(other.selectFrom, other.selectedPieceIndex, other.owner);
	}
	
	public boolean isSelected() {
		return (this.selectFrom != PIECE_ARRAY.NONE && selectedPieceIndex != -1 && owner != -1);
	}
	
	public void selected (final PIECE_ARRAY from, final int selectedPieceIndex, final int turn) {
		this.selectFrom = from;
		this.selectedPieceIndex = selectedPieceIndex;
		this.owner = turn;
	}
	
	public void selected (final SelectedPiece selectedPiece) {
		selected(selectedPiece.selectFrom, selectedPiece.selectedPieceIndex, selectedPiece.owner);
	}
	
	public void clearIndex() {
		selectFrom = PIECE_ARRAY.NONE;
		selectedPieceIndex = -1;
		selectedX = selectedY = -1;
		owner = -1;
	}

	@Override
	public String toString() {
		return "select from " + String.valueOf(selectFrom) + ", index " + String.valueOf(selectedPieceIndex) + ", turn " + String.valueOf(owner);
	}
}
