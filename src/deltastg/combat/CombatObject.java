/** ��������֮��(http://sekai.agrp.info)����۵�һ������Stg
 * ���ߣ�����޵С�����˴��Marco
 * Some Rights Reserved.
 */
package deltastg.combat;

import org.lwjgl.util.vector.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import deltastg.Renderer;
import deltastg.base.IDrawable;
import deltastg.base.IRemovable;
import deltastg.base.IUpdatable;

/** ���Ի��ơ���ײ�������ı�׼ս���������
 * @author ����޵�
 * ����޸�12/26/2012
 */
public class CombatObject implements IDrawable, IRemovable, IUpdatable {
	
	/** ����һ��CombatObject
	 * @param texture ����
	 * @param position λ��
	 * @param boundingRadius ��ײ�뾶
	 * @param faction ��Ӫ
	 * @param scale ����ֵ
	 * @param color ��ɫ
	 */
	public CombatObject(Texture texture, Vector2f position,
			float boundingRadius, int faction, float scale, Color color) {
		super();
		this.texture = texture;
		this.position.set(position);
		this.positionl.set(position);
		this.boundingRadius = boundingRadius;
		this.scale = scale;
		this.color = color;
		this.faction = faction;
	}
	
	public CombatObject(Texture texture, Vector2f position,
			float boundingRadius, int faction, float scale,
			Color color,float direction,float rotation,float speed)
	{
		this(texture, position,
				boundingRadius,faction, scale, color);
		this.speed = speed;
		this.setRotation(rotation);
		this.setDirection(direction);
		
	}

	/* (non-Javadoc)
	 * @see deltastg.base.IUpdatable#update(float)
	 */
	@Override
	public void update(float timeSpan) {
		Vector2f positionl = getPosition();
		if(speed!=0)
		{
			Vector2f offset = new Vector2f(directionV);
			offset.scale(speed*timeSpan);
			setPosition(positionl.x+offset.x,positionl.y+offset.y);
		}
	}

	/* (non-Javadoc)
	 * @see deltastg.base.IUpdatable#getIsActived()
	 */
	@Override
	public boolean getIsActived() {
		// TODO Auto-generated method stub
		return true;
	}

	/* (non-Javadoc)
	 * @see deltastg.base.IRemovable#isRemoving()
	 */
	@Override
	public boolean isRemoving() {
		// TODO Auto-generated method stub
		return isBeingRemoved;
	}

	/* (non-Javadoc)
	 * @see deltastg.base.IDrawable#draw(float)
	 */
	@Override
	public void draw(float timeSpan) {
		if(texture!=null)
		{
			if(rotation==0)
			{
				Renderer.drawImageCentered(texture, (int)position.getX(), (int)position.getY(), scale, color);//���Ʊ���ͼ
			}
			else Renderer.drawImageCentered(texture, (int)position.getX(), (int)position.getY(), scale, color, rotation);//���Ʊ���ͼ
		}

	}

	/* (non-Javadoc)
	 * @see deltastg.base.IDrawable#getIsVisible()
	 */
	@Override
	public boolean getIsVisible() {
		// TODO Auto-generated method stub
		return true;
	}
	
	/** �������
	 * @return the texture
	 */
	public Texture getTexture() {
		return texture;
	}

	/** ��������
	 * @param texture the texture to set
	 */
	public void setTexture(Texture texture) {
		this.texture = texture;
		
	}
	/**
	 * @return the boundingRadius
	 */
	public float getBoundingRadius() {
		return boundingRadius;
	}

	/**
	 * @param boundingRadius the boundingRadius to set
	 */
	public void setBoundingRadius(float boundingRadius) {
		this.boundingRadius = boundingRadius;
	}
	

	/** ��ȡλ�� ע���ȡ����λ�õĿ���
	 * @return the position
	 */
	public Vector2f getPosition() {
		return new Vector2f(position);
	}

	/** ����λ��
	 * @param position the position to set
	 */
	public void setPosition(Vector2f position) {
		this.position.set(position);
	}

	/** ����λ��
	 * @param x ������
	 * @param y ������
	 */
	public void setPosition(float x,float y) {
		this.position.set(x,y);
	}

	
	public void remove()
	{
		isBeingRemoved = true;
	}
	
	/**���Ҫ�������Ч�����Զ�������������̳���������ɣ���
	 * ����ͽ�ֱ���Ƴ���
	 * ��˵�����ע�͵����Ǹ�˭�����أ�
	 */
	public void beginToDie()
	{
		isDying = true;
		isBeingRemoved = true;
	}
	
	/** �õ�ͼƬ����ת�� ����
	 * @return the rotation
	 */
	public float getRotation() {
		return rotation;
	}
	/** ����ͼƬ����ת�� ����
	 * @param rotation the rotation to set
	 */
	public void setRotation(float rotation) {
		//����rotation��0��2pi֮��
		float twoPI = (float)(2*Math.PI);
		this.rotation = rotation-((int)(rotation/twoPI))*twoPI;
		
	}
	
	/** ������Ӫ
	 * @return the faction
	 */
	public int getFaction() {
		return faction;
	}

	/** �õ���Ӫ
	 * @param faction the faction to set
	 */
	public void setFaction(int faction) {
		this.faction = faction;
	}
	
	/**
	 * @return the direction
	 */
	public float getDirection() {
		return direction;
	}
	
	/**
	 * @param direction the direction to set
	 */
	public void setDirection(float direction) {
		//����direction��0��2pi֮��
		float twoPI = (float)(2*Math.PI);
		this.direction = direction-((int)(direction/twoPI))*twoPI;
		
		//����µ��ٶȷ�������
		Vector4f origin = new Vector4f(1,0,0,0);
		Matrix4f rM = new Matrix4f();
		rM.setIdentity(); //����Ϊ��Ⱦ���
		rM.rotate(direction, new Vector3f(0,0,1));//������ת����
		Matrix4f.transform(rM, origin, origin);
		directionV.set(origin.x, origin.y);
		
	}

	/** �����ٶȣ�ÿ��
	 * @param speed the speed to set
	 */
	public void setSpeed(float speed) {
		this.speed = speed;
	}
	/** ��ȡ�ٶȣ�ÿ��
	 * @return the speed
	 */
	public float getSpeed() {
		return speed;
	}
	




	/** �Ƿ���������
	 * @return the isDying
	 */
	public boolean getIsDying() {
		return isDying;
	}





	/** �����Ƿ����������ı�ǣ�����ڲ�������������״̬ʱӦ������isBeingRemovedΪfalse isDyingΪtrue
	 * @param isDying the isDying to set
	 */
	public void setDying(boolean isDying) {
		this.isDying = isDying;
	}





	private Texture texture;
	private Vector2f position = new Vector2f(0,0);

	private float boundingRadius = 1f;

	private float scale = 1.0f;
	private boolean isBeingRemoved = false;
	private Color color = Color.white;
	private int faction = 0;
	private float rotation = 0;
	/** �ٶȷ��� ����  0Ϊx�������� */
	private float direction = 0;
	private Vector2f directionV = new Vector2f(1,0);
	private float speed = 0;
	private Vector2f positionl = new Vector2f(0,0);
	private boolean isDying = false;

}
