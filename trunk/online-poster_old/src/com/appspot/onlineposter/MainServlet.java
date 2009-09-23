package com.appspot.onlineposter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.*;

import migool.util.HttpUtil;

@SuppressWarnings("serial")
public class MainServlet extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/plain");

		PrintWriter out = resp.getWriter();
		out.write(HttpUtil.toString(req.getInputStream()));
	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html");

		PrintWriter out = resp.getWriter();

		out.write("<center><form method=post onsubmit='document.forms[0].but.disabled=true'>");
		
		out.write("Заголовок:<br><input type='text' name='t' size='87'><br>");
		
		out.write("URL:<br><input type='text' name='u' size='87'><br>");
		
		out.write("Краткая новость:<br><textarea name='ss' cols=65 rows=12></textarea><br>");
		
		out.write("Продолжение:<br><textarea name='cs' cols=65 rows=20></textarea><br>");
		
		out.write("<input type=submit name=but value=Отправить>");
		out.write("</form></center>");
	}
}