package programa;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Listado extends javax.swing.JFrame {

    String hora,fecha,usuario;
    private Ingreso in;
    private Licencias l;
    boolean conectado=false;
    DAO dao = new DAO();
    private Credencial credencial;

    
    public Listado() {
        initComponents();
        setTitle("Listado y busqueda");
        ImageIcon img = new ImageIcon(getClass().getResource("/imagenes/icono.png"));
        setIconImage(img.getImage());        
        this.setLocationRelativeTo(null);
    }
      
    public void credencial(Credencial c){
        this.credencial = c;
    }        
    
    private void listarLicencias(){
        txtarea.setText("");
        ArrayList<Licencias> lista = new ArrayList();
        lista = dao.listarLicencias();
        if (txtarea.getText().equalsIgnoreCase("")) {
            for (int i = 0; i < lista.size(); i++) {
            Licencias l = lista.get(i);
            String rut = l.getRut();
            String enf = l.getEnfermedad();
            int dias = l.getDias();
            String isa = l.getIsapre();
            String fec = l.getFecha();
            String est = l.getEstado();
            String dia = Integer.toString(dias);
            txtarea.append(rut+" "+enf+" "+dia+" "+isa+" "+fec+" "+est+"\n");
            }
        }      
    }
   
    private void busquedaEmergente(String s){
        txtarea.setText("");
        ArrayList<Licencias> lista = new ArrayList();
        lista = dao.busquedaEmergente(s);
        DefaultTableModel modelo = (DefaultTableModel) grilla.getModel();
        int filas = modelo.getRowCount();
        for (int i = 0; i < filas; i++) {
            modelo.removeRow(0);
        }        
        if (txtarea.getText().equalsIgnoreCase("")) {
            for (int i = 0; i < lista.size(); i++) {
            Licencias l = lista.get(i);
            Object[] datos = new Object[6]; 
            datos[0] = l.getRut();     
            datos[1] = l.getEnfermedad();     
            datos[2] = l.getDias();   
            datos[3] = l.getIsapre();    
            datos[4] = l.getFecha();     
            datos[5] = l.getEstado();  
            modelo.addRow(datos);
            String rut = l.getRut();
            String enf = l.getEnfermedad();
            int dias = l.getDias();
            String isa = l.getIsapre();
            String fec = l.getFecha();
            String est = l.getEstado();
            String dia = Integer.toString(dias);
            txtarea.append(rut+" "+enf+" "+dia+" "+isa+" "+fec+" "+est+"\n");
            }
        }
        grilla.setModel(modelo);
    }       
    
    private void showM(boolean m){
        jButton1.setEnabled(m);
        jButton2.setEnabled(m);
        jButton3.setEnabled(m);        
        jButton4.setEnabled(m);
        jButton5.setEnabled(m);
        jButton6.setEnabled(m);      
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtcont = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        tabla = new javax.swing.JScrollPane();
        grilla = new javax.swing.JTable();
        jButton7 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtarea = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(640, 400));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jLabel2.setText("Busqueda por algún criterio:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, 20));

        jTextField1.setEnabled(false);
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });
        jPanel1.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 10, 170, 20));

        jLabel6.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel6.setText("N° REGISTROS BD");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 0, -1, 40));

        txtcont.setEnabled(false);
        txtcont.setEditable(false);
        jPanel1.add(txtcont, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 10, 40, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 580, 40));

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jLabel3.setText("Mostrar Datos: _____________________________________________________");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 420, 20));

        grilla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tabla.setViewportView(grilla);

        jPanel2.add(tabla, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 560, 130));

        jButton7.setBackground(new java.awt.Color(29, 87, 135));
        jButton7.setFont(new java.awt.Font("Arial Narrow", 1, 10)); // NOI18N
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setText("ACTUALIZAR");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 0, 140, 30));

        jButton2.setBackground(new java.awt.Color(29, 87, 135));
        jButton2.setFont(new java.awt.Font("Arial Narrow", 1, 10)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("LISTAR TODAS LAS BD DEL PC");
        jButton2.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton2.setEnabled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 180, 20));

        jButton4.setBackground(new java.awt.Color(29, 87, 135));
        jButton4.setFont(new java.awt.Font("Arial Narrow", 1, 10)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("LISTAR BD HISTORICA");
        jButton4.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton4.setEnabled(false);
        jPanel2.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 220, 180, 20));

        jButton1.setBackground(new java.awt.Color(29, 87, 135));
        jButton1.setFont(new java.awt.Font("Arial Narrow", 1, 10)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("LISTAR 2 TABLAS JUNTAS (ESPECIALIDAD)");
        jButton1.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton1.setEnabled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 220, 180, 20));

        jButton3.setBackground(new java.awt.Color(29, 87, 135));
        jButton3.setFont(new java.awt.Font("Arial Narrow", 1, 10)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("LISTAR TODOS LOS RESPALDOS");
        jButton3.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton3.setEnabled(false);
        jPanel2.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 180, 20));

        jButton5.setBackground(new java.awt.Color(29, 87, 135));
        jButton5.setFont(new java.awt.Font("Arial Narrow", 1, 10)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("LISTAR TABLAS DE LA BD");
        jButton5.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        jButton5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton5.setEnabled(false);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 250, 180, 20));

        jButton6.setBackground(new java.awt.Color(29, 87, 135));
        jButton6.setFont(new java.awt.Font("Arial Narrow", 1, 10)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("LISTAR 2 TABLAS JUNTAS (PERFIL)");
        jButton6.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        jButton6.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton6.setEnabled(false);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 250, 180, 20));

        txtarea.setEditable(false);
        txtarea.setColumns(20);
        txtarea.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        txtarea.setTabSize(3000);
        jScrollPane1.setViewportView(txtarea);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 560, 40));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 580, 290));

        jLabel4.setFont(new java.awt.Font("Arial Narrow", 1, 34)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(28, 60, 92));
        jLabel4.setText(" LISTADO Y BUSQUEDA");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 330, -1));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fprincipal.png"))); // NOI18N
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 640, 410));

        jMenu1.setText("ConectarBD");
        jMenu1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu1MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu1);

        jMenu2.setText("DesconectarBD");
        jMenu2.setEnabled(false);
        jMenu2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jMenu2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu2MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        dao.listarUsuariosPerfil(grilla);
        txtarea.setText("");
        ArrayList<Usuario> lista2 = new ArrayList();
        lista2 = dao.listarUsuarios();
        for (int i = 0; i < lista2.size(); i++) {
            Usuario u = lista2.get(i);
            txtarea.append(u.getUsuario()+" "+u.getNombre()+" "+u.getApellido()+" "+u.getPerfil()+"\n");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        if(jMenu1.isEnabled()){
            JOptionPane.showMessageDialog(this, "<html><b> ¡Error! </b><br>El servidor se encuentra desconectado.</html>");
        }else{
            dao.listarLicencias(grilla);
            txtcont.setText(Integer.toString(dao.mostrarCantidadReg()));
            txtarea.setText("");
            listarLicencias();
            jTextField1.setEnabled(true);
            showM(true);
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jMenu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MouseClicked
        // CONECTAR
        if(jMenu1.isEnabled()){
            if(conectado==false){
                conectado=true;
                dao.conectar();
                JOptionPane.showMessageDialog(this, "<html><b> ¡Has sido conectado con éxito! </b></html>");  
                jMenu1.setEnabled(false);
                jMenu2.setEnabled(true);
            }            
        }
    }//GEN-LAST:event_jMenu1MouseClicked

    private void jMenu2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu2MouseClicked
        // DESCONECTAR
        if(jMenu2.isEnabled()){
            if(conectado==true){
                conectado=false;
                dao.desconectar();
                txtcont.setText("");
                txtarea.setText("");
                JOptionPane.showMessageDialog(this, "<html><b> ¡Has sido desconectado con éxito! </b></html>");  
                jMenu2.setEnabled(false);
                jMenu1.setEnabled(true);
                showM(false);
            }            
        }
    }//GEN-LAST:event_jMenu2MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String titulos="BD DEL PC";
        txtarea.setText("");
        DefaultTableModel modelo = (DefaultTableModel) grilla.getModel();
        int filas = modelo.getRowCount();
        modelo.setColumnCount(0);
        for (int i = 0; i < filas; i++) {
            modelo.removeRow(0);
        }
        modelo.addColumn(titulos);
        ArrayList<String> bases = new ArrayList();
        bases = dao.listarBD();
        String bd="";        
        Object[] datos = new Object[1];
        for (int i = 0; i < bases.size(); i++) {
            bd = bases.get(i);
            datos[0] = bd;
            modelo.addRow(datos);
            txtarea.append(bd+"\n");
        }
        grilla.setModel(modelo);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        String titulos="TABLAS DE LA BD";
        txtarea.setText("");
        DefaultTableModel modelo = (DefaultTableModel) grilla.getModel();
        int filas = modelo.getRowCount();
        modelo.setColumnCount(0);
        for (int i = 0; i < filas; i++) {
            modelo.removeRow(0);
        }
        modelo.addColumn(titulos);
        ArrayList<String> tablas = new ArrayList();        
        tablas = dao.listarTablas();
        String tb="";        
        Object[] datos = new Object[1];
        for (int i = 0; i < tablas.size(); i++) {
            tb = tablas.get(i);
            datos[0] = tb;
            modelo.addRow(datos);
            txtarea.append(tb+"\n");
        }
        grilla.setModel(modelo);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        dao.listarUsuariosEspecialiad(grilla);
        txtarea.setText("");
        ArrayList<Usuario> lista2 = new ArrayList();
        lista2 = dao.listarUsuarios();
        for (int i = 0; i < lista2.size(); i++) {
            Usuario u = lista2.get(i);
            txtarea.append(u.getUsuario()+" "+u.getNombre()+" "+u.getApellido()+" "+u.getEspecialidad()+"\n");
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        if(!this.jTextField1.getText().isEmpty()){
            String cad=this.jTextField1.getText();
            busquedaEmergente(cad);
        }else{
            listarLicencias();
            dao.listarLicencias(grilla);
        }
    }//GEN-LAST:event_jTextField1KeyReleased

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        Autentificacion a = new Autentificacion();
        a.credencial(credencial);
        a.setVisible(true); 
        if (jMenu2.isEnabled()) {
            dao.desconectar();
        }
        this.dispose();
    }//GEN-LAST:event_formWindowClosed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable grilla;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JScrollPane tabla;
    private javax.swing.JTextArea txtarea;
    private javax.swing.JTextField txtcont;
    // End of variables declaration//GEN-END:variables
}
