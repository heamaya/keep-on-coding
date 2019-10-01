package com.tipuana.csa.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("serial")
public class Dike implements Serializable, Comparable<Object> {
	private long id;
	private int version = 0;
	private Date created = new Date();
	private String name;
	private WorkState workState;
	private WorkStateDate workStateDate;
	private DikeSlope dikeSlope;
	private Double maximumHeight;
	private Double groundVolume;
	private Double slopeSurface;
	private Double baseSurface;
	private Double length;
	private Double maximumWidth;
	private Set<Outlet> outlets = new HashSet<Outlet>();
	private Set<Spillway> spillways = new HashSet<Spillway>();
	private SystematizationProject systematizationProject;
	private User user;
	
	public long getId() {
		return id;
	}
	
	public int getVersion() {
		return version;
	}
	
	public Date getCreated() {
		return created;
	}
	
	public String getName() {
		return name;
	}
	
	public WorkState getWorkState() {
		return workState;
	}
	
	public DikeSlope getDikeSlope() {
		return dikeSlope;
	}
	
	public Double getMaximumHeight() {
		return maximumHeight;
	}
	
	public Double getGroundVolume() {
		return groundVolume;
	}
	
	public Double getSlopeSurface() {
		return slopeSurface;
	}
	
	public Double getBaseSurface() {
		return baseSurface;
	}
	
	public Double getLength() {
		return length;
	}
	
	public Double getMaximumWidth() {
		return maximumWidth;
	}
	
	public Set<Outlet> getOutlets() {
		return outlets;
	}
	
	public Set<Spillway> getSpillways() {
		return spillways;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public void setVersion(int version) {
		this.version = version;
	}
	
	public void setCreated(Date created) {
		this.created = created;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setWorkState(WorkState workState) {
		this.workState = workState;
	}
	
	public void setDikeSlope(DikeSlope dikeSlope) {
		this.dikeSlope = dikeSlope;
	}
	
	public void setMaximumHeight(Double maximumHeight) {
		this.maximumHeight = maximumHeight;
	}
	
	public void setGroundVolume(Double groundVolume) {
		this.groundVolume = groundVolume;
	}
	
	public void setSlopeSurface(Double slopeSurface) {
		this.slopeSurface = slopeSurface;
	}
	
	public void setBaseSurface(Double baseSurface) {
		this.baseSurface = baseSurface;
	}
	
	public void setLength(Double length) {
		this.length = length;
	}
	
	public void setMaximumWidth(Double maximumWidth) {
		this.maximumWidth = maximumWidth;
	}
	
	public void setOutlets(Set<Outlet> outlets) {
		this.outlets = outlets;
	}

	public void setSpillways(Set<Spillway> spillways) {
		this.spillways = spillways;
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

		if(object instanceof Dike) {
			return Long.valueOf(getCreated().getTime()).compareTo(
				   Long.valueOf(( (Dike) object).getCreated().getTime()));
		}
		
		return 0;
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime
				* result
				+ ((systematizationProject == null) ? 0
						: systematizationProject.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Dike other = (Dike) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (systematizationProject == null) {
			if (other.systematizationProject != null)
				return false;
		} else if (!systematizationProject.equals(other.systematizationProject))
			return false;
		return true;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}