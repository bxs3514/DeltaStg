/** 基于世界之境(http://sekai.agrp.info)世界观的一个简易Stg
 * 作者：大地无敌、贝祥舜、Marco
 * Some Rights Reserved.
 */
package deltastg.base;


/** 可更新物件的接口
 * @author 大地无敌
 * 最后修改12/25/2012
 */
public interface IUpdatable {
	/** 更新物件逻辑
	 * @param timeSpan 与上一帧的时间间隔
	 */
	void update(float timeSpan);
	/** 得到物件是否执行update
	 * @return 物件是否执行update
	 */
	boolean getIsActived();
}
