/** ��������֮��(http://sekai.agrp.info)����۵�һ������Stg
 * ���ߣ�����޵С�����˴��Marco
 * Some Rights Reserved.
 */
package deltastg.base;


/** �ɸ�������Ľӿ�
 * @author ����޵�
 * ����޸�12/25/2012
 */
public interface IUpdatable {
	/** ��������߼�
	 * @param timeSpan ����һ֡��ʱ����
	 */
	void update(float timeSpan);
	/** �õ�����Ƿ�ִ��update
	 * @return ����Ƿ�ִ��update
	 */
	boolean getIsActived();
}
