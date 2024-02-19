package login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class loginm {

    static final String URL = "jdbc:mysql://148.225.60.126/bd2?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    public static void main(String[] args) {
        Scanner sc  = new Scanner(System.in);
        System.out.print("Usuario: ");
        String usuario=sc.next();
        System.out.print("Contraseña: ");
        String contra=sc.next();
        
        try{
            
            ingresoBaseDatos(usuario, contra);
        } catch (Exception e) {
            System.out.println("SQL Exception:" + e.getMessage());
        }
    }
public static void ingresoBaseDatos(String program_username, String program_password) {
        
        //usuario y contraseña
        String     user    = "bd2";
        String     password= "MementoVivere";
        Connection conexion = null;
        Statement instruccion = null;
        ResultSet conjuntoResultados = null;
        String     SQL     = "SELECT * FROM ALUMNOS";
    
        //String SQL_auth = "SELECT username FROM FCG_users WHERE username = '" + program_username + "'" + " AND password = SHA2('" + program_password + "', 256)";
        String SQL_auth = "SELECT username FROM ASAC_USERS WHERE username = '" + program_username + "'" + " AND password = SHA2('" + program_password + "', 256)";
        System.out.println(SQL_auth);
        try {
            conexion = DriverManager.getConnection(URL, user, password);
            instruccion = conexion.createStatement();
            conjuntoResultados = instruccion.executeQuery(SQL_auth);
            if (conjuntoResultados.next() == true) {
                System.out.println("Bienvenido al sistema de login " + program_username);
                menu_opciones(program_username);
                menu(program_username);
            }
            else {
                System.out.println("Usuario o contraseña incorrectos");
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception:" + e.getMessage());
            System.out.println("SQL State:" + e.getSQLState());
            System.out.println("Vendor Error:" + e.getErrorCode());
        }
    }
       
public static void menu_opciones(String program_username){
    String URL = "jdbc:mysql://148.225.60.126:3306/bd2";
        Connection conexion = null;
        Statement  instruccion = null;
        ResultSet  conjuntoResultados = null;
        String     SQL     = "SELECT OPCIONES_MENU.* FROM OPCIONES_MENU, ASAC_USERS WHERE ASAC_USERS.username ='" + program_username + "'" + " AND ASAC_USERS.usertype = OPCIONES_MENU.TIPO_USER";
        String     user    = "bd2";
        String     password= "MementoVivere";  
    try {      
            conexion = DriverManager.getConnection(URL,user,password);
            instruccion = conexion.createStatement();
            
            conjuntoResultados = instruccion.executeQuery(SQL);
            System.out.println("Menú:");
 
            ResultSetMetaData metaDatos = conjuntoResultados.getMetaData();
            int numeroColumnas = metaDatos.getColumnCount();
            for (int i = 1; i <= numeroColumnas; i++) {
                System.out.print("  " + metaDatos.getColumnName(i));  
            }
            System.out.println("");
            
            while (conjuntoResultados.next()) {
                for (int i = 1; i <= numeroColumnas; i++) {
                    System.out.print("   " +conjuntoResultados.getObject(i));
                }
                System.out.println("");
            }
            conexion.close();
            
        } catch (SQLException ex) {
            
            System.out.println("SQL Exception:"+ex.getMessage());
            System.out.println("SQL State:" + ex.getSQLState());
            System.out.println("Vendor Error:"+ ex.getErrorCode());
        }
    }

public static void menu(String program_username){
    Scanner sc  = new Scanner(System.in);
    int opcion = 0;
    System.out.println("ELIGE UNA OPCION ");
    opcion = sc.nextInt();
        do{
            if (opcion>=1 || opcion <=5){
                switch(opcion){
                case 1: 
                    System.out.println("Elegiste agregar usuarios.");
                    System.out.print("Ingrese el nombre: ");
                    String username = sc.next();
                    System.out.print("Ingrese la contraseña: ");
                    String password = sc.next();
                    System.out.print("Ingrese el tipo de usuario ADMIN/USER: ");
                    String typauser = sc.next();
                    System.out.print("Ingrese el email: ");
                    String email = sc.next();
                    AgregarAlumnos(username, password, typauser,email);
                    break;
                case 2:
                    System.out.println("Modificar usuario");
                    Modificar();
                    break;
                case 3:
                    System.out.println("Ver lista de alumnos.");
                    lista_alumnos();
                    break;
                case 4:
                    System.out.println("lista de bitacoras.");
                    lista_bitacora();
                    break;
                case 5:
                    System.out.println("Exit");
                    System.exit(0);                 
                    break;
            }
            }if (opcion>=6 || opcion <=7){
                switch(opcion){
                case 6:
                    System.out.println("lista de alumnos");
                    lista_alumnos();
                    break;
                case 7:
                    System.out.println("Exit");
                    System.exit(0);
                    break;
            }
                break;
            }else{
        System.out.println("Opcion invalida");
    } break;
    }while(opcion != 5);
    menu_opciones(program_username);
    menu(program_username);
   
}

public static void lista_alumnos(){
    String URL = "jdbc:mysql://148.225.60.126:3306/bd2";
        Connection conexion = null;
        Statement  instruccion = null;
        ResultSet  conjuntoResultados = null;
        String     SQL     = "SELECT * FROM ALUMNOS";
        String     user    = "bd2";
        String     password= "MementoVivere";  
    try {      
            conexion = DriverManager.getConnection(URL,user,password);
            instruccion = conexion.createStatement();
            
            conjuntoResultados = instruccion.executeQuery(SQL);
            System.out.println("Lista de alumnos");
            ResultSetMetaData metaDatos = conjuntoResultados.getMetaData();
            int numeroColumnas = metaDatos.getColumnCount();
            for (int i = 1; i <= numeroColumnas; i++) {
                System.out.print("  " + metaDatos.getColumnName(i));  
            }
            System.out.println("");
            while (conjuntoResultados.next()) {
                for (int i = 1; i <= numeroColumnas; i++) {
                    System.out.print("   " +conjuntoResultados.getObject(i));
                }
                System.out.println("");
            }
            conexion.close();
            
        } catch (SQLException ex) {
            
            System.out.println("SQL Exception:"+ex.getMessage());
            System.out.println("SQL State:" + ex.getSQLState());
            System.out.println("Vendor Error:"+ ex.getErrorCode());
        }
}

public static void lista_bitacora(){
    String URL = "jdbc:mysql://148.225.60.126:3306/bd2";
        Connection conexion = null;
        Statement  instruccion = null;
        ResultSet  conjuntoResultados = null;
        String     SQL     = "SELECT * FROM ASAC_BITACORA";
        String     user    = "bd2";
        String     password= "MementoVivere";  
    try {      
            conexion = DriverManager.getConnection(URL,user,password);
            instruccion = conexion.createStatement();
            
            conjuntoResultados = instruccion.executeQuery(SQL);
            System.out.println("Bitacora");
            ResultSetMetaData metaDatos = conjuntoResultados.getMetaData();
            int numeroColumnas = metaDatos.getColumnCount();
            for (int i = 1; i <= numeroColumnas; i++) {
                System.out.print("  " + metaDatos.getColumnName(i));  
            }
            System.out.println("");

            while (conjuntoResultados.next()) {
                for (int i = 1; i <= numeroColumnas; i++) {
                    System.out.print("   " +conjuntoResultados.getObject(i));
                }
                System.out.println("");
            }
            conexion.close();
            
        } catch (SQLException ex) {
            
            System.out.println("SQL Exception:"+ex.getMessage());
            System.out.println("SQL State:" + ex.getSQLState());
            System.out.println("Vendor Error:"+ ex.getErrorCode());
        }
}

public static void AgregarAlumnos(String username, String password, String typauser, String email){
    String URL = "jdbc:mysql://148.225.60.126/bd2?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    String user = "bd2";
    String pass = "MementoVivere";
    Connection conexion = null;
    Statement instruccion = null;
   String SQL = "INSERT INTO ASAC_USERS (`username`, `password`, `usertype`, `email`) VALUES ('" + username + "', SHA2('" + password + "', 256), '" + typauser + "', '" + email + "')";
    try {      
            conexion = DriverManager.getConnection(URL,user,pass);
            instruccion = conexion.createStatement();
            instruccion.executeUpdate(SQL);
                System.out.println("El alumno fue agregado");

            conexion.close();

    } catch (SQLException e) {
        System.out.println("SQL Exception:" + e.getMessage());
        System.out.println("SQL State:" + e.getSQLState());
        System.out.println("Vendor Error:" + e.getErrorCode());
    }
}

public static void Modificar(){
    String URL = "jdbc:mysql://148.225.60.126/bd2?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    String user = "bd2";
    String pass = "MementoVivere";
    Connection conexion = null;
    Statement instruccion = null;
    
    Scanner sc  = new Scanner(System.in);
    System.out.println("La lista de usuarios a modificar: ");
    lista_user();
    System.out.println("Introduzca el id a modificar:");
    int id=sc.nextInt();
    System.out.println("Introduzca el campo a modificar: ");
    String campo=sc.next();
    System.out.println("Introduzca el nuevo dato: ");
    String dato=sc.next();
    
    boolean tipocampo=false;
    if (campo=="password"){
        tipocampo=true;
    }else{
        tipocampo=false;
    }
    
    
    if (tipocampo==false){
        String SQL = "UPDATE ASAC_USERS SET " + campo + " = '" + dato + "' WHERE id = '" + id + "'";
        try {
            conexion = DriverManager.getConnection(URL,user,pass);
            instruccion = conexion.createStatement();
            instruccion.executeUpdate(SQL);
            System.out.println("Modificación realizada");
            conexion.close();
            
        } catch (SQLException e) {
            System.out.println("SQL Exception:" + e.getMessage());
            System.out.println("SQL State:" + e.getSQLState());
            System.out.println("Vendor Error:" + e.getErrorCode());
        }
        
    }else{
        String SQL = "UPDATE ASAC_USERS SET " + campo + " = SHA2('" + dato + "', 256) WHERE id = '" + id + "'";
        try {
            conexion = DriverManager.getConnection(URL,user,pass);
            instruccion = conexion.createStatement();
            instruccion.executeUpdate(SQL);
            System.out.println("Modificación realizada");
            conexion.close();
            
        } catch (SQLException e) {
            System.out.println("SQL Exception:" + e.getMessage());
            System.out.println("SQL State:" + e.getSQLState());
            System.out.println("Vendor Error:" + e.getErrorCode());
        }
    }  
}

public static void lista_user(){
    String URL = "jdbc:mysql://148.225.60.126:3306/bd2";
        Connection conexion = null;
        Statement  instruccion = null;
        ResultSet  conjuntoResultados = null;
        String     SQL     = "SELECT * FROM ASAC_USERS";
        String     user    = "bd2";
        String     password= "MementoVivere";  
    try {      
            conexion = DriverManager.getConnection(URL,user,password);
            instruccion = conexion.createStatement();
            conjuntoResultados = instruccion.executeQuery(SQL);
            ResultSetMetaData metaDatos = conjuntoResultados.getMetaData();
            int numeroColumnas = metaDatos.getColumnCount();
            for (int i = 1; i <= numeroColumnas; i++) {
                System.out.print("  " + metaDatos.getColumnName(i));  
            }
            System.out.println("");
            while (conjuntoResultados.next()) {
                for (int i = 1; i <= numeroColumnas; i++) {
                    System.out.print("   " +conjuntoResultados.getObject(i));
                }
                System.out.println("");
            }
            conexion.close();
            
        } catch (SQLException ex) {
            
            System.out.println("SQL Exception:"+ex.getMessage());
            System.out.println("SQL State:" + ex.getSQLState());
            System.out.println("Vendor Error:"+ ex.getErrorCode());
        }
}    
        }