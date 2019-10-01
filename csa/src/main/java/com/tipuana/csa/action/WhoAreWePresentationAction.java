package com.tipuana.csa.action;

import java.util.Random;

import com.tipuana.csa.action.util.Constants;


@SuppressWarnings("serial")
public class WhoAreWePresentationAction extends BasePresentationPhotoAction {
	private Integer seed;

	@Override
	public String execute() {
		super.execute();
		Random random = new Random();
		setSeed(random.nextInt(2) + 1);
		
		return Constants.LIST;
	}

	public Integer getSeed() {
		return seed;
	}

	public void setSeed(Integer seed) {
		this.seed = seed;
	}

	@Override
	public String getPath() {
		return Constants.WHO_WE_ARE_PRESENTATION_PATH;
	}
}