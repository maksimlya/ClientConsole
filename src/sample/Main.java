package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Main extends Application {

    public static Socket socket = null;

    @Override
    public void start(Stage primaryStage) throws Exception{

        socket = new Socket("localhost",4000);
        System.out.println("Connected to server at " + socket.getLocalAddress() + ":" + socket.getLocalPort());
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Welcome to Afeka-War-Game console!");
        primaryStage.setScene(new Scene(root, 600, 475));
        primaryStage.show();

        primaryStage.setOnHiding(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                try {
                    DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
                    outputStream.writeUTF("goodbye");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.exit(0);
            }
        });
    }


    public static void main(String[] args) {
        launch(args);
    }
}
