package com.kunkun.web.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import com.kunkun.commons.Page;
import com.kunkun.domain.Book;
import com.kunkun.domain.Catagory;
import com.kunkun.domain.Order;
import com.kunkun.service.BusinessService;
import com.kunkun.service.impl.BusinessServiceImpl;
import com.kunkun.util.FillBeanUtil;

public class ControlServlet extends HttpServlet {

	private BusinessService s = new BusinessServiceImpl();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String op = request.getParameter("op");
		if ("addCatagory".equals(op)) {
			addCatagory(request, response);
		} else if ("showAllCategories".equals(op)) {
			showAllCategories(request, response);
		} else if ("showAddBookUI".equals(op)) {
			showAddBookUI(request, response);
		} else if ("addBook".equals(op)) {
			try {
				addBook(request, response);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		} else if ("showAllBooks".equals(op)) {
			showAllBooks(request, response);
		}else if ("showNeedOperatorOrder".equals(op)) {
			showOrders(request, response);
		}
	}

	private void showOrders(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<Order> list = new ArrayList<Order>();
		list = s.findOrders();
		request.setAttribute("needOperator", list);
		request.getRequestDispatcher("/manage/order.jsp").forward(request, response);
	}

	private void showAllBooks(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String num = request.getParameter("num");
		Page page = s.findAllBookPageRecords(num);
		request.setAttribute("page", page);
		request.getRequestDispatcher("/manage/listBooks.jsp").forward(request, response);
	}

	private void addBook(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (!isMultipart) {
			request.setAttribute("msg", "表单有误，请您核对");
			request.getRequestDispatcher("/manage/message.jsp").forward(
					request, response);
		}
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload sfu = new ServletFileUpload(factory);
		List<FileItem> items = sfu.parseRequest(request);
		Book book = new Book();
		for (FileItem item : items) {
			// 封装基本数据类型到JavaBean中
			if (item.isFormField()) {
				String fieldName = item.getFieldName();
				String fieldValue = item.getString(request
						.getCharacterEncoding());
				BeanUtils.setProperty(book, fieldName, fieldValue);
			} else {
				// 文件上传（图片）
				String fileName = item.getName();
				if (fileName != null && !fileName.trim().equals("")) {
					// 该UUID的文件名
					fileName = UUID.randomUUID().toString() + "."
							+ FilenameUtils.getExtension(fileName);
					// 计算存储文件的地址
					String storeDirectory = getServletContext().getRealPath(
							"/images");
					String path = makeDirs(storeDirectory, fileName);// /dir1/dir2

					book.setPath(path);
					book.setPhotoFileName(fileName);

					item.write(new File(storeDirectory + path + "/" + fileName));
				}
			}
			// 保存信息到数据库
			s.addBook(book);
		}
	}

	private String makeDirs(String storeDirectory, String fileName) {
		int hashCode = fileName.hashCode();
		int dir1 = hashCode & 0xf;
		int dir2 = (hashCode & 0xf0) >> 4;

		String newPath = "/" + dir1 + "/" + dir2;
		File file = new File(storeDirectory, newPath);
		if (!file.exists()) {
			file.mkdirs();
		}
		return newPath;
	}

	// 添加书籍的界面
	private void showAddBookUI(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<Catagory> cs = s.findAllCatagories();
		request.setAttribute("cs", cs);
		request.getRequestDispatcher("/manage/addBook.jsp").forward(request,
				response);
	}

	private void showAllCategories(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<Catagory> cs = s.findAllCatagories();
		request.setAttribute("cs", cs);
		request.getRequestDispatcher("/manage/listCategory.jsp").forward(
				request, response);
	}

	private void addCatagory(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Catagory catagory = FillBeanUtil.fillBean(request, Catagory.class);
		s.addCatagory(catagory);
		request.setAttribute("msg", "保存成功！");
		request.getRequestDispatcher("/Manager/message.jsp").forward(request,
				response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);

	}

}
