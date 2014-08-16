package com.cvte.game.game.heart;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.cvte.game.Assets;

public class Heart extends Actor {

	private Sprite mSpHeart;
	
	public final static int HEART_WIDTH = 40;
	public final static int HEART_HEIGHT = 40;
	
	public Heart() {
		
	}
	
	public void dispose() {
		mSpHeart = null;
	}

	@Override
	public void act(float delta) {
		super.act(delta);
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		
		if (mSpHeart != null) {
			mSpHeart.draw(batch);
		}
	}

	@Override
	public void setPosition(float x, float y) {
		super.setPosition(x, y);
		
		if (mSpHeart != null) {
			mSpHeart.setPosition(x, y);
		}
	}
	
	public void init() {
		mSpHeart = new Sprite(Assets.getTextureHeart());
		mSpHeart.setSize(HEART_WIDTH, HEART_HEIGHT);
		
		setBounds(0, 0, HEART_WIDTH, HEART_HEIGHT);
	}
	
	
	
	
}
