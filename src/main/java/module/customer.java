package module;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="customer")
public class customer {

   @Id
    private Long customerID;

    private String fName;

    private  String lName;

    private  String dob;

    private  int phoneNumber;

    private  String email;

    private String passward;

    private String address;
 @OneToMany(mappedBy = "customer")
 @JoinColumn(name = "customerID")
 private List<savedList> savedLists;
 @OneToMany(mappedBy = "customer")
 @JoinColumn(name = "customerID")
 private List<shoppingcart>shoppingcarts;
 @OneToMany(mappedBy = "offerID")
 @JoinColumn(name = "offerID")
 private List<offer> offers;
}
