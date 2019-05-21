package com.centit.dispatchdoc.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.dao.CodeBook;
import com.centit.core.dao.HQLUtils;
import com.centit.core.utils.PageDesc;
import com.centit.dispatchdoc.po.VUserTransaffairDcyjGr;
import com.centit.support.utils.StringRegularOpt;

public class VUserTransaffairDcyjGrDao extends BaseDaoImpl<VUserTransaffairDcyjGr>
	{
		private static final long serialVersionUID = 1L;
		public static final Log log = LogFactory.getLog(VUserTransaffairDcyjGrDao.class);
		
	public Map<String, String> getFilterField() {
		if( filterField == null){
			filterField = new HashMap<String, String>();

			filterField.put("transId" , CodeBook.EQUAL_HQL_ID);


			filterField.put("leaderName" , CodeBook.LIKE_HQL_ID);

			filterField.put("shipDate" , CodeBook.LIKE_HQL_ID);

			filterField.put("unitName" , CodeBook.LIKE_HQL_ID);

			filterField.put("leaderNote" , CodeBook.LIKE_HQL_ID);

			filterField.put("djId" , CodeBook.LIKE_HQL_ID);

			filterField.put("optId" , CodeBook.LIKE_HQL_ID);

			filterField.put("flowCode" , CodeBook.LIKE_HQL_ID);

			filterField.put("flowInstId" , CodeBook.LIKE_HQL_ID);

			filterField.put("transAffairNo" , CodeBook.LIKE_HQL_ID);

			filterField.put("transaffairname" , CodeBook.LIKE_HQL_ID);

			filterField.put("transAffairQueryKey" , CodeBook.LIKE_HQL_ID);

			filterField.put("bizstate" , CodeBook.LIKE_HQL_ID);

			filterField.put("biztype" , CodeBook.LIKE_HQL_ID);

			filterField.put("orgcode" , CodeBook.LIKE_HQL_ID);

			filterField.put("orgname" , CodeBook.LIKE_HQL_ID);

			filterField.put("headunitcode" , CodeBook.LIKE_HQL_ID);

			filterField.put("headusercode" , CodeBook.LIKE_HQL_ID);

			filterField.put("content" , CodeBook.LIKE_HQL_ID);

			filterField.put("powerid" , CodeBook.LIKE_HQL_ID);

			filterField.put("powername" , CodeBook.LIKE_HQL_ID);

			filterField.put("solvestatus" , CodeBook.LIKE_HQL_ID);

			filterField.put("solvetime" , CodeBook.LIKE_HQL_ID);

			filterField.put("solvenote" , CodeBook.LIKE_HQL_ID);

			filterField.put("createuser" , CodeBook.LIKE_HQL_ID);

			filterField.put("createdate" , CodeBook.LIKE_HQL_ID);

			filterField.put("caseNo" , CodeBook.LIKE_HQL_ID);

			filterField.put("sendArchiveNo" , CodeBook.LIKE_HQL_ID);

			filterField.put("acceptArchiveNo" , CodeBook.LIKE_HQL_ID);

			filterField.put("riskType" , CodeBook.LIKE_HQL_ID);

			filterField.put("riskDesc" , CodeBook.LIKE_HQL_ID);

			filterField.put("riskResult" , CodeBook.LIKE_HQL_ID);

			filterField.put("isUpload" , CodeBook.LIKE_HQL_ID);

			filterField.put("userCode" , CodeBook.LIKE_HQL_ID);

			filterField.put("subItemType" , CodeBook.LIKE_HQL_ID);

			filterField.put("incomeDocNo" , CodeBook.LIKE_HQL_ID);

			filterField.put("incomeDeptType" , CodeBook.LIKE_HQL_ID);

			filterField.put("incomeDeptName" , CodeBook.LIKE_HQL_ID);

			filterField.put("zbUnitName" , CodeBook.LIKE_HQL_ID);

			filterField.put("zbUnitShortName" , CodeBook.LIKE_HQL_ID);

			filterField.put("hbUnitNames" , CodeBook.LIKE_HQL_ID);

			filterField.put("hbUnitShortNames" , CodeBook.LIKE_HQL_ID);

			filterField.put("docTitle" , CodeBook.LIKE_HQL_ID);

			filterField.put("fwh" , CodeBook.LIKE_HQL_ID);

			filterField.put("wjlx" , CodeBook.LIKE_HQL_ID);

			filterField.put("acceptDate" , CodeBook.LIKE_HQL_ID);

			filterField.put("zbcbrTransContent" , CodeBook.LIKE_HQL_ID);

			filterField.put("dispatchDocTitle" , CodeBook.LIKE_HQL_ID);


		}
		return filterField;
	} 
    public List<VUserTransaffairDcyjGr> listUserTransaffair(Map<String, Object> filterMap, PageDesc pageDesc) {
        List<Object> valueList = new ArrayList<Object>();
        StringBuffer hql = new StringBuffer(" from ").append(getClassTShortName()).append(" where 1=1");
        
        Map<String, String> filterField = this.getFilterField(); // dao中查询属性
        Set<String> keySet = filterMap.keySet(); // 所有的条件
        Set<String> removeKeySet = new HashSet<String>(); // 已经在In条件中存在，而需要移除的普通属性
        for (String key : keySet) {
            if (key.startsWith("IN_")) { // 需要拼接In语句的
                removeKeySet.add(key.substring(3));
            }
        }
        for (String key : removeKeySet) {
            if (filterMap.containsKey(key)) { // 移除已经转换成In语句条件的
                filterMap.remove(key);
            }
        }
        
        keySet = filterMap.keySet(); // 重新获取所有的key值
        for (String key : keySet) {
            String sqlFormat = null;
            if (filterField.containsKey(key)) {
                sqlFormat = filterField.get(key);
            } else if (filterField.containsKey(key.toUpperCase())) {
                sqlFormat = filterField.get(key.toUpperCase());
            }
            
            if (null != sqlFormat) { // 如果dao中的查询属性中存在
                Object value = filterMap.get(key);
                if (null == value || StringUtils.isBlank(value.toString())) { // 如果值为空或空白，则不做处理
                    continue;
                }
                
                if (key.startsWith(CodeBook.NO_PARAM_FIX)) { // 如果是不需要输入参数的（value必须为true或非0数字）
                    if (StringRegularOpt.isTrue(value.toString())) {
                        hql.append(" and ").append(sqlFormat);
                    }
                } else if (key.startsWith("IN_")) { // 需要拼接in语句（转化为多个等于条件并且使用or连接）
                    if (filterField.containsKey(key) || filterField.containsKey(key.toUpperCase())) {
                        String[] inCols = String.valueOf(filterMap.get(key)).split(",");
                        Set<String> colSet = new HashSet<String>();
                        for (int i=0; i<inCols.length; i++) {
                            if (StringUtils.isNotBlank(inCols[i])) {
                                colSet.add(inCols[i]);
                            }
                        }
                        if (!colSet.isEmpty()) {
                            String keyName = key.substring(3);
                            String prefix = " and (";
                            for (String colVal : colSet) {
                                hql.append(prefix).append(keyName).append(" = ?");
                                prefix = " or ";
                                valueList.add(colVal);
                            }
                            hql.append(")");
                        }
                    }
                } else {
                    if (CodeBook.LIKE_HQL_ID.equalsIgnoreCase(sqlFormat)) { // like语句
                        hql.append(String.format(" and %s like ? ", key));
                        valueList.add(HQLUtils.getMatchString(value.toString())); // 将空白符同时转为%
                    } else if (CodeBook.EQUAL_HQL_ID.equalsIgnoreCase(sqlFormat)) { // = 语句
                        hql.append(String.format(" and %s = ? ", key));
                        valueList.add(value.toString());
                    } else { // 自定义的语句
                        hql.append(" and ").append(sqlFormat);
                        String clearSql = sqlFormat.replaceAll("\\?", "");
                        for (int i=0; i<sqlFormat.length()-clearSql.length(); i++) {
                            valueList.add(value.toString());
                        }
                    }
                }
            }
        }
        if (filterMap.containsKey(CodeBook.SELF_ORDER_BY)) { // 自定义的查询条件
            hql.append(" order by ").append(filterMap.get(CodeBook.SELF_ORDER_BY));
        } else if (filterField.containsKey(CodeBook.ORDER_BY_HQL_ID)) { // 在dao中配置的默认查询语句
            hql.append(" order by ").append(filterField.get(CodeBook.ORDER_BY_HQL_ID));
        }
        
        if (null != pageDesc) {
            return listObjects(hql.toString(), valueList.toArray(), pageDesc);
        }
        return listObjects(hql.toString(), valueList.toArray());
    }
}
