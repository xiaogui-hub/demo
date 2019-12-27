package com.jswl.portal.druid;

import javax.servlet.annotation.WebServlet;
import com.alibaba.druid.support.http.StatViewServlet;

@WebServlet(urlPatterns = "/druid/*")
public class DruidServlet extends StatViewServlet{

}