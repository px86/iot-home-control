package io.github.px86.iothomecontrol.deviceregistry;

import java.time.Duration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RegistryService {

  private RedisTemplate<String, String> redisTemplate;

  @Autowired
  public RegistryService(RedisTemplate<String, String> redisTemplate) {
    this.redisTemplate = redisTemplate;
  }

  public void set(String key, String value) {
    set(key, value, Duration.ofSeconds(5L));
  }

  public void set(String key, String value, Duration expiresIn) {
    this.redisTemplate.opsForValue().set(key, value, expiresIn);
  }

  public String get(String key) {
    return this.redisTemplate.opsForValue().get(key);
  }
}
