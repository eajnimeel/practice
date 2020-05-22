package com.example.demo.cache;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.SimpleCacheErrorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.SerializationException;

import java.time.Duration;

/**
 * @EnableCacing 어노테이션을 통해 캐시 활성화
 */
@Configuration
@EnableCaching
@RequiredArgsConstructor
public class CacheConfig extends CachingConfigurerSupport {

    @Value("${spring.cache.redis.key-prefix}")
    private String prefixCacheName;

    @Value("${spring.cache.redis.time-to-live}")
    private long ttl;

    /**
     * 캐시 내용을 사람이 알아보기 쉽게 GenericJackson2JsonRedisSerializer 를 사용<br/>
     * 그루핑을 하기 위한 prefixCacheName 추가<br/>
     * 캐시의 유효시간은 TTL 로 정의됨<br/>
     * 한 캐시 메니저에 속한 모든 데이터의 ttl 은 동일하니, 다른 TTL 을 정의하려면 캐시 메니저를 추가해야함<br/>
     * 스프링 캐시에서는 TTL 을 동적으로 관리하지 않음<br/>
     */
    @Bean
    CacheManager cacheManager(RedisConnectionFactory connectionFactory) {

        RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig().serializeValuesWith(
                RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()))
                .prefixCacheNameWith(prefixCacheName).entryTtl(Duration.ofSeconds(ttl));

        return RedisCacheManager.RedisCacheManagerBuilder.fromConnectionFactory(connectionFactory)
                .cacheDefaults(configuration).build();
    }

    @Override
    @Bean
    public CacheErrorHandler errorHandler() {

        return new SimpleCacheErrorHandler() {

            @Override
            public void handleCacheGetError(RuntimeException exception, Cache cache, Object key) {
                // 캐시에서 역직렬화를 실패한 경우, 에러를 발생시키지 않고 대상 메소드를 그대로 호출함(호출 결과는 캐싱함)
                if (isSerializationError(exception)) {
                    return;
                }

                throw exception;
            }

            private boolean isSerializationError(RuntimeException exception) {
                if (exception instanceof SerializationException) {
                    return true;
                }

                Throwable cause = exception.getCause();
                return (cause != null && cause instanceof SerializationException);
            }
        };
    }
}
