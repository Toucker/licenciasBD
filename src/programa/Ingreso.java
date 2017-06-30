package programa;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;


public class Ingreso extends javax.swing.JFrame {

    ArrayList<Licencias> lista;
    DAO dao = new DAO();
    boolean existe=false,conectado=false;
    String hora,fecha,usuario;
    String rut,enf,isa,fechastring,est;
    int dias,cantidad,existet;
    private Credencial credencial;
    
    public Ingreso() {
        initComponents();
        this.setLocationRelativeTo(null);
        ImageIcon img = new ImageIcon(getClass().getResource("/imagenes/icono.png"));
        setIconImage(img.getImage());        
        setTitle("Ingreso de licencias Medicas");
        txtrut1.requestFocus();    
    }
    
    public void credencial(Credencial c){
        this.credencial = c;
    }    
         
    private void limpiarCasillas(){
        txtrut1.setText("");
        txtrut2.setText("");
        cboenf.setSelectedIndex(0);
        txtdias.setText("");
        cboisa.setSelectedIndex(0);
        selfecha.setDate(null);
    }
        
    private void activar(boolean option){
        cboenf.setEnabled(option);
        txtdias.setEnabled(option);
        cboisa.setEnabled(option);
        optace.setEnabled(option);
        optrec.setEnabled(option);
        selfecha.setEnabled(option);
    } 

    private void activar2(boolean option){
        txtrut1.setEnabled(option);
        txtrut2.setEnabled(option);
    }
    
    private void rut(){
        if(jMenu1.isEnabled()){
            JOptionPane.showMessageDialog(this, "<html><b> ¡Error! </b><br>El servidor se encuentra desconectado.</html>");
        }else if(txtrut1.getText().length()>=7){
            if(txtrut1.getText().equals("")&&txtrut2.getText().equals("")){
                JOptionPane.showMessageDialog(this, "Debe ingresar un RUT para iniciar la busqueda.");
            }else if(txtrut1.getText().equals("") || txtrut2.getText().equals("") ){
                JOptionPane.showMessageDialog(this, "Debe ingresar un RUT valido para iniciar la busqueda.");
            }else{
                activar(true);
                activar2(false);
                btnreg.setEnabled(true);
            }
        }else{
            JOptionPane.showMessageDialog(this, "Debe ingresar un RUT valido para iniciar la busqueda.");
        }      
    }
    
    private void registrarLicencia(){
        rut = txtrut1.getText()+"-"+txtrut2.getText();
        enf = cboenf.getSelectedItem().toString();
        dias = Integer.parseInt(txtdias.getText());
        isa = cboisa.getSelectedItem().toString();
        Date fechajd=selfecha.getDate();
        SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
        fechastring=sdf.format(fechajd); 
        if (optace.isSelected()) {
            est = optace.getText();
            }else{
            est = optrec.getText();
            }
        Licencias l = new Licencias(rut,enf,dias,isa,fechastring,est);
        dao.registrarLicencia(l);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupoEstados = new javax.swing.ButtonGroup();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtcont = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cboenf = new javax.swing.JComboBox<>();
        txtdias = new javax.swing.JTextField();
        cboisa = new javax.swing.JComboBox<>();
        optace = new javax.swing.JRadioButton();
        optrec = new javax.swing.JRadioButton();
        txtrut1 = new javax.swing.JFormattedTextField();
        txtrut2 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        selfecha = new com.toedter.calendar.JDateChooser();
        btnreg = new javax.swing.JButton();
        btnres = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Arial Narrow", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setText("LICENCIAS MEDICAS");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        jLabel4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel4.setText("N° REGISTROS BD");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 70, -1, 20));

        txtcont.setEnabled(false);
        txtcont.setEditable(false);
        getContentPane().add(txtcont, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 70, 40, -1));

