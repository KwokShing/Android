package cn.csdn.domain;

public class Contact {

	private String sksj;
	private int id;
	private String skdd;
	private String bjmc;
	
	
	public String getSksj() {
		return sksj;
	}


	public void setSksj(String sksj) {
		this.sksj = sksj;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getSkdd() {
		return skdd;
	}


	public void setSkdd(String skdd) {
		this.skdd = skdd;
	}


	public String getBjmc() {
		return bjmc;
	}


	public void setBjmc(String bjmc) {
		this.bjmc = bjmc;
	}


	@Override
	public String toString() {
		return "Person [bjmc=" + bjmc + ",  skdd=" + skdd
				+ ", sksj=" + sksj + "]";
	}


	public Contact() {
	
		// TODO Auto-generated constructor stub
	}


	 public Contact(String sksj, String skdd, String bjmc) {
		super();
		this.sksj = sksj;
		this.skdd = skdd;
		this.bjmc = bjmc;
	}


	
}
