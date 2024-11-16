package travel.management.system;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;

public class ViewPackage extends JFrame {
    private JPanel contentPane;
    private JLabel l1, l2, l3, l4, l5, l6, l7;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                ViewPackage frame = new ViewPackage("");
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public ViewPackage(String username) {
        setBounds(580, 220, 850, 450);
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("travel/management/system/icons/bookedDetails.jpg"));
        Image i3 = i1.getImage().getScaledInstance(500, 350, Image.SCALE_DEFAULT);
        ImageIcon i2 = new ImageIcon(i3);
        JLabel la1 = new JLabel(i2);
        la1.setBounds(450, 40, 350, 350);
        add(la1);

        JLabel lblName = new JLabel("VIEW PACKAGE DETAILS");
        lblName.setFont(new Font("Yu Mincho", Font.PLAIN, 20));
        lblName.setBounds(88, 11, 350, 53);
        contentPane.add(lblName);

        // Labels for displaying data
        addLabelAndValue("Username:", 35, 70, l1 = new JLabel(), 271);
        addLabelAndValue("Package:", 35, 110, l2 = new JLabel(), 271);
        addLabelAndValue("Number of Persons:", 35, 150, l3 = new JLabel(), 271);
        addLabelAndValue("ID:", 35, 190, l4 = new JLabel(), 271);
        addLabelAndValue("Number:", 35, 230, l5 = new JLabel(), 271);
        addLabelAndValue("Phone:", 35, 270, l6 = new JLabel(), 271);
        addLabelAndValue("Price:", 35, 310, l7 = new JLabel(), 271);

        // Database query to fetch details
        fetchPackageDetails(username);

        JButton btnExit = new JButton("Back");
        btnExit.addActionListener(e -> setVisible(false));
        btnExit.setBounds(160, 350, 120, 30);
        btnExit.setBackground(Color.BLACK);
        btnExit.setForeground(Color.WHITE);
        contentPane.add(btnExit);

        getContentPane().setBackground(Color.WHITE);
    }

    private void addLabelAndValue(String labelText, int x, int y, JLabel valueLabel, int valueX) {
        JLabel label = new JLabel(labelText);
        label.setBounds(x, y, 200, 14);
        contentPane.add(label);

        valueLabel.setBounds(valueX, y, 200, 14);
        contentPane.add(valueLabel);
    }

    private void fetchPackageDetails(String username) {
        String query = "SELECT * FROM bookPackage WHERE username = ?";
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3308/travel_db", "root", "Varun@24");
             PreparedStatement pst = conn.prepareStatement(query)) {

            pst.setString(1, username);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    l1.setText(rs.getString("username"));
                    l2.setText(rs.getString("package"));
                    l3.setText(rs.getString("persons"));
                    l4.setText(rs.getString("id"));
                    l5.setText(rs.getString("number"));
                    l6.setText(rs.getString("phone"));
                    l7.setText(rs.getString("price"));
                } else {
                    JOptionPane.showMessageDialog(this, "No details found for the user: " + username);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
