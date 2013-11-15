package deors.demos.web.gwt2spring.client.services;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import deors.demos.web.gwt2spring.shared.OrderException;

public interface GetProductNamesServiceAsync {

    void getProductNames(AsyncCallback<List<String>> callback) throws OrderException;
}
