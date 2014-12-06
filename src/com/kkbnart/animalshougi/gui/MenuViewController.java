package com.kkbnart.animalshougi.gui;

import android.view.View;
import android.widget.Button;

import com.kkbnart.animalshougi.AnimalShougiActivity;
import com.kkbnart.animalshougi.R;

public class MenuViewController extends ViewController<AnimalShougiActivity> {

	public MenuViewController(AnimalShougiActivity activity, final int viewId) {
		super(activity);
		initView(viewId);
	}

	@Override
	public void initView(final int viewId) {
		super.initView(viewId);
		isViewPrepared = false;
		
		// TODO
		// Change layout of menu
		
		final Button buttonStart = (Button) findViewByIdInView(R.id.button_start);
		buttonStart.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				parentActivity.switchMode(AnimalShougiActivity.GAME_SET);
			}
		});
		
		final Button buttonHowto = (Button) findViewByIdInView(R.id.button_howto);
		buttonHowto.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				parentActivity.switchMode(AnimalShougiActivity.HOWTO);
			}
		});
	}

	@Override
	public boolean prepareView() {
		if(!isViewPrepared) {
			// TODO
			isViewPrepared = true;
		}
		return isViewPrepared;
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
