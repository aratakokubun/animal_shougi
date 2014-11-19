package com.kkbnart.animalshougi.gui;

import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import com.kkbnart.animalshougi.AnimalShougiActivity;
import com.kkbnart.animalshougi.R;
import com.kkbnart.animalshougi.controller.GameManager;
import com.kkbnart.animalshougi.controller.PlayerList;

public class GameSetViewController extends ViewController<AnimalShougiActivity> {

	public GameSetViewController(AnimalShougiActivity activity, final int viewId) {
		super(activity);
		initView(viewId);
	}
	
	@Override
	public void initView(final int viewId) {
		super.initView(viewId);
		isViewPrepared = false;
		
		final RadioGroup gameType = (RadioGroup) findViewByIdInView(R.id.game_type);
		gameType.check(R.id.normal);
		
		final RadioGroup playType = (RadioGroup) findViewByIdInView(R.id.play_type);
		playType.check(R.id.vs_man);
		
		final Button start = (Button) findViewByIdInView(R.id.start);
		start.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startGame(gameType.getCheckedRadioButtonId(), playType.getCheckedRadioButtonId());
			}
		});
    }
	
	public void startGame(final int gameTypeId, final int playTypeId) {
		int gameType = GameManager.GAME_NORMAL;
		switch (gameTypeId) {
		case R.id.normal:
			gameType = GameManager.GAME_NORMAL;
			break;
		case R.id.gorogoro:
			gameType = GameManager.GAME_GOROGORO;
			break;
		case R.id.large:
			gameType = GameManager.GAME_LARGE;
			break;
		}
		
		// TODO
		// need to swap players first/second
		int[] players = {PlayerList.MAN, PlayerList.MAN};
		switch (playTypeId) {
		case R.id.vs_com:
			players[0] = PlayerList.MAN;
			players[1] = PlayerList.COM;
			break;
		case R.id.com_vs_com:
			players[0] = PlayerList.COM;
			players[1] = PlayerList.COM;
			break;
		case R.id.vs_man:
			players[0] = PlayerList.MAN;
			players[1] = PlayerList.MAN;
			break;
		case R.id.vs_man_bt:
			players[0] = PlayerList.MAN;
			players[1] = PlayerList.BT_MAN;
			break;
		}
		
		((GameViewController)parentActivity.getViewController(AnimalShougiActivity.GAME)).registerGameSettings(gameType, players, getResources());
		parentActivity.switchMode(AnimalShougiActivity.GAME);
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
