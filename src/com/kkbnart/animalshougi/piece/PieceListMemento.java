package com.kkbnart.animalshougi.piece;

import java.util.Vector;

import com.kkbnart.animalshougi.controller.PieceList;

public class PieceListMemento extends Vector<PieceList> {
	private static final long serialVersionUID = 1L;
	
	private int iterIndex;

	public PieceListMemento() {
		super();
		iterIndex = 0;
	}
	
	public int getIterIndex() {
		return iterIndex;
	}
	
	@Override
	public boolean add(PieceList list) {
		// Remove elements over iterator index
		for (int i = iterIndex+1; i < size()-1; i++) {
			remove(i);
		}
		// Add new element
		if (super.add(list)) {
			iterIndex++;
			return true;
		} else {
			return false;
		}
	}
	
	public PieceList restoreState() {
		if (iterIndex > 0) {
			iterIndex--;
			return this.elementAt(iterIndex);
		} else {
			return null;
		}
	}
	
	public PieceList proceedState() {
		if (iterIndex < size()-1) {
			iterIndex++;
			return this.elementAt(iterIndex);
		} else {
			return null;
		}
	}
}
