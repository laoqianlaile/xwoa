package com.centit.stat.twodimenform;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;

import com.centit.core.dao.SQLUtils;
import com.centit.core.dao.SQLWithNamedParamsCallBack;
import com.centit.stat.po.QueryColumn;
import com.centit.stat.po.QueryCondition;
import com.centit.stat.po.QueryModel;
import com.centit.stat.po.html.table.CTableCell;
import com.centit.stat.po.html.table.CTableLine;
import com.centit.stat.po.html.table.CTablePanel;
import com.centit.support.compiler.Lexer;
import com.centit.support.utils.DatetimeOpt;
import com.centit.support.utils.JSONOpt;
import com.centit.support.utils.StringBaseOpt;
import com.centit.support.utils.StringRegularOpt;
import com.opensymphony.xwork2.ActionContext;
import com.centit.sys.service.CodeRepositoryUtil;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.struts2.ServletActionContext;

/**
 * TODO Class description should be added
 * 
 * @author zk
 * @create 2013-12-19
 * @version 
 */
public class FormDataModel {
    //模块名称，对应统计模块
    private static final String ALL="N";//全选对应的key
    private String modelName;
    /**
     * 2 ： 二维表  3 ：同比分析 4：环比分析 5：交叉制表
     */
    private String modelType;
    private List<QueryColumn> columns;
    private List<QueryCondition> conditions;
    private List<Object[]>  formData;
    private List<Object[]> paramData;
    private List<Object[]> crossTableColumns;
    private String[]    rowLogicUrl;
   
    private Integer paramCount;
    private String formNameFormat;
    private String querySql;
    private String formName;
    private String resultName;
    private String rowDrawChart; //是否按行 T 画统计图 F 不画
    private long drawChartBeginCol; //画图起始列
    private long drawChartEndCol; //画图结束列
    private String additionRow; // 0 : 没有  1 合计  2 均值  3 合计 和 均值
    private String rowLogic;
    private String logicUrlFormat;
    private String logicUrl;
    //private long rowLogicValue;
    private String[] isShowColumn;    
    private int retStartPos;
    private int retMaxSize;
    private String columnSql;
    private String isTree;
    private String exportData;
    
    public String getExportData() {
        return exportData;
    }

    public void setExportData(String exportData) {
        this.exportData = exportData;
    }

    public String getColumnSql() {
        return columnSql;
    }

    public void setColumnSql(String columnSql) {
        this.columnSql = columnSql;
    }

    public String getIsTree() {
		return isTree;
	}

	public void setIsTree(String isTree) {
		this.isTree = isTree;
	}

	/**
     * 页面展示表格
     */
    private CTablePanel tablePanel;
    
    public CTablePanel getTablePanel() {
        return tablePanel;
    }

    public void setTablePanel(CTablePanel tablePanel) {
        this.tablePanel = tablePanel;
    }

    /**
     * 字段中不同数据类别个数
     */
    private int rowGroupSum;
    private int colGroupSum;
    private int dataAnalyseSum;

    
    public String getLogicUrlFormat() {
        return logicUrlFormat;
    }

    public void setLogicUrlFormat(String logicUrlFormat) {
        this.logicUrlFormat = logicUrlFormat;
    }

    public String getLogicUrl() {
        return logicUrl;
    }

    public void setLogicUrl(String logicUrl) {
        this.logicUrl = logicUrl;
    }


    
    public FormDataModel()
    {
        formData=null;
        paramCount=0;
        conditions=null;
        columns=null;
        additionRow = "0";
        rowDrawChart = "F";
        rowLogic = null;
        retStartPos = -1;
        retMaxSize = -1;
        rowGroupSum = 0;
        colGroupSum = 0;
        dataAnalyseSum = 0;       
    }
    
    public FormDataModel(String modelName,String fromNameFormat)
    {
        this();
        this.modelName = modelName; 
        this.formNameFormat = fromNameFormat; 
    }

    
    public String getFormNameFormat() {
        return formNameFormat;
    }

    public void setFormNameFormat(String fromNameFormat) {
        this.formNameFormat = fromNameFormat;
    }

    public Integer getParamCount() {
        return paramCount;
    }

    public void setParamCount(Integer paramCount) {
        this.paramCount = paramCount;
    }
    
    public String getModelName() {
        return modelName;
    }
    
    public void setModelName(String modelName) {
        this.modelName = modelName;
    }
    
