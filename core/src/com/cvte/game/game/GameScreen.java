package com.cvte.game.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.cvte.game.Data;
import com.cvte.game.game.cell.CellManager;

public class GameScreen implements Screen {
	private static GameScreen instance;
	
	private Stage mStage;
	private BG mBG;
	private CellManager mCellManager;
	
	//目标位置
	private int mTargetIndexA;
	private int mTargetIndexB;

	private int mCurLevel = 0;
	
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
		mStage = new Stage(new ScalingViewport(Scaling.stretch, Data.SCREEN_WIDTH, Data.SCREEN_HEIGHT));
		
		mBG = new BG();
		
		mCellManager = CellManager.getInstance();
		mCellManager.init();
		
		mStage.addActor(mBG);
		mStage.addActor(mCellManager);
		
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
	
	public int getCurLevel() {
		return mCurLevel;
	}
	
	public void touchCell(int posA, int posB) {
		if ((posA == mTargetIndexA) && (posB == mTargetIndexB)) {
			++mCurLevel;
			
			mCellManager.reset();
			mCellManager.init();
		}
	}

}
