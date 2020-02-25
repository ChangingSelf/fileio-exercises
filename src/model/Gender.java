package model;

public enum Gender {
	UNSET("未设置"),
	MALE("男"),
	FAMALE("女");
	
	private String m_genderString;
	private Gender(String gender) {
		m_genderString = gender;
	}
	
	public String getGenderString() {
		return m_genderString;
	}
	
	/**
	 * 根据字符串的值返回对应的枚举值
	 * TODO：应该可以优化成不用手动写switch的
	 * @param genderString
	 * @return 返回字符串对应的枚举值，找不到则返回null
	 */
	public static Gender newGender(String genderString) {
		switch (genderString) {
		case "男":
			return Gender.MALE;
		case "女":
			return Gender.FAMALE;

		default:
			break;
		}
		return null;
	}
	
}
