package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;


public class Agenda {
	public static String STUDENTS_PATH = "resources/students";
	public static String SUBJECTS_PATH = "resources/subjects";

	private List<Student> students;
	private List<Subject> subjects;

	public Agenda() throws FileNotFoundException, IOException {
		students = new ArrayList<>();
		subjects = new ArrayList<>();
		loadStudents();
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

	public void loadStudents() throws FileNotFoundException, IOException {
		File[] studs = new File(STUDENTS_PATH).listFiles();
		students.clear();
		for(File propStud : studs) {
			Properties p = new Properties();
			p.load(new FileInputStream(propStud.getPath()));

			Enumeration<Object> keys = p.keys();

			for(int i = 0; keys.hasMoreElements(); i++) {
				Object key = keys.nextElement();
				System.out.println(key.toString());
			}
		}
	}

}