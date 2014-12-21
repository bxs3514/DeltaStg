/** ��������֮��(http://sekai.agrp.info)����۵�һ������Stg
 * ���ߣ�����޵С�Marco������˴
 * Some Rights Reserved.
 */
package deltastg.combat.bullets;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import deltastg.*;
import deltastg.combat.Bullet;

/** ���� ����������С�ӵ�
 * @author ����޵�
 * ����޸�Dec 27, 2012
 */
public class ExplosionBullet extends Bullet {
	private GameWorld gameWorld;
	private float currentRotation = 0;
	
	/** ���ɱ���
	 */
	public ExplosionBullet(Vector2f position,int faction,float direction,GameWorld gameWorld) {
		super(Contents.bullets[4], position, 15f, faction, 1, Color.white, 1,
				direction, 64, 6);
		this.gameWorld = gameWorld;
		
	}

	/* (non-Javadoc)
	 * @see deltastg.combat.CombatObject#beginToDie()
	 */
	@Override
	public void beginToDie() {
		for(int i=0;i<10;i++)
		{
			gameWorld.addBullet(new DirectBullet(Contents.bullets[3], this
					.getPosition(), 3, this.getFaction(), 1f,
					Color.white, 334, currentRotation, 256, 10));
			currentRotation+=(float)Math.PI/5;
		}
		super.beginToDie();
	}
	
	

}
