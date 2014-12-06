package com.kkbnart.animalshougi.gui;

import android.content.res.Resources;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.kkbnart.animalshougi.AnimalShougiActivity;
import com.kkbnart.animalshougi.R;
import com.kkbnart.animalshougi.controller.GameManager;
import com.kkbnart.animalshougi.player.Player;
import com.kkbnart.utils.LaunchDialogHandler;

public class GameViewController extends ViewController<AnimalShougiActivity> {
	private TouchDetectableSurfaceView surfaceView;
	private GameSurfaceView gameSurfaceView;
	
	private GameManager gameManager;

	public GameViewController(AnimalShougiActivity activity, final int viewId) {
		super(activity);
		initView(viewId);
	}
	
	@Override
	public void initView(final int viewId) {
		super.initView(viewId);
		isViewPrepared = false;
		
		gameManager = new GameManager(this);
		surfaceView = (TouchDetectableSurfaceView)findViewByIdInView(R.id.game_surface_view);
		surfaceView.setGameManager(gameManager);
		gameSurfaceView = new GameSurfaceView(parentActivity, surfaceView, gameManager);
	}

	@Override
	public void startView() {
		gameManager.startGame();
	}

	@Override
	public void endView() {
	}

	@Override
	public boolean isSwitchModeProhibited() {
		return false;
	}
	
	public void registerGameSettings(int gameType, final int[] players, final Resources resources) {
		gameManager.registerGameSettings(gameType, players, resources);
	}
	
	public void drawSurfaceView() {
		gameSurfaceView.doDraw();
	}
	
	public void showGameEndDialog(final int turn, final Player winner) {
		final LaunchDialogHandler ldh = LaunchDialogHandler.getInstance();
		ldh.showDialog(R.string.game_end_dialog, R.layout.game_end_dialog);
		
		TextView result = (TextView) ldh.findViewById(R.id.text_result);
		result.setText(winner.toString() + String.valueOf(turn+1) + parentActivity.getString(R.string.game_end_result));
		
		Button onceMore = (Button) ldh.findViewById(R.id.button_once_more);
		onceMore.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				gameManager.resetGame();
				gameManager.resetPieces(parentActivity.getResources());
				gameManager.startGame();
				ldh.dismissDialog();
			}
		});
		
		Button backMenu = (Button) ldh.findViewById(R.id.button_back_menu);
		backMenu.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				parentActivity.switchMode(AnimalShougiActivity.MENU);
				ldh.dismissDialog();
			}
		});
	}
}