package com.degwee.bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.primefaces.context.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.degwee.model.User;
import com.degwee.service.ClientService;
import com.degwee.service.UserService;
import com.degwee.utils.Constants;

import javassist.bytecode.stackmap.BasicBlock.Catch;

@ManagedBean(name = "loginBean")
@SessionScoped
public class LoginBean {

	@Autowired
	UserService userService;
	private String userName;
	private String password;
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String login() {
		try {
			if (userName == null || userName.isEmpty())
				Constants.showMessage("Please Enter UserName", true);
			else if (password == null || password.isEmpty())
				Constants.showMessage("Please Enter Password", true);
			else {
				user = null;
				user = userService.findUserByUserNamePass(userName, password);
				if (user == null) {
					Constants.showMessage(
							"Please Enter a Valid UserName & Password,the entered Credentials not exits in our system",
							true);
				} else {
					FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(
					        Constants.AUTH_KEY, userName);
					return "home";
				}

			}
		} catch (Exception e) {
			Constants.showMessage("Error In Login,ex:" + e.getMessage(), true);
			System.out.println(e);
		}
		return null;
	}
	public String logout() {
	    FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
	        .remove(Constants.AUTH_KEY);
	    return null;
	  }

	@PostConstruct
	private void init() {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		ServletContext servletContext = (ServletContext) externalContext.getContext();
		WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext).getAutowireCapableBeanFactory()
				.autowireBean(this);
	}
}
