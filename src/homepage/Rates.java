package homepage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Rates {

    public Rates() {
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

        // === TOP NAV BAR ===
        JPanel top = new JPanel();
        top.setPreferredSize(new Dimension(200, 80));
        top.setBackground(Color.white);

        JPanel top1 = new JPanel(null);
        JPanel top2 = new JPanel();
        top1.setPreferredSize(new Dimension(600, 80));
        top2.setPreferredSize(new Dimension(500, 50));
        top1.setBackground(Color.white);
        top2.setBackground(Color.white);
        
             ImageIcon iconLogo = new ImageIcon("C:\\Users\\AMD\\OneDrive\\Documents\\NetBeansProjects\\2ndSemProject\\src\\homepage\\image\\small.png");
        Image imageResort99 = iconLogo.getImage();
        Image modifiedimageResort99 = imageResort99.getScaledInstance(80, 65, java.awt.Image.SCALE_SMOOTH);
        iconLogo = new ImageIcon(modifiedimageResort99);

        JLabel iconicLogo = new JLabel();
        iconicLogo.setIcon(iconLogo);
        iconicLogo.setBounds(50,6,80, 65);
        top1.add(iconicLogo);
        
        top.add(top1);
        top.add(top2);

        // navigation buttons
        Font navFont = new Font("Mongolian Baiti", Font.ROMAN_BASELINE, 16);

        JButton amenities = new JButton("AMENITIES");
        amenities.setBorder(null);
        amenities.setBackground(Color.white);
        amenities.setFont(navFont);
        amenities.setForeground(Color.black);
        amenities.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Amen();
                frame.dispose();
            }
        });

        JButton rates = new JButton("<html><u>RATES</u></html>");
        rates.setBorder(null);
        rates.setBackground(Color.white);
        rates.setFont(navFont);
        rates.setForeground(Color.black);
        rates.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Rates();
                frame.dispose();
            }
        });

        JButton home = new JButton("HOME");
        home.setBorder(null);
        home.setBackground(Color.white);
        home.setFont(navFont);
        home.setForeground(Color.black);
        home.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Home();
                frame.dispose();
            }
        });

        JButton about = new JButton("ABOUT");
        about.setBorder(null);
        about.setBackground(Color.white);
        about.setFont(navFont);
        about.setForeground(Color.black);
        about.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new About();
                frame.dispose();
            }
        });

        JButton book = new JButton("BOOK NOW");
        book.setBackground(new Color(0x556B2F));
        book.setFont(navFont);
        book.setForeground(Color.white);
        book.setFocusPainted(false);
        book.setBorderPainted(false);
        book.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Book();
                frame.dispose();
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        buttonPanel.add(amenities);
        buttonPanel.add(rates);
        buttonPanel.add(home);
        buttonPanel.add(about);
        buttonPanel.add(book);
        buttonPanel.setBackground(Color.white);
        buttonPanel.setPreferredSize(new Dimension(500, 30));
        top2.add(buttonPanel);

        // === MAIN IMAGE ===
        ImageIcon resort = new ImageIcon(
            "C:\\Users\\AMD\\OneDrive\\Documents\\NetBeansProjects\\HomePage\\src\\homepage\\image\\Logo4.png"
        );
        Image imageResort = resort.getImage();
        Image modifiedImageResort = imageResort.getScaledInstance(1086, 575, Image.SCALE_SMOOTH);
        resort = new ImageIcon(modifiedImageResort);
        JLabel picture = new JLabel(resort);

        // === CENTER PANEL LAYOUT ===
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
        center.add(picture);
        center.add(Box.createRigidArea(new Dimension(0, 5)));

        // Fonts & color
        Font headerFont  = new Font("Mongolian Baiti", Font.BOLD, 24);
        Font sectionFont = new Font("Mongolian Baiti", Font.PLAIN, 16);
        Font boldFont    = new Font("Mongolian Baiti", Font.BOLD, 16);
        Color lightGreen = new Color(0xD8EFD8);

        // Data arrays
        String[][] leftText = {
            { "DAY TOUR",     "9:00 AM - 5:00PM"  },
            { "DAY TO NIGHT", "11:00 AM - 8:00PM" },
            { "OVERNIGHT",    "6:00 PM - 8:00 AM" }
        };
        String[][] rightText = {
            { "₱ 5,000 / WEEKDAYS<br>₱ 6,000 / WEEKENDS & HOLIDAYS", "30 PAX" },
            { "₱ 5,000 / WEEKDAYS<br>₱ 6,000 / WEEKENDS & HOLIDAYS", "30 PAX" },
            { "₱ 7,000 / WEEKDAYS<br>₱ 8,000 / WEEKENDS & HOLIDAYS", "30 PAX" }
        };
        String[] extraHtml = {
            "<html><b>IN EXCESS - Day Tour 9AM - 5PM</b><br>"
          + "• 100/head Adult/Teens<br>"
          + "• 75/head 9-12y/o SC/PWD<br>"
          + "• 50/head 8 y/o Below</html>",
            "<html><b>IN EXCESS - Night Tour 6PM on</b><br>"
          + "• 150/head Adult/Teens<br>"
          + "• 100/head 9-12y/o SC/PWD<br>"
          + "• 76/head 8 y/o Below</html>"
        };

        // REGULAR RATES header
