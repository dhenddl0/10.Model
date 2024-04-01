package com.model2.mvc.web.product;

import java.sql.Date;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.user.UserService;


@RestController
@RequestMapping("/product/*")
public class RestProductController {
	
	@	Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;

   public RestProductController() {
      System.out.println(this.getClass());
   }
   
	
//	  @Value("#{commonProperties['pageUnit']}")
//	  //@Value("#{commonProperties['pageUnit'] ?: 3}") int pageUnit;
//	  
//	  @Value("#{commonProperties['pageSize']}")
//	  //@Value("#{commonProperties['pageSize'] ?: 2}") int pageSize;
	 

   
//   @RequestMapping(value = "addProduct", method = RequestMethod.GET)
//   public String addProduct() throws Exception{
//	   
//	   System.out.println("/addProductView");
//	   
//	   return "forward:/product/addProductView.jsp";
//   }
//   
//   @RequestMapping(value = "addProduct", method = RequestMethod.POST)
//   public String addProduct(@ModelAttribute("product") Product product, Model model, ServletRequest request) throws Exception {
//	   
//	   System.out.println("/addProduct");
//	   
//	   String[] date = request.getParameter("manuDate").split("-");
//	   
//	   product.setManuDate(date[0] + date[1] + date[2]);
//	   
//	   productService.addProduct(product);
//	   model.addAttribute("product", product);
//	   
//	   return "forward:/product/addProduct.jsp";
//   }
   
   @RequestMapping(value = "json/getProduct/{prodNo}/{menu}", method = RequestMethod.GET)
   public Product getProduct(@PathVariable int prodNo, 
		   @PathVariable String menu,
		   										Model model, ServletRequest request,
		   										HttpServletResponse response
		   										) throws Exception {
	   
	   System.out.println("/getProduct");
	   
	   Product product = productService.getProduct(prodNo);
	  
	   model.addAttribute("product", product);
	   model.addAttribute("menu", menu);
	   
	   //request.setAttribute("menu", request.getParameter("menu"));
	   
	   String history = null;
       Cookie[] cookies = ((HttpServletRequest) request).getCookies();
       if (cookies != null && cookies.length > 0) {
           for (int i = 0; i < cookies.length; i++) {
               Cookie cookie = cookies[i];
               if (cookie.getName().equals("history")) {
                   history = cookie.getValue();
               }
           }
       }
       
       // ���� ������ ��ǰ ��ȣ�� ���� ���� ��Ͽ� �߰�
       if (history == null) {
           history = Integer.toString(prodNo);
       } else {
           history += "/" + prodNo;
       }
       
       // ��Ű�� ���� ����� ����
       Cookie historyCookie = new Cookie("history", history);
       historyCookie.setPath("/");
       response.addCookie(historyCookie);

	 
	   return productService.getProduct(prodNo);
   }
//
//
//   @RequestMapping(value = "updateProduct", method = RequestMethod.GET)
//   public String updateProductView(@RequestParam("prodNo") int prodNo, Model model, @RequestParam("menu") String menu) throws Exception{
//	   
//	   System.out.println("/updateProductView");
//	   
//	   Product product = productService.getProduct(prodNo);
//	   
//	   model.addAttribute("product", product);
//	   model.addAttribute("menu", menu);
//	   
//	   return "forward:/product/updateProductView.jsp";
//	   
//   }
//   
//   @RequestMapping(value = "updateProduct", method = RequestMethod.POST)
//   public String updateProduct(@ModelAttribute("product") Product product , Model model, ServletRequest request,
//		  /* HttpSession session*/ @RequestParam("menu") String menu
//		   ) throws Exception{
//	   
//	   System.out.println("/updateProduct.do");
//	   System.out.println("menu = " +menu);
//	   
//	  // Product product = productService.getProduct(prodNo);
//	   
//	   
//	   product.setRegDate(Date.valueOf(request.getParameter("regDate2")));
//	  
//	   productService.updateProduct(product);   
//	   
//	   
//	   model.addAttribute("product", product);
//	   model.addAttribute("menu", menu);
//	   
//		/*
//		 * int sessionNo = ((Product)session.getAttribute("product")).getProdNo();
//		 * if(sessionNo == product.getProdNo()) { session.setAttribute("product",
//		 * product); }
//		 */
//	   
//	   return "forward:/product/getProduct.jsp";
//   }
//   
//   @RequestMapping(value = "listProduct")
//   public String listProduct(@ModelAttribute("search") Search search, Model model, ServletRequest request,
//		   @RequestParam("menu") String menu, int pageSize, int pageUnit
//		   ) throws Exception{
//	   
//	   //request.getAttribute("menu");
//
//	   
//	   System.out.println("/listProduct");
//	   
//	   if(search.getCurrentPage() == 0) {
//		   search.setCurrentPage(1);
//	   }
//	   search.setPageSize(pageSize);
//	   
//	   Map<String, Object> map = productService.getProductList(search);
//	   
//	   Page resultPage = new Page(search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
//	   
//	   System.out.println(resultPage);
//	     
//	   
//	   model.addAttribute("list", map.get("list"));
//	   model.addAttribute("resultPage", resultPage);
//	   model.addAttribute("search", search);
//	   model.addAttribute("menu", menu);
//	   
//	   return "forward:/product/listProduct.jsp";
//   }
   
}