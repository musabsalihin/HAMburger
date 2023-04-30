import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Order {
    private String orderID;
    private double totalPrice;
    private Date date;
    public ArrayList<Menu> allOrder = new ArrayList<Menu>();

    private FileWriter out;

    // add list for all order

    public Order() {
        totalPrice = 0;
        // try {
        // out = new FileWriter("daily_entry.txt");
        // } catch (IOException e) {
        // e.printStackTrace();
        // }
    }

    public void setOrderID(String orderID) {

        this.orderID = orderID;
    }

    public void addTotalPrice(double totalPrice) {
        this.totalPrice += totalPrice;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getOrderID() {
        return this.orderID;
    }

    public double getTotalPrice() {
        return this.totalPrice;
    }

    public Date getDate() {
        return this.date;
    }

    public String getDate_toString() {
        return this.date.getDate() + "/" + (this.date.getMonth() + 1) + "/" + (1900 + this.date.getYear());
    }

    public void newOrder(JTextField totalPrice) {
        home.totalOrder += 1;
        System.out.println("New order");
        home.personalOrder = new Order();
        home.personalOrder.setOrderID("INV000" + home.totalOrder);
        date = new Date();
        home.personalOrder.setDate(date);
        home.panelOrder = home.personalOrder.previewOrder(home.panelOrder);
        totalPrice.setText("" + home.personalOrder.getTotalPrice());

    }

    public JPanel previewOrder(JPanel panel) {

        JPanel panelList = new JPanel();

        panelList.setLayout(null);

        panel.setVisible(false);

        for (int i = 0; i < this.allOrder.size(); i++) {
            JTextField labelOrder = new JTextField("");
            JTextField labelPrice = new JTextField("");
            JTextField labelQty = new JTextField("");
            labelOrder.setEditable(false);
            labelQty.setEditable(false);
            labelPrice.setEditable(false);

            labelOrder.setBounds(0, i * 20, 150, 20);
            panelList.add(labelOrder);
            labelQty.setBounds(150, i * 20, 30, 20);
            panelList.add(labelQty);
            labelPrice.setBounds(180, i * 20, 80, 20);
            panelList.add(labelPrice);

            labelOrder.setText(this.allOrder.get(i).getName());
            // labelQty.setText("" + ((Burger) this.allOrder.get(i)).getQty());
            if (allOrder.get(i).getType().equals("Burger"))
                labelQty.setText("" + ((Burger) this.allOrder.get(i)).getQty());

            else if (allOrder.get(i).getType().equals("Side"))
                labelQty.setText("" + ((Side) this.allOrder.get(i)).getQty());

            else if (allOrder.get(i).getType().equals("Hot Drinks"))
                labelQty.setText("" + ((Hot_Drinks) this.allOrder.get(i)).getQty());

            else if (allOrder.get(i).getType().equals("Cold Drinks"))
                labelQty.setText("" + ((Cold_Drinks) this.allOrder.get(i)).getQty());

            labelPrice.setText("RM" + this.allOrder.get(i).getPrice());
        }

        panelList.setBounds(0, 20, 260, 540);
        panel.add(panelList);
        panel.setVisible(true);

        return panel;

    }

    public void viewOrder(JTextField totalPrice) {
        JFrame frame = new JFrame("Receipt");
        JPanel panel = new JPanel();
        JPanel panelList = new JPanel();

        frame.setSize(300, 800);
        frame.setLocationRelativeTo(null);
        // panel.setLayout(new GridLayout(0,1));
        panel.setLayout(null);
        // panelList.setLayout(new GridLayout(0,2));
        panelList.setLayout(null);
        frame.add(panel);
        JLabel labelOrderID = new JLabel("");
        JLabel labeltotalPrice = new JLabel("");
        JLabel labelDate = new JLabel("");
        // print all menu
        labelOrderID.setText("Order ID: " + this.orderID);
        labelDate.setText("Date: " + getDate_toString());
        labelOrderID.setBounds(100, 0, 100, 20);
        labelDate.setBounds(105, 20, 100, 20);
        panel.add(labelOrderID);
        panel.add(labelDate);
        JLabel name = new JLabel("Name");
        JLabel price = new JLabel("Price");
        JLabel qty = new JLabel("Qty");
        name.setBounds(20, 40, 150, 20);
        qty.setBounds(150, 40, 150, 20);
        price.setBounds(200, 40, 150, 20);
        panel.add(name);
        panel.add(qty);
        panel.add(price);

        for (int i = 0; i < this.allOrder.size(); i++) {
            JLabel labelOrder = new JLabel("");
            JLabel labelPrice = new JLabel("");
            JLabel labelQty = new JLabel("");
            labelOrder.setBounds(20, (i) * 20, 150, 30);
            labelQty.setBounds(150, (i) * 20, 150, 30);
            // labelOrder.setBounds(50,(i+1)*30,150,30);
            // labelPrice.setBounds(200,(i+1)*30,150,30);
            labelPrice.setBounds(200, (i) * 20, 150, 30);
            panelList.add(labelOrder);
            panelList.add(labelQty);
            panelList.add(labelPrice);
            labelOrder.setText(this.allOrder.get(i).getName());
            // labelQty.setText("" + ((Burger) this.allOrder.get(i)).getQty());
            if (allOrder.get(i).getType().equals("Burger"))
                labelQty.setText("" + ((Burger) this.allOrder.get(i)).getQty());

            else if (allOrder.get(i).getType().equals("Side"))
                labelQty.setText("" + ((Side) this.allOrder.get(i)).getQty());

            else if (allOrder.get(i).getType().equals("Hot Drinks"))
                labelQty.setText("" + ((Hot_Drinks) this.allOrder.get(i)).getQty());

            else if (allOrder.get(i).getType().equals("Cold Drinks"))
                labelQty.setText("" + ((Cold_Drinks) this.allOrder.get(i)).getQty());

            labelPrice.setText("RM" + this.allOrder.get(i).getPrice());
        }
        labeltotalPrice.setText("Amount: RM" + this.totalPrice);
        labeltotalPrice.setFont(labeltotalPrice.getFont().deriveFont(24.0f));

        labeltotalPrice.setBounds(50, 90 + allOrder.size() * 20, 250, 40);

        JButton confirm = new JButton("Confirm");
        confirm.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                confirmOrder(frame, totalPrice);
            }
        });
        confirm.setBounds(70, allOrder.size() * 20 + 140, 150, 40);
        panelList.setBounds(0, 60, 300, (allOrder.size() * 20) + 30);
        panel.add(panelList);
        panel.add(labeltotalPrice);
        panel.add(confirm);
        int height = allOrder.size() * 30 + 220;
        frame.setSize(300, height);
        frame.setVisible(true);

    }

    public void confirmOrder(JFrame frame, JTextField totalPrice) {
        // addorder to txt
        // openfile\
        System.out.println("pressed");
        try {
            FileWriter out = new FileWriter("daily_entry.txt", true);
            // out.write("test");
            out.append("\nOrderID: " + this.orderID);
            out.append("\nDate: " + this.getDate_toString() + "\n");

            for (int i = 0; i < this.allOrder.size(); i++) {
                ;

                out.append((i + 1) + ". " + this.allOrder.get(i).getName());
                // labelQty.setText("" + ((Burger) this.allOrder.get(i)).getQty());
                if (allOrder.get(i).getType().equals("Burger"))
                    out.append("\n\t" + ((Burger) this.allOrder.get(i)).getQty());

                else if (allOrder.get(i).getType().equals("Side"))
                    out.append("\n\tQty: " + ((Side) this.allOrder.get(i)).getQty());

                else if (allOrder.get(i).getType().equals("Hot Drinks"))
                    out.append("\n\tQty: " + ((Hot_Drinks) this.allOrder.get(i)).getQty());

                else if (allOrder.get(i).getType().equals("Cold Drinks"))
                    out.append("\n\tQty: " + ((Cold_Drinks) this.allOrder.get(i)).getQty());

                out.append("\n\tPrice: RM" + this.allOrder.get(i).getPrice() + "\n");
            }
            out.append("Amount: RM" + this.totalPrice + "\n");
            home.totalOrder += 1;
            System.out.println("New order");
            home.personalOrder = new Order();
            home.personalOrder.setOrderID("INV000" + home.totalOrder);
            date = new Date();
            home.personalOrder.setDate(date);
            home.panelOrder = home.personalOrder.previewOrder(home.panelOrder);
            totalPrice.setText("" + home.personalOrder.getTotalPrice());
            out.close();

        } catch (IOException e) {
            System.out.println("File not found");
        }

        frame.dispose();
        // write into file

    }

    public void editOrder(JTextField totalP) {
        JFrame frame = new JFrame();
        frame.setSize(400, 600);
        frame.setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        panel.setSize(400, 800);

        panel.setLayout(new GridLayout(0, 1));

        // panel.setLayout(null);
        // panel2.setLayout(null);
        for (int i = 0; i < allOrder.size(); i++) {
            JButton list = new JButton(allOrder.get(i).getName());
            list.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Iterator it = allOrder.iterator();

                    while (it.hasNext()) {

                        Menu to_edit = (Menu) it.next();

                        String order_name = (String) (to_edit.getName());
                        if (order_name.equals(((JButton) e.getSource()).getText())) {
                            System.out.println(to_edit.getType());
                            if (to_edit.getType().equals("Burger")) {
                                Burger food = (Burger) to_edit;
                                Burger.editQty(food);
                            } else if (to_edit.getType().equals("Side")) {
                                Side food = (Side) to_edit;
                                Side.editQty(food);
                            } else if (to_edit.getType().equals("Hot Drinks")) {
                                Hot_Drinks food = (Hot_Drinks) to_edit;
                                Hot_Drinks.editQty(food);
                            } else if (to_edit.getType().equals("Cold Drinks")) {
                                Cold_Drinks food = (Cold_Drinks) to_edit;
                                Cold_Drinks.editQty(food);
                            }

                        }
                    }
                }
            });
            // list.setBounds(0,i*100,400,100);
            // panel2.add(list);
            list.setFont(list.getFont().deriveFont(36.0f));
            panel.add(list);
        }
        // panel.add(panel2);
        JScrollPane pane = new JScrollPane(panel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        frame.add(pane);

        frame.setVisible(true);

    }

    public void delOrder(JTextField totalP) {
        JFrame frame = new JFrame();
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        JPanel panel = new JPanel();

        panel.setLayout(new GridLayout(0, 1));
        // panel.setSize(500, 500);
        // panel.setLayout(null);
        // panel2.setLayout(null);
        for (int i = 0; i < allOrder.size(); i++) {
            JButton list = new JButton(allOrder.get(i).getName());
            list.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Iterator it = allOrder.iterator();

                    while (it.hasNext()) {

                        Menu to_del = (Menu) it.next();
                        String order_name = (String) (to_del.getName());
                        if (order_name.equals(((JButton) e.getSource()).getText())) {
                            // System.out.println(((Menu) it.next()).getPrice());
                            totalPrice -= (to_del).getPrice();
                            totalP.setText("" + getTotalPrice());
                            it.remove();
                            home.panelOrder = home.personalOrder.previewOrder(home.panelOrder);
                            System.out.println("deleted");
                            break;
                        }
                    }
                    list.setVisible(false);
                }
            });
            // list.setBounds(0,i*100,400,100);
            // panel2.add(list);
            list.setFont(list.getFont().deriveFont(36.0f));
            panel.add(list);
        }
        // panel.add(panel2);
        JScrollPane pane = new JScrollPane(panel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        frame.add(pane);

        frame.setVisible(true);

    }

    public boolean checkOrder(String sent_name, double sent_price, JTextField totalPrice) {
        Iterator it = allOrder.iterator();

        while (it.hasNext()) {

            Menu to_check = (Menu) it.next();
            String order_name = (String) (to_check.getName());
            if (order_name.equals(sent_name)) {
                // System.out.println(((Menu) it.next()).getPrice());
                addTotalPrice(sent_price);
                // personalOrder.addTotalPrice((to_check).getPrice());
                totalPrice.setText("" + getTotalPrice());
                // it.remove();
                if (to_check.getType().equals("Burger"))
                    ((Burger) to_check).addQty(sent_price);
                else if (to_check.getType().equals("Side"))
                    ((Side) to_check).addQty(sent_price);
                else if (to_check.getType().equals("Hot Drinks"))
                    ((Hot_Drinks) to_check).addQty(sent_price);
                else if (to_check.getType().equals("Cold Drinks"))
                    ((Cold_Drinks) to_check).addQty(sent_price);
                System.out.println("added");
                return true;
            }
        }
        return false;
    }

    public boolean checkOrder(String sent_name) {
        Iterator it = allOrder.iterator();

        while (it.hasNext()) {

            Menu to_check = (Menu) it.next();
            String order_name = (String) (to_check.getName());
            if (order_name.equals(sent_name)) {

                return true;
            }
        }
        return false;
    }
}
