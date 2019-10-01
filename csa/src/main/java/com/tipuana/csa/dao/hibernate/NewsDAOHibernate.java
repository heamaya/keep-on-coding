package com.tipuana.csa.dao.hibernate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.tipuana.csa.dao.NewsDAO;
import com.tipuana.csa.model.News;


public class NewsDAOHibernate extends GenericHibernateDAO<News, Long> implements
		NewsDAO {
	
	@SuppressWarnings("unchecked")
	public List<News> find(final News news, final MatchMode matchMode) {
		
		return (List<News>) getHibernateTemplate().execute(new HibernateCallback<List<News>>() {

            public List<News> doInHibernate(Session session) throws HibernateException, SQLException {
                
            	List<String> excludeProperties = new ArrayList<String>();
                excludeProperties.add("id");
                excludeProperties.add("created");
                excludeProperties.add("version");
                excludeProperties.add("imageFileName");
                excludeProperties.add("imageContentType");
                excludeProperties.add("imageDescriptionSpanish");
                excludeProperties.add("imageDescriptionPortuguese");
                excludeProperties.add("imageDescriptionEnglish");
                
                if(news.getTitleSpanish() == null || news.getTitleSpanish().equalsIgnoreCase("")) {
                    excludeProperties.add("titleSpanish");
                }
                    
                if(news.getBodySpanish() == null || news.getBodySpanish().equalsIgnoreCase("")) {
                    excludeProperties.add("bodySpanish");
                }
                
                if(news.getTitlePortuguese() == null || news.getTitlePortuguese().equalsIgnoreCase("")) {
                    excludeProperties.add("titlePortuguese");
                }
                    
                if(news.getBodyPortuguese() == null || news.getBodyPortuguese().equalsIgnoreCase("")) {
                    excludeProperties.add("bodyPortuguese");
                }
                
                if(news.getTitleEnglish() == null || news.getTitleEnglish().equalsIgnoreCase("")) {
                    excludeProperties.add("titleEnglish");
                }
                    
                if(news.getBodyEnglish() == null || news.getBodyEnglish().equalsIgnoreCase("")) {
                    excludeProperties.add("bodyEnglish");
                }
                
                Criteria newsCriteria = session.createCriteria(News.class);
                Example newsExample = Example.create(news);
                newsExample.enableLike(matchMode);
                      
                for(String exclude : excludeProperties) {
                	newsExample.excludeProperty(exclude);
                }

                newsCriteria.add(newsExample);
                newsCriteria.addOrder(Order.asc("created"));
                
                return newsCriteria.list();
            }
        });
	}

}
