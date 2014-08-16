package com.cvte.game.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.cvte.game.Data;
import com.cvte.game.Lovelace;
import com.cvte.game.game.cell.CellManager;
import com.cvte.game.game.heart.HeartManager;

public class GameScreen implements Screen {
	private static GameScreen instance;
	
	private Stage mStage;
	private BG mBG;
	private CellManager mCellManager;
	private HeartManager mHeartManager;
	private Score mScore;
	
	//目标位置
	private int mTargetIndexA;
	private int mTargetIndexB;


	
	public static GameScreen getInstance() {
		if (instance == null) {
			instance = new GameScreen();
		}
		return instance;
	}
	
	public static GameScreen getInstanceValue() {
		return instance;
	}
	
	private GameScreen() {
		
	}
	
	@Override
	public void dispose() {
		if (mStage != null) {
			mStage.dispose();
			mStage = null;
		}
		if (mBG != null) {
			mBG.dispose();
			mBG = null;
		}
		if (mCellManager != null) {
			mCellManager.dispose();
			mCellManager = null;
		}
		if (mHeartManager != null) {
			mHeartManager.dispose();
			mHeartManager = null;
		}
		if (mScore != null) {
			mScore.dispose();
			mScore = null;
		}
		
		instance = null;
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		mStage.act(delta);
		mStage.draw();
	}

	@Override
	public void resize(int width, int height) {
		mStage.getViewport().update(width, height, true);
	}

	@Override
	public void show() {
		Data.mCurLevel = 0;
		Data.mCurScore = 0;
		Data.mCurHeart = Data.MAX_HEART_NUM;
		
		mStage = new Stage(new ScalingViewport(Scaling.stretch, Data.SCREEN_WIDTH, Data.SCREEN_HEIGHT));
		
		mBG = new BG();
		
		mCellManager = CellManager.getInstance();
		mCellManager.init();
		
		mHeartManager = HeartManager.getInstance();
		mHeartManager.init();
		
		mScore = new Score();
		mScore.displayScore(Data.mCurScore);
		
		mStage.addActor(mBG);
		mStage.addActor(mCellManager);
		mStage.addActor(mHeartManager);
		mStage.addActor(mScore.getLabelScore());
		
		Gdx.input.setInputProcessor(mStage);
	}

	@Override
	public void hide() {
		
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}
	
	/**
	 * 设置目标格子的位置索引
	 * @param i
	 * @param j
	 */
	public void setTargetIndex(int i, int j) {
		mTargetIndexA = i;
		mTargetIndexB = j;
		
		System.out.println(i + " " + j);
	}
	
	public void touchCell(int posA, int posB) {
		if ((posA == mTargetIndexA) && (posB == mTargetIndexB)) {
			++Data.mCurLevel;
			
			++Data.mCurScore;
			mScore.displayScore(Data.mCurScore);
			
			mCellManager.reset();
			mCellManager.init();
		}
		else {
			--Data.mCurHeart;
			mHeartManager.update(Data.mCurHeart);
			
			if (Data.mCurHeart <= 0) {
				Lovelace.getInstance().jumpScreen(Lovelace.SCREEN_OVER);
			}
		}
	}
	
	private void restart() {
		Data.mCurLevel = 0;
		Data.mCurScore = 0;
		Data.mCurHeart = Data.MAX_HEART_NUM;
		
		mCellManager.reset();
		mCellManager.init();
		
		mHeartManager.reset();
		mHeartManager.init();
		
		mScore.displayScore(Data.mCurScore);
	}

}
