package homepage;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.Border;
import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class Book {

    static String first;
    static String middle;
    static String last;
    static String phoneNo;
    static String packageTP;
    static String roomTP = "";
    static String utils = "";
    static int electric = 0;

    static int totals = 0;
    static int utilPrice;
    static int roomPrice = 0;
    static String reservationTP = "";

    static String burnerL = "";
    static String Videoke = "";
    static String Bonfire = "";

    static String weekWhat = "";
    static int reset = 0;

    static int PackagePrice = 0;

    static JRadioButton opt1 = new JRadioButton("Electric Fan");
    static JRadioButton opt2 = new JRadioButton("Electric Kettle");
    static JRadioButton opt3 = new JRadioButton("Electric Griller");
    static JRadioButton opt4 = new JRadioButton("Electric Hotpot");
    static JRadioButton opt5 = new JRadioButton("Rice Cooker");
    static JRadioButton opt6 = new JRadioButton("Turbo Boiler");
    static JRadioButton opt7 = new JRadioButton("Sound Speaker");
    static JRadioButton opt8 = new JRadioButton("₱500 Use of Single burner small Fryingpan & Caserol");
    static JRadioButton opt9 = new JRadioButton("₱500 Use of Video (Strictly until 10pm only)");
    static JRadioButton opt10 = new JRadioButton("Bonfire (Free)");

    static JRadioButton check1 = new JRadioButton("Select Room 1");
    static JRadioButton check2 = new JRadioButton("Select Room 2");
    static JRadioButton check3 = new JRadioButton("Select Room 3");
    static JRadioButton check4 = new JRadioButton("Select Room 4");

    static final JButton[] lastClickedButtonNext = {null};
    static int checkNext = 0;

    static String mattres = " ";

    //FOR PAYMENT INSERTION
    static String paymentMethod = "";
    static LocalDate paymentDate = null;
    static int ReservationFee = 0;
    static int Amount = 0;
    static String packageCode = "";

    //FOR RESERVATION INSERTION
    static LocalDate CheckIN = null;
    static LocalDate CheckOUT = null;
    static String Status = "Pending";

    //FOR ROOM INSERTION
    static int roomID = 0;
    static String RoomNo = "";
    static String utilityOpt = "";
    static int utilityID = 0;

    static JTextField chickin = new JTextField();
    static JTextField chickout = new JTextField();
    static JTextField fname = new JTextField();
    static JTextField mname = new JTextField();
    static JTextField lname = new JTextField();
    static JTextField contact = new JTextField();
    static JTextField packagesK = new JTextField();

    static int exist = 0;

    static String url = "jdbc:mysql://localhost:3306/projectsystem";
    static String password = "1234";
    static String username = "root";

    public Book() {
        Packages();
    }

    static void createConnection() {
        try {
            Connection con = DriverManager.getConnection(url, username, password);
            Statement stmt = con.createStatement();
            System.out.println("Database Connection Success");

        } catch (SQLException ex) {
            Logger.getLogger(Book.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Database Connection Successs");
        }
    }

    public static void INSERTION() {

        try {
            Connection con = DriverManager.getConnection(url, username, password);
            String insertCustomer = "INSERT INTO customer(LastName, FirstName, MiddleName, ContactNo) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(insertCustomer, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, last);
            stmt.setString(2, first);
            stmt.setString(3, middle);
            stmt.setString(4, phoneNo);
            stmt.executeUpdate();

            int customerId = 0; //GET THE RECENT INSERT
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                customerId = rs.getInt(1);
            }

            System.out.println("CustomerID: " + customerId);

            //PAYMENTTTTTTTTTTTTTTTTTTTTTTTTTTTTT
            String insertPayment = "INSERT INTO payment( PaymentMethod, PaymentDate, ReservationFee, Amount) VALUES (?, ?, ?, ?)";
            PreparedStatement pStmt = con.prepareStatement(insertPayment, Statement.RETURN_GENERATED_KEYS);
            pStmt.setString(1, paymentMethod);
            pStmt.setDate(2, Date.valueOf(paymentDate));
            pStmt.setInt(3, ReservationFee);
            pStmt.setInt(4, Amount);
            pStmt.executeUpdate();

            int paymentId = 0; //GET THE RECENT INSERT
            ResultSet rsP = pStmt.getGeneratedKeys();
            if (rsP.next()) {
                paymentId = rsP.getInt(1);
            }

            //RESERVATIONNNNNNNNNNNNNNNNNNNNNNNNNNNN
            String insertReservation = "INSERT INTO reservation(CheckInDate, CheckOutDate, ReservationStatus, CustomerID, PackageCode, PaymentID) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement iStmt = con.prepareStatement(insertReservation, Statement.RETURN_GENERATED_KEYS);
            iStmt.setDate(1, Date.valueOf(CheckIN));
            iStmt.setDate(2, Date.valueOf(CheckOUT));
            iStmt.setString(3, Status);
            iStmt.setInt(4, customerId);
            iStmt.setString(5, packageCode);
            iStmt.setInt(6, paymentId);
            iStmt.executeUpdate();

            int reservationId = 0; //GET THE RECENT INSERT
            ResultSet rRsP = iStmt.getGeneratedKeys();
            if (rRsP.next()) {
                reservationId = rRsP.getInt(1);
            }

            if (roomTP.isEmpty()) {

            } else {
                String insertRoom = "INSERT INTO room_order( RoomNo, ReservationNo) VALUES (?, ?)";
                PreparedStatement rStmt = con.prepareStatement(insertRoom);

                if (check1.isSelected()) {
                    RoomNo = "101";
                    rStmt.setString(1, RoomNo);
                    rStmt.setInt(2, reservationId);
                    rStmt.executeUpdate();
                }
                if (check2.isSelected()) {
                    RoomNo = "102";
                    rStmt.setString(1, RoomNo);
                    rStmt.setInt(2, reservationId);
                    rStmt.executeUpdate();
                }
                if (check3.isSelected()) {
                    RoomNo = "103";
                    rStmt.setString(1, RoomNo);
                    rStmt.setInt(2, reservationId);
                    rStmt.executeUpdate();
                }
                if (check4.isSelected()) {
                    RoomNo = "104";
                    rStmt.setString(1, RoomNo);
                    rStmt.setInt(2, reservationId);
                    rStmt.executeUpdate();
                }

            }

            if (utils.isEmpty()) {

            } else {

                String insertUtility = "INSERT INTO utility_order(UtilityID, ReservationNo) VALUES (?, ?)";
                PreparedStatement inStmt = con.prepareStatement(insertUtility);

                if (opt1.isSelected()) {
                    utilityID = 2000;
                    inStmt.setInt(1, utilityID);
                    inStmt.setInt(2, reservationId);
                    inStmt.executeUpdate();
                }

                if (opt2.isSelected()) {
                    utilityID = 2001;
                    inStmt.setInt(1, utilityID);
                    inStmt.setInt(2, reservationId);
                    inStmt.executeUpdate();
                }

                if (opt3.isSelected()) {
                    utilityID = 2002;
                    inStmt.setInt(1, utilityID);
                    inStmt.setInt(2, reservationId);
                    inStmt.executeUpdate();
                }

                if (opt4.isSelected()) {
                    utilityID = 2003;
                    inStmt.setInt(1, utilityID);
                    inStmt.setInt(2, reservationId);
                    inStmt.executeUpdate();
                }

                if (opt5.isSelected()) {
                    utilityID = 2004;
                    inStmt.setInt(1, utilityID);
                    inStmt.setInt(2, reservationId);
                    inStmt.executeUpdate();
                }

                if (opt6.isSelected()) {
                    utilityID = 2005;
                    inStmt.setInt(1, utilityID);
                    inStmt.setInt(2, reservationId);
                    inStmt.executeUpdate();
                }

                if (opt7.isSelected()) {
                    utilityID = 2006;
                    inStmt.setInt(1, utilityID);
                    inStmt.setInt(2, reservationId);
                    inStmt.executeUpdate();
                }

                if (opt8.isSelected()) {
                    utilityID = 2007;
                    inStmt.setInt(1, utilityID);
                    inStmt.setInt(2, reservationId);
                    inStmt.executeUpdate();
                }

                if (opt9.isSelected()) {
                    utilityID = 2008;
                    inStmt.setInt(1, utilityID);
                    inStmt.setInt(2, reservationId);
                    inStmt.executeUpdate();
                }

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void Contact() {

        JFrame frame = new JFrame();
        frame.setSize(1200, 800);
        frame.setTitle("Luz Ville Resort");

        JPanel design = new JPanel();
        JPanel bottom = new JPanel();
        JPanel right = new JPanel();
        JPanel left = new JPanel();
        JPanel center = new JPanel();
            center.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));

        design.setBackground(Color.black);
        bottom.setBackground(Color.white);
        right.setBackground(Color.white);
        left.setBackground(Color.white);
        center.setBackground(Color.white);

        design.setPreferredSize(new Dimension(200, 120));
        design.setLayout(new BorderLayout());
        design.setBorder(null);

        bottom.setPreferredSize(new Dimension(200, 70));
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
        iconicLogo.setBounds(50, 6, 80, 65);
        top1.add(iconicLogo);

        //=======================================================/
        JButton amenities = new JButton("AMENITIES");
        amenities.setBorder(null);
        amenities.setBackground(Color.white);
        amenities.setFont(new Font("Mongolian Baiti", Font.ROMAN_BASELINE, 16));
        amenities.setForeground(Color.black);

        amenities.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Amen();
                frame.dispose();

            }
        });

        JButton rates = new JButton("RATES");
        rates.setBorder(null);
        rates.setBackground(Color.white);
        rates.setFont(new Font("Mongolian Baiti", Font.ROMAN_BASELINE, 16));
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
        home.setFont(new Font("Mongolian Baiti", Font.ROMAN_BASELINE, 16));
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
        about.setFont(new Font("Mongolian Baiti", Font.ROMAN_BASELINE, 16));
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
        book.setFont(new Font("Mongolian Baiti", Font.ROMAN_BASELINE, 16));
        book.setForeground(Color.white);
        book.setFocusPainted(false);
        book.setBorderPainted(false);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));

        buttonPanel.add(amenities);
        buttonPanel.add(rates);
        buttonPanel.add(home);
        buttonPanel.add(about);
        buttonPanel.add(book);

        buttonPanel.setBackground(Color.white);
        buttonPanel.setPreferredSize(new Dimension(500, 30));

        top2.add(buttonPanel, BorderLayout.WEST);
        center.setLayout(null);

        if (exist == 1) {
            fname = new JTextField(first);
            mname = new JTextField(middle);
            lname = new JTextField(last);
            contact = new JTextField(phoneNo);

        }

        JLabel contactLabel = new JLabel("Contact Detail");
        contactLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
        contactLabel.setForeground(Color.black);

        JLabel f = new JLabel("Full Name");
        f.setFont(new Font("Tahoma", Font.BOLD, 16));
        f.setForeground(Color.black);

        fname.setFont(new Font("Open Sans", Font.BOLD, 15));
        fname.setBackground(new Color(217, 217, 217));
        fname.setBorder(null);
        fname.setForeground(Color.black);

        mname.setFont(new Font("Open Sans", Font.BOLD, 15));
        mname.setBackground(new Color(217, 217, 217));
        mname.setBorder(null);
        mname.setForeground(Color.black);

        lname.setFont(new Font("Open Sans", Font.BOLD, 15));
        lname.setBackground(new Color(217, 217, 217));
        lname.setBorder(null);
        lname.setForeground(Color.black);

        contact.setFont(new Font("Open Sans", Font.BOLD, 15));
        contact.setBackground(new Color(217, 217, 217));
        contact.setBorder(null);
        contact.setForeground(Color.black);

        JLabel phone = new JLabel("Phone Number");

        phone.setFont(new Font("Tahoma", Font.BOLD, 16));
        phone.setForeground(Color.black);

        JLabel fn = new JLabel("First Name: ");
        JLabel mn = new JLabel("Middle Name: ");
        JLabel ln = new JLabel("Last Name: ");
        JLabel ex = new JLabel("ex. (1234-567-8901)");

        JPanel full = new JPanel(null);
        full.setBounds(50, 100, 900, 450);
        full.setBackground(Color.white);

        f.setBounds(0, -30, 100, 100);
        fname.setBounds(0, 30, 200, 30);
        mname.setBounds(250, 30, 200, 30);
        lname.setBounds(500, 30, 200, 30);

        fn.setBounds(0, 55, 200, 30);
        mn.setBounds(250, 55, 200, 30);
        ln.setBounds(500, 55, 200, 30);

        phone.setBounds(0, 105, 200, 30);
        contact.setBounds(0, 130, 200, 30);
        ex.setBounds(0, 155, 200, 30);

        contactLabel.setBounds(50, -25, 300, 200);

        JButton next = new JButton("NEXT");
        next.setBackground(new Color(0x556B2F));
        next.setFont(new Font("Tohoma", Font.BOLD, 20));
        next.setForeground(Color.white);
        next.setFocusPainted(false);
        next.setBorderPainted(false);
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                first = (String) fname.getText();
                middle = (String) mname.getText();
                last = (String) lname.getText();
                phoneNo = (String) contact.getText();

                String ceck = chickin.getText();
                checkNext = 0;
                if (!first.isEmpty() && !middle.isEmpty() && !last.isEmpty() && !phoneNo.isEmpty() && !ceck.isEmpty()) {
                    Room();
                    frame.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Kindly complete the required information to proceed.", "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        next.setBounds(850, 500, 180, 50);

        JPanel calen = new JPanel();
        calen.setBounds(225, 130, 400, 305);
        calen.setBackground(Color.white);

        calen.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        Border bottomBorder = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK);

        JPanel calenTop = new JPanel(new GridBagLayout());
        calenTop.setBackground(Color.white);
        calenTop.setPreferredSize(new Dimension(398, 30));
        calen.add(calenTop, BorderLayout.NORTH);
        calenTop.setBorder(bottomBorder);
        
        

        JLabel monthyear = new JLabel("May 2025");
        calenTop.add(monthyear);

        JPanel calenBody = new JPanel(new GridLayout(0, 7, 3, 2));
        calenBody.setPreferredSize(new Dimension(398, 260));
        calenBody.setBackground(Color.white);

        calen.add(calenBody, BorderLayout.CENTER);

        String[] weekdays = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        for (String dayName : weekdays) {
            JLabel headerLabel = new JLabel(dayName, SwingConstants.CENTER);
            headerLabel.setBackground(Color.white);
            headerLabel.setFont(new Font("Arial", Font.BOLD, 12));
            headerLabel.setOpaque(true);
            calenBody.add(headerLabel);
        }

        LocalDate today = LocalDate.now();
        int year = today.getYear();
        int month = today.getMonthValue();
        int daysInMonth = YearMonth.of(year, month).lengthOfMonth();

        LocalDate firstDay = LocalDate.of(year, month, 1);
        int startDayOfWeek = firstDay.getDayOfWeek().getValue() % 7;
        for (int i = 0; i < startDayOfWeek; i++) {
            calenBody.add(new JLabel(""));
        }

        // COOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO
        Set<LocalDate> bookedDates = new HashSet<>();

        try {

            Connection con = DriverManager.getConnection(url, username, password);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT CheckInDate FROM reservation");

            while (rs.next()) {
                LocalDate date = rs.getDate("CheckInDate").toLocalDate();
                bookedDates.add(date);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        final JButton[] lastClickedButton = {null};
        for (int day = 1; day <= daysInMonth; day++) {
            LocalDate currentDate = LocalDate.of(2025, month, day);
            LocalDate currentDateCheker = LocalDate.of(2025, month, day);
            JButton dayLabel = new JButton(String.format("%2d", day));
            dayLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            dayLabel.setOpaque(true);
            dayLabel.setBackground(Color.white);
            dayLabel.setFocusPainted(false);

            if (bookedDates.contains(currentDate)) {
                dayLabel.setBackground(Color.RED);
                dayLabel.setForeground(Color.black);
                dayLabel.setEnabled(false);
            } else {
                dayLabel.setBackground(Color.WHITE);
                dayLabel.setForeground(Color.black);
            }

            if (currentDateCheker.equals(CheckIN) & checkNext == 0) {
                dayLabel.setBackground(Color.GREEN);
            }

            calenBody.add(dayLabel);
            int finalDay = day;
            dayLabel.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    checkNext = 1;

                    if (lastClickedButton[0] != null) {
                        lastClickedButton[0].setBackground(Color.WHITE);
                    }

                    LocalDate selectedDate = LocalDate.of(2025, month, finalDay);
                    DayOfWeek dayOfWeek = selectedDate.getDayOfWeek();

                    if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {
                        weekWhat = "Weekends";
                        packagesK.setText("  " + packageTP + " - Weekends");
                    } else {
                        weekWhat = "Weekdays";
                        packagesK.setText("  " + packageTP + " - Weekdays");

                    }

                    if (packageTP == "DayTour" || packageTP == "DayNight") {

                        String text = dayLabel.getText().trim();

                        String ceckin = "  " + String.valueOf(month) + " / " + text + " / " + "2025";
                        chickin.setText(ceckin);
                        chickout.setText(ceckin);

                        //checkIN
                        CheckIN = LocalDate.of(2025, month, Integer.parseInt(text));
                        CheckOUT = LocalDate.of(2025, month, Integer.parseInt(text));

                    } else {
                        int text = Integer.parseInt(dayLabel.getText().trim()) + 1;
                        int texts = Integer.parseInt(dayLabel.getText().trim());
                        String ceckin = "  " + String.valueOf(month) + " / " + texts + " / " + "2025";
                        String ceckout = "  " + String.valueOf(month) + " / " + text + " / " + "2025";

                        chickin.setText(ceckin);
                        chickout.setText(ceckout);

                        CheckIN = LocalDate.of(2025, month, texts);
                        CheckOUT = LocalDate.of(2025, month, text);
                        //checkIN

                        //greeen button
                    }

                    dayLabel.setBackground(Color.GREEN);
                    lastClickedButton[0] = dayLabel;
                    lastClickedButtonNext[0] = dayLabel;

                }

            });

        }

        chickin.setFont(new Font("Tohoma", Font.BOLD, 16));
        chickin.setBackground(new Color(217, 217, 217));
        chickin.setBorder(null);
        chickin.setForeground(Color.black);
        chickin.setEditable(false);

        chickout.setFont(new Font("Open Sans", Font.BOLD, 16));
        chickout.setBackground(new Color(217, 217, 217));
        chickout.setBorder(null);
        chickout.setForeground(Color.black);
        chickout.setEditable(false);

        packagesK = new JTextField("  " + packageTP + " - " + weekWhat);
        packagesK.setFont(new Font("Open Sans", Font.BOLD, 16));
        packagesK.setBackground(new Color(217, 217, 217));
        packagesK.setBorder(null);
        packagesK.setForeground(Color.black);
        packagesK.setEditable(false);

        chickin.setBounds(645, 145, 200, 30);
        chickout.setBounds(645, 215, 200, 30);
        packagesK.setBounds(645, 285, 200, 30);

        JLabel checkIL = new JLabel("Check in: ");
        JLabel checkOL = new JLabel("Check out: ");
        JLabel packageL = new JLabel("Package: ");
        checkIL.setBounds(645, 120, 200, 30);
        checkOL.setBounds(645, 190, 200, 30);
        packageL.setBounds(645, 260, 200, 30);

        JButton back = new JButton("BACK");
        back.setBackground(new Color(0x556B2F));
        back.setFont(new Font("Tohoma", Font.BOLD, 20));
        back.setForeground(Color.white);
        back.setFocusPainted(false);
        back.setBorderPainted(false);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exist = 1;
                Packages();
                chickin.setText("");
                chickout.setText("");
                weekWhat = "";
                packagesK.setText("");
                checkNext = 1;
                frame.dispose();

            }
        });

        back.setBounds(50, 500, 180, 50);

        center.add(back);
        full.add(checkIL);
        full.add(checkOL);
        full.add(chickin);
        full.add(chickout);
        full.add(calen);
        full.add(f);
        full.add(fn);
        full.add(mn);
        full.add(ln);
        full.add(fname);
        full.add(mname);
        full.add(lname);
        full.add(phone);
        full.add(contact);
        full.add(ex);
        center.add(next);
        center.add(contactLabel);
        full.add(packagesK);
        full.add(packageL);

        center.add(full);

        frame.add(design, BorderLayout.NORTH);
        design.add(top, BorderLayout.SOUTH);
        frame.add(bottom, BorderLayout.SOUTH);
        frame.add(right, BorderLayout.EAST);
        frame.add(left, BorderLayout.WEST);
        frame.add(center, BorderLayout.CENTER);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

    }

    public static void Packages() {
        JFrame frame = new JFrame();
        frame.setSize(1200, 800);
        frame.setTitle("Luz Ville Resort");

        JPanel design = new JPanel();
        JPanel bottom = new JPanel();
        JPanel right = new JPanel();
        JPanel left = new JPanel();
        JPanel center = new JPanel();

        center.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));

        design.setBackground(Color.black);
        bottom.setBackground(Color.white);
        right.setBackground(Color.white);
        left.setBackground(Color.white);
        center.setBackground(Color.white);

        design.setPreferredSize(new Dimension(200, 120));
        design.setLayout(new BorderLayout());
        design.setBorder(null);

        bottom.setPreferredSize(new Dimension(200, 70));
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
        iconicLogo.setBounds(50, 6, 80, 65);
        top1.add(iconicLogo);

        //=======================================================/
        JButton amenities = new JButton("AMENITIES");
        amenities.setBorder(null);
        amenities.setBackground(Color.white);
        amenities.setFont(new Font("Mongolian Baiti", Font.ROMAN_BASELINE, 16));
        amenities.setForeground(Color.black);

        amenities.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Amen();
                frame.dispose();

            }
        });

        JButton rates = new JButton("RATES");
        rates.setBorder(null);
        rates.setBackground(Color.white);
        rates.setFont(new Font("Mongolian Baiti", Font.ROMAN_BASELINE, 16));
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
        home.setFont(new Font("Mongolian Baiti", Font.ROMAN_BASELINE, 16));
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
        about.setFont(new Font("Mongolian Baiti", Font.ROMAN_BASELINE, 16));
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
        book.setFont(new Font("Mongolian Baiti", Font.ROMAN_BASELINE, 16));
        book.setForeground(Color.white);
        book.setFocusPainted(false);
        book.setBorderPainted(false);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));

        buttonPanel.add(amenities);
        buttonPanel.add(rates);
        buttonPanel.add(home);
        buttonPanel.add(about);
        buttonPanel.add(book);

        buttonPanel.setBackground(Color.white);
        buttonPanel.setPreferredSize(new Dimension(500, 30));

        top2.add(buttonPanel, BorderLayout.WEST);
        center.setLayout(null);

        //===========================================================================/
        JLabel packageLabel = new JLabel("Packages");
        packageLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
        packageLabel.setForeground(Color.black);
        packageLabel.setBounds(50, -50, 300, 200);

        center.add(packageLabel);

        JPanel full = new JPanel(null);
        full.setBounds(50, 100, 1000, 400);
        full.setBackground(Color.white);

        JPanel package1 = new JPanel();
        package1.setBounds(0, 30, 275, 360);

        JPanel packageheader1 = new JPanel(new GridBagLayout());
        packageheader1.setBounds(50, 5, 175, 60);
        packageheader1.setBackground(new Color(135, 206, 235));
        full.add(packageheader1);

        JLabel dtLabel = new JLabel("DAY TOUR");
        packageheader1.add(dtLabel);
        dtLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
        dtLabel.setForeground(new Color(255, 248, 220));

        //imageeee Package1
        JPanel img1 = new JPanel();
        img1.setBounds(0, 40, 275, 270);
        package1.setBackground(new Color(217, 217, 217));
        img1.setBackground(new Color(217, 217, 217));

        package1.add(img1);

        ImageIcon resort = new ImageIcon("C:\\Users\\AMD\\OneDrive\\Documents\\NetBeansProjects\\2ndSemProject\\src\\homepage\\image\\Package1.jpg");
        Image imageResort1 = resort.getImage();
        Image modifiedimageResort1 = imageResort1.getScaledInstance(275, 270, java.awt.Image.SCALE_SMOOTH);
        resort = new ImageIcon(modifiedimageResort1);

        JLabel poolings1 = new JLabel();
        poolings1.setIcon(resort);

        img1.add(poolings1);

        JPanel package2 = new JPanel();
        package2.setBounds(360, 30, 275, 360);

        JPanel packageheader2 = new JPanel(new GridBagLayout());
        packageheader2.setBounds(410, 5, 175, 60);
        packageheader2.setBackground(new Color(255, 127, 80));
        full.add(packageheader2);

        JLabel dtLabe2 = new JLabel("DAY TO NIGHT");
        packageheader2.add(dtLabe2);
        dtLabe2.setFont(new Font("Tahoma", Font.BOLD, 23));
        dtLabe2.setForeground(new Color(255, 248, 220));

        //imageeee Package2
        JPanel img2 = new JPanel();
        img2.setBounds(0, 40, 275, 270);
        package2.setBackground(new Color(217, 217, 217));
        img2.setBackground(new Color(217, 217, 217));

        package2.add(img2);

        ImageIcon resort1 = new ImageIcon("C:\\Users\\AMD\\OneDrive\\Documents\\NetBeansProjects\\2ndSemProject\\src\\homepage\\image\\Package1.jpg");
        Image imageResort2 = resort1.getImage();
        Image modifiedimageResort2 = imageResort2.getScaledInstance(275, 270, java.awt.Image.SCALE_SMOOTH);
        resort1 = new ImageIcon(modifiedimageResort2);

        JLabel poolings2 = new JLabel();
        poolings2.setIcon(resort1);

        img2.add(poolings2);

        JPanel package3 = new JPanel();
        package3.setBounds(720, 30, 275, 360);

        JPanel packageheader3 = new JPanel(new GridBagLayout());
        packageheader3.setBounds(770, 5, 175, 60);
        packageheader3.setBackground(new Color(90, 24, 154));
        full.add(packageheader3);

        JLabel dtLabe3 = new JLabel("OVERNIGHT");
        packageheader3.add(dtLabe3);
        dtLabe3.setFont(new Font("Tahoma", Font.BOLD, 25));
        dtLabe3.setForeground(new Color(255, 248, 220));

        //imageeee Package2
        JPanel img3 = new JPanel();
        img3.setBounds(0, 40, 275, 270);
        package3.setBackground(new Color(217, 217, 217));
        img3.setBackground(new Color(217, 217, 217));

        package3.add(img3);

        ImageIcon resort2 = new ImageIcon("C:\\Users\\AMD\\OneDrive\\Documents\\NetBeansProjects\\2ndSemProject\\src\\homepage\\image\\Package3.jpg");
        Image imageResort3 = resort2.getImage();
        Image modifiedimageResort3 = imageResort3.getScaledInstance(275, 270, java.awt.Image.SCALE_SMOOTH);
        resort2 = new ImageIcon(modifiedimageResort3);

        JLabel poolings3 = new JLabel();
        poolings3.setIcon(resort2);

        img3.add(poolings3);

        //====================================================/ PACKAGE
        JButton selectPackage1 = new JButton("Select");
        selectPackage1.setBackground(new Color(0x556B2F));
        selectPackage1.setFont(new Font("Tohoma", Font.BOLD, 20));
        selectPackage1.setForeground(Color.white);
        selectPackage1.setFocusPainted(false);
        selectPackage1.setBorderPainted(false);

        selectPackage1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                packageTP = "DayTour";
                Contact();
                frame.dispose();
            }
        });
        JButton selectPackage2 = new JButton("Select");
        selectPackage2.setBackground(new Color(0x556B2F));
        selectPackage2.setFont(new Font("Tohoma", Font.BOLD, 20));
        selectPackage2.setForeground(Color.white);
        selectPackage2.setFocusPainted(false);
        selectPackage2.setBorderPainted(false);
        selectPackage2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                packageTP = "DayNight";
                Contact();
                frame.dispose();
            }
        });
        JButton selectPackage3 = new JButton("Select");
        selectPackage3.setBackground(new Color(0x556B2F));
        selectPackage3.setFont(new Font("Tohoma", Font.BOLD, 20));
        selectPackage3.setForeground(Color.white);
        selectPackage3.setFocusPainted(false);
        selectPackage3.setBorderPainted(false);
        selectPackage3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                packageTP = "Overnight";
                Contact();
                frame.dispose();
            }
        });

        full.add(package1);
        full.add(package2);
        full.add(package3);

        package1.setLayout(null);
        package2.setLayout(null);
        package3.setLayout(null);

        selectPackage1.setBounds(40, 320, 200, 30);
        selectPackage2.setBounds(40, 320, 200, 30);
        selectPackage3.setBounds(40, 320, 200, 30);

        package1.add(selectPackage1);
        package2.add(selectPackage2);
        package3.add(selectPackage3);

        center.add(full);
        frame.add(design, BorderLayout.NORTH);
        design.add(top, BorderLayout.SOUTH);
        frame.add(bottom, BorderLayout.SOUTH);
        frame.add(right, BorderLayout.EAST);
        frame.add(left, BorderLayout.WEST);
        frame.add(center, BorderLayout.CENTER);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    /*ROOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO
        OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOM*/
    public static void Room() {
        {
            JFrame frame = new JFrame();
            frame.setSize(1200, 800);
            frame.setTitle("Luz Ville Resort");

            JPanel design = new JPanel();
            JPanel bottom = new JPanel();
            JPanel right = new JPanel();
            JPanel left = new JPanel();
            JPanel center = new JPanel();
        center.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));

            design.setBackground(Color.black);
            bottom.setBackground(Color.white);
            right.setBackground(Color.white);
            left.setBackground(Color.white);
            center.setBackground(Color.white);

            design.setPreferredSize(new Dimension(200, 120));
            design.setLayout(new BorderLayout());
            design.setBorder(null);

            bottom.setPreferredSize(new Dimension(200, 70));
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
            iconicLogo.setBounds(50, 6, 80, 65);
            top1.add(iconicLogo);

            //=======================================================/
            JButton amenities = new JButton("AMENITIES");
            amenities.setBorder(null);
            amenities.setBackground(Color.white);
            amenities.setFont(new Font("Mongolian Baiti", Font.ROMAN_BASELINE, 16));
            amenities.setForeground(Color.black);

            amenities.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new Amen();
                    frame.dispose();

                }
            });

            JButton rates = new JButton("RATES");
            rates.setBorder(null);
            rates.setBackground(Color.white);
            rates.setFont(new Font("Mongolian Baiti", Font.ROMAN_BASELINE, 16));
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
            home.setFont(new Font("Mongolian Baiti", Font.ROMAN_BASELINE, 16));
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
            about.setFont(new Font("Mongolian Baiti", Font.ROMAN_BASELINE, 16));
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
            book.setFont(new Font("Mongolian Baiti", Font.ROMAN_BASELINE, 16));
            book.setForeground(Color.white);
            book.setFocusPainted(false);
            book.setBorderPainted(false);

            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));

            buttonPanel.add(amenities);
            buttonPanel.add(rates);
            buttonPanel.add(home);
            buttonPanel.add(about);
            buttonPanel.add(book);

            buttonPanel.setBackground(Color.white);
            buttonPanel.setPreferredSize(new Dimension(500, 30));

            top2.add(buttonPanel, BorderLayout.WEST);
            center.setLayout(null);

            //===========================================================================/
            JLabel packageLabel = new JLabel("Select Room Type (optional)");
            packageLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
            packageLabel.setForeground(Color.black);
            packageLabel.setBounds(50, -25, 500, 200);

            center.add(packageLabel);

            JButton back = new JButton("BACK");
            back.setBackground(new Color(0x556B2F));
            back.setFont(new Font("Tohoma", Font.BOLD, 20));
            back.setForeground(Color.white);
            back.setFocusPainted(false);
            back.setBorderPainted(false);
            back.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    exist = 1;
                    Contact();
                    frame.dispose();

                }
            });

            JButton next = new JButton("NEXT");
            next.setBackground(new Color(0x556B2F));
            next.setFont(new Font("Tohoma", Font.BOLD, 20));
            next.setForeground(Color.white);
            next.setFocusPainted(false);
            next.setBorderPainted(false);
            next.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    if (check1.isSelected()) {
                        roomTP += "Room #1, ";
                        roomPrice += 1500;
                        check1.setSelected(true);

                    }

                    if (check2.isSelected()) {
                        roomTP += "Room #2, ";
                        roomPrice += 1500;
                        check2.setSelected(true);

                    }

                    if (check3.isSelected()) {
                        roomTP += "Room #3, ";
                        roomPrice += 2000;
                        check3.setSelected(true);

                    }

                    if (check4.isSelected()) {
                        roomTP += "Room #4, ";
                        check4.setSelected(true);

                        if (packageTP == "DayTour" || packageTP == "DayNight") {
                            roomPrice += 300;
                        } else {
                            roomPrice += 500;
                        }
                    }

                    System.out.print(roomTP);
                    Utilities();
                    frame.dispose();

                }
            });

            next.setBounds(850, 500, 180, 50);

            back.setBounds(50, 500, 180, 50);
            JPanel full = new JPanel(null);
            full.setBounds(50, 100, 1000, 400);
            full.setBackground(Color.white);

            //=====================================================================/ ROOM
            JPanel room1 = new JPanel();
            room1.setBounds(0, 30, 240, 360);

            JPanel room2 = new JPanel();
            room2.setBounds(265, 30, 240, 360);

            JPanel room3 = new JPanel();
            room3.setBounds(530, 30, 240, 360);

            JPanel room4 = new JPanel();
            room4.setBounds(795, 30, 240, 360);

            //====================================================/ ROOM
            full.add(room1);
            full.add(room2);
            full.add(room3);
            full.add(room4);
            center.add(next);

            room1.setLayout(null);
            JLabel roomL1 = new JLabel("Room #1");
            roomL1.setFont(new Font("Tohoma", Font.BOLD, 25));
            roomL1.setForeground(Color.black);

            JLabel descL1 = new JLabel("airconditioned room");
            descL1.setFont(new Font("Tohoma", Font.BOLD, 18));
            descL1.setForeground(Color.black);

            JLabel descL11 = new JLabel("capacity: 4-6 people");
            descL11.setFont(new Font("Tohoma", Font.BOLD, 18));
            descL11.setForeground(Color.black);

            JPanel image1 = new JPanel();

            check1.setOpaque(false);
            
               ImageIcon resort = new ImageIcon("C:\\Users\\AMD\\OneDrive\\Documents\\NetBeansProjects\\2ndSemProject\\src\\homepage\\image\\room1.png");
               Image imageResort = resort.getImage();
               Image modifiedimageResort = imageResort.getScaledInstance(200, 130, java.awt.Image.SCALE_SMOOTH);
               resort = new ImageIcon(modifiedimageResort);
               
               ImageIcon resort1 = new ImageIcon("C:\\Users\\AMD\\OneDrive\\Documents\\NetBeansProjects\\2ndSemProject\\src\\homepage\\image\\kubo1.png");
               Image imageResort1 = resort1.getImage();
               Image modifiedimageResort1 = imageResort1.getScaledInstance(200, 130, java.awt.Image.SCALE_SMOOTH);
               resort1 = new ImageIcon(modifiedimageResort1);

               JLabel poolings1 = new JLabel();
               poolings1.setIcon(resort);
               JLabel poolings2 = new JLabel();
               JLabel poolings3 = new JLabel();
               JLabel poolings4 = new JLabel();
               poolings2.setIcon(resort);
               poolings3.setIcon(resort);
               poolings4.setIcon(resort1);
               image1.add(poolings1);
            

            room1.add(image1);
            room1.add(roomL1);
            room1.add(descL1);
            room1.add(descL11);
            room1.add(check1);

            roomL1.setBounds(40, 5, 200, 50);
            image1.setBounds(20, 50, 200, 130);
            descL1.setBounds(33, 180, 200, 50);
            descL11.setBounds(33, 210, 200, 50);
            check1.setBounds(40, 250, 200, 50);

            room2.setLayout(null);
            JLabel roomL2 = new JLabel("Room #2");
            roomL2.setFont(new Font("Tohoma", Font.BOLD, 25));
            roomL2.setForeground(Color.black);

            JLabel descL2 = new JLabel("airconditioned room");
            descL2.setFont(new Font("Tohoma", Font.BOLD, 18));
            descL2.setForeground(Color.black);

            JLabel descL22 = new JLabel("capacity: 4-6 people");
            descL22.setFont(new Font("Tohoma", Font.BOLD, 18));
            descL22.setForeground(Color.black);

            JPanel image2 = new JPanel();


            check2.setOpaque(false);
            
            image2.add(poolings2);

            room2.add(image2);
            room2.add(roomL2);
            room2.add(descL2);
            room2.add(descL22);
            room2.add(check2);

            roomL2.setBounds(40, 5, 200, 50);
            image2.setBounds(20, 50, 200, 130);
            descL2.setBounds(33, 180, 200, 50);
            descL22.setBounds(33, 210, 200, 50);
            check2.setBounds(40, 250, 200, 50);

            room3.setLayout(null);
            JLabel roomL3 = new JLabel("Room #3");
            roomL3.setFont(new Font("Tohoma", Font.BOLD, 25));
            roomL3.setForeground(Color.black);

            JLabel descL3 = new JLabel("airconditioned room");
            descL3.setFont(new Font("Tohoma", Font.BOLD, 18));
            descL3.setForeground(Color.black);

            JLabel descL33 = new JLabel("capacity: 6-10 people");
            descL33.setFont(new Font("Tohoma", Font.BOLD, 18));
            descL33.setForeground(Color.black);

            JPanel image3 = new JPanel();

    
            check3.setOpaque(false);
            
            image3.add(poolings3);

            room3.add(image3);
            room3.add(roomL3);
            room3.add(descL3);
            room3.add(descL33);
            room3.add(check3);

            roomL3.setBounds(40, 5, 200, 50);
            image3.setBounds(20, 50, 200, 130);
            descL3.setBounds(33, 180, 200, 50);
            descL33.setBounds(33, 210, 200, 50);
            check3.setBounds(40, 250, 200, 50);

            room4.setLayout(null);
            JLabel roomL4 = new JLabel("Room #4");
            roomL4.setFont(new Font("Tohoma", Font.BOLD, 25));
            roomL4.setForeground(Color.black);

            JLabel descL4 = new JLabel("kubo for couples");
            descL4.setFont(new Font("Tohoma", Font.BOLD, 15));
            descL4.setForeground(Color.black);

            JLabel descL44 = new JLabel("capacity: 4-6 people");
            descL44.setFont(new Font("Tohoma", Font.BOLD, 15));
            descL44.setForeground(Color.black);

            JPanel image4 = new JPanel();


            check4.setOpaque(false);
            
            image4.add(poolings4);

            room4.add(image4);
            room4.add(roomL4);
            room4.add(descL4);
            room4.add(descL44);
            room4.add(check4);

            roomL4.setBounds(40, 5, 200, 50);
            image4.setBounds(20, 50, 170, 130);
            descL4.setBounds(28, 180, 200, 50);
            descL44.setBounds(28, 210, 200, 50);
            check4.setBounds(20, 250, 200, 50);

            frame.add(design, BorderLayout.NORTH);
            design.add(top, BorderLayout.SOUTH);
            frame.add(bottom, BorderLayout.SOUTH);
            frame.add(right, BorderLayout.EAST);
            frame.add(left, BorderLayout.WEST);
            frame.add(center, BorderLayout.CENTER);

            center.add(back);
            center.add(full);

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
            frame.setLocationRelativeTo(null);

        }
    }

    /*UTILITIESSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS
        UTILITIESSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS
        UTILITIESSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS*/
    public static void Utilities() {

        JFrame frame = new JFrame();
        frame.setSize(1200, 800);
        frame.setTitle("Luz Ville Resort");

        JPanel design = new JPanel();
        JPanel bottom = new JPanel();
        JPanel right = new JPanel();
        JPanel left = new JPanel();
        JPanel center = new JPanel();
            center.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));

        design.setBackground(Color.black);
        bottom.setBackground(Color.white);
        right.setBackground(Color.white);
        left.setBackground(Color.white);
        center.setBackground(Color.white);

        design.setPreferredSize(new Dimension(200, 120));
        design.setLayout(new BorderLayout());
        design.setBorder(null);

        bottom.setPreferredSize(new Dimension(200, 70));
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
        iconicLogo.setBounds(50, 6, 80, 65);
        top1.add(iconicLogo);

        //=======================================================/
        JButton amenities = new JButton("AMENITIES");
        amenities.setBorder(null);
        amenities.setBackground(Color.white);
        amenities.setFont(new Font("Mongolian Baiti", Font.ROMAN_BASELINE, 16));
        amenities.setForeground(Color.black);

        amenities.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Amen();
                frame.dispose();

            }
        });

        JButton rates = new JButton("RATES");
        rates.setBorder(null);
        rates.setBackground(Color.white);
        rates.setFont(new Font("Mongolian Baiti", Font.ROMAN_BASELINE, 16));
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
        home.setFont(new Font("Mongolian Baiti", Font.ROMAN_BASELINE, 16));
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
        about.setFont(new Font("Mongolian Baiti", Font.ROMAN_BASELINE, 16));
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
        book.setFont(new Font("Mongolian Baiti", Font.ROMAN_BASELINE, 16));
        book.setForeground(Color.white);
        book.setFocusPainted(false);
        book.setBorderPainted(false);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));

        buttonPanel.add(amenities);
        buttonPanel.add(rates);
        buttonPanel.add(home);
        buttonPanel.add(about);
        buttonPanel.add(book);

        buttonPanel.setBackground(Color.white);
        buttonPanel.setPreferredSize(new Dimension(500, 30));

        top2.add(buttonPanel, BorderLayout.WEST);
        center.setLayout(null);

        //===========================================================================/
        JLabel packageLabel = new JLabel("Select Utilities (optional)");
        packageLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
        packageLabel.setForeground(Color.black);
        packageLabel.setBounds(50, -25, 500, 200);

        center.add(packageLabel);

        JButton back = new JButton("BACK");
        back.setBackground(new Color(0x556B2F));
        back.setFont(new Font("Tohoma", Font.BOLD, 20));
        back.setForeground(Color.white);
        back.setFocusPainted(false);
        back.setBorderPainted(false);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exist = 1;
                Room();
                frame.dispose();
                reset = 0;

            }
        });

        back.setBounds(50, 500, 180, 50);
        JPanel full = new JPanel(null);
        full.setBounds(50, 100, 1000, 400);
        full.setBackground(Color.white);

        JLabel desc = new JLabel("Kindly check the things you want to rent: ");
        desc.setBounds(0, 20, 300, 20);
        desc.setFont(new Font("Open Sans", Font.BOLD, 15));
        desc.setForeground(Color.black);

        JLabel desc1 = new JLabel("Electric Appliances");
        desc1.setBounds(0, 50, 300, 20);
        desc1.setFont(new Font("Open Sans", Font.PLAIN, 20));
        desc1.setForeground(Color.black);

        JLabel desc2 = new JLabel("₱200 per appliances");
        desc2.setBounds(0, 70, 300, 30);
        desc2.setFont(new Font("Open Sans", Font.PLAIN, 20));
        desc2.setForeground(Color.black);

        JPanel app = new JPanel(new GridLayout(8, 0, 0, 10));
        app.setBackground(Color.white);
        app.setBounds(20, 110, 200, 275);

        opt1.setBackground(Color.white);
        opt1.setFont(new Font("Tohoma", Font.BOLD, 15));

        opt2.setBackground(Color.white);
        opt2.setFont(new Font("Tohoma", Font.BOLD, 15));

        opt3.setBackground(Color.white);
        opt3.setFont(new Font("Tohoma", Font.BOLD, 15));

        opt4.setBackground(Color.white);
        opt4.setFont(new Font("Tohoma", Font.BOLD, 15));
        opt5.setBackground(Color.white);
        opt5.setFont(new Font("Tohoma", Font.BOLD, 15));
        opt6.setBackground(Color.white);
        opt6.setFont(new Font("Tohoma", Font.BOLD, 15));
        opt7.setBackground(Color.white);
        opt7.setFont(new Font("Tohoma", Font.BOLD, 15));

        app.add(opt1);
        app.add(opt2);
        app.add(opt3);
        app.add(opt4);
        app.add(opt5);
        app.add(opt6);
        app.add(opt7);

        JPanel app1 = new JPanel(new GridLayout(8, 0, 0, 10));
        app1.setBackground(Color.white);
        app1.setBounds(300, 110, 500, 275);

        opt8.setBackground(Color.white);
        opt8.setFont(new Font("Tohoma", Font.BOLD, 15));

        opt9.setBackground(Color.white);
        opt9.setFont(new Font("Tohoma", Font.BOLD, 15));

        opt10.setBackground(Color.white);
        opt10.setFont(new Font("Tohoma", Font.BOLD, 15));

        JButton next = new JButton("PAY");
        next.setBackground(new Color(0x556B2F));
        next.setFont(new Font("Tohoma", Font.BOLD, 20));
        next.setForeground(Color.white);
        next.setFocusPainted(false);
        next.setBorderPainted(false);
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();

                if (opt1.isSelected()) {
                    utilPrice += 200;
                    utils += " Electric Fan,";
                    opt1.setSelected(true);
                    electric++;

                }
                if (opt2.isSelected()) {
                    utilPrice += 200;
                    utils += " Electric Kettle,";
                    opt2.setSelected(true);
                    electric++;
                }
                if (opt3.isSelected()) {
                    utilPrice += 200;
                    utils += " Electric Fan,";
                    opt3.setSelected(true);
                    electric++;
                }
                if (opt4.isSelected()) {
                    utilPrice += 200;
                    utils += " Electric Hotpot,";
                    opt4.setSelected(true);
                    electric++;
                }
                if (opt5.isSelected()) {
                    utilPrice += 200;
                    utils += " Rice Cooker,";
                    opt5.setSelected(true);
                    electric++;
                }
                if (opt6.isSelected()) {
                    utilPrice += 200;
                    utils += " Turbo Boiler,";
                    opt6.setSelected(true);
                    electric++;
                }
                if (opt7.isSelected()) {
                    utilPrice += 200;
                    utils += " Sound Speaker,";
                    opt7.setSelected(true);
                    electric++;
                }
                if (opt8.isSelected()) {
                    utilPrice += 500;
                    utils += " Single Burner,";
                    opt8.setSelected(true);
                    burnerL = "Single Burner";
                }
                if (opt9.isSelected()) {
                    utilPrice += 500;
                    utils += " Videoke,";
                    opt9.setSelected(true);
                    Videoke = "Videoke";
                }
                if (opt10.isSelected()) {
                    utils += " Bonfire,";
                    opt10.setSelected(true);
                    Bonfire = "Bonfire";
                }

                if (reset == 1) {
                    if (check1.isSelected()) {
                        roomTP += "Room #1, ";
                        roomPrice += 1500;
                        check1.setSelected(true);

                    }

                    if (check2.isSelected()) {
                        roomTP += "Room #2, ";
                        roomPrice += 1500;
                        check2.setSelected(true);

                    }

                    if (check3.isSelected()) {
                        roomTP += "Room #3, ";
                        roomPrice += 2000;
                        check3.setSelected(true);

                    }

                    if (check4.isSelected()) {
                        roomTP += "Room #4, ";
                        check4.setSelected(true);

                        if (packageTP == "DayTour" || packageTP == "DayNight") {
                            roomPrice += 300;
                        } else {
                            roomPrice += 500;
                        }
                    }
                }

                Payment();

            }
        });

        next.setBounds(850, 500, 180, 50);

        app1.add(opt8);
        app1.add(opt9);
        app1.add(opt10);

        full.add(app);
        full.add(app1);
        full.add(desc);
        full.add(desc1);
        full.add(desc2);
        center.add(next);

        frame.add(design, BorderLayout.NORTH);
        design.add(top, BorderLayout.SOUTH);
        frame.add(bottom, BorderLayout.SOUTH);
        frame.add(right, BorderLayout.EAST);
        frame.add(left, BorderLayout.WEST);
        frame.add(center, BorderLayout.CENTER);

        center.add(back);
        center.add(full);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    /*RECEPTIOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO
        OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO
        OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOON*/
    public static void Payment() {

        JFrame frame = new JFrame();
        frame.setSize(1200, 800);
        frame.setTitle("Luz Ville Resort");

        JPanel design = new JPanel();
        JPanel bottom = new JPanel();
        JPanel right = new JPanel();
        JPanel left = new JPanel();
        JPanel center = new JPanel();
           center.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));

        design.setBackground(Color.black);
        bottom.setBackground(Color.white);
        right.setBackground(Color.white);
        left.setBackground(Color.white);
        center.setBackground(Color.white);

        design.setPreferredSize(new Dimension(200, 120));
        design.setLayout(new BorderLayout());
        design.setBorder(null);

        bottom.setPreferredSize(new Dimension(200, 70));
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
        iconicLogo.setBounds(50, 6, 80, 65);
        top1.add(iconicLogo);

        //=======================================================/
        JButton amenities = new JButton("AMENITIES");
        amenities.setBorder(null);
        amenities.setBackground(Color.white);
        amenities.setFont(new Font("Mongolian Baiti", Font.ROMAN_BASELINE, 16));
        amenities.setForeground(Color.black);

        amenities.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Amen();
                frame.dispose();

            }
        });

        JButton rates = new JButton("RATES");
        rates.setBorder(null);
        rates.setBackground(Color.white);
        rates.setFont(new Font("Mongolian Baiti", Font.ROMAN_BASELINE, 16));
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
        home.setFont(new Font("Mongolian Baiti", Font.ROMAN_BASELINE, 16));
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
        about.setFont(new Font("Mongolian Baiti", Font.ROMAN_BASELINE, 16));
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
        book.setFont(new Font("Mongolian Baiti", Font.ROMAN_BASELINE, 16));
        book.setForeground(Color.white);
        book.setFocusPainted(false);
        book.setBorderPainted(false);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));

        buttonPanel.add(amenities);
        buttonPanel.add(rates);
        buttonPanel.add(home);
        buttonPanel.add(about);
        buttonPanel.add(book);

        buttonPanel.setBackground(Color.white);
        buttonPanel.setPreferredSize(new Dimension(500, 30));

        top2.add(buttonPanel, BorderLayout.WEST);
        center.setLayout(null);

        //===========================================================================/
        JLabel packageLabel = new JLabel("PAYMENT DETAILS");
        packageLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
        packageLabel.setForeground(Color.black);
        packageLabel.setBounds(50, -25, 500, 200);

        center.add(packageLabel);

        JButton back = new JButton("CHANGE");
        back.setBackground(new Color(0x556B2F));
        back.setFont(new Font("Tohoma", Font.BOLD, 20));
        back.setForeground(Color.white);
        back.setFocusPainted(false);
        back.setBorderPainted(false);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exist = 1;
                Utilities();
                frame.dispose();
                electric = 0;
                Videoke = "";
                burnerL = "";
                PackagePrice = 0;
                utilPrice = 0;
                roomPrice = 0;
                roomTP = "";
                reset = 1;

            }
        });

        back.setBounds(50, 500, 180, 50);
        JPanel full = new JPanel(null);
        full.setBounds(50, 100, 1000, 400);
        full.setBackground(Color.white);

        JLabel desc = new JLabel("PAYMENT DETAILS");
        desc.setBounds(0, 20, 300, 20);
        desc.setFont(new Font("Open Sans", Font.BOLD, 15));
        desc.setForeground(Color.black);

        JPanel summary = new JPanel(null);
        summary.setBounds(600, 0, 400, 400);
        Border bottomBorders = BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK);
        summary.setBorder(bottomBorders);

        JLabel summaryL = new JLabel("SUMMARY");
        summaryL.setBounds(20, 20, 300, 20);
        summaryL.setFont(new Font("TOHOMA", Font.BOLD, 25));

        JLabel productsL = new JLabel("Package: ");
        productsL.setBounds(20, 60, 300, 30);
        productsL.setFont(new Font("TOHOMA", Font.BOLD, 20));
        productsL.setForeground(Color.black);

        JLabel products = new JLabel(packageTP + " - " + weekWhat);
        products.setBounds(20, 90, 300, 30);
        summary.add(products);

        JLabel roomL = new JLabel("Room: ");
        roomL.setBounds(20, 130, 300, 30);
        roomL.setFont(new Font("TOHOMA", Font.BOLD, 20));
        roomL.setForeground(Color.black);

        JLabel room = new JLabel(roomTP + mattres);
        room.setBounds(20, 160, 300, 30);
        summary.add(room);

        JLabel utilL = new JLabel("Extra Utilities: ");
        utilL.setBounds(20, 200, 300, 30);
        utilL.setFont(new Font("TOHOMA", Font.BOLD, 20));
        utilL.setForeground(Color.black);

        String elec = String.valueOf(electric);
        JLabel electricApp = new JLabel("x" + elec + " Appliances");
        electricApp.setBounds(20, 230, 300, 30);
        summary.add(electricApp);

        JLabel util1 = new JLabel(burnerL);
        util1.setBounds(20, 250, 300, 30);
        summary.add(util1);

        JLabel util2 = new JLabel(Videoke);
        util2.setBounds(20, 270, 300, 30);
        summary.add(util2);

        JLabel amountL = new JLabel("AMOUNT");
        amountL.setBounds(270, 20, 300, 20);
        amountL.setFont(new Font("Tohoma", Font.BOLD, 25));

        if (packageTP == "DayTour" && weekWhat == "Weekends") {
            PackagePrice = 5000;
            packageCode = "dt-weekends";
        } else if (packageTP == "DayTour" && weekWhat == "Weekdays") {
            PackagePrice = 6000;
            packageCode = "dt-weekdays";
        } else if (packageTP == "DayNight" && weekWhat == "Weekends") {
            PackagePrice = 5000;
            packageCode = "dtn-weekends";
        } else if (packageTP == "DayNight" && weekWhat == "Weekdays") {

            PackagePrice = 6000;
            packageCode = "dtn-weekdays";
        } else if (packageTP == "Overnight" && weekWhat == "Weekends") {
            PackagePrice = 7000;
            packageCode = "ot-weekends";
        } else if (packageTP == "Overnight" && weekWhat == "Weekdays") {
            PackagePrice = 8000;
            packageCode = "ot-weekdays";
        }

        String packageS = String.valueOf(PackagePrice);
        JLabel packageAmount = new JLabel(packageS + " PHP");
        packageAmount.setBounds(270, 90, 300, 20);
        packageAmount.setFont(new Font("Tohoma", Font.PLAIN, 25));
        packageAmount.setForeground(new Color(78, 131, 84));

        String utilS = String.valueOf(utilPrice + " PHP");
        JLabel utilAmount = new JLabel(utilS);
        utilAmount.setBounds(270, 240, 300, 20);
        utilAmount.setFont(new Font("Tohoma", Font.PLAIN, 25));
        utilAmount.setForeground(new Color(78, 131, 84));

        String roomS = String.valueOf(roomPrice);
        JLabel roomAmount = new JLabel(roomS + " PHP");
        roomAmount.setBounds(270, 160, 300, 20);
        roomAmount.setFont(new Font("Tohoma", Font.PLAIN, 25));
        roomAmount.setForeground(new Color(78, 131, 84));

        JPanel paymentSection = new JPanel(new GridBagLayout());
        paymentSection.setBorder(bottomBorders);
        paymentSection.setBounds(20, 300, 300, 50);

        JLabel totalAmount = new JLabel("TOTAL AMOUNT DUE: ");
        totalAmount.setFont(new Font("Tohoma", Font.BOLD, 15));
        totalAmount.setForeground(Color.BLACK);

        totals = PackagePrice + utilPrice + roomPrice;
        String TotalD = String.valueOf(totals);
        JLabel totalAmountR = new JLabel(TotalD + " PHP");
        totalAmountR.setFont(new Font("Tohoma", Font.PLAIN, 25));
        totalAmountR.setForeground(new Color(78, 131, 84));

        JLabel payL = new JLabel("Payment Methods");
        payL.setBounds(0, -75, 200, 200);
        payL.setFont(new Font("Tohoma", Font.BOLD, 18));
        payL.setForeground(Color.black);

        JRadioButton GCash = new JRadioButton("GCash");
        JRadioButton Cash = new JRadioButton("Cash / 2000 Reservation FEE");
        GCash.setFont(new Font("Tohoma", Font.BOLD, 18));
        GCash.setForeground(Color.black);
        Cash.setFont(new Font("Tohoma", Font.BOLD, 18));
        Cash.setForeground(Color.black);

        ButtonGroup group = new ButtonGroup();
        group.add(GCash);
        group.add(Cash);

        GCash.setBounds(0, 50, 200, 30);
        Cash.setBounds(200, 50, 300, 30);
        GCash.setBackground(Color.white);
        Cash.setBackground(Color.white);

        JLabel amountPay = new JLabel("Enter Amount : ");
        amountPay.setBounds(0, 25, 200, 200);
        amountPay.setFont(new Font("Tohoma", Font.BOLD, 18));
        amountPay.setForeground(Color.black);

        JTextField textField = new JTextField();
        textField.setBounds(0, 150, 200, 30);
        textField.setFont(new Font("Tohoma", Font.BOLD, 18));
        textField.setForeground(Color.black);

        JButton pay = new JButton("PAY");
        pay.setBackground(new Color(0x556B2F));
        pay.setFont(new Font("Tohoma", Font.BOLD, 20));
        pay.setForeground(Color.white);
        pay.setFocusPainted(false);
        pay.setBorderPainted(false);
        pay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String amountPaid = textField.getText();
                if (amountPaid.equals(TotalD) && GCash.isSelected()) {
                    int response = JOptionPane.showConfirmDialog(null, "Do you want to continue?", "Confirm",
                            JOptionPane.YES_NO_OPTION);
                    if (response == JOptionPane.YES_OPTION) {
                        reservationTP = "Fully Paid";

                        paymentMethod = "GCASH";
                        paymentDate = LocalDate.now();
                        ReservationFee = 0;
                        Amount = totals;
                        Status = "Paid";
                        INSERTION();
                        
                        Reception();
                        frame.dispose();

                    }

                } else if (amountPaid.equals("2000") && Cash.isSelected()) {

                    int response = JOptionPane.showConfirmDialog(null, "Do you want to continue?", "Confirm",
                            JOptionPane.YES_NO_OPTION);
                    if (response == JOptionPane.YES_OPTION) {
                        reservationTP = "Paid Reservation Fee";

                        paymentMethod = "CASH";
                        paymentDate = LocalDate.now();
                        ReservationFee = 2000;
                        Amount = totals;
                        Status = "Pending";

                        INSERTION();
                       
                        Reception();
                        frame.dispose();

                    }

                } else if (Double.parseDouble(amountPaid) > 2000 && Cash.isSelected() || Double.parseDouble(amountPaid) < 2000 && Cash.isSelected() ) {

                    JOptionPane.showMessageDialog(null, "The reservation fee for cash transactions is ₱2000.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                    else if (Double.parseDouble(amountPaid) > totals && GCash.isSelected()) {
                            JOptionPane.showMessageDialog(null, "Amount exceeds", "Error", JOptionPane.ERROR_MESSAGE);
                           }
                else if (Double.parseDouble(amountPaid) < totals && GCash.isSelected()) {
                            JOptionPane.showMessageDialog(null, "Invalid amount entered.", "Error", JOptionPane.ERROR_MESSAGE);
                           }
             
            }
        });

        pay.setBounds(0, 200, 200, 30);

        full.add(GCash);
        full.add(Cash);
        full.add(payL);
        full.add(textField);
        full.add(amountPay);
        full.add(pay);
        paymentSection.add(totalAmount);
        paymentSection.add(totalAmountR);

        summary.add(utilAmount);
        summary.add(roomAmount);
        summary.add(packageAmount);
        summary.add(paymentSection);
        summary.add(roomL);
        summary.add(utilL);
        summary.add(summaryL);
        summary.add(amountL);
        summary.add(productsL);

        frame.add(design, BorderLayout.NORTH);
        design.add(top, BorderLayout.SOUTH);
        frame.add(bottom, BorderLayout.SOUTH);
        frame.add(right, BorderLayout.EAST);
        frame.add(left, BorderLayout.WEST);
        frame.add(center, BorderLayout.CENTER);

        full.add(summary);
        center.add(back);
        center.add(full);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    /*RECEPTIOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO
        OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO
        OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOON*/
    public static void Reception() {
        JFrame frame = new JFrame();
        frame.setSize(1200, 800);
        frame.setTitle("Luz Ville Resort");

        JPanel design = new JPanel();
        JPanel bottom = new JPanel();
        JPanel right = new JPanel();
        JPanel left = new JPanel();
        JPanel center = new JPanel();
            center.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));

        design.setBackground(Color.black);
        bottom.setBackground(Color.white);
        right.setBackground(Color.white);
        left.setBackground(Color.white);
        center.setBackground(Color.white);

        design.setPreferredSize(new Dimension(200, 120));
        design.setLayout(new BorderLayout());
        design.setBorder(null);

        bottom.setPreferredSize(new Dimension(200, 70));
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
        iconicLogo.setBounds(50, 6, 80, 65);
        top1.add(iconicLogo);

        //=======================================================/
        JButton amenities = new JButton("AMENITIES");
        amenities.setBorder(null);
        amenities.setBackground(Color.white);
        amenities.setFont(new Font("Mongolian Baiti", Font.ROMAN_BASELINE, 16));
        amenities.setForeground(Color.black);

        amenities.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CheckIN = null;
                CheckOUT = null;
                packagesK.setText(" ");
                reservationTP = "";
                fname.setText("");
                lname.setText("");
                mname.setText("");
                contact.setText("");
                chickin.setText("");
                chickout.setText("");
                reset = 0;
                weekWhat = "";
                first = null;
                middle = null;
                last = null;
                phoneNo = null;
                Videoke = "";
                burnerL = "";
                PackagePrice = 0;
                utilPrice = 0;
                roomPrice = 0;
                roomTP = "";
                utils = "";
                electric = 0;
                
                opt1.setSelected(false);
                opt2.setSelected(false);
                opt3.setSelected(false);
                opt4.setSelected(false);
                opt5.setSelected(false);
                opt6.setSelected(false);
                opt7.setSelected(false);
                opt8.setSelected(false);
                opt9.setSelected(false);
                opt10.setSelected(false);
                check1.setSelected(false);
                check2.setSelected(false);
                check3.setSelected(false);
                check4.setSelected(false);

                
                new Amen();
                frame.dispose();

            }
        });

        JButton rates = new JButton("RATES");
        rates.setBorder(null);
        rates.setBackground(Color.white);
        rates.setFont(new Font("Mongolian Baiti", Font.ROMAN_BASELINE, 16));
        rates.setForeground(Color.black);
        rates.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CheckIN = null;
                CheckOUT = null;
                packagesK.setText(" ");
                reservationTP = "";
                fname.setText("");
                lname.setText("");
                mname.setText("");
                contact.setText("");
                chickin.setText("");
                chickout.setText("");
                reset = 0;
                weekWhat = "";
                first = null;
                middle = null;
                last = null;
                phoneNo = null;
                Videoke = "";
                burnerL = "";
                PackagePrice = 0;
                utilPrice = 0;
                roomPrice = 0;
                roomTP = "";
                utils = "";
                electric = 0;
                
                opt1.setSelected(false);
                opt2.setSelected(false);
                opt3.setSelected(false);
                opt4.setSelected(false);
                opt5.setSelected(false);
                opt6.setSelected(false);
                opt7.setSelected(false);
                opt8.setSelected(false);
                opt9.setSelected(false);
                opt10.setSelected(false);
                check1.setSelected(false);
                check2.setSelected(false);
                check3.setSelected(false);
                check4.setSelected(false);

                
                new Rates();
                frame.dispose();

            }
        });

        JButton home = new JButton("HOME");
        home.setBorder(null);
        home.setBackground(Color.white);
        home.setFont(new Font("Mongolian Baiti", Font.ROMAN_BASELINE, 16));
        home.setForeground(Color.black);

        home.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CheckIN = null;
                CheckOUT = null;
                packagesK.setText(" ");
                reservationTP = "";
                fname.setText("");
                lname.setText("");
                mname.setText("");
                contact.setText("");
                chickin.setText("");
                chickout.setText("");
                reset = 0;
                weekWhat = "";
                first = null;
                middle = null;
                last = null;
                phoneNo = null;
                Videoke = "";
                burnerL = "";
                PackagePrice = 0;
                utilPrice = 0;
                roomPrice = 0;
                roomTP = "";
                utils = "";
                electric = 0;
                
                opt1.setSelected(false);
                opt2.setSelected(false);
                opt3.setSelected(false);
                opt4.setSelected(false);
                opt5.setSelected(false);
                opt6.setSelected(false);
                opt7.setSelected(false);
                opt8.setSelected(false);
                opt9.setSelected(false);
                opt10.setSelected(false);
                check1.setSelected(false);
                check2.setSelected(false);
                check3.setSelected(false);
                check4.setSelected(false);

                
                
                new Home();
                frame.dispose();

            }
        });

        JButton about = new JButton("ABOUT");
        about.setBorder(null);
        about.setBackground(Color.white);
        about.setFont(new Font("Mongolian Baiti", Font.ROMAN_BASELINE, 16));
        about.setForeground(Color.black);

        about.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CheckIN = null;
                CheckOUT = null;
                packagesK.setText(" ");
                reservationTP = "";
                fname.setText("");
                lname.setText("");
                mname.setText("");
                contact.setText("");
                chickin.setText("");
                chickout.setText("");
                reset = 0;
                weekWhat = "";
                first = null;
                middle = null;
                last = null;
                phoneNo = null;
                Videoke = "";
                burnerL = "";
                PackagePrice = 0;
                utilPrice = 0;
                roomPrice = 0;
                roomTP = "";
                utils = "";
                electric = 0;
                
                opt1.setSelected(false);
                opt2.setSelected(false);
                opt3.setSelected(false);
                opt4.setSelected(false);
                opt5.setSelected(false);
                opt6.setSelected(false);
                opt7.setSelected(false);
                opt8.setSelected(false);
                opt9.setSelected(false);
                opt10.setSelected(false);
                check1.setSelected(false);
                check2.setSelected(false);
                check3.setSelected(false);
                check4.setSelected(false);
                
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
        
         book.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CheckIN = null;
                CheckOUT = null;
                packagesK.setText(" ");
                reservationTP = "";
                fname.setText("");
                lname.setText("");
                mname.setText("");
                contact.setText("");
                chickin.setText("");
                chickout.setText("");
                reset = 0;
                weekWhat = "";
                first = null;
                middle = null;
                last = null;
                phoneNo = null;
                Videoke = "";
                burnerL = "";
                PackagePrice = 0;
                utilPrice = 0;
                roomPrice = 0;
                roomTP = "";
                utils = "";
                electric = 0;
                
                opt1.setSelected(false);
                opt2.setSelected(false);
                opt3.setSelected(false);
                opt4.setSelected(false);
                opt5.setSelected(false);
                opt6.setSelected(false);
                opt7.setSelected(false);
                opt8.setSelected(false);
                opt9.setSelected(false);
                opt10.setSelected(false);
                check1.setSelected(false);
                check2.setSelected(false);
                check3.setSelected(false);
                check4.setSelected(false);
                
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
        center.setLayout(null);

        //===========================================================================/
        JLabel packageLabel = new JLabel("Receipt Date");
        packageLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
        packageLabel.setForeground(Color.black);
        packageLabel.setBounds(50, -25, 500, 200);

        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        String dateString = date.format(formatter);

        JLabel dateLabel = new JLabel(dateString + " - " + reservationTP);
        dateLabel.setForeground(Color.black);
        dateLabel.setBounds(50, 0, 500, 200);

        center.add(dateLabel);

        JLabel bookBy = new JLabel("Book by: ");
        bookBy.setFont(new Font("Tahoma", Font.BOLD, 15));
        bookBy.setForeground(Color.black);
        bookBy.setBounds(500, -70, 500, 200);
        JLabel name = new JLabel("Name: ");
        name.setFont(new Font("Tahoma", Font.BOLD, 15));
        name.setForeground(Color.black);
        name.setBounds(500, -45, 500, 200);

        JLabel nameF = new JLabel(first + " " + middle + " " + last);
        nameF.setFont(new Font("Tahoma", Font.PLAIN, 15));
        nameF.setForeground(Color.black);
        nameF.setBounds(575, -45, 500, 200);

        JLabel contactN = new JLabel("Contact: ");
        contactN.setFont(new Font("Tahoma", Font.BOLD, 15));
        contactN.setForeground(Color.black);
        contactN.setBounds(500, -20, 500, 200);

        JLabel contactR = new JLabel(phoneNo);
        contactR.setFont(new Font("Tahoma", Font.PLAIN, 15));
        contactR.setForeground(Color.black);
        contactR.setBounds(575, -20, 500, 200);

        JPanel full = new JPanel(null);
        full.setBounds(50, 100, 1000, 400);
        full.setBackground(Color.white);

        JPanel Line1 = new JPanel();
        JPanel Line2 = new JPanel();
        JPanel Line3 = new JPanel();
        JPanel Line4 = new JPanel();
        JPanel Line5 = new JPanel();
        JPanel Line6 = new JPanel();

        JLabel packageL = new JLabel("Package: ");
        packageL.setBounds(50, 0, 100, 100);
        Line1.setBounds(0, 60, 1000, 2);

        String packageP = String.valueOf(PackagePrice + " PHP");
        JLabel packageAmount = new JLabel(packageP);
        packageAmount.setBounds(850, 30, 100, 100);
        full.add(packageAmount);

        JLabel packageT = new JLabel(packageTP);
        packageT.setBounds(55, 30, 100, 100);

        JLabel amount = new JLabel("Amount: ");
        amount.setBounds(850, 0, 100, 100);

        JLabel roomT = new JLabel("Room Type: ");
        roomT.setBounds(50, 65, 100, 100);
        Line2.setBounds(0, 100, 1000, 2);

        String roomPs = String.valueOf(roomPrice + " PHP");
        JLabel roomP = new JLabel(roomPs);
        roomP.setBounds(850, 100, 100, 100);
        full.add(roomP);

        JLabel roomTPY = new JLabel(roomTP);
        roomTPY.setBounds(55, 100, 300, 100);

        Line3.setBounds(0, 130, 1000, 2);
        Line4.setBounds(0, 170, 1000, 2);
        Line5.setBounds(0, 200, 1000, 2);
        Line6.setBounds(0, 240, 1000, 2);

        JLabel util = new JLabel("Utilities: ");
        util.setBounds(50, 135, 100, 100);

        String utilP = String.valueOf(utilPrice + " PHP");
        JLabel utilPr = new JLabel(utilP);
        utilPr.setBounds(850, 170, 100, 100);
        full.add(utilPr);

        JLabel utilL = new JLabel(utils);
        utilL.setBounds(55, 170, 500, 100);

        int totals = PackagePrice + roomPrice + utilPrice;
        String Price = String.valueOf(totals);
        JLabel totalAmount = new JLabel("Total Amount : " + Price + " PHP");
        totalAmount.setBounds(800, 210, 500, 100);
        full.add(totalAmount);

        full.add(utilL);
        full.add(packageL);
        full.add(roomTPY);
        full.add(packageT);
        full.add(amount);
        full.add(Line1);
        full.add(roomT);
        full.add(Line2);
        full.add(Line3);
        full.add(Line4);
        full.add(Line5);
        full.add(util);
        full.add(Line6);

        center.add(bookBy);
        center.add(name);
        center.add(nameF);
        center.add(contactN);
        center.add(contactR);
        center.add(packageLabel);

        JButton next = new JButton("BOOK ANOTHER");
        next.setBackground(new Color(0x556B2F));
        next.setFont(new Font("Tohoma", Font.BOLD, 15));
        next.setForeground(Color.white);
        next.setFocusPainted(false);
        next.setBorderPainted(false);
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CheckIN = null;
                CheckOUT = null;
                packagesK.setText(" ");
                reservationTP = "";
                fname.setText("");
                lname.setText("");
                mname.setText("");
                contact.setText("");
                chickin.setText("");
                chickout.setText("");
                reset = 0;
                weekWhat = "";
                first = null;
                middle = null;
                last = null;
                phoneNo = null;
                Videoke = "";
                burnerL = "";
                PackagePrice = 0;
                utilPrice = 0;
                roomPrice = 0;
                roomTP = "";
                utils = "";
                electric = 0;
                
                opt1.setSelected(false);
                opt2.setSelected(false);
                opt3.setSelected(false);
                opt4.setSelected(false);
                opt5.setSelected(false);
                opt6.setSelected(false);
                opt7.setSelected(false);
                opt8.setSelected(false);
                opt9.setSelected(false);
                opt10.setSelected(false);
                check1.setSelected(false);
                check2.setSelected(false);
                check3.setSelected(false);
                check4.setSelected(false);
                new Book();
                frame.dispose();

            }
        });

        next.setBounds(850, 500, 180, 50);

        frame.add(design, BorderLayout.NORTH);
        design.add(top, BorderLayout.SOUTH);
        frame.add(bottom, BorderLayout.SOUTH);
        frame.add(right, BorderLayout.EAST);
        frame.add(left, BorderLayout.WEST);
        frame.add(center, BorderLayout.CENTER);

        center.add(next);

        center.add(full);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

    }

    public static void main(String[] args) {
        Book pro = new Book();
        pro.createConnection();
   

    }

}
