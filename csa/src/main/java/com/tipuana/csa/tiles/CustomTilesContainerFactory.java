package com.tipuana.csa.tiles;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.tiles.TilesApplicationContext;
import org.apache.tiles.context.TilesRequestContextFactory;
import org.apache.tiles.factory.BasicTilesContainerFactory;

public class CustomTilesContainerFactory extends  BasicTilesContainerFactory {
	
	@Override
	protected List<URL> getSourceURLs(TilesApplicationContext tilesApplicationContext, TilesRequestContextFactory tilesRequestContextFactory) {

		List<URL> urls = null;

		try {
			urls = new ArrayList<URL>();
			urls.add(tilesApplicationContext.getResource("/WEB-INF/tiles_es.xml"));
			urls.add(tilesApplicationContext.getResource("/WEB-INF/tiles_pt.xml"));
			urls.add(tilesApplicationContext.getResource("/WEB-INF/tiles_en.xml"));
			urls.add(tilesApplicationContext.getResource("/WEB-INF/tiles.xml"));
			
		} catch(Exception exception) {

		}

		return urls;
	}
}