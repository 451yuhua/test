package com.newpermission.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EntityFillUtil {

	private final static String CHILDRENKEY = "nodes";
	private final static String IDKEY = "id";
	private final static String NAMEKEY = "label";
	
	
	
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
	
	/**
	 * 通过节点的父id如parentId封装其所有的子节点树
	 * @param list 所有节点集合
	 * @param textName 节点展示的文本对应的属性名
	 * @param pidPropertyName 节点的父id对应的属性名
	 * @param parentId 节点的父id值
	 * @param idName 节点id对应的属性名
	 * @return
	 */
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
					treeMap.put(IDKEY, getIdM.invoke(e));
					id = (T) getIdM.invoke(e);
					getNameM = eClass.getMethod("get" + lowerToUpper(textName));
					treeMap.put(NAMEKEY, getNameM.invoke(e));
					treeList.add(treeMap);
					tempList.remove(e);
					List<Map<String, Object>> resultList = null;
					if (tempList != null && tempList.size() > 0) {
						resultList = vueTreeFormatCommonUtil(tempList, textName, pidPropertyName, id, idName);
					}
					if (resultList != null && !resultList.isEmpty()) {
						treeMap.put(CHILDRENKEY, resultList);
					}
				}
			} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e1) {
				e1.printStackTrace();
			}
		}
		return treeList;
	}
	
	/**
	 * 将所有的父节点以及封装好的子节点list再次进行封装
	 * @param parents 父节点集合
	 * @param textName 节点展示的文本对应的属性名
	 * @param pidPropertyName 该节点的父节点属性名，如：parentId
	 * @param parentId 该节点的父节点id值
	 * @param idName 节点的id对应属性名
	 * @param children 封装好的子节点集合
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T, E> List<Map<String, Object>> vueTreeFormatFromNodeUtil(List<E> parents,String textName,
			String pidPropertyName, T parentId,String idName, List<Map<String, Object>> children){
		
		if (parents == null || parents.size() == 0) {
			return children;
		}
		List<Map<String, Object>> treeList = new ArrayList<>();
		List<Map<String, Object>> tempTreeList = children;
		Map<String, Object> treeMap = null;
		Method getIdM = null;
		Method getNameM = null;
		Method parentIdMethod = null;
		for (E e : parents) {
			Class<?> eClass = e.getClass(); 
			treeMap = new HashMap<>();
			try {
				parentIdMethod = eClass.getMethod("get" + lowerToUpper(pidPropertyName));
				getIdM = eClass.getMethod("get" + lowerToUpper(idName));
				T pid = (T) parentIdMethod.invoke(e);
				T id = (T) getIdM.invoke(e);
				if (parentId.equals(id)) {
					treeList = new ArrayList<>();
					treeMap.put(IDKEY, id);
					getNameM = eClass.getMethod("get" + lowerToUpper(textName));
					treeMap.put(NAMEKEY, getNameM.invoke(e));
					treeList.add(treeMap);
					if (!tempTreeList.isEmpty()) {
						treeMap.put(CHILDRENKEY, tempTreeList);
					}
					tempTreeList = new ArrayList<>();
					tempTreeList.addAll(treeList);
					parentId = pid;
				}
			} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e1) {
				e1.printStackTrace();
			}
		}
		return treeList;
	}
	
	/**
	 * 首字母大写工具
	 * @param str
	 * @return
	 */
	public static String lowerToUpper(String str){
		str.substring(0, 1).toUpperCase();
		return str.substring(0, 1).toUpperCase()+str.substring(1);
	}
	
	/**
	 * 通过节点id获取其所有父节点的集合
	 * @param list 所有的节点集合
	 * @param clazz 节点类型
	 * @param parentName 父节点id的属性名如：parentId
	 * @param idName 节点id属性名
	 * @param targetId 节点id值
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T, E> List<T> getParents(List<T> list,Class<?> clazz , String parentName, String idName, E targetId) {
		List<T> tList = new ArrayList<>();
		List<T> tempList = new ArrayList<>();
		tempList.addAll(list);
		for (T t : list) {
			try {
				Method idMethod = clazz.getMethod("get"+lowerToUpper(idName));
				Method parentMethod = clazz.getMethod("get"+lowerToUpper(parentName));
				E id = (E) idMethod.invoke(t);
				if (targetId.equals(id)) {
					tList.add(t);
					tempList.remove(t);
					E pid = (E) parentMethod.invoke(t);
					if(null != pid) {
						tList.addAll(getParents(tempList,clazz,parentName,idName,pid));
					}
					break;
				}
			} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
			
		}
		return tList;
	}
	
	/**
	 * 通过中间的节点获取其所有父节点以及所有子节点
	 * @param list 所有的节点集合
	 * @param clazz 节点类型
	 * @param textName 属性菜单展示的文本属性名
	 * @param pidPropertyName 父节点id的属性名
	 * @param id 该节点的ID值
	 * @param idName 该节点的id属性名
	 * @return 返回树形结构集合
	 */
	public static <T, E> List<Map<String, Object>> treeFormatByNode(List<E> list,Class<?> clazz ,String textName,String pidPropertyName, T id,String idName) {
		List<E> parentList = getParents(list, clazz, pidPropertyName, idName, id);
		List<Map<String, Object>> children = vueTreeFormatCommonUtil(list, textName, pidPropertyName, id, idName);
		return vueTreeFormatFromNodeUtil(parentList, textName, pidPropertyName, id, idName, children);
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
