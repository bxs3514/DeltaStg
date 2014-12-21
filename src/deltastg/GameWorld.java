/** 基于世界之境(http://sekai.agrp.info)世界观的一个简易Stg
 * 作者：大地无敌、Marco、贝祥舜
 * Some Rights Reserved.
 */
package deltastg;

import java.util.*;

import deltastg.base.*;
import deltastg.combat.*;

/** 游戏世界，嘛就是游戏物件管理器一只而已……
 * @author 大地无敌
 * 最后修改Dec 27, 2012
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
		//执行玩家操作
		if(player!=null)
			player.playerInput();
		
		ArrayList<Battler> battlersList = battlers.getClonedList();
		ArrayList<Bullet> bulletList = bullets.getClonedList();
		//进行碰撞检测与伤害处理
		for(Battler battler:battlersList)
		{
			for(Bullet bullet:bulletList)
			{
				
				if((!bullet.getIsDying())&&battler.getFaction()!=bullet.getFaction() && CollisionChecker.isCollided(battler, bullet))
				{
					battler.takeDamage(bullet.getDamage(), bullet);//造成伤害
					bullet.beginToDie();//杀死子弹
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
	

	/**玩家*/
	private IPlayer player;

	/**得到玩家
	 * @return the player
	 */
	public IPlayer getPlayer() {
		return player;
	}

	/**设置玩家
	 * @param player the player to set
	 */
	public void setPlayer(IPlayer player) {
		this.player = player;
	}

}
