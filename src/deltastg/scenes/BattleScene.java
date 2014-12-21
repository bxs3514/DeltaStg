/** 基于世界之境(http://sekai.agrp.info)世界观的一个简易Stg
 * 作者：大地无敌、Marco、贝祥舜
 * Some Rights Reserved.
 */
package deltastg.scenes;

import java.util.*;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;
import org.lwjgl.input.*;

import deltastg.*;
import deltastg.combat.*;
import deltastg.combat.battlers.*;


/** 战斗画面
 * @author 大地无敌
 * 最后修改2012-12-27
 */
public class BattleScene extends Scene {
	private GameWorld gameWorld = new GameWorld();
	private WendyDarian wendyDarian;
	private float totalTime = 0;
	private float respawnTime = 4;
	private float currentRespawn = 0;
	private Random random = new Random();
	private BOSS boss;
	private String win = "Fly to the Sky!";
    private String fail = "Try again!\n" +
    		"按[Esc]重来";
    public boolean isRestarting = false;
    Texture head = Renderer.LoadPNG("res/head.png");
	
	public BattleScene(GameRunner gameRunner) {
		super(gameRunner);
		super.setBackground(Renderer.LoadPNG("res/background.png"));
		wendyDarian = new WendyDarian(new Vector2f(640,500), 1,gameWorld);
		gameWorld.addBattler(wendyDarian);
		gameWorld.setPlayer(wendyDarian);
		gameWorld.addBattler(new Enemy1(new Vector2f(1330,0),2,gameWorld,(float)Math.PI*4/5));
	}


	/* (non-Javadoc)
	 * @see deltastg.Scene#update(float)
	 */
	@Override
	public void update(float timeSpan) {
		
		super.update(timeSpan);
		totalTime+=timeSpan;
		gameWorld.update(timeSpan);
		
		if(totalTime<60)
		{
			//刷怪
			if(totalTime<=5)
			{
			
			}
			else if(totalTime <=20)
			{
				respawnTime = 2;
			}
			else if(totalTime <=40)
			{
				respawnTime = 1.5f;
			}
			else if(totalTime <= 60)
			{
				respawnTime = 0.8f;
			}
			currentRespawn+=timeSpan;
			if(currentRespawn>respawnTime)
			{
				currentRespawn-=respawnTime;
				newEnemy();
			}
		}
		else if(boss==null)
		{
			boss = new BOSS(new Vector2f(640,-110), 3, gameWorld);
			gameWorld.addBattler(boss);
		}
	}
	
	


	/* (non-Javadoc)
	 * @see deltastg.Scene#draw(float)
	 */
	@Override
	public void draw(float timeSpan) {
		
		super.draw(timeSpan);
		
		gameWorld.draw(timeSpan);
		
		if(wendyDarian!=null)
		{
			Renderer.drawImage(head, 0, Renderer.getHeight()-150, Color.white);
			Renderer.Drawquad(150, Renderer.getHeight()-34,(int)(720f*(wendyDarian.gethP()/wendyDarian.getMaxHP())), 24,Color.orange);
		}
		if(boss!=null)
		{
			Renderer.Drawquad(150, 10,(int)(720f*(boss.gethP()/boss.getMaxHP())), 34, Color.orange);
		}
		
		if(wendyDarian!=null && wendyDarian.isRemoving())
		{ 
			Renderer.currentFont.drawString(601, 351, fail,Color.black);
			Renderer.currentFont.drawString(600, 350, fail,Color.white);
			if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE))
			{
				this.isRestarting = true;
			}
		}
		else if(boss!=null && boss.isRemoving())
		{
			Renderer.currentFont.drawString(601, 351, win,Color.black);
			Renderer.currentFont.drawString(600, 350, win,Color.white);
		}

	}
	
	private void newEnemy()
	{
		int t;
		if (totalTime<=20)
			t = random.nextInt()%3;
		else t = random.nextInt()%6;
		switch (t)
		{
		case 0:
			gameWorld.addBattler(new Enemy1(new Vector2f(Renderer.getWidth()+50,random.nextFloat()*Renderer.getHeight()/2),2,gameWorld,(float)Math.PI*4/5));
			break;
		case 1:
			gameWorld.addBattler(new Enemy1(new Vector2f(-50,random.nextFloat()*Renderer.getHeight()/2),2,gameWorld,(float)Math.PI*1/5));
			break;
		case 2:
			gameWorld.addBattler(new Enemy1(new Vector2f(random.nextFloat()*Renderer.getWidth(),-50),2,gameWorld,(float)Math.PI*random.nextFloat()));
			break;
		case 3:
			gameWorld.addBattler(new Enemy2(new Vector2f(Renderer.getWidth()+50,random.nextFloat()*Renderer.getHeight()/2),2,gameWorld,(float)Math.PI*9/10));
			break;
		case 4:
			gameWorld.addBattler(new Enemy2(new Vector2f(-50,random.nextFloat()*Renderer.getHeight()/2),2,gameWorld,(float)Math.PI/10));
			break;
		case 5:
			gameWorld.addBattler(new Enemy2(new Vector2f(random.nextFloat()*Renderer.getWidth(),-50),2,gameWorld,(float)Math.PI/2));
			break;
		}
		
	}


	@Override
	public boolean isRemoving() {
		// TODO Auto-generated method stub
		return isRestarting;
	}

	
	
}
