package view;

import java.util.EventObject;

public class AddPatientEvent extends EventObject {
    
    private String firstName;
    private String lastName;
    private String address;
    private String ssd;
    private String phoneNumber;

    public AddPatientEvent(Object source, String firstName, String lastName,
                           String address, String ssd, String phoneNumber) {
        super(source);
        
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.ssd = ssd;               
    }

    public String getAddress() {
        return address;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getSsd() {
        return ssd;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setSsd(String ssd) {
        this.ssd = ssd;
    }            
}
