package com.tipuana.csa.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.io.FileSystemResource;

import com.tipuana.csa.dao.CityDAO;
import com.tipuana.csa.dao.ProvinceDAO;
import com.tipuana.csa.dao.UserDAO;
import com.tipuana.csa.model.Address;
import com.tipuana.csa.model.City;
import com.tipuana.csa.model.Person;
import com.tipuana.csa.model.Province;
import com.tipuana.csa.model.Role;
import com.tipuana.csa.model.User;


public class HibernateUserCreation {
	
	public static void main(String[] args) throws Exception {
		create();
	}
	
	public static void create() throws Exception {
		GenericApplicationContext context = new GenericApplicationContext();
		XmlBeanDefinitionReader xmlReader = new XmlBeanDefinitionReader(context);
		
		FileSystemResource [] resources = new FileSystemResource[3];
		resources[0] = new FileSystemResource("WebContent/WEB-INF/spring-data-tools.xml");
		resources[1] = new FileSystemResource("WebContent/WEB-INF/spring-security.xml");
		resources[2] = new FileSystemResource("WebContent/WEB-INF/spring-servlet.xml");
		
		xmlReader.loadBeanDefinitions(resources);
		context.refresh();

		UserDAO userDAO = (UserDAO) context.getBean("userDAO");
		ProvinceDAO provinceDAO = (ProvinceDAO) context.getBean("provinceDAO");
		CityDAO cityDAO = (CityDAO) context.getBean("cityDAO");
		
		List<User> users = new ArrayList<User>();
		
		Person person = new Person();
		person.setFirstName("Hernán");
		person.setLastName("Amaya");
		
		Province province = new Province();
		province.setName("Córdoba");
		province = provinceDAO.findByExample(province, "id","version","created","addresses","cities").get(0);
		
		City city = new City();
		city.setName("Río Tercero");
		city.setProvince(province);
		city = cityDAO.findByExample(city, "id","version","created","addresses").get(0);
		
		Address address = new Address();
		address.setProvince(province);
		address.setCity(city);
		person.setAddress(address);
		
		User user = new User();
		user.setUsername("heamaya@gmail.com");
		user.setRole(Role.ROOT);
		user.setPerson(person);
		user.setEnabled(true);
		user.setPassword("HEA30327198");
		
		users.add(user);
				
		userDAO.makePersistent(users);
	}

}