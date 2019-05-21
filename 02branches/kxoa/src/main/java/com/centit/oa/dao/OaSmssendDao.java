package com.centit.oa.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.centit.core.dao.CodeBook;
import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.dao.HQLUtils;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.oa.po.OaSmssend;
import java.math.BigDecimal;

public class OaSmssendDao extends BaseDaoImpl<OaSmssend>
	{
		private static final long serialVersionUID = 1L;
		public static final Log log = LogFactory.getLog(OaSmssendDao.class);
		
	public Map<String, String> getFilterField() {
		if( filterField == null){
			filterField = new HashMap<String, String>();

			filterField.put("smsid" , CodeBook.EQUAL_HQL_ID);


			filterField.put("sendnum" , CodeBook.LIKE_HQL_ID);

			filterField.put("sendpeo" , CodeBook.LIKE_HQL_ID);

			filterField.put("acceptnum" , CodeBook.LIKE_HQL_ID);

			filterField.put("acceptpeo" , CodeBook.LIKE_HQL_ID);

			filterField.put("sendtime" , CodeBook.LIKE_HQL_ID);

			filterField.put("restoremessage" , CodeBook.LIKE_HQL_ID);

			filterField.put("content" , CodeBook.LIKE_HQL_ID);

			filterField.put("state" , CodeBook.EQUAL_HQL_ID);

			filterField.put("senderdepart" , CodeBook.LIKE_HQL_ID);

			filterField.put("acceptpeocode" , CodeBook.LIKE_HQL_ID);

			filterField.put("datasource" , CodeBook.LIKE_HQL_ID);

			filterField.put("sendpeocode" , CodeBook.LIKE_HQL_ID);
			
			filterField.put(CodeBook.ORDER_BY_HQL_ID, " sendtime desc");
			
			//短信发送条件
			filterField.put("NP_sendstate" , " ( state is null or (state != '0' and state != 'E') ) ");
			
			filterField.put("currentdatetime", " sendtime < to_timestamp(?,'yyyy-MM-dd hh24:mi:ss.ff')");
			//短信发送时允许的时间范围
			filterField.put("allowdatetime", " sendtime > to_timestamp(?,'yyyy-MM-dd')");
			//filterField.put(CodeBook.ORDER_BY_HQL_ID, " to_number(smsid) asc");
			
			
			
		}
		return filterField;
	} 
	
	 
	    /**
	     * 依据下行短信序列ID seqId获取短信
	     */
	 
	    @SuppressWarnings("unchecked")
	    public List<OaSmssend> getMsgBySeqId(String seqId) {
	        List<OaSmssend> ls = getHibernateTemplate().find(
	                "FROM OaSmssend  where sequenceId=?",
	                seqId);
	        return ls;
	    }
	    
	    /**
         * 下行短信的msgId，由企信通平台生成，用来标识一条短信获取短信
         */
     
        @SuppressWarnings("unchecked")
        public List<OaSmssend> getMsgByMsgId(String seqId) {
            List<OaSmssend> ls = getHibernateTemplate().find(
                    "FROM OaSmssend  where msgId=?",
                    seqId);
            return ls;
        }
        
        /**
         * 查询某用户当月手动发送的
         * @param usercode
         * @return
         */
        public Long getSentMsgs(String usercode){
            
            if(StringUtils.isNotBlank(usercode)){
                StringBuffer sb = new StringBuffer("select count(t.smsid) from oa_smssend t ")
                .append("where t.sendpeocode = '" + usercode + "' ")
                .append("and t.state = '0' ")
                .append("and t.datasource = 'R' ")
                .append("and to_char(t.sendtime, 'MM') = to_char(sysdate, 'MM')");
                
                BigDecimal b = ((List<BigDecimal>)findObjectsBySql(sb.toString())).get(0);
                return Long.parseLong(b.toString());
            }else {
                return Long.parseLong("0");
            }
        }
	
}
