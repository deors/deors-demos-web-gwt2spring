package deors.demos.web.gwt2spring.client.services;

import java.util.Collection;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import deors.demos.web.gwt2spring.shared.OrderException;
import deors.demos.web.gwt2spring.shared.Product;

@RemoteServiceRelativePath("getProducts")
public interface GetProductsService extends RemoteService {

    Collection<? extends Product> getProducts() throws OrderException;
}
