package com.example.howard.mp3player.Bean;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.List;
import java.util.Map;

public class FastJsonTools 
{
	public FastJsonTools()
	{
		
	}
	
	public static String createJsonString(Object object) 
	{
		String jsonString = JSON.toJSONString(object);
		return jsonString;
	}
	
	public static <T> T createJsonBean(String jsonString, Class<T> cls)
	{
        T t = null ;
        
        try
		{
        	t = JSON.parseObject(jsonString, cls);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			t = null ;
		}
        
        return t;
    }
	
	public static <T> List<T> createJsonToListBean(String jsonString,Class<T> cls) 
    {
		List<T> list = null ;		
		try
		{
			list = JSON.parseArray(jsonString, cls);

		}
		catch(Exception e)
		{			
		}		
        return list;
    }
	
	public static List<Map<String, Object>> createJsonToListMap(String jsonString)
	{
        List<Map<String, Object>> list2 = JSON.parseObject(jsonString,
                new TypeReference<List<Map<String, Object>>>() {
                });
        return list2;
    }
	
	public static <T> T getJsonBean(String jsonString, Class<T> cls)
	{
		T t = null ;
		try
		{
			t = JSON.parseObject(jsonString,cls);
			
		}
		catch(Exception e)
		{			
		}		
		return t ;
	}
	
}