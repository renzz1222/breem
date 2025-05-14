package homepage;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class About {

    public About() {
        JFrame frame = new JFrame();
        frame.setSize(1200, 800);
        frame.setTitle("Luz Ville Resort");

        JPanel design = new JPanel();
        JPanel bottom = new JPanel();
        JPanel right = new JPanel();
        JPanel left = new JPanel();
        JPanel center = new JPanel();

        design.setBackground(Color.black);
        bottom.setBackground(Color.white);
        right.setBackground(Color.white);
        left.setBackground(Color.white);
        center.setBackground(Color.white);

        design.setPreferredSize(new Dimension(200, 120));
        design.setLayout(new BorderLayout());
        design.setBorder(null);

        bottom.setPreferredSize(new Dimension(200, 100));
        right.setPreferredSize(new Dimension(50, 100));
        left.setPreferredSize(new Dimension(50, 100));

        JPanel top = new JPanel();
        top.setPreferredSize(new Dimension(200, 80));
        top.setBackground(Color.white);

        JPanel top1 = new JPanel();
        JPanel top2 = new JPanel();

        top.add(top1, BorderLayout.EAST);
        top.add(top2, BorderLayout.WEST);

        top1.setPreferredSize(new Dimension(600, 80));
        top2.setPreferredSize(new Dimension(500, 50));

        top1.setBackground(Color.white);
        top2.setBackground(Color.white);

        // Nav Buttons
        JButton amenities = new JButton("AMENITIES");
        amenities.setBorder(null);
        amenities.setBackground(Color.white);
        amenities.setFont(new Font("Mongolian Baiti", Font.PLAIN, 16));
        amenities.setForeground(Color.black);
        amenities.addActionListener(e -> {
            new Amen();
            frame.dispose();
        });

        JButton rates = new JButton("RATES");
        rates.setBorder(null);
        rates.setBackground(Color.white);
        rates.setFont(new Font("Mongolian Baiti", Font.PLAIN, 16));
        rates.setForeground(Color.black);
        rates.addActionListener(e -> {
            new Rates();
            frame.dispose();
        });

        JButton home = new JButton("HOME");
        home.setBorder(null);
        home.setBackground(Color.white);
        home.setFont(new Font("Mongolian Baiti", Font.PLAIN, 16));
        home.setForeground(Color.black);
        home.addActionListener(e -> {
            new Home();
            frame.dispose();
        });

        JButton about = new JButton("<html><u>ABOUT</u></html>");
        about.setBorder(null);
        about.setBackground(Color.white);
        about.setFont(new Font("Mongolian Baiti", Font.PLAIN, 16));
        about.setForeground(Color.black);

        JButton book = new JButton("BOOK NOW");
        book.setBackground(new Color(0x556B2F));
        book.setFont(new Font("Mongolian Baiti", Font.PLAIN, 16));
        book.setForeground(Color.white);
        book.setFocusPainted(false);
        book.setBorderPainted(false);
        book.addActionListener(e -> {
            new Book();
            frame.dispose();
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        buttonPanel.add(amenities);
        buttonPanel.add(rates);
        buttonPanel.add(home);
        buttonPanel.add(about);
        buttonPanel.add(book);
        buttonPanel.setBackground(Color.white);
        buttonPanel.setPreferredSize(new Dimension(500, 30));

        top2.add(buttonPanel, BorderLayout.WEST);

        // === Banner and Content ===
        JPanel center1 = new JPanel();
        JPanel center2 = new JPanel();

        center.add(center1, BorderLayout.NORTH);
        center.add(center2, BorderLayout.NORTH);

        center1.setPreferredSize(new Dimension(1200, 190));
        center2.setPreferredSize(new Dimension(1200, 380));

        center1.setBackground(Color.black);
        center2.setBackground(Color.white);

        JLabel aboutLabel = new JLabel("ABOUT US");
        aboutLabel.setForeground(new Color(0xFFF8DC));
        aboutLabel.setFont(new Font("Poppins", Font.BOLD, 70));
        aboutLabel.setBounds(400, 60, 500, 80);

        
        ImageIcon resort = new ImageIcon("C:\\Users\\AMD\\OneDrive\\Documents\\NetBeansProjects\\HomePage\\src\\homepage\\image\\Amenities.png");
        
        JLabel backgroundLabel = new JLabel(resort);
        backgroundLabel.setBounds(0, 0, 1200, 200);
        backgroundLabel.setLayout(null);

        // Add "ABOUT US" on top of the image
        aboutLabel.setBounds(400, 60, 500, 80);
        backgroundLabel.add(aboutLabel);

        // Add background label to center1
        center1.setLayout(null);
        center1.add(backgroundLabel);
        
        Image imageResort = resort.getImage();
        Image modifiedimageResort = imageResort.getScaledInstance(1200, 200, java.awt.Image.SCALE_SMOOTH);
        resort = new ImageIcon(modifiedimageResort);

        // About Us Text
        JTextArea aboutText = new JTextArea(
            "Luz Ville is a private resort and events place venue located in San Miguel, Hagonoy, Bulacan.\n\n"
          + "It started as a family business by the Arellano Family, where they intended the resort to be a place of relaxation and entertainment for people looking for a refreshing change of pace.\n\n"
          + "Luz Ville resort is a place of wonder where people of all ages can truly have fun and enjoy themselves. "
          + "The resort offers various packages where guests can freely pick which bundle best suits their preferences.\n\n"
          + "The resort’s offers are exceptional and suitable for different occasions—be it birthday celebrations, reunions, holidays, or any event. "
          + "It offers pools for children and adults to enjoy a relaxing and fun experience."
        );
        aboutText.setWrapStyleWord(true);
        aboutText.setLineWrap(true);
        aboutText.setEditable(false);
        aboutText.setFont(new Font("Serif", Font.PLAIN, 18));
        aboutText.setForeground(Color.BLACK);
        aboutText.setBackground(Color.WHITE);
        aboutText.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        JScrollPane scrollPane = new JScrollPane(aboutText);
        scrollPane.setBorder(null);
        scrollPane.setBounds(50, 30, 1100, 320);

        center2.setLayout(null);
        center2.add(scrollPane);

        // === Frame Composition ===
        frame.add(design, BorderLayout.NORTH);
        design.add(top, BorderLayout.SOUTH);
        frame.add(bottom, BorderLayout.SOUTH);
        frame.add(center, BorderLayout.CENTER);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
    }

    public static void main(String[] args) {
        new About();
    }
}