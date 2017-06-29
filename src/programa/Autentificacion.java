
package programa;

import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;


public class Autentificacion extends javax.swing.JFrame {
    ArrayList<String> usuarios;
    DAO a;
    
    private Credencial credencial;
    
    int intentos=0,limite=0;
    String hour,date,usuario;
    
    int cont1;  
        
    Registrar r = new Registrar();  
    Ingreso i = new Ingreso();    
    Listado l = new Listado();  
    
    boolean autores;
    
    DAO d = new DAO();
    
    public Autentificacion() {
        initComponents();
        this.setLocationRelativeTo(null);        
        ImageIcon img = new ImageIcon(getClass().getResource("/imagenes/icono.png"));
        setIconImage(img.getImage());
       //d.conectar();                
        setTitle("Autentificación de usuarios");
        Timer reloj = new Timer();
        TimerTask task = new TimerTask() {
            public void run() {
              relojs();
            }
        };        
        reloj.scheduleAtFixedRate(task, 1000, 1000);    
    }
    
    public void credencial(Credencial c){
        this.credencial = c;
        jMenu1.setEnabled(true);
        jLabel10.setText("INFORMACIÓN");
        jLabel15.setText("Usuario: "+c.getUsuario());
        jLabel20.setText("Hora de Ingreso: "+c.getHora());
        jLabel21.setText("Fecha de Ingreso: "+c.getFecha());
        switch(c.getPerfil()){
            case 1: jLabel1.setText("Perfil: Administrador");
                    jMenu6.setEnabled(true);
                    jMenu6.setVisible(true);
                    jMenuItem2.setEnabled(true);
                    break;
            case 2:jLabel1.setText("Perfil: Doctor");jMenuItem2.setEnabled(true);break;
                    
            default:jLabel1.setText("Perfil: Observador");jMenuItem2.setEnabled(false);break;
        }
        
        jButton1.setVisible(false);
        logmenu(false);
    }
    
    private void relojs(){
        Date now = new Date(System.currentTimeMillis());
        SimpleDateFormat fecha = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat hora = new SimpleDateFormat("HH:mm:ss");        
        jLabel8.setText(fecha.format(now));
        jLabel7.setText(hora.format(now)+" Hrs.");           
    }  
    
    private void login(){
        String pass = new String(jPasswordField1.getPassword());
        usuario = jTextField1.getText();
        if(intentos <=2){

            if(pass.equals("")){
                JOptionPane.showMessageDialog(this, "Es necesario que ingrese su contraseña!");
            }else{
                String log = d.ingresar(jTextField1.getText(), pass);
                if(!log.equalsIgnoreCase("ERROR")){
                    Date now = new Date(System.currentTimeMillis());
                    SimpleDateFormat fecha = new SimpleDateFormat("dd-MM-yyyy");
                    SimpleDateFormat hora = new SimpleDateFormat("HH:mm");
                    hour=""+hora.format(now);
                    date=""+fecha.format(now);                    
                    if(log.equalsIgnoreCase("1")){
                        JOptionPane.showMessageDialog(this, "Usuario: "+usuario+".\nPerfil: Administrador\nHora: "+hora.format(now)+" Hrs"+"\nFecha: "+fecha.format(now),"Has ingresado con éxito.",JOptionPane.CLOSED_OPTION);                        
                        jMenu6.setEnabled(true);
                        jMenu6.setVisible(true);                     
                    }else if(log.equalsIgnoreCase("2")){
                        JOptionPane.showMessageDialog(this, "Usuario: "+usuario+".\nPerfil: Doctor\nHora: "+hora.format(now)+" Hrs"+"\nFecha: "+fecha.format(now),"Has ingresado con éxito.",JOptionPane.CLOSED_OPTION);  
                        jMenuItem2.setEnabled(true);
                    }else{
                        JOptionPane.showMessageDialog(this, "Usuario: "+usuario+".\nPerfil: Observador\nHora: "+hora.format(now)+" Hrs"+"\nFecha: "+fecha.format(now),"Has ingresado con éxito.",JOptionPane.CLOSED_OPTION);                          
                        jMenuItem2.setEnabled(false);
                    }
                    credencial = new Credencial(usuario,hora.format(now),fecha.format(now),Integer.parseInt(log));
                    jLabel10.setText("INFORMACIÓN");
                    jLabel15.setText("Usuario: "+usuario);
                    jLabel20.setText("Hora de Ingreso: "+hora.format(now));
                    jLabel21.setText("Fecha de Ingreso: "+fecha.format(now));
                    switch(Integer.parseInt(log)){
                        case 1:jLabel1.setText("Perfil: Administrador");break;
                        case 2:jLabel1.setText("Perfil: Doctor");break;
                        default:jLabel1.setText("Perfil: Observador");break;
                    }                    
                    jMenu1.setEnabled(true);                   
                    logmenu(false);
                }else{
                    JOptionPane.showMessageDialog(this, "No existe ninguna cuenta que coincida con los datos ingresados. ","ERROR",JOptionPane.ERROR_MESSAGE);
                    intentos++;
                    jTextField2.setText(Integer.toString(intentos));
                }
            }
        
        }else{
            limite++;
            JOptionPane.showMessageDialog(this, "Excedió el limite de intentos permitidos, espere un momento. ","ERROR",JOptionPane.ERROR_MESSAGE);
            if(limite==1){
                Timer failed = new Timer();
                TimerTask taskint = new TimerTask() {
                    public void run() {
                      fallidos();
                    }
                };        
                failed.schedule(taskint, 10000);
            }         
        }        
    }
              
