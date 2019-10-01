package com.tipuana.csa.action.setup;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.json.annotations.JSON;
import org.hibernate.criterion.Order;

import com.tipuana.csa.action.util.Constants;
import com.tipuana.csa.model.Farmer;

@SuppressWarnings("serial")
public class QuickFarmerAction extends FarmerAction {
	private List<Farmer> agricultorFarmers;
	
	public QuickFarmerAction() {
		this.agricultorFarmers = new ArrayList<Farmer>();
	}

	@JSON(serialize=true, name="agricultorFarmers")
	public List<Farmer> getAgricultorFarmers() {
		return agricultorFarmers;
	}

	public void setAgricultorFarmers(List<Farmer> agricultorFarmers) {
		this.agricultorFarmers = agricultorFarmers;
	}
	
    public String list() {
    	setActionMethod(Constants.LIST);
    	
		List<Order> orders = new ArrayList<Order>();
		orders.add(Order.asc("lastName"));
		orders.add(Order.asc("firstName"));
        
    	setAgricultorFarmers(getPersonDAO().findAll(orders));
       
    	return Constants.LIST;
    }
	
}