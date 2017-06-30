
package programa;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;


public class DAO {
    
    ArrayList<String> lista =new ArrayList();      
    private Connection con = null;
    private ResultSet rs = null;
    private Statement sen = null;  
    static Connection connection = null;
    static Statement statement = null;    
    private String parametros;
    
    public void conectar(){
        try{
            String parametros = "com.mysql.jdbc.Driver";
            Class.forName(parametros);
            String url = "jdbc:mysql://localhost:3306/licencias";
            con = DriverManager.getConnection(url, "root", "");
        }catch(SQLException e){
            javax.swing.JOptionPane.showMessageDialog(null,"Error al conectarse al Motor de Datos MySQL:"+e.getMessage(),"Error",0);  
        }catch(Exception e){
            javax.swing.JOptionPane.showMessageDialog(null,"Error al conectarse al Motor de Datos MySQL:"+e.getMessage(),"Error",0);  
        }
    } 
    
    private static void createDatabase() throws SQLException {
        String sql_bd = "CREATE DATABASE IF NOT EXISTS `licencias`;";
        statement  = connection.createStatement();
        statement.executeUpdate(sql_bd);
        statement.execute("use licencias");
        //--//
        String sql_tbl1 = "CREATE TABLE IF NOT EXISTS `especialidades` ("+
        "`esp_id` char(1) NOT NULL,"+
        "`especialidad` VARCHAR(20) NOT NULL,"+        
        "constraint PK_Usuario PRIMARY KEY (`esp_id`));";         
        statement.executeUpdate(sql_tbl1);        
        //--//   
        String sql_tbl2 = "CREATE TABLE IF NOT EXISTS `perfiles` ("+
        "`per_id` char(1) NOT NULL,"+
        "`perfil` VARCHAR(20) NOT NULL,"+        
        "constraint PK_Usuario PRIMARY KEY (`per_id`));";         
        statement.executeUpdate(sql_tbl2);        
        //--//            
        String sql_tbl3 = "CREATE TABLE IF NOT EXISTS `usuarios` ("+
        "`usu_id` int(5) UNSIGNED NOT NULL AUTO_INCREMENT,"+
        "`usu_nom` VARCHAR(20) NOT NULL,"+
        "`usu_ape` VARCHAR(20) NOT NULL,"+        
        "`usuario` VARCHAR(20) NOT NULL,"+
        "`password` VARCHAR(20) DEFAULT NULL,"+
        "`perfil` char(1) NOT NULL DEFAULT '1',"+
        "`especialidad` char(1) NOT NULL,"+        
        "constraint PK_Usuario PRIMARY KEY (`usu_id`),"+
        "constraint FK_Perfiles FOREIGN KEY(`perfil`) REFERENCES perfiles(`per_id`),"+
        "constraint FK_Especialidad FOREIGN KEY(`especialidad`) REFERENCES especialidades(`esp_id`));";
        statement.executeUpdate(sql_tbl3);
    }    
    
