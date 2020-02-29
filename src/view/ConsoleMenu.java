package view;

import java.util.Scanner;

import model.StudentInfo;
import model.StudentInfoSystem;

public class ConsoleMenu {
	
	private StudentInfoSystem studentInfoSystem = new StudentInfoSystem();
	
	
	/**
	 * 启动系统
	 */
	public void boot() {
		Scanner scanner = new Scanner(System.in);
		
		String inputString = "";
		
		while(!(inputString = scanner.next()).equalsIgnoreCase("Q")) {
			switch (inputString) {
			case "A":case "a"://输出当前所有学生信息
				printList();
				break;
			case "B":case "b"://录入学生信息
				addStudent();
				break;
			case "C":case "c"://查询学生信息
				search();
				break;
			case "D":case "d"://修改学生信息
				studentInfoSystem.printStudentList();
				break;
			case "E":case "e"://删除学生信息
				studentInfoSystem.printStudentList();
				break;
			case "F":case "f"://将当前信息列表保存到文件
				studentInfoSystem.printStudentList();
				break;
			case "G":case "g"://从已有文件读取数据
				studentInfoSystem.printStudentList();
				break;
			case "H":case "h"://显示菜单
				printMenu();
				break;
			default:
				break;
			}
			System.out.println("输入H显示菜单选项");
		}
		
		System.out.println("===========已退出，欢迎下次使用=========");
	}
	
	
	public void printMenu() {
		System.out.println("===========欢迎进入简易学生信息管理系统=========");
		System.out.println("请输入选项前对应的字母使用功能");
		System.out.println("A.输出当前所有学生信息");
		System.out.println("B.录入学生信息");
		System.out.println("C.查询学生信息");
		System.out.println("D.修改学生信息");
		System.out.println("E.删除学生信息");
		System.out.println("F.将当前信息列表保存到文件");
		System.out.println("G.从已有文件读取数据");
		System.out.println("H.显示本菜单");
		System.out.println("Q.退出");
	}
	
	/**
	 * 打印学生信息列表
	 */
	public void printList() {
		studentInfoSystem.printStudentList();
	}
	
	/**
	 * 录入学生信息
	 */
	public void addStudent() {
		System.out.println("====录入学生信息====");
		StudentInfo newStudentInfo = studentInfoSystem.inputStudentInfo();
		studentInfoSystem.addStudent(newStudentInfo);
	}
	
	/**
	 * 查找
	 */
	public void search() {
		System.out.println("====查询学生信息====");
		System.out.println("请输入学号");
		Scanner scanner = new Scanner(System.in);
		String studentId = scanner.next();
		StudentInfo studentInfo = studentInfoSystem.search(studentId);
		if(studentInfo == null) {
			//如果没找到
			System.out.printf("未找到学号为[%s]的学生\n",studentId);
			
		}
		else {
			studentInfo.printInfo(true);//如果找到了则输出
		}
	}
	

	public static void main(String[] args) {
		ConsoleMenu menu = new ConsoleMenu();
		menu.printMenu();
		menu.boot();

	}

}
