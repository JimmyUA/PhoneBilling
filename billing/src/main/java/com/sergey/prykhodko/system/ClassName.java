package com.sergey.prykhodko.system;

public class ClassName {
    public static String getCurrentClassName(){
        try {
            throw new RuntimeException();
        }catch (RuntimeException e){
            return e.getStackTrace()[1].getClassName();
        }
    }
}