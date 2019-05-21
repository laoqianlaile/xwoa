package com.centit.stat.po.html.table;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import com.centit.stat.po.QueryColumn;
import com.centit.stat.po.html.AbstractCHtmlComponent;
import com.centit.support.utils.DatetimeOpt;
import com.centit.sys.service.CodeRepositoryUtil;

/**
 * 表格单元格组件，后台数据和前台TD之间的转换
 * 
 * @author zk
 * @create 2013-6-8
 * @version 
 */
public class CTableCell extends AbstractCHtmlComponent {
    
    public static final String TARGET_BLANK = "_Blank";
    
    public static final String TARGET_NAVTAB = "navtab";
    
    public static final String TARGET_DIALOG = "dialog";
    
    /**
     * 检查style是否含有display属性
     */
    public static final Pattern PATTERN_DISPLAY = Pattern.compile("display:(.*?);");
    
    /**
     * HTML模板 不带链接
     */
    public static final String TABLE_CELL_MODEL = "<td${id}${class}${style}${rowspan}${colspan} data-value='${value}'>${data}</td>";
    
    /**
     * HTML模板 带链接
     */
    public static final String TABLE_CELL_MODEL_LINK 
        = "<td ${id}${class}${style}${rowspan}${colspan} data-value='${value}'><a href='${href}'${target} external='true'>${data}</a></td>";
    
    /**
     * HTML模板 不带链接
     */
    public static final String TABLE_HEAD_CELL_MODEL = "<th${id}${class}${style}${rowspan}${colspan}>${data}</th>";
    
    /**
     * HTML模板 带链接
     */
    public static final String TABLE_HEAD_CELL_MODEL_LINK 
        = "<th ${id}${class}${style}${rowspan}${colspan}><a href='${href}'${target} external='true'>${data}</a></th>";
    
    /**
     * 存储属性
     */
    private Map<String, String> property;
    
    /**
     * 单元格原始值
     */
    private Object value;
    
    /**
     * 单元格展示值
     */
    private String displayValue;
    
    /**
     * 单元格链接
     */
    private String href;
    
    /**
     * 单元格链接target
     */
    private String linkTarget;
    
    /**
     * 是否展示
     */
    private boolean display = true;
    
    private boolean isHead = false;
    
    private int rowspan;
    
    private int colspan;
    
    /**
     * 和报表功能结合，创建表格单元格
     * @param orignValue
     * @param col
     * @param params
     * @return
     */
    public static CTableCell createTableCell(Object orignValue, QueryColumn col, Map<String, Object> params) {
        CTableCell cell = new CTableCell();
        
        
        String hrefValue = "";
        if (null != orignValue && !StringUtils.isEmpty(""+orignValue)) {
            hrefValue = parseHref(col, params);
        }
        
        String showValue = parseDisplayValue(orignValue, col);

        cell.setValue(orignValue);
        cell.setDisplayValue(showValue);
        cell.setHref(hrefValue);
        cell.setLinkTarget(col.getLinkType());
        
        cell.setDisplay(parseDisplay(col)); 
        
        // 设置属性
        for(Entry<String, Object> entry : params.entrySet()) {
            String key = entry.getKey().replaceAll(":", "").trim();
            Object value = entry.getValue();
            
            if (null == value) continue;
            
            cell.setProperty(key, value.toString().trim());
        }
        
        return cell;
    }
    
    /**
     * 和报表功能结合，创建表格单元格
     * 
     * @param value 原始值
     * @param col 列
     * @param params 行参数
     * @return
     */
    /**
     * @param orignValue
     * @param col
     * @param params
     * @return
     */
    public static CTableCell createTableHeadCell(String orignValue, QueryColumn col) {
        CTableCell cell = new CTableCell();
        
        cell.setValue(orignValue);
        cell.setDisplayValue(parseDisplayValue(orignValue, col));
        
        cell.setDisplay(parseDisplay(col)); 
        cell.setLinkTarget(col.getLinkType());
        cell.setHead(true);
        
        return cell;
    }
    
    /**
     * 和报表功能结合，创建表格单元格
     * 
     * @param value 原始值
     * @param col 列
     * @param params 行参数
     * @return
     */
    /**
     * @param orignValue
     * @param col
     * @param params
     * @return
     */
    public static CTableCell createTableHeadCell(String orignValue) {
        CTableCell cell = new CTableCell();
        
        cell.setValue(orignValue);
        cell.setDisplayValue(orignValue);
        cell.setHead(true);
        
        return cell;
    }
    
    /**
     * 解析是否展示
     * 
     * @param col
     * @return
     */
    private static boolean parseDisplay(QueryColumn col) {
        // 为空或者值为 T 均显示
        if (null == col || StringUtils.isBlank(col.getIsShow()) || "T".equals(col.getIsShow())) {
            return true;
        }
        
        return false;
    }

    /**
     * 根据查询数值替换参数
     * 
     * @param col
     * @param params
     * @return
     */
    private static String parseHref(QueryColumn col, Map<String, Object> params) {
        if (null == col || StringUtils.isBlank(col.getColLogic())) {
            return null;
        }
        
        String colLogic = col.getColLogic();
        
        for(Entry<String, Object> entry : params.entrySet()) {
            if (null == entry.getValue()) continue;
            
            colLogic = colLogic.replaceAll(entry.getKey(), ""+entry.getValue());
        }
        
        return colLogic;
    }