    private void consultarDBA(){
        try{
            conectar();
            sen = con.createStatement();
            rs = sen.executeQuery("select especialidad from especialidades");
            if(!rs.next()){
                parametros = "insert into especialidades values (1,'Pediatría');";
                sen.execute(parametros);
                parametros = "insert into especialidades values (2,'Nutriología');";
                sen.execute(parametros); 
                parametros = "insert into especialidades values (3,'Dermatología');";
                sen.execute(parametros);
                parametros = "insert into especialidades values (4,'Cardiología');";
                sen.execute(parametros); 
                parametros = "insert into especialidades values (5,'DBA');";
                sen.execute(parametros);                 
            }  
            rs = sen.executeQuery("select perfil from perfiles");
            if(!rs.next()){
                parametros = "insert into perfiles values (1,'Administrador');";
                sen.execute(parametros);
                parametros = "insert into perfiles values (2,'Doctor');";
                sen.execute(parametros); 
                parametros = "insert into perfiles values (3,'Observador');";
                sen.execute(parametros);                 
            } 
            rs = sen.executeQuery("select usuario from usuarios");
            if(!rs.next()){
                parametros = "insert into usuarios values (1,'Matias','Rivas','MRivas','123456',1,5);";
                sen.execute(parametros);
                parametros = "insert into usuarios values (2,'Raul','Fuentes','RFuentes','123456',1,5);";
                sen.execute(parametros);                
            }            
            sen.close();
            desconectar();
        }catch(Exception e){
            javax.swing.JOptionPane.showMessageDialog(null,"Error:"+e.getMessage(),"Error",0);        
        }
    }
    
    
    public void desconectar(){
        try{
            con.close();
        }catch(SQLException e){
            javax.swing.JOptionPane.showMessageDialog(null,"Error al desconectarse al Motor de Datos MySQL:"+e.getMessage(),"Error",0);         
            System.exit(0);
        }catch(Exception e){
            javax.swing.JOptionPane.showMessageDialog(null,"Error al desconectarse al Motor :"+e.getMessage(),"Error",0);         
            System.exit(0);
        }
    }    
    
    
    public String ingresar(String usuario,String password){
        String perfil = "ERROR";        
        try {
            conectar();
            sen = con.createStatement();
            rs = sen.executeQuery("SELECT * FROM usuarios WHERE usuario='"+usuario+"' && password='"+password+"'");
            while(rs.next()){
                perfil=rs.getString("perfil");
            }
            sen.close();
            desconectar();
        }catch (SQLException ex) {
            System.out.println("The following exception has occured: " + ex.getMessage());
        } catch (Exception ex2) {
            System.out.println("The following exception has occured: " + ex2.getMessage());
        }
        return perfil;
    }
    
    public ArrayList<String> usuarios(){
        try {
            DriverManager.setLoginTimeout(23);
            connection = DriverManager.getConnection("jdbc:mysql://localhost/", "root", "");            
            createDatabase();
            consultarDBA();
            conectar();
            sen = con.createStatement();
            rs = sen.executeQuery("select * from usuarios");
            while(rs.next()){
                String lala = rs.getString(4);
                lista.add(lala);
            }
            sen.close();
            desconectar();
        }catch (SQLException ex) {
                System.out.println("The following exception has occured: " + ex.getMessage());
        } catch (Exception ex2) {
            System.out.println("The following exception has occured: " + ex2.getMessage());
        } 
        return lista;
    }    
    
    public void registrarLicencia(Licencias l){
        String rut,enf,isa,fecha,est;
        int dias;
        try{
            rut = l.getRut();
            enf = l.getEnfermedad();
            dias = l.getDias();
            isa = l.getIsapre();
            fecha = l.getFecha();
            est = l.getEstado();
            sen = con.createStatement();
            parametros = "insert into Licencia set rut='"+rut+"', enfermedad='"+enf+"', dias="+dias+", isapre='"+isa+"', fecha='"+fecha+"', estado='"+est+"'";
            sen.execute(parametros);
            sen.close();
        }catch(SQLException e){
            javax.swing.JOptionPane.showMessageDialog(null,"Error al registrar al Motor de Datos MySQL:"+e.getMessage(),"Error",0); 
        }catch(Exception e){
            javax.swing.JOptionPane.showMessageDialog(null,"Error al registrar al Motor :"+e.getMessage(),"Error",0); 
        }
    }
    
    public boolean verificarTabla(){
        String tabla;
        try{ 
            sen = con.createStatement();
            rs = sen.executeQuery("show tables");
            while(rs.next()){
                tabla = rs.getString("Tables_in_licencias");
                if (tabla.equalsIgnoreCase("licencia")) {
                    sen.close();
                    return true;  
                }
            }
        }catch(SQLException e){
            javax.swing.JOptionPane.showMessageDialog(null,"Error al registrar al Motor de Datos MySQL:"+e.getMessage(),"Error",0);
        }catch(Exception e){
            javax.swing.JOptionPane.showMessageDialog(null,"Error al registrar al Motor :"+e.getMessage(),"Error",0); 
        }
        return false;
    }
    
