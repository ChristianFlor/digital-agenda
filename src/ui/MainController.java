package ui;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

//import com.gluonhq.charm.glisten.control.CardPane;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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


    @FXML
    private TextField nameSubject;


    @FXML
    private TextField nrcSubject;
    
    @FXML
    private TextField txtCredits;
    @FXML
    private TextField txtFaculty;
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
    		showInformation(0);
    		
    	}
    }
 
	@FXML
    void nextStudent(ActionEvent event) {
    	actualPosition++;
    	
    	if(actualPosition>=agenda.getStudents().size() ||agenda.getStudents().get(actualPosition)==null ) {
    		actualPosition = 0;
       	}
    		showInformation(actualPosition);
    	
    }

    @FXML
    void previousStudent(ActionEvent event) {
    		actualPosition--;
    	
    	if(actualPosition<0 ||agenda.getStudents().get(actualPosition)==null ) {
    		actualPosition = agenda.getStudents().size()-1;
    	}
    	showInformation(actualPosition);
    	
    }
    private void showInformation(int position) {
    	Student next = agenda.getStudents().get(position);
		nameStudent.setText(next.getName() + " "+ next.getLastName());
		emailStudent.setText(next.getEmail());
		codeStudent.setText(next.getIdCode());
		programStudent.setText(next.getProgram());
		phoneStudent.setText(next.getPhoneNumber());
		
		Image image = new Image(new File(next.getProfpic()).toURI().toString());
		photoStudent.setImage(image);
		subjectList.getItems().clear();
		
		for (int i = 0; i<next.getSubjects().size(); i++) {
			subjectList.getItems().add(next.getSubjects().get(i).getNrc()+" "+next.getSubjects().get(i).getName());
		}
		subjectList.setOnMouseClicked(new EventHandler<MouseEvent>() {
		    @Override
		    public void handle(MouseEvent click) {

		        if (click.getClickCount() == 2) {
		         int nrc = Integer.parseInt(subjectList.getSelectionModel().getSelectedItem().substring(0,5));
		         Subject sub = agenda.getSubjects().get(nrc);
		         nameSubject.setText(sub.getName());
		         nrcSubject.setText(""+sub.getNrc());
		         txtFaculty.setText(sub.getFaculty());
		         txtCredits.setText(""+sub.getCredits());
		        }
		    }
		});
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
