package Valentinaa.Kostyukhina.ivt.ch.makery.adress;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.File;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import Valentinaa.Kostyukhina.ivt.ch.makery.adress.model.VPersonListWrapperK;
import Valentinaa.Kostyukhina.ivt.ch.makery.adress.model.VPersonK;
import Valentinaa.Kostyukhina.ivt.ch.makery.adress.view.VBirthdayStatisticsControllerK;
import Valentinaa.Kostyukhina.ivt.ch.makery.adress.view.VPersonEditDialogControllerK;
import Valentinaa.Kostyukhina.ivt.ch.makery.adress.view.VPersonOverviewControllerK;
import Valentinaa.Kostyukhina.ivt.ch.makery.adress.view.VRootLayoutControllerK;

import java.util.prefs.Preferences;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.controlsfx.dialog.Dialogs;

public class VMainAppK extends Application {

	
	 private Stage vprimaryStagek;
	 private BorderPane vrootLayoutk;
	 private ObservableList<VPersonK> vpersonDatak = FXCollections.observableArrayList();
	  
	 public VMainAppK() {
			
			vpersonDatak.add(new VPersonK("Hans", "Muster"));
			vpersonDatak.add(new VPersonK("Ruth", "Mueller"));
			vpersonDatak.add(new VPersonK("Heinz", "Kurz"));
			vpersonDatak.add(new VPersonK("Cornelia", "Meier"));
			vpersonDatak.add(new VPersonK("Werner", "Meyer"));
			vpersonDatak.add(new VPersonK("Lydia", "Kunz"));
			vpersonDatak.add(new VPersonK("Anna", "Best"));
			vpersonDatak.add(new VPersonK("Stefan", "Meier"));
			vpersonDatak.add(new VPersonK("Martin", "Mueller"));
					}

	 public ObservableList<VPersonK> getPersonData() {
			return vpersonDatak;
		}
	 
