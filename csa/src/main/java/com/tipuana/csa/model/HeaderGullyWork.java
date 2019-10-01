package com.tipuana.csa.model;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class HeaderGullyWork implements Serializable, Comparable<Object> {
	private long id;
	private int version = 0;
	private Date created = new Date();
	private WorkState workState;
	private WorkStateDate workStateDate;
	private HeaderGullyWorkType type;
	private String name;
	private Integer brickCount;
	private Integer blockCount;
	private Integer concrete;
	private Integer iron;
	private Double ironThickness;
	private Double gabions;
	private SystematizationProject systematizationProject; 
	private User user;

	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public WorkState getWorkState() {
		return workState;
	}
	
	public void setWorkState(WorkState workState) {
		this.workState = workState;
	}
	
	public HeaderGullyWorkType getType() {
		return type;
	}
	
	public void setType(HeaderGullyWorkType type) {
		this.type = type;
	}
	
	public Integer getBrickCount() {
		return brickCount;
	}
	
	//@IntRangeFieldValidator(type=ValidatorType.FIELD, key="brickCount.range", min="0", max="100000")
	public void setBrickCount(Integer brickCount) {
		this.brickCount = brickCount;
	}
	
	public Integer getBlockCount() {
		return blockCount;
	}
	
	//@IntRangeFieldValidator(type=ValidatorType.FIELD, key="blockCount.range", min="0", max="100000")
	public void setBlockCount(Integer blockCount) {
		this.blockCount = blockCount;
	}
	
	public Integer getConcrete() {
		return concrete;
	}
	
	//@DoubleRangeFieldValidator(type=ValidatorType.FIELD, key="concrete.range", minInclusive="0", maxInclusive="100000")
	public void setConcrete(Integer concrete) {
		this.concrete = concrete;
	}
	
	public Integer getIron() {
		return iron;
	}
	
	//@IntRangeFieldValidator(type=ValidatorType.FIELD, key="iron.range", min="0", max="100000")
	public void setIron(Integer iron) {
		this.iron = iron;
	}
	
	public Double getIronThickness() {
		return ironThickness;
	}
	
	//@DoubleRangeFieldValidator(type=ValidatorType.FIELD, key="ironThickness.range", minInclusive="0", maxInclusive="100000")
	public void setIronThickness(Double ironThickness) {
		this.ironThickness = ironThickness;
	}
	
	public Double getGabions() {
		return gabions;
	}
	
	//@DoubleRangeFieldValidator(type=ValidatorType.FIELD, key="gabiones.range", minInclusive="0", maxInclusive="100000")
	public void setGabions(Double gabiones) {
		this.gabions = gabiones;
	}
	
	public int getVersion() {
		return version;
	}

	public Date getCreated() {
		return created;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public void setCreated(Date created) {
		this.created = created;
	}
	
	public SystematizationProject getSystematizationProject() {
		return systematizationProject;
	}

	public void setSystematizationProject(
			SystematizationProject systematizationProject) {
		this.systematizationProject = systematizationProject;
	}
	
	public WorkStateDate getWorkStateDate() {
		return workStateDate;
	}

	public void setWorkStateDate(WorkStateDate workStateDate) {
		this.workStateDate = workStateDate;
	}

	@Override
	public int compareTo(Object object) {
		
		if(object instanceof HeaderGullyWork) {
			return Long.valueOf(getCreated().getTime()).compareTo(
				   Long.valueOf(( (HeaderGullyWork) object).getCreated().getTime()));
		}
		
		return 0;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}