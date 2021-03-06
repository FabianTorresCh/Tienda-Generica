package package_conexion;

// IMPORTACION DE ELEMENTOS
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

// CUERPO DE PROCESOS DE CONEXION
public class conexion_database {
    private Connection con;
    Statement consulta;
    
    private static final String URL = "jdbc:mysql://localhost:3306/bd_tienda?useSSL=false&serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8"; {
        System.out.println("\n\n>> >> >> conexion_database / private static final String URL = () {} / INICIO PROCESO DE CONEXION");
    }
    private static final String USER = "root"; {
        System.out.println(">> >> >> conexion_database / private static final String USER = \"root\"; {} / INSERCION DE USUARIO");
    }
    private static final String PASS = "root"; {
        System.out.println(">> >> >> conexion_database / private static final String PASS = \"root\"; {} / INSERCION DE CONTRASEÑA");
    }
    
    public Connection Conexion(){
        System.out.println("** ** ** ** ** ** ** ** ** ** ** **\n>> >> >> conexion_database / public Connection Conexion(){} / ENTRANDO A CONEXION");
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(URL, USER, PASS);
            consulta = con.createStatement();
            if(con != null){
                System.out.println(">> >> >> conexion_database / public Connection Conexion(){} / CONECTADO A BASE DE DATOS\n** ** ** ** ** ** ** ** ** ** ** **");
            }
        }
        catch(ClassNotFoundException | SQLException e){
            System.out.println(">> >> >> conexion_database / public Connection Conexion(){} / ERROR DE CONEXION !!!\n** ** ** ** ** ** ** ** ** ** ** **");
            System.out.println("Error"+e.getMessage());
        }
        return con;
    }
}
