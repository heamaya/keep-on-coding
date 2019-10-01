package com.tipuana.csa.dao;

import java.util.List;

import org.hibernate.criterion.MatchMode;

import com.tipuana.csa.model.News;


public interface NewsDAO extends GenericDAO<News, Long> {

	public abstract List<News> find(final News news, final MatchMode matchMode);
}
