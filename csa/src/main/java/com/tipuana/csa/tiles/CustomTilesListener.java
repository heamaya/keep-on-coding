package com.tipuana.csa.tiles;

import org.apache.tiles.startup.TilesInitializer;
import org.apache.tiles.web.startup.AbstractTilesListener;

public class CustomTilesListener extends  AbstractTilesListener {

	@Override
	protected TilesInitializer createTilesInitializer() {
		return new CustomTilesInitializer();
	}

}
