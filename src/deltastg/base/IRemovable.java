/** 基于世界之境(http://sekai.agrp.info)世界观的一个简易Stg
 * 作者：大地无敌、贝祥舜、Marco
 * Some Rights Reserved.
 */
package deltastg.base;


/** 可移除物件的接口
 * @author 大地无敌
 * 最后修改12/25/2012
 */
public interface IRemovable {
	/** 绘出物件
	 * @return 物件是否已经可以移除，如果是，则管理它的itemManager会在下次update时将它移除
	 */
	boolean isRemoving();
}
