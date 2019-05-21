package com.centit.stat.po.html.table;

import java.util.ArrayList;
import java.util.List;

import com.centit.stat.po.html.AbstractCHtmlComponent;

/**
 * 表格内容组件
 * 
 * @author zk
 * @create 2013-6-8
 * @version 
 */
public abstract class AbstractCTableBody extends AbstractCHtmlComponent{
    
    /**
     * tr集合
     */
    private List<CTableLine> lines;
    
    /**
     * 添加单元格
     * 
     * @param cell
     */
    public void addCell(CTableCell cell) {
        int length = getLines().size();
        
        getLines().get(length - 1).addCell(cell);
    }
    
    /**
     * 新增一行
     */
    public void addLine() {
        getLines().add(new CTableLine());
    }
    
    public List<CTableLine> getLines() {
        if (null == lines) {
            lines = new ArrayList<CTableLine>();
            lines.add(new CTableLine());
        }
        
        return lines;
    }
}
