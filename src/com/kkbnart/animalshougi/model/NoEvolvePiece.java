package com.kkbnart.animalshougi.model;

import com.kkbnart.animalshougi.controller.GameManager;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

public abstract class NoEvolvePiece extends Piece {
	protected int pieceImageId;
	protected Resources resources;

	public NoEvolvePiece(final int state, final int owner, final int x, final int y, final int pieceImageId, final Resources resources) {
		super(state, owner, x, y);
		this.pieceImageId = pieceImageId;
		this.resources = resources;
	}

	@Override
	public int getImageId() {
		return pieceImageId;
	}

	@Override
	public Drawable getImage(final Rect rect, final int column, final int row) {
		if (owner == GameManager.FIRST) {
			// Set bounds of draw rectangle
			Drawable pieceImage = resources.getDrawable(pieceImageId);
			pieceImage.setBounds(getPieceRect(rect, column, row));
			return pieceImage;
		} else {
			Bitmap img = BitmapFactory.decodeResource(resources, pieceImageId);
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
