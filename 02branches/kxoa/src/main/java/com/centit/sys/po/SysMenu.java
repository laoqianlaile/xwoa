package com.centit.sys.po;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: 梁清洁
 * Date: 11-6-30 上午11:28
 */
public class SysMenu {
    private String id;//菜单的id
    private String name;//菜单名称
    private String url;//指向的URL
    private String parentId;
    private long order;//同级菜单的排序
    private List<SysMenu> subMenus;

    public List<SysMenu> getSubMenus() {
        return subMenus;
    }

    public void setSubMenus(List<SysMenu> subMenus) {
        this.subMenus = subMenus;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }



    public long getOrder() {
        return order;
    }

    public void setOrder(Long order) {

        this.order = (order==null)?0:order;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append("Menu");
        sb.append("{id='").append(id).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", url='").append(url).append('\'');
        sb.append(", parentId='").append(parentId).append('\'');
        sb.append(", order=").append(order);
        sb.append(", subMenus=").append(subMenus);
        sb.append('}');
        return sb.toString();
    }
}
