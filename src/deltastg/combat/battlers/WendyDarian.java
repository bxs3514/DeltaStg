/** 基于世界之境(http://sekai.agrp.info)世界观的一个简易Stg
 * 作者：大地无敌、Marco、贝祥舜
 * Some Rights Reserved.
 */
package deltastg.combat.battlers;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;
import org.lwjgl.input.*;

import deltastg.*;
import deltastg.combat.Battler;
import deltastg.combat.IPlayer;
import deltastg.combat.bullets.*;

/**
 * 温蒂·达利安（玩家）
 * 
 * @author 大地无敌 最后修改2012-12-27
 */
public class WendyDarian extends Battler implements IPlayer {

	private float maxSpeed = 1024f;
	private GameWorld world;
	private float shootCooldown = 0.125f;
	private float cooldownRemain = 0;
	private float hpRegene = 25;

	/**
	 * @param position 位置
	 * @param faction 阵营
	 * @param world 世界
	 */
	public WendyDarian( Vector2f position, int faction, GameWorld world) {
		super(Contents.battlers[0], position, 6f, faction, 1, Color.white, 1000);
		this.world = world;
	}

	@Override
	public void playerInput() {
		if (!this.isRemoving()) {
			Vector2f moveDirection = new Vector2f(0, 0);
			if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)
					|| Keyboard.isKeyDown(Keyboard.KEY_A)) {
				moveDirection.x -= 1;
			}
			if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)
					|| Keyboard.isKeyDown(Keyboard.KEY_D)) {
				moveDirection.x += 1;
			}
			if (Keyboard.isKeyDown(Keyboard.KEY_UP)
					|| Keyboard.isKeyDown(Keyboard.KEY_W)) {
				moveDirection.y += 1;
			}
			if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)
					|| Keyboard.isKeyDown(Keyboard.KEY_S)) {
				moveDirection.y -= 1;
			}

			if (moveDirection.x == 0 && moveDirection.y == 0)
				stop();
			else {
				moveDirection.normalise();
				Vector2f xaxis = new Vector2f(1,0);
				float cross = xaxis.x*moveDirection.y-xaxis.y*moveDirection.x;
				if(cross!=0)
					move(-Math.signum(cross)*Vector2f.angle(moveDirection, new Vector2f(1, 0)),
						maxSpeed);
				else move(Vector2f.angle(moveDirection, new Vector2f(1, 0)),
						maxSpeed);
			}
			
			
			if(Keyboard.isKeyDown(Keyboard.KEY_SPACE))
				shoot();
		}

	}

	/* (non-Javadoc)
	 * @see deltastg.combat.Battler#update(float)
	 */
	@Override
	public void update(float timeSpan) {
		if(cooldownRemain>0)
			cooldownRemain-=timeSpan;
		super.update(timeSpan);
		
		Vector2f pos = this.getPosition();
		if(pos.x<0)
		{
			this.setPosition(0,pos.y);
		}
		if(pos.x>Renderer.getWidth())
		{
			this.setPosition(Renderer.getWidth(),pos.y);
		}
		if(pos.y<0)
		{
			this.setPosition(pos.x,0);
		}
		if(pos.y>Renderer.getHeight())
		{
			this.setPosition(pos.x,Renderer.getHeight());
		}
		
		this.sethP(this.gethP()+hpRegene*timeSpan);
	}
	
	public void shoot()
	{
		if(cooldownRemain<=0)
		{
			world.addBullet(new DirectBullet(Contents.bullets[0],this.getPosition(), 15, this.getFaction(), 1, Color.white, 97, -(float)Math.PI/2, 1280, 1));
			cooldownRemain+=shootCooldown;
		}
	}

	
}
