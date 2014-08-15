package com.cvte.game.game.cell;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;



public class CellData {

	public final static int MAX_CELL_NUM_IN_LINE = 9;//һ�������Ӹ���
	
	//�趨����ɫֵ
	private final static Color[] DEMO_COLORS = {
		Color.RED,Color.GREEN,Color.LIGHT_GRAY,Color.GRAY,Color.DARK_GRAY,Color.PINK,Color.ORANGE,
		Color.YELLOW,Color.MAGENTA,Color.CYAN,Color.OLIVE,Color.PURPLE,Color.MAROON,
	};
	
	//�û��ƺ�
	private final static String[] USER_NAMES = {
		"Ϲ��","ɫä","ɫ��","ɫ��","ɫ��","ɫħ","����ɫħ","��̬ɫħ","�¶���ɫ"
	};
	
	public static int getCellNumInLine(int level) {
		int num = 0;
		num = level + 2;
		if (num > MAX_CELL_NUM_IN_LINE) {
			num = MAX_CELL_NUM_IN_LINE;
		}
		return num;
	}
	
	/**
	 * ���һ�������ɫ
	 * @return
	 */
	public static Color getRdmColor() {
		return DEMO_COLORS[MathUtils.random(DEMO_COLORS.length - 1)];
	}
	
	/**
	 * ���һ���������ɫ
	 * @param color
	 * @return
	 */
	public static Color getOtherColor(Color color) {
		Color newColor = new Color();
		newColor.set(color);
		newColor.mul(MathUtils.random(), MathUtils.random(), MathUtils.random(), 1);
		return newColor;
	}
	
	/**
	 * ����û��ƺ�
	 * @param level
	 * @return
	 */
	public static String getUserName(int level) {
		int index = level>>1;
		if (index > USER_NAMES.length - 1) {
			index = USER_NAMES.length - 1;
		}
		return USER_NAMES[index];
	}
	
}
