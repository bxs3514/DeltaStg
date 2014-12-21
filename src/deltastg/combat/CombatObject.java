/** 基于世界之境(http://sekai.agrp.info)世界观的一个简易Stg
 * 作者：大地无敌、贝祥舜、Marco
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

/** 可以绘制、碰撞、死亡的标准战斗物件基类
 * @author 大地无敌
 * 最后修改12/26/2012
 */
public class CombatObject implements IDrawable, IRemovable, IUpdatable {
	
	/** 构造一个CombatObject
	 * @param texture 纹理
	 * @param position 位置
	 * @param boundingRadius 碰撞半径
	 * @param faction 阵营
	 * @param scale 缩放值
	 * @param color 颜色
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
				Renderer.drawImageCentered(texture, (int)position.getX(), (int)position.getY(), scale, color);//绘制背景图
			}
			else Renderer.drawImageCentered(texture, (int)position.getX(), (int)position.getY(), scale, color, rotation);//绘制背景图
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
	
	/** 获得纹理
	 * @return the texture
	 */
	public Texture getTexture() {
		return texture;
	}

	/** 设置纹理
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
	

	/** 获取位置 注意获取的是位置的拷贝
	 * @return the position
	 */
	public Vector2f getPosition() {
		return new Vector2f(position);
	}

	/** 设置位置
	 * @param position the position to set
	 */
	public void setPosition(Vector2f position) {
		this.position.set(position);
	}

	/** 设置位置
	 * @param x 横坐标
	 * @param y 纵坐标
	 */
	public void setPosition(float x,float y) {
		this.position.set(x,y);
	}

	
	public void remove()
	{
		isBeingRemoved = true;
	}
	
	/**如果要添加死亡效果或自定义死亡过程请继承这个方法吧！！
	 * 否则就将直接移除！
	 * 话说我这个注释到底是给谁看的呢？
	 */
	public void beginToDie()
	{
		isDying = true;
		isBeingRemoved = true;
	}
	
	/** 得到图片的旋转角 弧度
	 * @return the rotation
	 */
	public float getRotation() {
		return rotation;
	}
	/** 设置图片的旋转角 弧度
	 * @param rotation the rotation to set
	 */
	public void setRotation(float rotation) {
		//限制rotation在0到2pi之间
		float twoPI = (float)(2*Math.PI);
		this.rotation = rotation-((int)(rotation/twoPI))*twoPI;
		
	}
	
	/** 设置阵营
	 * @return the faction
	 */
	public int getFaction() {
		return faction;
	}

	/** 得到阵营
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
		//限制direction在0到2pi之间
		float twoPI = (float)(2*Math.PI);
		this.direction = direction-((int)(direction/twoPI))*twoPI;
		
		//获得新的速度方向向量
		Vector4f origin = new Vector4f(1,0,0,0);
		Matrix4f rM = new Matrix4f();
		rM.setIdentity(); //设置为恒等矩阵
		rM.rotate(direction, new Vector3f(0,0,1));//生成旋转矩阵
		Matrix4f.transform(rM, origin, origin);
		directionV.set(origin.x, origin.y);
		
	}

	/** 设置速度，每秒
	 * @param speed the speed to set
	 */
	public void setSpeed(float speed) {
		this.speed = speed;
	}
	/** 获取速度，每秒
	 * @return the speed
	 */
	public float getSpeed() {
		return speed;
	}
	




	/** 是否正在死亡
	 * @return the isDying
	 */
	public boolean getIsDying() {
		return isDying;
	}





	/** 设置是否正在死亡的标记，如果在播放死亡动画的状态时应该设置isBeingRemoved为false isDying为true
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
	/** 速度方向 弧度  0为x轴正方向 */
	private float direction = 0;
	private Vector2f directionV = new Vector2f(1,0);
	private float speed = 0;
	private Vector2f positionl = new Vector2f(0,0);
	private boolean isDying = false;

}
