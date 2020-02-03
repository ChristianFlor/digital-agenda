package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
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
	 * @throws IOException 
	 */
	public boolean registerStudent(String name, String lastName, String idCode, String program, int semester, String email, String phoneNumber) throws IOException {
		boolean possible = true;
		for(int i =0; i < students.size() && possible; i++) {
			if(idCode.equals(students.get(i).getIdCode())) possible = true;
		}
		if(possible) {
			students.add(new Student(name, lastName, idCode, program, email, phoneNumber, semester));
			File f = new File("resources/students/"+idCode+".properties");
			FileWriter fw = new FileWriter(f);
			BufferedWriter bw = new BufferedWriter(fw); 
			bw.append("name="+name+"\n");
			bw.append("lastName="+lastName+"\n");
			bw.append("email="+email+"\n");
			bw.append("id="+idCode+"\n");
			bw.append("phoneNumber="+phoneNumber+"\n");
			bw.append("program="+program+"\n");
			bw.append("semester="+semester);
			bw.close();
		}
		return possible;
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

			String name = p.getProperty("name");
			String lName = p.getProperty("lastName");
			String email = p.getProperty("email");
			String id = p.getProperty("id");
			String pn = p.getProperty("phoneNumber");
			String prog = p.getProperty("program");
			int semester = Integer.parseInt(p.getProperty("semester"));
			String pp = p.getProperty("profPic");
			
			students.add(new Student(name, lName, id, prog, email, pn, semester));
		}
	}

	public void loadSubjects() throws FileNotFoundException, IOException {
		File[] subjs = new File(SUBJECTS_PATH).listFiles();
		subjects.clear();
		for(File propStud : subjs) {
			Properties p = new Properties();
			p.load(new FileInputStream(propStud.getPath()));

			String name = p.getProperty("name");
			int nrc = Integer.parseInt(p.getProperty("nrc"));
			String faculty = p.getProperty("faculty");
			int credits = Integer.parseInt(p.getProperty("credits"));
			int students = Integer.parseInt(p.getProperty("students"));
			subjects.add(new Subject(name, nrc, faculty, credits, students));
		}
	}
	
	public List<Student> getStudents() {
		return students;
	}
}