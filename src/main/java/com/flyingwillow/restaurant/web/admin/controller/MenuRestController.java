package com.flyingwillow.restaurant.web.admin.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.flyingwillow.restaurant.domain.FieldOrder;
import com.flyingwillow.restaurant.domain.Menu;
import com.flyingwillow.restaurant.service.IMenuService;
import com.flyingwillow.restaurant.util.web.Constants;
import com.flyingwillow.restaurant.util.web.DataTableParam;
import com.flyingwillow.restaurant.util.web.DataTableResponse;
import com.flyingwillow.restaurant.util.web.FileUploadUtil;
import com.flyingwillow.restaurant.util.web.WebUtil;
import com.flyingwillow.restaurant.web.admin.dto.MenuDTO;
import com.flyingwillow.restaurant.web.admin.vo.JsonResponseStatus;
import com.flyingwillow.restaurant.web.admin.vo.MenuVO;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.ArrayList;
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

    @RequestMapping(value = "/menu", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public ResponseEntity<DataTableResponse<Menu>> listMenu(@RequestParam(required = false)String dataTableParam){

        DataTableParam param = null;
        if(StringUtils.isNotBlank(dataTableParam)){
            param = new DataTableParam(dataTableParam);
        }

        List<Menu> list = null;
        Integer total = 0;
        if(null==param){
            list = menuService.getMenuList(null, (Constants.PAGE_START-1)*Constants.PAGE_LENGTH,Constants.PAGE_LENGTH);
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
        DataTableResponse<Menu> response = new DataTableResponse<Menu>(null!=param?param.getDraw():1,total,total,list);

        if(null==list){
            return new ResponseEntity<DataTableResponse<Menu>>(response,HttpStatus.NO_CONTENT);
        }else{

            return new ResponseEntity<DataTableResponse<Menu>>(response,HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/menu/search", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public ResponseEntity searchMenu(@RequestParam(required = false)String q){

        List<MenuVO> list = new ArrayList<>();
        if(StringUtils.isBlank(q)){
            return new ResponseEntity(list,HttpStatus.OK);
        }
        List<Menu> menus = menuService.searchMenuByKeywords(q,0,20);
        for(Menu menu : menus){
            list.add(new MenuVO(menu));
        }
        return new ResponseEntity(list,HttpStatus.OK);
    }

    @RequestMapping(value = "/menu/{menuId}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public ResponseEntity<Menu> getMenu(@PathVariable Integer menuId){

        if(null==menuId){
            return new ResponseEntity<Menu>(new Menu(),HttpStatus.BAD_REQUEST);
        }

        Menu menu = menuService.getMenuById(menuId);

        return new ResponseEntity<Menu>(menu,HttpStatus.OK);

    }

    @RequestMapping(value = "/menu/{menuId}", method = RequestMethod.PUT,
            consumes = {"multipart/form-data","application/x-www-form-urlencoded"},
            produces = "application/json;charset=UTF-8")
    public ResponseEntity<Menu> updateMenu(@PathVariable Integer menuId, @RequestParam(required = false) MultipartFile img,
                                           MenuDTO menuDTO) throws IOException {

        if(null==menuId){
            return new ResponseEntity<Menu>(new Menu(),HttpStatus.BAD_REQUEST);
        }
        menuDTO.setImgPath(FileUploadUtil.saveFile(img));
        Menu menu = menuDTO.toMenu();
        menu.setId(menuId);
        menuService.updateMenu(menu);
        return new ResponseEntity<Menu>(menu,HttpStatus.OK);
    }

    @RequestMapping(value = "/menu", method = RequestMethod.POST,
            consumes = {"multipart/form-data","application/x-www-form-urlencoded"},
            produces = "application/json;charset=UTF-8")
    public ResponseEntity<JsonResponseStatus> createMenu(@RequestParam(required = false) MultipartFile img, MenuDTO menuDTO) throws IOException {

        menuDTO.setImgPath(FileUploadUtil.saveFile(img));
        Menu menu = menuDTO.toMenu();
        menuService.saveMenu(menu);

        return new ResponseEntity<JsonResponseStatus>(JsonResponseStatus.buildSuccessResponse(), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/menu", method = RequestMethod.DELETE, produces = "application/json;charset=UTF-8")
    public ResponseEntity<JsonResponseStatus> deleteMenu(@RequestBody String menuIds){
        if(null==menuIds||menuIds.isEmpty()){
            return new ResponseEntity<JsonResponseStatus>(JsonResponseStatus.buildFailResponse(400,"Bad Request : menuId is empty."),HttpStatus.BAD_REQUEST);
        }
        JSONArray list = JSON.parseArray(menuIds);
        menuService.deleteMenuByIds(list.toJavaList(Integer.class));
        return new ResponseEntity<JsonResponseStatus>(JsonResponseStatus.buildSuccessResponse(),HttpStatus.OK);
    }

    @RequestMapping(value = "/menu/{menuId}", method = RequestMethod.DELETE, produces = "application/json;charset=UTF-8")
    public ResponseEntity<JsonResponseStatus> deleteMenu(@PathVariable Integer menuId){
        if(null==menuId){
            return new ResponseEntity<JsonResponseStatus>(JsonResponseStatus.buildFailResponse(400,"Bad Request : menuId is empty."),HttpStatus.BAD_REQUEST);
        }
        menuService.deleteMenu(menuId);
        return new ResponseEntity<JsonResponseStatus>(JsonResponseStatus.buildSuccessResponse(),HttpStatus.OK);
    }

}
