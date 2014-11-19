package com.kkbnart.animalshougi.gui;

import com.kkbnart.animalshougi.AnimalShougiActivity;

public class HowtoViewController extends ViewController<AnimalShougiActivity> {

	public HowtoViewController(AnimalShougiActivity activity, final int viewId) {
		super(activity);
		initView(viewId);
	}
	
	@Override
	public void initView(final int viewId) {
		super.initView(viewId);
		isViewPrepared = false;
	}
	
	@Override
	public void startView() {}

	@Override
	public void endView() {}

	@Override
	public boolean isSwitchModeProhibited() {
		return false;
	}
}
