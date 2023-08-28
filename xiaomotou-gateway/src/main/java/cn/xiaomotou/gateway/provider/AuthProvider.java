package cn.xiaomotou.gateway.provider;


import java.util.ArrayList;
import java.util.List;

/**
 * 鉴权配置
 *
 * @author Chill
 */
public class AuthProvider {

	private static final List<String> DEFAULT_SKIP_URL = new ArrayList<>();

	static {
		DEFAULT_SKIP_URL.add("/user/login");
	}

	/**
	 * 默认无需鉴权的API
	 */
	public static List<String> getDefaultSkipUrl() {
		return DEFAULT_SKIP_URL;
	}

}
