package com.zjmzxfzhl.codecreate;

import com.zjmzxfzhl.codecreate.first.CreateController;
import com.zjmzxfzhl.codecreate.first.CreateEntity;
import com.zjmzxfzhl.codecreate.first.CreateMybatisMapper;
import com.zjmzxfzhl.codecreate.first.CreateMybatisMapperXml;
import com.zjmzxfzhl.codecreate.first.CreateService;
import com.zjmzxfzhl.codecreate.first.CreateSql;
import com.zjmzxfzhl.codecreate.first.CreateVue;
import com.zjmzxfzhl.codecreate.util.CodeUtil;

/**
 * @author 庄金明
 * @date 2020年3月25日
 */
public class CreateFirst {
	public static void main(String[] args) {
		// 创建建表语句
		CreateSql.create(CodeUtil.createTableName);
		// 创建对象实体类和数据库一一对应
		CreateEntity.create(CodeUtil.createTableName);
		// 创建mybatisMapper.xml文件
		CreateMybatisMapperXml.create(CodeUtil.createTableName, "first");
		// 创建mybatisMapper.java文件
		CreateMybatisMapper.create(CodeUtil.createTableName, "first");
		// 创建操作数据库的Service类
		CreateService.create(CodeUtil.createTableName, "first");
		// 创建SpringMvc控制器的Controller类
		CreateController.create(CodeUtil.createTableName, "first");
		// 创建Vue前端文件
		CreateVue.create(CodeUtil.createTableName, "first");
	}
}
