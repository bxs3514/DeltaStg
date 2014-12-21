/** 基于世界之境(http://sekai.agrp.info)世界观的一个简易Stg
 * 作者：大地无敌、Bei Kun、Marco
 * Some Rights Reserved.
 */
package deltastg.scenes;


import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.input.*;

import deltastg.*;
import deltastg.base.*;
import deltastg.Scene;
import deltastg.combat.*;


/** 背景介绍画面
 * @author 大地无敌
 * 最后修改12/25/2012
 */
public class IntroducingScene extends Scene {
	private String finalString;//最终显示的文本
	private String[] str;
	private float displayTime;
	private float fullTime;
	
	public IntroducingScene(GameRunner gameRunner)
	{
		super(gameRunner);
		super.setBackground(Renderer.LoadPNG("res/background.png"));
		finalString = "　　北方王国\n" +
				"　　纯白的世界。天也是纯白的。\n" +
				"　　我站在天空之城的崖边，脚下是洁白的大地，头顶是\n" +
				"洁白的天空，眼前是洁白的旭日。\n" +
				"　　梦想，是什么样的东西呢？\n" +
				"　　没有对与错。只是希望摸索到某天发现自己不是徒劳，\n" +
				"不过我们似乎还没有资格作这样的感慨：因为从不够努力。\n" +
				"怀着感恩之心过每一天会不会好一点？但千万不要知足。\n" +
				"　　于是一位知己这样说道。\n" +
				"　　嘛，我也打算这样活着就是了。\n" +
				"　　魔法少女见习，温蒂·达莉安——\n" +
				"　　Take Off!\n" +
				"\n" +
				"\n" +
				"\n" +
				"按[空格键]继续。";
		
		str = finalString.split("\n");
	
	}

	@Override
	public void update(float timeSpan) {

		super.update(timeSpan);
		if (Keyboard.isKeyDown(Keyboard.KEY_SPACE))
			((DeltaRunner)(super.getGameRunner())).startBattle();
	}

	@Override
	public void draw(float timeSpan) {
		super.draw(timeSpan);
		
		Color.white.bind();
		Renderer.currentFont.drawString(300, 200, finalString,Color.white);
		
	}

	@Override
	public boolean isRemoving() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
