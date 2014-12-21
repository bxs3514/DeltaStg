/** ��������֮��(http://sekai.agrp.info)����۵�һ������Stg
 * ���ߣ�����޵С�Marco������˴
 * Some Rights Reserved.
 */
package deltastg;

import java.util.*;

import deltastg.base.*;
import deltastg.combat.*;

/** ��Ϸ���磬�������Ϸ���������һֻ���ѡ���
 * @author ����޵�
 * ����޸�Dec 27, 2012
 */
public class GameWorld implements IDrawable, IUpdatable {

	public ItemManager<Battler> battlers = new ItemManager<Battler>(20);
	public ItemManager<Bullet> bullets = new ItemManager<Bullet>(500);
	
	public GameWorld() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see deltastg.base.IUpdatable#update(float)
	 */
	@Override
	public void update(float timeSpan) {
		//ִ����Ҳ���
		if(player!=null)
			player.playerInput();
		
		ArrayList<Battler> battlersList = battlers.getClonedList();
		ArrayList<Bullet> bulletList = bullets.getClonedList();
		//������ײ������˺�����
		for(Battler battler:battlersList)
		{
			for(Bullet bullet:bulletList)
			{
				
				if((!bullet.getIsDying())&&battler.getFaction()!=bullet.getFaction() && CollisionChecker.isCollided(battler, bullet))
				{
					battler.takeDamage(bullet.getDamage(), bullet);//����˺�
					bullet.beginToDie();//ɱ���ӵ�
				}
			}
		}
		
		battlers.update(timeSpan);
		bullets.update(timeSpan);

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
	 * @see deltastg.base.IDrawable#draw(float)
	 */
	@Override
	public void draw(float timeSpan) {
		bullets.draw(timeSpan);
		battlers.draw(timeSpan);
	}

	/* (non-Javadoc)
	 * @see deltastg.base.IDrawable#getIsVisible()
	 */
	@Override
	public boolean getIsVisible() {
		// TODO Auto-generated method stub
		return true;
	}
	
	public void addBattler(Battler battler)
	{
		battlers.add(battler);
	}
	
	public void addBullet(Bullet bullet)
	{
		bullets.add(bullet);
	}
	

	/**���*/
	private IPlayer player;

	/**�õ����
	 * @return the player
	 */
	public IPlayer getPlayer() {
		return player;
	}

	/**�������
	 * @param player the player to set
	 */
	public void setPlayer(IPlayer player) {
		this.player = player;
	}

}
