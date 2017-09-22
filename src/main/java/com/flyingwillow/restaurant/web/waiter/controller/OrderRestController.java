package com.flyingwillow.restaurant.web.waiter.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by liuxuhui on 2017/9/22.
 */
@RestController
@RequestMapping("/waiter")
public class OrderRestController {


    // "/order" create  request body {tableNo:n, details:[]}



    // "/order/detail/{detailId}/" 增加量 put

    // "/order/{orderId}/"  增加菜品  post

    // "/order/detail/{detailId}"  delete

    // "/order/deliver"  上菜 put

    // "/order/{orderId}" get

    // "/order/{orderId}/check" put

}
