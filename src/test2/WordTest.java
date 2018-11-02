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
       // configuration.setClassForTemplateLoading(this.getClass(), "/com");  //FTL文件所存在的位置  
        configuration.setDirectoryForTemplateLoading(new File("d:/"));  
        Template t=null;  

        try {
//            t = configuration.getTemplate("wordModel.ftl"); //文件名
            t = configuration.getTemplate("dis1.ftl"); //文件名
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
    
 //   对图片进行编码:
    public String getImageStr() {  
        String imgFile = "C:\\Users\\陈伟\\Desktop\\e卷题库\\rr.jpg";  
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
		try { // 捕捉异常
			//conn = getConnection(); // 调用getConnection()方法构造Connection对象的一个实例conn
			Statement stmt = null;
			ResultSet rs = null;
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery(sql);
		} catch (SQLException ex) {
			System.err.println(ex.getMessage()); // 输出异常信息
		}
		return rs;
		
		 // 返回结果集对象
	}
    
    
  //查询方法
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
//                if(type.equals("多选题")){
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
       
        String n_nine = "框架提取了________软件的共性部分，因此在此领域内新项目的开发过程中代码不需要从头编写，只需要在框架的基础上进行一些________便可满足要求。\r\n".replace("\r\n", "<w:p></w:p>");
        String n_ten = "________描述了一个在特点环境下发生的问题，以及该问题的________。\r\n".replace("\r\n", "<w:p></w:p>");
       
        String n_eleven = "JavaBean主要特性内省机制、________机制和________机制。\r\n".replace("\r\n", "<w:p></w:p>");
        String n_twelve = "在CORBA中，____是发出请求以及接受通知的集合，____是接受请求以及发出通知的集合。\r\n".replace("\r\n", "<w:p></w:p>");
        String n_thirteeen = "分布式系统是____分布在网络计算机上且通过____传递进行通信和动作协调的系统。\r\n".replace("\r\n", "<w:p></w:p>");
        String n_fourteen = "软件体系结构是指一个系统的有目的的设计和规划，这个设计规划既不描述________，也不描述系统怎样开发，它只描述系统的组成元素及其相互的________。\r\n".replace("\r\n", "<w:p></w:p>");
        String n_fifteen = "	________通过WSDL来描述，通过________作访问，在商业注册中心（如UDDI）发布，从而使开发者和电子商务应用程序可以搜索并定位到该服务。\r\n".replace("\r\n", "<w:p></w:p>");
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
        String p_one = "从硬件环境、结构、构件重用、系统维护、安全的要求和速度等方面，分析比较三层C／S结构和B／S结构之间的异同。\r\n" + 
        		"\r\n" + 
        		"\r\n" + 
        		"\r\n".replace("\r\n", "<w:p></w:p>");
        String p_two = "面向服务的构架（SOA）包括三个主要部分，试在下图中标出图中模块分别对应其中的哪个部分。\r\n" + 
        		"\r\n" + 
        		"\r\n" + 
        		"\r\n".replace("\r\n", "<w:p></w:p>");
        String p_three = "试述SAAM评估的基本步骤。\r\n" + 
        		"\r\n" + 
        		"\r\n".replace("\r\n", "<w:p></w:p>");
        String d_one = "会计管理系统的参与者包括：结算中心的用户（包括凭证录入人员，凭证核对人员，扎帐人员，存单管理人员，系统维护人员等）而且每种业务人员都授予了相应操作权限。系统的功能包括：\r\n" + 
        		"（1）凭证录入人员：凭证输入\r\n" + 
        		"（2）凭证核对人员：校验凭证输入是否真确\r\n" + 
        		"（3）扎帐人员：计算单日发生的会计业务中借贷是否平衡，并做扎帐的处理\r\n" + 
        		"（4）系统维护人员：摘要、凭证、科目种类的维护以及给使用人员授\r\n" + 
        		"权\r\n" + 
        		"请画出构建该系统所需要的用例图、类图和构件图。\r\n" + 
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
            map.put("content", "内容"+i);  
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
