package deors.demos.web.gwt2spring.client.entrypoints;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import deors.demos.web.gwt2spring.client.services.GetProductByNameService;
import deors.demos.web.gwt2spring.client.services.GetProductByNameServiceAsync;
import deors.demos.web.gwt2spring.client.services.GetProductNamesService;
import deors.demos.web.gwt2spring.client.services.GetProductNamesServiceAsync;
import deors.demos.web.gwt2spring.client.services.PlaceOrderService;
import deors.demos.web.gwt2spring.client.services.PlaceOrderServiceAsync;
import deors.demos.web.gwt2spring.shared.OrderException;
import deors.demos.web.gwt2spring.shared.Product;

public class ProductListSpring
    implements EntryPoint {

    // GUI elements

    private VerticalPanel mainPanel = new VerticalPanel();
    private Label headerLabel = new Label("Product List");
    private FlexTable productsTable = new FlexTable();
    private Label noProductsLabel = new Label("No products selected yet");
    private HorizontalPanel totalPricePanel = new HorizontalPanel();
    private Label totalPriceLabel = new Label("The total price for your order is: ");
    private Label totalQuantityLabel = new Label();
    private PushButton submitButton = new PushButton("Submit your order");
    private Label newProductLabel = new Label("Select the product that you want to add to the list:");
    private HorizontalPanel newProductPanel = new HorizontalPanel();
    private Label lastUpdateLabel = new Label();
    private MultiWordSuggestOracle newProductOracle = new MultiWordSuggestOracle();
    private SuggestBox newProductText = new SuggestBox(newProductOracle);
    private PushButton newProductButton = new PushButton("Add product to list");
    private DialogBox dialogBox = new DialogBox();
    private PushButton closeButton = new PushButton("Close");
    private VerticalPanel confirmPanel = new VerticalPanel();
    private HTML messageText = new HTML();
    private HTML confirmText = new HTML();

    // services

    private GetProductNamesServiceAsync getProductNamesSvc = GWT.create(GetProductNamesService.class);
    private GetProductByNameServiceAsync getProductByNameSvc = GWT.create(GetProductByNameService.class);
    private PlaceOrderServiceAsync placeOrderSvc = GWT.create(PlaceOrderService.class);

    // data structures

    private List<String> productNames = new ArrayList<String>();
    private List<String> addedNames = new ArrayList<String>();
    private Map<String, Integer> order = new HashMap<String, Integer>();

    public void onModuleLoad() {

        // creates the GUI layout

        mainPanel.add(headerLabel);
        mainPanel.add(productsTable);
        mainPanel.add(noProductsLabel);
        mainPanel.add(totalPricePanel);
        mainPanel.add(newProductLabel);
        mainPanel.add(newProductPanel);
        mainPanel.add(lastUpdateLabel);

        headerLabel.addStyleName("headerLabel");

        productsTable.setText(0, 0, "Product Id");
        productsTable.setText(0, 1, "Product Name");
        productsTable.setText(0, 2, "Price");
        productsTable.setText(0, 3, "Quantity");
        productsTable.setText(0, 4, "Line Total");
        productsTable.setText(0, 5, "");
        productsTable.addStyleName("productsTableContent");
        productsTable.getRowFormatter().addStyleName(0, "productsTableHeader");
        productsTable.getCellFormatter().addStyleName(0, 0, "productsTableIdCell");
        productsTable.getCellFormatter().addStyleName(0, 1, "productsTableNameCell");
        productsTable.getCellFormatter().addStyleName(0, 2, "productsTablePriceCell");
        productsTable.getCellFormatter().addStyleName(0, 3, "productsTableQuantityCell");
        productsTable.getCellFormatter().addStyleName(0, 4, "productsTablePriceCell");
        productsTable.getCellFormatter().addStyleName(0, 5, "productsTableActionCell");
        productsTable.setVisible(false);

        noProductsLabel.addStyleName("noProductsLabel");

        totalPricePanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
        totalPricePanel.add(totalPriceLabel);
        totalPricePanel.add(totalQuantityLabel);
        totalPricePanel.add(submitButton);
        totalPricePanel.addStyleName("totalPricePanel");
        totalPricePanel.setVisible(false);

        totalPriceLabel.addStyleName("totalPriceLabel");

        totalQuantityLabel.addStyleName("totalQuantityLabel");

        submitButton.addStyleName("submitButton");

        newProductLabel.addStyleName("newProductLabel");

        newProductPanel.add(newProductText);
        newProductPanel.add(newProductButton);
        newProductPanel.addStyleName("newProductPanel");

        newProductText.addStyleName("newProductText");

        newProductButton.addStyleName("newProductButton");

        lastUpdateLabel.addStyleName("lastUpdateLabel");

        // creates the confirmation dialog
        dialogBox.setText("Order Confirmation");
        dialogBox.setAnimationEnabled(true);

        messageText.addStyleName("messageText");

        confirmText.addStyleName("confirmText");

        closeButton.addStyleName("closeButton");

        confirmPanel.add(messageText);
        confirmPanel.add(confirmText);
        confirmPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        confirmPanel.add(closeButton);
        confirmPanel.add(new HTML("<br/>"));
        confirmPanel.addStyleName("confirmPanel");

        dialogBox.setWidget(confirmPanel);

        // event handlers

        submitButton.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {

                submitButton.setEnabled(false);
                submitOrder();
            }
        });

        newProductText.addKeyPressHandler(new KeyPressHandler() {

            @Override
            public void onKeyPress(KeyPressEvent event) {

                if (event.getCharCode() == KeyCodes.KEY_ENTER) {
                    addProduct();
                }
            }
        });

        newProductButton.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {

                addProduct();
            }
        });

        closeButton.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {

                dialogBox.hide();
                submitButton.setEnabled(true);
                submitButton.setFocus(true);
            }
        });

        // adds the GUI elements to the HTML page
        RootPanel.get("productListSpring").add(mainPanel);

        // loads the product names list
        loadProductNames();

        // refreshes widget status
        refresh();
    }

    private void loadProductNames() {

        try {
            getProductNamesSvc.getProductNames(new AsyncCallback<List<String>>() {

                @Override
                public void onFailure(Throwable caught) {
                    Window.alert("The product list could not be loaded. Pleasy try again by reloading this page.");
                }

                @Override
                public void onSuccess(List<String> result) {
                    productNames = result;
                    newProductOracle.addAll(result);
                }
            });
        } catch (OrderException oe) {

            Window.alert("The product list could not be loaded. Pleasy try again by reloading this page.");
        }
    }

    private void addProduct() {

        if (productNames.size() == 0) {
            Window.alert("Product list not loaded. Please try again by reloading this page.");
            return;
        }

        final String productName = newProductText.getText().trim();

        // validate whether a product was entered
        if (productName.length() == 0) {
            Window.alert("Please, select a product by its name.");
            return;
        }

        // validate whether the product is already in the table
        if (addedNames.contains(productName)) {
            Window.alert("The product '" + productName + "' was already added to the list.");
            return;
        }

        // validate product name and retrieve product data
        if (!productNames.contains(productName)) {
            Window.alert("The product '" + productName + "' was not found in our database.");
            return;
        }

        // add the product, price and set quantity to 1
        // add button to remove the product from the list
        addedNames.add(productName);

        try {
            getProductByNameSvc.getProduct(productName, new AsyncCallback<Product>() {

                @Override
                public void onFailure(Throwable caught) {
                    Window.alert("The product information could not be read. Please try again");
                    addedNames.remove(productName);
                }

                @Override
                public void onSuccess(Product result) {

                    int row = productsTable.getRowCount();

                    productsTable.getRowFormatter().addStyleName(row, "productsTablePajama" + (row % 2));
                    productsTable.getCellFormatter().addStyleName(row, 0, "productsTableIdCell");
                    productsTable.getCellFormatter().addStyleName(row, 1, "productsTableNameCell");
                    productsTable.getCellFormatter().addStyleName(row, 2, "productsTablePriceCell");
                    productsTable.getCellFormatter().addStyleName(row, 3, "productsTableQuantityCell");
                    productsTable.getCellFormatter().addStyleName(row, 4, "productsTablePriceCell");
                    productsTable.getCellFormatter().addStyleName(row, 5, "productsTableActionCell");

                    productsTable.setText(row, 0, result.getId());
                    productsTable.setText(row, 1, result.getName());
                    productsTable.setText(row, 2, result.getPrice().toString());

                    TextBox quantityText = new TextBox();
                    quantityText.setText("1");
                    quantityText.addStyleName("productsTableQuantitycell");
                    quantityText.addChangeHandler(new ChangeHandler() {

                        @Override
                        public void onChange(ChangeEvent event) {

                            int index = addedNames.indexOf(productName);
                            BigDecimal price = new BigDecimal(productsTable.getText(index + 1, 2));
                            BigDecimal quantity = new BigDecimal(((TextBox) event.getSource()).getText());
                            BigDecimal lineTotal = price.multiply(quantity);
                            productsTable.setText(index + 1, 4, lineTotal.toString());

                            order.put(productName, quantity.intValue());

                            refresh();
                        }
                    });
                    productsTable.setWidget(row, 3, quantityText);

                    productsTable.setText(row, 4, result.getPrice().toString());

                    PushButton removeButton = new PushButton("Remove");
                    removeButton.addStyleName("removeButton");
                    removeButton.addClickHandler(new ClickHandler() {

                        @Override
                        public void onClick(ClickEvent event) {

                            int index = addedNames.indexOf(productName);
                            addedNames.remove(productName);
                            productsTable.removeRow(index + 1);

                            order.remove(productName);

                            refresh();
                        }
                    });
                    productsTable.setWidget(row, 5, removeButton);

                    newProductText.setText("");

                    order.put(productName, 1);

                    refresh();
                }
            });
        } catch (OrderException oe) {

            Window.alert("The product list could not be loaded. Pleasy try again by reloading this page.");
        }
    }

    private void refresh() {

        calculateTotal();

        productsTable.setVisible(productsTable.getRowCount() != 1);
        noProductsLabel.setVisible(productsTable.getRowCount() == 1);
        totalPricePanel.setVisible(productsTable.getRowCount() != 1);

        // formats current date/time
        String now = DateTimeFormat.getFormat(PredefinedFormat.DATE_TIME_LONG)
            .format(new Date());
        lastUpdateLabel.setText("Last updated at: " + now);

        // moves the focus to the new product text field
        newProductText.setFocus(true);
    }

    private void calculateTotal() {

        BigDecimal total = new BigDecimal("0");
        int rows = productsTable.getRowCount();
        for (int i = 1; i < rows; i++) {
            total = total.add(new BigDecimal(productsTable.getText(i, 4)));
        }
        totalQuantityLabel.setText(total.toString());
    }

    private void submitOrder() {

        placeOrderSvc.placeOrder(order, new AsyncCallback<String>() {

            @Override
            public void onFailure(Throwable t) {

                dialogBox.setText("Order Failure");
                messageText.setHTML("<b>Your order was not processed</b>");
                confirmText.addStyleName("errorLabel");
                confirmText.setHTML("The error was: " + t.getMessage());
                dialogBox.center();
                closeButton.setFocus(true);
            }

            @Override
            public void onSuccess(String result) {

                dialogBox.setText("Order Confirmation");
                messageText.setHTML("<b>Your order was successfully processed</b>");
                confirmText.removeStyleName("errorLabel");
                confirmText.setHTML("Your order confirmation number is: " + result);
                dialogBox.center();
                closeButton.setFocus(true);
            }
        });
    }
}
