package com.kkbnart.utils;

import android.app.Activity;
import android.app.AlertDialog;

public class LaunchAlertDialogHandler {
	private static LaunchAlertDialogHandler instance = null;
	
	private Activity parent;
	private AlertDialog dialog;
	
	public LaunchAlertDialogHandler(){
		parent = null;
		dialog = null;
	}

	// Get singleton instance
	public static synchronized LaunchAlertDialogHandler getInstance( ){
		if(instance == null){
			instance = new LaunchAlertDialogHandler();
		}
		return instance;
	}
	
	public void setActivity(Activity activity) {
		parent = activity;
	}
	
	// Show dialog
	public AlertDialog.Builder getAlertDialogBuilder() {
		return new AlertDialog.Builder(parent);
	}
	
	public void showDialog(AlertDialog.Builder builder) {
		if(checkDialogState()){
			dialog = builder.create();
			dialog.show();
		}
	}
	
	// Kill dialog
	public void dismissDialog(){
		dialog.dismiss();
	}
	
	// Check if dialog can be launched
	private boolean checkDialogState() {
		if(parent != null) {
			if(dialog == null || !dialog.isShowing()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
}
