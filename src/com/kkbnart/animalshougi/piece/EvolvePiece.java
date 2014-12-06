package com.kkbnart.animalshougi.piece;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

public abstract class EvolvePiece extends AnimalPiece {
	public static final int CHILD = 0;
	public static final int EVOLVED = 1;
	
	protected int evolveState;
	protected int[] pieceImageIds = new int[2];
	protected Resources resources;

	public EvolvePiece(final int x, final int y, final int direction, final int[] pieceImageIds, final Resources resources) {
		super(x, y, direction);
		evolveState = CHILD;
		System.arraycopy(pieceImageIds, 0, this.pieceImageIds, 0, this.pieceImageIds.length);
		this.resources = resources;
	}
	
	public int getEvolveState() {
		return evolveState;
	}
	
	@Override
	protected void moveTo(final int x, final int y, final int boardColumn, final int boardRow) {
		// If reach to the end, evolve state
		if ((direction == DIREC_BOTTOM && y == 0) || (direction == DIREC_TOP && y == boardRow-1)) {
			evolveState = EVOLVED;
		}
		super.moveTo(x, y, boardColumn, boardRow);
	};

	public void taken() {
		evolveState = CHILD;
	}
	
	@Override
	public Drawable getImage(final Rect rect) {
		Bitmap img = BitmapFactory.decodeResource(resources, pieceImageIds[evolveState]);
		
		// Rotate angle
		Matrix mat = new Matrix();
		mat.postRotate(90*direction);
		Drawable pieceImage = new BitmapDrawable(resources, 
				Bitmap.createBitmap(img, 0, 0, img.getWidth(), img.getHeight(), mat, false));
		
		// Set bounds of draw rectangle
		pieceImage.setBounds(rect);
		return pieceImage;
	}
}