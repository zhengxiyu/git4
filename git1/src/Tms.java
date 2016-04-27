package com.briup.git1;

import java.util.Scanner;
public class Tms {
	//��ʦ���飬�����������н�ʦ����Ϣ��
	private Teacher[] teas = new Teacher[3];
	private int index = 0; //��ʦ�ĸ���
	//��ӽ�ʦ
	public void add(Teacher tea){
		//��������е�Ԫ�صĸ������ڵ������鳤�ȵ�ʱ��˵�����鳤�Ȳ���
		if(index>=teas.length){
			//������չ
			Teacher[] demo = new Teacher[teas.length+3];
			//����Ŀ���
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
	//ͨ��idɾ����ʦ 1002  1T  "HELLO WORLD"
	public void deleteById(long id){
		//���÷�����ȡidΪָ�����������������е�λ��
		int teaIndex = queryIndexById(id); // 1
		if(teaIndex!=-1){
			for(int i=teaIndex;i<index-1;i++){
				teas[i] = teas[i+1];
			}
			teas[--index] = null;
		}
	}
	
	//ͨ��id���Ҹý�ʦ���ڵ�λ�� 1002
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
	//ͨ��id��ѯ��ʦ ddl  dml
	public Teacher queryById(long id){
		//���÷�����ȡidΪָ�����������������е�λ��
		int teaIndex = queryIndexById(id);
		return teaIndex==-1?null:teas[teaIndex];
	}

	//�鿴���н�ʦ��Ϣ
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
	�����롣������[name,age]
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
	//�˵�
	public void menu(){
		System.out.println("********��ʦ����ϵͳ*******");
		System.out.println("*1���鿴���н�ʦ��Ϣ*");
		System.out.println("*2����ӽ�ʦ��Ϣ*");
		System.out.println("*3��ɾ����ʦ��Ϣ*");
		System.out.println("*4����ѯ��ʦ��Ϣ*");
		System.out.println("*5���޸Ľ�ʦ��Ϣ*");
		System.out.println("*exit���˳�*");
		System.out.println("*help������*");
		System.out.println("***************************");
	}

	public static void main(String[] args){
		//����tms����
		Tms tms = new Tms();
		tms.menu();	//��ʾ��ҳ��
		Scanner scanner = new Scanner(System.in);
		while(true){
			System.out.print("�����빦�ܱ�ţ�");
			//�ȴ��û����빦�ܱ�ţ����û�����س���ʱ���ȡ�س�ǰ���������
			String option = scanner.nextLine();
			switch(option){
				case "1"://��ѯ����
					System.out.println("���������н�ʦ����Ϣ��");
					Teacher[] teas = tms.queryAll();
					for(Teacher tea : teas){
						System.out.println(tea);
					}
					System.out.println("�ܼƣ�"+teas.length+" ��");
					break;
				case "2"://���
					while(true){
						System.out.println("�������ʦ��Ϣ��id#name#age����������break�ص���һ��Ŀ¼");
						String teaStr = scanner.nextLine();
						if(teaStr.equals("break")){
							break;
						}
						String[] teaArr = teaStr.split("#");
						long id = Long.parseLong(teaArr[0]);
						String name = teaArr[1];
						int age = Integer.parseInt(teaArr[2]);
						//��װ����
						Teacher tea = new Teacher(id,name,age);
						//�жϸ��û�id�Ƿ��Ѿ�����ռ��
						Teacher dbTea = tms.queryById(id);
						if(dbTea!=null){
							System.out.println("��id�Ѿ�����ռ�ã�������¼�룡");
							continue;
						}

					tms.add(tea);
						System.out.println("��ӳɹ���");
					}
					
					break;
				case "3"://ɾ��
					while(true){
						System.out.print("��������Ҫɾ����ʦ��id��break������һ��Ŀ¼:");
						String id = scanner.nextLine();
						if(id.equals("break")){
							break;
						}
						tms.deleteById(Long.parseLong(id));
						System.out.println("ɾ���ɹ�����ʦ����Ϊ��"+tms.index);
					}
					break;
				case "4"://��ѯ
					while(true){
						System.out.print("��������Ҫ��ѯ��ʦ��id��break������һ��Ŀ¼:");
						String id = scanner.nextLine();
						if(id.equals("break")){
							break;
						}
						Teacher tea = tms.queryById(Long.parseLong(id));
						System.out.println("��������Ҫ���ҵĽ�ʦ����Ϣ��");
						System.out.println(tea!=null?tea:"not found!");
					}
					break;
				case "5"://�޸�
					while(true){
						System.out.print("��������Ҫ�޸Ľ�ʦ��id��break������һ��Ŀ¼:");
						String id = scanner.nextLine();
						if(id.equals("break")){
							break;
						}
						Teacher tea = tms.queryById(Long.parseLong(id));
						if(tea == null){
							System.out.println("�ý�ʦ�����ڣ�");
							continue;
						}
						System.out.println("ԭ��ϢΪ��"+tea);
						System.out.println("��������Ҫ�޸ĵ���Ϣ��name#age��");
						String teaStr = scanner.nextLine();
						String[] stuArr = teaStr.split("#");

						String name = stuArr[0];
						int age = Integer.parseInt(stuArr[1]);

						Teacher newTea = new Teacher(Long.parseLong(id),name,age);

						tms.update(newTea);
						System.out.println("�޸ĳɹ���");
					}
					break;
				case "help":
					tms.menu();
					break;
				case "exit":
					System.out.println("bye bye");
					System.exit(0);
				default:
					System.out.println("����������������룡");
			}
		}
			
	}
}