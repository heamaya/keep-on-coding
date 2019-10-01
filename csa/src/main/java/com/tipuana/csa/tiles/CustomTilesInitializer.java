package com.tipuana.csa.tiles;

import org.apache.tiles.TilesApplicationContext;
import org.apache.tiles.factory.AbstractTilesContainerFactory;
import org.apache.tiles.startup.DefaultTilesInitializer;

public class CustomTilesInitializer extends DefaultTilesInitializer {

	@Override
    protected AbstractTilesContainerFactory createContainerFactory(TilesApplicationContext context) {
		return new CustomTilesContainerFactory();
    }
}

