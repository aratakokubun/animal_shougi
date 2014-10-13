package com.kkbnart.animalshougi.model;

import com.kkbnart.animalshougi.controller.GameManager;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

public abstract class EvolvePiece extends Piece {
	public static final int CHILD = 0;
	public static final int EVOLVED = 1;
	
	protected int evolveState;
	protected int[] pieceImageIds = new int[2];
	protected Resources resources;

	public EvolvePiece(final int state, final int owner, final int x, final int y, final int[] pieceImageIds, final Resources resources) {
		super(state, owner, x, y);
		evolveState = CHILD;
		System.arraycopy(pieceImageIds, 0, this.pieceImageIds, 0, this.pieceImageIds.length);
		this.resources = resources;
	}
	
	public int getEvolveState() {
		return evolveState;
	}

	@Override
	public void taken(int takenBy) {
		super.taken(takenBy);
		evolveState = CHILD;
	}
	
	@Override
	public int getImageId() {
		return pieceImageIds[evolveState];
	}
	
	@Override
	public Drawable getImage(final Rect rect, final int column, final int row) {
		if (owner == GameManager.FIRST) {
			// Set bounds of draw rectangle
			Drawable pieceImage = resources.getDrawable(pieceImageIds[evolveState]);
			pieceImage.setBounds(getPieceRect(rect, column, row));
			return pieceImage;
		} else {
			Bitmap img = BitmapFactory.decodeResource(resources, pieceImageIds[evolveState]);
			// Flip upside down
			Matrix mat = new Matrix();
			mat.preScale(-1, -1);
			Drawable pieceImage = new BitmapDrawable(resources
					, Bitmap.createBitmap(img, 0, 0, img.getWidth(), img.getHeight(), mat, false));
			// Set bounds of draw rectangle
			pieceImage.setBounds(getPieceRect(rect, column, row));
			return pieceImage;
		}
	}
}
