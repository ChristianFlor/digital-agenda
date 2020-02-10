package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;


public class Agenda {
	public static String STUDENTS_PATH = "resources/students";
	public static String SUBJECTS_PATH = "resources/subjects";

	private List<Student> students;
	private Map<Integer, Subject> subjects;
	
	public Agenda() throws FileNotFoundException, IOException {
		students = new ArrayList<>();
		subjects = new HashMap<>();
		loadSubjects();
		loadStudents();
	}

	public Map<Integer, Subject> getSubjects() {
		return subjects;
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
	public boolean registerStudent(String name, String lname, String idCode, String program, int semester, String email, String profpic, String phoneNumber) throws IOException {
		boolean possible = true;
		for(int i =0; i < students.size() && possible; i++) {
			if(idCode.equals(students.get(i).getIdCode())) possible = false;
		}
		if(possible) {
			students.add(new Student(name, lname, idCode, program, email, phoneNumber, profpic,semester));
			File f = new File(STUDENTS_PATH+"/"+idCode+".properties");
			FileWriter fw = new FileWriter(f);
			BufferedWriter bw = new BufferedWriter(fw);
			
			bw.append("name="+name+"\n");
			bw.append("lastName="+lname+"\n");
			bw.append("email="+email+"\n");
			bw.append("id="+idCode+"\n");
			bw.append("phoneNumber="+phoneNumber+"\n");
			bw.append("program="+program+"\n");
			bw.append("profpic="+profpic+"\n");
			bw.append("semester="+semester);
			
			bw.close();
			fw.close();
		}
		return possible;
	}

	/**
	 * 
	 * @param idCode
	 */
	public boolean deleteStudent(String idCode) {
		File f = new File(STUDENTS_PATH+"/"+idCode+".properties");
		for(int i = 0; i < students.size(); i++) {
			if(students.get(i).getIdCode().equals(idCode)) {
				students.remove(students.get(i));
				break;
			}
		}
		return f.delete();
	}
	
	
	/**
	 * 
	 * @param idCode
	 * @param nrc
	 * @throws IOException 
	 */
	public boolean addSubjectToStudent(String idCode, String name, int nrc, String faculty, int credits, int numberStudents) throws IOException {
		boolean found = false;
		for(int i = 0; i < students.size() && !found;i++) {
			if(students.get(i).getIdCode().equals(idCode)) { 
				found = true;
				if(!subjects.containsKey(nrc)) {
				
					Subject sub = new Subject(name,nrc,faculty,credits,numberStudents);
					File f = new File("resources/subjects/"+nrc+".properties");
					FileWriter fw = new FileWriter(f);
					BufferedWriter bw = new BufferedWriter(fw);
					bw.append("name="+name+"\n");
					bw.append("nrc="+nrc+"\n");
					bw.append("faculty="+faculty+"\n");
					bw.append("credits="+credits+"\n");
					bw.append("students="+1+"\n");
					bw.close();
					subjects.put(nrc, sub);
				}else {
					/*File f = new File("resources/subjects/"+nrc+".properties");
					
					Properties p = new Properties();
					FileInputStream is = new FileInputStream(f.getPath());
					p.load(is);
					int students = Integer.parseInt(p.getProperty("students"));
					FileWriter fw = new FileWriter(f);
					BufferedWriter bw = new BufferedWriter(fw);
					bw.append("students="+(students+1)+"\n");
					bw.close();*/
				}
				students.get(i).addSubject(subjects.get(nrc));
				
				File f = new File("resources/students/"+idCode+".properties");
				Properties p = new Properties();
				FileInputStream is = new FileInputStream(f.getPath());
				p.load(is);
				String curr = "";
				if(p.getProperty("subjects")!=null) {
					 curr = p.getProperty("subjects")+nrc+",";
				}else {
					 curr = nrc+",";
				}
				p.setProperty("subjects",curr);
				p.store(new FileWriter(f), "add subject");
				is.close();
				
			}
		}
		return found;
	}
 
	/**
	 * 
	 * @param idCode
	 * @param nrc
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public boolean deleteSubjectToStudent(String idCode, int nrc) throws FileNotFoundException, IOException {
		File[] studs = new File(STUDENTS_PATH).listFiles();
		boolean possible = false, possible2 = false;
		for(File propStud : studs) {
			Properties p = new Properties();
			p.load(new FileInputStream(propStud.getPath()));
			if(p.getProperty("id").equals(idCode)) {
				String[] subs = p.getProperty("subjects").split(",");
				String subjectss = "";
				int cnt = 0;
				for (int i = 0; i < subs.length; i++) {
					int nrcc=  Integer.parseInt(subs[i]);
					if(nrcc!=nrc) {
						if(cnt>0) {
							subjectss += ","+nrcc;
						}else
							subjectss += nrcc;
					}else
						possible = true;
				}
				p.setProperty("subjects", subjectss);
				p.store(new FileWriter(propStud), "add subject");
				
			}
			
		}
		for (int i = 0; i < students.size()&&!possible2; i++) {
			if(students.get(i).getIdCode().equals(idCode)) {
				List<Subject> list = students.get(i).getSubjects();
				for (int j = 0; j < list.size() && !possible2; j++) {
					if(list.get(j).getNrc()==nrc) {
						list.remove(j);
						possible2 = true;
					}
				}
			}
		}
		return (possible&&possible2);
	}

	/**
	 * 
	 * @param name
	 */
	public List<Student> searchStudentByName(String name) {
		List<Student> list = new ArrayList<>();
		for(int i = 0; i < students.size();i++) {
			if(students.get(i).getName().toUpperCase().contains(name.toUpperCase())) {
				list.add(students.get(i));
			} 
		}
		return list;
	}
	
	public List<Student> searchStudentByLastName(String lname) {
		List<Student> list = new ArrayList<>();
		for(int i = 0; i < students.size();i++) {
			if(students.get(i).getLastname().toUpperCase().contains(lname.toUpperCase())) {
				list.add(students.get(i));
			} 
		}
		return list;
	}

	/**
	 * 
	 * @param idCode
	 */
	public Student searchStudentByIdCode(String idCode) {
		Student stu = null;
		for(int i = 0; i < students.size();i++) {
			if(students.get(i).getIdCode().equals(idCode)) {
				stu = students.get(i);
				break;
			}
		}
		return stu;
	}

	

	/**
	 * 
	 * @param email
	 */
	public Student searchStudentByEmail(String email) {
		Student stu = null;
		for(int i = 0; i < students.size();i++) {
			if(students.get(i).getEmail().equals(email)) {
				stu = students.get(i);
				break;
			} 
		}
		return stu;
	}

	public void loadStudents() throws FileNotFoundException, IOException {
		File[] studs = new File(STUDENTS_PATH).listFiles();
		students.clear();
		for(File propStud : studs) {
			Properties p = new Properties();
			FileInputStream inputStream = new FileInputStream(propStud.getPath());
			p.load(inputStream);

			String name = p.getProperty("name");
			String lname = p.getProperty("lastName");
			String email = p.getProperty("email");
			String id = p.getProperty("id");
			String pn = p.getProperty("phoneNumber");
			String prog = p.getProperty("program");
			int semester = Integer.parseInt(p.getProperty("semester"));
			String pp = p.getProperty("profpic");
			String[] subs = p.getProperty("subjects").split(",");
			students.add(new Student(name, lname, id, prog, email, pn, pp, semester));
			for (int i = 0; i < subs.length; i++) {
				int nrc=  Integer.parseInt(subs[i]);
				students.get(students.size()-1).addSubject(subjects.get(nrc));
				
			}
			
			inputStream.close();
		}
	}

	public void loadSubjects() throws FileNotFoundException, IOException {
		File[] subjs = new File(SUBJECTS_PATH).listFiles();
		subjects.clear();
		for(File propStud : subjs) {
			Properties p = new Properties();
			FileInputStream is = new FileInputStream(propStud.getPath());
			p.load(is);

			String name = p.getProperty("name");
			int nrc = Integer.parseInt(p.getProperty("nrc"));
			String faculty = p.getProperty("faculty");
			int credits = Integer.parseInt(p.getProperty("credits"));
			int students = Integer.parseInt(p.getProperty("students"));
			subjects.put(nrc, new Subject(name, nrc, faculty, credits, students));
			
			is.close();
		}
	}
	
	public double calculateAverageSubjects() {
		int total=0;
		double avarage = 0;
		for (int i = 0; i < students.size(); i++) {
			total+=students.get(i).getSubjects().size();
		}
		avarage = total/students.size();
		return avarage;
	}
	
	public double calculateAverageCredits() {
		int total=0;
		double avarage = 0;
		for (int i = 0; i < students.size(); i++) {
			for (int j = 0; j < students.get(i).getSubjects().size(); j++) {
				total+=students.get(i).getSubjects().get(j).getCredits();
				
			}
			
			
		}
		avarage = total/students.size();
		return avarage;
	}
	
	public List<Subject> converToList(){
		List<Subject> subs = new ArrayList<>();
		for (Map.Entry<Integer, Subject> e : subjects.entrySet()) {
			subs.add(e.getValue());
		}
		return subs;
	}
	
	public Subject mostMatriculated() {
		List<Subject> subs = converToList();
		subs.sort(new Comparator<Subject>() {

			@Override
			public int compare(Subject o1, Subject o2) {
				return o1.getStudents()-o2.getStudents();
			}
			
		});
		return subs.get(subs.size()-1);
	}
	
	public Subject lessMatriculated() {
		List<Subject> subs = converToList();
		subs.sort(new Comparator<Subject>() {

			@Override
			public int compare(Subject o1, Subject o2) {
				return o1.getStudents()-o2.getStudents();
			}
			
		});
		return subs.get(0);
	}
	public List<Student> getStudents() {
		return students;
	}

}