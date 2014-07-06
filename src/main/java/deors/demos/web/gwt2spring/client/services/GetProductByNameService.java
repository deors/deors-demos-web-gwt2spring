package deors.demos.web.gwt2spring.client.services;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import deors.demos.web.gwt2spring.shared.OrderException;
import deors.demos.web.gwt2spring.shared.Product;

@RemoteServiceRelativePath("getProductByName")
public interface GetProductByNameService extends RemoteService {

    Product getProduct(String name) throws OrderException;
}
