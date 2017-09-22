package com.flyingwillow.restaurant.util.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.flyingwillow.restaurant.domain.FieldOrder;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by 刘旭辉 on 2017/9/14.
 */
public class DataTableParam {

    private final static String PARAM_NAME_DRAW = "draw";
    private final static String PARAM_NAME_COLUMNS = "columns";
    private final static String PARAM_NAME_ORDER = "order";
    private final static String PARAM_NAME_START = "start";
    private final static String PARAM_NAME_LENGTH = "length";
    private final static String PARAM_NAME_SEARCH = "search";

    /*------------------DT自动请求的参数(Sent parameters) begin--------------------*/
    /*
     * 绘制计数器。这个是用来确保Ajax从服务器返回的是对应的（Ajax是异步的，因此返回的顺序是不确定的）。 要求在服务器接收到此参数后再返回
     */
    private int draw; // 第几次请求
    /*
     * 第一条数据的起始位置，比如0代表第一条数据
     */
    private int start = Constants.PAGE_START;// 起止位置
    /*
     * 告诉服务器每页显示的条数，这个数字会等于返回的 data集合的记录数，可能会大于因为服务器可能没有那么多数据。
     * 这个也可能是-1，代表需要返回全部数据(尽管这个和服务器处理的理念有点违背)
     */
    private int length = Constants.PAGE_LENGTH; // 数据长度

    /*
     * 全局的搜索条件，条件会应用到每一列（ searchable需要设置为 true ）
     */
    private String search;

    private HashMap<String,HashMap<String,Object>> column;

    private List<FieldOrder> order;

    private final static HashMap<String,IDataTableParamProcessor> processorMap = new HashMap<>();
    private final static List<String> fieldSearch = new ArrayList<>();

    public DataTableParam(String json){
        initProcessors();
        JSONArray params = JSON.parseArray(json);
        if(null!=params){
            parseParam(params);
        }
    }

    private void initProcessors(){

        if(processorMap!=null&&processorMap.size()>0){
            return;
        }

        processorMap.put(PARAM_NAME_DRAW, new IDataTableParamProcessor() {
            @Override
            public void process(JSONObject object) {
                DataTableParam.this.setDraw(object.getInteger("value"));
            }
        });

        processorMap.put(PARAM_NAME_START,new IDataTableParamProcessor() {
            @Override
            public void process(JSONObject object) {
                DataTableParam.this.setStart(object.getInteger("value"));
            }
        });
        processorMap.put(PARAM_NAME_LENGTH,new IDataTableParamProcessor() {
            @Override
            public void process(JSONObject object) {
                DataTableParam.this.setLength(object.getInteger("value"));
            }
        });
        processorMap.put(PARAM_NAME_SEARCH,new IDataTableParamProcessor(){

            @Override
            public void process(JSONObject object) {
                JSONObject value = object.getJSONObject("value");
                if(null!=value){
                    DataTableParam.this.setSearch(value.getString("value"));
                }
            }
        });

        processorMap.put(PARAM_NAME_COLUMNS, new IDataTableParamProcessor(){

            @Override
            public void process(JSONObject object) {
                JSONArray list = object.getJSONArray("value");
                if(list==null||list.isEmpty()){
                    return;
                }
                HashMap<String, HashMap<String, Object>> result = new HashMap<String, HashMap<String, Object>>(list.size());
                for(int i = 0;i<list.size();i++){
                    JSONObject col = list.getJSONObject(i);
                    HashMap<String,Object> column = new HashMap<String, Object>();
                    column.put("index",i);
                    String name = col.getString("name");
                    String data = col.getString("data");
                    data = StringUtils.isNotBlank(data)?name:String.valueOf(i);
                    String search = col.getJSONObject("search").getString("value");
                    column.put("name", StringUtils.isNotBlank(name)?name:String.valueOf(i));
                    column.put("field",data);
                    column.put("searchable",col.getBoolean("searchable"));
                    column.put("orderable",col.getBoolean("orderable"));

                    result.put(data,column);

                    fieldSearch.add(data);
                }
                DataTableParam.this.setColumn(result);
            }
        });

        processorMap.put(PARAM_NAME_ORDER, new IDataTableParamProcessor(){

            @Override
            public void process(JSONObject object) {
                JSONArray list = object.getJSONArray("value");
                if(list==null||list.isEmpty()){
                    return;
                }
                List<FieldOrder> result = new ArrayList<FieldOrder>(list.size());
                for(int i = 0;i<list.size();i++){
                    JSONObject o = list.getJSONObject(i);
                    Integer index = o.getInteger("column");
                    if(null==index||index>=fieldSearch.size()){
                        continue;
                    }
                    String field = fieldSearch.get(index);
                    if(StringUtils.isBlank(field)){
                        continue;
                    }
                    String dir = o.getString("dir");
                    FieldOrder.FieldOrderType type = StringUtils.isNotBlank(dir)&&dir.equalsIgnoreCase("desc")?FieldOrder.FieldOrderType.DESC:FieldOrder.FieldOrderType.ASC;
                    result.add(new FieldOrder(field,type));
                }
                DataTableParam.this.setOrder(result);
            }
        });
    }

    private void parseParam(JSONArray params){
        for(int i = 0; i< params.size(); i++){
            JSONObject one = params.getJSONObject(i);
            String name = one.getString("name");
            IDataTableParamProcessor processor = processorMap.get(name);
            if(null!=processor){
                processor.process(one);
            }
        }
    }

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public int getStart() {
        return start>0?start-1:0;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public HashMap<String, HashMap<String, Object>> getColumn() {
        return column;
    }

    public void setColumn(HashMap<String, HashMap<String, Object>> column) {
        this.column = column;
    }

    public List<FieldOrder> getOrder() {
        return order;
    }

    public void setOrder(List<FieldOrder> order) {
        this.order = order;
    }

    private static interface IDataTableParamProcessor{
        public void process(JSONObject object);
    }
}
