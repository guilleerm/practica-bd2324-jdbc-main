import java.sql.*;
import java.util.Random;


public class Main {

    // @TODO Sistituye xxx por los parámetros de tu conexión

    private static final String DB_SERVER = "localhost";

    private static final int DB_PORT = 3306;

    private static final String DB_NAME = "bd2324";

    private static final String DB_USER = "root";

    private static final String DB_PASS = "";

    public static void main(String[] args) throws Exception {

        Class.forName("com.mysql.cj.jdbc.Driver");

        // PUNTO 1

        /*nuevoAutor("Guillermo Rojo");
        nuevoAutor("Diego Blanco");
        nuevoAutor("Marianela Estévez");
        nuevoAutor("Alejandro Lalana");*/

        //PUNTO 2

        //listaArticulosPorAutor("Ortega F.", 2021);

        //PUNTO 3

        listaAfiliaciones();


        // @TODO Prueba sus funciones
        // 1. Añadete como autor a la base de datos
        // 2. Muestra por pantalla la lista de artículos del autor “Ortega F.” en 2021
        // 3. Muestra por pantalla una lista de las afiliaciones y el número de autores que
        //    tiene cada una

    }

    private static void nuevoAutor(String authorName) throws SQLException {
        // @TODO Crea un metodo que añada un nuevo autor a la base de datos con importancia 0.
        // Genera el id de forma aleatoria

        String url = "jdbc:mysql://" + DB_SERVER + ":" + DB_PORT + "/" + DB_NAME;
        try {
            Connection conn = DriverManager.getConnection(url, DB_USER, DB_PASS);

            String sql = "INSERT INTO author(author_id, author_name, importance) VALUES(?, ?, 0)";
            PreparedStatement stmt = conn.prepareStatement(sql);

            Random rand = new Random();
            int randomNum = rand.nextInt(1000000000); //para poner un id con 10 digitos

            stmt.setLong(1, randomNum);
            stmt.setString(2, authorName);
            stmt.executeUpdate();
            stmt.close();
            conn.close();

            System.out.println("Autor: " + authorName + " añadido");

        } catch (SQLException e) {
            System.out.println("Error");
        }
    }

    private static void listaArticulosPorAutor(String authorName, int year) throws SQLException {
        // @TODO Muestra por pantalla una lista de articulos (título y fecha de publicación) para un
        //  autor y año

        String url = "jdbc:mysql://" + DB_SERVER + ":" + DB_PORT + "/" + DB_NAME;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Connection conn = DriverManager.getConnection(url, DB_USER, DB_PASS);

            String sql = "SELECT article.title, article.publication_date\n" +
                    "FROM article\n" +
                    "JOIN author_article ON article.DOI = author_article.DOI\n" +
                    "JOIN author ON author_article.author_id = author.author_id\n" +
                    "WHERE author.author_name = 'Ortega F.' AND YEAR(article.publication_date) = 2021;";

            stmt = conn.createStatement();
            if (stmt.execute(sql)) {
                rs = stmt.getResultSet();
                while (rs.next()) {
                    System.out.println(rs.getString("title") + " " + rs.getString("publication_date"));
                }
            }
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error");
        }
    }

    private static void listaAfiliaciones() throws SQLException {
        // @TODO Muestra por pantalla una lista de las instituciones y el numero de autores que
        //  tienen ordenados de más a menos autores

        String url = "jdbc:mysql://" + DB_SERVER + ":" + DB_PORT + "/" + DB_NAME;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Connection conn = DriverManager.getConnection(url, DB_USER, DB_PASS);

            String sql = "SELECT affiliation.affiliation_name, COUNT(author_affiliation.author_id) AS num_autores\n" +
                    "FROM author_affiliation\n" +
                    "JOIN affiliation ON author_affiliation.affiliation_id = affiliation.affiliation_id\n" +
                    "GROUP BY affiliation.affiliation_name\n" +
                    "ORDER BY num_autores DESC;";

            stmt = conn.createStatement();
            if (stmt.execute(sql)) {
                rs = stmt.getResultSet();
                while (rs.next()) {
                    System.out.println(rs.getString("affiliation_name") + " " + rs.getString("num_autores"));
                }
            }
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error");
        }
    }
}


