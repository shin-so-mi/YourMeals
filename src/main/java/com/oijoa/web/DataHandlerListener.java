package com.oijoa.web;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import com.oijoa.dao.BoardDao;
import com.oijoa.dao.OrderDao;
import com.oijoa.dao.OrderListDao;
import com.oijoa.dao.PaymentDao;
import com.oijoa.dao.ProductDao;
import com.oijoa.dao.QnADao;
import com.oijoa.dao.RecipeDao;
import com.oijoa.dao.mariadb.BoardDaoImpl;
import com.oijoa.dao.mariadb.OrderDaoImpl;
import com.oijoa.dao.mariadb.OrderListDaoImpl;
import com.oijoa.dao.mariadb.PaymentDaoImpl;
import com.oijoa.dao.mariadb.ProductDaoImpl;
import com.oijoa.dao.mariadb.QnADaoImpl;
import com.oijoa.dao.mariadb.RecipeDaoImpl;
import com.oijoa.service.BoardService;
import com.oijoa.service.DefaultBoardService;
import com.oijoa.service.DefaultOrderListService;
import com.oijoa.service.DefaultOrderService;
import com.oijoa.service.DefaultPaymentService;
import com.oijoa.service.DefaultProductService;
import com.oijoa.service.DefaultQnaService;
import com.oijoa.service.DefaultRecipeService;
import com.oijoa.service.OrderListService;
import com.oijoa.service.OrderService;
import com.oijoa.service.PaymentService;
import com.oijoa.service.ProductService;
import com.oijoa.service.QnaService;
import com.oijoa.service.RecipeService;
import com.oijoa.util.SqlSessionFactoryProxy;

@WebListener
public class DataHandlerListener implements ServletContextListener {

  @Override
  public void contextInitialized(ServletContextEvent sce) {
    // 시스템에서 사용할 객체를 준비한다.
    try {
      // Mybatis 객체 준비
      SqlSessionFactoryProxy sqlSessionFactory = new SqlSessionFactoryProxy(
          new SqlSessionFactoryBuilder().build(
              Resources.getResourceAsStream("com/oijoa/conf/mybatis-config.xml")));

      // DAO 구현체 생성
      BoardDao boardDao = new BoardDaoImpl(sqlSessionFactory);
      OrderDao orderDao = new OrderDaoImpl(sqlSessionFactory);
      OrderListDao orderListDao = new OrderListDaoImpl(sqlSessionFactory);
      ProductDao productDao = new ProductDaoImpl(sqlSessionFactory);
      RecipeDao recipeDao = new RecipeDaoImpl(sqlSessionFactory);
      QnADao qnaDao = new QnADaoImpl(sqlSessionFactory);
      PaymentDao paymentDao = new PaymentDaoImpl(sqlSessionFactory);

      // Service 구현체 생성
      BoardService boardService = new DefaultBoardService(boardDao);
      OrderService orderService = new DefaultOrderService(orderDao);
      OrderListService orderListService = new DefaultOrderListService(orderListDao);
      ProductService productService = new DefaultProductService(productDao);
      RecipeService recipeService = new DefaultRecipeService(recipeDao);
      QnaService qnaService = new DefaultQnaService(qnaDao);
      PaymentService paymentService = new DefaultPaymentService(paymentDao);

      // 다른 객체가 사용할 수 있도록 context 맵 보관소에 저장해둔다.
      ServletContext ctx = sce.getServletContext();
      ctx.setAttribute("boardService", boardService);
      ctx.setAttribute("orderService", orderService);
      ctx.setAttribute("productService", productService);
      ctx.setAttribute("recipeService", recipeService);
      ctx.setAttribute("orderListService", orderListService);
      ctx.setAttribute("qnaService", qnaService);
      ctx.setAttribute("paymentService", paymentService);

    } catch (Exception e) {
      System.out.println("Mybatis 및 DAO, 서비스 객체 준비 중 오류 발생!");
      e.printStackTrace();
    }
  }

}