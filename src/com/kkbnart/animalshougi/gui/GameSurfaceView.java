package com.kkbnart.animalshougi.gui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.util.Log;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;

import com.kkbnart.animalshougi.R;
import com.kkbnart.animalshougi.controller.GameManager;
import com.kkbnart.animalshougi.player.AnimalShougiPlayer;
import com.kkbnart.animalshougi.player.Player;
import com.kkbnart.animalshougi.player.SelectedPiece;

public class GameSurfaceView implements SurfaceHolder.Callback {
	public static final String TAG = "Game Surface View";
	public static final boolean D = true;
	
    private SurfaceHolder holder;

    // Game Manager to draw each item of objects
    GameManager gameManager;
	
	private Context context;
    
    public GameSurfaceView(Context context, SurfaceView surfaceView, GameManager gameManager) {
    	this.context = context;
        holder = surfaceView.getHolder();
        holder.addCallback(this);
        this.gameManager = gameManager;
    }
    
    /* -------------------------------------------------------------- */
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    	gameManager.gameDrawSize.updateSize(width, height);
    	WindowManager wm = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
    	Display disp = wm.getDefaultDisplay();
		int width = disp.getWidth();
    	int height = disp.getHeight();
    	doDraw();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
    	this.holder = holder;
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
    }
    
    /* -------------------------------------------------------------- */
    public void doDraw() {
		try {
			Canvas canvas = holder.lockCanvas();
			Paint paint = new Paint();

			canvas.drawColor(Color.WHITE, PorterDuff.Mode.CLEAR);
			canvas.drawColor(Color.WHITE);
			
			drawBackground(canvas);
			drawBoard(canvas);
			drawHighLight(canvas, paint);
			drawPieces(canvas);
			drawText(canvas);

			holder.unlockCanvasAndPost(canvas);
		} catch (NullPointerException e) {
			if (D) Log.e(TAG, e.toString());
		}
    }
    
    public void drawBackground(Canvas canvas) {
    	// TODO
    }
    
    public void drawBoard(Canvas canvas) {
    	gameManager.board.getImage(gameManager.gameDrawSize.getBoardImageRect()).draw(canvas);
    }
    
    public void drawHighLight(Canvas canvas, Paint paint) {
    	int turn = gameManager.getTurn();
    	int state = gameManager.players.getPlayerState(turn);
    	
    	switch (state) {
    	case Player.PUT:
    		AnimalShougiPlayer player = gameManager.players.get(turn);
    		SelectedPiece selectedPiece = player.getSelectedPiece();
    		
    		gameManager.pieces.drawHightlights(gameManager.gameDrawSize, selectedPiece, canvas, paint,
    				context.getResources().getColor(R.color.hightlight_selected));
    	}
    }
    
	public void drawPieces(Canvas canvas) {
		gameManager.pieces.drawPieceImages(gameManager.gameDrawSize, canvas);
    }
    
    public void drawText(Canvas canvas) {
    	// TODO
    }
}