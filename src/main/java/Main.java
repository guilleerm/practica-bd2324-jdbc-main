import java.sql.*;

public class Main {

    // @TODO Sistituye xxx por los parámetros de tu conexión

    private static final String DB_SERVER = "localhost";

    private static final int DB_PORT = 3306;

    private static final String DB_NAME =  "bd2324";

    private static final String DB_USER = "root";

    private static final String DB_PASS = "";

    public static void main(String[] args) throws Exception {

        Class.forName("com.mysql.cj.jdbc.Driver");

        String url = "jdbc:mysql://" + DB_SERVER + ":" + DB_PORT + "/" + DB_NAME;
        Connection conn = DriverManager.getConnection(url, DB_USER, DB_PASS);

        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM author WHERE importance = 100");
        //stmt.setString(1, "100");

        ResultSet resultSet = stmt.executeQuery();

        

        while (resultSet.next()) {
            String authorId = resultSet.getString("author_id");
            String name = resultSet.getString("author_name");
            String importance = resultSet.getString("importance");
            System.out.println(authorId + " " + name + " " + importance);
        }

        resultSet.close();
        stmt.close();



        // @TODO Prueba sus funciones
        // 1. Añadete como autor a la base de datos
        // 2. Muestra por pantalla la lista de artículos del autor “Ortega F.” en 2021
        // 3. Muestra por pantalla una lista de las afiliaciones y el número de autores que
        //    tiene cada una

        conn.close();
    }

    private static void nuevoAutor (String authorName) throws SQLException {
        // @TODO Crea un metodo que añada un nuevo autor a la base de datos con importancia 0.
        // Genera el id de forma aleatoria
    }

    private static void listaArticulosPorAutor (String authorName, int year) throws SQLException {
        // @TODO Muestra por pantalla una lista de articulos (título y fecha de publicación) para un
        //  autor y año

    }

    private static void listaAfiliaciones() throws SQLException {
        // @TODO Muestra por pantalla una lista de las instituciones y el numero de autores que
        //  tienen ordenados de más a menos autores

    }
}


