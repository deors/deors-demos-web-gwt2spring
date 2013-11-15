package deors.demos.web.gwt2spring.client.services;

import java.util.Collection;

import com.google.gwt.user.client.rpc.AsyncCallback;

import deors.demos.web.gwt2spring.shared.OrderException;
import deors.demos.web.gwt2spring.shared.Product;

public interface GetProductsServiceAsync {

    void getProducts(AsyncCallback<Collection<? extends Product>> callback) throws OrderException;
}