    public void createTableLicencias(){
        try{
            statement  = connection.createStatement();
            statement.execute("use licencias");
            String sql_tbl = "CREATE TABLE IF NOT EXISTS `licencia` ("+
            "`id` int(5) UNSIGNED NOT NULL AUTO_INCREMENT,"+
            "`rut` CHAR(10) NOT NULL,"+
            "`enfermedad` VARCHAR(25) NOT NULL,"+
            "`dias` VARCHAR(20) DEFAULT NULL,"+
            "`isapre` VARCHAR(20) NOT NULL DEFAULT '1',"+
            "`fecha` char(10) NOT NULL DEFAULT '1',"+  
            "`estado` VARCHAR(15) NOT NULL DEFAULT '1',"+ 
            "constraint PK_Licencias PRIMARY KEY (`id`));";         
            statement.executeUpdate(sql_tbl);
            statement.close(); 
        }catch(SQLException e){
            javax.swing.JOptionPane.showMessageDialog(null,"Error al registrar al Motor de Datos MySQL:"+e.getMessage(),"Error",0); 
        }catch(Exception e){
            javax.swing.JOptionPane.showMessageDialog(null,"Error al registrar al Motor :"+e.getMessage(),"Error",0); 
        }
    }
    
    
    
    public int mostrarCantidadReg(){
        String cant;
        int contador = 0;
        try{
            conectar();  
            String sql = "select * from licencia";
            sen = con.createStatement();
            rs = sen.executeQuery(sql);
            while(rs.next()){
                contador++;
            }
            sen.close();
        }catch(SQLException e){           
        }catch(Exception e){            
        }
        return contador;
}
    
    public ArrayList<Licencias> listarLicencias(){
        String rut,enf,isa,fech,est;
        int dias;
        ArrayList<Licencias> lista1 = new ArrayList();
        try{
            parametros = "select * from licencia";
            sen = con.createStatement();
            rs = sen.executeQuery(parametros);
            while(rs.next()){
                rut = rs.getString("rut");
                enf = rs.getString("enfermedad");
                dias = rs.getInt("dias");
                isa = rs.getString("isapre");
                fech = rs.getString("fecha");
                est = rs.getString("estado");
                Licencias l = new Licencias(rut,enf,dias,isa,fech,est);
                lista1.add(l);
            }
            sen.close();
            return lista1;
        }catch(SQLException e){
            System.out.println(e);
        }catch(Exception e){
            System.out.println(e);
        }
        return null;
    }
    
    public ArrayList<String> listarBD(){
        String bd;
        ArrayList<String> bases = new ArrayList();
        try{
            parametros = "show databases";
            sen = con.createStatement();
            rs = sen.executeQuery(parametros);
            while(rs.next()){
                bd = rs.getString("Database");
                bases.add(bd);
            }
            sen.close();
            return bases;
        }catch(SQLException e){

        }catch(Exception e){

        }
        return null;
    }
    
    public ArrayList<String> listarTablas(){
        String tb;
        ArrayList<String> tablas = new ArrayList();
        try{
            parametros = "show tables";
            sen = con.createStatement();
            rs = sen.executeQuery(parametros);
            while(rs.next()){
                tb = rs.getString("Tables_in_licencias");
                tablas.add(tb);
            }
            sen.close();
            return tablas;
        }catch(SQLException e){
            System.out.println(e);
        }catch(Exception e){
            System.out.println(e);
        }
        return null;
    }
    
