package lk.ijse.gdse66.hibernate.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.gdse66.hibernate.entity.Orders;
import lk.ijse.gdse66.hibernate.repo.OrdersRepo;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author : Kavithma Thushal
 * @project : ORM_GDSE66
 * @since : 7:26 AM - 8/7/2023
 **/
public class PlaceOrdersFormController {

    @FXML
    private AnchorPane root;
    @FXML
    private TextField txtOrderId;
    @FXML
    private TextField txtOrderDate;
    @FXML
    private TextField txtCustomerId;
    @FXML
    private TextField txtItemCode;
    private OrdersRepo ordersRepo;

    public void initialize() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime currentDateTime = LocalDateTime.now();
        txtOrderDate.setText(currentDateTime.format(formatter));
    }

    @FXML
    private void placeOrderOnAction(ActionEvent actionEvent) {
        Orders orders = new Orders();
        orders.setOrderId(Integer.parseInt(txtOrderId.getText()));
        orders.setCustomerId(Integer.parseInt(txtCustomerId.getText()));
        orders.setItemCode(Integer.parseInt(txtItemCode.getText()));

        ordersRepo = new OrdersRepo();
        boolean isOrdered = ordersRepo.placeOrder(orders);
        if (isOrdered == true) {
            new Alert(Alert.AlertType.INFORMATION, "Order placed successfully!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Try Again!").show();
        }
    }

    @FXML
    private void backOnAction(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) this.root.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/lk/ijse/gdse66/hibernate/view/dashboard_form.fxml"))));
    }
}
