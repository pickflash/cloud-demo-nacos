package cn.itcast.gateway;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/*全局过滤器*/
@Component
public class AuthorizeFilter implements GlobalFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        /*获取请求参数*/
        MultiValueMap<String, String> queryParams = exchange.getRequest().getQueryParams();;

        /*获取authorization参数*/
        String authorization = queryParams.getFirst("authorization");

        /*校验*/
        if("admin".equals(authorization)){
            /*放行*/
            return chain.filter(exchange);
        }
        /*拦截,并禁止访问*/
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);

        /*结束处理*/
        return exchange.getResponse().setComplete();
    }
}
