package authsortingvisualizer;

import java.awt.event.KeyEvent;
import javax.swing.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author Zaw Linn Htet
 */
public class Otp extends javax.swing.JFrame {

    /**
     * Creates new form Otp
     */
    public Otp() {
        initComponents();
        setIcon(); // Call the setIcon method to set the frame icon
        setTitle("Sorting Visualizer");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        sendAgainBtn = new javax.swing.JButton();
        submitBtn = new javax.swing.JButton();
        otp = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel10.setFont(new java.awt.Font("Candara Light", 1, 32)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("OTP Confirmation");

        sendAgainBtn.setBackground(new java.awt.Color(102, 153, 255));
        sendAgainBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        sendAgainBtn.setForeground(new java.awt.Color(255, 255, 255));
        sendAgainBtn.setText("Send Again");
        sendAgainBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendAgainBtnActionPerformed(evt);
            }
        });

        submitBtn.setBackground(new java.awt.Color(102, 153, 255));
        submitBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        submitBtn.setForeground(new java.awt.Color(255, 255, 255));
        submitBtn.setText("Submit");
        submitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitBtnActionPerformed(evt);
            }
        });

        otp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                otpKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(111, Short.MAX_VALUE)
                .addComponent(otp, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(sendAgainBtn)
                .addGap(22, 22, 22))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(jLabel10))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(147, 147, 147)
                        .addComponent(submitBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sendAgainBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(otp, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(submitBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void sendAgainBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendAgainBtnActionPerformed
        try {
            // Retrieve the email address entered by the user
            String userEmail = SignUp.getEmail(); // Assuming 'email' is the JTextField where the user enters the email address
           
            // Send OTP to the provided email address
            JavaMailUtil.sendMail(userEmail);
            JOptionPane.showMessageDialog(new JFrame(), "OTP has been sent to " + userEmail, "Success", JOptionPane.INFORMATION_MESSAGE);
            
        } catch (Exception ex) {
            Logger.getLogger(SignUp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_sendAgainBtnActionPerformed

    private void submitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitBtnActionPerformed
        try {           
            // Retrieve the OTP entered by the user
            String userOTP = otp.getText();

            // Check if the OTP is provided
            if (userOTP.isEmpty()) {
                JOptionPane.showMessageDialog(new JFrame(), "OTP is required", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                // Retrieve the OTP generated and sent to the user's email
                String otpSent = JavaMailUtil.getGeneratedOTP(); // Get the generated OTP from JavaMailUtil class

                // Compare the OTPs
                if (userOTP.equals(otpSent)) {
                    // Call the method to create a new account
                    createAccount();
                } else {
                    // OTPs do not match, display error message
                    JOptionPane.showMessageDialog(new JFrame(), "Incorrect OTP. Please enter the correct OTP.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(SignUp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_submitBtnActionPerformed

    private void otpKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_otpKeyPressed
         if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try {           
                // Retrieve the OTP entered by the user
                String userOTP = otp.getText();

                // Check if the OTP is provided
                if (userOTP.isEmpty()) {
                    JOptionPane.showMessageDialog(new JFrame(), "OTP is required", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Retrieve the OTP generated and sent to the user's email
                    String otpSent = JavaMailUtil.getGeneratedOTP(); // Get the generated OTP from JavaMailUtil class

                    // Compare the OTPs
                    if (userOTP.equals(otpSent)) {
                        // Call the method to create a new account
                        createAccount();
                    } else {
                        // OTPs do not match, display error message
                        JOptionPane.showMessageDialog(new JFrame(), "Incorrect OTP. Please enter the correct OTP.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } catch (Exception ex) {
                Logger.getLogger(SignUp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_otpKeyPressed

    private void createAccount() {
        // Retrieve the email address entered by the user
        String userEmail = SignUp.getEmail();
        String fullName = SignUp.getFname();
        String userPassword = SignUp.getPassword();
        String hashedPassword = SignUp.getHashedPassword();
        
        hashedPassword = BCrypt.hashpw(userPassword, BCrypt.gensalt());

        try (Connection con = establishConnection()) {
            // Proceed with registration
            String insertQuery = "INSERT INTO user(full_name, email, password) VALUES (?, ?, ?)";

            try (PreparedStatement insertStmt = con.prepareStatement(insertQuery)) {
                insertStmt.setString(1, fullName);
                insertStmt.setString(2, userEmail);
                insertStmt.setString(3, hashedPassword);
                insertStmt.executeUpdate();

                otp.setText("");

                // Show success message
                JOptionPane.showMessageDialog(null, "New account has been created successfully!");
                
                Login LoginFrame = new Login();
                LoginFrame.setVisible(true);
                LoginFrame.pack();
                LoginFrame.setLocationRelativeTo(null);  
            }
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(new JFrame(), "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * @param args the command line arguments
     */
    private void setIcon() {
        ImageIcon icon = new ImageIcon(getClass().getResource("bar.png"));
        setIconImage(icon.getImage());
    }
    
    // Database connection
    private Connection establishConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/java_user_database", "root", "");
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel10;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField otp;
    private javax.swing.JButton sendAgainBtn;
    private javax.swing.JButton submitBtn;
    // End of variables declaration//GEN-END:variables
}
