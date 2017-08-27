import org.apache.shiro.authz.UnauthorizedException;
import org.junit.Assert;
import org.junit.Test;

public class PermissionTest extends LoginTest {

	@Test
	public void testIsPermitted() {
		login("shiro-permission.ini", "wu", "123");

		//判断用户拥有权限：user:create
		Assert.assertTrue(subject().isPermitted("user:create"));

		//判断拥有权限：user:update user:delete
		Assert.assertTrue(subject().isPermittedAll("user:update", "user:delete"));

		//判断用户没有权限：user:view
		Assert.assertFalse(subject().isPermitted("user:view"));
	}

	@Test(expected = UnauthorizedException.class)
	public void testCheckPermission () {
		login("shiro-permission.ini", "wu", "123");

		//断言拥有权限：user:create
		subject().checkPermission("user:create");

		//断言拥有权限：user:delete and user:update
		subject().checkPermissions("user:delete", "user:update");

		//断言拥有权限：user:view 失败抛出异常
		subject().checkPermissions("user:view");
	}

	@Test
	public void testWildcardPermission1() {
		login("shiro-permission.ini", "li", "123");

		subject().checkPermissions("system:user:update", "system:user:delete");
		subject().checkPermissions("system:user:update,delete");
	}
}
