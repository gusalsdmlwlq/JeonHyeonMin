package hotel;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.io.*;
import java.nio.channels.ShutdownChannelGroupException;
import java.util.regex.*;

public class hotel implements ActionListener{
	private static Connection dbTest;
	JFrame frame = new JFrame();
	JLabel title = new JLabel("ȣ�� ���� �ý���");
	JMenuBar menu = new JMenuBar();
	JMenu file = new JMenu("File");
	JMenuItem read = new JMenuItem("Open");
	JFileChooser filechoose = new JFileChooser();
	JPanel p1 = new JPanel();
	JPanel p2 = new JPanel();
	JPanel p3 = new JPanel();
	JLabel label1 = new JLabel("��������");
	JLabel label2 = new JLabel("���� ���� ��Ȳ");
	JLabel label3 = new JLabel("���/��ȸ");
	
	//�α���
	String username;
	String password;
	JFrame login = new JFrame();
	JLabel id = new JLabel("���̵�");
    JLabel pw = new JLabel("��й�ȣ");
    JTextField idinput = new JTextField();
    JPasswordField pwinput = new JPasswordField();
    JButton loginbutton = new JButton("�α���");
	
	//��������
	JLabel customer = new JLabel("����");
	JTextField customer_input = new JTextField();
	JLabel checkin = new JLabel("üũ��(YYYYMMDD)");
	JTextField checkin_input = new JTextField();
	JLabel days = new JLabel("��");
	JComboBox<Integer> days_input = new JComboBox<Integer>();
	JLabel room = new JLabel("����");
	JComboBox<Integer> room_input = new JComboBox<Integer>();
	JButton reserve = new JButton("���� ���/����");
	JButton cancle = new JButton("���� ���");
	
	//���� ���� ��Ȳ
	JLabel today = new JLabel();
	JPanel table = new JPanel();
	JLabel table_cell[] = new JLabel[20];
	
	
	//���/��ȸ
	JTabbedPane tab = new JTabbedPane();
	JPanel tab1 = new JPanel();
	JPanel tab2 = new JPanel();
	JPanel tab3 = new JPanel();
	JLabel customer_name = new JLabel("����");
	JTextField customer_name_input = new JTextField();
	JButton customer_signup = new JButton("ȸ������");
	JButton customer_list = new JButton("��ȸ");
	JTextArea customer_info = new JTextArea();
	JScrollPane cust_info = new JScrollPane();
	JLabel room_num = new JLabel("����");
	JComboBox<Integer> room_num_input = new JComboBox<Integer>();
	JTextArea room_num_info = new JTextArea();
	JScrollPane room_info = new JScrollPane();
	JLabel employee = new JLabel("������");
	JTextField employee_input = new JTextField();
	JButton employee_signup = new JButton("�������");
	JButton employee_list = new JButton("��ȸ");
	JTextArea employee_info = new JTextArea();
	JScrollPane em_info = new JScrollPane();
	
	//ȸ������
	JFrame cust_signup = new JFrame();
	JPanel signup_cu = new JPanel();
	JLabel signup_customer = new JLabel("����");
	JTextField signup_customer_input = new JTextField();
	JLabel signup_cu_sex = new JLabel("����");
	JComboBox<String> signup_cu_sex_input = new JComboBox<String>();
	JLabel signup_cu_address = new JLabel("�ּ�");
	JComboBox<String> signup_cu_address_input = new JComboBox<String>();
	JLabel signup_cu_tel = new JLabel("����ó");
	JTextField signup_cu_tel_input = new JTextField();
	JButton signup_cu_ok = new JButton("���Խ�û");
	JButton signup_cu_cancle = new JButton("���");
	
	JFrame em_signup = new JFrame();
	JPanel signup_em = new JPanel();
	JLabel signup_employee = new JLabel("������");
	JTextField signup_employee_input = new JTextField();
	JLabel signup_em_sex = new JLabel("����");
	JComboBox<String> signup_em_sex_input = new JComboBox<String>();
	JLabel signup_em_address = new JLabel("�ּ�");
	JComboBox<String> signup_em_address_input = new JComboBox<String>();
	JLabel signup_em_tel = new JLabel("����ó");
	JTextField signup_em_tel_input = new JTextField();
	JButton signup_em_ok = new JButton("�������");
	JButton signup_em_cancle = new JButton("���");
	
