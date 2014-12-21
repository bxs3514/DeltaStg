/** ��������֮��(http://sekai.agrp.info)����۵�һ������Stg
 * ���ߣ�����޵С�����˴��Marco
 * Some Rights Reserved.
 */
package deltastg;

//����BSDʹ��LWJGL
import org.lwjgl.LWJGLException;
import org.lwjgl.input.*;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import deltastg.base.*;
import deltastg.scenes.*;

/** ��Ϸִ����
 * @author ����޵�
 * ����޸�12/25/2012
 */
public class GameRunner {
	
	
	public final void start() {
		try {
		
			Display.setDisplayMode(new DisplayMode(1280,720));//���÷ֱ���
			Display.create();
			Display.setVSyncEnabled(true);//������ֱͬ��
			Renderer.initialize2D(1280, 720);//��ʼ��opengl2D��ͼ
			this.setFrameRate(60);//����֡��
			Keyboard.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}
		initialize();
		
		while (!Display.isCloseRequested()) {
			update();//�߼�����
			draw();//���ƣ��о�����д���̰߳�draw��update�ֿ�����ò������ˡ�
			Display.update();
			Display.sync(frameRate); //ͬ��֡��Ϊ60fps
		}
		Display.destroy();
		
	}
	
	/**��ʼ��*/
	protected void initialize()
	{

		
	}
	
	/**�߼�����*/
	protected void update()
	{
		Keyboard.poll();
		sceneManager.update(idealTimeSpan);//ִ�г�����������update
		
	}
	
	/**����*/
	protected void draw()
	{
		sceneManager.draw(idealTimeSpan);//ִ�г�����������draw
	}
	
	/** �õ�֡��
	 * @return ֡��
	 */
	public int getFrameRate() {
		return frameRate;
	}

	/** ����֡��
	 * @param frameRate ֡��
	 */
	public void setFrameRate(int frameRate) {
		this.frameRate = frameRate;
		this.idealTimeSpan = 1f/frameRate;
	}
	
	protected ItemManager<Scene> sceneManager = new ItemManager<Scene>(10);//���������� �����ҵ������������ ����deltastg.base.ItemManager
	
	private int frameRate = 60;//֡��
	private float idealTimeSpan = 0.016667f;//��������µ�ʱ����
	
}
