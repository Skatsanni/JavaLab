package Valentinaa.Kostyukhina.ivt.ch.makery.adress.model;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "persons")
public class VPersonListWrapperK {
	
	    private List<VPersonK> persons;

	    @XmlElement(name = "person")
	    public List<VPersonK> getPersons() {
	        return persons;
	    }

	    public void setPersons(List<VPersonK> persons) {
	        this.persons = persons;
	    }
}