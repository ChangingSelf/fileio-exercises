package model;

public enum Gender {
	UNSET("未设置"),
	MALE("男"),
	FAMALE("女");
	
	private String m_gender;
	private Gender(String gender) {
		m_gender = gender;
	}
	
	public String getGender() {
		return m_gender;
	}
	
}
