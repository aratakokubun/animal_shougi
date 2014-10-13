/**
 * OverlayViewActivity.java
 * 
 *  一番上に重畳するViewの制御
 *  新しい機能(xmlを用いる)を追加する場合はここに処理を追加
 */

package com.kkbnart.animalshougi;

import java.util.HashMap;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;

import com.kkbnart.animalshougi.gui.GameSetViewController;
import com.kkbnart.animalshougi.gui.GameViewController;
import com.kkbnart.animalshougi.gui.HowtoViewController;
import com.kkbnart.animalshougi.gui.MenuViewController;
import com.kkbnart.animalshougi.gui.SettingViewController;
import com.kkbnart.animalshougi.gui.ViewController;

public class AnimalShougiActivity extends Activity {
	// Debugging
	private static final boolean D = true;
	private static final String TAG = "Animal Shougi Activity";
	
	// Application mode
	public static final int MENU	 = 0;
	public static final int HOWTO	 = 1;
	public static final int GAME_SET = 2;
	public static final int GAME	 = 3;
	public static final int SETTING	 = 4;
	private int mode;
	
	// Content of views
	private LinearLayout content;

	HashMap<Integer, ViewController<AnimalShougiActivity>> views;
	
	/* -------------------------------------------------------------------------------- */
	@Override
	public void onCreate(Bundle savedInstanceState){
		
		// set bottom menu bar transparent
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		setContentView(R.layout.main);
		super.onCreate(savedInstanceState);
		
		mode = MENU;
		content = (LinearLayout) findViewById(R.id.content);
		setMultipleViewController();
	}

	@SuppressLint("UseSparseArrays")
	private void setMultipleViewController() {
		views = new HashMap<Integer, ViewController<AnimalShougiActivity>>();

		views.put(MENU,		new MenuViewController(this, R.layout.menu));
		views.put(HOWTO, 	new HowtoViewController(this, R.layout.howto));
		views.put(GAME_SET, new GameSetViewController(this, R.layout.game_set));
		views.put(GAME, 	new GameViewController(this, R.layout.game));
		views.put(SETTING, 	new SettingViewController(this, R.layout.setting));
		
		switchMode(MENU);
	}
	
	public ViewController<AnimalShougiActivity> getViewController(int viewId) {
		return views.get(viewId);
	}
	
	/* -------------------------------------------------------------------------------- */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	// Dismiss other option menus declared at super classes
    	// TODO
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
    	// menu is once cleared and added based on mode
    	// TODO
    	menu.clear();
    	
    	return true;
    }
    
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
		CharSequence title = item.getTitle();
        return optionDoFunction(title.toString());
	}
	
	public boolean optionDoFunction(String title) {
		try {
			switch (title) {
	    	// TODO
			default:
				return false;
			}
    	} catch (ClassCastException e) {
    		if (D) Log.e(TAG, "Can not call method because of "+e.toString());
    		return false;
    	}
	}
	
	/* -------------------------------------------------------------------------------- */
	public void switchMode(int id) {
		if (!views.get(mode).isSwitchModeProhibited() && views.get(id).prepareView()) {
			views.get(mode).endView();
			content.removeAllViews();
			content.addView(views.get(id).getView());
			content.setVisibility(View.VISIBLE);
			
			views.get(id).startView();
			mode = id;
		}
	}
}