	public hotel(){
		frame.setBounds(0 , 0 , 1960 , 1080);
		frame.setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		title.setBounds(250,70,1400,80);
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("����",Font.BOLD,40));
		title.setBorder(new LineBorder(Color.BLACK, 4));
		frame.add(title);
		frame.add(label1);
		frame.add(label2);
		frame.add(label3);
		frame.add(file);
		frame.add(menu);
		label1.setBounds(60, 150, 400, 50);
		label1.setFont(new Font("����",Font.BOLD,30));
		label2.setBounds(1010, 150, 400, 50);
		label2.setFont(new Font("����",Font.BOLD,30));
		label3.setBounds(60, 550, 400, 50);
		label3.setFont(new Font("����",Font.BOLD,30));
		menu.setBounds(0, 0, 1960, 30);
		menu.add(file);
		file.add(read);
		read.addActionListener(this);
		filechoose.setBounds(0, 0, 1960, 600);
		frame.add(today);
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		today.setText("("+sdf.format(d)+")");
		today.setBounds(1600, 150, 300, 50);
		today.setFont(new Font("����",Font.BOLD,30));
		
		//�α���
		login.add(id);
		login.add(idinput);
		login.add(pw);
		login.add(pwinput);
		login.add(loginbutton);
		id.setBounds(50, 50, 100, 50);
		idinput.setBounds(150, 50, 200, 50);
		pw.setBounds(50, 150, 100, 50);
		pwinput.setBounds(150, 150, 200, 50);
		loginbutton.setBounds(100, 250, 200, 50);
		loginbutton.addActionListener(this);
		login.setLayout(null);
		login.setVisible(true);
		login.setBounds(0, 0, 500, 400);
		
		//��������
		p1.setBounds(50, 200, 850, 350);
		p1.setBorder(new LineBorder(Color.GRAY, 4));
		p1.setLayout(null);
		frame.add(p1);
		p1.add(customer);
		p1.add(customer_input);
		p1.add(checkin);
		p1.add(checkin_input);
		p1.add(days);
		p1.add(days_input);
		p1.add(room);
		p1.add(room_input);
		p1.add(reserve);
		reserve.addActionListener(this);
		p1.add(cancle);
		cancle.addActionListener(this);
		customer.setBounds(50, 10, 300, 50);
		customer_input.setBounds(400, 10, 300, 50);
		checkin.setBounds(50, 80, 300, 50);
		checkin_input.setBounds(400, 80, 300, 50);
		days.setBounds(50, 150, 300, 50);
		days_input.setBounds(400, 150, 300, 50);
		for(int i=1; i<=10; i++) days_input.addItem(i);
		room.setBounds(50, 220, 300, 50);
		room_input.setBounds(400, 220, 300, 50);
		reserve.setBounds(75, 290, 300, 50);
		cancle.setBounds(400, 290, 300, 50);
		
		//���� ���� ��Ȳ
		p2.setBounds(1000, 200, 850, 350);
		p2.setBorder(new LineBorder(Color.GRAY, 4));
		p2.setLayout(null);
		p2.add(table);
		table.setBounds(25, 25, 800, 300);
		table.setLayout(new GridLayout(4,5,10,10));
		for(int i=0; i<20; i++){
			table_cell[i] = new JLabel();
			table.add(table_cell[i]);
			table_cell[i].setBorder(new LineBorder(Color.BLACK,2));
			table_cell[i].setFont(new Font("����",Font.BOLD,20));
			table_cell[i].setOpaque(true);        
            table_cell[i].setBackground(Color.WHITE);
            table_cell[i].setHorizontalAlignment(JLabel.CENTER);
		}
		frame.add(p2);
		
		//���/��ȸ
		p3.setBounds(50, 600, 1800, 350);
		p3.setBorder(new LineBorder(Color.GRAY, 4));
		p3.setLayout(null);
		frame.add(p3);
		p3.add(tab);
		tab.setBounds(50, 25, 1700, 300);
		tab.addTab("��", tab1);
		tab.addTab("����", tab2);
		tab.addTab("����", tab3);
		tab1.setLayout(null);
		tab2.setLayout(null);
		tab3.setLayout(null);
		tab1.add(customer_name);
		tab1.add(customer_name_input);
		tab1.add(customer_signup);
		customer_signup.addActionListener(this);
		tab1.add(customer_list);
		customer_list.addActionListener(this);
		tab1.add(cust_info);
		cust_info.setViewportView(customer_info);
		customer_info.setEditable(false);
		customer_name.setBounds(50, 50, 250, 50);
		customer_name_input.setBounds(350, 50, 250, 50);
		customer_signup.setBounds(50, 150, 250, 50);
		customer_list.setBounds(350, 150, 250, 50);
		cust_info.setBounds(650, 10, 1000, 250);
		tab2.add(room_num);
		tab2.add(room_num_input);
		room_num_input.addActionListener(this);
		tab2.add(room_info);
		room_info.setViewportView(room_num_info);
		room_num_info.setEditable(false);
		room_num.setBounds(50, 50, 250, 50);
		room_num_input.setBounds(350, 50, 250, 50);
		room_info.setBounds(650, 10, 1000, 250);
		tab3.add(employee);
		tab3.add(employee_input);
		tab3.add(employee_signup);
		employee_signup.addActionListener(this);
		tab3.add(employee_list);
		employee_list.addActionListener(this);
		tab3.add(em_info);
		em_info.setViewportView(employee_info);
		employee_info.setEditable(false);
		employee.setBounds(50, 50, 250, 50);
		employee_input.setBounds(350, 50, 250, 50);
		employee_signup.setBounds(50, 150, 250, 50);
		employee_list.setBounds(350, 150, 250, 50);
		em_info.setBounds(650, 10, 1000, 250);
		
