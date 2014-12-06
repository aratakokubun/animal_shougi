package com.kkbnart.animalshougi.piecearray;

import android.graphics.Rect;

import com.kkbnart.animalshougi.board.BoardSize;
import com.kkbnart.animalshougi.piece.AnimalPiece;

public class OffBoardPieceArray<T extends AnimalPiece> extends PieceArray<T> {
	private static final long serialVersionUID = 1L;
	
	public OffBoardPieceArray(int owner) {
		super(owner);
	}

	@Override
	public Rect boardRect(final BoardSize boardSize, final int index) {
		Rect ret = new Rect();
		boardSize.getOffBoardRect(index).getPieceRect(index, 1).round(ret);
		return ret;
	}
}
