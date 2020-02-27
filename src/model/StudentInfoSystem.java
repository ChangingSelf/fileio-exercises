package model;

import java.io.*;
import java.util.Scanner;
import java.util.Vector;

public class StudentInfoSystem {
	
	private Vector<StudentInfo> m_studentInfoList = new Vector<StudentInfo>();//学生信息列表
	
	
	
	public StudentInfoSystem() {
		
	}
	
	public StudentInfoSystem(StudentInfo[] studentInfoList) {
		addStudents(studentInfoList);
	}

	/**
	 * 添加学生
	 * @param studentInfo
	 */
	public void addStudent(StudentInfo studentInfo) {
		m_studentInfoList.add(studentInfo);
	}
	
	public void addStudents(StudentInfo[] studentInfoList) {
		for(StudentInfo studentInfo : studentInfoList) {
			addStudent(studentInfo);
		}
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
		int age = 0;
		while (true) {
			try {
				age = scanner.nextInt();
				if(age<=0) {
					System.out.println("年龄必须是正数");
				}
				else {
					break;
				}
			} catch (Exception e) {
				System.out.println("年龄必须是整数");
				scanner.next();//清空错误数据
			}
			
			
		}
		
		
		
		
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
	
	/**
	 * 保存到文件中
	 * @param pathName 数据文件所在的路径字符串
	 * @return 保存的记录数，如果出错返回-1
	 */
	public int saveData(String pathName) {
		
		
		try {
			File dir = new File(pathName);
			if(!dir.isDirectory()) return -1;//如果传入的不是目录字符串，则返回
			
			File infoFile = new File(pathName + "/StudentInfoList.csv");//学生信息文件
			File rewardFile = new File(pathName + "/Rewards.csv");//学生奖励文件
			
			if(!infoFile.exists()) infoFile.createNewFile();
			if(!rewardFile.exists()) rewardFile.createNewFile();
			
			FileOutputStream infoFOS = new FileOutputStream(infoFile);
			FileOutputStream rewardFOS = new FileOutputStream(rewardFile);
			
			int curPosition = 0;//“奖励”文件指针当前位置
			for(StudentInfo studentInfo:m_studentInfoList) {
				String studentInfoString = String.format("%s,%s,%s,%d,%s,", 
						studentInfo.getStudentId(),
						studentInfo.getName(),
						studentInfo.getGender().getGenderString(),
						studentInfo.getAge(),
						studentInfo.getMajor()
						);
				String rewardString = String.join(",", studentInfo.getReward());//拼接奖励字符串
				if(rewardString == null) rewardString = "";
				byte[] rewardBuf = rewardString.getBytes();//转换为字节数组
				
				studentInfoString += String.format("%s,%s\n", curPosition,rewardBuf.length);
				curPosition += rewardBuf.length;//计算下一个位置
				if(rewardBuf.length!=0) curPosition += "\n".getBytes().length;
				
				byte[] infoBuf = studentInfoString.getBytes();
				
				//写入文件
				
				infoFOS.write(infoBuf);
				
				
				
				rewardFOS.write(rewardBuf);
				
				
			}
			rewardFOS.close();
			infoFOS.close();
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public static void main(String[] args) {
		
		Vector<String> rewards = new Vector<String>();
		
		rewards.add("三好学生");
		rewards.add("2033校奖学金");
	
		StudentInfo[] studentInfos = {
				new StudentInfo("2017901006", "杨啸", Gender.MALE, 21, "软件工程", null),
				new StudentInfo("2017999999", "小明", Gender.MALE, 21, "计算机科学与技术", rewards)
		};

		StudentInfoSystem sInfoSystem = new StudentInfoSystem(studentInfos);
		
		//sInfoSystem.addStudent(sInfoSystem.inputStudentInfo());
		//sInfoSystem.addStudent(sInfoSystem.inputStudentInfo());
		sInfoSystem.printStudentList();
		
		sInfoSystem.saveData(".");

	}

}
