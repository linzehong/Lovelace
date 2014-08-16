package com.cvte.game.game.cell;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.cvte.game.Data;
import com.cvte.game.game.GameScreen;

public class CellManager extends Group {
	private static CellManager instance;

	private Cell[][] mCells;
	
	private int mCellNumInLine;//一行由几个格子组成
	
	private int mCellWidth;//格子的宽
	private int mCellInterval;//格子间的空隙
	
	//格子显示区域
	private final int BOUNDS_WIDTH = 600;
	private final int BOUNDS_HEIGHT = 600;
	private final int BOUNDS_X = (Data.SCREEN_WIDTH - BOUNDS_WIDTH) / 2;
	private final int BOUNDS_Y = 200;
	
	public static CellManager getInstance() {
		if (instance == null) {
			instance = new CellManager();
		}
		return instance;
	}
	
	public static CellManager getInstanceValue() {
		return instance;
	}
	
	private CellManager() {
	
	}
	
	public void dispose() {
		disposeCells();
		
		instance = null;
	}

	@Override
	public void act(float delta) {
		super.act(delta);
	}
	
	public void reset() {
		disposeCells();
	}
	
	private void disposeCells() {
		if (mCells != null) {
			int length = mCells.length;
			for (int i = 0; i < length; ++i) {
				for (int j = 0; j < length; ++j) {
					if (mCells[i][j] != null) {
						mCells[i][j].dispose();
						mCells[i][j] = null;
					}
				}
			}
			mCells = null;
		}
	}
	
	public void init() {
		mCellNumInLine = CellData.getCellNumInLine(Data.mCurLevel);
		calcCellWidthAndInterval();
		
		mCells = new Cell[mCellNumInLine][mCellNumInLine];
		
		int targetIndexA = MathUtils.random(0, mCellNumInLine - 1);
		int targetIndexB = MathUtils.random(0, mCellNumInLine - 1);
		GameScreen.getInstanceValue().setTargetIndex(targetIndexA, targetIndexB);
		
		Color color0 = CellData.getRdmColor();
		Color color1 = CellData.getOtherColor(color0);
		
		int cellX;
		int cellY;
		for (int i = 0; i < mCellNumInLine; ++i) {
			for (int j = 0; j < mCellNumInLine; ++j) {
				mCells[i][j] = new Cell();
				addActor(mCells[i][j]);
				
				if ((i == targetIndexA) && (j == targetIndexB)) {
					mCells[i][j].initDefault(i, j, color1, mCellWidth);
				}
				else {
					mCells[i][j].initDefault(i, j, color0, mCellWidth);
				}
				cellX = mCellWidth * j + mCellInterval * (j + 1);
				cellY = mCellWidth * (mCellNumInLine - i - 1) + mCellInterval * (mCellNumInLine - i);
				mCells[i][j].setPosition(cellX, cellY);
			}
		}
		
		setBounds(BOUNDS_X, BOUNDS_Y, BOUNDS_WIDTH, BOUNDS_HEIGHT);
	}
	
	/**
	 * 计算获得格子的宽和格子间隔
	 * 每个格子边上都有一个间隔
	 * (格子宽  * 一行格子数) + (间隔宽 * (一行格子数 + 1)) = 显示区宽
	 * 间隔宽 = 格子宽 * 10%
	 */
	private void calcCellWidthAndInterval() {
		mCellWidth = (int)(BOUNDS_WIDTH / (mCellNumInLine * 1.1 + 0.1));
		mCellInterval = (int)(mCellWidth * 0.1);
		
		System.out.println("mCellWidth = " + mCellWidth + "  " + "mCellInterval = " + mCellInterval);
	}
	
	public int getCellNumInLine() {
		return mCellNumInLine;
	}
	
	public int getCellWidth() {
		return mCellWidth;
	}
	
}
