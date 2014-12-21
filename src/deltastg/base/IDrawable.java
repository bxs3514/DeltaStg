/** 基于世界之境(http://sekai.agrp.info)世界观的一个简易Stg
 * 作者：大地无敌、贝祥舜、Marco
 * Some Rights Reserved.
 */
package deltastg.base;


/** 可绘出物件的接口
 * @author 大地无敌
 * 最后修改12/25/2012
 */
public interface IDrawable {
	/** 绘出物件
	 * @param timeSpan 与上一帧的时间间隔
	 */
	void draw(float timeSpan);
	/** 得到物件是否执行draw
	 * @return 物件是否执行draw
	 */
	boolean getIsVisible();
}
