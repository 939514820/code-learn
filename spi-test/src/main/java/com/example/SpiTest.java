package com.example;


public class SpiTest {
    public static void main(String[] args) {
        LoggerService service = LoggerService.getService();
        service.debug("111");
        service.info("1111");
        System.out.println("Hello world!");
    }
//    主要的流程就是：
//
//    通过 URL 工具类从 jar 包的 /META-INF/services 目录下面找到对应的文件，
//    读取这个文件的名称找到对应的 spi 接口，
//    通过 InputStream 流将文件里面的具体实现类的全类名读取出来，
//    根据获取到的全类名，先判断跟 spi 接口是否为同一类型，如果是的，那么就通过反射的机制构造对应的实例对象，
//    将构造出来的实例对象添加到 Providers 的列表中。
}
