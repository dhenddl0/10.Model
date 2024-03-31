package com.model2.mvc.service.product.test;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.user.UserService;


/*
 *	FileName :  UserServiceTest.java
 * �� JUnit4 (Test Framework) �� Spring Framework ���� Test( Unit Test)
 * �� Spring �� JUnit 4�� ���� ���� Ŭ������ ���� ������ ��� ���� �׽�Ʈ �ڵ带 �ۼ� �� �� �ִ�.
 * �� @RunWith : Meta-data �� ���� wiring(����,DI) �� ��ü ����ü ����
 * �� @ContextConfiguration : Meta-data location ����
 * �� @Test : �׽�Ʈ ���� �ҽ� ����
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = { "classpath:config/commonservice.xml" })

@ContextConfiguration	(locations = {	"classpath:config/context-common.xml",
		"classpath:config/context-aspect.xml",
		"classpath:config/context-mybatis.xml",
		"classpath:config/context-transaction.xml" })

public class ProductServiceTest {

	//==>@RunWith,@ContextConfiguration �̿� Wiring, Test �� instance DI
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;

	//@Test
	public void testAddProduct() throws Exception {
		
		Product product = new Product();
		//product.setProdNo(1000);
		product.setProdName("����");
		product.setProdDetail("����");
		product.setManuDate("20240305");
		product.setPrice(Integer.parseInt("100"));
		product.setFileName("fileName");
		
		productService.addProduct(product);
		
	

		//==> console Ȯ��
		System.out.println(product);
		
		//==> API Ȯ��
		//Assert.assertEquals("testProdNo", product.getProdNo());
		Assert.assertEquals("����", product.getProdName());
		Assert.assertEquals("����", product.getProdDetail());
		Assert.assertEquals("20240305", product.getManuDate());
		Assert.assertEquals(Integer.parseInt("100"), product.getPrice());
		//Assert.assertEquals("fileName", product.getFileName());
	}


// @Test 
 public void testGetProduct() throws Exception {
 
 Product product = new Product(); //==> �ʿ��ϴٸ�... // user.setUserId("testUserId"); //
 product.setProdName("testProdName"); // user.setPassword("testPasswd"); //
 product.setProdDetail("testProdDetail"); 
// 		+ "// user.setPhone("111-2222-3333"); //
 product.setManuDate("20240304"); // user.setEmail("test@test.com");
 product.setPrice(2000000);
// product.setFileName("fileName");
 

 
 //==> console Ȯ�� //
 System.out.println(product);
 
 //==> API Ȯ�� Assert.assertEquals("testUserId", user.getUserId());
 Assert.assertEquals("testProdName", product.getProdName());
 Assert.assertEquals("testProdDetail", product.getProdDetail());
 Assert.assertEquals("20240304", product.getManuDate());
 Assert.assertEquals(2000000, product.getPrice());
 //Assert.assertEquals("fileName", product.getFileName());
 
// Assert.assertNotNull(productService.getProduct(Integer.parseInt("user02")));
// Assert.assertNotNull(productService.getProduct(Integer.parseInt("user05")));
 }




	 //@Test 
	 	
	 public void testUpdateProduct() throws Exception{
	  
	Product product = productService.getProduct(10051); 
	 Assert.assertNotNull(product);
	  
	 Assert.assertEquals("����", product.getProdName());
	 Assert.assertEquals("����", product.getProdDetail());
	 Assert.assertEquals("20240305", product.getManuDate());
	 Assert.assertEquals(100, product.getPrice());
	 //Assert.assertEquals("fileName", product.getFileName());
	  
	  product.setProdName("change"); 
	  product.setPrice(55055);
	  product.setProdDetail("changeDetail"); 
	  product.setManuDate("20220505");
	  
	  productService.updateProduct(product);
	  
	 // product = productService.getProduct(Integer.parseInt("testProdNo")); 
	 // Assert.assertNotNull(product);
	  
	  //==> console Ȯ�� //
	  System.out.println(product);
	  
	  //==> API Ȯ�� 
	  Assert.assertEquals("change", product.getProdName());
	  Assert.assertEquals("20220505", product.getManuDate());
	  Assert.assertEquals("change", product.getProdName());
	  Assert.assertEquals("changeDetail", product.getProdDetail()); }

  
 
 //@Test 
 public void testCheckDuplication() throws Exception{
 
	 Product product = new Product();
 //==> �ʿ��ϴٸ�... // User user = new User(); // user.setUserId("testUserId"); //
 product.setProdName("����"); // user.setPassword("testPasswd"); //
 product.setProdDetail("����"); 
 product.setManuDate("20240305"); // user.setEmail("test@test.com"); // //
 product.setPrice(100);
 productService.addProduct(product);
 
 
 //==> console Ȯ��
 System.out.println(productService.checkDuplication(10051));
 //System.out.println(productService.checkDuplication(Integer.parseInt("10040"+System.currentTimeMillis()) ) );
 
 //==> API Ȯ�� Assert.assertFalse( userService.checkDuplication("testUserId")
 //); 
 //Assert.assertTrue(
// productService.checkDuplication(Integer.parseInt("10040"+System.currentTimeMillis()) ) );
 
 }

 
 //==> �ּ��� Ǯ�� �����ϸ�.... 
 	
 	//@Test 
 	
 	public void testGetProductListAll() throws Exception{
 
	 Search search = new Search(); search.setCurrentPage(1);
	 search.setPageSize(3); Map<String,Object> map =
	 productService.getProductList(search);
	 
	 List<Object> list = (List<Object>)map.get("list"); 
	 Assert.assertEquals(3,list.size());
	 
	 //==> console Ȯ�� //
	 System.out.println(list);
	 
	 Integer totalCount = (Integer)map.get("totalCount");
	 System.out.println(totalCount);
	 
	 System.out.println("=======================================");
	 
	 search.setCurrentPage(1);
	 search.setPageSize(3);
	 search.setSearchCondition("0");
	 search.setSearchKeyword(""); 
	 map = productService.getProductList(search);
	 
	 list = (List<Object>)map.get("list"); Assert.assertEquals(3, list.size());
	 
	 //==> console Ȯ�� 
	 System.out.println(list);
	 
	 totalCount = (Integer)map.get("totalCount"); 
	 System.out.println(totalCount);
	 }
	
 
 
 // @Test 
  
  public void testGetProductListByProdNo() throws Exception{
  
  Search search = new Search(); 
  search.setCurrentPage(1);
  search.setPageSize(3); 
  search.setSearchCondition("0");
  search.setSearchKeyword("10005"); 
  Map<String,Object> map = productService.getProductList(search);
  
  List<Object> list = (List<Object>)map.get("list"); 
  Assert.assertEquals(1, list.size());
  
  //==> console Ȯ�� 
  System.out.println(list);
  
  Integer totalCount = (Integer)map.get("totalCount");
  System.out.println(totalCount);
  
  System.out.println("=======================================");
  
  search.setSearchCondition("0");
  search.setSearchKeyword(""+System.currentTimeMillis()); 
  map = productService.getProductList(search);
  
  list = (List<Object>)map.get("list"); 
  Assert.assertEquals(0, list.size());
  
  //==> console Ȯ�� //
  System.out.println(list);
  
  totalCount = (Integer)map.get("totalCount"); 
  System.out.println(totalCount);
  }


  
	 @Test 
	  
	  public void testGetProductListByProdName() throws Exception{
	  
	  Search search = new Search(); search.setCurrentPage(1);
	  search.setPageSize(3); search.setSearchCondition("1");
	  search.setSearchKeyword("�縻"); 
	  Map<String,Object> map = productService.getProductList(search);
	  
	  List<Object> list = (List<Object>)map.get("list"); 
	  Assert.assertEquals(3, list.size());
	  
	  //==> console Ȯ�� 
	  System.out.println(list);
	  
	  Integer totalCount = (Integer)map.get("totalCount");
	  System.out.println(totalCount);
	  
	  System.out.println("=======================================");
	  
	  search.setSearchCondition("1");
	  search.setSearchKeyword(""+System.currentTimeMillis()); 
	  map = productService.getProductList(search);
	  
	  list = (List<Object>)map.get("list");
	  Assert.assertEquals(0, list.size());
	  
	  //==> console Ȯ�� 
	  System.out.println(list);
	  
	  totalCount = (Integer)map.get("totalCount"); 
	  System.out.println(totalCount);
	  } }
	 