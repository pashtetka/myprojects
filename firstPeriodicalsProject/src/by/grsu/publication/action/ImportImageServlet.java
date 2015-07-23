package by.grsu.publication.action;

import java.io.IOException;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class ImportImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = Logger
			.getLogger(ImportImageServlet.class);

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getQueryString();
		String connectionURL = "jdbc:mysql://localhost:3306/periodicals_project?autoReconnect=true&amp;useUnicode=true&amp;characterEncoding=UTF-8&amp;characterSetResults=utf8&amp;connectionCollation=utf8_general_ci";
		java.sql.Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con = DriverManager.getConnection(connectionURL, "root", "55555");
			Statement st1 = con.createStatement();
			ResultSet rs1 = st1
					.executeQuery("SELECT `periodicals`.`image` FROM `periodicals_project`.`periodicals` where `periodicals`.`id` = "
							+ id);
			String imgLen = "";
			if (rs1.next()) {
				imgLen = rs1.getString(1);
			}
			rs1 = st1
					.executeQuery("SELECT `periodicals`.`image` FROM `periodicals_project`.`periodicals` where `periodicals`.`id` = "
							+ id);
			if (rs1.next()) {
					int len = imgLen.length();
					byte[] rb = new byte[len];
					InputStream readImg = rs1.getBinaryStream(1);
					@SuppressWarnings("unused")
					int index = readImg.read(rb, 0, len);
					st1.close();
					response.reset();
					response.setContentType("image/jpg");
					response.getOutputStream().write(rb, 0, len);
					response.getOutputStream().flush();
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
}