    public List<QueryColumn> getColumns() {
        if(columns==null)
            columns = new ArrayList<QueryColumn>();
        return columns;
    }
    public QueryColumn getColumn(String sName) {
        if(columns==null)
            return null;
        for(QueryColumn col:columns){
            if(sName.equals(col.getColName()))
                return col;
        }
        return null;
    }   
    public List<QueryCondition> getConditions() {
        if(conditions==null)
            conditions = new ArrayList<QueryCondition>();
        return conditions;
    }
    
    public QueryCondition getCondition(String sName) {
        if(conditions==null)
            return null;
        for(QueryCondition cond:conditions){
            if(sName.equals(cond.getCondName()))
                return cond;
        }
        return null;
    }


    public void setConditions(List<QueryCondition> conditions) {
        this.conditions = conditions;
    }
    
    public void setColumns(List<QueryColumn> columns) {
        this.columns = columns;
    }
    
    public List<Object[]> getFormData() {
        return formData;
    }
    
    public int getRowCount()
    {
        if(formData==null)
            return 0;
        return formData.size();
    }
    
    
    public void setFormData(List<Object[]> fromData) {
        this.formData = fromData;
    }
    
    public String getQuerySql() {
        return querySql;
    }

    public void setQuerySql(String querySql) {
        this.querySql = querySql;
    }
    
    public static String pretreatmentQuerySql(String querySql,Map<String,String> condMap){
        Lexer varMorp = new Lexer();
        varMorp.setFormula(querySql);
        String sDesFormula="";
        int nPos=0;
        String sWord = varMorp.getAWord();

        while( sWord!=null && ! sWord.equals("") ){
            if( sWord.equals("$")){
                int nEndPos = varMorp.getCurrPos();
                sWord = varMorp.getAWord();
                if(sWord.equals("{")){
                    sWord = varMorp.getStringUntil("}");
                    sDesFormula += querySql.substring(nPos, nEndPos-1);
                    nPos = varMorp.getCurrPos();
                    if(! StringBaseOpt.isNvl(sWord)){
                        String sFilterSql = condMap.get(sWord.trim()) ;
                        if(sFilterSql!=null)
                            sDesFormula += condMap.get(sWord.trim()) + " ";
                        else 
                            sDesFormula += " ";
                    }
                    sWord = varMorp.getAWord();
                 }
            }else               
                sWord = varMorp.getAWord();
        }
        if(nPos<querySql.length())
            sDesFormula += querySql.substring(nPos);
        return sDesFormula;
    }
    
    /**
     * 根据查询目录生成sql语句
     * @return
     */
    public SQLWithNamedParamsCallBack makeStatQuery() {
        return makeStatQuery(false);
    }
 
    /**
     * 根据查询目录生成sql语句,添加分页信息 
     * @return
     */
    public SQLWithNamedParamsCallBack makeStatQuery(boolean isPaging) {
        
        Map<String,Object> params = new HashMap<String,Object>();
        Map<String,String> condMap = new HashMap<String,String>();
        for(QueryCondition cond : getConditions()){
            String sName = cond.getCondName();
            if(cond.getCondValue()!=null && !"".equals(cond.getCondValue())){
                String filterSql = "";
                if(condMap.get(cond.getCondPlace())!=null )
                     filterSql= condMap.get(cond.getCondPlace())+" "+ cond.getCondFilterSql();
                else
                    filterSql=cond.getCondFilterSql(); 
                if (cond.getCondValue().equals(ALL))
                    filterSql = "";
                condMap.put(cond.getCondPlace(), filterSql);
            }
            params.put(sName, cond.getCondValue());
        }   
        SQLWithNamedParamsCallBack statQuery = new SQLWithNamedParamsCallBack();
        String sql = pretreatmentQuerySql(this.querySql,condMap);
        sql = addOrderParam(sql, params);
        statQuery.setQuerySql(sql);
        statQuery.setParameters(params);
        if(isPaging){
            statQuery.setRetStartPos(retStartPos);
            statQuery.setRetMaxSize(retMaxSize);
        }
        
        System.out.println("-----------------查询语句------------------");
        System.out.println(sql);
        System.out.println(params);
        
        return statQuery;
    }
    
    
    /**
     * 根据查询目录生成sql语句
     * 
     * @param sql
     * @return
     */
    public SQLWithNamedParamsCallBack makeStatQuery(String sql) {
        Map<String,Object> params = new HashMap<String,Object>();
        Map<String,String> condMap = new HashMap<String,String>();
        for(QueryCondition cond : getConditions()){
            String sName = cond.getCondName();
            if(cond.getCondValue()!=null && !"".equals(cond.getCondValue())){
                String filterSql = "";
                if(condMap.get(cond.getCondPlace())!=null )
                     filterSql= condMap.get(cond.getCondPlace())+" "+ cond.getCondFilterSql();
                else
                    filterSql=cond.getCondFilterSql(); 
                if (cond.getCondValue().equals(ALL))
                    filterSql = "";
                condMap.put(cond.getCondPlace(), filterSql);
            }
            params.put(sName, cond.getCondValue());
        }   
        SQLWithNamedParamsCallBack statQuery = new SQLWithNamedParamsCallBack();
        sql = pretreatmentQuerySql(sql,condMap);
        sql = addOrderParam(sql, params);
        statQuery.setQuerySql(sql);
        statQuery.setParameters(params);
        
        System.out.println("---------------列语句-----------------");
        System.out.println(sql);
        System.out.println(params);
        
        return statQuery;
    }
    
