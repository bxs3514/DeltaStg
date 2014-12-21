/** 基于世界之境(http://sekai.agrp.info)世界观的一个简易Stg
 * 作者：大地无敌、贝祥舜、Marco
 * Some Rights Reserved.
 */
package deltastg;

import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import deltastg.base.*;

/** 游戏场景基类 抽象类
 * @author 大地无敌
 * 最后修改12/25/2012
 */
public abstract class Scene implements IUpdatable, IDrawable,IRemovable {
	
	
	
	public Scene(GameRunner gameRunner) {
		super();
		this.gameRunner = gameRunner;
	}


	@Override
	public void update(float timeSpan) {
		
	}
	
	
	@Override
	public void draw(float timeSpan) {
		if(background!=null)
		{
			Renderer.drawImage(background, 0, 0,Color.white);//绘制背景图
		}
	}
	
	
	/**开启场景，执行update和draw
	 * 
	 */
	public void open()
	{
		this.isActived = true;
		this.isVisible = true;
	}
	
	/**关闭场景，停止执行update和draw
	 * 
	 */
	public void close()
	{
		this.isActived = false;
		this.isVisible = false;
	}
	
	@Override
	public boolean getIsVisible() {
		return isVisible;
	}
	@Override
	public boolean getIsActived() {
		return isActived;
	}
	
	/** 得到背景图
	 * @return 背景图
	 */
	public Texture getBackground() {
		return background;
	}

	/** 设置背景图 
	 * @param background 背景图
	 */
	public void setBackground(Texture background) {
		this.background = background;
	}

	/** 得到管理它的gameRunner
	 * @return the gameRunner
	 */
	protected GameRunner getGameRunner() {
		return gameRunner;
	}



	private Texture background; //背景图
	private boolean isActived = false; //是否激活，即是否执行update
	private boolean isVisible = false; //是否可见，即是否执行draw
	private GameRunner gameRunner;
	
	
	
}
