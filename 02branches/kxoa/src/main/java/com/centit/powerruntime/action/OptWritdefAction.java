package com.centit.powerruntime.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts2.interceptor.ServletResponseAware;
import org.extremecomponents.table.limit.Limit;

import com.centit.core.action.BaseEntityExtremeAction;
import com.centit.core.utils.ExtremeTableUtils;
import com.centit.core.utils.LabelValueBean;
import com.centit.core.utils.PageDesc;
import com.centit.powerruntime.po.OptWritdef;
import com.centit.powerruntime.po.TemplateFile;
import com.centit.powerruntime.service.OptWritdefManager;
import com.centit.powerruntime.service.TemplateFileManager;
import com.centit.sys.po.FDatadictionary;

public class OptWritdefAction extends BaseEntityExtremeAction<OptWritdef> implements
        ServletResponseAware {
    private static final long serialVersionUID = 1L;
    private OptWritdefManager optWritdefMag;
    private TemplateFileManager templateFileManager;

    public void setTemplateFileManager(TemplateFileManager templateFileManager) {
        this.templateFileManager = templateFileManager;
    }

    public void setOptWritdefManager(OptWritdefManager basemgr) {
        optWritdefMag = basemgr;
        this.setBaseEntityManager(optWritdefMag);
    }

    private List<FDatadictionary> fDatalist = new ArrayList<FDatadictionary>();

    @Override
    public String edit() {
       
        if (object.getWritid() == null) {
            optWritdefMag.clearObjectProperties(object);
            List<FDatadictionary> tmlist = optWritdefMag.getItemTypesWithOutHave();
            this.getTemplateSelectList(fDatalist, tmlist, 22);
        } else {
            object = optWritdefMag.getObjectById(object.getWritid());
        }
        return EDIT;
    }

    @SuppressWarnings("unchecked")
    private void getTemplateSelectList(
            @SuppressWarnings("rawtypes") List templatelist,
            List<FDatadictionary> tmlist, int len) {

        if (templatelist == null) {
            return;
        }
        templatelist.clear();

        if (tmlist != null && tmlist.size() > 0) {
            for (int i = 0; i < tmlist.size(); i++) {
                FDatadictionary po = (FDatadictionary) tmlist.get(i);
                String id = po.getDatacode();
                String value = po.getDatavalue();
                if (value.length() > len) {
                    value = value.substring(0, len) + "...";
                }
                templatelist.add(new LabelValueBean(value, id));
            }
        }

    }

    public String doAjax() throws IOException {
        String tempType = request.getParameter("tempType");
        String squerySource = request.getParameter("squerySource");
        response.setContentType("text/html; charset=utf-8");

        // 根据模版类别、查询来源决定是否剔除已经设置的模版编码
        List<TemplateFile> list = templateFileManager.listTemplateByTypeNoWith(
                tempType, squerySource);

        JSONArray arr = JSONArray.fromObject(list);// 这个类是把list转换成json的格式
        // System.out.println(arr);
        response.getWriter().print(arr);

        return null;
    }

    @Override
    public String list() {
        @SuppressWarnings("unchecked")
        Map<Object, Object> paramMap = request.getParameterMap();
        resetPageParam(paramMap);
        Map<String, Object> filterMap = convertSearchColumn(paramMap);
        Limit limit = ExtremeTableUtils.getLimit(request);
        PageDesc pageDesc = ExtremeTableUtils.makePageDesc(limit);
        objList = optWritdefMag.listObjects(filterMap, pageDesc);
        totalRows = pageDesc.getTotalRows();
        return LIST;
    }
    
    HttpServletResponse response;

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    @Override
    public void setServletResponse(HttpServletResponse response) {
        this.response = response;
    }

    public List<FDatadictionary> getFDatalist() {
        return fDatalist;
    }

    public void setFDatalist(List<FDatadictionary> fDatalist) {
        this.fDatalist = fDatalist;
    }

    /*
     * public String getTempateName() { return tempateName; }
     * 
     * public void setTempateName(String tempateName) { this.tempateName =
     * tempateName; }
     */

}
