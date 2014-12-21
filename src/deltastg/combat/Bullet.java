/** ��������֮��(http://sekai.agrp.info)����۵�һ������Stg
 * ���ߣ�����޵С�Marco������˴
 * Some Rights Reserved.
 */
package deltastg.combat;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

/** �ӵ�
 * @author ����޵�
 * ����޸�Dec 27, 2012
 */
public class Bullet extends CombatObject {
	
	private float damage = 1;
	private float lifeTime = 3f;
	/**���ʱ��*/
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



	/** �õ��˺�ֵ
	 * @return the damage
	 */
	public float getDamage() {
		return damage;
	}

	
	/** �����˺�ֵ
	 * @param damage the damage to set
	 */
	public void setDamage(float damage) {
		this.damage = damage;
	}

	/** ��ȡ�������ʱ�䣬��Ϊ0������
	 * @return the lifeTime
	 */
	public float getLifeTime() {
		return lifeTime;
	}

	/** �����������ʱ�䣬��Ϊ0������
	 * @param lifeTime the lifeTime to set
	 */
	public void setLifeTime(float lifeTime) {
		this.lifeTime = lifeTime;
	}
	

}
