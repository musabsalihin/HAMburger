import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class home extends GUI implements ActionListener {
    public static Order personalOrder = new Order();
    public static JPanel panelOrder;
    public static int totalOrder = 0;
    
    private static JTabbedPane panelMenu;
    private static JPanel panelBurger;
    private static JPanel panelSide;
    private static JPanel panelHot;
    private static JPanel panelCold;
    private static JPanel panelCalculator;
    private static Date date;
    private JLabel amt;
    private JTextField totalPrice;

    // border and margin
    private final int burger_BUTTON = 140;
    private final int WIDTH_MENU = 3 * burger_BUTTON;
    private final int HEIGHT_MENU = 4 * burger_BUTTON;
    private final int WIDTH_ORDER = 260;
    private final int HEIGHT_CALC = 130;
    private final int WIDTH_FRAME = WIDTH_MENU + WIDTH_ORDER;
    private final int WIDTH_CALC = WIDTH_FRAME - WIDTH_ORDER;
    private final int HEIGHT_FRAME = HEIGHT_MENU + HEIGHT_CALC + 30;
    private final int HEIGHT_ORDER = HEIGHT_MENU;

    public static FileWriter out;

    // private Icon burger;

    public home() {
        try {
            out = new FileWriter("daily_entry.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        frameHome = new JFrame("Home");
        frameHome.setSize(WIDTH_FRAME, HEIGHT_FRAME);
        frameHome.setLocationRelativeTo(null);
        frameHome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // burger panel.. list all burger available for the restaurant
        // panelMenu = new JPanel();
        panelMenu = new JTabbedPane();
        panelBurger = new JPanel();
        panelSide = new JPanel();
        panelHot = new JPanel();
        panelCold = new JPanel();
        // panelMenu.setLayout(null);
        panelMenu.setBounds(0, 0, WIDTH_MENU, HEIGHT_MENU);

        panelBurger.setLayout(null);
        panelBurger.setBounds(0, 0, WIDTH_MENU, HEIGHT_MENU);

        panelSide.setLayout(null);
        panelSide.setBounds(0, 0, WIDTH_MENU, HEIGHT_MENU);

        panelHot.setLayout(null);
        panelHot.setBounds(0, 0, WIDTH_MENU, HEIGHT_MENU);

        panelCold.setLayout(null);
        panelCold.setBounds(0, 0, WIDTH_MENU, HEIGHT_MENU);
        // panelburger.setLayout(new BorderLayout());

        panelOrder = new JPanel();
        panelOrder.setLayout(null);
        panelOrder.setBounds(WIDTH_MENU, 0, WIDTH_ORDER, HEIGHT_ORDER);
        panelOrder.setBackground(Color.white);
        // panelOrder.setLayout(new BorderLayout());

        panelCalculator = new JPanel();
        panelCalculator.setLayout(null);
        panelCalculator.setBounds(HEIGHT_MENU + 10, 0, WIDTH_CALC, HEIGHT_CALC);
        panelCalculator.setBackground(Color.blue);

        // take date from system
        date = new Date();

        /// burger list

        // create new file
        // file = new

        Burger.getAllBurger();
        Side.getAllSide();
        Cold_Drinks.getAllCold();
        Hot_Drinks.getAllHot();

    }

    public static String getDate_toString() {
        return ("" + date.getDate() + "/" + (date.getMonth() + 1) + "/" + (1900 + date.getYear()));
    }

    public void Open() {

        // frameHome.add(panelburger,BorderLayout.WEST);

        JLabel name = new JLabel("Name");
        JLabel price = new JLabel("Price");
        JLabel qty = new JLabel("Qty");
        name.setBounds(0, 0, 150, 20);
        qty.setBounds(150, 0, 150, 20);
        price.setBounds(200, 0, 150, 20);
        panelOrder.add(name);
        panelOrder.add(qty);
        panelOrder.add(price);

        personalOrder.setDate(date);
        personalOrder.setOrderID("INV000" + totalOrder);

        // update order whenever a burger was added or removed
        JButton new_order = new JButton("New Order");
        new_order.setBounds(0, HEIGHT_MENU, 140, HEIGHT_CALC);

        new_order.setVisible(true);
        new_order.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                personalOrder.newOrder(totalPrice);
            }
        });

        panelCalculator.add(new_order);

        JButton delOrder = new JButton(new AbstractAction("Delete Order") {
            public void actionPerformed(ActionEvent e) {
                personalOrder.delOrder(totalPrice);
                totalPrice.setText("" + personalOrder.getTotalPrice());

            }
        });
        delOrder.setBounds(140, HEIGHT_MENU, 140, HEIGHT_CALC);
        delOrder.setVisible(true);
        JButton editOrder = new JButton(new AbstractAction("Edit Order") {
            public void actionPerformed(ActionEvent e) {
                personalOrder.editOrder(totalPrice);
                totalPrice.setText("" + personalOrder.getTotalPrice());
            }
        });
        editOrder.setBounds(140 * 2, HEIGHT_MENU, 140, HEIGHT_CALC);
        editOrder.setVisible(true);

        amt = new JLabel("Amount: RM");
        amt.setFont(amt.getFont().deriveFont(24.0f));
        amt.setBounds(420, 613, 160, 50);
        amt.setForeground(Color.white);

        totalPrice = new JTextField("" + personalOrder.getTotalPrice());
        totalPrice.setBackground(Color.white);
        totalPrice.setEditable(false);
        totalPrice.setFont(totalPrice.getFont().deriveFont(24.0f));
        totalPrice.setBounds(570, 610, 90, 50);

        panelBurger = Burger.addBurger(panelBurger, totalPrice);
        panelSide = Side.addSide(panelSide, totalPrice);
        panelHot = Hot_Drinks.addHot(panelHot, totalPrice);
        panelCold = Cold_Drinks.addCold(panelCold, totalPrice);

        panelOrder = personalOrder.previewOrder(panelOrder);

        JButton printOrder = new JButton(new AbstractAction("Print Receipt") {
            public void actionPerformed(ActionEvent e) {
                personalOrder.viewOrder(totalPrice);
            }
        });

        printOrder.setBounds(420, HEIGHT_MENU, WIDTH_ORDER, 40);
        printOrder.setVisible(true);

        panelMenu.add("Burger", panelBurger);
        panelMenu.add("Side", panelSide);
        panelMenu.add("Hot Drinks", panelHot);
        panelMenu.add("Cold Drinks", panelCold);

        panelCalculator.add(printOrder);
        panelCalculator.add(delOrder);
        panelCalculator.add(editOrder);
        panelCalculator.add(amt);
        panelCalculator.add(totalPrice);

        frameHome.add(panelOrder);
        frameHome.add(panelMenu);
        frameHome.add(panelCalculator);

    }

    public void newOrder() {
        totalOrder += 1;
        System.out.println("New order");
        personalOrder = new Order();
        personalOrder.setOrderID("INV000" + totalOrder);
        date = new Date();
        personalOrder.setDate(date);
        totalPrice.setText("" + personalOrder.getTotalPrice());
        panelOrder = personalOrder.previewOrder(panelOrder);

    }

    public void actionPerformed(ActionEvent e) {
        String username = userField.getText();
        String password = pwField.getText();

        if (username.equals("admin") && password.equals("admin123")) {
            successful.setText("Login successful!");
            successful.setForeground(Color.black);
            JOptionPane.showMessageDialog(frame, successful);
            this.Open();
            // frame.setVisible(false);
            frame.dispose();
            frameHome.setVisible(true);

        } else {
            userField.setText(null);
            pwField.setText(null);
            successful.setText("Login failed.");
            successful.setForeground(Color.red);
            JOptionPane.showMessageDialog(frame, successful);
        }
    }
}
