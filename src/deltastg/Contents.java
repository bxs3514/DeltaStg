/** ��������֮��(http://sekai.agrp.info)����۵�һ������Stg
 * ���ߣ�����޵С�Marco������˴
 * Some Rights Reserved.
 */
package deltastg;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

/** ��̬�࣬�洢��ȡ�Ķ���Ϊ�˱����ظ���ȡ
 * ��Ȼ������ݹ�����ѧ�����������ɣ�XD
 * @author ����޵�
 * ����޸�Dec 26, 2012
 */
public class Contents {
	public static Texture[] battlers = new Texture[100];
	public static Texture[] bullets = new Texture[100];
	
	static {
		battlers[0] = Renderer.LoadPNG("res/player.png");
		battlers[1] = Renderer.LoadPNG("res/enemy1.png");
		battlers[2] = Renderer.LoadPNG("res/enemy2.png");
		battlers[3] = Renderer.LoadPNG("res/boss.png");
		bullets[0] =  Renderer.LoadPNG("res/bullet1.png");
		bullets[1] =  Renderer.LoadPNG("res/bullet2.png");
		bullets[2] =  Renderer.LoadPNG("res/bullet3.png");
		bullets[3] =  Renderer.LoadPNG("res/bullet4.png");
		bullets[4] =  Renderer.LoadPNG("res/bullet5.png");
		
	}

}
