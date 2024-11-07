package io.github.px86.iothomecontrol.deviceregistry;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
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

  public Map<String, String> getAll() {
    Set<String> keys = this.redisTemplate.keys("*");
    if (keys == null) {
      return Map.of();
    }
    Map<String, String> keyVals = new HashMap<>();
    for (String key : keys) {
      keyVals.put(key, get(key));
    }
    return keyVals;
  }
}
