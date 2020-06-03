package lzy.mybatisplus.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@MapperScan("lzy.mybatisplus.mapper")
public class MySpringBootConfig {

    // 乐观锁插件
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }

    // 分页插件
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    // 逻辑删除插件
    @Bean
    public ISqlInjector sqlInjector() {
        return new LogicSqlInjector();
    }

    /**
     * SQL执行 性能分析插件
     * 开发、测试环境使用，生产环境不推荐使用
     *
     * maxTime: SQL执行最大时长，超时则不执行
     * format: SQL是否格式化，默认为false
     *
     * 三种环境
     *   dev：开发环境
     *   test：测试环境
     *   prod：生产环境
     */
    @Bean
    @Profile({"dev","test"}) // 设置 dev test 环境开启
    public PerformanceInterceptor performanceInterceptor() {
        PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
        performanceInterceptor.setMaxTime(500); // 超过500ms，则sql不执行
        performanceInterceptor.setFormat(true);
        return performanceInterceptor;
    }
}
