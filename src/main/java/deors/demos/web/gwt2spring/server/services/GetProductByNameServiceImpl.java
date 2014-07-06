package deors.demos.web.gwt2spring.server.services;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import deors.demos.web.gwt2spring.client.services.GetProductByNameService;
import deors.demos.web.gwt2spring.shared.OrderException;
import deors.demos.web.gwt2spring.shared.Product;
import deors.demos.web.gwt2spring.spring.services.ProductService;

public class GetProductByNameServiceImpl
    extends RemoteServiceServlet
    implements GetProductByNameService {

    private static final long serialVersionUID = -2476299570084020006L;

    public GetProductByNameServiceImpl() {

        super();
    }

    public Product getProduct(String name) throws OrderException {

        ApplicationContext context =
            WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        ProductService service = (ProductService) context.getBean("productService");

        Product criteria = (Product) context.getBean("product");
        criteria.setName(name);

        return service.select(criteria);
    }
}
