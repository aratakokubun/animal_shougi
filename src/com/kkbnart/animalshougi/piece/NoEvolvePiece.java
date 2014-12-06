package com.kkbnart.animalshougi.piece;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

public abstract class NoEvolvePiece extends AnimalPiece {
	protected int pieceImageId;
	protected Resources resources;

	public NoEvolvePiece(final int x, final int y, final int direction, final int pieceImageId, final Resources resources) {
		super(x, y, direction);
		this.pieceImageId = pieceImageId;
		this.resources = resources;
	}
	
	@Override
	public Drawable getImage(final Rect rect) {
		Bitmap img = BitmapFactory.decodeResource(resources, pieceImageId);
		
		// Rotate angle
		Matrix mat = new Matrix();
		mat.postRotate(90*direction);
		Drawable pieceImage = new BitmapDrawable(resources
				, Bitmap.createBitmap(img, 0, 0, img.getWidth(), img.getHeight(), mat, false));
		
		// Set bounds of draw rectangle
		pieceImage.setBounds(rect);
		return pieceImage;
	}
}
