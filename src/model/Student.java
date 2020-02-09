package model;

import java.util.ArrayList;
import java.util.List;

public class Student {

	private String name;
	private String lname;
	private String idCode;
	private String program;
	private String email;
	private int semester;
	private String phoneNumber;
	private String profpic;
	private List<Subject> subjects;

	/**
	 * 
	 * @param name
	 * @param lastName
	 * @param idCode
	 * @param program
	 * @param semester
	 */
	public Student(String name, String ln, String idCode, String program, String email, String phoneNumber,String profpic, int semester) {

		this.name = name;
		lname = ln;
		this.idCode = idCode;
		this.program = program;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.setSemester(semester);
		this.profpic = profpic;
		this.subjects = new ArrayList<>();
	}

	public boolean addSubject(Subject sub) {
		return subjects.add(sub);
	}
	
	public String getName() {
		return this.name;
	}

	/**
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}


	public String getIdCode() {
		return this.idCode;
	}

	/**
	 * 
	 * @param idCode
	 */
	public void setIdCode(String idCode) {
		this.idCode = idCode;
	}

	public String getProgram() {
		return this.program;
	}

	/**
	 * 
	 * @param program
	 */
	public void setProgram(String program) {
		this.program = program;
	}

	public String getEmail() {
		return this.email;
	}

	/**
	 * 
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	/**
	 * 
	 * @param phoneNumer
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getProfpic() {
		return profpic;
	}

	public void setProfpic(String profpic) {
		this.profpic = profpic;
	}

	public List<Subject> getSubjects(){
		return subjects;
	}

	public String getLastname() {
		return lname;
	}

	public void setLastname(String lname) {
		this.lname = lname;
	}

	public int getSemester() {
		return semester;
	}

	public void setSemester(int semester) {
		this.semester = semester;
	}

}