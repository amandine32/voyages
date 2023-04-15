package Login;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import modele.Entites.*;

import javax.swing.*;

import Main.VoyagesMain;



public class LoginForm extends JFrame {
    final private Font mainFont = new Font("Segoe print", Font.BOLD, 18);
    JTextField tfEmail;
    JPasswordField pfPassword;

    public void initialize() {
        
        /*************** Form Panel ***************/
        JLabel lbLoginForm = new JLabel("Bienvenue", SwingConstants.CENTER);
        lbLoginForm.setFont(mainFont);

        JLabel lbEmail = new JLabel("Email");
        lbEmail.setFont(mainFont);

        tfEmail = new JTextField();
        tfEmail.setFont(mainFont);

        JLabel lbPassword = new JLabel("Mot de passe");
        lbPassword.setFont(mainFont);

        pfPassword = new JPasswordField();
        pfPassword.setFont(mainFont);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(0, 1, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        formPanel.add(lbLoginForm);
        formPanel.add(lbEmail);
        formPanel.add(tfEmail);
        formPanel.add(lbPassword);
        formPanel.add(pfPassword);

        /*************** Buttons Panel ***************/
        JButton btnLogin = new JButton("Connexion");
        btnLogin.setFont(mainFont);
        btnLogin.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                String email = tfEmail.getText();
                String password = String.valueOf(pfPassword.getPassword());

                Client user = getAuthenticatedUser(email, password);

                if (user != null) {
                    VoyagesMain mainFrame = new VoyagesMain();
                    mainFrame.instancierVueGenerale();
                    //VoyagesMain voyageMain = new VoyagesMain();
                    mainFrame.setVisible(true);
                    dispose();
                }
                else {
                    JOptionPane.showMessageDialog(LoginForm.this,
                            "Email or Password Invalid",
                            "Try again",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
            
        });

        JButton btnCancel = new JButton("Cancel");
        btnCancel.setFont(mainFont);
        btnCancel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                dispose();
            }
            
        });

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(1, 2, 10, 0));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        buttonsPanel.add(btnLogin);
        buttonsPanel.add(btnCancel);



        /*************** Initialise the frame ***************/
        add(formPanel, BorderLayout.NORTH);
        add(buttonsPanel, BorderLayout.SOUTH);

        setTitle("Login Form");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(400, 500);
        setMinimumSize(new Dimension(350, 450));
        //setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }



    private Client getAuthenticatedUser(String mail_c, String mdp_c) {
        Client user = null;

        final String DB_URL =  "jdbc:mysql://localhost:8889/bddvoyages?autoReconnect=true&useSSL=false";
        final String USERNAME = "root";
        final String PASSWORD = "root";

        try{
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            // Connected to database successfully...

            String sql = "SELECT * FROM client WHERE mail_c=? AND mdp_c=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, mail_c);
            preparedStatement.setString(2, mdp_c);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user = new Client(getDefaultCloseOperation(), sql, sql, sql, sql, sql, sql, sql, sql, sql);
                user.setNomc(resultSet.getString("nomc"));
                user.setMail_c(resultSet.getString("mail_c"));
                user.setMdp_c(resultSet.getString("mdp_c"));
            }

            preparedStatement.close();
            conn.close();

        }catch(Exception e){
            System.out.println("Database connexion failed!");
        }


        return user;
    }


    public static void main(String[] args) {
        LoginForm loginForm = new LoginForm();
        loginForm.initialize();
    }
}