        jPanel1.setBackground(new java.awt.Color(82, 82, 82));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("RUT PACIENTE");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, 20));

        jLabel6.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText(" - ");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 10, 20, 20));

        jLabel7.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("DÍAS DE LICENCIA");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, -1, 20));

        jLabel8.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("ISAPRE");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, -1, 20));

        jLabel9.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("FECHA");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, -1, 20));

        cboenf.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECCIONE UNA", "Gastritis", "Bronquitis", "Fractura", "Cancer" }));
        cboenf.setEnabled(false);
        jPanel1.add(cboenf, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 40, 180, -1));

        txtdias.setEnabled(false);
        txtdias.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtdiasKeyTyped(evt);
            }
        });
        jPanel1.add(txtdias, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 70, 180, -1));

        cboisa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECCIONE UNA", "Fonasa", "Ing", "Consalud", "Fusat", "Isamedica" }));
        cboisa.setEnabled(false);
        jPanel1.add(cboisa, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 100, 180, -1));

        optace.setBackground(new java.awt.Color(82, 82, 82));
        grupoEstados.add(optace);
        optace.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        optace.setForeground(new java.awt.Color(255, 255, 255));
        optace.setSelected(true);
        optace.setText("ACEPTADA");
        optace.setEnabled(false);
        jPanel1.add(optace, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 160, -1, -1));

        optrec.setBackground(new java.awt.Color(82, 82, 82));
        grupoEstados.add(optrec);
        optrec.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        optrec.setForeground(new java.awt.Color(255, 255, 255));
        optrec.setText("RECHAZADA");
        optrec.setEnabled(false);
        optrec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optrecActionPerformed(evt);
            }
        });
        jPanel1.add(optrec, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 160, -1, -1));

        txtrut1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtrut1KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtrut1KeyTyped(evt);
            }
        });
        jPanel1.add(txtrut1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 10, 120, -1));

        txtrut2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtrut2KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtrut2KeyTyped(evt);
            }
        });
        jPanel1.add(txtrut2, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 10, 40, -1));

        jLabel11.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("ENFERMEDAD");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, -1, 20));

        jLabel10.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("ESTADO");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, -1, 20));

        selfecha.setEnabled(false);
        jPanel1.add(selfecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 130, 180, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 350, 190));

        btnreg.setFont(new java.awt.Font("Arial Narrow", 1, 12)); // NOI18N
        btnreg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/ico_save.png"))); // NOI18N
        btnreg.setText("GRABAR");
        btnreg.setEnabled(false);
        btnreg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnregActionPerformed(evt);
            }
        });
        getContentPane().add(btnreg, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, 110, -1));

        btnres.setFont(new java.awt.Font("Arial Narrow", 1, 12)); // NOI18N
        btnres.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/ico_ingresar.png"))); // NOI18N
        btnres.setText("OTRO INGRESO");
        btnres.setEnabled(false);
        btnres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnresActionPerformed(evt);
            }
        });
        getContentPane().add(btnres, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 330, 140, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fingreso.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jMenu1.setText("Conectar BD");
        jMenu1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu1MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Desconectar BD");
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

    private void optrecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optrecActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_optrecActionPerformed

    private void txtrut1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtrut1KeyTyped
        char c = evt.getKeyChar();
	if(txtrut1.getText().length() >= 8 || c<'0'||c>'9'){ 
            evt.consume();
        } 
    }//GEN-LAST:event_txtrut1KeyTyped

    private void txtrut1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtrut1KeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER) {
            rut();
        }
    }//GEN-LAST:event_txtrut1KeyPressed

    private void txtrut2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtrut2KeyTyped
        char c = evt.getKeyChar();
	if(txtrut2.getText().length() >= 1 || (c<'1'||c>'9') && (c<'k'||c>'k') && (c<'K'||c>'K')){ 
            evt.consume();
        }
    }//GEN-LAST:event_txtrut2KeyTyped

    private void txtrut2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtrut2KeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER) {
            rut();
        }       
    }//GEN-LAST:event_txtrut2KeyPressed

    private void txtdiasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdiasKeyTyped
        char c = evt.getKeyChar();
        if ((c<'0'|| c>'9')) {
            evt.consume();
        }
        if (txtdias.getText().length() >= 2) {
            evt.consume();
        }
    }//GEN-LAST:event_txtdiasKeyTyped

    private void btnregActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnregActionPerformed
        String cbopt1 = cboenf.getSelectedItem().toString();
        String cbopt2 = cboisa.getSelectedItem().toString();
        ArrayList<String> lista_errores = new ArrayList();
        String fechastring=null;
        
        if (txtrut1.getText().length() == 0 || txtrut2.getText().length() == 0) {
            lista_errores.add(" Debe ingresar un rut\n");
        }
        
        if (cbopt1.equalsIgnoreCase("seleccione una")) {
            lista_errores.add("Debe seleccionar una enfermedad\n");  
        }
        
        if (txtdias.getText().length() == 0 ) {
            lista_errores.add("Debe ingresar una cantidad de días\n");
        }
        
        if (cbopt2.equalsIgnoreCase("seleccione una")) {
            lista_errores.add("Debe seleccionar una Isapre\n");
        }
        
        try{
        Date fechajd=selfecha.getDate();
        SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
        fechastring=sdf.format(fechajd); 
        }catch(Exception e){
            lista_errores.add("Debe seleccionar una fecha");
        }
                       
        if (!lista_errores.isEmpty() ) {
            String errores = lista_errores.toString().replaceAll(",", "- ").replace("[","- ").replace("]","");
            javax.swing.JOptionPane.showMessageDialog(this,"Tiene los siguientes errores:"+"\n\n"+errores, "Error", 2);
        }else{
            activar(false);
            existe = dao.verificarTabla();
            if(existe == false){
                int dialogResult = JOptionPane.showConfirmDialog(this, "<html> <b> ¡No existe la tabla! </b><br>¿Desea crearla?</html>", "", JOptionPane.YES_NO_OPTION);
                if (dialogResult == 0){ 
                    dao.createTableLicencias();
                    dao.createTableLicenciasHistorico();
                    registrarLicencia();
                    int dialogResult2 = JOptionPane.showConfirmDialog(this, "<html> <b> ¡Datos guardados! </b><br>¿Desea realizar un nuevo ingreso?</html>", "", JOptionPane.YES_NO_OPTION);
                    if (dialogResult2 == 0){
                        btnreg.setEnabled(false);
                        limpiarCasillas();
                        cantidad = dao.mostrarCantidadReg();
                        txtcont.setText(Integer.toString(cantidad));
                    }else{
                        btnreg.setEnabled(false);
                        btnres.setEnabled(false);
                    }    
                }else{ 
                    JOptionPane.showMessageDialog(this, "Los datos no fueron guardados.");                    
                }                
            }else{
                registrarLicencia();
                int dialogResult3 = JOptionPane.showConfirmDialog(this, "<html> <b> ¡Datos guardados! </b><br>¿Desea realizar un nuevo ingreso?</html>", "", JOptionPane.YES_NO_OPTION);                
                if (dialogResult3 == 0){
                    btnreg.setEnabled(false);
                    btnres.setEnabled(true);
                }else{
                    btnreg.setEnabled(false);
                    btnres.setEnabled(false); 
                }
            }
        }
    }//GEN-LAST:event_btnregActionPerformed

    private void btnresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnresActionPerformed
        limpiarCasillas();
        activar(false);
        activar2(true);
        txtcont.setText(Integer.toString(dao.mostrarCantidadReg()));
        btnres.setEnabled(false);
    }//GEN-LAST:event_btnresActionPerformed

    private void jMenu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MouseClicked
        // CONECTAR
        if(jMenu1.isEnabled()){
            if(conectado==false){
                    conectado=true;
                    dao.conectar();
                    JOptionPane.showMessageDialog(this, "<html><b> ¡Has sido conectado con éxito! </b></html>");  
                    txtcont.setText(Integer.toString(dao.mostrarCantidadReg()));
                    jMenu1.setEnabled(false);
                    jMenu2.setEnabled(true); 
                    txtrut1.setEnabled(true);
                    txtrut2.setEnabled(true);
            }            
        }
    }//GEN-LAST:event_jMenu1MouseClicked

    private void jMenu2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu2MouseClicked
        // DESCONECTAR
        if(jMenu2.isEnabled()){
            if(conectado==true){
                conectado=false;
                dao.desconectar();
                JOptionPane.showMessageDialog(this, "<html><b> ¡Has sido desconectado con éxito! </b></html>"); 
                limpiarCasillas();
                activar(false);
                jMenu2.setEnabled(false);
                jMenu1.setEnabled(true);
                btnreg.setEnabled(false);
                btnres.setEnabled(false);
                txtcont.setText("");
            }            
        }
    }//GEN-LAST:event_jMenu2MouseClicked

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        Autentificacion a = new Autentificacion();
        limpiarCasillas();
        a.credencial(credencial);
        a.setVisible(true); 
        if (existe == true) {
            dao.desconectar();
        }
        this.dispose();
    }//GEN-LAST:event_formWindowClosed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnreg;
    private javax.swing.JButton btnres;
    private javax.swing.JComboBox<String> cboenf;
    private javax.swing.JComboBox<String> cboisa;
    private javax.swing.ButtonGroup grupoEstados;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton optace;
    private javax.swing.JRadioButton optrec;
    private com.toedter.calendar.JDateChooser selfecha;
    private javax.swing.JTextField txtcont;
    private javax.swing.JTextField txtdias;
    private javax.swing.JFormattedTextField txtrut1;
    private javax.swing.JTextField txtrut2;
    // End of variables declaration//GEN-END:variables
}
