package travel.management.system;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;

public class UpdateCustomer extends JFrame {
    private JPanel contentPane;
    private JTextField t1, t2, t3, t4, t5, t6, t7, t8, t9;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                UpdateCustomer frame = new UpdateCustomer("");
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public UpdateCustomer(String username) throws SQLException {
        setBounds(580, 220, 850, 550);
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("travel/management/system/icons/update.png"));
        Image i3 = i1.getImage().getScaledInstance(200, 400, Image.SCALE_DEFAULT);
        ImageIcon i2 = new ImageIcon(i3);
        JLabel l1 = new JLabel(i2);
        l1.setBounds(500, 40, 200, 400);
        add(l1);

        JLabel lblName = new JLabel("UPDATE CUSTOMER DETAILS");
        lblName.setFont(new Font("Yu Mincho", Font.PLAIN, 20));
        lblName.setBounds(118, 11, 300, 53);
        contentPane.add(lblName);

        // Input Fields
        t1 = addLabelAndField("Username:", 70, true);
        t2 = addLabelAndField("ID:", 110, false);
        t3 = addLabelAndField("Number:", 150, false);
        t4 = addLabelAndField("Name:", 190, false);
        t5 = addLabelAndField("Gender:", 230, false);
        t6 = addLabelAndField("Country:", 270, false);
        t7 = addLabelAndField("Permanent Address:", 310, false);
        t8 = addLabelAndField("Phone:", 350, false);
        t9 = addLabelAndField("Email:", 390, false);

        // Load existing customer details
        fetchCustomerDetails(username);

        // Update Button
        JButton btnUpdate = new JButton("Update");
        btnUpdate.addActionListener(e -> updateCustomerDetails());
        btnUpdate.setBounds(100, 430, 120, 30);
        btnUpdate.setBackground(Color.BLACK);
        btnUpdate.setForeground(Color.WHITE);
        contentPane.add(btnUpdate);

        // Back Button
        JButton btnBack = new JButton("Back");
        btnBack.addActionListener(e -> setVisible(false));
        btnBack.setBounds(260, 430, 120, 30);
        btnBack.setBackground(Color.BLACK);
        btnBack.setForeground(Color.WHITE);
        contentPane.add(btnBack);

        getContentPane().setBackground(Color.WHITE);
    }

    private JTextField addLabelAndField(String labelText, int y, boolean isEditable) {
        JLabel label = new JLabel(labelText);
        label.setBounds(35, y, 200, 14);
        contentPane.add(label);

        JTextField textField = new JTextField();
        textField.setBounds(271, y, 150, 20);
        textField.setColumns(10);
        textField.setEditable(isEditable);
        contentPane.add(textField);

        return textField;
    }

    private void fetchCustomerDetails(String username) {
        String query = "SELECT * FROM customer WHERE username = ?";
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/travel_db", "root", "password");
             PreparedStatement pst = conn.prepareStatement(query)) {

            pst.setString(1, username);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    t1.setText(rs.getString("username"));
                    t2.setText(rs.getString("id_type"));
                    t3.setText(rs.getString("number"));
                    t4.setText(rs.getString("name"));
                    t5.setText(rs.getString("gender"));
                    t6.setText(rs.getString("country"));
                    t7.setText(rs.getString("address"));
                    t8.setText(rs.getString("phone"));
                    t9.setText(rs.getString("email"));
                } else {
                    JOptionPane.showMessageDialog(this, "No customer found with username: " + username);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void updateCustomerDetails() {
        String query = "UPDATE customer SET id_type = ?, number = ?, name = ?, gender = ?, country = ?, address = ?, phone = ?, email = ? WHERE username = ?";
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/travel_db", "root", "password");
             PreparedStatement pst = conn.prepareStatement(query)) {

            pst.setString(1, t2.getText());
            pst.setString(2, t3.getText());
            pst.setString(3, t4.getText());
            pst.setString(4, t5.getText());
            pst.setString(5, t6.getText());
            pst.setString(6, t7.getText());
            pst.setString(7, t8.getText());
            pst.setString(8, t9.getText());
            pst.setString(9, t1.getText());

            int rowsUpdated = pst.executeUpdate();
            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(this, "Customer details updated successfully!");
                setVisible(false);
            } else {
                JOptionPane.showMessageDialog(this, "Update failed. Please try again.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
