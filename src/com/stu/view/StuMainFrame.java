package com.stu.view;

import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.stu.dao.impl.StuDaoImpl;
import com.stu.model.StuInfo;
import com.stu.model.StuTableModel;

public class StuMainFrame extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JButton jb1, jb2, jb3, jb4;
	private JLabel jl;
	private JTextField jtf;
	private JTable jt;
	private JPanel jp1, jp2;
	private JScrollPane jsp;
	StuTableModel stm;

	public StuMainFrame() {
		jb1 = new JButton("查询");
		jb1.setActionCommand("query");
		jb1.addActionListener(this);
		jtf = new JTextField(10);
		jl = new JLabel("请输入要查询的学生ID:");
		jb2 = new JButton("添加");
		jb2.setActionCommand("addStu");
		jb2.addActionListener(this);
		jb3 = new JButton("修改");
		jb3.setActionCommand("upStu");
		jb3.addActionListener(this);
		jb4 = new JButton("删除");
//		jb4.setBackground(Color.RED);
		jb4.setActionCommand("delStu");
		jb4.addActionListener(this);
		jp1 = new JPanel();
		jp2 = new JPanel();
		stm = new StuTableModel();
		jt = new JTable(stm);
		jsp = new JScrollPane(jt);

		// 北部组件
		jp1.add(jl);
		jp1.add(jtf);
		jp1.add(jb1);
		this.add(jp1, "North");

		// 中部组件
		this.add(jsp);

		// 南部组件
		jp2.add(jb2);
		jp2.add(jb3);
		jp2.add(jb4);
		this.add(jp2, "South");

		this.setTitle("学生管理系统");
		Toolkit toolkit = this.getToolkit();
		Image myImage = toolkit.getImage("image/1.jpg");
		this.setIconImage(myImage);
		this.setBounds(300, 300, 400, 280);
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("query")) {
			if (this.jtf.getText().equals("") || this.jtf.getText() == null) {
				System.out.println("请输入要查询的学生的姓名");
				JOptionPane.showMessageDialog(this, "请输入需要查询的学生姓名!", "消息提示",
						JOptionPane.ERROR_MESSAGE);
				return;
			} else {
				try {
					System.out.println("查询学生信息");
					int stuId = Integer.parseInt(this.jtf.getText());
					StuInfo stu = new StuDaoImpl().queryOneStuById(stuId);
					stm = new StuTableModel(stu);
					jt.setModel(stm);
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(this, "亲,您所输入的学号或年龄有误,请重新输入!必须是数字哦","消息提示",JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
			}
		} else if (e.getActionCommand().equals("addStu")) {
			System.out.println("添加学生信息");
			new StuAddDialog(this, "添加学生", true);
			stm = new StuTableModel();
			jt.setModel(stm);
		} else if(e.getActionCommand().equals("delStu")){
			try {
				System.out.println("删除学生信息");
				stm = new StuTableModel();
				int row = jt.getSelectedRow();
				if(row==-1){
					JOptionPane.showMessageDialog(this, "请选择需要删除的学生所在的行!","消息提示",JOptionPane.INFORMATION_MESSAGE);
					return;
				}
//				int [] rows = jt.getSelectedRows();
//				System.out.println(rows[0]+""+rows[1]);
//			    int col = jt.getSelectedColumn();
				int stuId = Integer.parseInt((String)stm.getValueAt(row, 0));
				String stuName = (String)stm.getValueAt(row, 1);
				System.out.println("要删除的学生ID是:"+stuId+"学生姓名是:"+stuName);
				if(JOptionPane.showConfirmDialog(this, "您确定删除此学生信息吗?", "消息提示",
						JOptionPane.YES_NO_OPTION)==0){
					if(new StuDaoImpl().delUser(stuId)){
						Thread.sleep(1000);
						JOptionPane.showMessageDialog(this, "删除学生["+stuName+"]信息成功!","消息提示",JOptionPane.OK_OPTION);
						stm = new StuTableModel();
						jt.setModel(stm);
					}else {
						Thread.sleep(1000);
						JOptionPane.showMessageDialog(this, "删除学生["+stuName+"]信息失败!","消息提示",JOptionPane.WARNING_MESSAGE);
					}
					
				}
			} catch (NumberFormatException e1) {
				e1.printStackTrace();
			} catch (HeadlessException e1) {
				e1.printStackTrace();
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			
		}else if(e.getActionCommand().equals("upStu")){
			System.out.println("修改学生信息……");
			try {
				
				stm = new StuTableModel();
				int row = jt.getSelectedRow();
				if(row==-1){
					JOptionPane.showMessageDialog(this, "请选择需要修改的学生所在的行!","消息提示",JOptionPane.INFORMATION_MESSAGE);
					return;
				}
//				int [] rows = jt.getSelectedRows();
//				System.out.println(rows[0]+""+rows[1]);
//			    int col = jt.getSelectedColumn();
				int stuId = Integer.parseInt((String)stm.getValueAt(row, 0));
				StuDaoImpl stuImpl = new StuDaoImpl();
				
				new UpStuDialog(this,"修改学生",true,stuImpl.queryOneStuById(stuId));
				stm = new StuTableModel();
				jt.setModel(stm);
				
			} catch (NumberFormatException e1) {
				e1.printStackTrace();
			} catch (HeadlessException e1) {
				e1.printStackTrace();
			}
		}
	}
}
