/** 基于世界之境(http://sekai.agrp.info)世界观的一个简易Stg
 * 作者：大地无敌、Marco、贝祥舜
 * Some Rights Reserved.
 */
package deltastg.combat.bullets;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import deltastg.combat.Bullet;

/** 基础的直弹
 * @author 大地无敌
 * 最后修改2012-12-27
 */
public class DirectBullet extends Bullet {

	/** 生成直弹
	 */
	public DirectBullet(Texture texture, Vector2f position,
			float boundingRadius, int faction, float scale, Color color,
			float damage, float direction, float speed, float lifeTime) {
		super(texture, position, boundingRadius, faction, scale, color, damage,
				direction, speed, lifeTime);
		// TODO Auto-generated constructor stub
	}

}
