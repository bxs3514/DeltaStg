/** ��������֮��(http://sekai.agrp.info)����۵�һ������Stg
 * ���ߣ�����޵С�����˴��Marco
 * Some Rights Reserved.
 */
package deltastg.base;


/** �ɻ������Ľӿ�
 * @author ����޵�
 * ����޸�12/25/2012
 */
public interface IDrawable {
	/** ������
	 * @param timeSpan ����һ֡��ʱ����
	 */
	void draw(float timeSpan);
	/** �õ�����Ƿ�ִ��draw
	 * @return ����Ƿ�ִ��draw
	 */
	boolean getIsVisible();
}
