package module;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "offer")
public class offer {
    @Id
    private int offerID;
    private String offerDiscription;
    private int price;
    private Date offerDate;
    @ManyToOne
    @JoinColumn(name = "auctionID")
    private auction auction;
    @ManyToOne
    @JoinColumn(name = "customerID")
    private customer customer;
}
