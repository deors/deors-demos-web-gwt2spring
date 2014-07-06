package deors.demos.web.gwt2spring.client.services;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import deors.demos.web.gwt2spring.shared.OrderException;

@RemoteServiceRelativePath("getProductNames")
public interface GetProductNamesService extends RemoteService {

    List<String> getProductNames() throws OrderException;
}
