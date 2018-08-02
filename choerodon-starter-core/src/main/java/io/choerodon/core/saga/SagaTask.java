package io.choerodon.core.saga;

import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author flyleft
 * 2018/4/10
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SagaTask {

    /**
     * 任务的code，唯一
     */
    String code();

    /**
     * 描述
     */
    String description() default "";


    /**
     * 所属的saga的code
     */
    String sagaCode();

    /**
     * 该任务的在saga中的执行次序。
     * 越小越先执行
     */
    int seq();

    /**
     * 超时时间
     */
    int timeoutSeconds() default 300;

    /**
     * 最大并发数
     * 并发策略不为null时生效
     */
    int concurrentLimitNum() default 1;

    /**
     *并发策略
     * NONE: 不设置并发限制
     * TYPE: 根据ref_type限制并发
     * TYPE_AND_ID: 根据ref_type和ref_id共同限制并发
     */
    SagaDefinition.ConcurrentLimitPolicy concurrentLimitPolicy() default SagaDefinition.ConcurrentLimitPolicy.NONE;

    /**
     * 超时策略
     */
    SagaDefinition.TimeoutPolicy timeoutPolicy() default SagaDefinition.TimeoutPolicy.RETRY;

    /**
     * 最大重试次数。超时策略为SagaDef.TimeoutPolicy.RETRY时生效
     */
    int maxRetryCount() default 3;

    /**
     * 事务传播行为，默认0，范围-1 ～ 7
     * @return 事务传播行为，默认0，范围-1 ～ 7
     */
    int transactionDefinition() default DefaultTransactionDefinition.PROPAGATION_REQUIRED;


    /**
     * 通过类手动指定输出参数。根据类自动生成。
     * outputSchema优先级 > 优先级 > 方法返回值优先级
     */
    Class<?> outputSchemaClass() default Object.class;

    /**
     * 通过json字符串手动指定输出参数。比如{"name":"wang","age":23}
     * 优先级 > outputSchemaClass()优先级 > 方法返回值优先级。
     * 不为空时会覆盖outputSchemaClass生成的json schema。
     */
    String outputSchema() default "";
}
