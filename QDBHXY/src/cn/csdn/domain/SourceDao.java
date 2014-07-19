package cn.csdn.domain;

import java.io.Serializable;

public class SourceDao implements Serializable {
	private Integer id;
	private String first;
	private String second;
	private String three;
	private String four;
	private String five;
	private String six;
	private String seven;
	private String eight;
	private String nine;
	private String ten;
	public SourceDao() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SourceDao(Integer id, String first, String second, String three,
			String four, String five, String six, String seven, String eight,
			String nine, String ten) {
		super();
		this.id = id;
		this.first = first;
		this.second = second;
		this.three = three;
		this.four = four;
		this.five = five;
		this.six = six;
		this.seven = seven;
		this.eight = eight;
		this.nine = nine;
		this.ten = ten;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFirst() {
		return first;
	}
	public void setFirst(String first) {
		this.first = first;
	}
	public String getSecond() {
		return second;
	}
	public void setSecond(String second) {
		this.second = second;
	}
	public String getThree() {
		return three;
	}
	public void setThree(String three) {
		this.three = three;
	}
	public String getFour() {
		return four;
	}
	public void setFour(String four) {
		this.four = four;
	}
	public String getFive() {
		return five;
	}
	public void setFive(String five) {
		this.five = five;
	}
	public String getSix() {
		return six;
	}
	public void setSix(String six) {
		this.six = six;
	}
	public String getSeven() {
		return seven;
	}
	public void setSeven(String seven) {
		this.seven = seven;
	}
	public String getEight() {
		return eight;
	}
	public void setEight(String eight) {
		this.eight = eight;
	}
	public String getNine() {
		return nine;
	}
	public void setNine(String nine) {
		this.nine = nine;
	}
	public String getTen() {
		return ten;
	}
	public void setTen(String ten) {
		this.ten = ten;
	}
	@Override
	public String toString() {
		return "SourceDao [id=" + id + ", first=" + first + ", second="
				+ second + ", three=" + three + ", four=" + four + ", five="
				+ five + ", six=" + six + ", seven=" + seven + ", eight="
				+ eight + ", nine=" + nine + ", ten=" + ten + "]";
	}
	
}
