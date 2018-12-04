package com.newpermission.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EntityFillUtil {

	//static List<Org> orgs = new ArrayList<>();
	
	
	
	
	public static <T> List<Map<String, Object>> gijGoFormatCommonsUtil(List<T> list,String text,String childPropertyName,String idName){
		List<Map<String, Object>> treeList = new ArrayList<>();
		Map<String, Object> treeMap = null;
		//Field id = null;
		//Field name = null;
		//Field children = null;
		Method getIdM = null;
		Method getNameM = null;
		Method getChildrenM = null;
		List<T> childrenList = null;
		for (T obj : list) {
			treeMap = new HashMap<>();
			try {
				Class<?> objClass = obj.getClass();
				//id = objClass.getDeclaredField(idName);
				getIdM = objClass.getMethod("get"+lowerToUpper(idName));
				//children = objClass.getDeclaredField(childPropertyName);
				getChildrenM = objClass.getMethod("get"+lowerToUpper(childPropertyName));
				getNameM = objClass.getMethod("get"+lowerToUpper(text));
				//name = objClass.getDeclaredField(text);
				treeMap.put("id",  getIdM.invoke(obj));
				//treeMap.put("children", getChildrenM.invoke(obj));
				treeMap.put("text",  getNameM.invoke(obj));
				List<T> childrenList1 = (List<T>)getChildrenM.invoke(obj);
				if(childrenList != null && childrenList.size() > 0) {
					treeMap.put("children", gijGoFormatCommonsUtil( childrenList,text,childPropertyName,idName));
				}
				treeList.add(treeMap);
			} catch (SecurityException | IllegalArgumentException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		return treeList;
	}
	
	/**
	 * 
	 * @param list 要封装的对象集合
	 * @param textName text对应的属性名
	 * @param pidPropertyName 父级对象的属性名，如：parentId
	 * @param parentId 父级对象的属性值
	 * @param idName 对象的主键属性名与之对应的是parentId
	 * @return 返回封装好的map集合
	 */
	@SuppressWarnings("unchecked")
	public static <T, E> List<Map<String, Object>> gijGoFormatCommonUtil(List<E> list,String textName,String pidPropertyName, T parentId,String idName){
		List<Map<String, Object>> treeList = new ArrayList<>();
		Map<String, Object> treeMap = null;
		Method getIdM = null;
		Method getNameM = null;
		List<E> tempList = new ArrayList<>();
		tempList.addAll(list);
		Method parentIdMethod = null;
		for (E e : list) {
			Class<?> eClass = e.getClass(); 
			treeMap = new HashMap<>();
			try {
				parentIdMethod = eClass.getMethod("get" + lowerToUpper(pidPropertyName));
				T pid = (T) parentIdMethod.invoke(e);
				T id = null;
				if (parentId.equals(pid)) {
					getIdM = eClass.getMethod("get" + lowerToUpper(idName));
					treeMap.put("id", getIdM.invoke(e));
					id = (T) getIdM.invoke(e);
					getNameM = eClass.getMethod("get" + lowerToUpper(textName));
					treeMap.put("text", getNameM.invoke(e));
					treeList.add(treeMap);
					tempList.remove(e);
					List<Map<String, Object>> resultList = null;
					if (tempList != null && tempList.size() > 0) {
						resultList = gijGoFormatCommonUtil(tempList, textName, pidPropertyName, id, idName);
					}
					if (resultList != null && !resultList.isEmpty()) {
						treeMap.put("children", resultList);
					}
				}
			} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e1) {
				e1.printStackTrace();
			}
		}
		return treeList;
	}
	
	@SuppressWarnings("unchecked")
	public static <T, E> List<Map<String, Object>> vueTreeFormatCommonUtil(List<E> list,String textName,String pidPropertyName, T parentId,String idName){
		List<Map<String, Object>> treeList = new ArrayList<>();
		Map<String, Object> treeMap = null;
		Method getIdM = null;
		Method getNameM = null;
		List<E> tempList = new ArrayList<>();
		tempList.addAll(list);
		Method parentIdMethod = null;
		for (E e : list) {
			Class<?> eClass = e.getClass(); 
			treeMap = new HashMap<>();
			try {
				parentIdMethod = eClass.getMethod("get" + lowerToUpper(pidPropertyName));
				T pid = (T) parentIdMethod.invoke(e);
				T id = null;
				if (parentId.equals(pid)) {
					getIdM = eClass.getMethod("get" + lowerToUpper(idName));
					treeMap.put("id", getIdM.invoke(e));
					id = (T) getIdM.invoke(e);
					getNameM = eClass.getMethod("get" + lowerToUpper(textName));
					treeMap.put("label", getNameM.invoke(e));
					treeList.add(treeMap);
					tempList.remove(e);
					List<Map<String, Object>> resultList = null;
					if (tempList != null && tempList.size() > 0) {
						resultList = vueTreeFormatCommonUtil(tempList, textName, pidPropertyName, id, idName);
					}
					if (resultList != null && !resultList.isEmpty()) {
						treeMap.put("nodes", resultList);
					}
				}
			} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e1) {
				e1.printStackTrace();
			}
		}
		return treeList;
	}
	
	public static String lowerToUpper(String str){
		str.substring(0, 1).toUpperCase();
		return str.substring(0, 1).toUpperCase()+str.substring(1);
	}
	
	public static <T> List<T> getParentsAndChildren(List<T> list,T t, String relationName) {
		
		return null;
	}
	
	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		List<String> list2 = new ArrayList<>();
		list.add("aaaa");
		list.add("bbbb");
		list2.addAll(list);
		list2.remove("bbbb");
		for (String string : list) {
			System.out.println(string);
		}
		for (String string : list2) {
			System.out.println(string);
		}
	}
}
