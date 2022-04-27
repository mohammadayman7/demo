package module;

import javax.persistence.*;

@Entity
@Table(name = "shoppingcart")
public class shoppingcart {
    @Id
    private int cartID;
    private int numOfItems;
    private double totalPrice;
    @ManyToOne
    @JoinColumn(name = "itemID")
    private item item;
    @ManyToOne
    @JoinColumn(name = "customerID")
    private customer customer;
    @OneToOne(mappedBy = "odersID")
    @JoinColumn(name = "odersID")
    private orders orders;


}
