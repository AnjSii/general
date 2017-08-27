import org.apache.shiro.authz.UnauthorizedException;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class RoleTest extends LoginTest {

	@Test
	public void testHasRole() {
		login("shiro-role.ini", "wu", "123");

		//判断拥有角色：role1
		Assert.assertTrue(subject().hasRole("role1"));

		//判断拥有角色：role1 和 role2
		List<String> roleList = new ArrayList<>();
		roleList.add("role1");
		roleList.add("role2");
		Assert.assertTrue(subject().hasAllRoles(roleList));

		//判断拥有角色: role1, role2, role3
		List<String> roleList2 = new ArrayList<>();
		roleList2.add("role1");
		roleList2.add("role2");
		roleList2.add("role3");
		boolean[] result = subject().hasRoles(roleList2);
		Assert.assertEquals(true, result[0]);
		Assert.assertEquals(true, result[1]);
		Assert.assertEquals(false, result[2]);
	}

	@Test(expected = UnauthorizedException.class)
	public void testCheckRole() {
		login("shiro-role.ini", "wu", "123");

		//断言拥有角色 role1
		subject().checkRole("role1");

		//断言拥有角色 role1 和 role3 失败抛出异常
		subject().checkRoles("role1", "role3");
	}
}
