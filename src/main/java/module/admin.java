package module;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "admin")
public class admin {
    @Id

    private Long adminID;

    private String fName;

    private String lName;

    private String dob;

    private int phoneNumber;

    private String email;

    private String passward;

    public admin(Long adminID, String fName, String lName, String dob, int phoneNumber, String email) {
        this.adminID = adminID;
        this.fName = fName;
        this.lName = lName;
        this.dob = dob;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public admin() {

    }

    public Long getAdminID() {
        return adminID;
    }

    public void setAdminID(Long adminID) {
        this.adminID = adminID;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}