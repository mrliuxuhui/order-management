package com.flyingwillow.restaurant.web.admin.controller;

import com.flyingwillow.restaurant.domain.Menu;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by liuxuhui on 2017/9/14.
 */
@RestController
@RequestMapping("/admin/api")
public class MenuRestController {

    @RequestMapping(value = "/menu", method = RequestMethod.GET)
    public ResponseEntity<List<Menu>> listMenu(){

    }

    @RequestMapping(value = "/menu/{menuId}", method = RequestMethod.GET)
    public ResponseEntity<List<Menu>> getMenu(){

    }

    @RequestMapping(value = "/menu/{menuId}", method = RequestMethod.POST)
    public ResponseEntity<List<Menu>> updateMenu(){

    }

    @RequestMapping(value = "/menu", method = RequestMethod.POST)
    public ResponseEntity<List<Menu>> createMenu(){

    }

    @RequestMapping(value = "/menu", method = RequestMethod.DELETE)
    public ResponseEntity<List<Menu>> deleteMenu(){

    }

    @RequestMapping(value = "/menu/{menuId}", method = RequestMethod.DELETE)
    public ResponseEntity<List<Menu>> deleteMenu(@PathVariable Integer menuId){

    }

}
