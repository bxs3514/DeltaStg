/** ��������֮��(http://sekai.agrp.info)����۵�һ������Stg
 * ���ߣ�����޵С�Marco������˴
 * Some Rights Reserved.
 */
package deltastg.combat.bullets;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import deltastg.combat.Bullet;

/** ������ֱ��
 * @author ����޵�
 * ����޸�2012-12-27
 */
public class DirectBullet extends Bullet {

	/** ����ֱ��
	 */
	public DirectBullet(Texture texture, Vector2f position,
			float boundingRadius, int faction, float scale, Color color,
			float damage, float direction, float speed, float lifeTime) {
		super(texture, position, boundingRadius, faction, scale, color, damage,
				direction, speed, lifeTime);
		// TODO Auto-generated constructor stub
	}

}
