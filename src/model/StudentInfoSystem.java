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
	 * TODO：学号唯一性检测
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
	
	/**
	 * 从文件中加载
	 * @param pathName 数据文件所在的路径字符串
	 * @param clearFlag 是否清空内存中原有数据
	 * @return 读取的记录数，如果出错返回-1
	 */
	public int loadData(String pathName,boolean clearFlag) {
		try {
			if(clearFlag) m_studentInfoList.clear();
			
			File dir = new File(pathName);
			if(!dir.isDirectory()) return -1;//如果传入的不是目录字符串，则返回
			
			File infoFile = new File(pathName + "/StudentInfoList.csv");//学生信息文件
			File rewardFile = new File(pathName + "/Rewards.csv");//学生奖励文件
		
			
			BufferedReader bufReader = new BufferedReader(new FileReader(infoFile));//打开缓冲字符流
			RandomAccessFile randomAccessFile = new RandomAccessFile(rewardFile, "r");//打开随机读写
			
			String tmpString = null;
			while((tmpString = bufReader.readLine()) != null) {
				//按行读取
				String[] infoStrings = tmpString.split(",");//按照分隔符分割
				
				if(infoStrings.length != 7) {
					return -1;//如果字段数对不上，说明文件格式有问题
				}
					
				//从StudentInfo文件中读取学生信息
				String studentId = infoStrings[0];
				String name = infoStrings[1];
				Gender gender = Gender.newGender(infoStrings[2]);
				if(gender == null) return -1;
				
				int age = Integer.parseInt(infoStrings[3]);
				String major = infoStrings[4];
				
				long position = Long.parseLong(infoStrings[5]);
				int rewardLen = Integer.parseInt(infoStrings[6]);
				
				//从Reward文件中读取奖励信息
				randomAccessFile.seek(position);
				byte[] tmpBytes = new byte[1024];
				randomAccessFile.read(tmpBytes,0, rewardLen);//读取指定长度的奖励信息
				
				String rewardString = new String(tmpBytes).trim();
				
				
				Vector<String> rewardList = null;
				if(!rewardString.isEmpty()) {
					//如果奖励不为空则添加
					rewardList = new Vector<String>();
					String[] rewardArr = rewardString.split(",");
					for(String reward : rewardArr) {
						rewardList.add(reward);
					}
					
				}

				
				new StudentInfo(studentId, name, gender, age, major, rewardList).printInfo(true);
				System.out.println(position);
				System.out.println(rewardLen);
			}
			
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		
		return 0;
		
	}
	
	/**
	 * 通过学号查找学生信息
	 * @param studentId 学生学号
	 * @return 找到的学生信息，如找不到则返回null
	 */
	public StudentInfo search(String studentId) {
		return null;
		
	}
	
	/**
	 * 通过姓名查找学生信息
	 * @param name 学生姓名
	 * @return 第一个找到的符合的学生信息
	 */
	public StudentInfo searchByName(String name) {
		return null;
		
	}
	
	/**
	 * 修改学生信息。不需要修改的字段赋值为空
	 * @param studentInfo 学生信息入口
	 * @param studentid
	 * @param name
	 * @param gender
	 * @param age
	 * @param major
	 * @param reward
	 * @return 是否修改成功
	 */
	public boolean modify(StudentInfo studentInfo,String studentid,String name,Gender gender,int age,String major,Vector<String> reward) {
		return false;
		
	}
	
	/**
	 * 按照学号从小到大排序
	 * @param reverse 是否逆序
	 */
	public void sortById(boolean reverse) {
		
	}
	/**
	 * 按照姓名从小到大排序
	 * @param reverse 是否逆序
	 */
	public void sortByName(boolean reverse) {
		
	}
	
	/**
	 * 删除
	 * @param studentInfo
	 * @return 是否删除成功
	 */
	public boolean remove(StudentInfo studentInfo) {
		return false;
		
	}
	
	
	
	
	
	public static void main(String[] args) {
		
		Vector<String> rewards = new Vector<String>();
		
		rewards.add("三好学生");
		rewards.add("2033校奖学金");
	
		StudentInfo[] studentInfos = {
				new StudentInfo("2017901006", "杨啸", Gender.MALE, 21, "软件工程", null),
				new StudentInfo("2017999999", "小明", Gender.MALE, 21, "计算机科学与技术", rewards)
		};

		StudentInfoSystem sInfoSystem = new StudentInfoSystem();
		
		//sInfoSystem.addStudent(sInfoSystem.inputStudentInfo());
		//sInfoSystem.addStudent(sInfoSystem.inputStudentInfo());
		sInfoSystem.printStudentList();
		sInfoSystem.loadData(".", true);
		
		//sInfoSystem.saveData(".");

	}

}
