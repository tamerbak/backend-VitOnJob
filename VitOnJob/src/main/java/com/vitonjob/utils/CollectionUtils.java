package com.vitonjob.utils;

import java.util.Collection;

public class CollectionUtils {
	
	public static boolean isNotEmpty(Collection<?> collection){
		return collection != null && collection.size() > 0;
	}
	
	public static boolean isEmpty(Collection<?> collection){
		return collection == null || collection.size() == 0;
	}

}