    public Usuario cargarUsuario(String u){
        try{
            sen = con.createStatement();
            rs = sen.executeQuery("SELECT * FROM usuarios where usuario='"+u+"'");
            if(rs.next()){
                String usuario = rs.getString("usuario");
                String nombre = rs.getString("usu_nom");
                String apellido = rs.getString("usu_ape");
                String password = rs.getString("password");
                String perfil = rs.getString("perfil");
                String especialidad = rs.getString("especialidad");
                Usuario p = new Usuario(usuario,nombre,apellido,password,perfil,especialidad);
                sen.close();
                return p;
            }
        }catch(Exception e){
            return null;
        }
        return null;
    }
    
    public boolean consultarUsuario(String u){
        try{
            sen = con.createStatement();
            rs = sen.executeQuery("SELECT * FROM usuarios where usuario='"+u+"'");
            if (rs.next()) {
                sen.close();
                return true;
            }else{
                return false;
            }
        }catch(Exception e){
            return false;
        }
    }
    
    public void eliminarUsuario(String usuario){ 
        try{
            sen = con.createStatement();
            String sql = "delete from usuarios where usuario='"+usuario+"'";
            sen.execute(sql);
            sen.close();
        }catch(SQLException e){
            javax.swing.JOptionPane.showMessageDialog(null,"Error al eliminar al Motor de Datos MySQL:"+e.getMessage(),"Error",0);         
        }catch(Exception e){
            javax.swing.JOptionPane.showMessageDialog(null,"Error al eliminar al Motor :"+e.getMessage(),"Error",0);         
        }
    } 
    
    public void registrarUsuario(Usuario u){
        try{
            String usuario = u.getUsuario();
            String nombre = u.getNombre();
            String apellido = u.getApellido();
            String password = u.getPassword();
            int perfil = Integer.parseInt(u.getPerfil());
            int especialidad = Integer.parseInt(u.getEspecialidad());
            parametros = "insert into usuarios set usu_nom='"+nombre+"',usu_ape='"+apellido+"',usuario='"+usuario+"',password='"+password+"',perfil='"+perfil+"',especialidad='"+especialidad+"'";
            sen = con.createStatement();
            sen.execute(parametros);
            sen.close();
        }catch(SQLException e){
            javax.swing.JOptionPane.showMessageDialog(null,"Error al registrar al Motor de Datos MySQL:"+e.getMessage(),"Error",0);         
        }catch(Exception e){
            javax.swing.JOptionPane.showMessageDialog(null,"Error al registrar al Motor :"+e.getMessage(),"Error",0);         
        }
    }
    
    public void modificarUsuario(Usuario u){
        try{
            String usuario = u.getUsuario();
            String nombre = u.getNombre();
            String apellido = u.getApellido();
            String password = u.getPassword();
            int perfil = Integer.parseInt(u.getPerfil());
            int especialidad = Integer.parseInt(u.getEspecialidad());
            parametros = "update usuarios set usu_nom='"+nombre+"',usu_ape='"+apellido+"',password='"+password+"',perfil='"+perfil+"',especialidad='"+especialidad+"' where usuario='"+usuario+"'";
            sen = con.createStatement();
            sen.execute(parametros);
            sen.close();
        }catch(SQLException e){
            javax.swing.JOptionPane.showMessageDialog(null,"Error al registrar al Motor de Datos MySQL:"+e.getMessage(),"Error",0);         
        }catch(Exception e){
            javax.swing.JOptionPane.showMessageDialog(null,"Error al registrar al Motor :"+e.getMessage(),"Error",0);         
        }
    }
    
