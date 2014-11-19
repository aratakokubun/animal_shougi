package com.kkbnart.animalshougi.model;

import com.kkbnart.animalshougi.gui.TouchEnableController;

public class Man extends Player {

	public Man() {
		super();
	}

	@Override
	public void onWait() {
		// TODO Auto-generated method stub
	}

	@Override
	public void onSelect() {
		TouchEnableController.getInstance().setIsViewTouchEnabled(true);
	}

	@Override
	public void onPut() {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void onFinish() {
		TouchEnableController.getInstance().setIsViewTouchEnabled(false);
		super.onFinish();
	}
}
