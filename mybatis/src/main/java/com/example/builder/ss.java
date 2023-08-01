//package com.example.builder;
//
//import com.example.util.Resources;
//import org.dom4j.Element;
//
//import java.io.InputStream;
//import java.util.List;
//
//public class XMLConfigBuilder extends BaseBuilder {
//
//    /*
//     * <mappers>
//     *	 <mapper resource="org/mybatis/builder/AuthorMapper.xml"/>
//     *	 <mapper resource="org/mybatis/builder/BlogMapper.xml"/>
//     *	 <mapper resource="org/mybatis/builder/PostMapper.xml"/>
//     * </mappers>
//     */
//    private void mapperElement(Element mappers) throws Exception {
//        List<Element> mapperList = mappers.elements("mapper");
//        for (Element e : mapperList) {
//            String resource = e.attributeValue("resource");
//            InputStream inputStream = Resources.getResourceAsStream(resource);
//
//            // 在for循环里每个mapper都重新new一个XMLMapperBuilder，来解析
//            XMLMapperBuilder mapperParser = new XMLMapperBuilder(inputStream, configuration, resource);
//            mapperParser.parse();
//        }
//    }
//
//}
