package com.flyingwillow.restaurant.web.admin.controller;

import com.flyingwillow.restaurant.domain.FieldOrder;
import com.flyingwillow.restaurant.domain.Menu;
import com.flyingwillow.restaurant.service.IMenuService;
import com.flyingwillow.restaurant.util.web.Constants;
import com.flyingwillow.restaurant.util.web.DataTableParam;
import com.flyingwillow.restaurant.util.web.DataTableResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.List;

/**
 * Created by liuxuhui on 2017/9/14.
 */
@RestController
@RequestMapping("/admin/api")
public class MenuRestController {

    @Autowired
    private IMenuService menuService;

    @RequestMapping(value = "/menu", method = RequestMethod.GET)
    public ResponseEntity<DataTableResponse<Menu>> listMenu(@RequestParam(required = false)String dataTableParam){

        DataTableParam param = null;
        if(StringUtils.isNotBlank(dataTableParam)){
            param = new DataTableParam(dataTableParam);
        }

        List<Menu> list = null;
        Integer total = 0;
        if(null==param){
            list = menuService.getMenuList(null, Constants.PAGE_START,Constants.PAGE_LENGTH);
            total = menuService.getMenuCount(null);
        }else{

            String search = param.getSearch();
            HashMap<String,Object> query = new HashMap<>();
            if(StringUtils.isNotBlank(search)){
                if(search.matches("\\d+")){
                    query.put("id",Integer.parseInt(search));
                }else{
                    query.put("name",search);
                }
            }

            List<FieldOrder> orders = param.getOrder();
            if(null!=orders&&orders.size()>0){
                query.put("orders",orders);
            }

            list = menuService.getMenuList(query,param.getStart(),param.getLength());
            total = menuService.getMenuCount(query);

        }

        if(null==list){
            return new ResponseEntity<DataTableResponse<Menu>>(HttpStatus.NO_CONTENT);
        }else{
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
            DataTableResponse<Menu> response = new DataTableResponse<Menu>(null!=param?param.getDraw():1,total,total,list);
            return new ResponseEntity<DataTableResponse<Menu>>(response,headers,HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/menu/{menuId}", method = RequestMethod.GET)
    public ResponseEntity<Menu> getMenu(@PathVariable Integer menuId){

        if(null==menuId){
            return new ResponseEntity<Menu>(HttpStatus.BAD_REQUEST);
        }

        Menu menu = menuService.getMenuById(menuId);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return new ResponseEntity<Menu>(menu,headers,HttpStatus.OK);

    }

    @RequestMapping(value = "/menu/{menuId}", method = RequestMethod.PUT)
    public ResponseEntity<Menu> updateMenu(@PathVariable Integer menuId, Menu menu){

        if(null==menuId){
            return new ResponseEntity<Menu>(HttpStatus.BAD_REQUEST);
        }
        menu.setId(menuId);
        menuService.updateMenu(menu);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return new ResponseEntity<Menu>(menu,headers,HttpStatus.OK);
    }

    @RequestMapping(value = "/menu", method = RequestMethod.POST)
    public ResponseEntity<Void> createMenu(Menu menu, UriComponentsBuilder ucBuilder){

        menuService.saveMenu(menu);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/menu/{id}").buildAndExpand(menu.getId()).toUri());
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/menu", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteMenu(@RequestBody List<Integer> menuIds){
        if(null==menuIds||menuIds.isEmpty()){
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }
        menuService.deleteMenuByIds(menuIds);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/menu/{menuId}", method = RequestMethod.DELETE)
    public ResponseEntity<Menu> deleteMenu(@PathVariable Integer menuId){
        if(null==menuId){
            return new ResponseEntity<Menu>(HttpStatus.BAD_REQUEST);
        }
        menuService.deleteMenu(menuId);
        return new ResponseEntity<Menu>(HttpStatus.OK);
    }

}
