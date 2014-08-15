package com.cvte.game;

import com.badlogic.gdx.Game;
import com.cvte.game.game.GameScreen;

public class Lovelace extends Game {
	GameScreen gameScreen;
	
	@Override
	public void create () {
		gameScreen = GameScreen.getInstance();
		setScreen(gameScreen);
	}

	@Override
	public void dispose() {
		super.dispose();
	}
	
	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}
	
}
