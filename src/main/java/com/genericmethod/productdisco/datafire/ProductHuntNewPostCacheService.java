package com.genericmethod.productdisco.datafire;

import com.genericmethod.datafire.cache.DataFireInMemoryCacheService;
import com.genericmethod.productdisco.datafire.model.Post;
import org.springframework.stereotype.Component;

@Component
public class ProductHuntNewPostCacheService extends DataFireInMemoryCacheService<Post> {
}
