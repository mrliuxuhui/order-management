package com.flyingwillow.restaurant.domain;

/**
 * Created by 刘旭辉 on 2017/9/16.
 */
public class FieldOrder {
    private String field;
    private FieldOrderType dir;

    public FieldOrder() {
    }

    public FieldOrder(String field, FieldOrderType dir) {
        this.field = field;
        this.dir = dir;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getDir() {
        return dir.getValue();
    }

    public void setDir(FieldOrderType dir) {
        this.dir = dir;
    }

    public static enum FieldOrderType{
        ASC("ASC"),
        DESC("DESC");


        private String value;

        FieldOrderType(String value){
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}
