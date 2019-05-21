package com.centit.powerbase.po;

import java.util.Date;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * create by scaffold
 * @author codefan@hotmail.com
 */ 

public class PcpunishItem implements java.io.Serializable {
	private static final long serialVersionUID =  1L;


	private String punishclassid;

	private String  itemId;
	private String  version;
	private String  punishclassname;
	private Long  depid;
	private String  punishclasscode;
	private Long  punishobject;
	private Long  isinuse;
	private String  punishbasiscontent;
	private String  punishbasis;
	private String  remark;
	private String  islawsuit;
	private String  isreconsider;
	private Set<PcpunishStandard> pcpunishStandards = null;// new ArrayList<PcpunishStandard>();
	private Set<Pcfreeumpiredegree> pcfreeumpiredegrees = null;// new ArrayList<Pcfreeumpiredegree>();

	// Constructors
	/** default constructor */
	public PcpunishItem() {
	}
	/** minimal constructor */
	public PcpunishItem(
		String punishclassid		
		,String  punishclassname,Long  depid,Long  isinuse) {
	
	
		this.punishclassid = punishclassid;		
	
		this.punishclassname= punishclassname; 
		this.depid= depid; 
		this.isinuse= isinuse; 		
	}

/** full constructor */
	public PcpunishItem(
	 String punishclassid		
	,String  itemId,String  version,String  punishclassname,Long  depid,String  punishclasscode,Long  punishobject,Long  isinuse,String  punishbasiscontent,String  punishbasis,String  remark,String  islawsuit,String  isreconsider) {
	
	
		this.punishclassid = punishclassid;		
	
		this.itemId= itemId;
		this.version= version;
		this.punishclassname= punishclassname;
		this.depid= depid;
		this.punishclasscode= punishclasscode;
		this.punishobject= punishobject;
		this.isinuse= isinuse;
		this.punishbasiscontent= punishbasiscontent;
		this.punishbasis= punishbasis;
		this.remark= remark;
		this.islawsuit= islawsuit;
		this.isreconsider= isreconsider;		
	}
	

  
	public String getPunishclassid() {
		return this.punishclassid;
	}

	public void setPunishclassid(String punishclassid) {
		this.punishclassid = punishclassid;
	}
	// Property accessors
  
	public String getItemId() {
		return this.itemId;
	}
	
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
  
	public String getVersion() {
		return this.version;
	}
	
	public void setVersion(String version) {
		this.version = version;
	}
  
	public String getPunishclassname() {
		return this.punishclassname;
	}
	
	public void setPunishclassname(String punishclassname) {
		this.punishclassname = punishclassname;
	}
  
	public Long getDepid() {
		return this.depid;
	}
	
	public void setDepid(Long depid) {
		this.depid = depid;
	}
  
	public String getPunishclasscode() {
		return this.punishclasscode;
	}
	
	public void setPunishclasscode(String punishclasscode) {
		this.punishclasscode = punishclasscode;
	}
  
	public Long getPunishobject() {
		return this.punishobject;
	}
	
	public void setPunishobject(Long punishobject) {
		this.punishobject = punishobject;
	}
  
	public Long getIsinuse() {
		return this.isinuse;
	}
	
	public void setIsinuse(Long isinuse) {
		this.isinuse = isinuse;
	}
  
	public String getPunishbasiscontent() {
		return this.punishbasiscontent;
	}
	
	public void setPunishbasiscontent(String punishbasiscontent) {
		this.punishbasiscontent = punishbasiscontent;
	}
  
	public String getPunishbasis() {
		return this.punishbasis;
	}
	
	public void setPunishbasis(String punishbasis) {
		this.punishbasis = punishbasis;
	}
  
	public String getRemark() {
		return this.remark;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}
  
	public String getIslawsuit() {
		return this.islawsuit;
	}
	
	public void setIslawsuit(String islawsuit) {
		this.islawsuit = islawsuit;
	}
  
	public String getIsreconsider() {
		return this.isreconsider;
	}
	
	public void setIsreconsider(String isreconsider) {
		this.isreconsider = isreconsider;
	}


	public Set<PcpunishStandard> getPcpunishStandards(){
		if(this.pcpunishStandards==null)
			this.pcpunishStandards = new HashSet<PcpunishStandard>();
		return this.pcpunishStandards;
	}

