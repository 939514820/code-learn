package com.example.session;

import io.netty.channel.Channel;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author 小傅哥，微信：fustack
 * @description 泛化调用会话工厂接口
 * @github github.com/fuzhengwei
 * @copyright 公众号：bugstack虫洞栈 | 博客：bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public interface IGenericReferenceSessionFactory {

    Future<Channel> openSession() throws ExecutionException, InterruptedException;

}