    public void listarUsuariosEspecialiad(javax.swing.JTable grilla){   
        String titulos[]= new String[4];
        titulos[0] ="Usuario";
        titulos[1] ="Nombre";
        titulos[2] ="Apellido";
        titulos[3] ="Especialidad";
        try{
            
            DefaultTableModel modelo = (DefaultTableModel) grilla.getModel();
            int filas = modelo.getRowCount();
            modelo.setColumnCount(0);
            for (int i = 0; i < filas; i++) {
                modelo.removeRow(0);
            }
            for (int i = 0; i < 4; i++) {
                modelo.addColumn(titulos[i]);
            }
        
            Object[] datos = new Object[5];
            String sql = "select usuarios.usu_nom,usuarios.usu_ape,usuarios.usuario,especialidades.especialidad from usuarios,especialidades where usuarios.especialidad=especialidades.esp_id";
            sen = con.createStatement();
            rs = sen.executeQuery(sql);
            while(rs.next()){
                datos[0] = rs.getString("usuarios.usuario");
                datos[1] = rs.getString("usuarios.usu_nom");
                datos[2] = rs.getString("usuarios.usu_ape");
                datos[3] = rs.getString("especialidades.especialidad");
                modelo.addRow(datos);
            }
            grilla.setModel(modelo);
            sen.close();
        }catch(SQLException e){
            javax.swing.JOptionPane.showMessageDialog(null,"Error al listar al Motor de Datos MySQL:"+e.getMessage(),"Error",0);         
        }catch(Exception e){
            javax.swing.JOptionPane.showMessageDialog(null,"Error al listar al Motor :"+e.getMessage(),"Error",0);         
        }
    }
    
    public void listarLicencias(javax.swing.JTable grilla){
        String titulos[]= new String[6];
        titulos[0] ="RUT";
        titulos[1] ="Enfermedad";
        titulos[2] ="Días";
        titulos[3] ="Isapre";
        titulos[4] ="Fecha";
        titulos[5] ="Estado";
        try{
            DefaultTableModel modelo = (DefaultTableModel) grilla.getModel();
            int filas = modelo.getRowCount();
            modelo.setColumnCount(0);
            for (int i = 0; i < filas; i++) {
                modelo.removeRow(0);
            }
            for (int i = 0; i < 5; i++) {
                modelo.addColumn(titulos[i]);
            }
            Object[] datos = new Object[6];
            String sql = "select * from licencia";
            sen = con.createStatement();
            rs = sen.executeQuery(sql);
            while(rs.next()){
                datos[0] = rs.getString("rut");
                datos[1] = rs.getString("enfermedad");
                datos[2] = rs.getString("dias");
                datos[3] = rs.getString("isapre");
                datos[4] = rs.getString("fecha");
                datos[5] = rs.getString("estado");
                modelo.addRow(datos);
            }
            grilla.setModel(modelo);
            sen.close();
        }catch(SQLException e){
            javax.swing.JOptionPane.showMessageDialog(null,"Error al listar al Motor de Datos MySQL:"+e.getMessage(),"Error",0);         
        }catch(Exception e){
            javax.swing.JOptionPane.showMessageDialog(null,"Error al listar al Motor :"+e.getMessage(),"Error",0);         
        }
    }
    public ArrayList<Usuario> listarUsuarios(){
        String usu,nom,ape,perf,esp;
        try{
            ArrayList<Usuario> lista = new ArrayList();
            String sql = "select usuarios.usu_nom,usuarios.usu_ape,usuarios.usuario,perfiles.perfil,especialidades.especialidad from usuarios,perfiles,especialidades where usuarios.perfil=perfiles.per_id and usuarios.especialidad=especialidades.esp_id";
            sen = con.createStatement();
            rs = sen.executeQuery(sql);
            while(rs.next()){
                usu = rs.getString("usuarios.usuario");
                nom = rs.getString("usuarios.usu_nom");
                ape = rs.getString("usuarios.usu_ape");
                perf = rs.getString("perfiles.perfil");
                esp = rs.getString("especialidades.especialidad");
                Usuario u = new Usuario(usu,nom,ape,perf,esp);
                lista.add(u);
            }
            sen.close();
            return lista;
        }catch(SQLException e){
            System.out.println(e);
        }catch(Exception e){
            System.out.println(e);
        }
        return null;
    }
    
