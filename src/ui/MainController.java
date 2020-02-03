package ui;


import java.io.FileNotFoundException;
import java.io.IOException;

//import com.gluonhq.charm.glisten.control.CardPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
    		phoneStudent.setText(first.getPhoneNumer());
    		// falta imagen
    		actualPosition++;
    	}
    }
    
    @FXML
    void nextStudent(ActionEvent event) {
    	actualPosition++;
    	
    	if(actualPosition>=agenda.getStudents().size() ||agenda.getStudents().get(actualPosition)!=null ) {
    		
    	}
    	if(agenda.getStudents().get(actualPosition)!=null) {
    		Student first = agenda.getStudents().get(actualPosition);
    		nameStudent.setText(first.getName() + " "+ first.getLastName());
    		emailStudent.setText(first.getEmail());
    		codeStudent.setText(first.getIdCode());
    		programStudent.setText(first.getProgram());
    		phoneStudent.setText(first.getPhoneNumer());
    		// falta imagen
    		actualPosition++;
    	}
    }

    @FXML
    void previousStudent(ActionEvent event) {

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
