package Valentinaa.Kostyukhina.ivt.ch.makery.adress.view;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import org.controlsfx.dialog.Dialogs;

import Valentinaa.Kostyukhina.ivt.ch.makery.adress.model.VPersonK;
import Valentinaa.Kostyukhina.ivt.ch.makery.adress.util.VDateUtilK;
public class VPersonEditDialogControllerK {
	
	    @FXML
	    private TextField firstNameField;
	    @FXML
	    private TextField lastNameField;
	    @FXML
	    private TextField streetField;
	    @FXML
	    private TextField postalCodeField;
	    @FXML
	    private TextField cityField;
	    @FXML
	    private TextField birthdayField;


	    private Stage vdialogStagek;
	    private VPersonK vpersonk;
	    private boolean vokClickedk = false;

	  
	    @FXML
	    private void initialize() {
	    }

	    public void setDialogStage(Stage dialogStage) {
	        this.vdialogStagek = dialogStage;
	    }

	    public void setPerson(VPersonK person) {
	        this.vpersonk = person;

	        firstNameField.setText(person.getFirstName());
	        lastNameField.setText(person.getLastName());
	        streetField.setText(person.getStreet());
	        postalCodeField.setText(Integer.toString(person.getPostalCode()));
	        cityField.setText(person.getCity());
	        birthdayField.setText(VDateUtilK.vformatK(person.getBirthday()));
	        birthdayField.setPromptText("dd.vk.yyyy");
	    }

	
	    public boolean isOkClicked() {
	        return vokClickedk;
	    }

	    @FXML
	    private void handleOk() {
	        if (visInputValidK()) {
	            vpersonk.setFirstName(firstNameField.getText());
	            vpersonk.setLastName(lastNameField.getText());
	            vpersonk.setStreet(streetField.getText());
	            vpersonk.setPostalCode(Integer.parseInt(postalCodeField.getText()));
	            vpersonk.setCity(cityField.getText());
	            vpersonk.setBirthday(VDateUtilK.vparseK(birthdayField.getText()));

	            vokClickedk = true;
	            vdialogStagek.close();
	        }
	    }

	    @FXML
	    private void handleCancel() {
	        vdialogStagek.close();
	    }

	   
	    private boolean visInputValidK() {
	        String errorMessage = "";

	        if (firstNameField.getText() == null || firstNameField.getText().length() == 0) {
	            errorMessage += "No valid first name!\n"; 
	        }
	        if (lastNameField.getText() == null || lastNameField.getText().length() == 0) {
	            errorMessage += "No valid last name!\n"; 
	        }
	        if (streetField.getText() == null || streetField.getText().length() == 0) {
	            errorMessage += "No valid street!\n"; 
	        }

	        if (postalCodeField.getText() == null || postalCodeField.getText().length() == 0) {
	            errorMessage += "No valid postal code!\n"; 
	        } else {
	           
	            try {
	                Integer.parseInt(postalCodeField.getText());
	            } catch (NumberFormatException e) {
	                errorMessage += "No valid postal code (must be an integer)!\n"; 
	            }
	        }

	        if (cityField.getText() == null || cityField.getText().length() == 0) {
	            errorMessage += "No valid city!\n"; 
	        }

	        if (birthdayField.getText() == null || birthdayField.getText().length() == 0) {
	            errorMessage += "No valid birthday!\n";
	        } else {
	            if (!VDateUtilK.vvalidDateMK(birthdayField.getText())) {
	                errorMessage += "No valid birthday. Use the format dd.vk.yyyy!\n";
	            }
	        }

	        if (errorMessage.length() == 0) {
	            return true;
	        } else {
	            Dialogs.create()
	                .title("Invalid Fields")
	                .masthead("Please correct invalid fields")
	                .message(errorMessage)
	                .showError();
	            return false;
	        }
	    }
	}