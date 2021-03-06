package com.genericmethod.productdisco.datafire;

import com.genericmethod.datafire.cache.CacheService;
import com.genericmethod.datafire.event.DataFireEvent;
import com.genericmethod.datafire.event.DataFireEventNotifier;
import com.genericmethod.datafire.event.DataFireEventProducer;
import com.genericmethod.productdisco.datafire.enums.ProductHuntEventType;
import com.genericmethod.productdisco.datafire.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class ProductHuntNewPostEventNotifier extends DataFireEventNotifier<Post,ProductHuntEventType> {

    @Autowired
    private ProductHuntNewPostCacheService productHuntNewPostCacheService;

    @Autowired
    private ProductHuntNewPostEventProducer productHuntEventProducer;


    @Override
    public CacheService getCacheService() {
        return productHuntNewPostCacheService;
    }

    @Override
    public DataFireEventProducer getEventProducer() {
        return productHuntEventProducer;
    }

    @Override
    public List<DataFireEvent<Post, ProductHuntEventType>> getEvent(Post cachedObj, Post dataObject) {

        List<DataFireEvent<Post, ProductHuntEventType>> events = new ArrayList<>();

        if (cachedObj == null && dataObject != null){
            events.add(new DataFireEvent<>(dataObject, ProductHuntEventType.NEW_PRODUCT_ADDED));
        }
        return events;
    }
}
