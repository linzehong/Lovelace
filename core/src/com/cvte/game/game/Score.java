package com.cvte.game.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.cvte.game.Data;

public class Score {

	private BitmapFont mFont;
	private Label mLbScore;
	
	public Score() {
		mFont = new BitmapFont(Gdx.files.internal("num.fnt"));
		
		LabelStyle labelStyle = new LabelStyle(mFont, mFont.getColor());
		
		mLbScore = new Label("0", labelStyle);
		displayScore(0);
	}
	
	public void dispose() {
		if (mFont != null) {
			mFont.dispose();
			mFont = null;
		}
		if (mLbScore != null) {
			mLbScore = null;
		}
	}
	
	public void displayScore(int score) {
		mLbScore.setText(score + "");
		
		mLbScore.setX((Data.SCREEN_WIDTH - mLbScore.getWidth()) / 2);
		mLbScore.setY(Data.SCREEN_HEIGHT - 40 - mLbScore.getHeight());
	}
	
	public Label getLabelScore() {
		return mLbScore;
	}
	
}
