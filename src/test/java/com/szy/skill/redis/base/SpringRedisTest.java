/**
 * Copyright (C), 2014-2019, 深圳兔展智能科技有限公司
 * FileName: SpringRedisTest
 * Author:   EDZ
 * Date:     2019/6/18 15:46
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.szy.skill.redis.base;

import com.sun.org.apache.regexp.internal.RE;
import com.szy.skill.redis.RedisSkillApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.*;
import java.text.MessageFormat;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author EDZ
 * @create 2019/6/18
 * @since 1.0.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RedisSkillApplication.class)
@Slf4j
public class SpringRedisTest {

    @Autowired
    StringRedisTemplate redisTemplate;

    @Test
    public void test4Set() {
        redisTemplate.opsForValue().set(MessageFormat.format("szy:spring:redis:{0}", 1), "深圳", 10, TimeUnit.SECONDS);
    }

    @Test
    public void test4Sub() {
        redisTemplate.boundValueOps(MessageFormat.format("szy:spring:redis:{0}", 2)).increment(-1);
    }

    @Test
    public void test4Get() {
        String str = redisTemplate.opsForValue().get(MessageFormat.format("szy:spring:redis:{0}", 1));
        log.info(str);
    }

    /**
     * 注意 获取的实时过期时间
     */
    @Test
    public void test4GetExpire() {
        long expire = redisTemplate.getExpire(MessageFormat.format("szy:spring:redis:{0}", 1));
        log.info(expire + "");
    }

    @Test
    public void test4Delete() {
        // >10ms  删除操作很慢？
        boolean deleted = redisTemplate.delete(MessageFormat.format("szy:spring:redis:{0}", 2));
        log.info(deleted + "");
    }

    @Test
    public void test4CheckKey() {
        boolean hasKey = redisTemplate.hasKey(MessageFormat.format("szy:spring:redis:{0}", 2));
        log.info(hasKey + "");
    }

    @Test
    public void test4SetCollection() {
        redisTemplate.opsForSet().add(MessageFormat.format("szy:spring:redis:{0}", 3), "北京", "上海");
    }

    @Test
    public void test4SetExpire() {
        redisTemplate.expire(MessageFormat.format("szy:spring:redis:{0}", 3), 10, TimeUnit.SECONDS);
    }

    @Test
    public void test4SetContainData() {
        boolean isMember = redisTemplate.opsForSet().isMember(MessageFormat.format("szy:spring:redis:{0}", 3), "上海");
        log.info(isMember + "");
    }

    @Test
    public void test4GetSetMembers() {
        Set<String> members = redisTemplate.opsForSet().members(MessageFormat.format("szy:spring:redis:{0}", 3));
        members.stream().forEach(member -> log.info(member));
    }

}