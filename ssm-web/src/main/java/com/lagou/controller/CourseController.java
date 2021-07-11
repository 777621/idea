package com.lagou.controller;

import com.lagou.entity.Course;
import com.lagou.entity.CourseTeacherVo;
import com.lagou.entity.CourseVo;
import com.lagou.entity.ResponseResult;
import com.lagou.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    /**
     * 根据条件查询课程信息
     * @param courseVo
     * @return
     */
    @RequestMapping("/findCourseByCondition")
    public ResponseResult findCourseByCondition(@RequestBody CourseVo courseVo){

        System.out.println("courseVo:::::::::::::"+courseVo.toString());
        List<Course> courseList = courseService.findCourseByCondition(courseVo);

        ResponseResult result = new ResponseResult(true, 200, "响应成功", courseList);

        return result;

    }

    /**
     * 图片上传
     * @param file
     * @param request
     * @return
     */
    @RequestMapping("/courseUpload")
    public ResponseResult fileUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request){

        try {
            if(file.isEmpty()){

                throw new RuntimeException();
            }

            //获取项目部署路径

            String realPath = request.getServletContext().getRealPath("/");

            String webappsPath = realPath.substring(0, realPath.indexOf("ssm_web"));

            //获取原文件名
            String originalFilename = file.getOriginalFilename();

            //设置新文件名
            String newFileName = System.currentTimeMillis() + originalFilename.substring(originalFilename.lastIndexOf("."));

            // 存到upload这个目录
            String uploadPath = webappsPath +"upload\\";
            File filePath = new File(uploadPath, newFileName);

            //判断目录是否存在
            if(!filePath.getParentFile().exists()){

                filePath.getParentFile().mkdirs();

                System.out.println("创建目录："+filePath);
            }

            file.transferTo(filePath);

            //将新文件名 文件路径返回
            Map<String,String> map = new HashMap<>();

            map.put("fileName",newFileName);

            map.put("filePath","http://localhost:8080/upload/"+newFileName);

            ResponseResult result = new ResponseResult(true, 200, "图片上传成功", map);

            return result;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 新增或修改课程信息
     * @return
     */
    @RequestMapping("/saveOrUpdateCourse")
    public ResponseResult saveOrUpdateCourse(@RequestBody CourseVo courseVo){

        if(courseVo.getId() == null){

            courseService.saveCourseOrTeacher(courseVo);

            ResponseResult result = new ResponseResult(true, 200, "添加成功", null);

            return result;
        }else{

            courseService.updateCourseOrTeacher(courseVo);

            ResponseResult result = new ResponseResult(true, 200, "修改成功", null);

            return result;
        }
    }

    /**
     * 回显课程信息
     * @return
     */
    @RequestMapping("/findCourseById")
    public ResponseResult findCourseById(@RequestParam("id") Integer id){

        CourseTeacherVo courseTeacherVo = courseService.findCourseById(id);

        ResponseResult result = new ResponseResult(true, 200, "回显课程信息成功", courseTeacherVo);

        return result;
    }

    /**
     * 修改课程状态
     * @param id
     * @param status
     * @return
     */
    @RequestMapping("/updateCourseStatus")
    public ResponseResult updateCourseStatus(@RequestParam("id") int id,@RequestParam("status") int status){

        //调用业务
        courseService.updateCourseStatus(id,status);

        //封装返回数据
        Map<String, Integer> map = new HashMap<>();

        map.put("status",status);

        ResponseResult result = new ResponseResult(true, 200, "修改课程状态成功", map);

        return result;
    }
}
