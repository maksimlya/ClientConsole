package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Main extends Application {

    static Socket socket = null;

    @Override
    public void start(Stage primaryStage) throws Exception{

        socket = new Socket("localhost",4000);
        System.out.println("Connected to server at " + socket.getLocalAddress() + ":" + socket.getLocalPort());
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        root.getStylesheets().add("sample/style.css");
        primaryStage.setTitle("Welcome to Afeka-War-Game console!");
        primaryStage.setScene(new Scene(root, 800, 375));
        primaryStage.setResizable(false);
        primaryStage.show();

        primaryStage.setOnHiding(event -> {
            try {
                DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
                outputStream.writeUTF("goodbye");
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.exit(0);
        });
    }


    public static void main(String[] args) {
        launch(args);
    }
}