		//ȸ������
		cust_signup.setBounds(500, 300, 800, 500);
		cust_signup.add(signup_customer);
		cust_signup.add(signup_customer_input);
		cust_signup.add(signup_cu_sex);
		cust_signup.add(signup_cu_sex_input);
		cust_signup.add(signup_cu_address);
		cust_signup.add(signup_cu_address_input);
		cust_signup.add(signup_cu_tel);
		cust_signup.add(signup_cu_tel_input);
		cust_signup.add(signup_cu_ok);
		signup_cu_ok.addActionListener(this);
		cust_signup.add(signup_cu_cancle);
		signup_cu_cancle.addActionListener(this);
		cust_signup.setLayout(null);
		signup_customer.setBounds(50, 50, 300, 50);
		signup_customer_input.setBounds(350, 50, 300, 50);
		signup_cu_sex.setBounds(50, 125, 300, 50);
		signup_cu_sex_input.setBounds(350, 125, 300, 50);
		signup_cu_address.setBounds(50, 200, 300, 50);
		signup_cu_address_input.setBounds(350, 200, 300, 50);
		signup_cu_tel.setBounds(50, 275, 300, 50);
		signup_cu_tel_input.setBounds(350, 275, 300, 50);
		signup_cu_ok.setBounds(25, 350, 300, 50);
		signup_cu_cancle.setBounds(375, 350, 300, 50);
		signup_cu_sex_input.addItem("��");
		signup_cu_sex_input.addItem("��");
		signup_cu_address_input.addItem("����");
		signup_cu_address_input.addItem("�Ȼ�");
		signup_cu_address_input.addItem("����");
		signup_cu_address_input.addItem("�뱸");
		signup_cu_address_input.addItem("����");
		signup_cu_address_input.addItem("��õ");
		
