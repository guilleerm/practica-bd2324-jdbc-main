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

        // apartado 1
/*        nuevoAutor("Guillermo Rojo");
        nuevoAutor("Diego Blanco");
        nuevoAutor("Marianela Estévez");
        nuevoAutor("Alejandro Lalana");*/

        //APARTADO 2
        //listaArticulosPorAutor("Ortega F.", 2021);










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
        try{
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
        }catch (SQLException e){
            throw e;
        }
    }

    private static void listaArticulosPorAutor(String authorName, int year) throws SQLException {
        // @TODO Muestra por pantalla una lista de articulos (título y fecha de publicación) para un
        //  autor y año

        String url = "jdbc:mysql://" + DB_SERVER + ":" + DB_PORT + "/" + DB_NAME;
        Statement stmt = null;
        ResultSet rs = null;

        try{
            Connection conn = DriverManager.getConnection(url, DB_USER, DB_PASS);

            //String sql = "SELECT article.title, article.publication_date FROM article INNER JOIN author_article ON article.DOI = author_article.DOI INNER JOIN author ON author_article.author_id = author.author_id WHERE author.author_name = '" + authorName + "' AND YEAR(article.publication_date) = " + year + ";";
            String sql = "";

            stmt = conn.createStatement();
            if(stmt.execute(sql)){
                rs = stmt.getResultSet();
                while(rs.next()){
                    System.out.println(rs.getString("title") + " " + rs.getString("publication_date"));
                }
            }
            stmt.close();
            conn.close();
        }catch (SQLException e){
            throw e;
        }


    }

    private static void listaAfiliaciones() throws SQLException {
        // @TODO Muestra por pantalla una lista de las instituciones y el numero de autores que
        //  tienen ordenados de más a menos autores

    }
}


