package com.qubin.demo.mvp2;

/**
 * author：created by qub
 * date：2020/6/11 16
 * descri：
 */
public class DataModel {

    public static BaseModel request(String token){

        BaseModel model = null;

        try {
            model = (BaseModel)Class.forName(token).newInstance();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        return model;
    }
}
