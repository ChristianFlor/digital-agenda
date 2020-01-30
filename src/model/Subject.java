package model;

public class Subject {

	private String name;
	private int nrc;
	private String faculty;
	private int credits;
	private int students;

	/**
	 * 
	 * @param name
	 * @param nrc
	 * @param faculty
	 * @param credits
	 * @param students
	 */
	public Subject(String name, int nrc, String faculty, int credits, int students) {
		// TODO - implement Subject.Subject
		throw new UnsupportedOperationException();
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

	public int getNrc() {
		return this.nrc;
	}

	/**
	 * 
	 * @param nrc
	 */
	public void setNrc(int nrc) {
		this.nrc = nrc;
	}

	public String getFaculty() {
		return this.faculty;
	}

	/**
	 * 
	 * @param faculty
	 */
	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}

	public int getCredits() {
		return this.credits;
	}

	/**
	 * 
	 * @param credits
	 */
	public void setCredits(int credits) {
		this.credits = credits;
	}

	public int getStudents() {
		return this.students;
	}

	/**
	 * 
	 * @param students
	 */
	public void setStudents(int students) {
		this.students = students;
	}

}