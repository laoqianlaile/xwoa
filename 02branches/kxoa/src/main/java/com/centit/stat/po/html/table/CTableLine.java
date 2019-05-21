package com.centit.stat.po.html.table;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.centit.stat.po.html.AbstractCHtmlComponent;

/**
 * 表格行组件，后台数据和表格TR之间的转换
 * 
 * @author zk
 * @create 2013-6-8
 * @version 
 */
public class CTableLine extends AbstractCHtmlComponent {
    public static final String TABLE_LINE_MODEL = "<tr${id}${class}${style}>${cells}</tr>";
    
    /**
     * 单元格集合
     */
    private List<CTableCell> cells;
    
    /**
     * 添加单元格
     * 
     * @param cell
     */
    public void addCell(CTableCell cell) {
        getCells().add(cell);
    }
    
    public List<CTableCell> getCells() {
        if (null == cells) {
            cells = new ArrayList<CTableCell>();
        }
        
        return cells;
    }

    @Override
    public String getHtml() {
        String strStyle = (StringUtils.isEmpty(getCssStyle()) ? "" : " style='" + getCssStyle() + "'");
        String strClass = (StringUtils.isEmpty(getCssClass()) ? "" : " class='" + getCssClass() + "'");
        String strId = (StringUtils.isEmpty(getId()) ? "" : " id='" + getId() + "'");
        
        StringBuilder sb = new StringBuilder();
        for (CTableCell cell : getCells()) {
            sb.append(cell.getHtml());
        }
        
        // 替换返回
        return TABLE_LINE_MODEL.replace("${id}", strId)
                .replace("${class}", strClass)
                .replace("${style}", strStyle)
                .replace("${cells}", sb.toString());
    }
}
