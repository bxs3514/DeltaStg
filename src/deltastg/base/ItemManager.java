/** 基于世界之境(http://sekai.agrp.info)世界观的一个简易Stg
 * 作者：大地无敌、贝祥舜、Marco
 * Some Rights Reserved.
 */
package deltastg.base;

import java.util.*;

/** 物件管理器，模版类，update和draw时会执行下属物件的update和draw
 * @author 大地无敌
 * 最早我是在XNA中实现的这样的一个管理类
 * 感觉不错
 * 于是也移植到JAVA大作业里面来
 * 最后修改12/25/2012
 */
public class ItemManager<T> implements IUpdatable, IDrawable{
	
	/** 创建一个ItemManager
	 */
	public ItemManager()
	{
		memberList = new ArrayList<T>();
	}
	
	/** 创建一个ItemManager
	 * @param defaultSize 预估成员表大小
	 */
	public ItemManager(int defaultSize)
	{
		memberList = new ArrayList<T>(defaultSize);
	}
	
    /** 添加某一物，将在下一次update时加入
     * @param item
     */
    public void add(T item)
    {
        addingList.add(item);
        latestAddedMember = item;
    }

	
	@Override
	public void update(float timeSpan) {
		//进行子物件的添加与更新
        for(T o : addingList)//遍历待添加物体
        {
            memberList.add(o);
        }
        addingList.clear();//清空添加队列

        for (T o : memberList)
        {

            if (isItemRemoving(o))//如果一个物体将要被移除
            {
            	removingList.add(o);//则将它加到移除队列里
                continue;
            }

            
            if (o instanceof IUpdatable&&((IUpdatable)o).getIsActived())
            {
                ((IUpdatable)o).update(timeSpan);//执行更新
            }
        }
        
        for(T o : removingList)//遍历待移除物体
        {
        	memberList.remove(o);//移除这个物体
        }
        removingList.clear();//清空移除队列
		
	}
	
	
	@Override
	public void draw(float timeSpan) {
        for (T o : memberList)
        {
            if (!isItemRemoving(o))
                if (o instanceof IDrawable&&((IDrawable)o).getIsVisible())
                    ((IDrawable)o).draw(timeSpan);//如果该物体不是将被移除的物体且含有IDrawable接口则执行draw
        }
	}
	
	
	/** 获取逻辑上现有成员数
	 * @return 逻辑上现有成员数
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
	
	public T latestAddedMember; //最后一个添加的物体
	private ArrayList<T> memberList;//下属成员表
    private ArrayList<T> removingList = new ArrayList<T>(10);//待移除的项
    private ArrayList<T> addingList = new ArrayList<T>(10);//待添加的项
  
    
    /** 判断一个物体是不是可以被移除
     * @param item 待判断的物体
     * @return 物体可否被移除
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
