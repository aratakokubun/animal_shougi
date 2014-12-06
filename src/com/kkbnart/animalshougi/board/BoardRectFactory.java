package com.kkbnart.animalshougi.board;

import java.util.ArrayList;

import android.graphics.Rect;
import android.graphics.RectF;
import android.text.Editable.Factory;
import android.util.Log;

public class BoardRectFactory extends Factory {
	
	public BoardSize getBoardSize(final int players, final int column, final int row, final int offBoardColumn
			, final int viewWidth, final int viewHeight, final float rateBoardWidth, final float rateBoardHeight
			, final float ratePieceWidth, final float ratePieceHeight, final float rateOffBoardWidth, final float rateOffBoardHeight) {
		// Reset board size instance
		BoardSize boardSize = new BoardSize();
		
		float imageSizeRate = ((float)(viewHeight*rateBoardHeight)/(float)(viewWidth*rateBoardWidth));
		float boardSizeRate = ((float)row/(float)column);

		// Rectangle to put board
		float boardWidth, boardHeight;
		if (imageSizeRate > boardSizeRate) {
			boardWidth = (int)(viewWidth*rateBoardHeight);
			boardHeight = (int)(boardWidth*boardSizeRate);
		} else {
			boardHeight = (int)(viewHeight*rateBoardWidth);
			boardWidth = (int)(boardHeight/boardSizeRate);
		}
		Rect boardImageRect = new Rect(
				(int)((viewWidth -boardWidth )/2.f),
				(int)((viewHeight-boardHeight)/2.f),
				(int)((viewWidth -boardWidth )/2.f) + (int)boardWidth,
				(int)((viewHeight-boardHeight)/2.f) + (int)boardHeight);
		
		// Rectangle to put pieces
		float pieceAreaWidth = boardWidth*ratePieceWidth;
		float pieceAreaHeight = boardHeight*ratePieceHeight;
		BoardRectBase boardRect = new BoardRect(
				new RectF(
					(int)((viewWidth -pieceAreaWidth )/2.f),
					(int)((viewHeight-pieceAreaHeight)/2.f),
					(int)((viewWidth -pieceAreaWidth )/2.f) + (int)pieceAreaWidth,
					(int)((viewHeight-pieceAreaHeight)/2.f) + (int)pieceAreaHeight),
					column, row);
		
		// Rectangle to put taken pieces
		float takenAreaWidth = boardWidth*rateOffBoardWidth;
		float takenAreaHeight = (viewHeight-boardHeight)*rateOffBoardHeight;
		ArrayList<BoardRectBase> offBoardRects = new ArrayList<BoardRectBase>();
		// First player front(bottom) of view
		offBoardRects.add(
				new OffBoardRect(
					new RectF(
						(int)((viewWidth -takenAreaWidth )/2.f),
						(int)((viewHeight-boardHeight-takenAreaHeight)/4.f + boardRect.bottom),
						(int)((viewWidth -takenAreaWidth )/2.f) + (int)takenAreaWidth,
						(int)((viewHeight-boardHeight-takenAreaHeight)/4.f + boardRect.bottom + takenAreaHeight/2.f)),
						offBoardColumn));
		// Second player back(top) of view
		offBoardRects.add(
				new OffBoardRect(
					new RectF(
						(int)((viewWidth -takenAreaWidth )/2.f),
						(int)((viewHeight-boardHeight-takenAreaHeight)/4.f),
						(int)((viewWidth -takenAreaWidth )/2.f) + (int)takenAreaWidth,
						(int)((viewHeight-boardHeight-takenAreaHeight)/4.f + takenAreaHeight/2.f)),
						offBoardColumn));
		
		// Set to boardSize
		boardSize.setSizes(boardImageRect, boardRect, offBoardRects);
		Log.d("hoge", boardRect.toString() + ", " + offBoardRects.toString());
		
		return boardSize;
	}
}
