package io.github.px86.iothomecontrol.deviceregistry;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisTemplateConfig {

  @Value("${redis.host}")
  private String redisHost;

  @Value("${redis.port}")
  private Integer redisPort;

  @Bean
  public LettuceConnectionFactory lettuceConnectionFactory() {
    LettuceConnectionFactory connectionFactory = new LettuceConnectionFactory(redisHost, redisPort);
    connectionFactory.afterPropertiesSet();
    return connectionFactory;
  }

  @Bean
  public RedisTemplate<String, String> redisTemplate() {
    RedisTemplate<String, String> template = new RedisTemplate<>();
    template.setConnectionFactory(lettuceConnectionFactory());
    template.setDefaultSerializer(StringRedisSerializer.UTF_8);
    template.afterPropertiesSet();
    return template;
  }
}
