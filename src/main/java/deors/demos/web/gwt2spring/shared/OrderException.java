package deors.demos.web.gwt2spring.shared;

public class OrderException
    extends Exception {

    private static final long serialVersionUID = 3789975608310005259L;

    public OrderException() {

        super();
    }

    public OrderException(String message) {

        super(message);
    }

    public OrderException(Throwable cause) {

        super(cause);
    }

    public OrderException(String message, Throwable cause) {

        super(message, cause);
    }
}
