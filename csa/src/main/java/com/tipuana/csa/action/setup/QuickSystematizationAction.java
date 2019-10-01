package com.tipuana.csa.action.setup;

import java.util.List;

import org.apache.struts2.json.annotations.JSON;

import com.tipuana.csa.action.util.Constants;
import com.tipuana.csa.model.Systematization;

@SuppressWarnings("serial")
public class QuickSystematizationAction extends SystematizationAction {
	private List<Systematization> systematizations = null;
	
	@JSON(serialize=true, name="systematizations")
	public List<Systematization> getSystematizations() {
		return systematizations;
	}

	public void setSystematizations(List<Systematization> systematizations) {
		this.systematizations = systematizations;
	}
	
	@Override
    public String list() {
    	setActionMethod(Constants.LIST);
        
    	setSystematizations(getSystematizationDAO().findAll());
       
    	return Constants.LIST;
    }

}
