package com.cvte.game.game.cell;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.AlphaAction;
import com.badlogic.gdx.scenes.scene2d.actions.DelayAction;
import com.badlogic.gdx.scenes.scene2d.actions.ParallelAction;
import com.badlogic.gdx.scenes.scene2d.actions.ScaleToAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.cvte.game.Assets;
import com.cvte.game.game.GameScreen;

public class Cell extends Actor {
	
	private Sprite mSpCell;
	
	private int mPosA;
	private int mPosB;
	private int mCellWidth;
	
	private int mState;
	private final int STATE_EMPTY = 0;
	private final int STATE_DEFAULT = 1;
	
	public Cell() {
		mState = STATE_EMPTY;
	}
	
	public void dispose() {
		mSpCell = null;
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		
		if (mSpCell != null) {
			mSpCell.setColor(getColor());
			mSpCell.draw(batch);
		}
	}

	@Override
	public void setPosition(float x, float y) {
		super.setPosition(x, y);

		if (mSpCell != null) {
			mSpCell.setPosition(x, y);
		}
	}
	
	@Override
	public void setScale(float scaleX, float scaleY) {
		super.setScale(scaleX, scaleY);

		if (mSpCell != null) {
			mSpCell.setScale(scaleX, scaleY);
		}
	}

	@Override
	public void setColor(Color color) {
		super.setColor(color);
		
		if (mSpCell != null) {
			mSpCell.setColor(color);
		}
	}

	public void initDefault(int posA, int posB, Color color,int cellWidth) {
		mPosA = posA;
		mPosB = posB;
		mCellWidth = cellWidth;
		
		mSpCell = new Sprite(Assets.getTextureCell(), mCellWidth, mCellWidth);
		
		setColor(color);
		getColor().a = 0;
		mSpCell.setColor(getColor());
		setScale(0, 0);
		
		AlphaAction alphaAction1 = Actions.alpha(0.1f, 0.4f);
		AlphaAction alphaAction2 = Actions.alpha(0.5f, 0.4f);
		SequenceAction sequenceAction1 = Actions.sequence(alphaAction1, alphaAction2);
		ScaleToAction scaleToAction2 = Actions.scaleTo(1.1f, 1.1f, 0.4f);
		ScaleToAction scaleToAction3 = Actions.scaleTo(1f, 1f, 0.4f);
		SequenceAction sequenceAction2 = Actions.sequence(scaleToAction2, scaleToAction3);
		DelayAction delayAction = Actions.delay(MathUtils.random(0.5f));
		ParallelAction parallelAction = Actions.parallel(sequenceAction1, sequenceAction2);
		SequenceAction sequenceAction = Actions.sequence(delayAction, parallelAction);
		addAction(sequenceAction);
		
		addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				clickeCell(event, x, y);
			}
		});
				
		setBounds(0, 0, mCellWidth, mCellWidth);
		
		mState = STATE_DEFAULT;
	}
	
	private void clickeCell(InputEvent event, float x, float y) {
		GameScreen.getInstanceValue().touchCell(mPosA, mPosB);
	}
	
}
