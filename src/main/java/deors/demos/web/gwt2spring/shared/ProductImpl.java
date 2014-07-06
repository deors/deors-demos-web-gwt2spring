package deors.demos.web.gwt2spring.shared;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUCT")
public class ProductImpl
    implements Product {

    private static final long serialVersionUID = -8275686314692860544L;

    private String id;

    private String name;

    private String category;

    private BigDecimal price;

    public ProductImpl() {

        super();
    }

    public ProductImpl(String id, String name, String category, BigDecimal price) {

        this();
        setId(id);
        setName(name);
        setCategory(category);
        setPrice(price);
    }

    @Id
    @Column(name = "ID")
    public String getId() {

        return id;
    }

    @Column(name = "NAME")
    public String getName() {

        return name;
    }

    @Column(name = "CATEGORY")
    public String getCategory() {

        return category;
    }

    @Column(name = "PRICE")
    public BigDecimal getPrice() {

        return price;
    }

    public void setId(String id) {

        this.id = id;
    }

    public void setName(String name) {

        this.name = name;
    }

    public void setCategory(String category) {

        this.category = category;
    }

    public void setPrice(BigDecimal price) {

        this.price = price;
    }
}
