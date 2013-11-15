package deors.demos.web.gwt2spring.server.services;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import deors.demos.web.gwt2spring.client.services.GetProductNamesService;
import deors.demos.web.gwt2spring.shared.OrderException;
import deors.demos.web.gwt2spring.spring.services.ProductService;

public class GetProductNamesServiceImpl
    extends RemoteServiceServlet
    implements GetProductNamesService {

    private static final long serialVersionUID = 1264506849809341557L;

    public GetProductNamesServiceImpl() {

        super();
    }

    public List<String> getProductNames() throws OrderException {

        ApplicationContext context =
            WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        ProductService service = (ProductService) context.getBean("productService");

        return service.selectNames();
    }
}
