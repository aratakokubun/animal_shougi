package com.kkbnart.animalshougi.gui;

import java.util.ListIterator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.kkbnart.animalshougi.R;
import com.kkbnart.animalshougi.controller.FpsManager;
import com.kkbnart.animalshougi.controller.GameManager;
import com.kkbnart.animalshougi.model.Piece;
import com.kkbnart.animalshougi.model.Player;

public class GameSurfaceView implements SurfaceHolder.Callback, Runnable {
	public static final String TAG = "Game Surface View";
	public static final boolean D = true;
	
    private SurfaceHolder holder;

    // Game Manager to draw each item of objects
    GameManager gameManager;
    
    // Thread Loop
    private static final long SLEEP = 100l;
    private FpsManager fpsManager;
	private Thread thread = null;
	private boolean isRunning;
	
	private Context context;
    
    public GameSurfaceView(Context context, SurfaceView surfaceView, GameManager gameManager) {
    	this.context = context;
        holder = surfaceView.getHolder();
        holder.addCallback(this);
        this.gameManager = gameManager;
        fpsManager = new FpsManager();
        isRunning = false;
    }
    
    public void requestStartThread() {
    	if (thread == null) {
    		thread = new Thread(this);
            isRunning = true;
    	}
    }
    
    public void requestStopThread() {
    	isRunning = false;
    }
    
    /* -------------------------------------------------------------- */
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		if (thread != null) {
			if(!thread.isAlive()){
				thread.start();
			}
		}
		GameDrawSize.getInstance().updateSize(width, height);
		GameDrawSize.getInstance().calcBoardSize(gameManager.board);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
    	this.holder = holder;
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
    	thread = null;
    }

    @Override
    public void run() {
		while (isRunning && thread != null) {
			try{
				long elapsedTime = fpsManager.getElapsedTimeAndMeasure();
				long sleepTime = (SLEEP > elapsedTime) ? SLEEP-elapsedTime : 0;
				Thread.sleep(sleepTime);
			}	catch(InterruptedException e){
				if (D) Log.e("Error", "Sleep Execute Error!");
			}
				
			doDraw();
		}
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
    	gameManager.board.getImage(GameDrawSize.getInstance().getBoardRect()).draw(canvas);
    }
    
    public void drawHighLight(Canvas canvas, Paint paint) {
    	int turn = gameManager.getTurn();
    	int state = gameManager.players.getPlayerState(turn);
    	
    	switch (state) {
    	case Player.PUT:
    		Player player = gameManager.players.get(turn);
    		Piece piece = gameManager.pieces.get(player.getSelectedPieceIndex());
    		Rect pieceArea = GameDrawSize.getInstance().getPieceAreaRect();
    		int boardColumn = gameManager.board.getBoardColumn(), boardRow = gameManager.board.getBoardRow();
    		
    		if (piece != null) {
	    		// Highlight selected piece (light blue)
	    		paint.setColor(context.getResources().getColor(R.color.hightlight_selected));
				canvas.drawRect(piece.getPieceRect(pieceArea, boardColumn, boardRow), paint);
				
	    		// Highlight next moveÅ@(light blue)
	    		// paint.setColor(context.getResources().getColor(R.color.hightlight_move));
				ListIterator<Point> nextMoves = piece.getNextMoves(boardColumn, boardRow).listIterator();
				while (nextMoves.hasNext()) {
					Point p = nextMoves.next();
					// Highlight if no other my pieces exist in the point
					if (!gameManager.pieces.isPlayerPieceExist(p.x, p.y, turn)) {
						canvas.drawRect(Piece.getPieceRect(pieceArea, p.x, p.y, boardColumn, boardRow), paint);
					}
				}
	    		break;
    		}
    	}
    }
    
	public void drawPieces(Canvas canvas) {
    	ListIterator<Piece> it = gameManager.pieces.listIterator();
    	while (it.hasNext()) {
    		it.next().drawPieceImage(
    				GameDrawSize.getInstance().getPieceAreaRect(),
    				gameManager.board.getBoardColumn(),
    				gameManager.board.getBoardRow(),
    				canvas);
    	}
    }
    
    public void drawText(Canvas canvas) {
    	// TODO
    }
}