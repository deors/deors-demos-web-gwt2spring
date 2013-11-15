package deors.demos.web.gwt2spring.server.services;

import java.util.Map;
import java.util.Random;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import deors.demos.web.gwt2spring.client.services.PlaceOrderService;
import deors.demos.web.gwt2spring.shared.OrderException;

public class PlaceOrderServiceImpl
    extends RemoteServiceServlet implements PlaceOrderService {

    private static final long serialVersionUID = 2994660159466254843L;

    @Override
    public String placeOrder(Map<String, Integer> products)
        throws OrderException {

        boolean valid = true;

        System.out.println("order received in the server");
        for (Map.Entry<String, Integer> entry : products.entrySet()) {
            System.out.println("  " + entry.getKey() + " quantity " + entry.getValue());
            if (entry.getKey().contains("Brik")) {
                valid = false;
            }
        }

        if (!valid) {
            throw new OrderException("Invalid product");
        }

        Random r = new Random();
        return Integer.toString(Math.abs(r.nextInt()));
    }
}
