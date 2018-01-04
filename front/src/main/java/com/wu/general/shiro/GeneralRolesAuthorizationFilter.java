package com.wu.general.shiro;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.web.filter.authz.RolesAuthorizationFilter;


/**
 * 允许多种角色组合访问的RolesAuthorizationFilter
 *
 */
public class GeneralRolesAuthorizationFilter extends RolesAuthorizationFilter {

	@SuppressWarnings({"unchecked"})
	public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws IOException {

		Subject subject = getSubject(request, response);
		String[] rolesArray = (String[]) mappedValue;

		if (rolesArray == null || rolesArray.length == 0) {
			//no roles specified, so nothing to check - allow access.
			return true;
		}

		Set<Set<String>> roles = parseRoles(rolesArray);
		return hasAllRoles(subject, roles);
	}

	private Set<Set<String>> parseRoles(String[] rolesArray) {
		Set<Set<String>> roles = new HashSet<>();
		for (String role : CollectionUtils.asSet(rolesArray)) {
			Set<String> rs = CollectionUtils.asSet(role.split("\\|"));
			roles.add(rs);
		}
		return roles;
	}

	private boolean hasAllRoles(Subject subject, Set<Set<String>> roles) {
		for (Set<String> rs : roles) {
			if (!hasAnyRole(subject, rs)) {
				return false;
			}
		}
		return true;
	}

	private boolean hasAnyRole(Subject subject, Set<String> roles) {
		for (String role : roles) {
			if (subject.hasRole(role.trim())) {
				return true;
			}
		}
		return false;
	}
}
