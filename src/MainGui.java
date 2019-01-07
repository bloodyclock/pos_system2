import com.itextpdf.text.DocumentException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.util.LinkedHashMap;
import java.util.Map;

public class MainGui {
    private JButton button1;
    private JPanel RootPanel;
    private JTextField texbox_proid;
    private JLabel text1;
    private JLabel SumTotal;
    JTextField discount;
    private JButton button2;
    private JLabel sub_tottal;
    private JLabel lsave;
    private JLabel dare;
    private JTextArea textStr;
    private JSpinner spin_item ;
    private JButton createReportButton;
    int sum=0;
    int sum_dis =0;
    String listItemStr;
    String send_item;


    Map<String, Item> mapItem = new LinkedHashMap<String, Item>();

    public void Guitest(String[]human) {
        JFrame frame = new JFrame("Cashier System");
        frame.setPreferredSize(new Dimension(1024,768));
        frame.setContentPane(new MainGui(human).RootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public MainGui(String[]hello) {
        spin_item.setValue(1);
        dare.setText(hello[2]+"  "+hello[1]);
        texbox_proid.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    button1.doClick();
                }
            }
        });


        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String[]data;
                send_item = "";
                listItemStr = "";
                for(int i=0;i<70;i++){
                    listItemStr+="-";
                }
                listItemStr+="\nID       Product                  \tQty          \tPrice\n";
                for(int i=0;i<70;i++){
                    listItemStr+="-";
                }
                listItemStr+="\n";
                Sql_connect co = new Sql_connect();
                data= co.connect(texbox_proid.getText());//get product data in database
                texbox_proid.setText("");
                if(data[0]=="")
                    JOptionPane.showMessageDialog(null,"No Data","No Data",JOptionPane.WARNING_MESSAGE);
                sum += Integer.valueOf(data[2])*(Integer) spin_item.getValue();
                SumTotal.setText(String.valueOf(sum));
                sub_tottal.setText(String.valueOf(sum));


                if(mapItem.containsKey(data[0])){
                   Item item = mapItem.get(data[0]);
                        item.setQty(item.getQty()+(Integer) spin_item.getValue());
                }else {
                    Item pro_item =new Item();
                    pro_item.setPro_id(data[0]);
                    pro_item.setPro_name(data[1]);
                    pro_item.setPrice(Integer.valueOf(data[2]));
                    pro_item.setQty((Integer) spin_item.getValue());
                    mapItem.put(data[0], pro_item);
                }
                if(!mapItem.isEmpty()){
                    for (Map.Entry<String, Item>  entry : mapItem.entrySet()) {
                        Item item = entry.getValue();
                            listItemStr += item.getPro_id() +"     "+item.getPro_name()+"                 \t "+item.qty+"          \t "+item.getPrice()*item.getQty()+ "\n";
                        send_item += item.getPro_id() +"     "+item.getPro_name()+"                 \t "+item.qty+"          \t "+item.getPrice()*item.getQty()+ "\n";

                    }


                }

                textStr.setText(listItemStr);
                spin_item.setValue(1);
            }
        });
        discount.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    button2.doClick();
                }
            }
        });
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String a = discount.getText();
                discount di = new discount();
                if (sum >= 500){
                    sum_dis = di.dis(sum, discount.getText());
                    sub_tottal.setText(String.valueOf(sum_dis));
                    lsave.setText(String.valueOf(sum-sum_dis));
                    if(sum_dis==0){
                    sub_tottal.setText(String.valueOf(sum));
                    lsave.setText("0");
                    JOptionPane.showMessageDialog(null,"No Data","No Data",JOptionPane.WARNING_MESSAGE);
                    }
                }
                else
                    JOptionPane.showMessageDialog(null,"please buy more than 500","warning",JOptionPane.WARNING_MESSAGE);

            }
        });

        createReportButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Create_pdf cre = new Create_pdf();
                try {
                    cre.Cre_pdf(send_item,dare.getText(),sum,Integer.valueOf(lsave.getText()));
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                } catch (DocumentException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }


}
