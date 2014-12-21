/** 基于世界之境(http://sekai.agrp.info)世界观的一个简易Stg
 * 作者：大地无敌、Marco、贝祥舜
 * Some Rights Reserved.
 */
package deltastg;

/** 程序入口类
 * @author 大地无敌
 * 最后修改12/24/2012
 */
public class Program {

	/** 程序的入口点
	 * @param args
	 */
	public static void main(String[] args) {
		GameRunner game = new DeltaRunner();
		game.start();
	}

}
