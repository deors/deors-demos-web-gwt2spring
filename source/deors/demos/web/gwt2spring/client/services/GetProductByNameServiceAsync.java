package deors.demos.web.gwt2spring.client.services;

import com.google.gwt.user.client.rpc.AsyncCallback;

import deors.demos.web.gwt2spring.shared.OrderException;
import deors.demos.web.gwt2spring.shared.Product;

public interface GetProductByNameServiceAsync {

    void getProduct(String name, AsyncCallback<Product> callback) throws OrderException;
}
