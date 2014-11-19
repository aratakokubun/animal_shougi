package com.kkbnart.animalshougi.gui;

import android.app.Activity;
import android.content.res.Resources;
import android.view.View;
import android.widget.Toast;

public abstract class ViewController <Parent extends Activity> {
	protected Parent parentActivity;
	protected int viewId;
	protected View view;
	protected boolean isViewPrepared;

	public ViewController(Parent activity) {
		this.parentActivity = activity;
	}
	
	public void initView(final int viewId) {
		this.viewId = viewId;
		this.view = parentActivity.getLayoutInflater().inflate(viewId, null);
	}
	
	public View getView() {
		return view;
	}

	public View findViewById(final int id) {
		return parentActivity.findViewById(id);
	}
	
	public View findViewByIdInView(final int id) { 
		return view.findViewById(id);
	}

	public Resources getResources() {
		return parentActivity.getResources();
	}
	
	public void showToast(final String Message){
		Toast.makeText(
				parentActivity,
				Message,
				Toast.LENGTH_SHORT
		).show();
	}
	
	public boolean prepareView() {
		return true;
	}

	public abstract void startView();
	
	public abstract void endView();
	
	public abstract boolean isSwitchModeProhibited();
}