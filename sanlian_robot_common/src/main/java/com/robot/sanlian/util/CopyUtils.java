package com.robot.sanlian.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.List;

/** 
 * 实现两个实体类属性之间的复制 (若source中字段属性值为null或者为空字符串，则不复制）
 * source :
 * target：将source数据复制到target中去
 * @author lvp
 *  
 */  
public class CopyUtils {  
  
    public static void Copy(Object source, Object target) throws Exception {  
        // 获取属性  
        BeanInfo sourceBean = Introspector.getBeanInfo(source.getClass(),  
                java.lang.Object.class);  
        PropertyDescriptor[] sourceProperty = sourceBean  
                .getPropertyDescriptors();  
  
        BeanInfo destBean = Introspector.getBeanInfo(target.getClass(),  
                java.lang.Object.class);  
        PropertyDescriptor[] destProperty = destBean.getPropertyDescriptors();  
  
        try {  
            for (int i = 0; i < sourceProperty.length; i++) {  
                for (int j = 0; j < destProperty.length; j++) {  
                    if (sourceProperty[i].getName().equals(  
                            destProperty[j].getName()) && (sourceProperty[i].getReadMethod()!=null 
                            && sourceProperty[i].getReadMethod().invoke(source)!=null 
                            && destProperty[j].getWriteMethod()!=null)) {  
                        // 调用source的getter方法和target的setter方法  
//                    	System.out.println(" destProperty[j].getWriteMethod():"+ destProperty[j].getName()+",sourceProperty[i].getReadMethod().invoke(source): "+sourceProperty[i].getReadMethod()  
//                                .invoke(source));
                        destProperty[j].getWriteMethod().invoke(
                        		target,  
                                sourceProperty[i].getReadMethod()  
                                        .invoke(source));  
                        break;  
                    }  
                }  
            }  
        } catch (Exception e) {  
        	e.printStackTrace();
            throw new Exception("属性复制失败:" + e.getMessage());  
        }  
    }  
    
    
    
    /**
     * 将source集合copy到空集合target中
     * @param source
     * @param target
     * @throws Exception
     */
    public static void CopyList(List<Object> source, List<Object> target) throws Exception {  
		    try {  
		    	for(int m=0;m<source.size();m++){
		    		   Object sr=source.get(m);
		    		   Object tr=target.get(m);
		    		   BeanInfo sourceBean = Introspector.getBeanInfo(sr.getClass(),  
		    	                java.lang.Object.class);  
		    		   PropertyDescriptor[] sourceProperty = sourceBean  
		    	                .getPropertyDescriptors(); 
		    		   BeanInfo destBean = Introspector.getBeanInfo(tr.getClass(),  
		    	                java.lang.Object.class);  
		    	        PropertyDescriptor[] destProperty = destBean.getPropertyDescriptors();  
		                for (int i = 0; i < sourceProperty.length; i++) {  
		                    for (int j = 0; j < destProperty.length; j++) {  
		                        if (sourceProperty[i].getName().equals(  
		                                destProperty[j].getName()) && (sourceProperty[i].getReadMethod()!=null && sourceProperty[i].getReadMethod().invoke(source)!=null && destProperty[j].getWriteMethod()!=null)) {  
		                            // 调用source的getter方法和target的setter方法  
		//                        	System.out.println(" destProperty[j].getWriteMethod():"+ destProperty[j].getName()+",sourceProperty[i].getReadMethod().invoke(source): "+sourceProperty[i].getReadMethod()  
		//                                    .invoke(source));
		                            destProperty[j].getWriteMethod().invoke(
		                            		target,  
		                                    sourceProperty[i].getReadMethod()  
		                                            .invoke(source));  
		                            break;  
		                        }  
		                    }  
		                } 
		    		}
		        } catch (Exception e) {  
		        	e.printStackTrace();
		            throw new Exception("属性复制失败:" + e.getMessage());  
		        }  
		    }  
}  

