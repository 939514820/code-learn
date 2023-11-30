//package com.example.eg;
//
//import com.alibaba.fastjson2.JSONObject;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.sun.xml.internal.ws.util.StringUtils;
//import lombok.extern.slf4j.Slf4j;
//import net.bytebuddy.ByteBuddy;
//import net.bytebuddy.dynamic.DynamicType;
//import net.bytebuddy.implementation.FixedValue;
//import net.bytebuddy.matcher.ElementMatchers;
//
//import java.lang.reflect.Field;
//import java.lang.reflect.Modifier;
//import java.util.Optional;
//
//@Slf4j
//public class T {
//    public Object handleEntity(Object data) {
//        if(data == null) {
//            return data;
//        }
//
//        ObjectMapper mapper = new ObjectMapper();
//
//        String json = "{}";
//        try {
//            //解决@JsonFormat注解解析不了的问题
//            json = mapper.writeValueAsString(data);
//
//        } catch (JsonProcessingException e) {
////            log.error("Json解析失败：" + e);
////            log.error("Json解析失败，data=" + data);
//        }
//
//        DynamicType.Builder<? extends Object> builder = new ByteBuddy()
//                //.redefine(data.getClass())
//                .subclass(data.getClass())
//                //.modifiers(Opcodes.ACC_PUBLIC)
//                //.name("com.builder.vo." + ClassUtil.getClassName(data, true) + "Sub")
//                .name(  "DynamicTypeBuilder")
//                ;
//
//
//        JSONObject jsonObject = JSONObject.parseObject(json);
//
//        for (Field field : data.getClass().getDeclaredFields()) {
//
//            Object o = jsonObject.getObject(field.getName(), field.getType());
//
//            if(o != null) {
//                builder = builder
//                        .defineProperty(field.getName(), field.getType())//声明；getter和setter方法
//                        .method(ElementMatchers.named("get" + StringUtils.capitalize(field.getName())))
//                        .intercept(FixedValue.value(o))
//                ;
//            }
//
////            if (fie ld.getAnnotation(Dict.class) != null) {
////                String dictCode = field.getAnnotation(Dict.class).dictCode();
////                String dictName = field.getAnnotation(Dict.class).dictName();
////                if(StringUtils.isBlank(dictName)) {
////                    dictName = field.getName() + DICT_SUFFIX;
////                }
//
//                String dictValue = String.valueOf(jsonObject.get(field.getName()));
//
//                //翻译字典值对应的text值
////                Optional<String> textValue = Optional.ofNullable(dictCacheService.getText(dictCode, dictValue));
//                //jsonObject.put(dictName, textValue.orElse("未知"));
//
//                builder = builder.defineField("file", String.class, Modifier.PRIVATE)//.value(value)
//                        .defineMethod("get" + "file", String.class, Modifier.PUBLIC)
////                        .intercept(FixedValue.value("textValue".orElse("--")))
//                ;
//
////            }
//
//        }
//
//        Object returnObject = null;
//        try {
//
//            /*
//            //保存生成的类class文件到某个文件夹
//            try {
//                builder.make().saveIn(new File("c:/0"));
//            } catch (IOException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//            */
//
//            returnObject =  builder.make()
//                    .load(getClass().getClassLoader())
//                    //.load(getClass().getClassLoader(), ClassLoadingStrategy.Default.INJECTION)
//                    .getLoaded()
//                    .newInstance()
//            ;
//
//        } catch (InstantiationException e) {
////            log.error("字典转换出错", e);
//
//        } catch (IllegalAccessException e) {
////            log.error("字典转换出错", e);
//        }
//
//        //CglibUtil.copy(data, returnObject);
//
//        return returnObject;
//
//        //return jsonObject;
//        //return data;
//    }
//}
