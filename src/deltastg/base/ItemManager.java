/** ��������֮��(http://sekai.agrp.info)����۵�һ������Stg
 * ���ߣ�����޵С�����˴��Marco
 * Some Rights Reserved.
 */
package deltastg.base;

import java.util.*;

/** �����������ģ���࣬update��drawʱ��ִ�����������update��draw
 * @author ����޵�
 * ����������XNA��ʵ�ֵ�������һ��������
 * �о�����
 * ����Ҳ��ֲ��JAVA����ҵ������
 * ����޸�12/25/2012
 */
public class ItemManager<T> implements IUpdatable, IDrawable{
	
	/** ����һ��ItemManager
	 */
	public ItemManager()
	{
		memberList = new ArrayList<T>();
	}
	
	/** ����һ��ItemManager
	 * @param defaultSize Ԥ����Ա���С
	 */
	public ItemManager(int defaultSize)
	{
		memberList = new ArrayList<T>(defaultSize);
	}
	
    /** ���ĳһ�������һ��updateʱ����
     * @param item
     */
    public void add(T item)
    {
        addingList.add(item);
        latestAddedMember = item;
    }

	
	@Override
	public void update(float timeSpan) {
		//�������������������
        for(T o : addingList)//�������������
        {
            memberList.add(o);
        }
        addingList.clear();//�����Ӷ���

        for (T o : memberList)
        {

            if (isItemRemoving(o))//���һ�����彫Ҫ���Ƴ�
            {
            	removingList.add(o);//�����ӵ��Ƴ�������
                continue;
            }

            
            if (o instanceof IUpdatable&&((IUpdatable)o).getIsActived())
            {
                ((IUpdatable)o).update(timeSpan);//ִ�и���
            }
        }
        
        for(T o : removingList)//�������Ƴ�����
        {
        	memberList.remove(o);//�Ƴ��������
        }
        removingList.clear();//����Ƴ�����
		
	}
	
	
	@Override
	public void draw(float timeSpan) {
        for (T o : memberList)
        {
            if (!isItemRemoving(o))
                if (o instanceof IDrawable&&((IDrawable)o).getIsVisible())
                    ((IDrawable)o).draw(timeSpan);//��������岻�ǽ����Ƴ��������Һ���IDrawable�ӿ���ִ��draw
        }
	}
	
	
	/** ��ȡ�߼������г�Ա��
	 * @return �߼������г�Ա��
	 */
	public int count()
	{
		return memberList.size()+addingList.size()+removingList.size();
	}
	
	@Override
	public boolean getIsVisible() {
		return true;
	}
	@Override
	public boolean getIsActived() {
		return true;
	}
	
	public ArrayList<T> getClonedList()
	{
		return (ArrayList<T>)memberList.clone();
	}
	
	public T latestAddedMember; //���һ����ӵ�����
	private ArrayList<T> memberList;//������Ա��
    private ArrayList<T> removingList = new ArrayList<T>(10);//���Ƴ�����
    private ArrayList<T> addingList = new ArrayList<T>(10);//����ӵ���
  
    
    /** �ж�һ�������ǲ��ǿ��Ա��Ƴ�
     * @param item ���жϵ�����
     * @return ����ɷ��Ƴ�
     */
    private boolean isItemRemoving(T item)
    {
        if (item instanceof IRemovable)
        {
            return ((IRemovable)item).isRemoving();
        }
        else return false;
    }


}
