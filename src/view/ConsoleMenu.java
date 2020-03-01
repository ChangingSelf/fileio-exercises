package view;

import java.util.Scanner;
import java.util.Vector;

import model.Gender;
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
				System.out.println("====录入学生信息====");
				addStudent();
				break;
			case "C":case "c"://查询学生信息
				System.out.println("====查询学生信息====");
				search();
				break;
			case "D":case "d"://修改学生信息
				modify();
				break;
			case "E":case "e"://删除学生信息
				remove();
				break;
			case "F":case "f"://将当前信息列表保存到文件
				save();
				break;
			case "G":case "g"://从已有文件读取数据
				load();
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
		
		StudentInfo newStudentInfo = studentInfoSystem.inputStudentInfo();
		studentInfoSystem.addStudent(newStudentInfo);
	}
	
	/**
	 * 查找
	 * @return 返回查找结果，如果未找到则返回null
	 */
	public StudentInfo search() {
		
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
		return studentInfo;
	}
	
	/**
	 * 修改学生信息
	 * @return 是否修改成功
	 */
	public boolean modify() {
		printList();
		System.out.println("====修改学生信息====");
		StudentInfo studentInfo = search();
		if(studentInfo == null) return false;
		System.out.println("请依次输入修改后的内容");
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("请输入学生的学号，如果不需要修改，则直接回车");
		String studentId = scanner.nextLine();
		studentId = (studentId.equals(""))?null:studentId;
		
		
		System.out.println("请输入学生的姓名，如果不需要修改，则直接回车");
		String name = scanner.nextLine();
		name = (name.equals(""))?null:name;
		
		String genderString = "";
		Gender gender = null;
		System.out.println("请输入学生的性别,输入\"男\" 或者\"女\"，输入别的内容则不修改");
		genderString = scanner.nextLine();
		gender =  Gender.newGender(genderString);
		
		
		System.out.println("请输入学生的年龄，如果不需要修改，则输入0或者其他不合法数据");
		int age = 0;
		
		try {
			age = scanner.nextInt();
		} catch (Exception e) {
			scanner.next();//清空错误数据
			age = 0;
		}
		
		if(age<0) age = 0;

		scanner.nextLine();//清除缓冲区空行
		
		System.out.println("请输入学生的专业，如果不需要修改，则直接回车");
		String major = scanner.nextLine();
		major = (major.equals(""))?null:major;
		
		
		System.out.println("请输入学生的奖励，每输入完一项换行，输入\"done\"结束输入，输入\"null\"表示不需要修改");
		String rewardString = "";
		Vector<String> reward = new Vector<String>();
		do {
			rewardString = scanner.nextLine();
			if(!rewardString.equalsIgnoreCase("done") && !rewardString.equalsIgnoreCase("null")) {
				reward.add(rewardString);
			}
				
		} while (!rewardString.equalsIgnoreCase("done") && !rewardString.equalsIgnoreCase("null"));
		if(rewardString.equalsIgnoreCase("null")) {
			reward = null;//不需要修改
		}
		
		
		return studentInfoSystem.modify(studentInfo, studentId, name, gender, age, major, reward);
		
	}
	
	public void remove() {
		Scanner scanner = new Scanner(source)
		
		
		StudentInfo info = studentInfoSystem.search(studentId);
	}

	public void save() {
		studentInfoSystem.saveData(".");
	}
	
	/**
	 * 
	 * @return 是否加载成功
	 */
	public boolean load() {
		int studentNum = studentInfoSystem.loadData(".", true);
		if(studentNum == -1)
		{
			System.out.println("读取失败，请检查路径是否正确，以及文件是否符合格式");
			return false;
		}
		
		System.out.printf("成功载入%d条学生信息\n",studentNum);
		return true;
		
	}
	
	public static void main(String[] args) {
		ConsoleMenu menu = new ConsoleMenu();
		menu.printMenu();
		menu.boot();

	}

}
