package com.wu.general.foundation.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.wu.general.core.annotation.Lock;
import com.wu.general.core.domain.IdEntity;

@Cacheable
@Cache (usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name = "shop_user")
public class User extends IdEntity {
	private static final long serialVersionUID = 8026813053768023527L;

	private String userName; //会员名

	private String trueName; //真实姓名

	@Lock
	private String password; //密码

	private String userRole; //会员角色

	private Date birthday; //生日

	private String telephone; //手机

	@Column(columnDefinition = "int default 0")
	private int years; //

	private String MSN; //MSN

	private String address; //详细地址

	private int sex; //性别

	private String email; //邮箱

	private int emailValid; //邮箱是否有效，0无，1有

	private String mobile;

	private int status;

	private boolean enabled = true;

	@ManyToMany(targetEntity = Role.class, fetch = FetchType.EAGER)
	@JoinTable(name = "shop_user_role", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = { @JoinColumn(name = "role_id") })
	private Set<Role> roles = new TreeSet<>();

	@Transient
	private Map<String, List<Res>> roleResources;

	private Date lastLoginDate; //最后一次登录时间

	private Date loginDate;  //登录时间

	private String lastLoginIp;

	private String loginIp; //登录IP

	private int loginCount; //登录次数

	private int report; // -1 恶意举报 被禁止    0允许举报

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getTrueName() {
		return trueName;
	}

	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public int getYears() {
		return years;
	}

	public void setYears(int years) {
		this.years = years;
	}

	public String getMSN() {
		return MSN;
	}

	public void setMSN(String MSN) {
		this.MSN = MSN;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getEmailValid() {
		return emailValid;
	}

	public void setEmailValid(int emailValid) {
		this.emailValid = emailValid;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Map<String, List<Res>> getRoleResources() {
		if (this.roleResources == null) {
			this.roleResources = new HashMap<>();

			for (Role role : this.roles) {
				String roleCode = role.getRoleCode();
				List<Res> ress = role.getReses();
				for (Res res : ress) {
					String key = roleCode + "_" + res.getType();
					if (!(this.roleResources.containsKey(key))) {
						this.roleResources.put(key, new ArrayList<Res>());
					}
					this.roleResources.get(key).add(res);
				}
			}
		}
		return this.roleResources;
	}

	public void setRoleResources(Map<String, List<Res>> roleResources) {
		this.roleResources = roleResources;
	}

	public Date getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public Date getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

	public String getLastLoginIp() {
		return lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public int getLoginCount() {
		return loginCount;
	}

	public void setLoginCount(int loginCount) {
		this.loginCount = loginCount;
	}

	public int getReport() {
		return report;
	}

	public void setReport(int report) {
		this.report = report;
	}

	// TODO 检查帐号过期状态
	public boolean isAccountNonExpired() {
		return true;
	}

	// TODO 检查帐号锁定状态
	public boolean isAccountNonLocked() {
		return true;
	}

	// TODO 检查密码过期状态
	public boolean isCredentialsNonExpired() {
		return true;
	}
}
