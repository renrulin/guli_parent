package com.atguigu.demo.excel;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sgd
 * @date 2021/8/4下午3:48
 */
public class TestEasyExcel {
    public static void main(String[] args) {
//        String filename="/home/sgd/Desktop/write.xls";
//        EasyExcel.write(filename,DemoData.class).sheet("学生列表").doWrite(getData());

        String filename="/home/sgd/Desktop/write.xls";
        EasyExcel.read(filename,DemoData.class,new ExcelListener()).sheet().doRead();
    }

    private static List<DemoData> getData(){
        List<DemoData> list = new ArrayList<>();
        for (int i=0;i<10;i++){
            DemoData data=new DemoData();
            data.setSno(i);
            data.setSname("lucy"+i);
            list.add(data);
        }
        return list;
    }
}
