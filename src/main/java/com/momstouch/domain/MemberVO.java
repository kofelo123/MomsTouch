package com.momstouch.domain;

import java.sql.Timestamp;

public class MemberVO {
  private String id;
  private String pwd;
  private String name;
  private String email;
  private String email2;
  private String totalEmail;
  private String address2;
  private String totalAddress;
  private String zip_Num;
  private String address;
  private String phone;
  private String phone2;
  private String phone3;
  private String totalPhone;
  private String useyn;
  private Timestamp indate;
  
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getZip_Num() {
		return zip_Num;
	}

	public void setZip_Num(String zip_Num) {
		this.zip_Num = zip_Num;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUseyn() {
		return useyn;
	}

	public void setUseyn(String useyn) {
		this.useyn = useyn;
	}

	public Timestamp getIndate() {
		return indate;
	}

	public void setIndate(Timestamp indate) {
		this.indate = indate;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getTotalAddress() {
		return address+" "+address2;
	}



	public String getEmail2() {
		return email2;
	}

	public void setEmail2(String email2) {
		this.email2 = email2;
	}


	public String getTotalEmail() {
		return email+"@"+email2;
	}

	public String getTotalPhone() {
		return phone+"-"+phone2+"-"+phone3;
	}

	public String getPhone2() {
		return phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public String getPhone3() {
		return phone3;
	}

	public void setPhone3(String phone3) {
		this.phone3 = phone3;
	}

	@Override
	public String toString() {
		return "MemberVO [id=" + id + ", pwd=" + pwd + ", name=" + name + ", email=" + email + ", zip_Num=" + zip_Num
				+ ", address=" + address + ", phone=" + phone + ", useyn=" + useyn + ", indate=" + indate + "]";
	}
  
}