JLabel lblRegular = new JLabel("REGULAR RATES", SwingConstants.CENTER);
lblRegular.setFont(headerFont);
lblRegular.setOpaque(true);
lblRegular.setBackground(new Color(0x556B2F));
lblRegular.setForeground(Color.white);
lblRegular.setPreferredSize(new Dimension(0,  50));      // keep fixed height
lblRegular.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
lblRegular.setAlignmentX(Component.CENTER_ALIGNMENT);

center.add(lblRegular);
center.add(Box.createRigidArea(new Dimension(0, 15)));


        // --- REGULAR RATES GRID (3×2) ---
        JPanel regularGrid = new JPanel(new GridLayout(3, 2, 20, 20));
        regularGrid.setBackground(Color.white);
        Dimension cellSize = new Dimension(400, 120);

        for (int i = 0; i < 3; i++) {
            // Left cell
            JPanel cellL = new JPanel(new GridLayout(2, 1, 0, 5));
            cellL.setBackground(lightGreen);
            cellL.setPreferredSize(cellSize);
            JLabel t1 = new JLabel(leftText[i][0], SwingConstants.CENTER);
            t1.setFont(boldFont);
            JLabel t2 = new JLabel(leftText[i][1], SwingConstants.CENTER);
            t2.setFont(sectionFont);
            cellL.add(t1);
            cellL.add(t2);
            regularGrid.add(cellL);

            // Right cell
            JPanel cellR = new JPanel(new GridLayout(2, 1, 0, 5));
            cellR.setBackground(lightGreen);
            cellR.setPreferredSize(cellSize);
            JLabel p1 = new JLabel(
                "<html><div style='text-align:center;'>" + rightText[i][0] + "</div></html>",
                SwingConstants.CENTER
            );
            p1.setFont(sectionFont);
            JLabel p2 = new JLabel(rightText[i][1], SwingConstants.CENTER);
            p2.setFont(boldFont);
            cellR.add(p1);
            cellR.add(p2);
            regularGrid.add(cellR);
        }
        center.add(regularGrid);
        center.add(Box.createRigidArea(new Dimension(0, 30)));


// --- ADDITIONAL COST HEADER ---
JLabel lblAdd = new JLabel("ADDITIONAL COST", SwingConstants.CENTER);
lblAdd.setFont(headerFont);
lblAdd.setOpaque(true);
lblAdd.setBackground(new Color(0x556B2F));
lblAdd.setForeground(Color.white);

lblAdd.setPreferredSize(new Dimension(0, 50));
lblAdd.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
lblAdd.setAlignmentX(Component.CENTER_ALIGNMENT);

center.add(lblAdd);
center.add(Box.createRigidArea(new Dimension(0, 15)));



        // --- ADDITIONAL COST GRID (1×2) ---
        JPanel extraGrid = new JPanel(new GridLayout(1, 2, 20, 0));
        extraGrid.setBackground(Color.white);
        Dimension extraSize = new Dimension(400, 140);

        for (String html : extraHtml) {
            JPanel ex = new JPanel(new BorderLayout());
            ex.setBackground(lightGreen);
            ex.setPreferredSize(extraSize);
            JLabel L = new JLabel(html, SwingConstants.CENTER);
            L.setFont(sectionFont);
            ex.add(L, BorderLayout.CENTER);
            extraGrid.add(ex);
        }
        center.add(extraGrid);

        // === ASSEMBLE FRAME ===
        frame.add(design, BorderLayout.NORTH);
        design.add(top, BorderLayout.SOUTH);
        frame.add(bottom, BorderLayout.SOUTH);
        frame.add(right, BorderLayout.EAST);
        frame.add(left, BorderLayout.WEST);
        frame.add(center, BorderLayout.CENTER);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new Home();
    }
}



