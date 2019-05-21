/**
 * 
 */
package com.centit.webservice.pojo;




/**
 * 客户端调用参数传递
 * 
 * @author ljy
 * @create 2013-10-9
 * @version
 */
public class CentitWebServiceInfo {

    private CentitWSReturninfo returninfo;


    private Object bizdata;

    public CentitWSReturninfo getReturninfo() {
        return returninfo;
    }

    public void setReturninfo(CentitWSReturninfo returninfo) {
        this.returninfo = returninfo;
    }

    public Object getBizdata() {
        return bizdata;
    }

    public void setBizdata(Object bizdata) {
        this.bizdata = bizdata;
    }




}
