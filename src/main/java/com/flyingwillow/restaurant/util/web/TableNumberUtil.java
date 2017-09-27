package com.flyingwillow.restaurant.util.web;

import com.flyingwillow.restaurant.util.Config;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuxuhui on 2017/9/27.
 */
public class TableNumberUtil {

    public static List<Integer> getAllTableNumberList(){
        String numbers = Config.gets("table.number.list");
        if(numbers.contains("-")){
            List<Integer> list = new ArrayList<>();
            String[] nums = numbers.split("-");
            int start = Integer.parseInt(nums[0]);
            int end = Integer.parseInt(nums[1]);
            for(int i = start;i<=end;i++){
                list.add(i);
            }

            return list;
        }

        return null;
    }
}
