package com.qubin.demo;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * author：created by qub
 * date：2020/3/30 20
 * descri：
 */
public class Demo {

    public static void main(String[] args) {
        String str = "12332";
        System.out.println('a'- 'A');
        int [] arr = {0,1,2,3,4,5,6,7};
        System.out.println(searchIndexByValue(arr,3));
        

    }

    public static int searchIndexByValue(int [] arr,int value){
        if(arr.length == 0){
            return -1;
        }

        for(int i = 0; i < arr.length ;i++){
            if(arr[i] == value){
                return i;
            }
        }
        return -1;

    }


    public static void insert(int [] arr,int index,int value){

       int [] arr1 = new int [arr.length + 1];
        for (int i = 0; i < index; i++) {
            arr1[i] = arr[i];
        }

        arr1[index] = value;

        for (int i = index; i < arr.length; i++) {
            arr1[i + 1] = arr[i];
        }

        for (int i = 0; i < arr1.length; i++) {
            System.out.print(arr1[i] + " ");
        }

    }

    public static void delByIndex(int [] arr,int index){
        if(arr.length == 0){
            return ;
        }

        if(index > arr.length - 1){
            return ;
        }

        for(int i = index ; i < arr.length - 1 ; i++){
            arr[i] = arr[i + 1];
        }

        arr[arr.length - 1] = 0;

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }

    }

    public static boolean isCircle(String str){
        if (str.length() == 0){
            return false;
        }

        char [] c = str.toCharArray();

        int i = 0;
        int j = c.length - 1;
        while (i < j){
            if (c[i] == c[j]){
                i ++;
                j -- ;
            }else {
                return false;
            }
        }

        return true;
    }

    public static Node addNode(int n){
       Node node = new Node(0);
       Node head = node;
        for (int i = 1; i < n; i++) {
            head.next = new Node(i);
            head = head.next;
        }
        return head;
    }

    public static Node reserveNode(Node head){

       if (head == null || head.next == null){
           return head;
       }
       Node pre = null;
       Node next = null;
      while(head != null){
          next = head.next;
          head.next = pre;
          pre = head;
          head = next;
      }
      return pre;

    }

    public static Node reverseList(Node node) {
        Node pre = null;
        Node next = null;
        while (node != null) {
            next = node.next;
            node.next = pre;
            pre = node;
            node = next;
        }
        return pre;
    }

    public static boolean isHaveCire(Node node){
        if (node == null){
            return false;
        }

        Node p = node;
        Node q = node;
        while(p.next != null && q.next.next != null){
            p = p.next;
            q = q.next.next;

            if (p == q){
                return true;
            }
        }
        return false;
    }

    public static Node getMiddleNode(Node node){
        if (node == null){
            return null;
        }

        Node fast = node;
        Node slow = node;
        while (fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }

        return slow;
    }

    public static Node deleteNodeByIndex(Node node,int index){
        if (node == null){
            return node;
        }

        Node fast = node;
        Node slow = node;
        int n = 0;
        while (n < index){
            fast = fast.next;
            n ++;
        }

        while(fast != null && fast.next != null){
            fast = fast.next;
            slow = slow.next;
        }

        slow.next = slow.next.next;
        return slow;

    }

    public static Node mergeTwoLink(Node p,Node q){
        if (p == null){
            return q;
        }
        if (q == null){
            return p;
        }

        if (p == null && q == null){
            return null;
        }

        Node head = null;

        if (p.data > q.data){
            head = q;
            head.next = mergeTwoLink(p,q.next);
        }else {
            head = p;
            head.next = mergeTwoLink(p.next,q);
        }
        return head;
    }

    public int [] twoSum(int [] arr,int target){
        if (arr.length == 0){
            return null;
        }

        Map<Integer,Integer> map = new HashMap<>();
        int [] a = new int[2];
        for (int i = 0;i < arr.length;i++){
            int value = target - arr[i];
            if (map.containsKey(value) && map.get(value) != i){
                a[0] = value;
                a[1] = arr[i];
                break;
            }

            map.put(arr[i],i);

        }
        return a;
    }

    public int firstUniqChar(String s){
        if (s.length() == 0){
            return -1;

        }

        char [] c = s.toCharArray();
        Map<Character,Integer> map = new HashMap<>();
        for (int i = 0; i < c.length; i++) {
            if (!map.containsKey(c[i])){
                map.put(c[i],1);
            }else {
                map.put(c[i],map.get(c[i]) + 1);
            }


        }

        for (int i = 0; i < c.length;i++){
            if (map.get(c[i]) == 1){
                return i;
            }
        }


            return -1;


    }



}

class Node{
    public int data;
    public Node next;

    public Node(int data){
        this.data = data;
    }
}
