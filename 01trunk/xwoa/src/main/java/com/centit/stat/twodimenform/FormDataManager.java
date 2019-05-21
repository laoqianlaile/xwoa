package com.centit.stat.twodimenform;

import java.util.List;

public interface FormDataManager {
    public FormDataModel getDataModel(String modelName);
    
    /**
     * 
     * @param formData 数据查询模块，根据这个结构中的设置查询数据
     * @param isPaging 是否查询数据总量，如果有分页时需要用
     * @return
     */
    public Integer queryFormData(FormDataModel formData,boolean isPaging);
    public Integer queryFormData(FormDataModel formData,boolean needSum,boolean isPaging);
    
    /**
     * 查询对比分析数据
     * @param formData
     * @return
     */
    public Integer queryCompareData(FormDataModel formData);
    public Integer queryCompareData(FormDataModel formData,boolean needSum);
    
    /**
     * 查询交叉制表数据
     * @param formData
     * @return
     */
    public Integer queryCrossData(FormDataModel formData);
    public Integer queryCrossData(FormDataModel formData,boolean needSum);
    /**
     * 执行原生的select sql查询语句
     * @param sql
     * @return
     */
    public List<Object[]> findDataBySql(String sql);
}
