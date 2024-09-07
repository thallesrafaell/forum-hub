package dev.thallesrafael.forumhub.validations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class ValidadorJwtInvalidado {


    private final StringRedisTemplate redisTemplate;


    @Autowired
    public ValidadorJwtInvalidado(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void validar(String token){
        var tokenValidado =  redisTemplate.hasKey(token);
        if (tokenValidado){
            throw new RuntimeException("Não autorizado!");
        }
    }

}
