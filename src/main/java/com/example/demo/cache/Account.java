package com.example.demo.cache;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 기본 생성자가 있어야만 캐시의 JSON 정보를 객체에 역직렬화 할 수 있음<br>
 * 역직렬화시 GenericJackson2JsonRedisSerializer 가 Reflection 을 이용하여 객체에 매핑하는데, 기본 생성자가 필요<br>
 * 따라서 @NoArgsConstructor 어노테이션을 통해 Default Constructor 를 추가함.
 */
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class Account {
    @Getter
    private int no;
    @Getter
    private String name;
}
