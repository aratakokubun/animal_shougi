package com.kkbnart.animalshougi.piecearray;

import android.content.res.Resources;
import android.text.Editable.Factory;

import com.kkbnart.animalshougi.R;
import com.kkbnart.animalshougi.controller.GameManager;
import com.kkbnart.animalshougi.piece.AnimalPiece;
import com.kkbnart.animalshougi.piece.Chicken;
import com.kkbnart.animalshougi.piece.Elephant;
import com.kkbnart.animalshougi.piece.Giraffe;
import com.kkbnart.animalshougi.piece.Lion;
import com.kkbnart.animalshougi.piece.RotatablePiece;

public class AnimalPieceFactory extends Factory {
	
	public PlayerPieceArray<AnimalPiece> createPieces(final int type, final int owner, final Resources resources) {
		switch(type) {
		case GameManager.GAME_NORMAL:
			return createNormalPieces(owner, resources);
		case GameManager.GAME_GOROGORO:
			return createGoroGoroPieces(owner, resources);
		case GameManager.GAME_LARGE:
			return createLargePieces(owner, resources);
		default:
			return new PlayerPieceArray<AnimalPiece>(owner);
		}
	}
	
	public PlayerPieceArray<AnimalPiece> createNormalPieces(final int owner, final Resources resources) {
		PlayerPieceArray<AnimalPiece> normalList = new PlayerPieceArray<AnimalPiece>(owner);
		// 2 Chicken [1,1], [1,2]
		// 2 elephant [2,0], [0,3]
		// 2 giraffe [0,0], [2,3]
		// 2 lion [1,0], [1,3]
		int[] chickenImageIds = {R.drawable.chick, R.drawable.chicken};
		switch (owner) {
		case GameManager.FIRST:
			normalList.add(new Chicken(1, 2, RotatablePiece.DIREC_TOP, chickenImageIds, resources));
			normalList.add(new Elephant(0, 3, RotatablePiece.DIREC_TOP, R.drawable.elephant, resources));
			normalList.add(new Giraffe(2, 3, RotatablePiece.DIREC_TOP, R.drawable.giraffe, resources));
			normalList.add(new Lion(1, 3, RotatablePiece.DIREC_TOP, R.drawable.lion, resources));
			break;
		case GameManager.SECOND:
			normalList.add(new Chicken(1, 1, RotatablePiece.DIREC_BOTTOM, chickenImageIds, resources));
			normalList.add(new Elephant(2, 0, RotatablePiece.DIREC_BOTTOM, R.drawable.elephant, resources));
			normalList.add(new Giraffe(0, 0, RotatablePiece.DIREC_BOTTOM, R.drawable.giraffe, resources));
			normalList.add(new Lion(1, 0, RotatablePiece.DIREC_BOTTOM, R.drawable.lion, resources));
			break;
		}
		return normalList;
	}
	
	public PlayerPieceArray<AnimalPiece> createGoroGoroPieces(final int owner, final Resources resources) {
		PlayerPieceArray<AnimalPiece> gorogorolList = new PlayerPieceArray<AnimalPiece>(owner);
		// TODO
		return gorogorolList;
	}
	
	public PlayerPieceArray<AnimalPiece> createLargePieces(final int owner, final Resources resources) {
		PlayerPieceArray<AnimalPiece> largelList = new PlayerPieceArray<AnimalPiece>(owner);
		// TODO
		return largelList;
	}
	
	public OffBoardPieceArray<AnimalPiece> createOffBoardPieces(final int owner) {
		return new OffBoardPieceArray<AnimalPiece>(owner);
	}

}
