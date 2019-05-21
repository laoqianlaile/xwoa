package com.centit.sys.po;

/**
 * FOptflownoinfo entity.
 * 
 * @author MyEclipse Persistence Tools
 */
//业务操作流水号信息表
public class FOptflownoinfo implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID =  1L;

	private FOptflownoinfoId id;   //主键id
	private Long curno;				//当前编号

	// Constructors

	/** default constructor */
	public FOptflownoinfo() {
	}

	/** full constructor */
	public FOptflownoinfo(FOptflownoinfoId id, Long curno) {
		this.id = id;
		this.curno = curno;
	}

	// Property accessors
	public FOptflownoinfoId getId() {
		return this.id;
	}

	public void setId(FOptflownoinfoId id) {
		this.id = id;
	}

	public Long getCurno() {
		return this.curno;
	}

	public void setCurno(Long curno) {
		this.curno = curno;
	}

	public void copy(FOptflownoinfo other){
  
		this.curno= other.getCurno();
	}
	
	public void copyNotNullProperty(FOptflownoinfo other){
  
		if( other.getCurno() != null)
			this.curno= other.getCurno();
	}
}
