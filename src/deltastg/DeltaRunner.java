/** 基于世界之境(http://sekai.agrp.info)世界观的一个简易Stg
 * 作者：大地无敌、Marco、贝祥舜
 * Some Rights Reserved.
 */
package deltastg;

import deltastg.scenes.*;

/** 继承GameRunner的类 运行游戏的基础
 * @author 大地无敌
 * 最后修改2012-12-27
 */
public class DeltaRunner extends GameRunner {
	private IntroducingScene introducingScene;
	private BattleScene battleScene;
	/* (non-Javadoc)
	 * @see deltastg.GameRunner#initialize()
	 */
	@Override
	protected void initialize() {
		super.initialize();
		introducingScene = new IntroducingScene(this);
		battleScene = new BattleScene(this);
		sceneManager.add(introducingScene);
		sceneManager.add(battleScene);
		
		introducingScene.open();
	}
	/* (non-Javadoc)
	 * @see deltastg.GameRunner#update()
	 */
	@Override
	protected void update() {
		if(battleScene.isRemoving())
		{
			battleScene = new BattleScene(this);
			sceneManager.add(battleScene);
			battleScene.open();
		}
		super.update();

	}
	
	public void startBattle()
	{
		introducingScene.close();
		battleScene.open();
	}
}
