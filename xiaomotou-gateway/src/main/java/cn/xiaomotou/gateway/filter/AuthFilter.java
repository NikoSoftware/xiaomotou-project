
package cn.xiaomotou.gateway.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import cn.xiaomotou.common.utils.JwtHmacUtil;
import cn.xiaomotou.gateway.provider.AuthProvider;
import cn.xiaomotou.gateway.provider.RequestProvider;
import cn.xiaomotou.gateway.provider.ResponseProvider;
import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

/**
 * 鉴权认证
 *
 * @author Chill
 */
@Slf4j
@Component
@AllArgsConstructor
public class AuthFilter implements GlobalFilter, Ordered {

	private static final String AUTHORIZE_TOKEN = "authorize";

	@Autowired
	ObjectMapper objectMapper;

	@Autowired
	JwtHmacUtil jwtHmacUtil;

	private final AntPathMatcher antPathMatcher = new AntPathMatcher();



	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		//校验 Token 放行
		String originalRequestUrl = RequestProvider.getOriginalRequestUrl(exchange);
		String path = exchange.getRequest().getURI().getPath();

		if (isSkip(path) || isSkip(originalRequestUrl)) {
			return chain.filter(exchange);
		}

		ServerHttpRequest request = exchange.getRequest();
		HttpHeaders headers = exchange.getRequest().getHeaders();

		//从请求头中获取token
		String token = headers.getFirst(AUTHORIZE_TOKEN);
		if (token == null) {
			//从请求头参数中获取token
			token = request.getQueryParams().getFirst(AUTHORIZE_TOKEN);
		}


		//校验 Token 合法性
		ServerHttpResponse resp = exchange.getResponse();
		if (StringUtils.isEmpty(token)) {
			return unAuth(resp, "缺失令牌,鉴权失败");
		}


		Claims claims = null;
		try {
			claims = jwtHmacUtil.parseHmacJWT(token);
		} catch (Exception e) {
			return unAuth(resp, "授权异常");
		}
		if (token == null || claims == null) {
			return unAuth(resp, "请求未授权");
		}

		String userId = String.valueOf(claims.get("UserId"));

		//判断 Token 状态
			String accessToken = jwtHmacUtil.getAccessToken(userId);
			if (!token.equalsIgnoreCase(accessToken)) {
				return unAuth(resp, "令牌已失效");
			}


//		licenseRes = checkLicenseOtherInfo(tenantId, rawPath);
//		if (!licenseRes.isSuccess()) {
//			if (licenseRes.getCode() == LicenseResMsg.ONLINE_USERS_CHECK_FAIL.getCode()) {
//				JwtUtil.removeAccessToken(tenantId, userId);
//			}
//			return unLicense(resp, redirectUrl, licenseRes);
//		}

		return chain.filter(exchange);
	}






	private boolean isSkip(String path) {
		return AuthProvider.getDefaultSkipUrl().stream().anyMatch(pattern -> antPathMatcher.match(pattern, path));
	}

	private Mono<Void> unAuth(ServerHttpResponse resp, String msg) {
		resp.setStatusCode(HttpStatus.UNAUTHORIZED);
		resp.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
		String result = "";
		try {
			result = objectMapper.writeValueAsString(ResponseProvider.unAuth(msg));
		} catch (JsonProcessingException e) {
			log.error(e.getMessage(), e);
		}
		DataBuffer buffer = resp.bufferFactory().wrap(result.getBytes(StandardCharsets.UTF_8));
		return resp.writeWith(Flux.just(buffer));
	}


	private Mono<Void> unLicense(ServerHttpResponse resp, String url, Object data) {
		resp.setStatusCode(HttpStatus.UNAUTHORIZED);
		resp.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
		String result = "";
		try {
			result = objectMapper.writeValueAsString(ResponseProvider.redirect(url, data));
		} catch (JsonProcessingException e) {
			log.error(e.getMessage(), e);
		}
		DataBuffer buffer = resp.bufferFactory().wrap(result.getBytes(StandardCharsets.UTF_8));
		return resp.writeWith(Flux.just(buffer));
	}


	@Override
	public int getOrder() {
		return -100;
	}

}
