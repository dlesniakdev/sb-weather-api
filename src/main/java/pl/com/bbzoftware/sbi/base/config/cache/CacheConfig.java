package pl.com.bbzoftware.sbi.base.config.cache;

import com.google.common.cache.CacheBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@EnableCaching
@Configuration
public class CacheConfig extends CachingConfigurerSupport {

  @Value("${cache.ttl}")
  private Integer cacheTtl;

  @Override
  public CacheManager cacheManager() {
    testMethod();
    return new ConcurrentMapCacheManager() {
      @Override
      protected Cache createConcurrentMapCache(final String name) {
        return new ConcurrentMapCache(name,
            CacheBuilder.newBuilder()
                .expireAfterWrite(cacheTtl, TimeUnit.MINUTES)
                .maximumSize(100)
                .build()
                .asMap(), false);
      }
    };
  }
  
  public void testMethod() {
    String s = "";
    IntStream.range(0, 1000).forEach(i ->
      IntStream.range(0, 1000).forEach(j -> 
        s = s + "Root loop: " + i + "\nInner loop: " + j + "\n";
      )
    );
    
    System.out.println(s);
    
    try {
    File file = new File("temp");
    InputStream inputStream = new FileInputStream(file);
    byte[] content = IOUtils.toByteArray(inputStream);
    } catch (Exception ex) {
    } finally {
      inputStream.close();
    }
  }
}
