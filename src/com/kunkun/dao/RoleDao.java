package com.kunkun.dao;

import java.util.List;

import com.kunkun.permission.domain.Role;
import com.kunkun.permission.domain.User;

public interface RoleDao {

	List<Role> findRoles(User user);

}
