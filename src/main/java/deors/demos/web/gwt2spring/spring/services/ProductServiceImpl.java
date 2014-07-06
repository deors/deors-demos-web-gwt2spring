package deors.demos.web.gwt2spring.spring.services;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import deors.demos.web.gwt2spring.shared.OrderException;
import deors.demos.web.gwt2spring.shared.Product;
import deors.demos.web.gwt2spring.spring.dao.ProductDAO;

@Service("productService")
@Transactional
public class ProductServiceImpl
    implements ProductService {

    private ProductDAO productDAO;

    public void setProductDAO(ProductDAO productDAO) {

        this.productDAO = productDAO;
    }

    public ProductServiceImpl() {

        super();
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public Product select(Product criteria) throws OrderException {

        return productDAO.select(criteria);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public Collection<? extends Product> selectAll() throws OrderException {

        return productDAO.selectAll();
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public List<String> selectNames() throws OrderException {

        return productDAO.selectNames();
    }
}
