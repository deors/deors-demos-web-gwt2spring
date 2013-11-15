package deors.demos.web.gwt2spring.server.services;

import java.util.Collection;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import deors.demos.web.gwt2spring.client.services.GetProductsService;
import deors.demos.web.gwt2spring.shared.OrderException;
import deors.demos.web.gwt2spring.shared.Product;
import deors.demos.web.gwt2spring.spring.services.ProductService;

public class GetProductsServiceImpl
    extends RemoteServiceServlet
    implements GetProductsService {

    private static final long serialVersionUID = -2505459229819777073L;

    public GetProductsServiceImpl() {

        super();
    }

    public Collection<? extends Product> getProducts() throws OrderException {

        ApplicationContext context =
            WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        ProductService service = (ProductService) context.getBean("productService");

        return service.selectAll();
    }
}