    private void logmenu(boolean b){
        jLabel2.setVisible(b);
        jTextField1.setVisible(b);
        jPasswordField1.setVisible(b);
        jLabel4.setVisible(b);
        jTextField2.setVisible(b);
        jButton2.setVisible(b);
        jButton3.setVisible(b);
        jButton4.setVisible(b);
        jButton5.setVisible(b);
        if(b == true){
            jMenu5.setEnabled(false);
            jMenu1.setEnabled(false);
        }else{
            jMenu5.setEnabled(true);
            jMenu1.setEnabled(true);
        }
    }    
    
    private void fallidos(){
        intentos=0;
        limite=0;
        jTextField2.setText(Integer.toString(intentos));
        JOptionPane.showMessageDialog(this, "El ingreso fue habilitado nuevamente, cuenta con tres intentos. ");
    }       

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        jLabel3 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        logo = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jPasswordField1 = new javax.swing.JPasswordField();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu6 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenu7 = new javax.swing.JMenu();
        jMenu8 = new javax.swing.JMenu();
        jMenu9 = new javax.swing.JMenu();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        jDialog1.setBackground(new java.awt.Color(255, 255, 255));
        jDialog1.setResizable(false);
        jDialog1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jDialog1FocusLost(evt);
            }
        });
        jDialog1.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("AUTORES:");
        jDialog1.getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, -1, -1));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel11.setText("- Matías Rivas");
        jDialog1.getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, -1, -1));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel12.setText("- Raul Fuentes");
        jDialog1.getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, -1, -1));

        jLabel14.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        jLabel14.setText("+569 93594042");
        jDialog1.getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 220, 100, -1));

        jLabel13.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        jLabel13.setText("+569 50912262");
        jDialog1.getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 160, 100, -1));

        jLabel16.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        jLabel16.setText("matias.rivas@mrmonkey.cl");
        jDialog1.getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 180, 150, -1));

        jLabel17.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        jLabel17.setText("raul.fuentes@mrmonkey.cl");
        jDialog1.getContentPane().add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 240, 160, -1));

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel18.setText("Mr. Monkeys");
        jDialog1.getContentPane().add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/logo.png"))); // NOI18N
        jDialog1.getContentPane().add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 70, -1, -1));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/MOSTRAR_USU1.png"))); // NOI18N
        jButton1.setBorder(null);
        jButton1.setBorderPainted(false);
        jButton1.setMaximumSize(new java.awt.Dimension(150, 40));
        jButton1.setMinimumSize(new java.awt.Dimension(150, 40));
        jButton1.setPreferredSize(new java.awt.Dimension(150, 40));
        jButton1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/MOSTRAR_USU2.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 300, -1, 30));

        jButton2.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/BTN1-2.png"))); // NOI18N
        jButton2.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/BTM1-2.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 300, 40, 30));
        jButton2.setVisible(false);

        jButton3.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/BTN2-1.png"))); // NOI18N
        jButton3.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/BTM2-1.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 300, 50, 30));
        jButton3.setVisible(false);

        jButton4.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/BTN1-1.png"))); // NOI18N
        jButton4.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/BTM1-1.png"))); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 300, 40, 30));
        jButton4.setVisible(false);

        jButton5.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/BTN2-2.png"))); // NOI18N
        jButton5.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/BTM2-2.png"))); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 300, 50, 30));
        jButton5.setVisible(false);

        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/lmedicine.png"))); // NOI18N
        getContentPane().add(logo, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 0, -1, -1));

        jTextField1.setBorder(null);
        jTextField1.setEditable(false);
        jTextField1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
        });
        getContentPane().add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 170, 240, 30));

        jPasswordField1.setBorder(null);
        jPasswordField1.setEditable(false);
        jPasswordField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPasswordField1KeyPressed(evt);
            }
        });
        getContentPane().add(jPasswordField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 230, 240, 30));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/login.png"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, -1, -1));

        jLabel4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("INTENTO FALLIDO N°:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 360, 130, 20));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 51, 102));
        jLabel5.setText("Hora:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, 20));

        jLabel6.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 51, 102));
        jLabel6.setText("Fecha:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, 20));

        jLabel7.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 51, 102));
        jLabel7.setText("00:00:00 Hrs.");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, 80, 20));

        jLabel8.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 51, 102));
        jLabel8.setText("00/00/0000");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, 70, 20));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 350, 160, 40));

        jTextField2.setEditable(false);
        jTextField2.setBackground(new java.awt.Color(255, 255, 255));
        jTextField2.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        jTextField2.setText("0");
        getContentPane().add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 360, 20, 20));

        jLabel10.setFont(new java.awt.Font("Calibri", 1, 40)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(35, 47, 93));
        jLabel10.setText("AUTENTIFICACIÓN");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, -1, -1));

        jLabel15.setFont(new java.awt.Font("Arial Narrow", 1, 10)); // NOI18N
        jLabel15.setText("Usuario:");
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 200, -1, -1));

        jLabel20.setFont(new java.awt.Font("Arial Narrow", 1, 10)); // NOI18N
        jLabel20.setText("Hora de Ingreso:");
        getContentPane().add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 240, -1, -1));

        jLabel21.setFont(new java.awt.Font("Arial Narrow", 1, 10)); // NOI18N
        jLabel21.setText("Fecha de Ingreso:");
        getContentPane().add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 260, -1, -1));

        jLabel1.setFont(new java.awt.Font("Arial Narrow", 1, 10)); // NOI18N
        jLabel1.setText("Perfil:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 220, -1, -1));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fprincipal.png"))); // NOI18N
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jMenuBar1.setBackground(new java.awt.Color(0, 51, 51));

        jMenu6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/ico_administracion.png"))); // NOI18N
        jMenu6.setText("Administración");
        jMenu6.setEnabled(false);
        jMenu6.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jMenu6.setVisible(false);
        jMenu6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu6MouseClicked(evt);
            }
        });

        jMenu3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/ico_usu.png"))); // NOI18N
        jMenu3.setText("Mantenedor de Usuarios");
        jMenu3.setFont(new java.awt.Font("Arial Narrow", 0, 12)); // NOI18N
        jMenu3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu3MouseClicked(evt);
            }
        });
        jMenu6.add(jMenu3);

        jMenu7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/ico_backup.png"))); // NOI18N
        jMenu7.setText("Respaldos");
        jMenu7.setFont(new java.awt.Font("Arial Narrow", 0, 12)); // NOI18N

        jMenu8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/ico_import.png"))); // NOI18N
        jMenu8.setText("Importar");
        jMenu8.setFont(new java.awt.Font("Arial Narrow", 0, 12)); // NOI18N
        jMenu7.add(jMenu8);

        jMenu9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/ico_export.png"))); // NOI18N
        jMenu9.setText("Exportar");
        jMenu9.setFont(new java.awt.Font("Arial Narrow", 0, 12)); // NOI18N
        jMenu7.add(jMenu9);

        jMenu6.add(jMenu7);

        jMenuBar1.add(jMenu6);

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/ico_archivo.png"))); // NOI18N
        jMenu1.setText("Archivo");
        jMenu1.setEnabled(false);
        jMenu1.setFocusable(false);
        jMenu1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu1MouseClicked(evt);
            }
        });

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/ico_ingresar.png"))); // NOI18N
        jMenuItem2.setText("Ingresar");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_W, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/ico_listar.png"))); // NOI18N
        jMenuItem1.setText("Listar");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/ico_ingreso.png"))); // NOI18N
        jMenu5.setText("Autentificación");
        jMenu5.setEnabled(false);
        jMenu5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jMenu5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu5MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu5);

        jMenu4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/ico_autor.png"))); // NOI18N
        jMenu4.setText("Autor");
        jMenu4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jMenu4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu4MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu4);

        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/ico_salir.png"))); // NOI18N
        jMenu2.setText("Salir");
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

    private void jMenu5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu5MouseClicked
        if(jMenu5.isEnabled()){
            int dialogResult = JOptionPane.showConfirmDialog(this, "<html> <b> ¿Estas seguro de cerrar tu sesión? </b></html>", "Autentificación", JOptionPane.YES_NO_OPTION);
            if (dialogResult == 0) { 
                jLabel10.setText("AUTENTIFICACIÓN");
                jTextField1.setText("");
                jPasswordField1.setText("");
                jLabel15.setText("");
                jLabel20.setText("");
                jLabel21.setText("");           
                intentos=0;
                jTextField2.setText(""+intentos);
                logmenu(true); 
                jButton1.setVisible(true);
                jButton2.setVisible(false);
                jButton3.setVisible(false);
                jButton4.setVisible(false);
                jButton5.setVisible(false);
                jMenu6.setEnabled(false);
                jMenu6.setVisible(false); 
            }           
        }
    }//GEN-LAST:event_jMenu5MouseClicked

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed

        l.credencial(credencial);
        l.setVisible(true);
        setVisible(false);
        
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jPasswordField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPasswordField1KeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            login();
        }        
        
    }//GEN-LAST:event_jPasswordField1KeyPressed

    private void jMenu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MouseClicked
    }//GEN-LAST:event_jMenu1MouseClicked

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            login();
        }
    }//GEN-LAST:event_jTextField1KeyPressed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        i.credencial(credencial);
        i.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenu4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu4MouseClicked
        jDialog1.setTitle("AUTORES");
        jDialog1.pack();
        jDialog1.setLocationRelativeTo(null);
        jDialog1.setVisible(true);
    }//GEN-LAST:event_jMenu4MouseClicked

    private void jMenu2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu2MouseClicked
        int dialogResult = JOptionPane.showConfirmDialog(this, "<html> <b> ¿Estas seguro de Salir? </b></html>", "", JOptionPane.YES_NO_OPTION);
        if (dialogResult == 0) { System.exit(0);}   
    }//GEN-LAST:event_jMenu2MouseClicked

    private void jDialog1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jDialog1FocusLost
        if (!(jDialog1.isFocusOwner())) {
            jDialog1.dispose();
        }
    }//GEN-LAST:event_jDialog1FocusLost

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        jTextField1.setText(usuarios.get(0));
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        jButton1.setVisible(false);
        jButton2.setVisible(true);
        jButton3.setVisible(true);
        jButton4.setVisible(true);
        jButton5.setVisible(true);
        jPasswordField1.setEditable(true);
        usuarios = d.usuarios();
        jTextField1.setText(usuarios.get(0));
        cont1=0;
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        jTextField1.setText(usuarios.get(usuarios.size()-1));
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if(cont1 != usuarios.size()){
            jTextField1.setText(usuarios.get(cont1));
            cont1++;
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        if (cont1 == 0){
            
        }else{
           cont1--; 
           jTextField1.setText(usuarios.get(cont1));
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jMenu6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu6MouseClicked

    }//GEN-LAST:event_jMenu6MouseClicked

    private void jMenu3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu3MouseClicked
        r.credencial(credencial);
        r.setEnabled(true);
        r.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenu3MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Autentificacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Autentificacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Autentificacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Autentificacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Autentificacion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenu jMenu9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JLabel logo;
    // End of variables declaration//GEN-END:variables
}
