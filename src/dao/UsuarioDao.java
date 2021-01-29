package dao;

import modelo.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UsuarioDao extends Conexion {

    public ArrayList<Usuario> listar() {
        ArrayList<Usuario> usuarios = new ArrayList();

        try (Connection connection = connectToDB()) {
            String query = "SELECT * FROM usuario"; //sentencia
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Usuario user = new Usuario(
                        Integer.valueOf(rs.getString("id")),
                        rs.getString("nombre"),
                        rs.getString("apellido")
                );
                usuarios.add(user);
            }
        } catch (SQLException e) {
            System.out.println("" + e.getMessage());
            // TODO: handle exception
        }

        return usuarios;
    }
    //Guardar un nuevo usuario
    public void insertarUsuario(Usuario usuario) {
        try (Connection connection = connectToDB()) {
            PreparedStatement ps = null;
            //Statement statement = connection.createStatement();
            String query = "insert into usuario (nombre, apellido) values (?,?)";
            ps = connection.prepareStatement(query);
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getApellido());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("la tabla no exist");
            //e.printStackTrace();
        }
    }

    //Actualizar  un usuario
    public void actualizarUsuario(Usuario usuario) {
        try (Connection connection = connectToDB()) {
            PreparedStatement ps = null;
            //Statement statement = connection.createStatement();
            String query = "update usuario set usunombre = ?, usuapellido = ? where usuid =?"; //ojo no olvidarsse del where
            ps = connection.prepareStatement(query);
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getApellido());
            ps.setInt(3,  usuario.getId());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("error:"+e.getMessage() );
            //estos errores guardar ennun archivo
        }
    }
    //Eliminar  un usuario
    public void eliminarUsuario(int id) {
        try (Connection connection = connectToDB()) {
            PreparedStatement ps = null;
            //Statement statement = connection.createStatement();
            String query = "delete from usuario where usuid =? ";///ojo con el where
            ps = connection.prepareStatement(query);
            ps.setInt(1, id);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
