package org.extremecomponents.table.tag;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.extremecomponents.util.HtmlBuilder;

import com.centit.support.utils.HtmlFormUtils;

/**
 * 
 * 自定义标签
 * 用来将request作用域里所有attribute用input隐藏域渲染出来
 * 
 * @author lay
 * @create 2016年3月29日
 * @version
 */
public class ReqeustAttributeForm extends TagSupport{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String id;
    private String action;
    
    @Override
    public int doStartTag() throws JspException {
           
        HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
        
        JspWriter writer = this.pageContext.getOut();
        HtmlBuilder htmlBuilder = new HtmlBuilder(writer);
        
        htmlBuilder.form().method("post")
                          .id(TagUtils.evaluateExpressionAsString("id", id, this, pageContext))
                          .name(TagUtils.evaluateExpressionAsString("id", id, this, pageContext))
                          .action(TagUtils.evaluateExpressionAsString("action", action, this, pageContext))
                          .close();
        htmlBuilder.div().close();
        Enumeration<?> e = request.getAttributeNames();
        while( e.hasMoreElements())   {   
            String name = e.nextElement().toString();
            if(name.startsWith("s_")){
                Object value = request.getAttribute(name);
                htmlBuilder.input("hidden").name(name).value(HtmlFormUtils.getParameterString(value)).close();
            }
        }
       
        htmlBuilder.divEnd();
        htmlBuilder.formEnd();
       return EVAL_BODY_INCLUDE;
    }
    
    @Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }

    
    @Override
    public void release() {
        super.release();
        id = null;
        action = null;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
    
    
}
