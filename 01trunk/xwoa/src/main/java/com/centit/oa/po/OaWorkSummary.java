package com.centit.oa.po;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.centit.sys.util.DateTimeUtil;

public class OaWorkSummary implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String value;
    private int size = 0;
    private boolean checkempty = false;
    private List<OaArrangeweek> arrangeweekJsons = new ArrayList<OaArrangeweek>();

    public OaWorkSummary() {

    }

    public OaWorkSummary(Date addDays) {
        setId(new SimpleDateFormat("yyyy-MM-dd").format(addDays));
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
        if (StringUtils.isNotBlank(id)) {
            try {
                Date date = new SimpleDateFormat("yyyy-MM-dd").parse(id);
                setValue(new SimpleDateFormat("M月d日").format(date) + "<br/>"
                        + DateTimeUtil.getDayOfWeekShort(date));
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<OaArrangeweek> getArrangeweekJsons() {
        return arrangeweekJsons;
    }

    public void setArrangeweekJsons(List<OaArrangeweek> oaArrangeweeks) {
        if (oaArrangeweeks.size() > 0) {
            this.arrangeweekJsons = oaArrangeweeks;
            setSize(arrangeweekJsons.size());
            setCheckempty(false);
        } else {
            setSize(1);
            setCheckempty(true);
        }
    }

    public boolean isCheckempty() {
        return checkempty;
    }

    public void setCheckempty(boolean checkempty) {
        this.checkempty = checkempty;
    }

}
