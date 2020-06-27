/*
 * This is a project created for 4th Sem Summer Internship at NIT Delhi.
 * Supervisor: Dr. Rajya Laxmi Leila
 */
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
import java.util.logging.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
/**
 *
 * @author amrit raj
 */
public class project extends JFrame implements ActionListener{
    
    JLabel title,l1,l2,l3,l4,l5; 
    JTextField t1,t2;
    JComboBox c1,c2;
    JButton b;
    HashMap<String,HashMap<String,String>> map=new HashMap<>();
    
    project(String s){
        super(s);
        Font font1=new Font("SansSerif",Font.BOLD,15);
        
        title=new JLabel("Project Assignment for B. Tech Final Year Students");
        title.setFont(font1);
        title.setBounds(30, 40, 450, 20);
        
        l1=new JLabel("Name:");
        l1.setBounds(50, 80, 100 , 20);
        
        l2=new JLabel("Roll:");
        l2.setBounds(50, 110, 100, 20);
        
        l3=new JLabel("Your Areas of Interest:");
        l3.setBounds(50, 140, 150, 20);
        
        l4=new JLabel("1st Choice:");
        l4.setBounds(50, 170, 100, 20);
        
        l5=new JLabel("2nd Choice:");
        l5.setBounds(220, 170, 100, 20);
        
        t1=new JTextField(30);
        t1.setBounds(160, 80, 210, 20);
        
        t2=new JTextField(10);
        t2.setBounds(160, 110, 210, 20);
        
        String topics[]={"----select one----","Analytics Based on Social Networking Data","App Development","Arduino","Automation","Big Data Analytics","Computer Networks","Data Science","DBMS","Deep Learning","Gesture Recognition","IoT","Machine Learning","MATLAB","Robotics","Web Development","Wireless Communications"};
        
        c1=new JComboBox(topics);
        c1.setBounds(50, 200, 150, 20);
            
        c2=new JComboBox(topics);
        c2.setBounds(220, 200, 150, 20);
            
        b=new JButton("Submit");
        b.setBounds(150, 290, 100, 20);
        
        add(title);
        add(l1);
        add(l2);
        add(l3);
        add(l4);
        add(l5);
        add(t1);
        add(t2);
        add(c1);
        add(c2);
        add(b);
        
        setLayout(null);
        setSize(600,400);
        setVisible(true);
        b.addActionListener(this);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
    public static void main(String args[]) throws FileNotFoundException{ 
        new project("Project Assignment");   
    }

    private project() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String C1=(String) c1.getSelectedItem();
        String C2=(String) c2.getSelectedItem();
        String sname=t1.getText();
        String roll=t2.getText();
        List<Integer> list=new ArrayList<>();
        HashMap<String,Integer> map1=new HashMap<>();
        JSONParser jsonParser = new JSONParser();
        Scanner scanner = null;
        try {
            scanner = new Scanner( new File("D:\\4thSemInternshipProject\\src\\main\\java\\faculty.json") );
        } catch (FileNotFoundException ex) {
            Logger.getLogger(project.class.getName()).log(Level.SEVERE, null, ex);
        }
        String text = scanner.useDelimiter("\\A").next();
        scanner.close();
        Object obj = null;
        try {
            obj = jsonParser.parse(text);
        } catch (ParseException ex) {
            System.out.println("Parse Exception");
            Logger.getLogger(project.class.getName()).log(Level.SEVERE, null, ex);
        }
        JSONObject json=(JSONObject) obj;
        try {
            map = new ObjectMapper().readValue(json.toJSONString(), HashMap.class);
        } catch (JsonProcessingException ex) {
            System.out.println("JSON Processing Exception");
            Logger.getLogger(project.class.getName()).log(Level.SEVERE, null, ex);
        }
        int c=0;
        String choice=null,name=null;
        for(String i:map.keySet()){
            if(C1.equals(map.get(i).get("C1"))&&Integer.parseInt(map.get(i).get("Cap"))<30){
                choice=C1;
                map1.put(i,Integer.parseInt(map.get(i).get("Cap")));
                c++;
            }
        }
        if(c==0){
            for(String i:map.keySet()){
                if(C1.equals(map.get(i).get("C2"))&&Integer.parseInt(map.get(i).get("Cap"))<30){
                    choice=C1;
                    map1.put(i,Integer.parseInt(map.get(i).get("Cap")));
                    c++;
                }
            }
        }
        if(c==0){
            for(String i:map.keySet()){
                if(C2.equals(map.get(i).get("C1"))&&Integer.parseInt(map.get(i).get("Cap"))<30){
                    choice=C2;
                    map1.put(i,Integer.parseInt(map.get(i).get("Cap")));
                    c++;
                }
            }
        }
        if(c==0){
            for(String i:map.keySet()){
                if(C2.equals(map.get(i).get("C2"))&&Integer.parseInt(map.get(i).get("Cap"))<30){
                    choice=C2;
                    map1.put(i,Integer.parseInt(map.get(i).get("Cap")));
                    c++;
                }
            }
        }
        if(c==0){
            choice="No supervisor available for selected choices.";
            JOptionPane.showMessageDialog(this,choice);
        }
        else{
            int min;
            for(String i:map1.keySet())
                list.add(map1.get(i));            
            min=Collections.min(list);
            for(String i:map1.keySet()){
                if(map1.get(i)==min){
                    name=map.get(i).get("Name");
                    min++;
                    map.get(i).replace("Cap",String.valueOf(min));
                    break;
                }
            }
            JOptionPane.showMessageDialog(this,"Assigned Supervisor: "+name+"\nAssigned Topic for Project: "+choice);
            JSONObject jsonobj = new JSONObject(map);
            try {
                try (FileWriter file = new FileWriter("D:\\4thSemInternshipProject\\src\\main\\java\\faculty.json")) {
                    file.write(jsonobj.toJSONString());
                    file.flush();
                }
            } catch (IOException ex) { }
            try{
                FileWriter fw=new FileWriter("D:\\4thSemInternshipProject\\src\\main\\java\\list.txt",true);
                fw.write("Student Name: "+sname);
                fw.write("\nRoll No: "+roll);
                fw.write("\nSupervisor Name: "+name);
                fw.write("\nAssigned Topic for Project: "+choice);
                fw.write("\n----------------------------------------------------------------\n");
                fw.flush();
            } catch(IOException ex) {}
        }
    }
}
