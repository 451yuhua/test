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
	
	@SuppressWarnings("unchecked")
	public static <T, E> List<Map<String, Object>> gijGoFormatCommonUtil(List<E> list,String text,String pidPropertyName, T parentId,String idName){
		List<Map<String, Object>> treeList = new ArrayList<>();
		Map<String, Object> treeMap = null;
//		//Field id = null;
//		//Field name = null;
//		//Field children = null;
		Method getIdM = null;
		Method getNameM = null;
//		Method getChildrenM = null;
//		List<T> childrenList = null;
		Method parentIdMethod = null;
		for (E e : list) {
			Class<?> eClass = e.getClass(); 
			try {
				parentIdMethod = eClass.getMethod("get" + lowerToUpper(pidPropertyName));
				T pid = (T) parentIdMethod.invoke(e);
				if (parentId.equals(pid)) {
					getIdM = eClass.getMethod("get" + lowerToUpper(idName));
					treeMap.put("id", getIdM.invoke(e));
					getNameM = eClass.getMethod("get" + lowerToUpper(text));
				}
			} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e1) {
				e1.printStackTrace();
			}
		}
		/*for (T obj : list) {
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
		}*/
		return null;
	}
	
	public static String lowerToUpper(String str){
		str.substring(0, 1).toUpperCase();
		return str.substring(0, 1).toUpperCase()+str.substring(1);
	}
	
	public static void main(String[] args) {
//		Org org1 = new Org();
//		Org org2 = new Org();
//		Org org3 = new Org();
//		Org org4 = new Org();
//		Org newOrg = new Org();
//		org1.setLevel(3);
//		org1.setOrgName("3");
//		org2.setLevel(4);
//		org2.setOrgName("4.1");
//		org3.setLevel(4);
//		org3.setOrgName("4.2");
//		org4.setLevel(5);
//		org4.setOrgName("5");
//		newOrg.setOrgName("new3");
//		newOrg.setLevel(7);
//		List<Org> orgChildren = new ArrayList<>();
//		List<Org> orgChildChildren = new ArrayList<>();
//		orgChildren.add(org2);
//		orgChildren.add(org3);
//		orgChildChildren.add(org4);
//		org1.setOrgs(orgChildren);
//		org2.setOrgs(orgChildChildren);
		//List<Org> list = changeList(org1, 7);
		//list.set(0, newOrg);
		//for (Org o : list) {
			//System.out.println(o.getOrgName()+"de "+"level:"+o.getLevel());
		//}
		/*List<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.set(0, 4);
		System.out.println("共有"+list.size());
		for (Integer i : list) {
			System.out.println(i);
		}*/
	}
}
