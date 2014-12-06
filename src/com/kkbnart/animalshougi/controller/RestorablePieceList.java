package com.kkbnart.animalshougi.controller;

import com.kkbnart.animalshougi.piece.PieceListMemento;

public class RestorablePieceList extends PieceList {
	
	private PieceListMemento memento;
	
	public RestorablePieceList() {
		super();
		memento = new PieceListMemento();
	}

	public void takeAction() {
		memento.add(this);
	}
	
	public PieceList restoreState() {
		return memento.restoreState();
	}
	
	public PieceList proceedState() {
		return memento.proceedState();
	}
	
	public boolean checkRestorable() {
		return memento.getIterIndex() > 0;
	}
	
	public boolean checkProceedable() {
		return memento.getIterIndex() < memento.size() - 1;
	}
}
