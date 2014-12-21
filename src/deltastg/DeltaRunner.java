/** ��������֮��(http://sekai.agrp.info)����۵�һ������Stg
 * ���ߣ�����޵С�Marco������˴
 * Some Rights Reserved.
 */
package deltastg;

import deltastg.scenes.*;

/** �̳�GameRunner���� ������Ϸ�Ļ���
 * @author ����޵�
 * ����޸�2012-12-27
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
