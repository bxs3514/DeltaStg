/** ��������֮��(http://sekai.agrp.info)����۵�һ������Stg
 * ���ߣ�����޵С�����˴��Marco
 * Some Rights Reserved.
 */
package deltastg;

import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import deltastg.base.*;

/** ��Ϸ�������� ������
 * @author ����޵�
 * ����޸�12/25/2012
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
			Renderer.drawImage(background, 0, 0,Color.white);//���Ʊ���ͼ
		}
	}
	
	
	/**����������ִ��update��draw
	 * 
	 */
	public void open()
	{
		this.isActived = true;
		this.isVisible = true;
	}
	
	/**�رճ�����ִֹͣ��update��draw
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
	
	/** �õ�����ͼ
	 * @return ����ͼ
	 */
	public Texture getBackground() {
		return background;
	}

	/** ���ñ���ͼ 
	 * @param background ����ͼ
	 */
	public void setBackground(Texture background) {
		this.background = background;
	}

	/** �õ���������gameRunner
	 * @return the gameRunner
	 */
	protected GameRunner getGameRunner() {
		return gameRunner;
	}



	private Texture background; //����ͼ
	private boolean isActived = false; //�Ƿ񼤻���Ƿ�ִ��update
	private boolean isVisible = false; //�Ƿ�ɼ������Ƿ�ִ��draw
	private GameRunner gameRunner;
	
	
	
}
