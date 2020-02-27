package model;

import java.util.Vector;

public class StudentInfo {
	private String m_studentId = "";//学号
	private String m_name = "";//姓名
	private Gender m_gender = Gender.UNSET;//性别
	private int m_age = 0;//年龄
	private String m_major = "";//专业
	private Vector<String> m_reward = new Vector<String>();//奖励
	
	public StudentInfo() {
		
	}
	public StudentInfo(String studentId,String name,Gender gender,int age,String major,Vector<String> reward) {
		m_studentId = studentId;
		m_name = name;
		m_gender = gender;
		m_age = age;
		m_major = major;
		
		m_reward = reward != null? reward : new Vector<String>();
		
	}
	
	/**
	 * 打印个人信息
	 */
	public void printInfo(boolean tableHead) {
		String rewardString = String.join(",", m_reward);//拼接奖励字符串
		if(rewardString == null) rewardString = "";
		
		if(tableHead) System.out.printf("\n%20s\t%10s\t%5s\t%5s\t%20s\t%20s\n",
				"学号",
				"姓名",
				"性别",
				"年龄",
				"专业",
				"奖励"
				);
		System.out.printf("%s\t%10s\t%5s\t%5d\t%20s\t%20s\n",
				m_studentId,
				m_name,
				m_gender.getGenderString(),
				m_age,
				m_major,
				rewardString
				);
		
	}
	
	

	public String getStudentId() {
		return m_studentId;
	}


	public void setStudentId(String studentId) {
		this.m_studentId = studentId;
	}


	public String getName() {
		return m_name;
	}


	public void setName(String name) {
		this.m_name = name;
	}


	public Gender getGender() {
		return m_gender;
	}


	public void setGender(Gender gender) {
		this.m_gender = gender;
	}


	public int getAge() {
		return m_age;
	}


	public void setAge(int age) {
		this.m_age = age;
	}


	public String getMajor() {
		return m_major;
	}


	public void setMajor(String major) {
		this.m_major = major;
	}


	public Vector<String> getReward() {
		return m_reward;
	}


	public void setReward(Vector<String> reward) {
		this.m_reward = reward;
	}


	public static void main(String[] args) {
		StudentInfo sInfo = new StudentInfo();
		sInfo.getReward().add("2011校奖学金");
		sInfo.getReward().add("2012国家奖学金");
		sInfo.printInfo(true);

	}

}
