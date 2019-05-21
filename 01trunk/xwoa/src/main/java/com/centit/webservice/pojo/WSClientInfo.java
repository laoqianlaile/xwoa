/**
 * 
 */
package com.centit.webservice.pojo;

/**
 *客户端调用参数传递
 * 
 * @author ljy
 * @create 2013-10-9
 * @version
 */
public class WSClientInfo {
    /**
     * 服务请求地址
     */
    private String endPoint;

    /**
     * 接口认证用户名
     */
    private String userName;

    /**
     * 接口认证密码
     */
    private String password;

    /**
     * 接口传输参数（XML类型）
     */
    private String params;

    /**
     * 数据对象
     */
    private Object obj;

    public String getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
}
