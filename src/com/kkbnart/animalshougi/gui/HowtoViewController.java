package com.kkbnart.animalshougi.gui;

import java.util.Iterator;
import java.util.Vector;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import com.kkbnart.animalshougi.AnimalShougiActivity;

public class HowtoViewController extends ViewController<AnimalShougiActivity> {
	
	private Vector<Drawable> images;
	private int showImageId;
	
	public HowtoViewController(AnimalShougiActivity activity, final int viewId) {
		super(activity);
		// TODO set images
		initView(viewId);
	}
	
	@Override
	public void initView(final int viewId) {
		super.initView(viewId);
		isViewPrepared = false;
		
		// TODO
		// Explaining image proceeding with button flip
		// Layout buttons on the imageView
		
		// Back button to menu in upper left
	}
	
	@Override
	public void startView() {}

	@Override
	public void endView() {}

	@Override
	public boolean isSwitchModeProhibited() {
		return false;
	}
	
	private void setImages(Resources resources, Vector<Integer> imageIds) {
		Iterator<Integer> it = imageIds.iterator();
		while (it.hasNext()) {
			images.add(resources.getDrawable(it.next()));
		}
		showImageId = 0;
	}
	
	private void switchImage(int index) {
		if (index < images.size()) {
			showImageId = index;
		}
	}
}
