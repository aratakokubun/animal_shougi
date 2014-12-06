package com.kkbnart.animalshougi.gui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceView;
import com.kkbnart.animalshougi.controller.GameManager;

public class TouchDetectableSurfaceView extends SurfaceView {

	private GameManager gameManager = null;
	
	public TouchDetectableSurfaceView(Context context) {
		super(context);
	}

	public TouchDetectableSurfaceView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public TouchDetectableSurfaceView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
	
	public void setGameManager(GameManager gameManager) {
		this.gameManager = gameManager;
	}

	/* -------------------------------------------------------------------------------------- */
	@SuppressLint("ClickableViewAccessibility")
	@Override
    public boolean onTouchEvent(MotionEvent event) {
		if (gameManager != null && TouchEnableController.getInstance().getIsViewTouchEnabled()) {
			return gameManager.onTouchEvent(event);
		} else {
			return false;
		}
    }
}