	public void setPcpunishStandards(Set<PcpunishStandard> pcpunishStandards) {
		this.pcpunishStandards = pcpunishStandards;
	}	

	public void addPcpunishStandard(PcpunishStandard pcpunishStandard ){
		if (this.pcpunishStandards==null)
			this.pcpunishStandards = new HashSet<PcpunishStandard>();
		this.pcpunishStandards.add(pcpunishStandard);
	}
	
	public void removePcpunishStandard(PcpunishStandard pcpunishStandard ){
		if (this.pcpunishStandards==null)
			return;
		this.pcpunishStandards.remove(pcpunishStandard);
	}
	
	public PcpunishStandard newPcpunishStandard(){
		PcpunishStandard res = new PcpunishStandard();
  
//		res.setPunishclassid(this.getPunishclassid());

		return res;
	}
	/**
	 * 替换子类对象数组，这个函数主要是考虑hibernate中的对象的状态，以避免对象状态不一致的问题
	 * 
	 */
	public void replacePcpunishStandards(List<PcpunishStandard> pcpunishStandards) {
		List<PcpunishStandard> newObjs = new ArrayList<PcpunishStandard>();
		for(PcpunishStandard p :pcpunishStandards){
			if(p==null)
				continue;
			PcpunishStandard newdt = newPcpunishStandard();
			newdt.copyNotNullProperty(p);
			newObjs.add(newdt);
		}
		//delete
		boolean found = false;
		Set<PcpunishStandard> oldObjs = new HashSet<PcpunishStandard>();
		oldObjs.addAll(getPcpunishStandards());
		
		for(Iterator<PcpunishStandard> it=oldObjs.iterator(); it.hasNext();){
			PcpunishStandard odt = it.next();
			found = false;
			for(PcpunishStandard newdt :newObjs){
				if(odt.getCid().equals( newdt.getCid())){
					found = true;
					break;
				}
			}
			if(! found)
				removePcpunishStandard(odt);
		}
		oldObjs.clear();
		//insert or update
		for(PcpunishStandard newdt :newObjs){
			found = false;
			for(Iterator<PcpunishStandard> it=getPcpunishStandards().iterator();
			 it.hasNext();){
				PcpunishStandard odt = it.next();
				if(odt.getCid().equals( newdt.getCid())){
					odt.copy(newdt);
					found = true;
					break;
				}
			}
			if(! found)
				addPcpunishStandard(newdt);
		} 	
	}	

	public Set<Pcfreeumpiredegree> getPcfreeumpiredegrees(){
		if(this.pcfreeumpiredegrees==null)
			this.pcfreeumpiredegrees = new HashSet<Pcfreeumpiredegree>();
		return this.pcfreeumpiredegrees;
	}

	public void setPcfreeumpiredegrees(Set<Pcfreeumpiredegree> pcfreeumpiredegrees) {
		this.pcfreeumpiredegrees = pcfreeumpiredegrees;
	}	

	public void addPcfreeumpiredegree(Pcfreeumpiredegree pcfreeumpiredegree ){
		if (this.pcfreeumpiredegrees==null)
			this.pcfreeumpiredegrees = new HashSet<Pcfreeumpiredegree>();
		this.pcfreeumpiredegrees.add(pcfreeumpiredegree);
	}
	
	public void removePcfreeumpiredegree(Pcfreeumpiredegree pcfreeumpiredegree ){
		if (this.pcfreeumpiredegrees==null)
			return;
		this.pcfreeumpiredegrees.remove(pcfreeumpiredegree);
	}
	
