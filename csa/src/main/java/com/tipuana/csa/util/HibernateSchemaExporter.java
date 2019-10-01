package com.tipuana.csa.util;

import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.io.FileSystemResource;
import org.springframework.orm.hibernate3.LocalSessionFactoryBean;

public class HibernateSchemaExporter {
	
	public static void main(String[] args) throws Exception {
		export(true);
	}
	
	public static void export(boolean createOnly) throws Exception {
		GenericApplicationContext context = new GenericApplicationContext();
		XmlBeanDefinitionReader xmlReader = new XmlBeanDefinitionReader(context);
		
		FileSystemResource [] resources = new FileSystemResource[3];
		resources[0] = new FileSystemResource("WebContent/WEB-INF/spring-data-tools.xml");
		resources[1] = new FileSystemResource("WebContent/WEB-INF/spring-security.xml");
		resources[2] = new FileSystemResource("WebContent/WEB-INF/spring-servlet.xml");
		
		xmlReader.loadBeanDefinitions(resources);
		context.refresh();
		
		export(context, createOnly);
	}
	
	public static void export(GenericApplicationContext context, boolean createOnly) throws Exception {
		LocalSessionFactoryBean sessionFactory = (LocalSessionFactoryBean) context.getBean("&sessionFactory");
		SchemaExport schemaExport = new SchemaExport(sessionFactory.getConfiguration());
		schemaExport.setOutputFile((String) context.getBean("sqlDmlFileName"));
		schemaExport.setDelimiter(";");		
		schemaExport.execute(true, false, false, createOnly);
	}	
}
