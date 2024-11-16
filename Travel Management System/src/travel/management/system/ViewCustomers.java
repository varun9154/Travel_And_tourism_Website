package travel.management.system;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import net.proteanit.sql.DbUtils;
import java.sql.*;

public class ViewCustomers extends JFrame {
    private JPanel contentPane;
    private JTable table;
    private JLabel lblAvailability, lblCleanStatus, lblNewLabel, lblNewLabel_1, lblId;
    private Connection conn;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ViewCustomers frame = new ViewCustomers();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public ViewCustomers() throws SQLException {
        // Establish the database connection using Conn class
        conn = new Conn().getConnection(); // Get the connection object from Conn class

        // Setup JFrame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(580, 220, 900, 680);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Adding an image to the frame
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("travel/management/system/icons/viewall.jpg"));
        Image i3 = i1.getImage().getScaledInstance(626, 201, Image.SCALE_DEFAULT);
        ImageIcon i2 = new ImageIcon(i3);
        JLabel l1 = new JLabel(i2);
        l1.setBounds(0, 450, 626, 201);
        add(l1);

        // Set up table
        table = new JTable();
        table.setBounds(0, 40, 900, 350);
        contentPane.add(table);

        // Fetch data from database and populate the table
        try {
            String displayCustomerSQL = "SELECT * FROM customer";
            PreparedStatement pst = conn.prepareStatement(displayCustomerSQL);
            ResultSet rs = pst.executeQuery();
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        // Back button to close the frame
        JButton btnNewButton = new JButton("Back");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        btnNewButton.setBounds(390, 400, 120, 30);
        btnNewButton.setBackground(Color.BLACK);
        btnNewButton.setForeground(Color.WHITE);
        contentPane.add(btnNewButton);

        // Label for table column headers
        lblAvailability = new JLabel("Username");
        lblAvailability.setBounds(10, 15, 69, 14);
        contentPane.add(lblAvailability);

        lblCleanStatus = new JLabel("Id Type");
        lblCleanStatus.setBounds(110, 15, 76, 14);
        contentPane.add(lblCleanStatus);

        lblNewLabel = new JLabel("Number");
        lblNewLabel.setBounds(220, 15, 46, 14);
        contentPane.add(lblNewLabel);

        lblNewLabel_1 = new JLabel("Name");
        lblNewLabel_1.setBounds(320, 15, 76, 14);
        contentPane.add(lblNewLabel_1);

        lblId = new JLabel("Gender");
        lblId.setBounds(420, 15, 90, 14);
        contentPane.add(lblId);

        JLabel lblCountry = new JLabel("Country");
        lblCountry.setBounds(520, 15, 90, 14);
        contentPane.add(lblCountry);

        JLabel lblAddress = new JLabel("Address");
        lblAddress.setBounds(620, 15, 90, 14);
        contentPane.add(lblAddress);

        JLabel lblPhone = new JLabel("Phone");
        lblPhone.setBounds(720, 15, 90, 14);
        contentPane.add(lblPhone);

        JLabel lblEmail = new JLabel("Email");
        lblEmail.setBounds(820, 15, 90, 14);
        contentPane.add(lblEmail);

        getContentPane().setBackground(Color.WHITE);
    }
}
