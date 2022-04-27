package module;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "auction")
public class auction {

    @Id
    private int auctionID;
    private Date startDate;
    private Date endDate;
    private double startPrice;
    private double endPrice;
    @OneToOne(mappedBy = "itemID")
    @JoinColumn(name = "itemID")
    private item item;
    @OneToMany(mappedBy = "offerID")
    @JoinColumn(name = "offerID")
    private List<offer> offers;
}
