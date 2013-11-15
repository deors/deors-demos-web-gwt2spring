package deors.demos.web.gwt2spring.client.services;

import java.util.Map;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface PlaceOrderServiceAsync {

    void placeOrder(Map<String, Integer> products, AsyncCallback<String> callback);
}
