package com.kunkun.dao;

import java.util.List;

import com.kunkun.permission.domain.Function;
import com.kunkun.permission.domain.Role;

public interface FunctionDao {

	List<Function> findFunctions(Role role);

}
