package com.kkbnart.animalshougi.piecearray;

import android.graphics.Rect;

import com.kkbnart.animalshougi.board.BoardSize;
import com.kkbnart.animalshougi.piece.AnimalPiece;

public class PlayerPieceArray<T extends AnimalPiece> extends PieceArray<T> {
	private static final long serialVersionUID = 1L;

	public PlayerPieceArray(int owner) {
		super(owner);
	}
	
	@Override
	public Rect boardRect(final BoardSize boardSize, final int index) {
		Rect ret = new Rect();
		AnimalPiece piece = this.get(index);
		boardSize.getBoardRect().getPieceRect(piece.x, piece.y).round(ret);
		return ret;
	}
}
