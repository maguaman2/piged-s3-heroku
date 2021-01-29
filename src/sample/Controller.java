package sample;

import archivos.Archivos;
import dao.UsuarioDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import modelo.Usuario;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;


public class Controller implements Initializable {
    @FXML
    private Button btnGuardar;

    @FXML
    private Button btnListar;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtApellido;

    @FXML
    private TableView<Usuario> table1;

    @FXML
    private TableColumn<Usuario,Integer> colId;

    @FXML
    private TableColumn<Usuario,String> colNombre;

    @FXML
    private TableColumn<Usuario,String> colApellido;

    ObservableList<Usuario> obsList = FXCollections.observableArrayList();

    @FXML
    private void onClick1(MouseEvent event) {
        ObservableList<Usuario> row;
        row=table1.getSelectionModel().getSelectedItems();
        //colId.setText(String.valueOf(row.get(0).getId()));
        txtNombre.setText(row.get(0).getNombre());
        txtApellido.setText(row.get(0).getApellido());
    }

    @FXML
    private void onClick(ActionEvent event) {
        HashMap<String,String> datos = new HashMap<>();

        datos=Archivos.leerArchivo("C:\\archivos\\base.txt");
        System.out.println(datos.get("user"));

        Object evento = event.getSource();
        if (evento.equals(btnListar)) {
            System.out.println("Detecta pulsado boton listar");
        } else {
            try {
                System.out.println(txtNombre.getText() + " " + txtApellido.getText());
                Usuario user = new Usuario(txtNombre.getText(), txtApellido.getText());
                UsuarioDao insertar = new UsuarioDao();
                insertar.insertarUsuario(user);
            } catch (Exception ex) {
                System.out.println("" + ex.getMessage());
            }
            llenarLista();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colId.setCellValueFactory(new PropertyValueFactory<Usuario,Integer>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<Usuario,String>("nombre"));
        colApellido.setCellValueFactory(new PropertyValueFactory<Usuario,String>("apellido"));
        llenarLista();
    }

    private void llenarLista() {
        table1.getItems().clear();
        try {
            UsuarioDao usuarioDao = new UsuarioDao();
            for (Usuario usuario : usuarioDao.listar()) {
                obsList.add(usuario);
            }
            table1.setItems(obsList);
        } catch (Exception ex) {
            System.out.println("" + ex.getMessage());
        }
    }

}

