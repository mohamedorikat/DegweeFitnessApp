package com.degwee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.degwee.daos.UserDao;
import com.degwee.model.User;

@Service
public class UserService {
	@Autowired
	public UserDao userDao;

	public void save(User user) {
		userDao.save(user);
	}

	public User findUserById(Integer id) {
			return userDao.findUserById(id);
	}
	public List<User> findAllUsers()
	{
		return userDao.findAllUser();
	}
	public void update(User user )
	{
		 userDao.update(user);
	}
	public void delete(User user )
	{
		 userDao.delete(user);
	}
	public User findUserByUserNamePass(String userName,String password)
	{
		return userDao.findUserByUserNamePass(userName,password);
	}
}
