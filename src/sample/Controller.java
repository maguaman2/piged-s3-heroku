package sample;

import dao.UsuarioDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import modelo.Usuario;


public class Controller {
    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtApellido;

    @FXML
    private void onClick(ActionEvent event){

        try {
            System.out.println(txtNombre.getText()+" "+txtApellido.getText());

            Usuario user = new Usuario(txtNombre.getText(),txtApellido.getText());
            UsuarioDao insertar = new UsuarioDao();
            insertar.insertarUsuario(user);//lista.add(user1)
        } catch (Exception ex) {
            System.out.println("" + ex.getMessage());
        }


    }


}

