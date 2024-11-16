package travel.management.system;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class AddCustomer extends JFrame {
    private JPanel contentPane;
    private JTextField t1, t2, t3, t5, t6, t8;
    private JComboBox<String> comboBox;
    private JRadioButton r1, r2;
    private String username;

    public AddCustomer(String username) {
        this.username = username;
        setBounds(580, 220, 850, 550);
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("travel/management/system/icons/newcustomer.jpg"));
        Image i3 = i1.getImage().getScaledInstance(450, 500, Image.SCALE_DEFAULT);
        ImageIcon i2 = new ImageIcon(i3);
        JLabel l1 = new JLabel(i2);
        l1.setBounds(450, 40, 450, 420);
        add(l1);

        JLabel lblTitle = new JLabel("NEW CUSTOMER FORM");
        lblTitle.setFont(new Font("Yu Mincho", Font.PLAIN, 20));
        lblTitle.setBounds(118, 11, 260, 53);
        contentPane.add(lblTitle);

        JLabel lblUsername = new JLabel("Username :");
        lblUsername.setBounds(35, 70, 200, 14);
        contentPane.add(lblUsername);

        JTextField t7 = new JTextField(username);
        t7.setBounds(271, 70, 150, 20);
        t7.setEditable(false); // Set to non-editable since it's fetched from the session
        contentPane.add(t7);

        JLabel lblId = new JLabel("ID :");
        lblId.setBounds(35, 110, 200, 14);
        contentPane.add(lblId);

        comboBox = new JComboBox<>(new String[]{"Passport", "Aadhar Card", "Voter Id", "Driving license"});
        comboBox.setBounds(271, 110, 150, 20);
        contentPane.add(comboBox);

        JLabel lblNumber = new JLabel("Number :");
        lblNumber.setBounds(35, 150, 200, 14);
        contentPane.add(lblNumber);

        t1 = new JTextField();
        t1.setBounds(271, 150, 150, 20);
        contentPane.add(t1);

        JLabel lblName = new JLabel("Name :");
        lblName.setBounds(35, 190, 200, 14);
        contentPane.add(lblName);

        t2 = new JTextField();
        t2.setBounds(271, 190, 150, 20);
        contentPane.add(t2);

        JLabel lblGender = new JLabel("Gender :");
        lblGender.setBounds(35, 230, 200, 14);
        contentPane.add(lblGender);

        r1 = new JRadioButton("Male");
        r1.setBounds(271, 230, 70, 20);
        contentPane.add(r1);

        r2 = new JRadioButton("Female");
        r2.setBounds(350, 230, 100, 20);
        contentPane.add(r2);

        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(r1);
        genderGroup.add(r2);

        JLabel lblCountry = new JLabel("Country :");
        lblCountry.setBounds(35, 270, 200, 14);
        contentPane.add(lblCountry);

        t3 = new JTextField();
        t3.setBounds(271, 270, 150, 20);
        contentPane.add(t3);

        JLabel lblAddress = new JLabel("Permanent Address :");
        lblAddress.setBounds(35, 310, 200, 14);
        contentPane.add(lblAddress);

        t5 = new JTextField();
        t5.setBounds(271, 310, 150, 20);
        contentPane.add(t5);

        JLabel lblPhone = new JLabel("Phone :");
        lblPhone.setBounds(35, 350, 200, 14);
        contentPane.add(lblPhone);

        t6 = new JTextField();
        t6.setBounds(271, 350, 150, 20);
        contentPane.add(t6);

        JLabel lblEmail = new JLabel("Email :");
        lblEmail.setBounds(35, 390, 200, 14);
        contentPane.add(lblEmail);

        t8 = new JTextField();
        t8.setBounds(271, 390, 150, 20);
        contentPane.add(t8);

        JButton btnAdd = new JButton("Add");
        btnAdd.setBounds(100, 430, 120, 30);
        btnAdd.addActionListener(e -> addCustomerToDatabase(username));
        contentPane.add(btnAdd);

        JButton btnBack = new JButton("Back");
        btnBack.setBounds(260, 430, 120, 30);
        btnBack.addActionListener(e -> setVisible(false));
        contentPane.add(btnBack);

        contentPane.setBackground(Color.WHITE);
    }

    private void addCustomerToDatabase(String username) {
        String idType = (String) comboBox.getSelectedItem();
        String idNumber = t1.getText();
        String name = t2.getText();
        String gender = r1.isSelected() ? "Male" : (r2.isSelected() ? "Female" : null);
        String country = t3.getText();
        String address = t5.getText();
        String phone = t6.getText();
        String email = t8.getText();

        if (gender == null) {
            JOptionPane.showMessageDialog(this, "Please select a gender.");
            return;
        }

        try {
            Conn c = new Conn();
            String query = "INSERT INTO customer (username, id_type, number, name, gender, country, address, phone, email) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = c.c.prepareStatement(query);
            pst.setString(1, username);
            pst.setString(2, idType);
            pst.setString(3, idNumber);
            pst.setString(4, name);
            pst.setString(5, gender);
            pst.setString(6, country);
            pst.setString(7, address);
            pst.setString(8, phone);
            pst.setString(9, email);

            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Customer added successfully!");
            setVisible(false);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error adding customer: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new AddCustomer("testUser").setVisible(true);
    }
}
