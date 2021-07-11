package com.lagou.service.impl;

import com.lagou.dao.CourseMapper;
import com.lagou.dao.TeacherMapper;
import com.lagou.entity.Course;
import com.lagou.entity.CourseTeacherVo;
import com.lagou.entity.CourseVo;
import com.lagou.entity.Teacher;
import com.lagou.service.CourseService;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {

    /**
     * 根据条件查询课程信息
     */
    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public List<Course> findCourseByCondition(CourseVo courseVo) {

        Example example = new Example(Course.class);

        Example.Criteria c = example.createCriteria();

        if(courseVo.getCourseName() !=null && !"".equals(courseVo.getCourseName())){

            c.andLike("courseName","%"+courseVo.getCourseName()+"%");

        }
        if(courseVo.getStatus() !=null && !"".equals(courseVo.getStatus())){

            c.andEqualTo("status",courseVo.getStatus());

        }
        c.andEqualTo("isDel",0);



        List<Course> courseList = courseMapper.selectByExample(example);

        return courseList;
    }

    /**
     * 添加课程 讲师信息
     * @param courseVo
     */
    @Override
    public void saveCourseOrTeacher(CourseVo courseVo) {

        try {
            Course course = new Course();

            BeanUtils.copyProperties(course,courseVo);

            //补全课程信息
            Date date = new Date();
            course.setCreateTime(date);
            course.setUpdateTime(date);

            courseMapper.insertSelective(course);

            int id = course.getId();

            Teacher teacher = new Teacher();

            BeanUtils.copyProperties(teacher,courseVo);

            teacher.setCreateTime(date);
            teacher.setUpdateTime(date);
            teacher.setIsDel(0);
            teacher.setCourseId(id);

            teacherMapper.insertSelective(teacher);
        } catch (Exception e) {
            e.printStackTrace();
        }

        /*xml 方式
        try {
            Course course = new Course();

            BeanUtils.copyProperties(course,courseVo);

            //补全课程信息
            Date date = new Date();
            course.setCreateTime(date);
            course.setUpdateTime(date);

            //保存课程信息
            courseMapper.saveCourse(course);

            //获取主键
            int id = course.getId();

            //补全讲师信息
            Teacher teacher = new Teacher();

            BeanUtils.copyProperties(teacher,courseVo);

            teacher.setCreateTime(date);
            teacher.setUpdateTime(date);
            teacher.setIsDel(0);
            teacher.setCourseId(id);

            courseMapper.saveTeacher(teacher);
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }

    /**
     * 课程信息回显
     * @param id
     * @return
     */
    @Override
    public CourseTeacherVo findCourseById(Integer id) {

        //return courseMapper.findCourseById(id);
        Course course = courseMapper.selectByPrimaryKey(id);

        Example example = new Example(Teacher.class);

        Example.Criteria c = example.createCriteria();

        c.andEqualTo("courseId",id);

        Teacher teacher = teacherMapper.selectOneByExample(example);

        CourseTeacherVo courseTeacherVo = new CourseTeacherVo();

        courseTeacherVo.setCourse(course);

        courseTeacherVo.setTeacher(teacher);

        return courseTeacherVo;
    }

    /**
     * 修改课程信息
     * @param courseVo
     */
    @Override
    public void updateCourseOrTeacher(CourseVo courseVo) {

        try {
            Course course = new Course();

            BeanUtils.copyProperties(course,courseVo);

            //补全信息
            Date date = new Date();
            course.setUpdateTime(date);

            Example example = new Example(Course.class);

            Example.Criteria criteria = example.createCriteria();

            criteria.andEqualTo("id",courseVo.getId());

            courseMapper.updateByExampleSelective(course,example);

            Teacher teacher = new Teacher();
            BeanUtils.copyProperties(teacher,courseVo);

            //补全信息
            teacher.setCourseId(course.getId());
            teacher.setUpdateTime(date);

            Example example1 = new Example(teacher.getClass());

            Example.Criteria criteria1 = example1.createCriteria();

            criteria1.andEqualTo("courseId",teacher.getCourseId());

            teacherMapper.updateByExampleSelective(teacher,example1);
        } catch (Exception e) {
            e.printStackTrace();
        }
       /* try {
            Course course = new Course();

            BeanUtils.copyProperties(course,courseVo);

            //补全信息
            Date date = new Date();
            course.setUpdateTime(date);

            courseMapper.updateCourse(course);

            Teacher teacher = new Teacher();
            BeanUtils.copyProperties(teacher,courseVo);

            //补全信息
            teacher.setCourseId(course.getId());
            teacher.setUpdateTime(date);

            courseMapper.updateTeacher(teacher);
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }

    /**
     * 修改课程状态
     * @param id
     * @param status
     */
    @Override
    public void updateCourseStatus(int id, int status) {

        //封装数据
        Course course = new Course();
        course.setId(id);
        course.setUpdateTime(new Date());
        course.setStatus(status);

        //调用业务
        //courseMapper.updateCourseStatus(course);
        courseMapper.updateByPrimaryKeySelective(course);
    }
}
