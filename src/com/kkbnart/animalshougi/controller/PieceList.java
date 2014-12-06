package com.kkbnart.animalshougi.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.ListIterator;
import java.util.Map;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

import com.kkbnart.animalshougi.board.BoardSize;
import com.kkbnart.animalshougi.controller.PieceArrayCategory.PIECE_ARRAY;
import com.kkbnart.animalshougi.piece.AnimalPiece;
import com.kkbnart.animalshougi.piecearray.AnimalPieceFactory;
import com.kkbnart.animalshougi.piecearray.PieceArray;
import com.kkbnart.animalshougi.player.SelectedPiece;

@SuppressLint("UseSparseArrays")
public class PieceList {
	private static final int ARRAY_SIZE = 2;
	
	Map<PIECE_ARRAY, ArrayList<PieceArray<AnimalPiece>>> pieceArrays = new HashMap<PIECE_ARRAY, ArrayList<PieceArray<AnimalPiece>>>();

	public PieceList() {
		super();
		pieceArrays.put(PIECE_ARRAY.PLAYER, new ArrayList<PieceArray<AnimalPiece>>());
		pieceArrays.put(PIECE_ARRAY.OFF_BOARD, new ArrayList<PieceArray<AnimalPiece>>());
	}
	
	public void registerPieces(final int type, final Resources resources) {
		clear();
		AnimalPieceFactory factory = new AnimalPieceFactory();
		for (int i = 0; i < ARRAY_SIZE; i++) {
			pieceArrays.get(PIECE_ARRAY.PLAYER).add(factory.createPieces(type, i, resources));
			pieceArrays.get(PIECE_ARRAY.OFF_BOARD).add(factory.createOffBoardPieces(i));
		}
	}
	
	public void clear() {
		pieceArrays.get(PIECE_ARRAY.PLAYER).clear();
		pieceArrays.get(PIECE_ARRAY.OFF_BOARD).clear();
	}
	
	public void drawHightlights(final BoardSize boardSize, final SelectedPiece selectedPiece, Canvas canvas, Paint paint, final int hightlightColor) {
		int column = boardSize.getColumn(), row = boardSize.getRow();
		paint.setColor(hightlightColor);
		
		// Highlight selected piece (light blue)
		getArr(selectedPiece).drawHighLights(boardSize, selectedPiece.selectedPieceIndex, canvas, paint);

		// Highlight next move@(light blue)
		ListIterator<Point> nextMoves = get(selectedPiece).getNextMoves(column, row).listIterator();
		while (nextMoves.hasNext()) {
			Point p = nextMoves.next();
			if ((selectedPiece.selectFrom == PIECE_ARRAY.PLAYER && !getArr(selectedPiece).exist(p.x, p.y))
					|| (selectedPiece.selectFrom == PIECE_ARRAY.OFF_BOARD 
					&& !pieceArrays.get(PIECE_ARRAY.PLAYER).get(0).exist(column, row) 
					&& !pieceArrays.get(PIECE_ARRAY.PLAYER).get(1).exist(column, row))) {
				canvas.drawRect(boardSize.getBoardRect().getPieceRect(p.x, p.y), paint);
			}
		}
	}
	
	public void drawPieceImages(final BoardSize boardSize, Canvas canvas) {
		for (ArrayList<PieceArray<AnimalPiece>> val : pieceArrays.values()) {
			for (int i = 0; i < ARRAY_SIZE; i++) {
				val.get(i).drawImages(boardSize, canvas);
			}
		}
	}
	
	public AnimalPiece get(final SelectedPiece selectedPiece) {
		return pieceArrays.get(selectedPiece.selectFrom).get(selectedPiece.owner).get(selectedPiece.selectedPieceIndex);
	}
	
	public PieceArray<AnimalPiece> getArr(final SelectedPiece selectedPiece) {
		return pieceArrays.get(selectedPiece.selectFrom).get(selectedPiece.owner);
	}
	
	public SelectedPiece getPieceAtPosition(final BoardSize boardSize, final int x, final int y) {
		for (ArrayList<PieceArray<AnimalPiece>> val : pieceArrays.values()) {
			for (int i = 0; i < ARRAY_SIZE; i++) {
				int pieceIndex = val.get(i).getPieceIndexAtPosition(boardSize, x, y);
				if (pieceIndex != -1) {
					return new SelectedPiece(PIECE_ARRAY.PLAYER, pieceIndex, -1, -1, i);
				}
			}
		}
		return new SelectedPiece();
	}
	
	public void taken(final SelectedPiece selectedPiece, final int turn, final PIECE_ARRAY to) {
		AnimalPiece piece = getArr(selectedPiece).remove(selectedPiece.selectedPieceIndex); // from
		pieceArrays.get(to).get(turn).add(piece); // to
	}
	
	public boolean isEmpty() {
		return pieceArrays.get(PIECE_ARRAY.PLAYER).isEmpty() && pieceArrays.get(PIECE_ARRAY.OFF_BOARD).isEmpty();
	}
}
