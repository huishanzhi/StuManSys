package com.stu.view;

import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.stu.dao.impl.StuDaoImpl;
import com.stu.model.StuInfo;

public class UpStuDialog extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JLabel jl1, jl2, jl3, jl4, jl5, jl6;
	private JTextField jtf1, jtf2, jtf3, jtf4, jtf5, jtf6;
	private JButton jb1, jb2;
	private JPanel jp1, jp2, jp3, jp4;

	private StuDaoImpl stuImpl = new StuDaoImpl();

	public UpStuDialog() {

	}

	public UpStuDialog(Frame owner, String title, boolean modal, StuInfo stu) {
		super(owner, title, modal);

		//中部是2个panel 
		jp4 = new JPanel(new GridLayout(1,2));
		//中部 ---> 西部	
		jp1 = new JPanel(new GridLayout(6,1));
		jl1 = new JLabel("学号",JLabel.CENTER);
		jl2 = new JLabel("姓名",JLabel.CENTER);
		jl3 = new JLabel("年龄",JLabel.CENTER);
		jl4 = new JLabel("性别",JLabel.CENTER);
		jl5 = new JLabel("系别",JLabel.CENTER);
		jl6 = new JLabel("家庭住址",JLabel.CENTER);
		//中部 ---> 东部
		jp2 = new JPanel(new GridLayout(6,1));
		jtf1 = new JTextField(10);
		jtf1.setEnabled(false);
		jtf1.setText(stu.getStuId()+"");
		jtf2 = new JTextField(10);
		jtf2.setText(stu.getStuName());
		jtf3 = new JTextField(10);
		jtf3.setText(stu.getStuAge()+"");
		jtf4 = new JTextField(10);
		jtf4.setText(stu.getStuSex());
		jtf5 = new JTextField(10);
		jtf5.setText(stu.getStuDept());
		jtf6 = new JTextField(10);
		jtf6.setText(stu.getStuAddress());
		
		jp1.add(jl1);jp1.add(jl2);jp1.add(jl3);jp1.add(jl4);jp1.add(jl5);jp1.add(jl6);
		jp2.add(jtf1);jp2.add(jtf2);jp2.add(jtf3);jp2.add(jtf4);jp2.add(jtf5);jp2.add(jtf6);
		jp4.add(jp1);jp4.add(jp2);
		
		//南部
		jp3 = new JPanel();
		jb1 = new JButton("确定");
		jb1.setActionCommand("fix");
		jb1.addActionListener(this);
		jb2 = new JButton("取消");
		jb2.setActionCommand("cancle");
		jb2.addActionListener(this);
		
		jp3.add(jb1);jp3.add(jb2);
		
		
		
		this.add(jp4);
		this.add(jp3,"South");
		this.setBounds(350, 350, 300, 180);
		this.setVisible(true);
//		this.setResizable(false);
		
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("fix")){
			
			String s_stuId = this.jtf1.getText().trim();
			int stuId = Integer.parseInt(s_stuId);
			String stuName = this.jtf2.getText().trim();
			String stuAge = this.jtf3.getText().trim();
			String stuSex = this.jtf4.getText().trim();
			String stuDept = this.jtf5.getText().trim();
			String stuAddress = this.jtf6.getText().trim();
			
			if(stuName==null||stuDept==null||stuAge==null||stuSex==null||stuAddress==null
					||stuName.equals("")||stuDept.equals("")||stuAge.equals("")||stuSex.equals("")||stuAddress.equals("")){
				JOptionPane.showMessageDialog(this, "您所输入的信息不完整,请填写全部信息!","消息提示",JOptionPane.ERROR_MESSAGE);
				return;
			}else if(!stuSex.equals("男")&&!stuSex.equals("女")){
				JOptionPane.showMessageDialog(this, "性别必须为男或者女,请修改该选项的值!","消息提示",JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			try {
				if(stuImpl.upUser(new StuInfo(stuId,stuName,Integer.parseInt(stuAge),stuSex,stuAddress,stuDept))){
					JOptionPane.showMessageDialog(this, "修改学生信息成功!","消息提示",JOptionPane.OK_OPTION);
					this.dispose();
			
				}else{
					JOptionPane.showMessageDialog(this, "修改学生失败,请检查输入参数是否有误!点击取消按钮结束修改学校信息!","消息提示",JOptionPane.WARNING_MESSAGE);
//					this.dispose();
				}
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(this, "亲,您所输入的学号或年龄有误,请重新输入!必须是数字哦","消息提示",JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			} catch (HeadlessException e1) {
				JOptionPane.showMessageDialog(this, "系统出现未知错误,系统将返回主界面!","消息提示",JOptionPane.ERROR_MESSAGE);
				this.dispose();
				e1.printStackTrace();
			}
			
		}else if(e.getActionCommand().equals("cancle")){
			System.out.println("取消修改学生");
			this.dispose();
		}
	}
	
}
