package Valentinaa.Kostyukhina.ivt.ch.makery.adress.model;

import java.time.LocalDate;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import Valentinaa.Kostyukhina.ivt.ch.makery.adress.util.VLocalDateAdapterK;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class VPersonK {

    private final StringProperty vfirstNamek;
    private final StringProperty vlastNamek;
    private final StringProperty vstreetk;
    private final IntegerProperty vpostalCodek;
    private final StringProperty vcityk;
    private final ObjectProperty<LocalDate> vbirthdayk;

    public VPersonK() {
		this(null, null);
	}

    public VPersonK(String firstName, String lastName) {
        this.vfirstNamek = new SimpleStringProperty(firstName);
        this.vlastNamek = new SimpleStringProperty(lastName);
        this.vstreetk = new SimpleStringProperty("some street");
        this.vpostalCodek = new SimpleIntegerProperty(1234);
        this.vcityk = new SimpleStringProperty("some city");
        this.vbirthdayk = new SimpleObjectProperty<LocalDate>(LocalDate.of(1999, 2, 21));
    }

    public String getFirstName() {
        return vfirstNamek.get();
    }

    public void setFirstName(String firstName) {
        this.vfirstNamek.set(firstName);
    }

    public StringProperty firstNameProperty() {
        return vfirstNamek;
    }

    public String getLastName() {
        return vlastNamek.get();
    }

    public void setLastName(String lastName) {
        this.vlastNamek.set(lastName);
    }

    public StringProperty lastNameProperty() {
        return vlastNamek;
    }

    public String getStreet() {
        return vstreetk.get();
    }

    public void setStreet(String street) {
        this.vstreetk.set(street);
    }

    public StringProperty streetProperty() {
        return vstreetk;
    }

    public int getPostalCode() {
        return vpostalCodek.get();
    }

    public void setPostalCode(int postalCode) {
        this.vpostalCodek.set(postalCode);
    }

    public IntegerProperty postalCodeProperty() {
        return vpostalCodek;
    }

    public String getCity() {
        return vcityk.get();
    }

    public void setCity(String city) {
        this.vcityk.set(city);
    }

    public StringProperty cityProperty() {
        return vcityk;
    }
    
    @XmlJavaTypeAdapter(VLocalDateAdapterK.class)
    public LocalDate getBirthday() {
    	    return vbirthdayk.get();
    	}
   
    public void setBirthday(LocalDate birthday) {
        this.vbirthdayk.set(birthday);
    }

    public ObjectProperty<LocalDate> birthdayProperty() {
        return vbirthdayk;
    }
}