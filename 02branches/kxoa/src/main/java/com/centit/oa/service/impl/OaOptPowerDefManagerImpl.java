package com.centit.oa.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.oa.dao.OaOptPowerDefDao;
import com.centit.oa.po.OaOptPowerDef;
import com.centit.oa.service.OaOptPowerDefManager;
import com.centit.sys.util.IdGen;

public class OaOptPowerDefManagerImpl extends BaseEntityManagerImpl<OaOptPowerDef>
 implements OaOptPowerDefManager {
    private static final long serialVersionUID = 1L;
	private OaOptPowerDefDao dao;
	public void setOaOptPowerDefDao(OaOptPowerDefDao pDao)
	{
		setBaseDao(pDao);
		this.dao = pDao;
	}
	
	public OaOptPowerDef getObject(OaOptPowerDef object) {

		if(object==null)
			return null;
		OaOptPowerDef newObj = dao.getObjectById(object.getOptcode());
		if (newObj==null){
			newObj = object;
			newObj.setOptcode(dao.getNextOptCode());
		}
		return newObj;
	}

	public List<OaOptPowerDef> getOptDefByOptId(String optid)
	{
		return dao.getOptDefByOptId(optid);
	}

    @Override
    public String getNextOptCode() {
        return dao.getNextOptCode();
    }

    @Override
    public void saveDocViewPower(OaOptPowerDef initDef,
            String[] optcodelist) {
        List<OaOptPowerDef>   oaOptPowerDefList=new ArrayList<OaOptPowerDef>();
        //optname:${row.datavalue};optid:${role.datacode};optmethod:${row.datacode}
        for(int i=0; i<optcodelist.length; i++){
            String[]  optparam=optcodelist[i].split(";");
            String  optname=null!=optparam&&optparam.length>0?optparam[0]:"optname";
            String  optlevel=null!=optparam&&optparam.length>1?optparam[1]:"optlevel";
            String  optmethod=null!=optparam&&optparam.length>2?optparam[2]:"optmethod";
            OaOptPowerDef  opt=new OaOptPowerDef();
            opt.setOptcode(IdGen.uuid());//随记数
            opt.setOptname(optname);
            opt.setOptlevel(optlevel);//行政级别
            opt.setOptmethod(optmethod);
            opt.setOptid(initDef.getOptid());
            opt.setOptdesc(initDef.getOptdesc());
            oaOptPowerDefList.add(opt);
        }
        dao.saveOptPower(oaOptPowerDefList) ;
    }

    @Override
    public void deleteDocViewPower(String optid) {
        dao.deleteDocViewPower( optid);
        
    }
    

}
