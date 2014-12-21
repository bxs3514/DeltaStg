/** ��������֮��(http://sekai.agrp.info)����۵�һ������Stg
 * ���ߣ�����޵С�Marco������˴
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

/** ��ͨ�ĵ���
 * @author ����޵�
 * ����޸�Dec 27, 2012
 */
public class Enemy1 extends Battler {

	private float maxSpeed = 128f;
	private GameWorld world;
	private float shootCooldown = 0.75f;
	private float cooldownRemain = 1f;
	private float currentRotation = (float)Math.PI/2;
	

	/**
	 * @param position λ��
	 * @param faction ��Ӫ
	 * @param world ����
	 */
	public Enemy1( Vector2f position, int faction, GameWorld world,float direction) {
		super(Contents.battlers[1], position, 128f, faction, 1, Color.white, 500);
		this.world = world;
		this.move(direction, maxSpeed);
	}

	/* (non-Javadoc)
	 * @see deltastg.combat.Battler#update(float)
	 */
	@Override
	public void update(float timeSpan) {
		if(cooldownRemain>0)
			cooldownRemain-=timeSpan;
		super.update(timeSpan);
		shoot();
		//��������������
		if(getPosition().x>Renderer.getWidth()+100||getPosition().x<-100||getPosition().y>Renderer.getHeight()+100||getPosition().y<-100)
		{
			this.beginToDie();
		}
	}
	
	public void shoot() {
		if (cooldownRemain <= 0) {
			currentRotation+=(float)Math.PI/7;
			world.addBullet(new DirectBullet(Contents.bullets[2], this
					.getPosition(), 3, this.getFaction(), 0.2f,
					Color.white, 334, currentRotation, 256, 10));
			currentRotation+=(float)Math.PI/7;
			world.addBullet(new DirectBullet(Contents.bullets[2], this
					.getPosition(), 3, this.getFaction(), 0.2f,
					Color.white, 334, currentRotation, 256, 10));
			currentRotation-=(float)Math.PI/7*2;
			world.addBullet(new DirectBullet(Contents.bullets[2], this
					.getPosition(), 3, this.getFaction(), 0.2f,
					Color.white, 334, currentRotation, 256, 10));
			currentRotation-=(float)Math.PI/7;
			world.addBullet(new DirectBullet(Contents.bullets[2], this
					.getPosition(), 3, this.getFaction(), 0.2f,
					Color.white, 334, currentRotation, 256, 10));
			currentRotation = (float)Math.PI/2;
			cooldownRemain += shootCooldown;
		}
	}

}