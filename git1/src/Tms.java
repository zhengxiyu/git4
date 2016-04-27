package com.briup.git1;

import java.util.Scanner;
public class Tms {
	//教师数组，用来保存所有教师的信息的
	private Teacher[] teas = new Teacher[3];
	private int index = 0; //教师的个数
	//添加教师
	public void add(Teacher tea){
		//如果数组中的元素的个数大于等于数组长度的时候，说明数组长度不够
		if(index>=teas.length){
			//数组扩展
			Teacher[] demo = new Teacher[teas.length+3];
			//数组的拷贝
			System.arraycopy(teas,0,demo,0,teas.length);
			teas = demo;
		}
		teas[index++] = tea;// teas[index] = tea; index++;
	}
	/**
	stus = {
		{1001,terry},
		{1002,larry},	
		{1003,tom},
		null,
		null
	
	}
	// 1002  teaIndex = 1; index = 3
	for(int i=1;i<2;i++){
		//teas[0] = teas[1]
		//teas[1] = teas[2]
		teas[2] = teas[3]
	}
	index = 3
	{1,2,4,5,6,7,8,9,0}
	*/
	//通过id删除教师 1002  1T  "HELLO WORLD"
	public void deleteById(long id){
		//调用方法获取id为指定参数，所在数组中的位置
		int teaIndex = queryIndexById(id); // 1
		if(teaIndex!=-1){
			for(int i=teaIndex;i<index-1;i++){
				teas[i] = teas[i+1];
			}
			teas[--index] = null;
		}
	}
	
	//通过id查找该教师所在的位置 1002
	private int queryIndexById(long id){
		int teaIndex= -1;
		for(int i=0;i<index;i++){
			if(teas[i].getId() == id){
				teaIndex = i;
				break;
			}
		}
		return teaIndex;
	}
	//通过id查询教师 ddl  dml
	public Teacher queryById(long id){
		//调用方法获取id为指定参数，所在数组中的位置
		int teaIndex = queryIndexById(id);
		return teaIndex==-1?null:teas[teaIndex];
	}

	//查看所有教师信息
	public Teacher[] queryAll(){
		Teacher[] demo = new Teacher[index];
		System.arraycopy(teas,0,demo,0,index);
		return demo;
	}
	/**
	{
	{1001,terry,12},
	{1002,larry,13},
	null
	}
	1002
	{1002,larry,13}
	请输入。。。。[name,age]
	tom#14
	{1002,tom,14}
	*/  
	public void update(Teacher tea){
		for(int i=0;i<index;i++){
			if(tea.getId() == teas[i].getId()){
				teas[i].setName(tea.getName());
				teas[i].setAge(tea.getAge());
			}
		}
	}
	//菜单
	public void menu(){
		System.out.println("********教师管理系统*******");
		System.out.println("*1，查看所有教师信息*");
		System.out.println("*2，添加教师信息*");
		System.out.println("*3，删除教师信息*");
		System.out.println("*4，查询教师信息*");
		System.out.println("*5，修改教师信息*");
		System.out.println("*exit，退出*");
		System.out.println("*help，帮助*");
		System.out.println("***************************");
	}

	public static void main(String[] args){
		//创建tms对象
		Tms tms = new Tms();
		tms.menu();	//显示主页面
		Scanner scanner = new Scanner(System.in);
		while(true){
			System.out.print("请输入功能编号：");
			//等待用户输入功能编号，等用户输入回车的时候获取回车前输入的内容
			String option = scanner.nextLine();
			switch(option){
				case "1"://查询所有
					System.out.println("以下是所有教师的信息：");
					Teacher[] teas = tms.queryAll();
					for(Teacher tea : teas){
						System.out.println(tea);
					}
					System.out.println("总计："+teas.length+" 人");
					break;
				case "2"://添加
					while(true){
						System.out.println("请输入教师信息【id#name#age】或者输入break回到上一级目录");
						String teaStr = scanner.nextLine();
						if(teaStr.equals("break")){
							break;
						}
						String[] teaArr = teaStr.split("#");
						long id = Long.parseLong(teaArr[0]);
						String name = teaArr[1];
						int age = Integer.parseInt(teaArr[2]);
						//封装对象
						Teacher tea = new Teacher(id,name,age);
						//判断该用户id是否已经被人占用
						Teacher dbTea = tms.queryById(id);
						if(dbTea!=null){
							System.out.println("该id已经被人占用，请重新录入！");
							continue;
						}

					tms.add(tea);
						System.out.println("添加成功！");
					}
					
					break;
				case "3"://删除
					while(true){
						System.out.print("请输入您要删除教师的id或break返回上一级目录:");
						String id = scanner.nextLine();
						if(id.equals("break")){
							break;
						}
						tms.deleteById(Long.parseLong(id));
						System.out.println("删除成功！教师个数为："+tms.index);
					}
					break;
				case "4"://查询
					while(true){
						System.out.print("请输入您要查询教师的id或break返回上一级目录:");
						String id = scanner.nextLine();
						if(id.equals("break")){
							break;
						}
						Teacher tea = tms.queryById(Long.parseLong(id));
						System.out.println("以下是您要查找的教师的信息：");
						System.out.println(tea!=null?tea:"not found!");
					}
					break;
				case "5"://修改
					while(true){
						System.out.print("请输入您要修改教师的id或break返回上一级目录:");
						String id = scanner.nextLine();
						if(id.equals("break")){
							break;
						}
						Teacher tea = tms.queryById(Long.parseLong(id));
						if(tea == null){
							System.out.println("该教师不存在！");
							continue;
						}
						System.out.println("原信息为："+tea);
						System.out.println("请输入您要修改的信息【name#age】");
						String teaStr = scanner.nextLine();
						String[] stuArr = teaStr.split("#");

						String name = stuArr[0];
						int age = Integer.parseInt(stuArr[1]);

						Teacher newTea = new Teacher(Long.parseLong(id),name,age);

						tms.update(newTea);
						System.out.println("修改成功！");
					}
					break;
				case "help":
					tms.menu();
					break;
				case "exit":
					System.out.println("bye bye");
					System.exit(0);
				default:
					System.out.println("输入出错，请重新输入！");
			}
		}
			
	}
}