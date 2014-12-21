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

/** 碰撞检测判断类 静态类
 * @author 大地无敌
 * 最后修改12/26/2012
 */
public class CollisionChecker {
	/** 得到A物体和B物体距离的平方
	 * @param a
	 * @param b
	 * @return
	 */
	public static float distanceSquared(CombatObject a,CombatObject b)
	{
		Vector2f bnag = b.getPosition();
		bnag.negate();
		Vector2f result = new Vector2f();
		Vector2f.add(a.getPosition(), bnag, result);
		return result.lengthSquared();
	}
	
	/** 判断A物体和B物体是否相碰撞
	 * @param a
	 * @param b
	 * @return
	 */
	public static boolean isCollided(CombatObject a,CombatObject b)
	{
		float temp =a.getBoundingRadius()+b.getBoundingRadius();
		temp*=temp;
		return distanceSquared(a,b)<=temp;
	}
}
