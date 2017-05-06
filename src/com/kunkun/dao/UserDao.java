package com.kunkun.dao;

import com.kunkun.permission.domain.User;

public interface UserDao {

	User find(String username, String password);

}
