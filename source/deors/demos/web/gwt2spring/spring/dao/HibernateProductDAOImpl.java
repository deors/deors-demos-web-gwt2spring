package deors.demos.web.gwt2spring.spring.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import deors.demos.web.gwt2spring.shared.OrderException;
import deors.demos.web.gwt2spring.shared.Product;
import deors.demos.web.gwt2spring.shared.ProductImpl;

@Repository("productDAO")
public class HibernateProductDAOImpl
    extends HibernateTemplate
    implements ProductDAO {

    public HibernateProductDAOImpl() {

        super();
    }

    @Override
    public Product select(Product criteria) throws OrderException {

        try {
            return (Product) find("from ProductImpl where name=?", criteria.getName()).get(0);
        } catch (org.springframework.dao.DataAccessException dae) {
            // dae.printStackTrace();
            throw new OrderException("error", dae);
        }
    }

    @Override
    public Collection<? extends Product> selectAll() throws OrderException {

        try {
            return loadAll(ProductImpl.class);
        } catch (org.springframework.dao.DataAccessException dae) {
            // dae.printStackTrace();
            throw new OrderException("error", dae);
        }
    }

    @Override
    public List<String> selectNames() throws OrderException {

        try {
            Collection<? extends Product> products = selectAll();
            List<String> names = new ArrayList<String>();
            for (Product p : products) {
                names.add(p.getName());
            }
            return names;
        } catch (org.springframework.dao.DataAccessException dae) {
            // dae.printStackTrace();
            throw new OrderException("error", dae);
        }
    }
}
