package priv.austin.config;

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.reactor.context.SaReactorSyncHolder;
import cn.dev33.satoken.reactor.filter.SaReactorFilter;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.server.ServerWebExchange;
import priv.austin.common.api.CommonResult;

/**
 * @author Austin
 * @description 过滤器用于登录认证和权限认证，在setAuth方法中添加路由规则，在setError方法中添加鉴权失败的回调处理
 * @date 2021/8/26 10:18 上午
 */
@Configuration
public class SaTokenConfig{
    /**
     * 注册Sa-Token全局过滤器
     */
    @Bean
    public SaReactorFilter getSaReactorFilter() {
        return new SaReactorFilter()
                // 指定 拦截路由 与 放行路由
                .addInclude("/**").addExclude("/favicon.ico")

                // 认证函数: 每次请求执行
                .setAuth(r -> {
                    //执行SaRouter.stop()后会直接退出匹配链
                    SaRouter.match("/agent/**", SaRouter::stop);
                    // 登录验证 -- 拦截所有路由，并排除/auth/user/login 用于开放登录
                    SaRouter.match("/**", "/auth/user/login", StpUtil::checkLogin);
                     // 更多拦截处理方式，请参考“路由拦截式鉴权”章节
                })

                // 异常处理函数：每次认证函数发生异常时执行此函数
                .setError(e -> {
                    //设置错误返回格式为JSON
                    ServerWebExchange exchange = SaReactorSyncHolder.getContext();
                    exchange.getResponse().getHeaders().set("Content-Type", "application/json; charset=utf-8");
                    return SaResult.error(e.getMessage());
                })
                // 前置函数：在每次认证函数之前执行
                .setBeforeAuth(r -> {
                    // ---------- 设置一些安全响应头 ----------
                    SaHolder.getResponse()
                            // 服务器名称
                            .setServer("sa-server")
                            // 是否可以在iframe显示视图： DENY=不可以 | SAMEORIGIN=同域下可以 | ALLOW-FROM uri=指定域名下可以
                            .setHeader("X-Frame-Options", "SAMEORIGIN")
                            // 是否启用浏览器默认XSS防护： 0=禁用 | 1=启用 | 1; mode=block 启用, 并在检查到XSS攻击时，停止渲染页面
                            .setHeader("X-XSS-Protection", "1; mode=block")
                            // 禁用浏览器内容嗅探
                            .setHeader("X-Content-Type-Options", "nosniff")
                    ;
                });
    }
}
