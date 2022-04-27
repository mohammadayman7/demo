package module;

import javax.persistence.*;
import java.util.List;
import java.util.ListResourceBundle;

@Entity
@Table(name = "category")
public class category {
    @Id
    private int categoryID;
    private String categoryName;
    private int rootID;
    @OneToMany(mappedBy = "category")
    @JoinColumn(name = "itemID")
    private List<item>items;

}
