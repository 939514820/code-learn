package com.example.session;

import com.example.defaults.GenericReferenceSessionFactory;
import io.netty.channel.Channel;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author 小傅哥，微信：fustack
 * @description 会话工厂建造类
 * @github github.com/fuzhengwei
 * @copyright 公众号：bugstack虫洞栈 | 博客：bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class GenericReferenceSessionFactoryBuilder {

    public Future<Channel> build(Configuration configuration) {
        IGenericReferenceSessionFactory genericReferenceSessionFactory = new GenericReferenceSessionFactory(configuration);
        try {
            return genericReferenceSessionFactory.openSession();
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
