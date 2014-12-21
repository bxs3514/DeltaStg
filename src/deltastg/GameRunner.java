/** 基于世界之境(http://sekai.agrp.info)世界观的一个简易Stg
 * 作者：大地无敌、贝祥舜、Marco
 * Some Rights Reserved.
 */
package deltastg;

//基于BSD使用LWJGL
import org.lwjgl.LWJGLException;
import org.lwjgl.input.*;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import deltastg.base.*;
import deltastg.scenes.*;

/** 游戏执行类
 * @author 大地无敌
 * 最后修改12/25/2012
 */
public class GameRunner {
	
	
	public final void start() {
		try {
		
			Display.setDisplayMode(new DisplayMode(1280,720));//设置分辨率
			Display.create();
			Display.setVSyncEnabled(true);//开启垂直同步
			Renderer.initialize2D(1280, 720);//初始化opengl2D视图
			this.setFrameRate(60);//设置帧率
			Keyboard.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}
		initialize();
		
		while (!Display.isCloseRequested()) {
			update();//逻辑更新
			draw();//绘制，感觉这里写多线程把draw和update分开会更好不过算了。
			Display.update();
			Display.sync(frameRate); //同步帧数为60fps
		}
		Display.destroy();
		
	}
	
	/**初始化*/
	protected void initialize()
	{

		
	}
	
	/**逻辑更新*/
	protected void update()
	{
		Keyboard.poll();
		sceneManager.update(idealTimeSpan);//执行场景管理器的update
		
	}
	
	/**绘制*/
	protected void draw()
	{
		sceneManager.draw(idealTimeSpan);//执行场景管理器的draw
	}
	
	/** 得到帧率
	 * @return 帧率
	 */
	public int getFrameRate() {
		return frameRate;
	}

	/** 设置帧率
	 * @param frameRate 帧率
	 */
	public void setFrameRate(int frameRate) {
		this.frameRate = frameRate;
		this.idealTimeSpan = 1f/frameRate;
	}
	
	protected ItemManager<Scene> sceneManager = new ItemManager<Scene>(10);//场景管理器 基于我的物件管理器类 参照deltastg.base.ItemManager
	
	private int frameRate = 60;//帧数
	private float idealTimeSpan = 0.016667f;//理想情况下的时间间隔
	
}
