package ui;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

//import com.gluonhq.charm.glisten.control.CardPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import model.*;

public class MainController {

    @FXML
    private TextField nameStudent;

    @FXML
    private TextField phoneStudent;

    @FXML
    private TextField emailStudent;

    @FXML
    private TextField codeStudent;

    @FXML
    private TextField programStudent;


    //@FXML
    //private CardPane<String> panelSubject;

    @FXML
    private TextField nameSubject;

    @FXML
    private TextField finalGrade;

    @FXML
    private TextField nrcSubject;

    @FXML
    private TextField nameTeacher;

    @FXML
    private ComboBox<?> departament;

    @FXML
    private ComboBox<?> optionsSearch;

    @FXML
    private TextField search;

    @FXML
    private ImageView foundStudentImg;

    @FXML
    private Label dName;

    @FXML
    private Label dScore;

    @FXML
    private Label dNick;

    @FXML
    private Label dProgram;

    @FXML
    private VBox vBoxList;

    @FXML
    private ImageView photoStudent;
    
    @FXML
    private Button btnPrevious;

    @FXML
    private Button btnNext;
    
    @FXML
    private ListView<String> subjectList;

    private Agenda agenda;
    private int actualPosition;
    
    @FXML
    void initialize() throws FileNotFoundException, IOException {
    	agenda = new Agenda();
    	actualPosition = 0;
    	if(agenda.getStudents().get(0)!=null) {
    		Student first = agenda.getStudents().get(0);
    		nameStudent.setText(first.getName() + " "+ first.getLastName());
    		emailStudent.setText(first.getEmail());
    		codeStudent.setText(first.getIdCode());
    		programStudent.setText(first.getProgram());
    		phoneStudent.setText(first.getPhoneNumber());
    		
    		Image image = new Image(new File(first.getProfpic()).toURI().toString());
    		photoStudent.setImage(image);
    		for (int i = 0; i<first.getSubjects().size(); i++) {
    			subjectList.getItems().add(first.getSubjects().get(i).getName());
			}
    		
    	}
    }
    
    @FXML
    void nextStudent(ActionEvent event) {
    	actualPosition++;
    	
    	if(actualPosition>=agenda.getStudents().size() ||agenda.getStudents().get(actualPosition)==null ) {
    		actualPosition = 0;
       	}
    		Student next = agenda.getStudents().get(actualPosition);
    		nameStudent.setText(next.getName() + " "+ next.getLastName());
    		emailStudent.setText(next.getEmail());
    		codeStudent.setText(next.getIdCode());
    		programStudent.setText(next.getProgram());
    		phoneStudent.setText(next.getPhoneNumber());
    		
    		Image image = new Image(new File(next.getProfpic()).toURI().toString());
    		photoStudent.setImage(image);
    		subjectList.getItems().clear();
    		for (int i = 0; i<next.getSubjects().size(); i++) {
    			subjectList.getItems().add(next.getSubjects().get(i).getName());
			}
    	
    }

    @FXML
    void previousStudent(ActionEvent event) {
    		actualPosition--;
    	
    	if(actualPosition<0 ||agenda.getStudents().get(actualPosition)==null ) {
    		actualPosition = agenda.getStudents().size()-1;
    	}
    		Student previous = agenda.getStudents().get(actualPosition);
    		nameStudent.setText(previous.getName() + " "+ previous.getLastName());
    		emailStudent.setText(previous.getEmail());
    		codeStudent.setText(previous.getIdCode());
    		programStudent.setText(previous.getProgram());
    		phoneStudent.setText(previous.getPhoneNumber());
    		
    		Image image = new Image(new File(previous.getProfpic()).toURI().toString());
    		photoStudent.setImage(image);
    		subjectList.getItems().clear();
    		for (int i = 0; i<previous.getSubjects().size(); i++) {
    			subjectList.getItems().add(previous.getSubjects().get(i).getName());
			}
    	
    }
    
    @FXML
    void aboutProgram(ActionEvent event) {
    	
    }

    @FXML
    void addSubject(ActionEvent event) {

    }

    @FXML
    void delete(ActionEvent event) {

    }

    @FXML
    void exit(ActionEvent event) {

    }

    @FXML
    void registerStudent(ActionEvent event) {
    	nameStudent.setText("");
		emailStudent.setText("");
		codeStudent.setText("");
		programStudent.setText("");
		phoneStudent.setText("");
    }

    @FXML
    void save(ActionEvent event) {

    }

    @FXML
    void search(ActionEvent event) {

    }

    @FXML
    void sortCode(ActionEvent event) {

    }

    @FXML
    void sortEmail(ActionEvent event) {

    }

    @FXML
    void sortId(ActionEvent event) {

    }

    @FXML
    void sortName(ActionEvent event) {

    }

    @FXML
    void update(ActionEvent event) {

    }

    @FXML
    void uploadImage(ActionEvent event) {

    }

}
