import com.mysql.cj.MysqlConnection;

import java.sql.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Executavel {

    public static void main(String[] args) {
        String urlBanco = "jdbc:mysql://localhost:3306/DbJava";
        String usuario = "root";
        String senha = "root";
        try {
            Connection connection = DriverManager.getConnection(urlBanco,usuario,senha);
            inserir(connection,new Usuario(1,"Nicolas","123",17));
            inserir(connection,new Usuario(2,"Miguel","321",18));
            inserir(connection,new Usuario(3,"Gustavo","111",18));
            inserir(connection,new Usuario(4,"Romario","222",20));
            inserir(connection,new Usuario(5,"Gabriel","333",17));
            System.out.println(connection);
            System.out.println(buscarTodos(connection));
            System.out.println(buscarUm(1,connection));
            deletarUm(4,connection);
            System.out.println(buscarTodos(connection));
            atualizarSenha(1,"777",connection);
            System.out.println(buscarUm(1,connection));
            atualizarId(2,20,connection);
            System.out.println(buscarUm(20,connection));
            atualizarIdade(4,18,connection);
            System.out.println(buscarUm(4,connection));
            atualizarNome(5,"Henrique",connection);
            System.out.println(buscarUm(5,connection));
            atualizarUsuario(20,777,777,"Jesus Cristo vulgo JC","JC777",connection);
            System.out.println(buscarTodos(connection));
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    static void inserir(Connection connection,Usuario usuario){
        try {
            Statement statement = connection.createStatement();
            String comandoSql = "insert into usuario VALUES (" + usuario.getId() +", '"+ usuario.getUsuario() +" ','"+ usuario.getSenha() +"','"+ usuario.getIdade() +"')";
            statement.execute(comandoSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    static Set<Usuario> buscarTodos(Connection connection){
        String comandoSql = "select * from usuario;";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(comandoSql);
            Set<Usuario> listaUsuario = new HashSet<>();
            while (resultSet.next()){
                Integer id = resultSet.getInt("id");
                String nome = resultSet.getString("nome");
                String senha = resultSet.getString("senha");
                Integer idade = resultSet.getInt("idade");
                Usuario usuario = new Usuario(id, nome, senha, idade);
                listaUsuario.add(usuario);
            }
            return listaUsuario;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    static Usuario buscarUm(Integer id, Connection connection){
        String comandoSql = "SELECT * FROM usuario WHERE id = " + id;
        try (Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(comandoSql);
            while (resultSet.next()){
                String nome = resultSet.getString("nome");
                String senha = resultSet.getString("senha");
                Integer idade = resultSet.getInt("idade");
                return new Usuario(id,nome,senha,idade);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    public static void deletarUm(Integer id, Connection connection) {
        String comandoSql = "DELETE FROM usuario WHERE id = " + id;
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(comandoSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void atualizarId(Integer id, Integer novoId, Connection connection) {
        String comandoSql = "UPDATE usuario SET id = " + novoId + " WHERE id = " + id;
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(comandoSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void atualizarNome(Integer id, String novoUsuario, Connection connection) {
        String comandoSql = "UPDATE usuario SET nome = '" + novoUsuario + "' WHERE id = " + id;
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(comandoSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void atualizarSenha(Integer id, String novaSenha, Connection connection) {
        String comandoSql = "UPDATE usuario SET senha = '" + novaSenha + "' WHERE id = " + id;
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(comandoSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void atualizarIdade(Integer id, Integer novaIdade, Connection connection) {
        String comandoSql = "UPDATE usuario SET idade = " + novaIdade + " WHERE id = " + id;
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(comandoSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void atualizarUsuario(Integer id, Integer novoId,Integer novaIdade,String novoNome, String novaSenha, Connection connection){
        atualizarSenha(id,novaSenha,connection);
        atualizarIdade(id,novaIdade,connection);
        atualizarNome(id,novoNome,connection);
        atualizarId(id,novoId,connection);
        System.out.println(buscarUm(novoId,connection));
    }

}
