/** 基于世界之境(http://sekai.agrp.info)世界观的一个简易Stg
 * 作者：大地无敌、Marco、贝祥舜
 * Some Rights Reserved.
 */
package deltastg.combat;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

/** 子弹
 * @author 大地无敌
 * 最后修改Dec 27, 2012
 */
public class Bullet extends CombatObject {
	
	private float damage = 1;
	private float lifeTime = 3f;
	/**存活时间*/
	private float existingTime = 0;



	public Bullet(Texture texture, Vector2f position, float boundingRadius,
			int faction, float scale, Color color, float damage, float direction, float speed, float lifeTime) {
		super(texture, position, boundingRadius, faction, scale, color,direction,direction,speed);
		this.damage = damage;
		this.lifeTime = lifeTime;
	}
	
	/* (non-Javadoc)
	 * @see deltastg.combat.CombatObject#update(float)
	 */
	@Override
	public void update(float timeSpan) {
		this.existingTime+=timeSpan;
		super.update(timeSpan);
		if(existingTime>lifeTime) this.beginToDie();
	}



	/** 得到伤害值
	 * @return the damage
	 */
	public float getDamage() {
		return damage;
	}

	
	/** 设置伤害值
	 * @param damage the damage to set
	 */
	public void setDamage(float damage) {
		this.damage = damage;
	}

	/** 获取最大生存时间，若为0则无限
	 * @return the lifeTime
	 */
	public float getLifeTime() {
		return lifeTime;
	}

	/** 设置最大生存时间，若为0则无限
	 * @param lifeTime the lifeTime to set
	 */
	public void setLifeTime(float lifeTime) {
		this.lifeTime = lifeTime;
	}
	

}
