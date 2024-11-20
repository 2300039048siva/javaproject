

import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import javax.swing.*;


public class SportsRegistration {
    public JFrame frame;
    private final Image backgroundImage;
    private JTextField t1, t2, t3;
    private JRadioButton r1, r2;
    private JComboBox<String> cb, cb2, cb3, cbox,p1,p2;

    public SportsRegistration() {
        backgroundImage = new ImageIcon("C:\\Users\\shiva\\OneDrive\\Desktop\\wallpaper.jpg").getImage(); 
        frame = new JFrame("SPORTS REGISTRATION APPLICATION");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 800);
        
        showHomeScreen();
    }

    public SportsRegistration(Image backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    private JPanel createBackgroundPanel() {
        return new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
    }

    private void showHomeScreen() {
        JPanel panel = createBackgroundPanel();
        panel.setLayout(null);
        JLabel welcomeLabel = new JLabel("WELCOME TO SPORTS REGISTRATION!");
        welcomeLabel.setBounds(300, 1, 600, 90);
        welcomeLabel.setForeground(Color.GRAY);
        welcomeLabel.setFont(new Font("Serif", Font.BOLD, 30));
        panel.add(welcomeLabel);
        
        JButton register = new JButton("REGISTRATION");
        register.setBounds(500, 300, 200, 40);
        register.setBackground(Color.gray);
        register.addActionListener(e -> showRegistrationForm());
        panel.add(register);  
        JButton adminButton=new JButton("ADMIN");
        adminButton.setBounds(1000,20,75,75);
        adminButton.setBackground(Color.gray);
        

        adminButton.addActionListener(e -> new admin());
        panel.add(adminButton);
    
        frame.getContentPane().removeAll();
        frame.getContentPane().add(panel);
        frame.revalidate();
        frame.repaint();
    }

    private void showRegistrationForm() {
        JPanel panel = createBackgroundPanel();
        panel.setOpaque(false);
        panel.setLayout(null);
        JLabel l1 = new JLabel("SPORTS REGISTRATION");
        l1.setBounds(300, 1, 400, 100);
        l1.setForeground(Color.BLUE);
        l1.setFont(new Font("Serif", Font.BOLD, 30));
        panel.add(l1);

        JLabel l2 = new JLabel("STUDENT NAME:");
        l2.setBounds(100, 100, 200, 40);
        panel.add(l2);

        JLabel l3 = new JLabel("STUDENT ID:");
        l3.setBounds(100, 150, 200, 40);
        panel.add(l3);

        JLabel l4 = new JLabel("MOBILE NUMBER:");
        l4.setBounds(100, 200, 200, 40);
        panel.add(l4);

        JLabel l5 = new JLabel("PARTICIPATION:");
        l5.setBounds(100, 250, 100, 40);
        panel.add(l5);

        t1 = new JTextField();
        t1.setBounds(400, 100, 300, 40);
        panel.add(t1);

        t2 = new JTextField();
        t2.setBounds(400, 150, 300, 40);
        panel.add(t2);

        t3 = new JTextField();
        t3.setBounds(400, 200, 300, 40);
        panel.add(t3);

        r1 = new JRadioButton("SINGLE");
        r1.setBounds(400, 250, 80, 30);
        panel.add(r1);

        r2 = new JRadioButton("DOUBLE");
        r2.setBounds(600, 250, 80, 30);
        panel.add(r2);

        ButtonGroup bg = new ButtonGroup();
        bg.add(r1);
        bg.add(r2);

        JLabel l6 = new JLabel("SELECT THE SPORT:");
        l6.setBounds(100, 300, 300, 40);
        panel.add(l6);

        String[] sports = { "SELECT SPORT", "CRICKET", "KABBADI", "FOOTBALL", "VOLLEYBALL", "TENNIS", "BADMINTON" };
        cb = new JComboBox<>(sports);
        cb.setBounds(400, 300, 400, 40);
        cb.setVisible(false);
        panel.add(cb);

        String[] sports2 = { "SELECT THE 1ST SPORT", "CRICKET", "KABBADI", "FOOTBALL", "VOLLEYBALL", "TENNIS", "BADMINTON" };
        cb2 = new JComboBox<>(sports2);
        cb2.setBounds(400, 300, 200, 40);
        cb2.setVisible(false);
        panel.add(cb2);

        String[] sports3 = { "SELECT THE 2ND SPORT", "CRICKET", "KABBADI", "FOOTBALL", "VOLLEYBALL", "TENNIS", "BADMINTON" };
        cb3 = new JComboBox<>(sports3);
        cb3.setBounds(601, 300, 200, 40);
        cb3.setVisible(false);
        panel.add(cb3);

        r1.addActionListener(a -> {
            cb.setVisible(true);
            cb2.setVisible(false);
            cb3.setVisible(false);
        });

        r2.addActionListener(a -> {
            cb.setVisible(false);
            cb2.setVisible(true);
            cb3.setVisible(true);
        });
        

        JButton submit = new JButton("SUBMIT");
        submit.setBounds(300, 500, 200, 60);
        submit.setBackground(Color.green);
        submit.addActionListener(e -> {
            if (isFormValid()) {
                insertDataIntoDatabase();
                showPaymentScreen();
            }
        });

        
        panel.add(submit);
        frame.getContentPane().removeAll();
        frame.getContentPane().add(panel);
        frame.revalidate();
        frame.repaint();
    }

    

    public void showPaymentScreen() {
        JPanel panel = createBackgroundPanel();
        panel.setLayout(null);

        JLabel label = new JLabel("PAYMENT BLOCK");
        label.setForeground(Color.BLUE);
        label.setFont(new Font("Serif", Font.BOLD, 30));
        label.setBounds(300, 1, 500, 200);
        panel.add(label);

        JLabel method = new JLabel("PAYMENT METHOD :");
        method.setBounds(200, 300, 180, 40);
        panel.add(method);

        String[] paymentmethod = { "SELECT THE PAYMENT METHOD", "UPI-PAY", "E-WALLETS", "CASH" };
        cbox = new JComboBox<>(paymentmethod);
        cbox.setBounds(400, 300, 400, 40);
        panel.add(cbox);
        cbox.addActionListener((actionEvent) -> {
            String selectedItem = (String) cbox.getSelectedItem();
            if (selectedItem.equals("E-WALLETS")) {
                p1.setVisible(true);
            }else if (selectedItem.equals("UPI-PAY")) {
                p2.setVisible(true);
            }
        });
        
        String[] method1 = { "SELECT E-WALLET METHOD", "CREDIT CARD", "DEBIT CARD", "BANKACCOUNT INFO" };
        p1 = new JComboBox<>(method1);
        p1.setBounds(400, 350, 200, 40);
        p1.setVisible(false);
        panel.add(p1);
        String[] method2 = { "SELECT UPI METHOD", "PHONEPE", "GOOGLEPAY", "PAYTM", "BHARATPE" };
        p2 = new JComboBox<>(method2);
        p2.setBounds(400, 350, 200, 40);
        JButton confirmButton = new JButton("Confirm Payment");
        confirmButton.setBounds(300, 500, 200, 40);
        p2.setVisible(false);
        panel.add(p2);
        confirmButton.setBackground(Color.green);
        confirmButton.addActionListener(e -> payverify());
        confirmButton.addActionListener(e -> showConfirmationScreen());

        JButton back = new JButton("BACK");
        back.setBounds(1, 1, 100, 40);
        panel.add(back);
        back.addActionListener(e -> showRegistrationForm());

        panel.add(confirmButton);
        frame.getContentPane().removeAll();
        frame.getContentPane().add(panel);
        frame.revalidate();
        frame.repaint();
    }

    private void showConfirmationScreen() {
        JPanel panel = createBackgroundPanel();
        panel.setLayout(null);

        JLabel confirmationLabel = new JLabel("REGISTRATION SUCCESSFUL!");
        confirmationLabel.setBounds(300, 1, 600, 100);
        confirmationLabel.setFont(new Font("Serif", Font.BOLD, 30));
        panel.add(confirmationLabel);

        JButton homeButton = new JButton("BACK TO HOME");
        homeButton.setBounds(400, 500, 150, 40);
        homeButton.addActionListener(e -> showHomeScreen());
        panel.add(homeButton);

        frame.getContentPane().removeAll();
        frame.getContentPane().add(panel);
        frame.revalidate();
        frame.repaint();
    }
    public boolean isFormValid() {
        if (t1.getText().isEmpty() || t2.getText().isEmpty() || t3.getText().isEmpty() || (!r1.isSelected() && !r2.isSelected())) {
            JOptionPane.showMessageDialog(frame, "FILL ALL THE FORMS", "ERROR", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    public void insertDataIntoDatabase() throws NumberFormatException {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver"); 
            try (Connection c = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "system");
                 PreparedStatement ps = c.prepareStatement("INSERT INTO SportsRegistration(id, name, mobileno, participation_type, sport, sport_2) VALUES (?, ?,? ,?, ?, ?)"))
            {

                String name = t1.getText();
                long id = Long.parseLong(t2.getText());
                long mobileno =Long.parseLong( t3.getText());
                String participation_type = r1.isSelected() ? "Single" : "Double";
                String sport,sport_2;
                
                ps.setLong(1, id);
                ps.setString(2, name);
                
                ps.setLong(3, mobileno);
                ps.setString(4, participation_type);
                if(r1.isSelected())
                {
                    sport=(String)cb.getSelectedItem();
                    ps.setString(5,sport);
                    sport_2=(String)cb.getSelectedItem();
                    ps.setNull(6, Types.VARCHAR);
                    

                }
                if(r2.isSelected())
                {
                    sport=(String)cb2.getSelectedItem();
                    ps.setString(5,sport);
                    sport_2=(String)cb3.getSelectedItem();
                    ps.setString(6, sport_2);
                     

                }
               
                int rowsInserted =ps.executeUpdate();
                if(rowsInserted>0)
                {
                JOptionPane.showMessageDialog(frame, "DETAILS FILLED SUCCESSFUL!");
                }
                c.close();

            } 
            catch (SQLException e)
            {
                e.printStackTrace();
                JOptionPane.showMessageDialog(frame, "ERROR WHILE ENTERING THE DATA IN DATABASE!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (ClassNotFoundException e)
         {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "JDBC Driver not found!", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    public void payverify() throws NumberFormatException
    {
        try
        {
        
            Class.forName("oracle.jdbc.driver.OracleDriver"); 
            try (Connection c2 = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "system");
                 PreparedStatement ps1 = c2.prepareStatement("UPDATE SportsRegistration SET payment = ? WHERE id = ?"))
            {
                String payment=(String)cbox.getSelectedItem();
                ps1.setString(1,payment);
                long id = Long.parseLong(t2.getText());
                ps1.setLong(2, id);
                
                int rowsUpdated = ps1.executeUpdate();
                if (rowsUpdated > 0) {
                    JOptionPane.showMessageDialog(frame, "Payment updated successfully!");
                } else {
                    JOptionPane.showMessageDialog(frame, "No record found with the provided ID.", "Error", JOptionPane.ERROR_MESSAGE);
                }    

                c2.close();
                
            }
            catch (SQLException e)
            {
                e.printStackTrace();
                JOptionPane.showMessageDialog(frame, "ERROR WHILE ENTERING THE DATA IN DATABASE!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }catch (ClassNotFoundException e)
        {
           e.printStackTrace();
           JOptionPane.showMessageDialog(frame, "JDBC Driver not found!", "Error", JOptionPane.ERROR_MESSAGE);
       }
        
        
   }
  

    





    public static void main(String[] args) {
        SportsRegistration app = new SportsRegistration();
        
        app.frame.setVisible(true);
    }
}
class admin 
{
    private JFrame frame;
    private final Image backgroundImage;
    private JTextField t1;
    private JPasswordField t2;
    public admin()
    {
        
        backgroundImage = new ImageIcon("C:\\Users\\shiva\\OneDrive\\Desktop\\wallpaper.jpg").getImage(); 
        frame = new JFrame("ADMINISTRAIION");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setVisible(true);
        login();
        
    }
    private JPanel createBackgroundPanel() {
        return new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
    }
    public void login()
    {
        JPanel panel=createBackgroundPanel();
        panel.setLayout(null);
        JLabel label = new JLabel("ADMIN LOGIN");
        label.setBounds(150, 1, 400, 90);
        label.setForeground(Color.GRAY);
        label.setFont(new Font("Serif", Font.BOLD, 30));
        panel.add(label);
        JLabel l1=new JLabel("NAME:");
        l1.setBounds(100, 100, 200, 40);
        panel.add(l1);

        JLabel l2 = new JLabel("PASSWORD:");
        l2.setBounds(100, 150, 200, 40);
        panel.add(l2);
        t1 = new JTextField();
        t1.setBounds(200, 100, 200, 40);
        panel.add(t1);

        t2 = new JPasswordField();
        t2.setBounds(200, 150, 200, 40);
        panel.add(t2);
        JButton button=new JButton("LOGIN");
        button.setBounds(150,200,100,40);
        panel.add(button);
        button.addActionListener(e ->
        dataverify()
        );
        frame.getContentPane().removeAll();
        frame.getContentPane().add(panel);
        frame.revalidate();
        frame.repaint();
    }
    public void home()
    {
        JPanel panel=createBackgroundPanel();
        panel.setLayout(null);
        JButton button=new JButton("LOGIN DETAILS");
        button.setBounds(200,100,200,40);
        panel.add(button);
        button.addActionListener(e -> fetchData());
        frame.getContentPane().removeAll();
        frame.getContentPane().add(panel);
        frame.revalidate();
        frame.repaint();
        
    }
    public void fetchData() 
    {
        JPanel panel=createBackgroundPanel();
        panel.setLayout(null);
        try
        {
            Class.forName("oracle.jdbc.driver.OracleDriver"); 
                try (Connection c4 = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "system");
                     PreparedStatement ps4 = c4.prepareStatement("SELECT * FROM SportsRegistration"))
                {
                    System.out.println("ID:  NAME:         MOBILE NUMBER:  PARTICPATION TYPE: SPORT1:  SPORT2:   PAYMENT:    ");
                    ResultSet rs = ps4.executeQuery();
                    while (rs.next()) {
                        String name = rs.getString("name");
                        long id = rs.getLong("id");
                        long mobileno = rs.getLong("mobileno");
                        String participation_type=rs.getString("participation_type");
                        String sport=rs.getString("sport");
                        String sport_2=rs.getString("sport_2");
                        String payment=rs.getString("payment");
        
                        System.out.println(" " + id + "\t " + name + " \t" + mobileno+ " \t"+participation_type+" \t"+sport+" \t"+sport_2+" \t"+payment);
                    }
                 
            
                    c4.close();
                }catch (SQLException e)
                {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(frame, "NO DETAILS FETCHED", "Error", JOptionPane.ERROR_MESSAGE);
                }
                
        }catch (ClassNotFoundException e)
        {
           e.printStackTrace();
           JOptionPane.showMessageDialog(frame, "JDBC Driver not found!", "Error", JOptionPane.ERROR_MESSAGE);
       }
       frame.getContentPane().removeAll();
        frame.getContentPane().add(panel);
        frame.revalidate();
        frame.repaint();
    }

    public void dataverify()
    {
        
        try
        {
            Class.forName("oracle.jdbc.driver.OracleDriver"); 
            try (Connection c2 = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "system");
                 PreparedStatement ps3 = c2.prepareStatement("SELECT * FROM owner WHERE name = ? AND password = ?"))
            {
                String name=t1.getText();
                String password=new String(t2.getPassword());
                ps3.setString(1, t1.getText());
                ps3.setString(2, new String(t2.getPassword()));
                ResultSet rs = ps3.executeQuery();
                
                if (rs.next()) {
                    JOptionPane.showMessageDialog(frame, "Login Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    home();
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid Username or Password", "Error", JOptionPane.ERROR_MESSAGE);
                }
                
                c2.close();
                
    
            }
            catch (SQLException e)
            {
                e.printStackTrace();
                JOptionPane.showMessageDialog(frame, "INVALID USERNAME OR PASSWORD", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }catch (ClassNotFoundException e)
        {
           e.printStackTrace();
           JOptionPane.showMessageDialog(frame, "JDBC Driver not found!", "Error", JOptionPane.ERROR_MESSAGE);
       }
    
    }
    
    
}



    