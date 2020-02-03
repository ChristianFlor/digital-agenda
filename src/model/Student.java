package model;

public class Student {

	private String name;
	private String lastName;
	private String idCode;
	private String program;
	private String email;
	private int semester;
	private String phoneNumber;

	/**
	 * 
	 * @param name
	 * @param lastName
	 * @param idCode
	 * @param program
	 * @param semester
	 */
	public Student(String name, String lastName, String idCode, String program, String email, String phoneNumber, int semester) {
		this.name = name;
		this.lastName = lastName;
		this.idCode = idCode;
		this.program = program;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.semester = semester;
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

	public String getLastName() {
		return lastName;
	}

	/**
	 * 
	 * @param lastName
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	public String getPhoneNumer() {
		return this.phoneNumber;
	}

	/**
	 * 
	 * @param phoneNumer
	 */
	public void setPhoneNumer(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


}