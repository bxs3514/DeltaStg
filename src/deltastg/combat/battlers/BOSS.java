/** 基于世界之境(http://sekai.agrp.info)世界观的一个简易Stg
 * 作者：大地无敌、Marco、贝祥舜
 * Some Rights Reserved.
 */
package deltastg.combat.battlers;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import deltastg.Contents;
import deltastg.GameWorld;
import deltastg.Renderer;
import deltastg.combat.Battler;
import deltastg.combat.bullets.DirectBullet;
import deltastg.combat.bullets.ExplosionBullet;

/** 最终Boss
 * @author 大地无敌
 * 最后修改Dec 27, 2012
 */
public class BOSS extends Battler {

	private float maxSpeed = 128f;
	private GameWorld world;
	private float shootCooldown = 0.17f;
	private float cooldownRemain = 6f;
	private float currentRotation = -(float)Math.PI/2;
	
	private int stance = 0;
	private float stanceChangeTime = 5f;
	private float currentStanceTime =0;
	private float currentDirection = 0f;

	/**
	 * @param position 位置
	 * @param faction 阵营
	 * @param world 世界
	 */
	public BOSS( Vector2f position, int faction, GameWorld world) {
		super(Contents.battlers[3], position, 80f, faction, 1, Color.white, 30000);
		this.world = world;
		this.move((float)Math.PI/2, 50f);
	}

	/* (non-Javadoc)
	 * @see deltastg.combat.Battler#update(float)
	 */
	@Override
	public void update(float timeSpan) {
		if(cooldownRemain>0)
			cooldownRemain-=timeSpan;
		super.update(timeSpan);
		action();
		currentStanceTime+= timeSpan;
		if(currentStanceTime>stanceChangeTime)
		{
			currentStanceTime-=stanceChangeTime;
			stance++;
			stance%=4;
		}
		
		if(stance == 3)
		{
			if(this.getPosition().x>1000)
				this.currentDirection = (float)Math.PI;
			else if(this.getPosition().x<280)
				this.currentDirection = 0;
		}
		
	}
	
	public void action() {
		if (cooldownRemain <= 0) {
			switch (stance) {
			case 0:
				this.stop();

				break;
			case 1:

				shootCooldown = 0.125f;
				world.addBullet(new DirectBullet(Contents.bullets[1], this
						.getPosition().translate(0, 64), 15, this.getFaction(),
						1, Color.white, 97, (float) Math.PI / 2, 1280, 1));
				world.addBullet(new DirectBullet(Contents.bullets[1], this
						.getPosition().translate(64, 64), 4, this.getFaction(),
						1, Color.white, 97, (float) Math.PI / 2, 1280, 1));
				world.addBullet(new DirectBullet(Contents.bullets[1], this
						.getPosition().translate(-64, 64), 4,
						this.getFaction(), 1, Color.white, 97,
						(float) Math.PI / 2, 1280, 1));
				world.addBullet(new DirectBullet(Contents.bullets[1], this
						.getPosition().translate(32, 64), 4, this.getFaction(),
						1, Color.white, 97, (float) Math.PI / 2, 1280, 1));
				world.addBullet(new DirectBullet(Contents.bullets[1], this
						.getPosition().translate(-32, 64), 4,
						this.getFaction(), 1, Color.white, 97,
						(float) Math.PI / 2, 1280, 1));
				this.move(-(float) Math.PI / 2, 25f);

				break;
			case 2:

				shootCooldown = 0.2f;
				currentRotation += (float) Math.PI / 7;
				world.addBullet(new DirectBullet(Contents.bullets[2], this
						.getPosition(), 3, this.getFaction(), 0.2f,
						Color.white, 334, currentRotation, 256, 10));
				currentRotation += (float) Math.PI / 7;
				world.addBullet(new DirectBullet(Contents.bullets[2], this
						.getPosition(), 3, this.getFaction(), 0.2f,
						Color.white, 334, currentRotation, 256, 10));
				currentRotation -= (float) Math.PI / 7 * 2;
				world.addBullet(new DirectBullet(Contents.bullets[2], this
						.getPosition(), 3, this.getFaction(), 0.2f,
						Color.white, 334, currentRotation, 256, 10));
				currentRotation -= (float) Math.PI / 7;
				world.addBullet(new DirectBullet(Contents.bullets[2], this
						.getPosition(), 3, this.getFaction(), 0.2f,
						Color.white, 334, currentRotation, 256, 10));
				this.move((float) Math.PI / 2, 25f);

				break;
			case 3:
				shootCooldown = 1f;
				this.move(currentDirection, 256f);
				world.addBullet(new ExplosionBullet(this.getPosition()
						.translate(0, -32), this.getFaction(),
						(float) Math.PI / 2, this.world));
				break;
			}
			cooldownRemain += shootCooldown;
		}
	}

}
