/** 基于世界之境(http://sekai.agrp.info)世界观的一个简易Stg
 * 作者：大地无敌、Marco、贝祥舜
 * Some Rights Reserved.
 */
package deltastg.combat.bullets;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import deltastg.*;
import deltastg.combat.Bullet;

/** 爆弹 被灭后会生成小子弹
 * @author 大地无敌
 * 最后修改Dec 27, 2012
 */
public class ExplosionBullet extends Bullet {
	private GameWorld gameWorld;
	private float currentRotation = 0;
	
	/** 生成爆弹
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
