import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdDao {

    //Andreza do Nascimento Pessoa  Matricula: Cb3012191
    //Lidiane Souza Soares          Matricula:CB3012026

    /**
     * Connection database MySql
     * */
    public static Connection getConnection() {
        Connection connection = null;
        try {
            String dbDriver = "com.mysql.cj.jdbc.Driver";
            String dbURL = "jdbc:mysql://localhost:3306/";
            // Database name to access
            String dbName ="userProd?useTimezone=true&serverTimezone=America/Sao_Paulo";
            String dbUsername = "root";
            String dbPassword = "mysqlDev123@";

            Class.forName(dbDriver);
            connection = DriverManager.getConnection(dbURL + dbName, dbUsername,dbPassword);
        } catch (Exception e) {
            System.out.println(e);
        }
        return connection;
    }

    public static int save(Produto e) {
        int status = 0;
        try {
            Connection connection = ProdDao.getConnection();
            PreparedStatement prod = connection.prepareStatement("insert into userProd(nome,unidadeCompra,descricao,qtdPrevistoMes,precoMaxComprado) values (?,?,?,?,?)");
            prod.setString(1, e.getNome());
            prod.setInt(2,e.getUnidadeCompra());
            prod.setString(3,e.getDescricao());
            prod.setDouble(4,e.getQtdPrevistoMes());
            prod.setDouble(5,e.getPrecoMaxComprado());

            status = prod.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return status;
    }

    public static int  update(Produto e) throws SQLException {
        int status = 0;
        try {
            Connection connection = ProdDao.getConnection();
            PreparedStatement prod = connection.prepareStatement("update userProd set nome = ?, unidadeCompra = ?, descricao = ?,qtdPrevistoMes = ?, precoMaxComprado = ? where id = ?;");
            prod.setString(1, e.getNome());
            prod.setInt(2,e.getUnidadeCompra());
            prod.setString(3,e.getDescricao());
            prod.setDouble(4,e.getQtdPrevistoMes());
            prod.setDouble(5,e.getPrecoMaxComprado());
            prod.setInt(6, e.getId());

            status = prod.executeUpdate();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return  status;
    }

    public static int delete(int id) {
        int status = 0;
        try {
            Connection connection = ProdDao.getConnection();
            PreparedStatement prod = connection.prepareStatement("delete from userProd where id=?");
            prod.setInt(1, id);
            status = prod.executeUpdate();

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    public static Produto getProdtoById(int id) {
        Produto produto = new Produto();

        try {
            Connection connection = ProdDao.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from userProd where id=?");
            preparedStatement.setInt(1, id);
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                produto.setId(result.getInt(1));
                produto.setNome(result.getString(2));
                produto.setUnidadeCompra(result.getInt(3));
                produto.setDescricao(result.getString(4));
                produto.setQtdPrevistoMes(result.getDouble(5));
                produto.setPrecoMaxComprado(result.getDouble(6));
            }
            connection.close();
        } catch (SQLException e) {
            printSQLException(e);
        }
        return produto;
    }

    public static List<Produto> getAllProduto() {
        List<Produto> list = new ArrayList<Produto>();

        try {
            Connection connection = ProdDao.getConnection();
            PreparedStatement prod = connection.prepareStatement("select * from userProd");
            ResultSet result = prod.executeQuery();
            while (result.next()) {
                Produto e = new Produto();
                e.setId(result.getInt(1));
                e.setNome(result.getString(2));
                e.setUnidadeCompra(result.getInt(3));
                e.setDescricao(result.getString(4));
                e.setQtdPrevistoMes(result.getDouble(5));
                e.setPrecoMaxComprado(result.getDouble(6));
                list.add(e);
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
    private static void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }

}
