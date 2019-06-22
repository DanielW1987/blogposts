package rocks.danielw.ehcache.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CalculationServiceTest {

  @Autowired
  private CacheManager cacheManager;

  @Autowired
  private CalculationService calculationService;

  @Test
  void areaOfCircle() {
    assertNotNull(cacheManager);
    assertNotNull(calculationService);

    Cache cache = cacheManager.getCache("areaOfCircleCache");
    assertNotNull(cache);
    cache.clear();
    assertNull(cache.get(6));

    double result = calculationService.areaOfCircle(6);

    Double cacheResult = cache.get(6, Double.class);
    assertEquals(Double.valueOf(result), cacheResult);

  }
}