    /**
     * 
     * 根据查询目录生成对比分析语句
     * @param compareType   3 同比分析  4 环比分析 0 其他
     * @return
     */
    public String makeCondCompareValue(String compareType, int offset)  {
        String dateStr = "";
        
        for(QueryCondition cond : getConditions()){
            // 年比较
            if("3".equals(cond.getCompareType())){
                try { 
                    //如果是年份直接减1，否则转化为日志剪掉一年
                    if(StringRegularOpt.isNumber(cond.getCondValue())){
                        Integer preV = Integer.valueOf(cond.getCondValue());
                        preV = preV + offset;
                        cond.setCondValue( preV.toString()); 
                    }else
                        cond.setCondValue(DatetimeOpt.convertDatetimeToString(
                            DatetimeOpt.addYears(DatetimeOpt.smartPraseDate( cond.getCondValue()), offset) ));
                    
                    dateStr += cond.getCondValue() + "年";
                }catch (NumberFormatException e) {

                }
            }
            // 月比较
            else if("4".equals(cond.getCompareType())){
                try {
                    cond.setCondValue(DatetimeOpt.convertDateToString(
                            DatetimeOpt.addMonths(DatetimeOpt.smartPraseDate(cond.getCondValue()), offset), "yyyy-MM"));
                }catch (NumberFormatException e) {

                } 
                dateStr = cond.getCondValue();
            }
        }   
        
        return dateStr;
    }
    
    /**
     * 添加排序
     * 
     * @param sql
     * @param params
     * @return
     */
    private static String addOrderParam(String sql, Map<String, Object> params) {
        String sqlSen=sql;
        String orderField = (String)params.get("orderField");
        String orderDirection = (String)params.get("orderDirection");
        if (!StringUtils.isEmpty(orderField)) {
            sqlSen = SQLUtils.removeOrderBy(sql);
            sqlSen += " order by " + orderField + " " + orderDirection;
        } 
        
        return sqlSen;
    }

    /*  public void setStatQuery(SQLWithNamedParamsCallBack statQuery) {
        this.statQuery = statQuery;
    }
    
    public void setStatQuery(String sql) {
        this.statQuery = new SQLWithNamedParamsCallBack(sql);
    }
    
    public void setStatQuery(String sql,Map<String,Object> values) {
        this.statQuery = new SQLWithNamedParamsCallBack(sql,values);
    }
        */
    public String getFormName() {
        return formName;
    }
    
    public void setFormName(String fromName) {
        this.formName = fromName;
    }
    
    public String getResultName() {
        return resultName;
    }
    
    public void setResultName(String resultName) {
        this.resultName = resultName;
    }
    
    public String getRowDrawChart() {
        return rowDrawChart;
    }
    /**
     * 是否按行 T 画统计图 F 不画
     * @param rowDarwChart
     */
    public void setRowDrawChart(String rowDarwChart) {
        this.rowDrawChart = rowDarwChart;
    }

    public long getDrawChartBeginCol() {
        return drawChartBeginCol;
    }

    public void setDrawChartBeginCol(long darwChartBeginCol) {
        this.drawChartBeginCol = darwChartBeginCol;
    }

    public long getDrawChartEndCol() {
        return drawChartEndCol;
    }

    public void setDrawChartEndCol(long darwChartEndCol) {
        this.drawChartEndCol = darwChartEndCol;
    }

