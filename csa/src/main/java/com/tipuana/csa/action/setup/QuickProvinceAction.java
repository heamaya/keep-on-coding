package com.tipuana.csa.action.setup;

import java.util.List;

import org.apache.struts2.json.annotations.JSON;
import org.hibernate.criterion.Order;

import com.opensymphony.xwork2.Preparable;
import com.tipuana.csa.action.base.CreateReadUpdateRemoveDeleteAction;
import com.tipuana.csa.action.util.Constants;
import com.tipuana.csa.dao.ProvinceDAO;
import com.tipuana.csa.model.Province;


@SuppressWarnings("serial")
public class QuickProvinceAction extends CreateReadUpdateRemoveDeleteAction
		implements Preparable {

	private ProvinceDAO provinceDAO;
	private Province model;
	private List<Province> provinces;
	private String provinceName;

	public QuickProvinceAction() {
	}

	public String create() throws Exception {
		getProvinceDAO().makePersistent(getModelFromProperties());
		
		return list();
	}

	public String delete() throws Exception {
		getProvinceDAO().makeTransient(getModel());
		
		return list();
	}

	public String list() {
		setActionMethod(Constants.LIST);
		
		setProvinces(getProvinceDAO().findAll(Order.asc("name")));
		
		return Constants.LIST;
	}

	public String update() throws Exception {
		getProvinceDAO().makePersistent(getModelFromProperties());
		
		return list();
	}

	public void prepare() throws Exception {
		
		if (getRequestId() == 0L) {
			setModel(new Province());
		} else {
			setModel((Province) getProvinceDAO().findById(Long.valueOf(getRequestId()), true));
			setProvinceName(getModel().getName());
		}
		
	}

	@JSON(serialize=false)
	public Province getModel() {
		return model;
	}

	public void setModel(Province model) {
		this.model = model;
	}

	@JSON(serialize=false)
	public ProvinceDAO getProvinceDAO() {
		return provinceDAO;
	}

	public void setProvinceDAO(ProvinceDAO provinceDAO) {
		this.provinceDAO = provinceDAO;
	}

	@JSON(serialize=true, name="provinces")
	public List<Province> getProvinces() {
		return provinces;
	}

	public void setProvinces(List<Province> provinces) {
		this.provinces = provinces;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	
	public Province getModelFromProperties() {
		Province model = getModel();
		
		model.setName(getProvinceName());
	
		return model;
	}
	
}