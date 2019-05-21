package com.centit.oa.po;

import java.util.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * create by scaffold
 * 
 * @author codefan@hotmail.com
 */

public class OaCarinfo implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    private String djid;// 流水号

    private String carno;// 车牌号码
    private String isuse;// 启用标识
    private String brand;// 品牌
    private String modelType;// 型号
    private String carType;// 车辆类型
    private Long ratifyNumber;// 核定载人
    private String ratifyLoad;// 核定载重
    private String ratifyOil;// 核定油耗
    private String displacement;// 排气量
    private String oilLabel;// 用油标号
    private String frameNumber;// 车架号码
    private String engineNo;// 发动机号
    private String driver;// 配置司机
    private String usingNature;// 使用性质
    private String carItems;// 车内附属物品
    private String remark;// 备注说明
    private String depno;// 所属机构
    private String responsibleDep;// 责任部门
    private String responsiblePersion;// 责任人
    private byte[] carPicture;// 车辆图片
    private String carPictureName;// 图片名称
    private String creater;// 创建人
    private Date createtime;// 创建时间
    private Date motifytime;// 最后修改时间

    private String driveNo;// 行驶证号

    private Set<OaCarApply> oaCarApplys = null;// new ArrayList<OaCarApply>();

    private OaCommonType oaCommonType;
    private OaDriverInfo oaDriverInfo;
    private String rangeType;

    // Constructors
    /** default constructor */
    public OaCarinfo() {
    }

    /** minimal constructor */
    public OaCarinfo(String djid) {

        this.djid = djid;

    }
    

    /** full constructor */
    public OaCarinfo(String djid, String carno, String isuse, String brand,
            String modelType, String carType, Long ratifyNumber,
            String ratifyLoad, String ratifyOil, String displacement,
            String oilLabel, String frameNumber, String engineNo,
            String driver, String usingNature, String carItems, String remark,
            String depno, String responsibleDep, String responsiblePersion,
            byte[] carPicture, String carPictureName, String creater,
            Date createtime, Date motifytime, String driveNo) {

        this.djid = djid;

        this.carno = carno;
        this.isuse = isuse;
        this.brand = brand;
        this.modelType = modelType;
        this.carType = carType;
        this.ratifyNumber = ratifyNumber;
        this.ratifyLoad = ratifyLoad;
        this.ratifyOil = ratifyOil;
        this.displacement = displacement;
        this.oilLabel = oilLabel;
        this.frameNumber = frameNumber;
        this.engineNo = engineNo;
        this.driver = driver;
        this.usingNature = usingNature;
        this.carItems = carItems;
        this.remark = remark;
        this.depno = depno;
        this.responsibleDep = responsibleDep;
        this.responsiblePersion = responsiblePersion;
        this.carPicture = carPicture;
        this.carPictureName = carPictureName;
        this.creater = creater;
        this.createtime = createtime;
        this.motifytime = motifytime;
        this.driveNo = driveNo;
    }

    public String getRangeType() {
        return rangeType;
    }

    public void setRangeType(String rangeType) {
        this.rangeType = rangeType;
    }

    public String getDjid() {
        return this.djid;
    }

    public void setDjid(String djid) {
        this.djid = djid;
    }

    // Property accessors

    public String getCarno() {
        return this.carno;
    }

    public void setCarno(String carno) {
        this.carno = carno;
    }

    public String getIsuse() {
        return this.isuse;
    }

    public void setIsuse(String isuse) {
        this.isuse = isuse;
    }

    public String getBrand() {
        return this.brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModelType() {
        return this.modelType;
    }

    public void setModelType(String modelType) {
        this.modelType = modelType;
    }

    public String getCarType() {
        return this.carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public Long getRatifyNumber() {
        return this.ratifyNumber;
    }

    public void setRatifyNumber(Long ratifyNumber) {
        this.ratifyNumber = ratifyNumber;
    }

    public String getRatifyLoad() {
        return this.ratifyLoad;
    }

    public void setRatifyLoad(String ratifyLoad) {
        this.ratifyLoad = ratifyLoad;
    }

    public String getRatifyOil() {
        return this.ratifyOil;
    }

    public void setRatifyOil(String ratifyOil) {
        this.ratifyOil = ratifyOil;
    }

    public String getDisplacement() {
        return this.displacement;
    }

    public void setDisplacement(String displacement) {
        this.displacement = displacement;
    }

    public String getOilLabel() {
        return this.oilLabel;
    }

    public void setOilLabel(String oilLabel) {
        this.oilLabel = oilLabel;
    }

    public String getFrameNumber() {
        return this.frameNumber;
    }

    public void setFrameNumber(String frameNumber) {
        this.frameNumber = frameNumber;
    }

    public String getEngineNo() {
        return this.engineNo;
    }

    public void setEngineNo(String engineNo) {
        this.engineNo = engineNo;
    }

    public String getDriver() {
        return this.driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUsingNature() {
        return this.usingNature;
    }

    public void setUsingNature(String usingNature) {
        this.usingNature = usingNature;
    }

    public String getCarItems() {
        return this.carItems;
    }

    public void setCarItems(String carItems) {
        this.carItems = carItems;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDepno() {
        return this.depno;
    }

    public void setDepno(String depno) {
        this.depno = depno;
    }

    public String getResponsibleDep() {
        return this.responsibleDep;
    }

    public void setResponsibleDep(String responsibleDep) {
        this.responsibleDep = responsibleDep;
    }

    public String getResponsiblePersion() {
        return this.responsiblePersion;
    }

    public void setResponsiblePersion(String responsiblePersion) {
        this.responsiblePersion = responsiblePersion;
    }

    public byte[] getCarPicture() {
        return this.carPicture;
    }

    public void setCarPicture(byte[] carPicture) {
        this.carPicture = carPicture;
    }

    public String getCarPictureName() {
        return this.carPictureName;
    }

    public void setCarPictureName(String carPictureName) {
        this.carPictureName = carPictureName;
    }

    public String getCreater() {
        return this.creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public Date getCreatetime() {
        return this.createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getMotifytime() {
        return this.motifytime;
    }

    public void setMotifytime(Date motifytime) {
        this.motifytime = motifytime;
    }

    public String getDriveNo() {
        return driveNo;
    }

    public void setDriveNo(String driveNo) {
        this.driveNo = driveNo;
    }


    public Set<OaCarApply> getOaCarApplys() {
        if (this.oaCarApplys == null)
            this.oaCarApplys = new HashSet<OaCarApply>();
        return this.oaCarApplys;
    }

    public void setOaCarApplys(Set<OaCarApply> oaCarApplys) {
        this.oaCarApplys = oaCarApplys;
    }

    public void addOaCarApply(OaCarApply oaCarApply) {
        if (this.oaCarApplys == null)
            this.oaCarApplys = new HashSet<OaCarApply>();
        this.oaCarApplys.add(oaCarApply);
    }

    public void removeOaCarApply(OaCarApply oaCarApply) {
        if (this.oaCarApplys == null)
            return;
        this.oaCarApplys.remove(oaCarApply);
    }

    public OaCarApply newOaCarApply() {
        OaCarApply res = new OaCarApply();

        res.setCardjid(this.getDjid());

        return res;
    }

    /**
     * 替换子类对象数组，这个函数主要是考虑hibernate中的对象的状态，以避免对象状态不�?��的问�?
     * 
     */
    public void replaceOaCarApplys(List<OaCarApply> oaCarApplys) {
        List<OaCarApply> newObjs = new ArrayList<OaCarApply>();
        for (OaCarApply p : oaCarApplys) {
            if (p == null)
                continue;
            OaCarApply newdt = newOaCarApply();
            newdt.copyNotNullProperty(p);
            newObjs.add(newdt);
        }
        // delete
        boolean found = false;
        Set<OaCarApply> oldObjs = new HashSet<OaCarApply>();
        oldObjs.addAll(getOaCarApplys());

        for (Iterator<OaCarApply> it = oldObjs.iterator(); it.hasNext();) {
            OaCarApply odt = it.next();
            found = false;
            for (OaCarApply newdt : newObjs) {
                if (odt.getDjId().equals(newdt.getDjId())) {
                    found = true;
                    break;
                }
            }
            if (!found)
                removeOaCarApply(odt);
        }
        oldObjs.clear();
        // insert or update
        for (OaCarApply newdt : newObjs) {
            found = false;
            for (Iterator<OaCarApply> it = getOaCarApplys().iterator(); it
                    .hasNext();) {
                OaCarApply odt = it.next();
                if (odt.getDjId().equals(newdt.getDjId())) {
                    odt.copy(newdt);
                    found = true;
                    break;
                }
            }
            if (!found)
                addOaCarApply(newdt);
        }
    }

    public void copy(OaCarinfo other) {

        this.setDjid(other.getDjid());

        this.carno = other.getCarno();
        this.isuse = other.getIsuse();
        this.brand = other.getBrand();
        this.modelType = other.getModelType();
        this.carType = other.getCarType();
        this.ratifyNumber = other.getRatifyNumber();
        this.ratifyLoad = other.getRatifyLoad();
        this.ratifyOil = other.getRatifyOil();
        this.displacement = other.getDisplacement();
        this.oilLabel = other.getOilLabel();
        this.frameNumber = other.getFrameNumber();
        this.engineNo = other.getEngineNo();
        this.driver = other.getDriver();
        this.usingNature = other.getUsingNature();
        this.carItems = other.getCarItems();
        this.remark = other.getRemark();
        this.depno = other.getDepno();
        this.responsibleDep = other.getResponsibleDep();
        this.responsiblePersion = other.getResponsiblePersion();
        this.carPicture = other.getCarPicture();
        this.carPictureName = other.getCarPictureName();
        this.creater = other.getCreater();
        this.createtime = other.getCreatetime();
        this.motifytime = other.getMotifytime();

        this.oaCarApplys = other.getOaCarApplys();

        this.oaCommonType = other.getOaCommonType();
        this.oaDriverInfo = other.getOaDriverInfo();
        this.driveNo = other.getDriveNo();
        this.rangeType = other.getRangeType();
    }

    public void copyNotNullProperty(OaCarinfo other) {

        if (other.getDjid() != null)
            this.setDjid(other.getDjid());

        if (other.getCarno() != null)
            this.carno = other.getCarno();
        if (other.getIsuse() != null)
            this.isuse = other.getIsuse();
        if (other.getBrand() != null)
            this.brand = other.getBrand();
        if (other.getModelType() != null)
            this.modelType = other.getModelType();
        if (other.getCarType() != null)
            this.carType = other.getCarType();
        if (other.getRatifyNumber() != null)
            this.ratifyNumber = other.getRatifyNumber();
        if (other.getRatifyLoad() != null)
            this.ratifyLoad = other.getRatifyLoad();
        if (other.getRatifyOil() != null)
            this.ratifyOil = other.getRatifyOil();
        if (other.getDisplacement() != null)
            this.displacement = other.getDisplacement();
        if (other.getOilLabel() != null)
            this.oilLabel = other.getOilLabel();
        if (other.getFrameNumber() != null)
            this.frameNumber = other.getFrameNumber();
        if (other.getEngineNo() != null)
            this.engineNo = other.getEngineNo();
        if (other.getDriver() != null)
            this.driver = other.getDriver();
        if (other.getUsingNature() != null)
            this.usingNature = other.getUsingNature();
        if (other.getCarItems() != null)
            this.carItems = other.getCarItems();
        if (other.getRemark() != null)
            this.remark = other.getRemark();
        if (other.getDepno() != null)
            this.depno = other.getDepno();
        if (other.getResponsibleDep() != null)
            this.responsibleDep = other.getResponsibleDep();
        if (other.getResponsiblePersion() != null)
            this.responsiblePersion = other.getResponsiblePersion();
        if (other.getCarPicture() != null)
            this.carPicture = other.getCarPicture();
        if (other.getCarPictureName() != null)
            this.carPictureName = other.getCarPictureName();
        if (other.getCreater() != null)
            this.creater = other.getCreater();
        if (other.getCreatetime() != null)
            this.createtime = other.getCreatetime();
        if (other.getMotifytime() != null)
            this.motifytime = other.getMotifytime();
        if (other.getDriveNo() != null)
            this.driveNo = other.getDriveNo();
        if (other.getRangeType() != null)
            this.rangeType = other.getRangeType();
        this.oaCarApplys = other.getOaCarApplys();
    }

    public void clearProperties() {

        this.carno = null;
        this.isuse = null;
        this.brand = null;
        this.modelType = null;
        this.carType = null;
        this.ratifyNumber = null;
        this.ratifyLoad = null;
        this.ratifyOil = null;
        this.displacement = null;
        this.oilLabel = null;
        this.frameNumber = null;
        this.engineNo = null;
        this.driver = null;
        this.usingNature = null;
        this.carItems = null;
        this.remark = null;
        this.depno = null;
        this.responsibleDep = null;
        this.responsiblePersion = null;
        this.carPicture = null;
        this.carPictureName = null;
        this.creater = null;
        this.createtime = null;
        this.motifytime = null;
        this.driveNo = null;
        this.rangeType = null;
        this.oaCarApplys = new HashSet<OaCarApply>();
    }

    public OaCommonType getOaCommonType() {
        return oaCommonType;
    }

    public void setOaCommonType(OaCommonType oaCommonType) {
        this.oaCommonType = oaCommonType;
    }

    public OaDriverInfo getOaDriverInfo() {
        return oaDriverInfo;
    }

    public void setOaDriverInfo(OaDriverInfo oaDriverInfo) {
        this.oaDriverInfo = oaDriverInfo;
    }
}
