/*
 * This is a project created for 4th Sem Summer Internship at NIT Delhi.
 * Supervisor: Dr. Rajya Laxmi Leila
 */
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
/**
 *
 * @author amrit raj
 */
public class facultyregistration {
    public static void main(String args[]){
        HashMap<String,String> map1=new HashMap<>();
        HashMap<String,HashMap<String,String>> map = new HashMap<>();
        Scanner scan=new Scanner(System.in);
        System.out.println("-------------FACULTY REGISTRATION-------------");
        int c=1;
        String k;
        String Name,C1,C2,Cap;
        while(c!=0)
        {
            System.out.print("Enter your name: ");
            Name=scan.nextLine();
            System.out.print("Enter your ID no: ");
            k=scan.nextLine();
            System.out.print("Enter your first choice for the project: ");
            C1=scan.nextLine();
            System.out.print("Enter your second choice for the project: ");
            C2=scan.nextLine();
            System.out.print("Enter the number of students you are already supervsing(Enter 0 if none): ");
            Cap=scan.nextLine();
            map1.put("Name",Name);
            map1.put("C1", C1);
            map1.put("C2", C2);
            map1.put("Cap",Cap);
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
            map.put(k,map1);
            System.out.print("Next Faculty Registration?(0/1)   ");
            c=scan.nextInt();
        }
        JSONObject jsonobj = new JSONObject(map);
        try {
            try (FileWriter file = new FileWriter("D:\\4thSemInternshipProject\\src\\main\\java\\faculty.json")) {
                file.write(jsonobj.toJSONString());
                file.flush();
            }
        } catch (IOException ex) { }
    }
}