package model;

import java.util.ArrayList;
import java.util.List;

public class Agenda {

	private List<Student> students;
	private List<Subject> subjects;
	public Agenda() {
		students = new ArrayList<>();
		subjects = new ArrayList<>();
	}

	/**
	 * 
	 * @param name
	 * @param lastName
	 * @param idCode
	 * @param program
	 * @param semester
	 */
	public boolean registerStudent(String name, String lastName, String idCode, String program, int semester) {
		return true;
	}

	/**
	 * 
	 * @param idCode
	 */
	public boolean deleteStudent(int idCode) {
		// TODO - implement Agenda.deleteStudent
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param idCode
	 * @param nrc
	 */
	public boolean addSubjectToStudent(int idCode, int nrc) {
		// TODO - implement Agenda.addSubjectToStudent
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param idCode
	 * @param nrc
	 */
	public boolean deleteSubjectToStudent(int idCode, int nrc) {
		// TODO - implement Agenda.deleteSubjectToStudent
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param name
	 */
	public boolean searchStudentByName(String name) {
		boolean found = false;
		for(int i = 0; i < students.size();i++) {
			if(students.get(i).getName().equals(name)) found = true;
		}
		return found;
	}

	/**
	 * 
	 * @param idCode
	 */
	public boolean searchStudentByIdCode(String idCode) {
		boolean found = false;
		for(int i = 0; i < students.size();i++) {
			if(students.get(i).getIdCode().equals(idCode)) found = true;
		}
		return found;
	}

	/**
	 * 
	 * @param lastName
	 */
	public boolean searchStudentByLastName(String lastName) {
		boolean found = false;
		for(int i = 0; i < students.size();i++) {
			if(students.get(i).getLastName().equals(lastName)) found = true;
		}
		return found;
	}

	/**
	 * 
	 * @param email
	 */
	public boolean searchStudentByEmail(String email) {
		boolean found = false;
		for(int i = 0; i < students.size();i++) {
			if(students.get(i).getEmail().equals(email)) found = true;
		}
		return found;
	}

}