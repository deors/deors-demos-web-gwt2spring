package deors.demos.web.gwt2spring.spring.services;

import java.util.Collection;
import java.util.List;

import deors.demos.web.gwt2spring.shared.OrderException;
import deors.demos.web.gwt2spring.shared.Product;

public interface ProductService {

    Product select(Product criteria) throws OrderException;

    Collection<? extends Product> selectAll() throws OrderException;

    List<String> selectNames() throws OrderException;
}
