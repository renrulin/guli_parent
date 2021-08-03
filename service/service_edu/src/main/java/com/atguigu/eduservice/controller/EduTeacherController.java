package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.EduTeacher;
import com.atguigu.eduservice.entity.vo.TeacherQuery;
import com.atguigu.eduservice.service.EduTeacherService;
import com.atguigu.servicebase.exceptionhandler.GuliException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2021-07-30
 */
@Api(description = "讲师管理")
@RestController
@RequestMapping("/eduservice/teacher")
@CrossOrigin
public class EduTeacherController {

    @Autowired
    private EduTeacherService eduTeacherService;

    @ApiOperation(value = "所有讲师列表")
    @GetMapping("findAll")
    public R findAllTeacher(){
        List<EduTeacher> list = eduTeacherService.list(null);
        return R.ok().data("items",list);
    }

    @ApiOperation(value = "逻辑删除讲师")
    @DeleteMapping("{id}")
    public R removeTeacher(
            @ApiParam(name = "id",value = "讲师id",required = true)
            @PathVariable String id){
        boolean b = eduTeacherService.removeById(id);
        if(b){
            return R.ok();
        }else {
            return R.error();
        }
    }

    //分页查询讲师的方法
    @ApiOperation(value = "讲师分页查询")
    @GetMapping("pageTeacher/{current}/{limit}")
    public R pageListTeacher(
            @ApiParam(name = "current",value = "当前页数",required = true)
            @PathVariable int current,
            @ApiParam(name = "limit",value = "查询条数",required = true)
            @PathVariable int limit){
        Page<EduTeacher> pageTeacher = new Page<>(current,limit);

        try {
            int i=10/0;
        }catch (Exception e){
            throw new GuliException(201,"执行了自定义异常处理...");
        }

        Page<EduTeacher> page = eduTeacherService.page(pageTeacher, null);
//        long total = pageTeacher.getTotal();//总记录数
//        List<EduTeacher> records = pageTeacher.getRecords();//数据list集合
//        return R.ok().data("total",total).data("rows",records);
        return R.ok().data("page",page);
    }

    //条件查询带分页
    @ApiOperation(value = "讲师条件查询带分页")
    @PostMapping("pageTeacherCondition/{current}/{limit}")
    public R pageTeacherCondtion(
            @ApiParam(name = "current",value = "当前页数",required = true)
            @PathVariable int current,
            @ApiParam(name = "limit",value = "查询条数",required = true)
            @PathVariable int limit,
            @RequestBody(required = false) TeacherQuery teacherQuery){

        Page<EduTeacher> pageTeacher = new Page<>(current,limit);
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        //多条件组合查询
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
        if (!StringUtils.isEmpty(name)){
            wrapper.like("name",name);
        }
        if (!StringUtils.isEmpty(level)){
            wrapper.eq("level",level);
        }
        if (!StringUtils.isEmpty(begin)){
            wrapper.ge("gmt_create",begin);
        }
        if (!StringUtils.isEmpty(end)){
            wrapper.le("gmt_create",end);
        }
        eduTeacherService.page(pageTeacher,wrapper);
        long total = pageTeacher.getTotal();//总记录数
        List<EduTeacher> records = pageTeacher.getRecords();//数据list集合
        return R.ok().data("total",total).data("rows",records);
    }

    //添加讲师接口方法
    @ApiOperation(value = "添加讲师")
    @PostMapping("addTeacher")
    public R addTeacher(@RequestBody EduTeacher eduTeacher){
        boolean save = eduTeacherService.save(eduTeacher);
        if (save){
            return R.ok();
        }else {
            return R.error();
        }
    }

    //根据讲师id进行查询
    @ApiOperation(value = "根据讲师id查询")
    @PostMapping("getTeacher/{id}")
    public R getTeacher(@PathVariable String id){
        EduTeacher byId = eduTeacherService.getById(id);
        return R.ok().data("teacher",byId);
    }

    //讲师修改
    @ApiOperation(value = "讲师修改")
    @PostMapping("updateTeacher")
    public R updateTeacher(@RequestBody EduTeacher eduTeacher){
        boolean update = eduTeacherService.updateById(eduTeacher);
        if (update){
            return R.ok();
        }else {
            return R.error();
        }
    }
}

