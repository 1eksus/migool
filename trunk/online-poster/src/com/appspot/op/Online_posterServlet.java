package com.appspot.op;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.*;

@SuppressWarnings("serial")
public class Online_posterServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html");

		PrintWriter out = resp.getWriter();

		out.write("<center>");
		out.write("���������:<br><input type='text' name='title' size='87'><br>");

		out.write("URL:<br><input type='text' name='url' size='87'><br>");

		out.write("��������:<br><input type='file' name='image' size='87'><br>");

		out.write("������ �������:<br><textarea name='begStory' cols=65 rows=12></textarea><br>");

		out.write("����� �������:<br><textarea name='endStory' cols=65 rows=12></textarea><br>");

		out.write("��������:<br><input type='text' name='name' size='87'><br>");

		out.write("������������ ��������:<br><input type='text' name='originalName' size='87'><br>");

		out.write("������:<br><input type='text' name='version' size='87'><br>");

		out.write("���:<br><input type='text' name='year' size='87'><br>");

		out.write("������:<br><input type='text' name='format' size='87'><br>");

		out.write("����:<br><input type='text' name='language' size='87'><br>");

		out.write("������:<br><input type='text' name='size' size='87'><br>");

		// TODO public List<Image> screens;
		// out.write("������:<br><input type='text' name='size' size='87'><br>");

		// TODO
		// public List<List<FileLink>> fileLinks;
		// out.write("������:<br><input type='text' name='size' size='87'><br>");

		out.write("����:<br><input type='text' name='tags' size='87'><br>");

		// Warez:

		out.write("�����������:<br><input type='text' name='developer' size='87'><br>");

		out.write("������������ �������:<br><input type='text' name='os' size='87'><br>");

		out.write("��������� (32/64):<br><input type='text' name='platform' size='87'><br>");

		out.write("<input type='checkbox' name='free'>����������<br>");

		out.write("<input type='checkbox' name='crack' value='1' checked>� ����������<br>");

		out.write("</center>");
	}
}
