package com.tipuana.csa.action.setup;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.criterion.Order;

import com.tipuana.csa.dao.CommercialSocietyDAO;
import com.tipuana.csa.dao.FarmerDAO;
import com.tipuana.csa.dao.GenericCompanyDAO;
import com.tipuana.csa.model.CommercialSociety;
import com.tipuana.csa.model.Farmer;

@SuppressWarnings("serial")
public abstract class CommercialSocietyAction<M extends CommercialSociety, D extends GenericCompanyDAO<M, Long>> extends CompanyAction<M, D> {
	private FarmerDAO farmerDAO;
	private CommercialSocietyDAO commercialSocietyDAO;
	private List<Long> agricultorFarmersIds;
	private List<Long> ownersIds;
	
	public List<Farmer> getAgricultorFarmers() {
		M model = getModel();
		List<Farmer> allFarmers = null;
		
		if(model != null && model.getId() != null && model.getId() != 0) {
			allFarmers = farmerDAO.findNotInCommercialSociety(model.getId()); 
		} else {
			List<Order> orders = new ArrayList<Order>();
			orders.add(Order.asc("lastName"));
			orders.add(Order.asc("firstName"));
			allFarmers = farmerDAO.findAll(orders);
		}
		
		return (allFarmers != null ? allFarmers : new ArrayList<Farmer>()); 
	}
	
	public List<Farmer> getOwners() {
		M model = getModel();
		
		if(model != null && model.getId() != null && model.getId() != 0) {
			return new ArrayList<Farmer>(model.getFarmers());
		}
		
		return new ArrayList<Farmer>();
	}

	public FarmerDAO getFarmerDAO() {
		return farmerDAO;
	}

	public void setFarmerDAO(FarmerDAO farmerDAO) {
		this.farmerDAO = farmerDAO;
	}

	public CommercialSocietyDAO getCommercialSocietyDAO() {
		return commercialSocietyDAO;
	}

	public void setCommercialSocietyDAO(CommercialSocietyDAO commercialSocietyDAO) {
		this.commercialSocietyDAO = commercialSocietyDAO;
	}

	public List<Long> getAgricultorFarmersIds() {
		return agricultorFarmersIds;
	}

	public void setAgricultorFarmersIds(List<Long> agricultorFarmersIds) {
		this.agricultorFarmersIds = agricultorFarmersIds;
	}

	public List<Long> getOwnersIds() {
		return ownersIds;
	}

	public void setOwnersIds(List<Long> ownersIds) {
		this.ownersIds = ownersIds;
	}

	@Override
	public String create() throws Exception {
		
		if(ownersIds != null) {
			Set<Farmer> theOwners = new HashSet<Farmer>(getFarmerDAO().findIn(ownersIds));
			getModel().setFarmers(theOwners);
		}
		
		return super.create();
	}

	@Override
	public String update() throws Exception {
		
		if(ownersIds != null) {
			Set<Farmer> theOwners = new HashSet<Farmer>(getFarmerDAO().findIn(ownersIds));
			getModel().setFarmers(theOwners);
		}
		
		return super.update();
	}
}