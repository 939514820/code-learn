/*
 *    Copyright 2009-2023 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       https://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package com.example.reflection;

import com.example.reflection.invoker.Invoker;


import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;

/**
 * @author Clinton Begin
 */
public class MetaClass {


    private Reflector reflector;

    private MetaClass(Class<?> type) {
//      this.reflector = Reflector.forClass(type);
    }

    public static MetaClass forClass(Class<?> type) {
      return new MetaClass(type);
    }

    public String[] getGetterNames() {
      return reflector.getGetablePropertyNames();
    }

    public String[] getSetterNames() {
      return reflector.getSetablePropertyNames();
    }

    public Invoker getGetInvoker(String name) {
      return reflector.getGetInvoker(name);
    }

    public Invoker getSetInvoker(String name) {
      return reflector.getSetInvoker(name);
    }

    // ... 方法包装

}
