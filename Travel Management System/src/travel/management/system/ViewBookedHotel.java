package travel.management.system;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.sql.*;

public class ViewBookedHotel extends JFrame {
    private JPanel contentPane;
    private JLabel lblUsername, lblName, lblPersons, lblDays, lblRoomType, lblFood, lblId, lblNumber, lblPhone, lblCost;
    private Connection conn;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ViewBookedHotel frame = new ViewBookedHotel("testUser"); // Replace "testUser" with actual username
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public ViewBookedHotel(String username) {
        // Establish the database connection using Conn class
        conn = new Conn().getConnection(); // Get the connection object from Conn class

        setBounds(580, 220, 850, 600);
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitle = new JLabel("VIEW BOOKED HOTEL DETAILS");
        lblTitle.setFont(new Font("Yu Mincho", Font.PLAIN, 20));
        lblTitle.setBounds(88, 11, 350, 53);
        contentPane.add(lblTitle);

        setupLabels();
        loadHotelDetails(username);

        JButton btnBack = new JButton("Back");
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        btnBack.setBounds(160, 470, 120, 30);
        btnBack.setBackground(Color.BLACK);
        btnBack.setForeground(Color.WHITE);
        contentPane.add(btnBack);

        // Add an image
        ImageIcon icon = new ImageIcon(getClass().getResource("/travel/management/system/icons/bookedDetails.jpg"));
        Image img = icon.getImage().getScaledInstance(350, 350, Image.SCALE_DEFAULT);
        ImageIcon scaledIcon = new ImageIcon(img);
        JLabel imageLabel = new JLabel(scaledIcon);
        imageLabel.setBounds(450, 50, 350, 350);
        contentPane.add(imageLabel);

        getContentPane().setBackground(Color.WHITE);
    }

    private void setupLabels() {
        String[] labelNames = {
                "Username:", "Name:", "Number of Persons:", "Number of Days:",
                "AC / Non-AC:", "Food Included (Yes/No):", "ID:", "Number:",
                "Phone:", "Cost:"
        };

        JLabel[] labels = new JLabel[labelNames.length];
        JLabel[] valueLabels = new JLabel[labelNames.length];

        int yOffset = 70;
        for (int i = 0; i < labelNames.length; i++) {
            labels[i] = new JLabel(labelNames[i]);
            labels[i].setBounds(35, yOffset, 200, 14);
            contentPane.add(labels[i]);

            valueLabels[i] = new JLabel();
            valueLabels[i].setBounds(271, yOffset, 200, 14);
            contentPane.add(valueLabels[i]);

            yOffset += 40;
        }

        lblUsername = valueLabels[0];
        lblName = valueLabels[1];
        lblPersons = valueLabels[2];
        lblDays = valueLabels[3];
        lblRoomType = valueLabels[4];
        lblFood = valueLabels[5];
        lblId = valueLabels[6];
        lblNumber = valueLabels[7];
        lblPhone = valueLabels[8];
        lblCost = valueLabels[9];
    }

    private void loadHotelDetails(String username) {
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            // Fetch hotel booking details for the given username
            String query = "SELECT * FROM bookHotel WHERE username = ?";
            pst = conn.prepareStatement(query);
            pst.setString(1, username);
            rs = pst.executeQuery();

            if (rs.next()) {
                // Set the labels with values from the database
                lblUsername.setText(rs.getString("username"));
                lblName.setText(rs.getString("name"));
                lblPersons.setText(rs.getString("persons"));
                lblDays.setText(rs.getString("days"));
                lblRoomType.setText(rs.getString("room_type"));
                lblFood.setText(rs.getString("food"));
                lblId.setText(rs.getString("id"));
                lblNumber.setText(rs.getString("number"));
                lblPhone.setText(rs.getString("phone"));
                lblCost.setText(rs.getString("cost"));
            } else {
                JOptionPane.showMessageDialog(null, "No booking found for this user.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error fetching data from database.");
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (conn != null) conn.close(); // Close the connection
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
