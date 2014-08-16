package com.cvte.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.cvte.game.game.GameScreen;
import com.cvte.game.over.OverScreen;

public class Lovelace extends Game {
	private static Lovelace instance;
	
	public final static int SCREEN_GAME = 0;
	public final static int SCREEN_OVER = 1;
	
	private Screen mCurScreen;
	
	public static Lovelace getInstance() {
		if (instance == null) {
			instance = new Lovelace();
		}
		return instance;
	}
	
	public static Lovelace getInstanceValue() {
		return instance;
	}
	
	@Override
	public void create () {
		instance = this;
		
		jumpScreen(SCREEN_GAME);
		
		SoundManager.getInstance().playBGMusic();
	}

	@Override
	public void dispose() {
		super.dispose();
		
		SoundManager.getInstance().dispose();
	}
	
	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}
	
	public void jumpScreen(int screenID) {
		if (mCurScreen != null) {
			mCurScreen.dispose();
			mCurScreen = null;
		}
		
		switch (screenID) {
		case SCREEN_GAME:
			mCurScreen =  GameScreen.getInstance();
			break;
		case SCREEN_OVER:
			mCurScreen =  OverScreen.getInstance();
			break;
		}
		setScreen(mCurScreen);
	}
	
}