	public Pcfreeumpiredegree newPcfreeumpiredegree(){
		Pcfreeumpiredegree res = new Pcfreeumpiredegree();
  
//		res.setPunishclassid(this.getPunishclassid());

		return res;
	}
	/**
	 * 替换子类对象数组，这个函数主要是考虑hibernate中的对象的状态，以避免对象状态不一致的问题
	 * 
	 */
	public void replacePcfreeumpiredegrees(List<Pcfreeumpiredegree> pcfreeumpiredegrees) {
		List<Pcfreeumpiredegree> newObjs = new ArrayList<Pcfreeumpiredegree>();
		for(Pcfreeumpiredegree p :pcfreeumpiredegrees){
			if(p==null)
				continue;
			Pcfreeumpiredegree newdt = newPcfreeumpiredegree();
			newdt.copyNotNullProperty(p);
			newObjs.add(newdt);
		}
		//delete
		boolean found = false;
		Set<Pcfreeumpiredegree> oldObjs = new HashSet<Pcfreeumpiredegree>();
		oldObjs.addAll(getPcfreeumpiredegrees());
		
		for(Iterator<Pcfreeumpiredegree> it=oldObjs.iterator(); it.hasNext();){
			Pcfreeumpiredegree odt = it.next();
			found = false;
			for(Pcfreeumpiredegree newdt :newObjs){
				if(odt.getCid().equals( newdt.getCid())){
					found = true;
					break;
				}
			}
			if(! found)
				removePcfreeumpiredegree(odt);
		}
		oldObjs.clear();
		//insert or update
		for(Pcfreeumpiredegree newdt :newObjs){
			found = false;
			for(Iterator<Pcfreeumpiredegree> it=getPcfreeumpiredegrees().iterator();
			 it.hasNext();){
				Pcfreeumpiredegree odt = it.next();
				if(odt.getCid().equals( newdt.getCid())){
					odt.copy(newdt);
					found = true;
					break;
				}
			}
			if(! found)
				addPcfreeumpiredegree(newdt);
		} 	
	}	


	public void copy(PcpunishItem other){
  
		this.setPunishclassid(other.getPunishclassid());
  
		this.itemId= other.getItemId();  
		this.version= other.getVersion();  
		this.punishclassname= other.getPunishclassname();  
		this.depid= other.getDepid();  
		this.punishclasscode= other.getPunishclasscode();  
		this.punishobject= other.getPunishobject();  
		this.isinuse= other.getIsinuse();  
		this.punishbasiscontent= other.getPunishbasiscontent();  
		this.punishbasis= other.getPunishbasis();  
		this.remark= other.getRemark();  
		this.islawsuit= other.getIslawsuit();  
		this.isreconsider= other.getIsreconsider();
	
		this.pcpunishStandards = other.getPcpunishStandards();	
		this.pcfreeumpiredegrees = other.getPcfreeumpiredegrees();
	}
	
	public void copyNotNullProperty(PcpunishItem other){
  
	if( other.getPunishclassid() != null)
		this.setPunishclassid(other.getPunishclassid());
  
		if( other.getItemId() != null)
			this.itemId= other.getItemId();  
		if( other.getVersion() != null)
			this.version= other.getVersion();  
		if( other.getPunishclassname() != null)
			this.punishclassname= other.getPunishclassname();  
		if( other.getDepid() != null)
			this.depid= other.getDepid();  
		if( other.getPunishclasscode() != null)
			this.punishclasscode= other.getPunishclasscode();  
		if( other.getPunishobject() != null)
			this.punishobject= other.getPunishobject();  
		if( other.getIsinuse() != null)
			this.isinuse= other.getIsinuse();  
		if( other.getPunishbasiscontent() != null)
			this.punishbasiscontent= other.getPunishbasiscontent();  
		if( other.getPunishbasis() != null)
			this.punishbasis= other.getPunishbasis();  
		if( other.getRemark() != null)
			this.remark= other.getRemark();  
		if( other.getIslawsuit() != null)
			this.islawsuit= other.getIslawsuit();  
		if( other.getIsreconsider() != null)
			this.isreconsider= other.getIsreconsider();
	
		this.pcpunishStandards = other.getPcpunishStandards();	
		this.pcfreeumpiredegrees = other.getPcfreeumpiredegrees();
	}
	
	public void clearProperties(){
  
		this.itemId= null;  
		this.version= null;  
		this.punishclassname= null;  
		this.depid= null;  
		this.punishclasscode= null;  
		this.punishobject= null;  
		this.isinuse= null;  
		this.punishbasiscontent= null;  
		this.punishbasis= null;  
		this.remark= null;  
		this.islawsuit= null;  
		this.isreconsider= null;
	
		this.pcpunishStandards = new HashSet<PcpunishStandard>();	
		this.pcfreeumpiredegrees = new HashSet<Pcfreeumpiredegree>();
	}
}