    public String getAdditionRow() {
        return additionRow;
    }
    /**
     * 0 : 没有  1 合计  2 均值  3 合计 和 均值
     * @param additionRow
     */
    public void setAdditionRow(String additionRow) {
        this.additionRow = additionRow;
    }
    
    public String getRowLogic() {
        return rowLogic;
    }

    public void setRowLogic(String rowLogic) {
        this.rowLogic = rowLogic;
    }

    public FormDataModel copyModelMetaData(FormDataModel dataModel){
        modelName = dataModel.getModelName();
        modelType = dataModel.getModelType();
        columns = dataModel.getColumns();
        querySql = dataModel.getQuerySql();
        formNameFormat = dataModel.getFormNameFormat();
        resultName = dataModel.getResultName();
        conditions = dataModel.getConditions();
        rowDrawChart = dataModel.getRowDrawChart();
        drawChartBeginCol = dataModel.getDrawChartBeginCol();
        drawChartEndCol = dataModel.getDrawChartEndCol();
        additionRow = dataModel.getAdditionRow();               
        rowLogic =  dataModel.getRowLogic();
        logicUrlFormat = dataModel.getLogicUrlFormat();
        paramCount = dataModel.getParamCount();
        retStartPos = dataModel.getRetStartPos();
        retMaxSize = dataModel.getRetMaxSize();
        rowGroupSum = dataModel.getRowGroupSum();
        colGroupSum = dataModel.getColGroupSum();
        dataAnalyseSum = dataModel.getDataAnalyseSum();
        columnSql = dataModel.getColumnSql();
        isTree = dataModel.getIsTree();
        return this;
    }
    
    public void loadFromQueryModel(QueryModel qm){
        modelName = qm.getModelName();
        modelType = qm.getModelType();
        setQuerySql(qm.getQuerySql());
        formNameFormat = qm.getFormNameFormat();
        resultName = qm.getResultName();
        //conditions = qm.getConditions();
        rowDrawChart = qm.getRowDrawChart();
        drawChartBeginCol = qm.getDrawChartBeginCol();
        drawChartEndCol = qm.getDrawChartEndCol();
        additionRow = qm.getAdditionRow();  
        rowLogic =  qm.getRowLogic();
        logicUrlFormat = qm.getLogicUrl();
        columnSql = qm.getColumnSql();
        rowGroupSum = 0;
        colGroupSum = 0;
        dataAnalyseSum = 0; 
        isTree = qm.getIsTree();
        
        if(columns==null)
            columns= new ArrayList<QueryColumn>();
        else
            columns.clear();
        for(QueryColumn col:qm.getQueryColumns()){
            //R 行头  C 列头  D 数值
            if("R".equals(col.getShowType())){
                rowGroupSum++;
            }else if("C".equals(col.getShowType())){
                colGroupSum++;
            }else
                dataAnalyseSum++;
                
            columns.add( new QueryColumn().copy(col) );
        }
        
        if(conditions==null)
            conditions= new ArrayList<QueryCondition>();
        else
            conditions.clear();
        for(QueryCondition qc:qm.getQueryConditions())
            conditions.add( new QueryCondition().copy(qc));
     }
  
    public void makeParamByFormat(String userCode ,String unitCode) {
        //替换标题
        formName = formNameFormat;
        logicUrl = logicUrlFormat;
        
        if(conditions!=null)
             for(QueryCondition cond:conditions){
                String sName = cond.getCondName();
                String sValue = cond.getCondValue();
                
                if(sValue!=null && ! "".equals(sValue)){
                    if(formName!=null){
                      formName = formName.replaceAll(sName, sValue);
                    }
                    if(logicUrl!=null){
                      logicUrl = logicUrl.replaceAll( "var_"+sName , sValue);
                    }  
                }else{
                    if(formName!=null){
                        formName = formName.replaceAll(sName, sValue);
                     }
                    if(logicUrl!=null){
                        logicUrl = logicUrl.replaceAll( "var_"+sName , "");
                     }  
                }
            } 
        if(logicUrl!=null){
            logicUrl = logicUrl.replaceAll( "var_usercode" , userCode);
            logicUrl = logicUrl.replaceAll( "var_unitcode" , unitCode);
        }
        
        if(columns==null)
            return;
        int n=0;
        for(QueryColumn col:columns){
            col.setColProperty("col"+n);
            n++;
        }   
    }
    
