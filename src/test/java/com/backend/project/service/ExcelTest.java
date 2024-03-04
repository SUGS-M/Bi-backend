package com.backend.project.service;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.backend.project.model.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.commons.lang3.ObjectUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ResourceUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.*;
import java.util.stream.Collectors;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ExcelTest {
    //测试而已，固定路径即可，接口在用参数
    @Mock
    private HttpServletResponse response;

//    @InjectMocks
    @Resource
    private UserService userService;
//    @InjectMocks
    @Resource
    private BaseMapper<User> baseMapper;




    /**
     * Excel读操作
     * 作用：读取Excel数据，转化or存入数据库
     */
    @Test
    void ReadTest(){
        //1.获取File静态资源
        File file = null;
        try{
            file = ResourceUtils.getFile("classpath:网站数据.xlsx");
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
//        System.out.println(file);
        //2.读Excel
        List<Map<Integer,String>> list = EasyExcel.read(file)
                .excelType(ExcelTypeEnum.XLSX)
                .sheet()
                .headRowNumber(0)
                .doReadSync();
//        System.out.println(list);
//        [
//         {0=日期, 1=用户数, 2=null, 3=null},
//         {0=1号, 1=10, 2=null, 3=null},
//         {0=2号, 1=20},
//         {0=3号, 1=30},
//         {0=4号, 1=90},
//         {0=5号, 1=0},
//         {0=6号, 1=10},
//         {0=7号, 1=20}
//        ]      外部是List类型
//        System.out.println((list.get(0)));
//        {0=日期, 1=用户数, 2=null, 3=null}      Map类型
//        System.out.println(list.get(0).values());
//        [日期, 用户数, null, null]

        //3.转换格式
        //3.1 提取表头
        Map<Integer,String> headerMap = (LinkedHashMap)list.get(0);
//        System.out.println(headerMap);         //{0=日期, 1=用户数, 2=null, 3=null}
        List<String> headerList = headerMap.values().stream().filter(ObjectUtils::isNotEmpty).collect(Collectors.toList());
//        System.out.println(headerList);         //[日期, 用户数]
        //3.2 提取行数据
        List<String> bodyList = new ArrayList<>();
        List<List<String>> ansList = new ArrayList<>();
        for(int i=1; i<list.size(); i++){
            Map<Integer,String> bodyMap = (LinkedHashMap)list.get(i);
            bodyList = bodyMap.values().stream().filter(ObjectUtils::isNotEmpty).collect(Collectors.toList());
            ansList.add(bodyList);
        }
        System.out.println(bodyList); //toDo怎么把原先的覆盖了呢？
        System.out.println(ansList);
//        [
//          [1号, 10], [2号, 20], [3号, 30],
//          [4号, 90], [5号, 0], [6号, 10], [7号, 20]
//        ]
        //存入数据库

//        //3 提取行数据
//        for(int i=1; i<list.size(); i++){
//            Map<Integer,String> bodyMap = (LinkedHashMap)list.get(i);
//            List<String>  bodyList = bodyMap.values().stream().filter(ObjectUtils::isNotEmpty).collect(Collectors.toList());
//            System.out.println(bodyList);
//            System.out.println(bodyList.get(0));
//            System.out.println(bodyList.get(1));
//            //4 存入数据库
//            User user = new User();
//            user.setUserAccount(bodyList.get(0));
//            user.setUserPassword(bodyList.get(0));
//            //set只是创建，sava才是保存
//            userService.save(user);
//
//        }

    }

    /**
     * Excel写操作
     * 将数据表数据，写入Excel
     */
//    @Test
//    void WriteTest(){
//        try{
//            response.setContentType("application/vnd.ms-excel");
//            response.setCharacterEncoding("utf-8");
//            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
//            String fileName = URLEncoder.encode("导出列表", "UTF-8");
//            response.setHeader("Content-disposition", "attachment;filename="+ fileName + ".xlsx");
//            List<User> exportList = baseMapper.selectList(null);  //没有queryWrapper，即为全部
//            List<User> ansList = new ArrayList<>(exportList.size());            //存储空间
//            System.out.println(exportList);
//            for (User user : exportList){
//                User userI = new User();
//                BeanUtils.copyProperties(user,userI);
//                ansList.add(userI);
//            }
//            EasyExcel.write(response.getOutputStream(),User.class).sheet("导出测试").doWrite(ansList);
//        }catch (IOException e){
//            e.printStackTrace();
//        }finally {
//            //EasyExcel.write(response.getOutputStream(),User.class).sheet("导出测试").doWrite(ansList);
//        }
//    }

    //com.alibaba.excel.exception.ExcelGenerateException: Can not close IO.


//    public void exportData(HttpServletResponse response) {
//        try {
//            response.setContentType("application/vnd.ms-excel");
//            response.setCharacterEncoding("utf-8");
//            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
//            String fileName = URLEncoder.encode("课程分类", "UTF-8");
//            response.setHeader("Content-disposition", "attachment;filename="+ fileName + ".xlsx");
//            List<Subject> dictList = baseMapper.selectList(null);
//            List<SubjectEeVo> dictVoList = new ArrayList<>(dictList.size());
//            for(Subject dict : dictList) {
//                SubjectEeVo dictVo = new SubjectEeVo();
//                BeanUtils.copyProperties(dict,dictVo);
//                dictVoList.add(dictVo);
//            }
//            EasyExcel.write(response.getOutputStream(), SubjectEeVo.class).sheet("课程分类").doWrite(dictVoList);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

//    @Test
//    void WriteTest(HttpServletResponse response){
//    //测试方法中缺少对 javax.servlet.http.HttpServletResponse 参数的解析器 --》 通过使用 Mockito 框架，可以模拟 HttpServletResponse 对象
//        try{
//            response.setContentType("application/vnd.ms-excel");
//            response.setCharacterEncoding("utf-8");
//            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
//            String fileName = URLEncoder.encode("导出列表", "UTF-8");
//            response.setHeader("Content-disposition", "attachment;filename="+ fileName + ".xlsx");
//            List<User> exportList = baseMapper.selectList(null);
//            System.out.println(exportList);
//
//        }catch (IOException e){
//            e.printStackTrace();
//        }
//    }

}