		em_signup.setBounds(500, 300, 800, 500);
		em_signup.add(signup_employee);
		em_signup.add(signup_employee_input);
		em_signup.add(signup_em_sex);
		em_signup.add(signup_em_sex_input);
		em_signup.add(signup_em_address);
		em_signup.add(signup_em_address_input);
		em_signup.add(signup_em_tel);
		em_signup.add(signup_em_tel_input);
		em_signup.add(signup_em_ok);
		signup_em_ok.addActionListener(this);
		em_signup.add(signup_em_cancle);
		signup_em_cancle.addActionListener(this);
		em_signup.setLayout(null);
		signup_employee.setBounds(50, 50, 300, 50);
		signup_employee_input.setBounds(350, 50, 300, 50);
		signup_em_sex.setBounds(50, 125, 300, 50);
		signup_em_sex_input.setBounds(350, 125, 300, 50);
		signup_em_address.setBounds(50, 200, 300, 50);
		signup_em_address_input.setBounds(350, 200, 300, 50);
		signup_em_tel.setBounds(50, 275, 300, 50);
		signup_em_tel_input.setBounds(350, 275, 300, 50);
		signup_em_ok.setBounds(25, 350, 300, 50);
		signup_em_cancle.setBounds(375, 350, 300, 50);
		signup_em_sex_input.addItem("��");
		signup_em_sex_input.addItem("��");
		signup_em_address_input.addItem("����");
		signup_em_address_input.addItem("�Ȼ�");
		signup_em_address_input.addItem("����");
		signup_em_address_input.addItem("�뱸");
		signup_em_address_input.addItem("����");
		signup_em_address_input.addItem("��õ");
	}
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == loginbutton){ //�α���
			username = idinput.getText();
            password = new String(pwinput.getPassword());
            connectDB();
            login.setVisible(false);
		}
		else if(e.getSource() == read){ //���������� �б�
        	String sqlStr;
        	PreparedStatement stmt;
        	ResultSet rs;
        	int returnvalue = filechoose.showOpenDialog(frame);
        	if(returnvalue == JFileChooser.APPROVE_OPTION){
        		File returnfile = filechoose.getSelectedFile();
        		try{
        			BufferedReader in = new BufferedReader(new FileReader(returnfile));
        			String s,str[];
        			ArrayList<String> text = new ArrayList<String>();
        			int customers=0,employees=0,rooms=0;
        			while((s=in.readLine()) != null){
        				text.add(s);
        			}
        			try{
	        			customers = Integer.parseInt(text.get(0));
	        			for(int i=1; i<1+customers; i++){
	        				str = text.get(i).split("\\s+");
	    					//�ҷ��� �ؽ�Ʈ���� �� �����͸� hotel_customer�� ����
    						sqlStr = "insert into hotel_customer values('"+str[0]+"','"+str[1]+"','"+str[2]+"','"+str[3]+"')";
    			        	stmt = dbTest.prepareStatement(sqlStr);
    			        	rs = stmt.executeQuery();
	        			}
	        			employees = Integer.parseInt(text.get(customers+1));
	        			for(int i=customers+2; i<customers+employees+2; i++){
	        				str = text.get(i).split("\\s+");
	    					//�ҷ��� �ؽ�Ʈ���� ���� �����͸� hotel_employee�� ����
    						sqlStr = "insert into hotel_employee values('"+str[0]+"','"+str[1]+"','"+str[2]+"','"+str[3]+"')";
    			        	stmt = dbTest.prepareStatement(sqlStr);
    			        	rs = stmt.executeQuery();
	        			}
	        			rooms = Integer.parseInt(text.get(customers+employees+2));
	        			for(int i=customers+employees+3; i<text.size(); i++){
	        				str = text.get(i).split("\\s+");
	    					 //�ҷ��� �ؽ�Ʈ���� ���� �����͸� hotel_room�� ����
    						sqlStr = "insert into hotel_room values('"+str[0]+"','"+str[1]+"','"+str[2]+"')";
    			        	stmt = dbTest.prepareStatement(sqlStr);
    			        	rs = stmt.executeQuery();
	        			}
	        			//�ҷ��� �ؽ�Ʈ���� ���� �����͸� �о� ���� ���̺��� ����
	        			sqlStr = "select roomnumber from hotel_room";
	        			stmt = dbTest.prepareStatement(sqlStr);
			        	rs = stmt.executeQuery();
			        	int i=0;
			        	while(rs.next()){
			        		table_cell[i].setText(rs.getString("roomnumber"));
			        		room_input.addItem(Integer.parseInt(rs.getString("roomnumber")));
			        		room_num_input.addItem(Integer.parseInt(rs.getString("roomnumber")));
			        		i++;
			        	}
	        			roomcheck();
        			}
        			catch(Exception ex){
        				JOptionPane.showMessageDialog(frame,"������ �дµ� �����߽��ϴ�.");
        			}
        		}
        		catch(IOException ie){
        			System.out.println("���� �б� ����");
        			JOptionPane.showMessageDialog(frame,"�߸��� �����Դϴ�.");
        		}
        	}
        }
		else if(e.getSource() == reserve){ //����
			String sqlStr;
			String customer_tel,employee_tel;
        	PreparedStatement stmt;
        	ResultSet rs;
        	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        	Boolean ischange=false;
        	String room_number_="",checkin_="";
        	int days_=0;
        	try{
        		sqlStr="select count(*) from hotel_customer where name='"+customer_input.getText()+"'";
        		stmt = dbTest.prepareStatement(sqlStr);
	        	rs = stmt.executeQuery();
	        	rs.next();
	        	if(rs.getInt("count(*)")==0) throw new SQLException("��ϵ��� ���� ��");
	        	if(!checkin_input.getText().matches("^[0-9]{8}$")) throw new StringIndexOutOfBoundsException("�߸��� ��¥");
	        	String check = checkin_input.getText().substring(0,4)+"/"+checkin_input.getText().substring(4,6)+"/"+checkin_input.getText().substring(6,8);
	        	try{
	        		sqlStr="select * from reserve where customer_name='"+customer_input.getText()+"'";
	        		stmt = dbTest.prepareStatement(sqlStr);
		        	rs = stmt.executeQuery();
	        		while(rs.next()){
	        			Calendar checkincal = Calendar.getInstance();
	        			try{ //��¥�� ��ġ���� üũ�ؼ� ischange�� ����
	        				String[] checkin = check.split("/");
	        				Date reserveindate = format.parse(checkin[0]+"-"+checkin[1]+"-"+checkin[2]); //�����Ϸ��� ��¥
	        				Date checkindate = format.parse(rs.getString("checkin")); //����Ǿ� �ִ� üũ�� ��¥
	        				checkincal.setTime(checkindate);
	            			checkincal.add(Calendar.DATE, rs.getInt("days")-1);
	            			Date checkoutdate = checkincal.getTime(); //����Ǿ� �ִ� üũ�ƿ� ��¥
	            			checkincal.setTime(reserveindate);
	            			checkincal.add(Calendar.DATE, Integer.parseInt(days_input.getSelectedItem()+""));
	            			Date reserveoutdate = checkincal.getTime();
	            			//üũ�� ��¥ <= ���� ��¥ <= üũ�ƿ� ��¥ (��¥ ��ġ�� ��� üũ)
	            			if((reserveindate.compareTo(checkindate)>=0 && reserveindate.compareTo(checkoutdate)<=0) || (checkindate.compareTo(reserveindate)>=0 && checkindate.compareTo(reserveoutdate)<=0)){
	            				ischange=true;
	            				room_number_ = rs.getString("room_number");
	            				checkin_ = rs.getString("checkin").substring(0,4)+"/"+rs.getString("checkin").substring(5,7)+"/"+rs.getString("checkin").substring(8,10);
	            				days_ = rs.getInt("days");
	            			}
	        			}
	        			catch(ParseException pe){
	        				System.out.println(pe);
	        			}
	        		}
	        		sqlStr="select tel from hotel_customer where name='"+customer_input.getText()+"'";
        			stmt = dbTest.prepareStatement(sqlStr);
		        	rs = stmt.executeQuery();
		        	rs.next();
		        	customer_tel = rs.getString("tel");
		        	if(ischange){ //���� ����
	        			sqlStr="delete from reserve where customer_name='"+customer_input.getText()+"' and room_number='"+room_number_+"' and checkin='"+checkin_+"' and days="+days_;
	        			stmt = dbTest.prepareStatement(sqlStr);
			        	rs = stmt.executeQuery();
			        	sqlStr="select name,tel from hotel_employee order by DBMS_RANDOM.RANDOM";
		        		stmt = dbTest.prepareStatement(sqlStr);
			        	rs = stmt.executeQuery();
			        	rs.next();
			        	employee_tel = rs.getString("tel");
			        	sqlStr="insert into reserve values('"+customer_tel+"','"+employee_tel+"','"+customer_input.getText()+"','"+rs.getString("name")+"','"+room_input.getSelectedItem()+"','"+check+"',"+Integer.parseInt(days_input.getSelectedItem()+"")+")";
		        		stmt = dbTest.prepareStatement(sqlStr);
			        	rs = stmt.executeQuery();
			        	rs.next();
			        	JOptionPane.showMessageDialog(frame,"���� ���� �Ǿ����ϴ�.");
			        	reserved();
			        	roomcheck();
	        		}
	        		else{ //���� ���
		        		sqlStr="select name,tel from hotel_employee order by DBMS_RANDOM.RANDOM";
		        		stmt = dbTest.prepareStatement(sqlStr);
			        	rs = stmt.executeQuery();
			        	rs.next();
			        	employee_tel = rs.getString("tel");
			        	sqlStr="insert into reserve values('"+customer_tel+"','"+employee_tel+"','"+customer_input.getText()+"','"+rs.getString("name")+"','"+room_input.getSelectedItem()+"','"+check+"',"+Integer.parseInt(days_input.getSelectedItem()+"")+")";
		        		stmt = dbTest.prepareStatement(sqlStr);
			        	rs = stmt.executeQuery();
			        	rs.next();
			        	JOptionPane.showMessageDialog(frame,"���� ��� �Ǿ����ϴ�.");
			        	reserved();
			        	roomcheck();
	        		}
	        	}
	        	catch(SQLException se){
	        		System.out.println(se);
	        		JOptionPane.showMessageDialog(frame,"�߸��� �����Դϴ�.");
	        	}
        	}
        	catch(SQLException se){
        		JOptionPane.showMessageDialog(frame,"��ϵ��� ���� ���Դϴ�.");
        		System.out.println(se);
        	}
        	catch(StringIndexOutOfBoundsException ie){
        		JOptionPane.showMessageDialog(frame,"�߸��� ��¥�Դϴ�. yyyymmdd �������� �Է��ϼ���.");
        		System.out.println(ie);
        	}
		}
		else if(e.getSource() == cancle){ //�������
			String sqlStr;
        	PreparedStatement stmt;
        	ResultSet rs;
        	try{
        		String check = checkin_input.getText().substring(0,4)+"/"+checkin_input.getText().substring(4,6)+"/"+checkin_input.getText().substring(6,8);
        		sqlStr="delete from reserve where customer_name='"+customer_input.getText()+"' and room_number='"+room_input.getSelectedItem()+"' and checkin='"+check+"' and days="+Integer.parseInt(days_input.getSelectedItem()+"");
        		stmt = dbTest.prepareStatement(sqlStr);
	        	rs = stmt.executeQuery();
	        	rs.next();
	        	JOptionPane.showMessageDialog(frame,"������ҵǾ����ϴ�.");
	        	reserved();
	        	roomcheck();
        	}
        	catch(SQLException se){
        		System.out.println(se);
        		JOptionPane.showMessageDialog(frame,"���� ������ �����ϴ�.");
        	}
		}
        else if(e.getSource() == customer_list){ //����ȸ
        	String result="";
        	String sqlStr;
        	PreparedStatement stmt;
        	ResultSet rs;
        	try{
    			sqlStr = "select name,sex,address,tel from hotel_customer where name='"+customer_name_input.getText()+"'";
    			stmt = dbTest.prepareStatement(sqlStr);
	        	rs = stmt.executeQuery();
	        	rs.next();
        		result += "����: "+rs.getString("name");
	        	result += "\n����: "+rs.getString("sex");
	        	result += "\n�ּ�: "+rs.getString("address");
	        	result += "\n����ó: "+rs.getString("tel");
	        	sqlStr = "select sum(days) from hotel_customer,reserve where name=customer_name and name='"+customer_name_input.getText()+"'";
    			stmt = dbTest.prepareStatement(sqlStr);
	        	rs = stmt.executeQuery();
	        	rs.next();
	        	if(rs.getString("sum(days)") != null) result += "\n�� �����Ⱓ: "+rs.getString("sum(days)");
	        	else result += "\n�� �����Ⱓ: ����";
	        	sqlStr = "select max(checkin) from hotel_customer h,reserve r where name=customer_name and name='"+customer_name_input.getText()+"'";
    			stmt = dbTest.prepareStatement(sqlStr);
	        	rs = stmt.executeQuery();
	        	rs.next();
	        	if(rs.getString("max(checkin)") != null) result += "\n�ֱ� ������: "+rs.getString("max(checkin)").substring(0,10);
	        	else result += "\n�ֱ� ������: ����";
	        	sqlStr = "select employee_name,count(employee_name) from reserve where customer_name='"+customer_name_input.getText()+"' group by (employee_name) having count(employee_name) in (select max(count(employee_name)) from reserve where customer_name='"+customer_name_input.getText()+"' group by (employee_name))";
    			stmt = dbTest.prepareStatement(sqlStr);
	        	rs = stmt.executeQuery();
	        	if(!rs.next()) result += "\n������������(�ִ�) : ����";
	        	result += "\n������������(�ִ�): "+rs.getString("employee_name")+"     ("+rs.getInt("count(employee_name)")+"ȸ)";
	        	customer_info.setText(result);
			}
			catch(SQLException se){
				System.out.println(se);
				if(result == ""){
					customer_info.setText("�������� �����ϴ�.");
				}
				else{
					customer_info.setText(result);
				}
			}
        }
        else if(e.getSource() == room_num_input){ //���� ��ȸ
        	String result="";
        	String todate = today.getText();
        	String sqlStr;
        	PreparedStatement stmt;
        	ResultSet rs;
        	try{
    			sqlStr = "select roomnumber,capacity,type from hotel_room where roomnumber='"+room_num_input.getSelectedItem()+"'";
    			stmt = dbTest.prepareStatement(sqlStr);
	        	rs = stmt.executeQuery();
	        	rs.next();
        		result += "���ȣ: "+rs.getString("roomnumber");
	        	result += "\n�����ο�: "+rs.getString("capacity");
	        	result += "\nŸ��: "+rs.getString("type");
	        	sqlStr = "select count(*) from reserved where room_number='"+room_num_input.getSelectedItem()+"' and year="+Integer.parseInt(todate.substring(1,5))+" and month="+Integer.parseInt(todate.substring(6,8))+" and day="+Integer.parseInt(todate.substring(9,11));
    			stmt = dbTest.prepareStatement(sqlStr);
	        	rs = stmt.executeQuery();
	        	rs.next();
	        	if(rs.getInt("count(*)")==0){
	        		result += "\n����: �������";
	        	}
	        	else{
	        		result += "\n����: ������";
	        	}
	        	sqlStr = "select customer_name,count(customer_name) from reserve where room_number='"+room_num_input.getSelectedItem()+"' group by(customer_name) having count(customer_name) in (select max(count(customer_name)) from reserve where room_number='"+room_num_input.getSelectedItem()+"' group by(customer_name))";
    			stmt = dbTest.prepareStatement(sqlStr);
	        	rs = stmt.executeQuery();
	        	if(!rs.next()) result += "\n������(�ִ�) : ����\n������������(�ִ�) : ����";
	        	result += "\n������(�ִ�): "+rs.getString("customer_name")+"     ("+rs.getInt("count(customer_name)")+"ȸ)";
	        	sqlStr = "select employee_name,count(employee_name) from reserve where room_number='"+room_num_input.getSelectedItem()+"' group by (employee_name) having count(employee_name) in (select max(count(employee_name)) from reserve where room_number='"+room_num_input.getSelectedItem()+"' group by (employee_name))";
    			stmt = dbTest.prepareStatement(sqlStr);
	        	rs = stmt.executeQuery();
	        	rs.next();
	        	result += "\n������������(�ִ�): "+rs.getString("employee_name")+"     ("+rs.getInt("count(employee_name)")+"ȸ)";
	        	room_num_info.setText(result);
			}
			catch(SQLException se){
				System.out.println(se);
				room_num_info.setText(result);
			}
        }
        else if(e.getSource() == employee_list){ //���� ��ȸ
        	String result="";
        	String sqlStr;
        	PreparedStatement stmt;
        	ResultSet rs;
        	try{
    			sqlStr = "select name,sex,address,tel from hotel_employee where name='"+employee_input.getText()+"'";
    			stmt = dbTest.prepareStatement(sqlStr);
	        	rs = stmt.executeQuery();
	        	rs.next();
        		result += "������: "+rs.getString("name");
	        	result += "\n����: "+rs.getString("sex");
	        	result += "\n�ּ�: "+rs.getString("address");
	        	result += "\n����ó: "+rs.getString("tel");
	        	sqlStr = "select customer_name,count(customer_name) from reserve where employee_name='"+employee_input.getText()+"' group by (customer_name) having count(customer_name) in (select max(count(customer_name)) from reserve where employee_name='"+employee_input.getText()+"' group by (customer_name))";
    			stmt = dbTest.prepareStatement(sqlStr);
	        	rs = stmt.executeQuery();
	        	if(!rs.next()) result += "\n�����(�ִ�): ����\n��������(�ִ�): ����";
	        	result += "\n�����(�ִ�): "+rs.getString("customer_name")+"     ("+rs.getInt("count(customer_name)")+"ȸ)";
	        	sqlStr = "select room_number,count(room_number) from reserve where employee_name='"+employee_input.getText()+"' group by (room_number) having count(room_number) in (select max(count(room_number)) from reserve where employee_name='"+employee_input.getText()+"' group by (room_number))";
    			stmt = dbTest.prepareStatement(sqlStr);
	        	rs = stmt.executeQuery();
	        	rs.next();
	        	result += "\n��������(�ִ�): "+rs.getString("room_number")+"     ("+rs.getInt("count(room_number)")+"ȸ)";
	        	employee_info.setText(result);
			}
			catch(SQLException se){
				System.out.println(se);
				if(result == ""){
					employee_info.setText("���������� �����ϴ�.");
				}
				else{
					employee_info.setText(result);
				}
			}
        }
        else if(e.getSource() == customer_signup){ //������
        	cust_signup.setVisible(true);
        }
        else if(e.getSource() == signup_cu_ok){
        	String sqlStr;
        	PreparedStatement stmt;
        	ResultSet rs;
        	try{
        		sqlStr = "insert into hotel_customer values('"+signup_customer_input.getText()+"','"+signup_cu_sex_input.getSelectedItem()+"','"+signup_cu_address_input.getSelectedItem()+"','"+signup_cu_tel_input.getText()+"')";
        		stmt = dbTest.prepareStatement(sqlStr);
	        	rs = stmt.executeQuery();
	        	rs.next();
	        	signup_customer_input.setText("");
        		signup_cu_tel_input.setText("");
	        	cust_signup.setVisible(false);
	        	JOptionPane.showMessageDialog(frame,"���� �Ϸ� �Ǿ����ϴ�.");
        	}
        	catch(SQLException se){
        		signup_customer_input.setText("");
        		signup_cu_tel_input.setText("");
        		System.out.println(se);
        		JOptionPane.showMessageDialog(cust_signup,"�̹� ���Ե� ���Դϴ�.");
        	}
        }
        else if(e.getSource() == signup_cu_cancle){
        	signup_customer_input.setText("");
    		signup_cu_tel_input.setText("");
        	cust_signup.setVisible(false);
        }
        else if(e.getSource() == employee_signup){ //��������
        	em_signup.setVisible(true);
        }
        else if(e.getSource() == signup_em_ok){
        	String sqlStr;
        	PreparedStatement stmt;
        	ResultSet rs;
        	try{
        		sqlStr = "insert into hotel_employee values('"+signup_employee_input.getText()+"','"+signup_em_sex_input.getSelectedItem()+"','"+signup_em_address_input.getSelectedItem()+"','"+signup_em_tel_input.getText()+"')";
        		stmt = dbTest.prepareStatement(sqlStr);
	        	rs = stmt.executeQuery();
	        	rs.next();
	        	signup_employee_input.setText("");
        		signup_em_tel_input.setText("");
	        	em_signup.setVisible(false);
	        	JOptionPane.showMessageDialog(frame,"���� �Ϸ� �Ǿ����ϴ�.");
        	}
        	catch(SQLException se){
        		signup_employee_input.setText("");
        		signup_em_tel_input.setText("");
        		System.out.println(se);
        		JOptionPane.showMessageDialog(em_signup,"�̹� ���Ե� �����Դϴ�.");
        	}
        }
        else if(e.getSource() == signup_em_cancle){
        	signup_employee_input.setText("");
    		signup_em_tel_input.setText("");
        	em_signup.setVisible(false);
        }
    }
	private void connectDB(){
        try {
        	String sqlStr;
        	PreparedStatement stmt;
        	ResultSet rs;
            Class.forName("oracle.jdbc.OracleDriver");
            dbTest = DriverManager.getConnection("jdbc:oracle:thin:" + "@localhost:1521:XE", username, password);
            frame.setVisible(true);
            reserved();
            sqlStr = "select roomnumber from hotel_room";
			stmt = dbTest.prepareStatement(sqlStr);
        	rs = stmt.executeQuery();
        	int i=0;
        	while(rs.next()){
        		table_cell[i].setText(rs.getString("roomnumber"));
        		room_input.addItem(Integer.parseInt(rs.getString("roomnumber")));
        		room_num_input.addItem(Integer.parseInt(rs.getString("roomnumber")));
        		i++;
        	}
			roomcheck();
        }
        catch(Exception e){
        	System.out.println("1");
            System.out.println(e);
        }
    }
	public void reserved(){ //����� ��, ��¥�� reserved ���̺� ����
		String sqlStr,sqlStr_;
    	PreparedStatement stmt,stmt_;
    	ResultSet rs,rs_;
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    	try{ //���̺� �ʱ�ȭ
    		sqlStr = "drop table reserved";
			stmt = dbTest.prepareStatement(sqlStr);
        	rs = stmt.executeQuery();
    	}
    	catch(SQLException se){
    		System.out.println("2");
    		System.out.println(se);
    	}
    	
    	try{ //���̺� �ʱ�ȭ
        	sqlStr = "create table reserved(customer_name varchar(10) not null,room_number varchar(3) not null,year number(4) not null,month number(2) not null,day number(2) not null)";
			stmt = dbTest.prepareStatement(sqlStr);
        	rs = stmt.executeQuery();
    	}
    	catch(SQLException se){
    		System.out.println("3");
    		System.out.println(se);
    	}
    	try{ //reserved�� ������ ����
			sqlStr = "select customer_name,room_number,checkin,days from reserve";
			stmt = dbTest.prepareStatement(sqlStr);
        	rs = stmt.executeQuery();
        	Calendar calendar = Calendar.getInstance();
        	while(rs.next()){
        		try{
        			Date to = format.parse(rs.getString("checkin"));
        			calendar.setTime(to);
        		}
        		catch(ParseException pe){
        			System.out.println("4");
        			System.out.println(pe);
        		}
        		for(int i=0; i<rs.getInt("days"); i++){ //checkin�� days�� �о �����ؾ� �� ��¥���� ���
        			String date = format.format(calendar.getTime());
	        		sqlStr_="insert into reserved values('"+rs.getString("customer_name")+"','"+rs.getString("room_number")+"',"+Integer.parseInt(date.substring(0,4))+","+Integer.parseInt(date.substring(5,7))+","+Integer.parseInt(date.substring(8,10))+")";
	        		stmt_ = dbTest.prepareStatement(sqlStr_);
	            	rs_ = stmt_.executeQuery();
	            	rs_.next();
	            	calendar.add(Calendar.DATE,1);
            	}
        	}
		}
		catch(SQLException se){
			System.out.println("5");
			System.out.println(se);
		}
	}
	public void roomcheck(){ //����� ���� ��������� ǥ��
		String todate = today.getText();
		String sqlStr;
    	PreparedStatement stmt;
    	ResultSet rs;
    	try{
    		sqlStr="select room_number from reserved where year="+Integer.parseInt(todate.substring(1,5))+" and month="+Integer.parseInt(todate.substring(6,8))+" and day="+Integer.parseInt(todate.substring(9,11));
    		stmt = dbTest.prepareStatement(sqlStr);
        	rs = stmt.executeQuery();
        	for(int i=0; i<20; i++){
        		table_cell[i].setBackground(Color.WHITE);
    		}
        	while(rs.next()){
        		for(int i=0; i<20; i++){
	        		if(table_cell[i].getText().equals(rs.getString("room_number"))) table_cell[i].setBackground(Color.YELLOW);
        		}
        	}
    	}
    	catch(SQLException se){
    		System.out.println("6");
    		System.out.println(se);
    	}
	}
	public static void main(String[] args){
		hotel test = new hotel();
	}
}