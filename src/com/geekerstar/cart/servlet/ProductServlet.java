package com.geekerstar.cart.servlet;

import com.geekerstar.cart.data.LocalCache;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: Geekerstar(jikewenku.com)
 * @Date: 2018/7/12 16:19
 * @Description:商品（课程）控制器
 */
public class ProductServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.setAttribute("products", LocalCache.getProducts());
//        req.getRequestDispatcher("/WEB-INF/views/biz/list.jsp").forward(req, resp);

        String name = req.getParameter("title");

        String pageStr = req.getParameter("page");
        int page = 1;
        if (null != pageStr && !"".equals(pageStr)) {
            page = Integer.parseInt(pageStr);
        }

        int totalProducts = LocalCache.getProductsCount(name);
        int totalPage = totalProducts % 12 > 0 ? totalProducts / 12 + 1 : totalProducts / 12;

        req.setAttribute("curPage", page);
        req.setAttribute("prePage", page > 1 ? page - 1 : 1);
        req.setAttribute("nextPage", totalPage > page ? page + 1 : totalPage);
        req.setAttribute("totalPage", totalPage);
        req.setAttribute("title", name);


        req.setAttribute("products", LocalCache.getProducts(page, 12, name));
        req.getRequestDispatcher("/WEB-INF/views/biz/list.jsp").forward(req, resp);
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
