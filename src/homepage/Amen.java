package homepage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Amen {

    public Amen() {
        
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
        
        JPanel top1 = new JPanel(null);
        JPanel top2 = new JPanel();
        
        top.add(top1, BorderLayout.EAST);
        top.add(top2, BorderLayout.WEST);
        
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
        
        
        //=======================================================/
        JButton amenities = new JButton("<html><u>AMENITIES</u></html>");
        amenities.setBorder(null);
        amenities.setBackground(Color.white);
        amenities.setFont(new Font("Mongolian Baiti", Font.ROMAN_BASELINE, 16));
        amenities.setForeground(Color.black);
      
        
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
        
        JButton home = new JButton("HOME");
        home.setBorder(null);
        home.setBackground(Color.white);
        home.setFont(new Font("Mongolian Baiti", Font.ROMAN_BASELINE, 16));
        home.setForeground(Color.black);
        
         home.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e){
            new Home();
            frame.dispose();
          
            
        }
        });
        
        
        
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

        
        
        JPanel center1 = new JPanel();
        JPanel center2 = new JPanel();
       
        center.add(center1, BorderLayout.NORTH);
        center.add(center2, BorderLayout.NORTH);
        
        center1.setPreferredSize(new Dimension(1200, 190));
        center2.setPreferredSize(new Dimension(1200, 380));
        
        center1.setBackground(Color.black);
        center2.setBackground(Color.white);
        
        JLabel amenLabel = new JLabel ("AMENITIES");
        amenLabel.setForeground(new Color(0xFFF8DC));
        amenLabel.setFont(new Font("Poppins", Font.BOLD, 70));
        
        center1.add(amenLabel);
        center1.setLayout(null);
        
        ImageIcon resort = new ImageIcon("C:\\Users\\AMD\\OneDrive\\Documents\\NetBeansProjects\\HomePage\\src\\homepage\\image\\Amenities.png");
        
        ImageIcon pool1 = new ImageIcon("C:\\Users\\AMD\\OneDrive\\Documents\\NetBeansProjects\\HomePage\\src\\homepage\\image\\Pic1.png");
        ImageIcon pool2 = new ImageIcon("C:\\Users\\AMD\\OneDrive\\Documents\\NetBeansProjects\\HomePage\\src\\homepage\\image\\Pic2.png");
        ImageIcon pool3 = new ImageIcon("C:\\Users\\AMD\\OneDrive\\Documents\\NetBeansProjects\\HomePage\\src\\homepage\\image\\Pic3.png");
        ImageIcon pool4 = new ImageIcon("C:\\Users\\AMD\\OneDrive\\Documents\\NetBeansProjects\\HomePage\\src\\homepage\\image\\Pic4.png");
        
        Image imageResort1 = pool1.getImage();
        Image modifiedimageResort1 = imageResort1.getScaledInstance(240, 220, java.awt.Image.SCALE_SMOOTH);
        pool1 = new ImageIcon(modifiedimageResort1);
        
        Image imageResort2 = pool2.getImage();
        Image modifiedimageResort2 = imageResort2.getScaledInstance(240, 220, java.awt.Image.SCALE_SMOOTH);
        pool2 = new ImageIcon(modifiedimageResort2);
        
        Image imageResort3 = pool3.getImage();
        Image modifiedimageResort3 = imageResort3.getScaledInstance(240, 220, java.awt.Image.SCALE_SMOOTH);
        pool3 = new ImageIcon(modifiedimageResort3);
        
        Image imageResort4 = pool4.getImage();
        Image modifiedimageResort4 = imageResort4.getScaledInstance(240, 220, java.awt.Image.SCALE_SMOOTH);
        pool4 = new ImageIcon(modifiedimageResort4);
        
        
        
        Image imageResort = resort.getImage();
        Image modifiedimageResort = imageResort.getScaledInstance(1200, 200, java.awt.Image.SCALE_SMOOTH);
        resort = new ImageIcon(modifiedimageResort);
        
        

        
        
        JLabel picture = new JLabel();
        picture.setIcon(resort);
        
        picture.setBounds(0,0,1200,200);
        amenLabel.setBounds(400, 60, 500, 80);
        
        center1.add(picture);
        
        
        JPanel picturePanel = new JPanel();
        picturePanel.setBackground(Color.white);
        
        JLabel poolings1 = new JLabel();
        poolings1.setIcon(pool1);
        JLabel poolings2 = new JLabel();
        poolings2.setIcon(pool2);
        JLabel poolings3 = new JLabel();
        poolings3.setIcon(pool3);
        JLabel poolings4 = new JLabel();
        poolings4.setIcon(pool4);
        
        
        picturePanel.add(poolings1);
        picturePanel.add(poolings2);
        picturePanel.add(poolings3);
        picturePanel.add(poolings4);
        
        JLabel poolLabel = new JLabel("Pool");

        JLabel cottageLabel = new JLabel("Cottage");
        JLabel bonfire = new JLabel("Bonfire");
        JLabel event = new JLabel("Event place");
        
        
        
        
        picturePanel.setLayout(null);
        picturePanel.setBackground(Color.white);
        picturePanel.setBounds(0, 50, 1200, 250); 
        
        poolings1.setBounds(100, 10, 240, 220);
        poolLabel.setBounds(210, 235, 100, 20);


        poolings2.setBounds(350, 10, 240, 220);
        cottageLabel.setBounds(450, 235, 100, 20);


        poolings3.setBounds(600, 10, 240, 220);
        bonfire.setBounds(710, 235, 100, 20);


        poolings4.setBounds(850, 10, 240, 220);
        event.setBounds(925, 233, 160, 20);
        

        picturePanel.add(poolLabel);
        picturePanel.add(cottageLabel);
        picturePanel.add(bonfire);
        picturePanel.add(event);


     center2.setLayout(null);
center2.add(picturePanel);

        
        frame.add(design, BorderLayout.NORTH);  
        design.add(top, BorderLayout.SOUTH);
        frame.add(bottom, BorderLayout.SOUTH);
        frame.add(center, BorderLayout.CENTER);
        
        
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        

        
    }
    
        public static void main(String[]args){
        new Amen();
    }
    
}

        