	 @Override
	    public void start(Stage primaryStage) {
	        this.vprimaryStagek = primaryStage;
	        this.vprimaryStagek.setTitle("AdressApp");
	        this.vprimaryStagek.getIcons().add(new Image("file:resources/images/address_book_32.png"));
	        vinitRootLayoutK();
	        vshowPersonOverviewK();

	    }

	    
	    public void vinitRootLayoutK() {
	    	 try {
	    	       
	    	        FXMLLoader loader = new FXMLLoader();
	    	        loader.setLocation(VMainAppK.class
	    	                .getResource("view/RootLayout.fxml"));
	    	        vrootLayoutk = (BorderPane) loader.load();

	    	        
	    	        Scene scene = new Scene(vrootLayoutk);
	    	        vprimaryStagek.setScene(scene);

	    	     
	    	        VRootLayoutControllerK controller = loader.getController();
	    	        controller.setMainApp(this);

	    	        vprimaryStagek.show();
	    	    } catch (IOException e) {
	    	        e.printStackTrace();
	    	    }

	    	    File file = vgetPersonFilePathK();
	    	    if (file != null) {
	    	        vloadPersonDataFromFileK(file);
	    	    }
	        }

	   
	    public void vshowPersonOverviewK() {
	        try {
	            FXMLLoader vloaderk = new FXMLLoader();
	            vloaderk.setLocation(VMainAppK.class.getResource("view/PersonOverview.fxml"));
	           AnchorPane vpersonOverviewk = (AnchorPane) vloaderk.load();

	            vrootLayoutk.setCenter(vpersonOverviewk);
	            
	            VPersonOverviewControllerK controller = vloaderk.getController();
	            controller.setMainApp(this);

	            vprimaryStagek.show();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    public Stage vgetPrimaryStageK() {
	        return vprimaryStagek;
	    }
	      
	    public static void main(String[] args) {
	        launch(args);
	    
	}
	    
	    
	    public boolean showPersonEditDialog(VPersonK person) {
	        try {
	            
	            FXMLLoader vloaderk = new FXMLLoader();
	            vloaderk.setLocation(VMainAppK.class.getResource("view/PersonEditDialog.fxml"));
	            AnchorPane vpagek = (AnchorPane) vloaderk.load();

	            Stage vdialogStagek = new Stage();
	            vdialogStagek.setTitle("Edit Person");
	            vdialogStagek.initModality(Modality.WINDOW_MODAL);
	            vdialogStagek.initOwner(vprimaryStagek);
	            Scene vscenek = new Scene(vpagek);
	            vdialogStagek.setScene(vscenek);

	            VPersonEditDialogControllerK controller = vloaderk.getController();
	            controller.setDialogStage(vdialogStagek);
	            controller.setPerson(person);

	            vdialogStagek.showAndWait();

	            return controller.isOkClicked();
	        } catch (IOException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
	    
	    public File vgetPersonFilePathK() {
	        Preferences vprefsk = Preferences.userNodeForPackage(VMainAppK.class);
	        String vfilePathk = vprefsk.get("filePath", null);
	        if (vfilePathk != null) {
	            return new File(vfilePathk);
	        } else {
	            return null;
	        }
	    }

	    
	    public void vsetPersonFilePathK(File file) {
	        Preferences vprefsk = Preferences.userNodeForPackage(VMainAppK.class);
	        if (file != null) {
	            vprefsk.put("filePath", file.getPath());

	            
	            vprimaryStagek.setTitle("AdressApp - " + file.getName());
	        } else {
	            vprefsk.remove("filePath");

	            
	            vprimaryStagek.setTitle("AdressApp");
	        }
	    }
	    
	    
	    public void vloadPersonDataFromFileK(File file) {
	        try {
	            JAXBContext vcontextk = JAXBContext
	                    .newInstance(VPersonListWrapperK.class);
	            Unmarshaller vumk = vcontextk.createUnmarshaller();

	           
	            VPersonListWrapperK vwrapperk = (VPersonListWrapperK) vumk.unmarshal(file);

	            vpersonDatak.clear();
	            vpersonDatak.addAll(vwrapperk.getPersons());

	            
	            vsetPersonFilePathK(file);

	        } catch (Exception e) {
	            Dialogs.create()
	                    .title("Error")
	                    .masthead("Could not load data from file:\n" + file.getPath())
	                    .showException(e);
	        }
	    }

	  
	    public void savePersonDataToFile(File file) {
	        try {
	            JAXBContext vcontextk = JAXBContext
	                    .newInstance(VPersonListWrapperK.class);
	            Marshaller vmk = vcontextk.createMarshaller();
	            vmk.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

	         
	            VPersonListWrapperK vwrapperk = new VPersonListWrapperK();
	            vwrapperk.setPersons(vpersonDatak);

	            
	            vmk.marshal(vwrapperk, file);

	           
	            vsetPersonFilePathK(file);
	        } catch (Exception e) { 
	            Dialogs.create().title("Error")
	                    .masthead("Could not save data to file:\n" + file.getPath())
	                    .showException(e);
	        }
	    }
	    
	    public void vshowBirthdayStatisticsK() {
	        try {
	           
	            FXMLLoader vloaderk = new FXMLLoader();
	            vloaderk.setLocation(VMainAppK.class.getResource("view/BirthdayStatistics.fxml"));
	            AnchorPane vpagek = (AnchorPane) vloaderk.load();
	            Stage dialogStage = new Stage();
	            dialogStage.setTitle("Birthday Statistics");
	            dialogStage.initModality(Modality.WINDOW_MODAL);
	            dialogStage.initOwner(vprimaryStagek);
	            Scene scene = new Scene(vpagek);
	            dialogStage.setScene(scene);

	            VBirthdayStatisticsControllerK controller = vloaderk.getController();
	            controller.vsetPersonDataK(vpersonDatak);

	            dialogStage.show();

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
}