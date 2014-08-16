package com.cvte.game.game.heart;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.cvte.game.Data;

public class HeartManager extends Group {
	private static HeartManager instance;
	
	private Heart[] mHearts;
	
	public static HeartManager getInstance() {
		if (instance == null) {
			instance = new HeartManager();
		}
		return instance;
	}
	
	public static HeartManager getInstanceValue() {
		return instance;
	}
	
	private HeartManager() {
		
	}
	
	public void dispose() {
		disposeHearts();
		
		instance = null;
	}

	@Override
	public void act(float delta) {
		super.act(delta);
	}
	
	public void reset() {
		disposeHearts();
	}
	
	private void disposeHearts() {
		if (mHearts != null) {
			for (int i = mHearts.length - 1; i >= 0; --i) {
				if (mHearts[i] != null) {
					mHearts[i].dispose();
					mHearts[i] = null;
				}
			}
			mHearts = null;
		}
	}
	
	public void init() {
		mHearts = new Heart[Data.MAX_HEART_NUM];
		for (int i = 0; i < Data.MAX_HEART_NUM; ++i) {
			mHearts[i] = new Heart();
			mHearts[i].init();
			mHearts[i].setPosition(5 + (Heart.HEART_WIDTH + 5) * i, Data.SCREEN_HEIGHT - Heart.HEART_HEIGHT - 5);
			
			addActor(mHearts[i]);
		}
	}
	
	public void update(int heartNum) {
		for (int i = 0; i < Data.MAX_HEART_NUM; ++i) {
			mHearts[i].setVisible(i < heartNum);
		}
	}
	
}
