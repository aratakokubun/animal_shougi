/**
 * Native.java
 * 
 *  Native methodÇÃåƒÇ—èoÇµÉNÉâÉX
 */

package com.kkbnart.animalshougi.gui;


public class TouchEnableController {
	// Singleton instance
    private static TouchEnableController instance = null;
    
    private boolean isViewTouchEnabled;
	
	// Get singleton instance
	public static synchronized TouchEnableController getInstance(){
		if(instance == null){
			instance = new TouchEnableController();
		}
		return instance;
	}
	
	public TouchEnableController() {
		isViewTouchEnabled = false;
	}
	
	public void setIsViewTouchEnabled(boolean isViewTouchEnabled) {
		this.isViewTouchEnabled = isViewTouchEnabled;
	}
	
	public boolean getIsViewTouchEnabled() {
		return isViewTouchEnabled;
	}
}