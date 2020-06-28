package com.qubin.demo;

import java.util.HashMap;
import java.util.Map;

/**
 * author：created by qub
 * date：2020/5/9 13
 * descri：
 */
public class HashDemo {

    public static void main(String[] args) {

    }

    public static int [] twoSum(int [] arr,int target){
        Map<Integer,Integer> map = new HashMap<>();
        int [] temp = new int[2];
        if (arr.length == 0){
            return null;
        }

        for (int i = 0; i < arr.length; i++) {
            int value = target - arr[i];
            if (map.containsKey(value) && map.get(value) != i){
                temp[0] = i;
                temp[1] = map.get(value);
            }else {
                map.put(arr[i],i);
            }
        }
        return temp;
    }

    public static boolean isIsomorphic(String s,String t){
        char[] chars1 = s.toCharArray();
        char[] chars2 = t.toCharArray();
        Map<Character,Integer> map1 = new HashMap<>();
        Map<Character,Integer> map2 = new HashMap<>();
        for (int i = 0; i < chars1.length; i++) {
            if (!map1.containsKey(chars1[i])){
                map1.put(chars1[i],i);
            }

            if (!map2.containsKey(chars2[i])){
                map2.put(chars2[i],i);
            }

            if (map1.get(chars1[i]) != map2.get(chars2[i])){
                return false;
            }
        }
        return true;
    }

    public int findUniqChar(String s){
        char[] chars = s.toCharArray();
        Map<Character,Integer> map = new HashMap<>();
        for (int i = 0; i < chars.length; i++) {
            if (map.containsKey(chars[i])){
                map.put(chars[i],map.get(chars[i]) + 1);
            }else {
                map.put(chars[i],1);
            }
        }

        for (int i = 0; i < chars.length; i++) {
            if (map.get(chars[i]) == 1){
                return i;
            }
        }
        return -1;
    }
}
