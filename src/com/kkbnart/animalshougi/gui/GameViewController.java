package com.kkbnart.animalshougi.gui;

import android.content.res.Resources;

import com.kkbnart.animalshougi.AnimalShougiActivity;
import com.kkbnart.animalshougi.R;
import com.kkbnart.animalshougi.controller.GameManager;

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
		
		gameManager = new GameManager();
		surfaceView = (TouchDetectableSurfaceView)findViewByIdInView(R.id.game_surface_view);
		surfaceView.setGameManager(gameManager);
		gameSurfaceView = new GameSurfaceView(parentActivity, surfaceView, gameManager);
	}

	@Override
	public void startView() {
		gameSurfaceView.requestStartThread();
		gameManager.startGame();
	}

	@Override
	public void endView() {
		gameSurfaceView.requestStopThread();
	}

	@Override
	public boolean isSwitchModeProhibited() {
		return false;
	}
	
	public void registerGameSettings(int gameType, final int[] players, final Resources resource) {
		gameManager.setGameType(gameType);
		gameManager.registerPlayers(players);
		gameManager.registerPieces(resource);
		gameManager.registerBoard(resource);
	}
}