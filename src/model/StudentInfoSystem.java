package model;

import java.io.*;
import java.util.Scanner;
import java.util.Vector;

public class StudentInfoSystem {
	
	private Vector<StudentInfo> m_studentInfoList = new Vector<StudentInfo>();//学生信息列表

	/**
	 * 添加学生
	 * @param studentInfo
	 */
	public void addStudent(StudentInfo studentInfo) {
		m_studentInfoList.add(studentInfo);
	}
	
	/**
	 * 输入学生信息
	 * @return 返回封装好的学生信息对象
	 */
	public StudentInfo inputStudentInfo() {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("请输入学生的学号");
		String studentId = scanner.nextLine();
		
		System.out.println("请输入学生的姓名");
		String name = scanner.nextLine();
		
		
		String genderString = "";
		Gender gender = null;
		do {
			System.out.println("请输入学生的性别,输入\"男\" 或者\"女\"");
			genderString = scanner.nextLine();
			gender =  Gender.newGender(genderString);
		}while(gender == null);
		
		System.out.println("请输入学生的年龄");
		int age = scanner.nextInt();
		
		scanner.nextLine();//清除缓冲区空行
		
		System.out.println("请输入学生的专业");
		String major = scanner.nextLine();
		
		System.out.println("请输入学生的奖励，每输入完一项换行，输入\"done\"结束输入");
		String rewardString = "";
		Vector<String> reward = new Vector<String>();
		do {
			rewardString = scanner.nextLine();
			if(!rewardString.equalsIgnoreCase("done")) reward.add(rewardString);
		} while (!rewardString.equalsIgnoreCase("done"));
		
		
		return new StudentInfo(studentId,name,gender,age,major,reward);
	}
	
	/**
	 * 打印列表中所有学生信息
	 */
	public void printStudentList() {
		boolean tableHead = true;//只有第一行打印表头
		for(StudentInfo studentInfo : m_studentInfoList) {
			studentInfo.printInfo(tableHead);
			tableHead = false;
		}
	}
	
	public static void main(String[] args) {
		StudentInfoSystem sInfoSystem = new StudentInfoSystem();
		sInfoSystem.addStudent(sInfoSystem.inputStudentInfo());
		sInfoSystem.addStudent(sInfoSystem.inputStudentInfo());
		sInfoSystem.printStudentList();

	}

}
