package com.genericmethod.productdisco.datafire;

import com.genericmethod.datafire.event.DataFireEvent;
import com.genericmethod.datafire.event.DataFireEventProducer;
import com.genericmethod.productdisco.datafire.enums.ProductHuntEventType;
import com.genericmethod.productdisco.datafire.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductHuntNewPostEventProducer extends DataFireEventProducer<Post, ProductHuntEventType> {

    @Autowired
    Environment environment;

    @Override
    public void sendEvent(List<DataFireEvent<Post, ProductHuntEventType>> events) {

        Twitter twitter = new TwitterTemplate(environment.getProperty("twitter.consumerKey"),
                environment.getProperty("twitter.consumerSecret"),
                environment.getProperty("twitter.accessToken"),
                environment.getProperty("twitter.accessTokenSecret"));

        for (DataFireEvent<Post, ProductHuntEventType> event : events) {
            twitter.timelineOperations().updateStatus(event.getMessage().getName() + " now on @ProductHunt ! " + event.getMessage().getDiscussionUrl());
        }

    }
}
