package deors.demos.web.gwt2spring.shared;

import java.io.Serializable;
import java.math.BigDecimal;

public interface Product extends Serializable {

    String getId();

    String getName();

    String getCategory();

    BigDecimal getPrice();

    void setId(String id);

    void setName(String name);

    void setCategory(String category);

    void setPrice(BigDecimal price);

}