    /**
     * 根据列类型 解析展示格式
     * 
     * @param orignValue
     * @param col
     * @return
     */
    private static String parseDisplayValue(Object orignValue, QueryColumn col) {
        String value = (null != orignValue) ? orignValue.toString() : "";
        
        if (StringUtils.isEmpty(value)) {
            return (null != col.getDefaultValue()) ? col.getDefaultValue() : "";
        }
        
        if (null == col || StringUtils.isBlank(col.getColType())) {
            return value;
        }
        
        String colType = col.getColType();
        String format = col.getColFormat();
        
        // 字符串
        if ("S".equals(colType)) {
            return value;
        }
        
        try {
            // 数字 货币
            if ("N".equals(colType) || "C".equals(colType)) {
                if (StringUtils.isEmpty(format)) {
                    return value;
                }
                
                DecimalFormat df = new DecimalFormat(format);
                return df.format(Double.parseDouble(value));
            }
            
            // 百分比
            if ("P".equals(colType)) {
                DecimalFormat df = new DecimalFormat(format + "%");
                return df.format(Double.parseDouble(value));
            }
            
        }
        catch(Exception e) {
            return value;
        }
        
        // 日期
        if ("D".equals(colType)) {
            //TODO 以后需要更好的判断方法
            if ("合计".equals(value)) {
                return value;
            }
            
            return DatetimeOpt.convertDateToString(DatetimeOpt.smartPraseDate(value), format);
        }
        
        // 单位
        if ("U".equals(colType)) {
            return CodeRepositoryUtil.getValue("unitcode", value);
        }
        
        // 部门
        if ("E".equals(colType)) {
            return CodeRepositoryUtil.getValue("depno", value);
        }
        
        // 权利类型
        if ("O".equals(colType)) {
            return CodeRepositoryUtil.getValue("ITEM_TYPE", value);
        }
        
        // 权利状态
        if ("T".equals(colType)) {
            return CodeRepositoryUtil.getValue("QL_State", value);
        }
        
        // 数据字典
        if ("L".equals(colType)) {
            return CodeRepositoryUtil.getValue(format, value);
        }
        
        // HTML
        if ("H".equals(colType)) {
            return format;
        }
        
        return value;
    }

    public boolean isHead() {
        return isHead;
    }

    public void setHead(boolean isHead) {
        this.isHead = isHead;
    }

    public boolean isDisplay() {
        return display;
    }

    public void setDisplay(boolean display) {
        this.display = display;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getDisplayValue() {
        return displayValue;
    }

    public void setDisplayValue(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getLinkTarget() {
        return linkTarget;
    }

    public void setLinkTarget(String linkTarget) {
        this.linkTarget = linkTarget;
    }

    public int getRowspan() {
        return rowspan;
    }

    public void setRowspan(int rowspan) {
        this.rowspan = rowspan;
    }

    public int getColspan() {
        return colspan;
    }

    public void setColspan(int colspan) {
        this.colspan = colspan;
    }

    public Map<String, String> getProperty() {
        if (null == property) {
            property = new HashMap<String, String>();
        }
        
        return property;
    }

    public void setProperty(String key, String value) {
        this.property = getProperty();
        
        property.put(key, value);
    }

    @Override
    public String getHtml() {
        if (!display) {
            Matcher m = PATTERN_DISPLAY.matcher(getCssStyle());
            
            if (!m.matches()) {
                setCssStyle(getCssStyle() + "display:none;");
            }
            else {
                setCssStyle(m.replaceAll("display:none;"));
            }
        }
        
        String strRowspan = (0 == rowspan ? "" : " rowspan='" + rowspan + "'");
        String strColspan = (0 == colspan ? "" : " colspan='" + colspan + "'");
        String strTarget = (StringUtils.isEmpty(linkTarget) ? "" : " target='" + linkTarget + "'");
        String strStyle = (StringUtils.isEmpty(getCssStyle()) ? "" : " style='" + getCssStyle() + "'");
        String strClass = (StringUtils.isEmpty(getCssClass()) ? "" : " class='" + getCssClass() + "'");
        String strId = (StringUtils.isEmpty(getId()) ? "" : " id='" + getId() + "'");
        String strValue = (null != getValue()) ? getValue()+"" : "";
        
        String html;
        
        // 纯数据不带链接
        if (StringUtils.isEmpty(href)) {
            
            if (isHead) {
                html = TABLE_HEAD_CELL_MODEL;
            }
            else {
                html = TABLE_CELL_MODEL;
            }
        }
        else {
            if (isHead) {
                html = TABLE_HEAD_CELL_MODEL_LINK.replace("${href}", href)
                        .replace("${target}", strTarget);
            }
            else {
                html = TABLE_CELL_MODEL_LINK.replace("${href}", href)
                        .replace("${target}", strTarget);
            }
        }
        
        // 替换返回
        return html = html.replace("${id}", strId)
                .replace("${class}", strClass)
                .replace("${style}", strStyle)
                .replace("${rowspan}", strRowspan)
                .replace("${colspan}", strColspan)
                .replace("${data}", displayValue)
                .replace("${value}", strValue);
    }
}
