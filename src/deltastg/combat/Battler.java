/** ��������֮��(http://sekai.agrp.info)����۵�һ������Stg
 * ���ߣ�����޵С�Marco������˴
 * Some Rights Reserved.
 */
package deltastg.combat;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

/** ս����
 * @author ����޵�
 * ����޸�Dec 26, 2012
 */
public class Battler extends CombatObject {

	/** ����һ��ս����
	 * @param texture
	 * @param position
	 * @param boundingRadius
	 * @param scale
	 * @param color
	 */
	public Battler(Texture texture, Vector2f position, float boundingRadius,
			int faction, float scale, Color color, float maxHP) {
		super(texture, position, boundingRadius, faction, scale, color);
		this.maxHP = maxHP;
		this.hP = maxHP;
	}
	
	
	
	/* (non-Javadoc)
	 * @see deltastg.combat.CombatObject#update(float)
	 */
	@Override
	public void update(float timeSpan) {
	
		super.update(timeSpan);
		if(hP<=0)
			this.beginToDie();
			
	}
	
	
	/* (non-Javadoc)
	 * @see deltastg.combat.CombatObject#draw(float)
	 */
	@Override
	public void draw(float timeSpan) {
		// TODO Auto-generated method stub
		super.draw(timeSpan);
	}



	private float maxHP = 1f;
	/**
	 * @return the maxHP
	 */
	public float getMaxHP() {
		return maxHP;
	}
	/**
	 * @param maxHP the maxHP to set
	 */
	public void setMaxHP(float maxHP) {
		this.maxHP = maxHP;
		if(hP>maxHP) hP = maxHP;
	}

	private float hP = 1f;
	/**
	 * @return the hP
	 */
	public float gethP() {
		return hP;
	}
	/**
	 * @param hP the hP to set
	 */
	public void sethP(float hP) {
		if(hP<0)
			this.hP = 0;
		else if(hP<=maxHP)
			this.hP = hP;
		else this.hP = maxHP;
	}

	public void takeDamage(float damage,CombatObject source)
	{
		this.sethP(hP-damage);
	}
	
	/** ��ָ���ƶ�������ٶȿ�ʼ�ƶ�
	 * @param direction �ƶ�����
	 * @param speed �ٶ�
	 */
	public void move(float direction,float speed)
	{
		this.setDirection(direction);
		this.setSpeed(speed);
	}
	
	/**ֹͣ�ƶ�*/
	public void stop()
	{
		this.setSpeed(0);
	}

}
