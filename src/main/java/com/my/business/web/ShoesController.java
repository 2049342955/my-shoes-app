package com.my.business.web;

import com.my.business.common.FileUtils;
import com.my.business.common.ResponseEntity;
import com.my.business.common.ReturnType;
import com.my.business.common.annotion.HandleType;
import com.my.business.entity.Orders;
import com.my.business.entity.Picture;
import com.my.business.entity.Shoes;
import com.my.business.service.IPictureService;
import com.my.business.service.IShoesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.util.*;

@RestController
@RequestMapping("/shoes")
public class ShoesController {

    @Autowired
    private IShoesService iShoesService;

    @Autowired
    private IPictureService iPictureService;

    @HandleType
    @GetMapping("/get")
    public ResponseEntity<Shoes> get(String id){
        ResponseEntity responseEntity = new ResponseEntity();
        responseEntity.setData(iShoesService.get(id));
        return responseEntity;
    }

    @HandleType
    @GetMapping("/selectOne")
    public ResponseEntity<Shoes> selectOne(Shoes shoes){
        ResponseEntity responseEntity = new ResponseEntity();
        shoes.setStatus("Y");
        responseEntity.setData(iShoesService.selectOne(shoes));
        return responseEntity;
    }

    @HandleType(value = "picture",returnType = ReturnType.LIST)
    @GetMapping("/list")
    public ResponseEntity<List<Shoes>> list(Shoes shoes){
        ResponseEntity responseEntity = new ResponseEntity();
        shoes.setStatus("Y");
        responseEntity.setData(iShoesService.list(shoes));
        return responseEntity;
    }

    @HandleType
    @PostMapping("/save")
    public ResponseEntity<Shoes> save(@RequestBody Shoes shoes){
        ResponseEntity responseEntity = new ResponseEntity();
        if(StringUtils.isEmpty(shoes.getId())){
            shoes.setStatus("Y");
            shoes.setSales(0);
            shoes = iShoesService.save(shoes);
            Picture picture = new Picture();
            picture.setSid("-1");
            List<Picture> list = iPictureService.list(picture);
            for(Picture p:list){
                p.setSid(shoes.getId());
                iPictureService.save(p);
            }
        }else{
            shoes = iShoesService.save(shoes);
        }
        responseEntity.setData(shoes);
        return responseEntity;
    }

    @GetMapping("/delete")
    public Shoes delete(String id){
        Shoes shoes = new Shoes();
        shoes.setId(id);
        shoes.setStatus("N");
        return iShoesService.save(shoes);
    }


    @RequestMapping(value="/upload",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public boolean uploadImg(@RequestParam("file") MultipartFile file,
                                    HttpServletRequest request) throws FileNotFoundException {


        String imgFileName = file.getOriginalFilename();
        String imgPath = "src/main/webapp/static/img/";
        try {
            FileUtils.uploadFile(file.getBytes(), imgPath, imgFileName);
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            Picture picture = new Picture();
            picture.setName(imgFileName);
            picture.setSid("-1");
            picture.setUrl("http://localhost:8040//api/img/"+imgFileName);
            picture.setDate(new Date());
            iPictureService.save(picture);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return true;
    }


    @HandleType(value = "picture",returnType = ReturnType.LIST)
    @GetMapping("/getHome")
    public ResponseEntity<List<Shoes>> getHome(){
        ResponseEntity<List<Shoes>> responseEntity = new ResponseEntity<>();
        Calendar calendar = Calendar.getInstance();//日历对象
        calendar.setTime(new Date());//设置当前日期
        calendar.add(Calendar.MONTH, -3);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Orders orders = new Orders();
        orders.setDate(calendar.getTime());
        List<Shoes> list = iShoesService.getHome(orders);
        responseEntity.setData(list);
        return responseEntity;
    }



//    @RequestMapping("/addShoes")
//    public boolean addVideo(@RequestParam(value = "fileList",required = false) MultipartFile fileList, @RequestParam("name") String name,
//                           @RequestParam("brand") String brand, @RequestParam("number") String number,
//                            @RequestParam("size") String size,
//                           @RequestParam("color") String color,@RequestParam("price") String price,
//                            @RequestParam("stock") String stock,@RequestParam("type") String type,HttpServletRequest request){
//        //for(MultipartFile file: fileList){
//            String imgFileName = fileList.getOriginalFilename();
//            String imgPath = "src/main/webapp/static/img/";
//            try {
//                FileUtils.uploadFile(fileList.getBytes(), imgPath, imgFileName);
//
//            } catch (Exception e) {
//                // TODO: handle exception
//                e.printStackTrace();
//            }
//        //}
//
//        return false;
//    }

}
