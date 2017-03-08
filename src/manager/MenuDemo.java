package manager;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import java.util.List;
import java.util.Scanner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Good;
import bean.Lists;
import bean.Orders;

public class MenuDemo {

	Manager manager = Manager.getInstance();
	boolean isDone = true;
	Good goodinf;
	static String username;

	public void menu(int choose, HttpServletRequest request,
			HttpServletResponse response, PrintWriter out) throws SQLException, IOException {
		while (true) {
			isDone = true;
			switch (choose) {
			case 1:
				log(request, response, out);
				break;
			case 2:
				reg(request, response, out);			
				break;
			default:
				break;
			}
		}
	}

/*	public void adminMenu(Scanner sc) throws SQLException {
		while (isDone) {
			System.out
					.println("��ѡ�� 1.����ˮ�� 2.�鿴�û�����  3.�޸�ˮ����Ϣ 4.ˮ������ 5.�˳���¼ ");
			int choose = sc.nextInt();
			switch (choose) {
			case 1:
				add(sc);
				break;
			case 2:
				checkOrder();
				break;
			case 3:
				changeFruit(sc);

				break;
			case 4:
				sort(sc);
				break;
			case 5:
				isDone = false;
				break;
			default:
				break;
			}

		}

	}
	*/

	private void sort(Scanner sc) throws SQLException {
		System.out.println("��ѡ��1. ����ˮ������������ 2. ����ˮ��۸�����");
		int i = sc.nextInt();
		List<Good> manyGoods = manager.doSort(i);
		for (Good g : manyGoods) {
			System.out.println(g);
		}
	}

	private void changeFruit(Scanner sc) throws SQLException {
		System.out.println("������ˮ������");
		String goodsname = sc.next();
		System.out.println("�������޸ĺ�ĵ���");
		double goodsprice = sc.nextDouble();
		System.out.println("�������޸ĺ�Ŀ��");
		int count = sc.nextInt();
		if (manager.doChangeFruit(goodsname, goodsprice, count)) {
			System.out.println("�޸ĳɹ�~");
			return;
		}
		System.out.println("�޸�ʧ��~");
	}

	// ����Ա�鿴����
	private void checkOrder() throws SQLException {
		List<Lists> l = manager.docheckOrder();
		System.out.println(l);

	}

	// ����Ա��������
	private void add(Scanner sc) throws SQLException {
		System.out.println("������ˮ�����");
		String goodsname = sc.next();
		System.out.println("������ˮ��۸�");
		double goodsprice = sc.nextDouble();
		System.out.println("��������");
		int count = sc.nextInt();
		int i = manager.doAdd(goodsname, goodsprice, count);
		if (i == 1) {
			System.out.println("ˮ������ظ�,����������");
		}
		if (i == 2) {
			System.out.println("�ɹ����~");
		}
		if (i == 3) {
			System.out.println("���ʧ��~");
		}

	}

/*	public void subMenu(Scanner sc) throws SQLException {
		while (isDone) {			
			switch (choose) {
			case 1:
				checkFruit(sc);
				break;
			case 2:
				buyFruit(sc);
				break;
			case 3:
				saveMoney(sc);
				break;
			case 4:
				viewOrder();
				break;
			case 5:
				deleteOrder(sc);
				break;
			case 6:
				isDone = false;
				break;
			default:
				break;
			}

		}

	}
*/
	// ȡ��
	private void deleteOrder(Scanner sc) throws SQLException {
		System.out.println("������Ҫȡ��Ķ�����");
		int orderid = sc.nextInt();
		int i = manager.doDeleteOrder(orderid);
		if (i == 0) {
			System.out.println("�ɹ�ȡ��~");
		} else if (i == 1) {
			System.out.println("ȡ��ʧ��~");
		} else {
			System.out.println("û�иö���~");
		}
	}

	// �鿴�Լ��Ķ���
	private void viewOrder() throws SQLException {
		List<Orders> manyOrders = manager.doViewOrder();
		for (Orders o : manyOrders) {
			System.out.println(o);
		}

	}

	// ��ֵ
	private void saveMoney(Scanner sc) throws SQLException {
		System.out.println("�������ֵ���");
		double money = sc.nextDouble();
		if (manager.doSaveMoney(money)) {
			System.out.println("�ɹ���ֵ~");
		} else {
			System.out.println("��ֵʧ��~");
		}

	}

	// 注册
	public void reg(HttpServletRequest request, HttpServletResponse response,
			PrintWriter out) throws SQLException, IOException {
		String username = request.getParameter("name");
		String password = request.getParameter("pwd");
		if (!manager.check(username)) {
			if (manager.doReg(username, password)) {
				out.print("注册成功" + manager.mes);//
				response.sendRedirect("Log.html");

			} else {
				out.print("注册失败");
				response.sendRedirect("Log.html");
			}

		} else {
			out.print("用户名已存在！");
			response.sendRedirect("Log.html");
		}
	}

	// 验证登录
	public void log(HttpServletRequest request, HttpServletResponse response,
			PrintWriter out) throws SQLException, IOException {
		String username = request.getParameter("name");
		String password = request.getParameter("pwd");
		System.out.println("21333333333333333");
		if ("admin".equals(username) && "123456".equals(password)) {
			out.print("管理员登录成功");
			response.sendRedirect("AdminMenu.html");
			System.out.println("21666666666666666666666");
		}
		if (manager.doLog(username, password)) {
			out.print("用户登录成功");
			response.sendRedirect("Customer.html");
			
			System.out.println("2222222222222222");
		} else {
			System.out.println("用户名或者密码错误");
			response.sendRedirect("Log.html");
			System.out.println("222666666666666666");

		}

	}

	// ��ѯˮ����Ϣ
	/*public void checkFruit(HttpServletRequest request,
			HttpServletResponse response, PrintWriter out) throws SQLException {
		System.out.println("������ˮ������");
		String goodsname = sc.next();
		List<Good> manygoods = manager.queryGoods(goodsname);

		if (manygoods != null) {
			for (Good g : manygoods) {
				System.out.println("��Ĳ�ѯ���");
				System.out.println(g);
			}
		} else {
			System.out.println("û�и���Ʒ~");
		}

	}
	*/

	// ����ˮ��
	public void buyFruit(Scanner sc) throws SQLException {
		System.out.println("������ˮ����");
		String goodsname = sc.next();
		System.out.println("�����빺������");
		int count = sc.nextInt();
		int isSuccess = manager.buyGoods(goodsname, username, count);// �ж�����ʧ����

		if (isSuccess == 0) {
			System.out.println("�ɹ�����~");

		} else if (isSuccess == 1) {
			System.out.println("��治�㣬������ѡ��");

		} else if (isSuccess == 2) {
			System.out.println("�������~");
		} else if (isSuccess == 4) {
			System.out.println("û�и�ˮ��~");

		} else {
			System.out.println("����ʧ��~");
		}

	}
}
