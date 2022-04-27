package module;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "shoppingdetails")
public class shoppingdetails {
    @Id
    private int shoppingDetailsID;
    private double price;
}
