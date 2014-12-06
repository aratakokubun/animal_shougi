package com.kkbnart.animalshougi.player;



public abstract class AnimalShougiPlayer extends Player {
	protected SelectedPiece selectedPiece;

	public AnimalShougiPlayer() {
		super();
		selectedPiece = new SelectedPiece();
	}
	
	
	public SelectedPiece getSelectedPiece() {
		return selectedPiece;
	}
	
	public void selectPiece(final SelectedPiece piece) {
		// If the piece is already selected, clear flag of it
		// If not, change selected index
		if (this.selectedPiece.matchWith(piece)) {
			this.selectedPiece.clearIndex();
			changeState(SELECT);
		} else {
			this.selectedPiece.selected(piece);
			changeState(PUT);
		}
	}
	
	public void putPiece() {
		changeState(FINISH);
	}

	@Override
	public void onFinish() {
		selectedPiece.clearIndex();
	}
}
