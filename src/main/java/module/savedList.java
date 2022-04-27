package module;

import javax.persistence.*;

@Entity
@Table(name = "savedList")
public class savedList {
    @Id
    private int listID;
    @ManyToOne
    @JoinColumn(name = "customerID")
    private customer customer;
    @ManyToOne
    @JoinColumn(name = "itemID")
    private item item;

}