    public JSONObject toJsonData()
    {
        JSONObject objJson = new JSONObject();
        JSONOpt.setAttribute(objJson, "modelName", modelName);
        JSONOpt.setAttribute(objJson, "modelType", modelType);
        JSONOpt.setAttribute(objJson, "formName", formName);
        JSONOpt.setAttribute(objJson, "rowDarwChart", rowDrawChart);
        JSONOpt.setAttribute(objJson, "darwChartBeginCol", drawChartBeginCol);
        JSONOpt.setAttribute(objJson, "darwChartEndCol", drawChartEndCol);
        JSONOpt.setAttribute(objJson, "additionRow", additionRow);
        JSONOpt.setAttribute(objJson, "rowLogic", rowLogic);
        JSONOpt.setAttribute(objJson, "logicUrl", logicUrl);
        
        JSONOpt.setAttribute(objJson, "rowGroupSum", rowGroupSum);
        JSONOpt.setAttribute(objJson, "colGroupSum", colGroupSum);
        JSONOpt.setAttribute(objJson, "dataAnalyseSum", dataAnalyseSum);

        int i;
//        if(conditions!=null){
//            i=0;
//            for(QueryCondition cond:conditions){
//                JSONOpt.setAttribute(objJson, "conditions["+i+"]", cond.toJsonData());
//                i++;
//            }
//        }
        
        if(columns!=null){
            i=0;
            for(QueryColumn col:columns){
                JSONOpt.setAttribute(objJson, "columns["+i+"]", col.toJsonData());
                i++;
            }
        }
        
        if(formData!=null){
            i=0;
            JSONArray jarrayData = new JSONArray();
            for(Object[] objs:formData){
                int j=0;
                JSONArray jarray = new JSONArray();
                for(Object obj:objs)
                    jarray.add(j++,obj==null?"":obj.toString());
                jarrayData.add(i++,jarray);
            }
            objJson.element("fromData", jarrayData);
        }

        return objJson;
    }
    
    public JSONObject getJsonFormData() {
        return toJsonData();
    }

    public String[] getIsShowColumn() {
        return isShowColumn;
    }

    public void setIsShowColumn(String[] isShowColumn) {
        this.isShowColumn = isShowColumn;
    }

    public String[] getRowLogicUrl() {
        return rowLogicUrl;
    }

    public void setRowLogicUrl(String[] rowLogicUrl) {
        this.rowLogicUrl = rowLogicUrl;
    }

    public List<Object[]> getParamData() {
        if (null == paramData) {
            paramData = new ArrayList<Object[]> ();
        }
        
        return paramData;
    }

    public void setParamData(List<Object[]> paramData) {
        this.paramData = paramData;
    }

    public int getRetStartPos() {
        return retStartPos;
    }

    public void setRetStartPos(int retStartPos) {
        this.retStartPos = retStartPos;
    }

    public int getRetMaxSize() {
        return retMaxSize;
    }

    public void setRetMaxSize(int retMaxSize) {
        this.retMaxSize = retMaxSize;
    }
    public int getRowGroupSum() {
        return rowGroupSum;
    }

    public void setRowGroupSum(int rowGroupSum) {
        this.rowGroupSum = rowGroupSum;
    }

    public int getColGroupSum() {
        return colGroupSum;
    }

    public void setColGroupSum(int colGroupSum) {
        this.colGroupSum = colGroupSum;
    }

    public int getDataAnalyseSum() {
        return dataAnalyseSum;
    }

    public void setDataAnalyseSum(int dataAnalyseSum) {
        this.dataAnalyseSum = dataAnalyseSum;
    }
    /**
     * 2 ： 二维表  3 ：同比分析 4：环比分析 5：交叉制表
     * @return
     */
    public String getModelType() {
        return modelType;
    }
    /**
     * 2 ： 二维表  3 ：同比分析 4：环比分析 5：交叉制表
     * @param modelType
     */
    public void setModelType(String modelType) {
        this.modelType = modelType;
    }

    public int getDataColumnCount() {
        if("3".equals(modelType) || "4".equals(modelType))
            return getColumns().size() * 2 - rowGroupSum; 
        if("5".equals(modelType))
            return  rowGroupSum + getCrossTableColumns().size() *  dataAnalyseSum;
        return getColumns().size();
    }

    public List<Object[]> getCrossTableColumns() {
        if (null == crossTableColumns) {
            crossTableColumns = new ArrayList<Object[]> ();
        }
        return crossTableColumns;
    }

    public void setCrossTableColumns(List<Object[]> crossTableColumns) {
        this.crossTableColumns = crossTableColumns;
    }
}
