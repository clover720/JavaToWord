package test2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import sun.misc.BASE64Encoder;

public class WordTest {
	private Configuration configuration = null;  
	public ResultSet rs = null;
    public WordTest(){  
        configuration = new Configuration();  
        configuration.setDefaultEncoding("UTF-8");  
    }  
    
    public void createWord() throws IOException {  

        Map<String,Object> dataMap=new HashMap<String,Object>();  
        getData(dataMap);  
        configuration.setDefaultEncoding("UTF-8");
       // configuration.setClassForTemplateLoading(this.getClass(), "/com");  //FTL�ļ������ڵ�λ��  
        configuration.setDirectoryForTemplateLoading(new File("d:/"));  
        Template t=null;  

        try {
//            t = configuration.getTemplate("wordModel.ftl"); //�ļ���
            t = configuration.getTemplate("dis1.ftl"); //�ļ���
        } catch (IOException e) {
            e.printStackTrace();  
        }

        File outFile = new File("D:/outFilessa"+Math.random()*10000+".doc");  
        Writer out = null;  

        try {  

            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile),"utf-8"));  

        } catch (FileNotFoundException e1) {  

            e1.printStackTrace();  

        }  

        try {  
            t.process(dataMap, out);  

        } catch (TemplateException e) {  
            e.printStackTrace();  

        } catch (IOException e) {  

            e.printStackTrace();  

        }  

    }  
    
 //   ��ͼƬ���б���:
    public String getImageStr() {  
        String imgFile = "C:\\Users\\��ΰ\\Desktop\\e�����\\rr.jpg";  
        InputStream in = null;  
        byte[] data = null;  
        try {  
            in = new FileInputStream(imgFile);  
            data = new byte[in.available()];  
            in.read(data);  
            in.close();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        BASE64Encoder encoder = new BASE64Encoder();  
        return encoder.encode(data);  
    }
    
    
    public  ResultSet executeQuery(Connection conn,String sql) {
		try { // ��׽�쳣
			//conn = getConnection(); // ����getConnection()��������Connection�����һ��ʵ��conn
			Statement stmt = null;
			ResultSet rs = null;
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery(sql);
		} catch (SQLException ex) {
			System.err.println(ex.getMessage()); // ����쳣��Ϣ
		}
		return rs;
		
		 // ���ؽ��������
	}
    
    
  //��ѯ����
    public List query()  {
    	//conDao cc= conDao();
    	Connection conn=conDao.getConnection();
    	List questionsList = new ArrayList();
        question ques = null;
        String sql="";
        sql = "SELECT qid,subject,type,optionA,optionB,optionC,optionD FROM question WHERE courseId=1";
        Statement stmt;
		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery(sql);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        String type="";
        String answer="";
        try {
            while (rs.next()) {
                ques = new question();
                ques.setQid(rs.getInt(1));
                ques.setSubject(rs.getString(2));
                type=rs.getString(3);
                ques.setOptionA(rs.getString(4));
                ques.setOptionB(rs.getString(5));
                ques.setOptionC(rs.getString(6));
                ques.setOptionD(rs.getString(7));
//                if(type.equals("��ѡ��")){
//                	String[] ans=rs.getString(11).split(",");
//                	questionsForm1.setAnswerArr(ans);
//                }else{
//                	questionsForm1.setAnswer(rs.getString(11));
//                }
//                questionsForm1.setNote(rs.getString(12));
                questionsList.add(ques);
            }
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return questionsList;
    }
    
   
	private void getData(Map<String, Object> dataMap){  
    	List quel = null;
		quel = query();
		
    	question[] q= new question[50];
    	int i=0;
    	for(i=0;i<quel.size();i++)
    	{
    		q[i] = (question)quel.get(i);
    		
    	}
    	String n_one = q[9].getSubject()+"\r\n".replace("\r\n", "<w:p></w:p>");
        String n_two = q[9].getSubject()+"\r\n".replace("\r\n", "<w:p></w:p>");
    	String n_three=q[9].getSubject()+"\r\n".replace("\r\n", "<w:p></w:p>");
    	String n_four =q[9].getSubject()+"\r\n".replace("\r\n", "<w:p></w:p>");
        String n_five =q[8].getSubject()+"\r\n".replace("\r\n", "<w:p></w:p>");
        String n_six = q[7].getSubject()+"\r\n".replace("\r\n", "<w:p></w:p>");
        String n_seven = q[8].getSubject()+"\r\n".replace("\r\n", "<w:p></w:p>");
        String n_eight = q[9].getSubject()+"\r\n".replace("\r\n", "<w:p></w:p>");
       
        String n_nine = "�����ȡ��________����Ĺ��Բ��֣�����ڴ�����������Ŀ�Ŀ��������д��벻��Ҫ��ͷ��д��ֻ��Ҫ�ڿ�ܵĻ����Ͻ���һЩ________�������Ҫ��\r\n".replace("\r\n", "<w:p></w:p>");
        String n_ten = "________������һ�����ص㻷���·��������⣬�Լ��������________��\r\n".replace("\r\n", "<w:p></w:p>");
       
        String n_eleven = "JavaBean��Ҫ������ʡ���ơ�________���ƺ�________���ơ�\r\n".replace("\r\n", "<w:p></w:p>");
        String n_twelve = "��CORBA�У�____�Ƿ��������Լ�����֪ͨ�ļ��ϣ�____�ǽ��������Լ�����֪ͨ�ļ��ϡ�\r\n".replace("\r\n", "<w:p></w:p>");
        String n_thirteeen = "�ֲ�ʽϵͳ��____�ֲ���������������ͨ��____���ݽ���ͨ�źͶ���Э����ϵͳ��\r\n".replace("\r\n", "<w:p></w:p>");
        String n_fourteen = "�����ϵ�ṹ��ָһ��ϵͳ����Ŀ�ĵ���ƺ͹滮�������ƹ滮�Ȳ�����________��Ҳ������ϵͳ������������ֻ����ϵͳ�����Ԫ�ؼ����໥��________��\r\n".replace("\r\n", "<w:p></w:p>");
        String n_fifteen = "	________ͨ��WSDL��������ͨ��________�����ʣ�����ҵע�����ģ���UDDI���������Ӷ�ʹ�����ߺ͵�������Ӧ�ó��������������λ���÷���\r\n".replace("\r\n", "<w:p></w:p>");
        String c_one = q[0].getSubject()+"\r\n".replace("\r\n", "<w:p></w:p>") + 
        		q[0].getOptionA()+"          "+q[0].getOptionB()+"\r\n".replace("\r\n", "<w:p></w:p>") + 
        		q[0].getOptionA()+"          "+q[0].getOptionB()+"\r\n".replace("\r\n", "<w:p></w:p>");
        String c_two =q[1].getSubject()+"\r\n".replace("\r\n", "<w:p></w:p>") + 
        		q[1].getOptionA()+"          "+q[1].getOptionB()+"\r\n".replace("\r\n", "<w:p></w:p>") + 
        		q[1].getOptionA()+"          "+q[1].getOptionB()+"\r\n".replace("\r\n", "<w:p></w:p>");
        String c_three =q[0].getSubject()+"\r\n".replace("\r\n", "<w:p></w:p>") + 
        		q[2].getOptionA()+"          "+q[2].getOptionB()+"\r\n".replace("\r\n", "<w:p></w:p>") + 
        		q[2].getOptionA()+"          "+q[2].getOptionB()+"\r\n".replace("\r\n", "<w:p></w:p>");
        String c_four =q[0].getSubject()+"\r\n".replace("\r\n", "<w:p></w:p>") + 
        		q[0].getOptionA()+"          "+q[0].getOptionB()+"\r\n".replace("\r\n", "<w:p></w:p>") + 
        		q[0].getOptionA()+"          "+q[0].getOptionB()+"\r\n".replace("\r\n", "<w:p></w:p>");
        String c_five =q[0].getSubject()+"\r\n".replace("\r\n", "<w:p></w:p>") + 
        		q[0].getOptionA()+"          "+q[0].getOptionB()+"\r\n".replace("\r\n", "<w:p></w:p>") + 
        		q[0].getOptionA()+"          "+q[0].getOptionB()+"\r\n".replace("\r\n", "<w:p></w:p>");
        String p_one = "��Ӳ���������ṹ���������á�ϵͳά������ȫ��Ҫ����ٶȵȷ��棬�����Ƚ�����C��S�ṹ��B��S�ṹ֮�����ͬ��\r\n" + 
        		"\r\n" + 
        		"\r\n" + 
        		"\r\n".replace("\r\n", "<w:p></w:p>");
        String p_two = "�������Ĺ��ܣ�SOA������������Ҫ���֣�������ͼ�б��ͼ��ģ��ֱ��Ӧ���е��ĸ����֡�\r\n" + 
        		"\r\n" + 
        		"\r\n" + 
        		"\r\n".replace("\r\n", "<w:p></w:p>");
        String p_three = "����SAAM�����Ļ������衣\r\n" + 
        		"\r\n" + 
        		"\r\n".replace("\r\n", "<w:p></w:p>");
        String d_one = "��ƹ���ϵͳ�Ĳ����߰������������ĵ��û�������ƾ֤¼����Ա��ƾ֤�˶���Ա��������Ա���浥������Ա��ϵͳά����Ա�ȣ�����ÿ��ҵ����Ա����������Ӧ����Ȩ�ޡ�ϵͳ�Ĺ��ܰ�����\r\n" + 
        		"��1��ƾ֤¼����Ա��ƾ֤����\r\n" + 
        		"��2��ƾ֤�˶���Ա��У��ƾ֤�����Ƿ���ȷ\r\n" + 
        		"��3��������Ա�����㵥�շ����Ļ��ҵ���н���Ƿ�ƽ�⣬�������ʵĴ���\r\n" + 
        		"��4��ϵͳά����Ա��ժҪ��ƾ֤����Ŀ�����ά���Լ���ʹ����Ա��\r\n" + 
        		"Ȩ\r\n" + 
        		"�뻭��������ϵͳ����Ҫ������ͼ����ͼ�͹���ͼ��\r\n" + 
        		"\r\n".replace("\r\n", "<w:p></w:p>");
        
        dataMap.put("n_one", n_one); 
        dataMap.put("n_two", n_two);
        dataMap.put("n_three", n_three);
        dataMap.put("n_four", n_four);
        dataMap.put("n_five", n_five);
        dataMap.put("n_six", n_six);
        dataMap.put("n_seven", n_seven);
       dataMap.put("n_eight", n_eight);
        dataMap.put("n_nine", n_nine);
       dataMap.put("n_ten", n_ten);
        dataMap.put("n_eleven",n_eleven);
        dataMap.put("n_twelve", n_twelve);
        dataMap.put("n_thirteen", n_thirteeen);
        dataMap.put("n_fourteen", n_fourteen);
        dataMap.put("n_fifteen", n_fifteen);
        dataMap.put("c_one", c_one);
        dataMap.put("c_two", c_two);
        dataMap.put("c_three", c_three);
        dataMap.put("c_four", c_four);
        dataMap.put("c_five", c_five);
        dataMap.put("p_one", p_one);
        dataMap.put("p_two", p_two);
        dataMap.put("imgStr", getImageStr());
        dataMap.put("p_three", p_three);
        dataMap.put("d_one", d_one);
         
        
    	 /*dataMap.put("title",p_one );  
        dataMap.put("year", p_two);  
        dataMap.put("month", n_seven);  
        dataMap.put("day", n_eight);  
        dataMap.put("auditor", c_three);  
        dataMap.put("phone", c_two);  
        dataMap.put("weave", n_fourteen);  
        dataMap.put("number",d_one);  
        dataMap.put("content", n_one);  
        dataMap.put("imgStr", getImageStr()); 
        dataMap.put("haha", c_five); 
        dataMap.put("bb", n_two);  
        
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();  
        for (int i = 0; i < 10; i++) {  
            Map<String,Object> map = new HashMap<String,Object>();  
            map.put("number", i);  
            map.put("content", "����"+i);  
            list.add(map);  
        }
        dataMap.put("list", list);*/

    }  
    
    
    public static void main(String[] args) throws IOException {  
        WordTest test = new WordTest();  
        test.createWord();  
        System.out.println("success");

    }  
    
    

}
