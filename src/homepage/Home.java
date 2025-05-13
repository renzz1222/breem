package homepage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Home {

    public Home() {
        
        JFrame frame = new JFrame();
        frame.setSize(1200,800);
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
        
        //=======================================================/
        JButton amenities = new JButton("AMENITIES");
        amenities.setBorder(null);
        amenities.setBackground(Color.white);
        amenities.setFont(new Font("Mongolian Baiti", Font.ROMAN_BASELINE, 16));
        amenities.setForeground(Color.black);
       
        amenities.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e){
            new Amen();
            frame.dispose();
          
            
        }
        });
        
        JButton rates = new JButton("RATES");
        rates.setBorder(null);
        rates.setBackground(Color.white);
        rates.setFont(new Font("Mongolian Baiti", Font.ROMAN_BASELINE, 16));
        rates.setForeground(Color.black);
        
        rates.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e){
            new Rates();
            frame.dispose();
          
            
        }
        });
        
        JButton home = new JButton("<html><u>HOME</u></html>");
        home.setBorder(null);
        home.setBackground(Color.white);
        home.setFont(new Font("Mongolian Baiti", Font.ROMAN_BASELINE, 16));
        home.setForeground(Color.black);
        
        
        
        
        JButton about = new JButton("ABOUT");
        about.setBorder(null);
        about.setBackground(Color.white);
        about.setFont(new Font("Mongolian Baiti", Font.ROMAN_BASELINE, 16));
        about.setForeground(Color.black);
        
        about.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e){
            new About();
            frame.dispose();
          
            
        }
        });
        
        JButton book = new JButton("BOOK NOW");
        book.setBackground(new Color(0x556B2F));
        book.setFont(new Font("Mongolian Baiti", Font.ROMAN_BASELINE, 16));
        book.setForeground(Color.white);
        book.setFocusPainted(false);
        book.setBorderPainted(false);
        book.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e){
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
            
        top2.add(buttonPanel, BorderLayout.WEST);

        ImageIcon resort = new ImageIcon("C:\\Users\\AMD\\OneDrive\\Documents\\NetBeansProjects\\HomePage\\src\\homepage\\image\\Logo.png");

        Image imageResort = resort.getImage();
        Image modifiedimageResort = imageResort.getScaledInstance(1086, 575, java.awt.Image.SCALE_SMOOTH);
        resort = new ImageIcon(modifiedimageResort);
        
        JLabel picture = new JLabel();
        picture.setIcon(resort);
        
        center.add(picture);
        



        


        
        frame.add(design, BorderLayout.NORTH);  
        design.add(top, BorderLayout.SOUTH);
        frame.add(bottom, BorderLayout.SOUTH);
        frame.add(right, BorderLayout.EAST);
        frame.add(left, BorderLayout.WEST);
        frame.add(center, BorderLayout.CENTER);
        
        
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        

        
    }
    
 

    public static void main(String[]args){
        new Home();
    }
    
}
