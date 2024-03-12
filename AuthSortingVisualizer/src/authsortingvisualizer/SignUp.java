package authsortingvisualizer;

import javax.swing.*;
import java.sql.*;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author Zaw Linn Htet
 */

public class SignUp extends javax.swing.JFrame {
    
    public SignUp() {
       initComponents();
       password.setEchoChar('*'); 
       setIcon();
       setTitle("Sorting Visualizer");
    }
     
    private static String userEmail;
    private static String fullName;
    private static String userPassword;
    private static String hashedPassword;

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel9 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        fname = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        email = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        password = new javax.swing.JPasswordField();
        jLabel8 = new javax.swing.JLabel();
        SignInBtn = new javax.swing.JButton();
        showPass = new javax.swing.JCheckBox();
        jLabel10 = new javax.swing.JLabel();
        signUpBtn = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        confirmPassword = new javax.swing.JPasswordField();

        jLabel9.setFont(new java.awt.Font("Bahnschrift", 0, 36)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("SIGN IN");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sign Up");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(800, 500));
        jPanel1.setLayout(null);

        jPanel2.setBackground(new java.awt.Color(102, 153, 255));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/background.png"))); // NOI18N
        jLabel3.setText("h");

        jLabel2.setFont(new java.awt.Font("Showcard Gothic", 0, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Sorting Visualizer");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(389, 389, 389)
                .addComponent(jLabel1)
                .addContainerGap(11, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(20, 20, 20))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(jLabel2)
                .addGap(527, 527, 527)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2);
        jPanel2.setBounds(0, 0, 400, 500);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setBackground(new java.awt.Color(102, 102, 102));
        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Full name");

        fname.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        fname.setForeground(new java.awt.Color(102, 102, 102));
        fname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                fnameKeyPressed(evt);
            }
        });

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Email Address");

        email.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        email.setForeground(new java.awt.Color(102, 102, 102));
        email.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                emailKeyPressed(evt);
            }
        });

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Password");

        password.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        password.setForeground(new java.awt.Color(102, 102, 102));
        password.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                passwordKeyPressed(evt);
            }
        });

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Already have an account?");

        SignInBtn.setBackground(new java.awt.Color(102, 153, 255));
        SignInBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        SignInBtn.setForeground(new java.awt.Color(255, 255, 255));
        SignInBtn.setText("Sign In");
        SignInBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SignInBtnActionPerformed(evt);
            }
        });

        showPass.setBackground(new java.awt.Color(255, 255, 255));
        showPass.setForeground(new java.awt.Color(0, 0, 0));
        showPass.setText("Show Password");
        showPass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showPassActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Candara Light", 1, 36)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Sign up");

        signUpBtn.setBackground(new java.awt.Color(102, 153, 255));
        signUpBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        signUpBtn.setForeground(new java.awt.Color(255, 255, 255));
        signUpBtn.setText("Sign Up");
        signUpBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signUpBtnActionPerformed(evt);
            }
        });

        jLabel11.setBackground(new java.awt.Color(255, 255, 255));
        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Confirm Password");

        confirmPassword.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        confirmPassword.setForeground(new java.awt.Color(102, 102, 102));
        confirmPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                confirmPasswordKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)
                        .addComponent(SignInBtn))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(31, 31, 31)
                        .addComponent(jLabel10))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(signUpBtn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel11)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(showPass))
                        .addComponent(fname, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE)
                        .addComponent(email, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(password, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(confirmPassword, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING)))
                .addContainerGap(43, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel10)
                        .addGap(22, 22, 22))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel5)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fname, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(showPass))
                .addGap(7, 7, 7)
                .addComponent(confirmPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addComponent(signUpBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SignInBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addContainerGap(42, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel3);
        jPanel3.setBounds(400, 0, 400, 500);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void SignInBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SignInBtnActionPerformed
        Login LoginFrame = new Login();
        LoginFrame.setVisible(true);
        LoginFrame.pack();
        LoginFrame.setLocationRelativeTo(null); 
        this.dispose();
    }//GEN-LAST:event_SignInBtnActionPerformed
  
    private void showPassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showPassActionPerformed
        if (showPass.isSelected()) {
            password.setEchoChar((char) 0);
            confirmPassword.setEchoChar((char) 0);
        }
        else {
            password.setEchoChar('*');
            confirmPassword.setEchoChar('*');
        }
    }//GEN-LAST:event_showPassActionPerformed

    private void fnameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fnameKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            fullName = fname.getText();
            
            if (fullName.isEmpty()) {
                JOptionPane.showMessageDialog(new JFrame(), "Full name is required", "Error", JOptionPane.ERROR_MESSAGE);
                fname.requestFocusInWindow();
            } else {
                email.requestFocusInWindow();
            }
        }
    }//GEN-LAST:event_fnameKeyPressed

    private void emailKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_emailKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try (Connection con = establishConnection()) {
                userEmail = email.getText();
           
                if (userEmail.isEmpty()) {
                    JOptionPane.showMessageDialog(new JFrame(), "Email is required", "Error", JOptionPane.ERROR_MESSAGE);
                    email.requestFocusInWindow();
                } else if (!userEmail.contains("@")) {
                    JOptionPane.showMessageDialog(new JFrame(), "Invalid email format. Please include '@'", "Error", JOptionPane.ERROR_MESSAGE);
                    email.requestFocusInWindow();
                } else {
                    String checkQuery = "SELECT * FROM user WHERE email=?";
                    
                    try (PreparedStatement checkStmt = con.prepareStatement(checkQuery)) {
                        checkStmt.setString(1, userEmail);
                        ResultSet resultSet = checkStmt.executeQuery();

                        if (resultSet.next()) {
                            // User already exists
                            JOptionPane.showMessageDialog(new JFrame(), "User with this email already exists", "Error", JOptionPane.ERROR_MESSAGE);
                        } else {
                            password.requestFocusInWindow();
                        }
                    }
                }
            } catch (ClassNotFoundException | SQLException e) {
                JOptionPane.showMessageDialog(new JFrame(), "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }      
    }//GEN-LAST:event_emailKeyPressed

    private void passwordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passwordKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            // User input retrieval
            fullName = fname.getText();
            userEmail = email.getText();
            userPassword = new String(password.getPassword());

            // Hash the password using BCrypt
            String hashedPassword = BCrypt.hashpw(userPassword, BCrypt.gensalt());

            try (Connection con = establishConnection()) {
                if (userPassword.isEmpty()) {
                    JOptionPane.showMessageDialog(new JFrame(), "Password is required", "Error", JOptionPane.ERROR_MESSAGE);
                    password.requestFocusInWindow();
                } else if (userPassword.length() < 8) {
                    JOptionPane.showMessageDialog(new JFrame(), "Password must be at least eight characters long", "Error", JOptionPane.ERROR_MESSAGE);
                    password.requestFocusInWindow();
                } else {
                    confirmPassword.requestFocusInWindow();
                }          
            } catch (ClassNotFoundException | SQLException e) {
                JOptionPane.showMessageDialog(new JFrame(), "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_passwordKeyPressed

    private void signUpBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signUpBtnActionPerformed
        // User input retrieval
        fullName = fname.getText();
        userEmail = email.getText();
        userPassword = new String(password.getPassword());
        String userConfirmPassword = new String(confirmPassword.getPassword());

        // Hash the password using BCrypt
        String hashedPassword = BCrypt.hashpw(userPassword, BCrypt.gensalt());
 
        try (Connection con = establishConnection()) {
            // Check if full name is provided
            if (fullName.isEmpty()) {
                JOptionPane.showMessageDialog(new JFrame(), "Full name is required", "Error", JOptionPane.ERROR_MESSAGE);
                fname.requestFocusInWindow();
            } else if (userEmail.isEmpty()) {
                JOptionPane.showMessageDialog(new JFrame(), "Email is required", "Error", JOptionPane.ERROR_MESSAGE);
                email.requestFocusInWindow();
            } else if (!userEmail.contains("@")) {
                JOptionPane.showMessageDialog(new JFrame(), "Invalid email format. Please include '@'", "Error", JOptionPane.ERROR_MESSAGE);
                email.requestFocusInWindow();
            } else if (userPassword.isEmpty()) {
                JOptionPane.showMessageDialog(new JFrame(), "Password is required", "Error", JOptionPane.ERROR_MESSAGE);
                password.requestFocusInWindow();
            } else if (userPassword.length() < 8) {
                JOptionPane.showMessageDialog(new JFrame(), "Password must be at least eight characters long", "Error", JOptionPane.ERROR_MESSAGE);
                password.requestFocusInWindow();
            } else if (!userPassword.equals(userConfirmPassword)) {
                JOptionPane.showMessageDialog(new JFrame(), "Passwords do not match", "Error", JOptionPane.ERROR_MESSAGE);
                password.requestFocusInWindow();
            } else {
                // Check if the user already exists in the database
                String checkQuery = "SELECT * FROM user WHERE email=?";
                try (PreparedStatement checkStmt = con.prepareStatement(checkQuery)) {
                    checkStmt.setString(1, userEmail);
                    ResultSet resultSet = checkStmt.executeQuery();

                    if (resultSet.next()) {
                        // User already exists
                        JOptionPane.showMessageDialog(new JFrame(), "User with this email already exists", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        Otp OtpFrame = new Otp();
                        OtpFrame.setVisible(true);
                        OtpFrame.pack();
                        OtpFrame.setLocationRelativeTo(null); 
                        this.dispose();
                        
                        // Send OTP to the provided email address
                        JavaMailUtil.sendMail(userEmail);
                        JOptionPane.showMessageDialog(new JFrame(), "OTP has been sent to " + userEmail, "Success", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(SignUp.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }//GEN-LAST:event_signUpBtnActionPerformed

    private void confirmPasswordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_confirmPasswordKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            // User input retrieval
            fullName = fname.getText();
            userEmail = email.getText();
            userPassword = new String(password.getPassword());
            String userConfirmPassword = new String(confirmPassword.getPassword());

            // Hash the password using BCrypt
            String hashedPassword = BCrypt.hashpw(userPassword, BCrypt.gensalt());

            try (Connection con = establishConnection()) {
                if (!userPassword.equals(userConfirmPassword)) {
                    JOptionPane.showMessageDialog(new JFrame(), "Passwords do not match. Please enter the correct password.", "Error", JOptionPane.ERROR_MESSAGE);
                    confirmPassword.requestFocusInWindow();
                
                } else if (userConfirmPassword.isEmpty()) {
                    JOptionPane.showMessageDialog(new JFrame(), "Password is required.", "Error", JOptionPane.ERROR_MESSAGE);
                    confirmPassword.requestFocusInWindow();
                }
                
                else {
                    Otp OtpFrame = new Otp();
                    OtpFrame.setVisible(true);
                    OtpFrame.pack();
                    OtpFrame.setLocationRelativeTo(null); 
                    this.dispose();

                    try {
                        // Send OTP to the provided email address
                        JavaMailUtil.sendMail(userEmail);
                    } catch (Exception ex) {
                        Logger.getLogger(SignUp.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    JOptionPane.showMessageDialog(new JFrame(), "OTP has been sent to " + userEmail, "Success", JOptionPane.INFORMATION_MESSAGE);
                }          
            } catch (ClassNotFoundException | SQLException e) {
                JOptionPane.showMessageDialog(new JFrame(), "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_confirmPasswordKeyPressed
                  
    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton SignInBtn;
    private javax.swing.JPasswordField confirmPassword;
    private javax.swing.JTextField email;
    private javax.swing.JTextField fname;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPasswordField password;
    private javax.swing.JCheckBox showPass;
    private javax.swing.JButton signUpBtn;
    // End of variables declaration//GEN-END:variables
    
    public static String getEmail() {
       return userEmail;
    }
    
    public static String getFname() {
       return fullName;
    }
    
    public static String getPassword() {
       return userPassword;
    }
    
    public static String getHashedPassword() {
       return hashedPassword;
    }
    
    private void setIcon() {
       ImageIcon icon = new ImageIcon(getClass().getResource("bar.png"));
       setIconImage(icon.getImage());
    }
    
    // Database connection
    private Connection establishConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/java_user_database", "root", "");
    }
}
