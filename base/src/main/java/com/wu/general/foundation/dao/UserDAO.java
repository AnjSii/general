package com.wu.general.foundation.dao;

import org.springframework.stereotype.Repository;

import com.wu.general.core.base.AbstractGenericDAO;
import com.wu.general.foundation.domain.User;

@Repository ("userDAO")
public class UserDAO extends AbstractGenericDAO<User> {
}