    public void listarUsuariosEspecialidad(javax.swing.JTable grilla){   
        String titulos[]= new String[4];
        titulos[0] ="Usuario";
        titulos[1] ="Nombre";
        titulos[2] ="Apellido";
        titulos[3] ="Especialidad";
        try{
            
            DefaultTableModel modelo = (DefaultTableModel) grilla.getModel();
            int filas = modelo.getRowCount();
            modelo.setColumnCount(0);
            for (int i = 0; i < filas; i++) {
                modelo.removeRow(0);
            }
            for (int i = 0; i < 4; i++) {
                modelo.addColumn(titulos[i]);
            }
        
            Object[] datos = new Object[5];
            String sql = "select usuarios.usu_nom,usuarios.usu_ape,usuarios.usuario,perfiles.perfil from usuarios,perfiles where usuarios.especialidad=especialidades.esp_id";
            sen = con.createStatement();
            rs = sen.executeQuery(sql);
            while(rs.next()){
                datos[0] = rs.getString("usuarios.usuario");
                datos[1] = rs.getString("usuarios.usu_nom");
                datos[2] = rs.getString("usuarios.usu_ape");
                datos[3] = rs.getString("especialidades.especialidades");
                modelo.addRow(datos);
            }
            grilla.setModel(modelo);
            sen.close();
        }catch(SQLException e){
            javax.swing.JOptionPane.showMessageDialog(null,"Error al listar al Motor de Datos MySQL:"+e.getMessage(),"Error",0);         
        }catch(Exception e){
            javax.swing.JOptionPane.showMessageDialog(null,"Error al listar al Motor :"+e.getMessage(),"Error",0);         
        }
    } 
    
    public void listarUsuariosPerfil(javax.swing.JTable grilla){   
        String titulos[]= new String[4];
        titulos[0] ="Usuario";
        titulos[1] ="Nombre";
        titulos[2] ="Apellido";
        titulos[3] ="Perfil";
        try{
            
            DefaultTableModel modelo = (DefaultTableModel) grilla.getModel();
            int filas = modelo.getRowCount();
            modelo.setColumnCount(0);
            for (int i = 0; i < filas; i++) {
                modelo.removeRow(0);
            }
            for (int i = 0; i < 4; i++) {
                modelo.addColumn(titulos[i]);
            }
        
            Object[] datos = new Object[5];
            String sql = "select usuarios.usu_nom,usuarios.usu_ape,usuarios.usuario,perfiles.perfil from usuarios,perfiles where usuarios.perfil=perfiles.per_id";
            sen = con.createStatement();
            rs = sen.executeQuery(sql);
            while(rs.next()){
                datos[0] = rs.getString("usuarios.usuario");
                datos[1] = rs.getString("usuarios.usu_nom");
                datos[2] = rs.getString("usuarios.usu_ape");
                datos[3] = rs.getString("perfiles.perfil");
                modelo.addRow(datos);
            }
            grilla.setModel(modelo);
            sen.close();
        }catch(SQLException e){
            javax.swing.JOptionPane.showMessageDialog(null,"Error al listar al Motor de Datos MySQL:"+e.getMessage(),"Error",0);         
        }catch(Exception e){
            javax.swing.JOptionPane.showMessageDialog(null,"Error al listar al Motor :"+e.getMessage(),"Error",0);         
        }
    }
    
    public ArrayList<Licencias> busquedaEmergente(String r){
        String rut,enf,isa,fech,est;
        int dias;
        ArrayList<Licencias> lista1 = new ArrayList();
        try{
            parametros = "select * from licencia where rut like '"+r+"%'";
            sen = con.createStatement();
            rs = sen.executeQuery(parametros);
            while(rs.next()){
                rut = rs.getString("rut");
                enf = rs.getString("enfermedad");
                dias = rs.getInt("dias");
                isa = rs.getString("isapre");
                fech = rs.getString("fecha");
                est = rs.getString("estado");
                Licencias l = new Licencias(rut,enf,dias,isa,fech,est);
                lista1.add(l);
            }
            sen.close();
            return lista1;
        }catch(SQLException e){
            System.out.println(e);
        }catch(Exception e){
            System.out.println(e);
        }
        return null;
    }  
    
}
