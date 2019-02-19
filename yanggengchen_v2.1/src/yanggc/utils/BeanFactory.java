package yanggc.utils;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import yanggc.service.ProductService;

public class BeanFactory {
	
	private static SAXReader reader = null;
	private static Document doc = null;
	
	static{
		//dom4j解析
		reader = new SAXReader();
		//加载bean.xml配置文件  
		String path = BeanFactory.class.getClassLoader().getResource("bean.xml").getPath();
		try {
			doc = reader.read(path);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

	public static Object getBean(String id) {
		
		try {
			//<bean id="productService" class="com.itheima.service.impl.ProductServiceImpl"></bean>
			Element element = (Element) doc.selectSingleNode("//bean[@id='"+id+"']");//xpath语法
			String className = element.attributeValue("class");
			
			//反射创建对象
			Class clazz = Class.forName(className);
			return clazz.newInstance();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return null;
	}

}
