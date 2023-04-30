import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Cold_Drinks extends Menu {
    // private String name;
    private static ArrayList<Cold_Drinks> cold = new ArrayList<Cold_Drinks>();
    private int qty = 0;
    // private String id;
    // private double price;

    public Cold_Drinks(String name, double price) {
        super(name, price);
        type = "Cold Drinks";
    }
    
    public int getQty() {
        return qty;
    }

    public void setQty(int qty, double price) {
        this.qty = qty;
        this.price = price * this.qty;
    }

    public void addQty(double price) {
        qty++;
        addPrice(price);
    }

    public void addPrice(double price) {
        this.price += price;
    }

    public static void getAllCold() {
        try {
            File file = new File(
                    // "C:\\Users\\musab\\OneDrive\\Documents\\UNITEN\\Coding\\Java\\HAMburger\\HAMburger\\lib\\Cold_Drinks.txt");
                    "Cold_Drinks.txt");
            FileReader inFileStream = new FileReader(file);
            BufferedReader inData = new BufferedReader(inFileStream);
            String sN;
            String sP;
            String name;
            double price;

            while ((sN = inData.readLine()) != null) {
                // inData.readLine();
                name = sN;
                sP = inData.readLine();
                System.out.println(name + sP);
                price = Double.parseDouble(sP);
                Cold_Drinks temp = new Cold_Drinks(name, price);
                cold.add(temp);
            }
            inData.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static JPanel addCold(JPanel panel, JTextField totalPrice) {

        JButton food;
        int i, j = 0, k = 0;

        for (i = 0; i < cold.size(); i++) {
            food = new JButton(cold.get(i).getName());
            food.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String name;
                    boolean added;
                    double price = 0;
                    name = ((JButton) e.getSource()).getText();
                    // new_order.setBackground(Color.white);
                    for (int i = 0; i < cold.size(); i++) {
                        if (cold.get(i).getName().equals(((JButton) e.getSource()).getText()))
                            price = cold.get(i).getPrice();
                        // type = cold.get(i).getType();
                    }

                    Cold_Drinks temp = new Cold_Drinks(name, price);

                    /////////////////
                    // added = checkOrder(name, price);
                    added = home.personalOrder.checkOrder(name, price, totalPrice);
                    if (added == false) {
                        temp.setQty(1,price);
                        home.personalOrder.addTotalPrice(price);
                        home.personalOrder.allOrder.add(temp);
                    }
                    totalPrice.setText("" + home.personalOrder.getTotalPrice());
                    // panelOrder.add(totalPrice);
                    home.panelOrder = home.personalOrder.previewOrder(home.panelOrder);

                    System.out.println("1 cold drink was added " + ((JButton) e.getSource()).getText());
                    System.out.println(home.personalOrder.getTotalPrice());

                }
            });
            food.setBounds(k, j, 140, 140);
            food.setBackground(Color.white);
            k += 140;
            panel.add(food);
            if ((i + 1) % 3 == 0 && i != 0) {
                k = 0;
                j += 140;
            }
        }
        return panel;
    }

    public static void editQty(Cold_Drinks food) {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        panel.setLayout(null);
        JLabel lFoodName = new JLabel(food.getName());
        JButton add = new JButton("+");
        JTextField qty = new JTextField();
        qty.setText("" + food.getQty());
        JButton minus = new JButton("-");
        frame.setSize(200, 150);
        frame.setLocationRelativeTo(null);
        lFoodName.setBounds(0, 0, 200, 50);
        add.setBounds(0, 50, 50, 50);
        add.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double price = 0;
                for (int i = 0; i < cold.size(); i++) {
                    if (cold.get(i).getName().equals(food.getName())) {
                        price = cold.get(i).getPrice();
                    }
                    // type = cold.get(i).getType();
                }
                food.setQty(food.getQty() + 1, price);
                qty.setText("" + food.getQty());
                home.panelOrder = home.personalOrder.previewOrder(home.panelOrder);

            }
        });
        qty.setBounds(50, 50, 100, 50);
        qty.setEditable(false);
        minus.setBounds(150, 50, 50, 50);
        minus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double price = 0;
                for (int i = 0; i < cold.size(); i++) {
                    if (cold.get(i).getName().equals(food.getName())) {
                        price = cold.get(i).getPrice();
                    }
                    // type = cold.get(i).getType();
                }
                food.setQty(food.getQty() - 1, price);
                qty.setText("" + food.getQty());
                home.panelOrder = home.personalOrder.previewOrder(home.panelOrder);
            }
        });
        panel.add(lFoodName);
        panel.add(add);
        panel.add(qty);
        panel.add(minus);

        lFoodName.setFont(lFoodName.getFont().deriveFont(24.0f));
        add.setFont(lFoodName.getFont().deriveFont(20.0f));
        qty.setFont(lFoodName.getFont().deriveFont(20.0f));
        minus.setFont(lFoodName.getFont().deriveFont(20.0f));

        frame.add(panel);
        frame.setVisible(true);
        System.out.println(food.getQty());
    }
}