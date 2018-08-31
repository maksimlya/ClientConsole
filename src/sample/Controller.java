package sample;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private  String launcherIdValue = "L101";
    private String launcherDestructorIdValue = "LD201";
    private String missileDestructorIdValue = "D301";
    private String missileIdValue = "M1";

    private DataOutputStream outputStream;

    {
        try {
            outputStream = new DataOutputStream(Main.socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML TextField launcher_id;
    @FXML TextField dest_launcher_id;
    @FXML TextField launch_id;
    @FXML CheckBox is_hidden;
    @FXML TextField launcher_destructor_id;
    @FXML TextField launch_dest_id;
    @FXML TextField missile_destructor_id;
    @FXML TextField missile_dest_id;
    @FXML TextField missile_id;
    @FXML TextField dest_missile_id;
    @FXML ComboBox<String> type_box;
    @FXML TextField destination_fld;
    @FXML TextField fly_time_fld;
    @FXML TextField damage_fld;

    @FXML
    private void changeLauncherId(KeyEvent event){

        Object src = event.getSource();
        launcherIdValue = ((TextField)src).getText() ;

        if(!src.equals(launcher_id))
            launcher_id.setText(launcherIdValue);
      if(!src.equals(launch_id))
          launch_id.setText(launcherIdValue);
        if(!src.equals(dest_launcher_id))
            dest_launcher_id.setText(launcherIdValue);
    }

    @FXML
    private void changeLauncherDestructorId(KeyEvent event){

        Object src = event.getSource();
        launcherDestructorIdValue = ((TextField)src).getText() ;

        if(!src.equals(launcher_destructor_id))
            launcher_destructor_id.setText(launcherDestructorIdValue);
        if(!src.equals(launch_dest_id))
            launch_dest_id.setText(launcherDestructorIdValue);

    }

    @FXML
    private void changeMissileDestructorId(KeyEvent event){

        Object src = event.getSource();
        missileDestructorIdValue = ((TextField)src).getText() ;

        if(!src.equals(missile_destructor_id))
            missile_destructor_id.setText(missileDestructorIdValue);
        if(!src.equals(missile_dest_id))
            missile_dest_id.setText(missileDestructorIdValue);

    }

    @FXML
    private void changeMissileId(KeyEvent event){

        Object src = event.getSource();
        missileIdValue = ((TextField)src).getText() ;

        if(!src.equals(missile_id))
            missile_id.setText(missileIdValue);
        if(!src.equals(dest_missile_id))
            dest_missile_id.setText(missileIdValue);

    }

    @FXML
    private void addMissileLauncher(){
        String launcherId = launcher_id.getText();
        boolean isHidden = is_hidden.isSelected();

        try {
            outputStream.writeUTF("addMissileLauncher");
            outputStream.writeUTF(launcherId);
            outputStream.writeBoolean(isHidden);

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Launcher Id: " + launcherId + ", isHidden?: " + isHidden);
    }

    @FXML
    private void addLauncherDestructor(){
        String destructorId = launcher_destructor_id.getText();
        String type = type_box.getValue();

        try {
            outputStream.writeUTF("addLauncherDestructor");
            outputStream.writeUTF(destructorId);
            outputStream.writeUTF(type);

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Destructor Id: " + destructorId + ", type: " + type);
    }

    @FXML
    private void addMissileDestructor(){
        String destructorId = missile_destructor_id.getText();


        try {
            outputStream.writeUTF("addMissileDestructor");
            outputStream.writeUTF(destructorId);

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Destructor Id: " + destructorId);
    }

    @FXML
    private void launchMissile(){
        String launcherId = launcherIdValue;
        String missileId = missileIdValue;
        String destination = destination_fld.getText();
        String flyTime = fly_time_fld.getText();
        String damage = damage_fld.getText();

        try {
            outputStream.writeUTF("launchMissile");
            outputStream.writeUTF(missileId);
            outputStream.writeUTF(launcherId);
            outputStream.writeUTF(destination);
            outputStream.writeUTF(flyTime);
            outputStream.writeUTF(damage);

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Launch Missile id: " + missileId + ", from launcher: " + launcherId + ", at: " + destination + ", flyTime: " + flyTime + ", damage: " + damage);
    }

    @FXML
    private void destructMissile(){
        String missileId = missileIdValue;
        String destructorId = missileDestructorIdValue;

        try {
            outputStream.writeUTF("destructMissile");
            outputStream.writeUTF(missileId);
            outputStream.writeUTF(destructorId);

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Attempting to destroy missile id: " + missileId + " from destructor: " + destructorId);
    }

    @FXML
    private void destructLauncher(){
        String launcherId = launcherIdValue;
        String destructorId = launcherDestructorIdValue;

        try {
            outputStream.writeUTF("destructLauncher");
            outputStream.writeUTF(launcherId);
            outputStream.writeUTF(destructorId);

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Attempting to destroy launcher id: " + launcherId + " from destructor: " + destructorId);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        type_box.getItems().removeAll(type_box.getItems());
        type_box.getItems().addAll("Plane", "Ship");
        type_box.getSelectionModel().select("Plane");
    }


}
