/** 基于世界之境(http://sekai.agrp.info)世界观的一个简易Stg
 * 作者：大地无敌、Marco、贝祥舜
 * Some Rights Reserved.
 */
package deltastg.combat;

/** 玩家控制物件的接口
 * @author 大地无敌
 * 最后修改2012-12-27
 */
public interface IPlayer {
	/** 获取玩家1的输入并进行操作，在update时执行 */
	public void playerInput();
}
