package com.centit.app.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.centit.app.po.Innermsg;
import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.dao.CodeBook;

public class InnermsgDao extends BaseDaoImpl<Innermsg>
	{
    private static final long serialVersionUID = 1L;
	public Map<String, String> getFilterField() {
		if( filterField == null){
			filterField = new HashMap<String, String>();

			filterField.put("msgcode" , CodeBook.EQUAL_HQL_ID);

			filterField.put("sender" , CodeBook.LIKE_HQL_ID);

			filterField.put("senddate" , CodeBook.LIKE_HQL_ID);

			filterField.put("replymsgcode" , CodeBook.LIKE_HQL_ID);

			filterField.put("msgtitle" , CodeBook.LIKE_HQL_ID);

			filterField.put("receivetype" , CodeBook.LIKE_HQL_ID);

			filterField.put("receive" , CodeBook.LIKE_HQL_ID);

			filterField.put("msgstate" , CodeBook.LIKE_HQL_ID);

			filterField.put("msgcontent" , CodeBook.LIKE_HQL_ID);
			
			filterField.put("nodelete" , "msgstate <> ?");//增加过滤删除信息
			filterField.put(CodeBook.ORDER_BY_HQL_ID , "senddate desc");

		}
		return filterField;
	} 
	   /**
     * 生成内部消息编码
     * @return long
     */
    public long getNextMsgCode(){
        String sNo = getNextValueOfSequence("S_MSGCODE");
        return Long.valueOf(sNo);
    }
    
    /**
     * 查询某条消息的所有回复信息
     * @param replymsgcode
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<Innermsg> listMsgsByReplycode(Long replymsgcode){
        String baseHQL = "from Innermsg where replymsgcode = ? order by senddate asc";
        return (List<Innermsg>)findObjectsByHql(baseHQL,replymsgcode);
    }
    
    /**
     * 根据接受人查询邮件
     * @param replymsgcode
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<Innermsg> listMsgsByReceive(String receive){
        String baseHQL = "from Innermsg where receive = ? order by senddate desc";
        return (List<Innermsg>)findObjectsByHql(baseHQL,receive);
    }
	
    @Override
    public void saveObject(Innermsg innermsg) {
        if(innermsg.getMsgcode() == null || innermsg.getMsgcode() == 0){
            innermsg.setMsgcode(getNextMsgCode());
        }
        super.saveObject(innermsg);
    }
    
}
