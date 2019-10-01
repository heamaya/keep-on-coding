package com.tipuana.csa.action.setup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hibernate.criterion.Order;

import com.opensymphony.xwork2.Preparable;
import com.tipuana.csa.action.base.CreateReadUpdateRemoveDeleteSearchAction;
import com.tipuana.csa.action.util.Constants;
import com.tipuana.csa.dao.CityDAO;
import com.tipuana.csa.dao.PersonDAO;
import com.tipuana.csa.dao.ProvinceDAO;
import com.tipuana.csa.dao.UserDAO;
import com.tipuana.csa.dao.awareness.CityDAOAware;
import com.tipuana.csa.dao.awareness.ProvinceDAOAware;
import com.tipuana.csa.model.Address;
import com.tipuana.csa.model.City;
import com.tipuana.csa.model.Person;
import com.tipuana.csa.model.Province;
import com.tipuana.csa.model.Role;
import com.tipuana.csa.model.User;

@SuppressWarnings("serial")
public class UserAction extends CreateReadUpdateRemoveDeleteSearchAction
    implements Preparable, ProvinceDAOAware, CityDAOAware{
    private User model;
    private Role role;
    private UserDAO userDAO;
    private PersonDAO personDAO;
    private ProvinceDAO provinceDAO;
    private CityDAO cityDAO;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String primaryTelephoneNumber;
    private String secondaryTelephoneNumber;
    private String primaryCellPhoneNumber;
    private String secondaryCellPhoneNumber;
    private String secondaryEmail;
    private String comment;
    private Province province;
    private City city;
    private String street;
    private Integer streetNumber;
    private String department;
    private Integer floor;
    private String password;
    private String repeatPassword;
    private Boolean keepPassword;

    public UserAction() {
    	setListName("users");
    }

    public String create()
    {
        User user = getModelFromProperties();
        user.setPassword(getPassword());
        getUserDAO().makePersistent(user);
        
        return listAll();
    }

    public String delete()
    {
        getUserDAO().makeTransient(getModel());
        
        return listAll();
    }

    public String find() {
        setActionMethod(Constants.LIST);
        
        getRequest().put("users", getUserDAO().find(getModelFromProperties()));
        
        return Constants.LIST;
    }

    public String list() {
        setActionMethod(Constants.LIST);
        
        getRequest().put(getListName(), getUserDAO().findAllOrderByUser());
        
        return Constants.LIST;
    }

    public String update() {
        User user = getModelFromProperties();
        
        if(!getKeepPassword().booleanValue() && getPassword() != null && !getPassword().equalsIgnoreCase("")) {
            user.setPassword(getPassword());
            getUserDAO().makePersistent(user);
        } else {
            getPersonDAO().makePersistent(user.getPerson());
        }
        
        return listAll();
    }

    public void prepare() throws Exception {
        User user = null;
        
        if(getRequestId() == 0L) {
            user = new User();
        } else {
            user = (User) getUserDAO().findById(Long.valueOf(getRequestId()), true);
            setUsername(user.getUsername());
            setFirstName(user.getPerson().getFirstName());
            setLastName(user.getPerson().getLastName());
            setEmail(user.getPerson().getEmail());
            setSecondaryEmail(user.getPerson().getSecondaryEmail());
            setPrimaryTelephoneNumber(user.getPerson().getPrimaryTelephoneNumber());
            setSecondaryTelephoneNumber(user.getPerson().getSecondaryTelephoneNumber());
            setPrimaryCellPhoneNumber(user.getPerson().getPrimaryCellPhoneNumber());
            setSecondaryCellPhoneNumber(user.getPerson().getSecondaryCellPhoneNumber());
            setComment(user.getPerson().getComment());
            
            if(user.getPerson().getAddress() != null) {
                setProvince(user.getPerson().getAddress().getProvince());
                setCity(user.getPerson().getAddress().getCity());
                setStreet(user.getPerson().getAddress().getStreet());
                setStreetNumber(user.getPerson().getAddress().getStreetNumber());
                setFloor(user.getPerson().getAddress().getFloor());
                setDepartment(user.getPerson().getAddress().getDepartment());
            }
            
            setRole(user.getRole());
        }
        
        setModel(user);
    }

    public User getModel()
    {
        return model;
    }

    public void setModel(User model)
    {
        this.model = model;
    }

    public UserDAO getUserDAO()
    {
        return userDAO;
    }

    public void setUserDAO(UserDAO userDAO)
    {
        this.userDAO = userDAO;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPrimaryTelephoneNumber()
    {
        return primaryTelephoneNumber;
    }

    public void setPrimaryTelephoneNumber(String primaryTelephoneNumber)
    {
        this.primaryTelephoneNumber = primaryTelephoneNumber;
    }

    public String getSecondaryTelephoneNumber()
    {
        return secondaryTelephoneNumber;
    }

    public void setSecondaryTelephoneNumber(String secondaryTelephoneNumber)
    {
        this.secondaryTelephoneNumber = secondaryTelephoneNumber;
    }

    public String getPrimaryCellPhoneNumber()
    {
        return primaryCellPhoneNumber;
    }

    public void setPrimaryCellPhoneNumber(String primaryCellPhoneNumber)
    {
        this.primaryCellPhoneNumber = primaryCellPhoneNumber;
    }

    public String getSecondaryCellPhoneNumber()
    {
        return secondaryCellPhoneNumber;
    }

    public void setSecondaryCellPhoneNumber(String secondaryCellPhoneNumber)
    {
        this.secondaryCellPhoneNumber = secondaryCellPhoneNumber;
    }

    public String getSecondaryEmail()
    {
        return secondaryEmail;
    }

    public void setSecondaryEmail(String secondaryEmail)
    {
        this.secondaryEmail = secondaryEmail;
    }

    public String getComment()
    {
        return comment;
    }

    public void setComment(String comment)
    {
        this.comment = comment;
    }

    public City getCity()
    {
        return city;
    }

    public void setCity(City city)
    {
        this.city = city;
    }

    public String getStreet()
    {
        return street;
    }

    public void setStreet(String street)
    {
        this.street = street;
    }

    public Integer getStreetNumber()
    {
        return streetNumber;
    }

    public void setStreetNumber(Integer streetNumber)
    {
        this.streetNumber = streetNumber;
    }

    public String getDepartment()
    {
        return department;
    }

    public void setDepartment(String department)
    {
        this.department = department;
    }

    public Integer getFloor()
    {
        return floor;
    }

    public void setFloor(Integer floor)
    {
        this.floor = floor;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getRepeatPassword()
    {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword)
    {
        this.repeatPassword = repeatPassword;
    }

    public ProvinceDAO getProvinceDAO()
    {
        return provinceDAO;
    }

    public void setProvinceDAO(ProvinceDAO provinceDAO)
    {
        this.provinceDAO = provinceDAO;
    }

    public CityDAO getCityDAO()
    {
        return cityDAO;
    }

    public void setCityDAO(CityDAO cityDAO)
    {
        this.cityDAO = cityDAO;
    }

    public Boolean getKeepPassword()
    {
        return keepPassword;
    }

    public void setKeepPassword(Boolean keepPassword)
    {
        this.keepPassword = keepPassword;
    }

    public List<Province> getProvinces()
    {
        return getProvinceDAO().findAll(Order.asc("name"));
    }

    public List<City> getCities()
    {
        if(province != null)
            return getCityDAO().find(province.getId().longValue());
        else
            return new ArrayList<City>();
    }

    public User getModelFromProperties() {
        User user = getModel();
        user.setUsername(getUsername());
        user.setPerson(getPersonFromProperties());
        user.setEnabled(true);
        user.setRole(getRole());
        
        return user;
    }

    public Person getPersonFromProperties()
    {
        Person person = getModel().getPerson() != null ? getModel().getPerson() : new Person();
        person.setLastName(getLastName());
        person.setFirstName(getFirstName());
        person.setEmail(getUsername());
        person.setSecondaryEmail(getSecondaryEmail());
        person.setPrimaryTelephoneNumber(getPrimaryTelephoneNumber());
        person.setSecondaryTelephoneNumber(getSecondaryTelephoneNumber());
        person.setPrimaryCellPhoneNumber(getPrimaryCellPhoneNumber());
        person.setSecondaryCellPhoneNumber(getSecondaryCellPhoneNumber());
        person.setComment(getComment());
        
        if(getProvince() == null && getCity() == null && (getStreet() == null || getStreet().equalsIgnoreCase("")) && getStreetNumber() == null && (getDepartment() == null || getDepartment().equalsIgnoreCase("")) && getFloor() == null) {
            person.setAddress(null);
        } else {
            Address address = person.getAddress() != null ? person.getAddress() : new Address();
            address.setProvince(getProvince());
            address.setCity(getCity());
            address.setDepartment(getDepartment());
            address.setFloor(getFloor());
            address.setStreet(getStreet());
            address.setStreetNumber(getStreetNumber());
            person.setAddress(address);
        }
        
        return person;
    }

    public Role getRole()
    {
        return role;
    }

    public void setRole(Role role)
    {
        this.role = role;
    }

    public List<Role> getRoles()
    {
        return Arrays.asList(Role.values());
    }

    public Province getProvince()
    {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public PersonDAO getPersonDAO()
    {
        return personDAO;
    }

    public void setPersonDAO(PersonDAO personDAO)
    {
        this.personDAO = personDAO;
    }

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}