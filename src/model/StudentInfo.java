package model;

import java.util.Vector;

public class StudentInfo {
	private String m_studentId = "";//学号
	private String m_name = "";//姓名
	private Gender m_gender = Gender.UNSET;//性别
	private int m_age = 0;//年龄
	private String m_major = "";//专业
	private Vector<String> m_reward = new Vector<String>();//奖励
	

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
		// TODO Auto-generated method stub

	}

}
