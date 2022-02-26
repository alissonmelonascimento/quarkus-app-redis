package org.acme;

import io.quarkus.redis.client.RedisClient;

import java.util.Arrays;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.vertx.redis.client.Response;

@Singleton
public class EmployeeService {

    @Inject
    RedisClient redisClient;    

    public void insert(Employee employee) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        String str = mapper.writeValueAsString(employee);
        redisClient.set(Arrays.asList("employee", str ));

        //redisClient.hset(Arrays.asList("employee", str ));
    }

    Employee get(String key) throws JsonMappingException, JsonProcessingException {

        //Response res = redisClient.get(key);

        String str = redisClient.get(key).toString();

        //String str = redisClient.hget(arg0, arg1) (key).toString();

        ObjectMapper mapper = new ObjectMapper();
        Employee emp = mapper.readValue(str, Employee.class);

        return emp;
    } 
    
}
