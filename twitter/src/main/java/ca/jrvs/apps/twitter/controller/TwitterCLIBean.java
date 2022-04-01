package ca.jrvs.apps.twitter.controller;

import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.dao.HttpHelper;
import ca.jrvs.apps.twitter.service.Service;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TwitterCLIBean {

    public static void main(String[] args) {

    }

    @Bean
    public  TwitterCLIApp twitterCLIApp(Controller controller){

    }

    @Bean
    public Controller controller(Service service){

    }

    @Bean
    public CrdDao crdDao(HttpHelper httpHelper){

    }

    @Bean
    HttpHelper httpHelper(){

    }

}
