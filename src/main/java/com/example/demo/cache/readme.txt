스프링 부트와 레디스를 이용한 캐시
spring-boot-starter-cache
spring-boot-starter-data-redis
의존성 필요(아래만 있어도 돌아감)
기본 CacheManager 는 JdkSerializationRedisSerializer.
이걸로 직렬화를 하면 사람이 값을 확인할 수 없어 GenericJackson2JsonRedisSerializer 로 변경한 CacheManager 를 신규로 등록

public 메소드에
@Cacheable 어노테이션을 붙여 메소드의 반환값을 캐시에 등록
@CacheEvict 어노테이션을 붙여 캐시에서 삭제

