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

/** ��ײ����ж��� ��̬��
 * @author ����޵�
 * ����޸�12/26/2012
 */
public class CollisionChecker {
	/** �õ�A�����B��������ƽ��
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
	
	/** �ж�A�����B�����Ƿ�����ײ
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
