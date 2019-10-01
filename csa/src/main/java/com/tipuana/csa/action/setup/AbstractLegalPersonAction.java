package com.tipuana.csa.action.setup;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Order;

import com.tipuana.csa.dao.FarmerDAO;
import com.tipuana.csa.dao.GenericCompanyDAO;
import com.tipuana.csa.dao.awareness.FarmerDAOAware;
import com.tipuana.csa.model.Company;
import com.tipuana.csa.model.Farmer;

@SuppressWarnings("serial")
public abstract class AbstractLegalPersonAction<M extends Company, D extends GenericCompanyDAO<M, Long>> extends CompanyAction<M, D> implements FarmerDAOAware {
	private FarmerDAO farmerDAO;
	
	@Override
	public FarmerDAO getFarmerDAO() {
		return farmerDAO;
	}

	public void setFarmerDAO(FarmerDAO farmerDAO) {
		this.farmerDAO = farmerDAO;
	}
	
	public List<Farmer> getAgricultorFarmers() {
		List<Order> orders = new ArrayList<Order>();
		orders.add(Order.asc("lastName"));
		orders.add(Order.asc("firstName"));
		
		return getFarmerDAO().findAll(orders);
	}		
}