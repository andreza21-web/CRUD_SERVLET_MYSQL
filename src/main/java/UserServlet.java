import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/")
public class UserServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/newProd":
                    showNewForm(request, response);
                    break;

                case "/delete":
                    deleteProduto(request, response);
                    break;
                case "/update":
                    updateProduto(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/insert":
                    insertProduto(request, response);
                    break;
                case "/creditosAlunos":
                    creditosAlunos(request, response);
                    break;
                default:
                    listProduto(request, response);
                    break;

            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void creditosAlunos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("creditosAlunos");
        dispatcher.forward(request, response);
    }

    private void listProduto(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        List<Produto> list= ProdDao.getAllProduto();
        request.setAttribute("listUser", list);
        RequestDispatcher dispatcher = request.getRequestDispatcher("list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("form.jsp");
        dispatcher.forward(request, response);
    }


    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {

        String sid=request.getParameter("id");
        int id=Integer.parseInt(sid);

        Produto existingUser = ProdDao.getProdtoById(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("form2.jsp");
        request.setAttribute("user", existingUser);
        dispatcher.forward(request, response);

    }

    private void insertProduto(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {

        String nome = request.getParameter("nome");
        int unidadeCompra = Integer.parseInt(request.getParameter("unidadeCompra"));
        String descricao = request.getParameter("descricao");
        double qtdPrevistoMes = Double.parseDouble(request.getParameter("qtdPrevistoMes"));
        double precoMaxComprado = Double.parseDouble(request.getParameter("precoMaxComprado"));

        Produto newUser = new Produto(nome, unidadeCompra, descricao, qtdPrevistoMes, precoMaxComprado);

        ProdDao.save(newUser);
        response.sendRedirect("list");
    }

    private void updateProduto(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        String nome = request.getParameter("nome");
        int unidadeCompra = Integer.parseInt(request.getParameter("unidadeCompra"));
        String descricao = request.getParameter("descricao");
        double qtdPrevistoMes = Double.parseDouble(request.getParameter("qtdPrevistoMes"));
        double precoMaxComprado = Double.parseDouble(request.getParameter("precoMaxComprado"));

        Produto produto = new Produto(id, nome, unidadeCompra,descricao, qtdPrevistoMes,precoMaxComprado);
        ProdDao.update(produto);
        response.sendRedirect("list");
    }



    private void deleteProduto(HttpServletRequest request, HttpServletResponse response)throws SQLException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        System.out.println(id);
        ProdDao.delete(id);
        response.sendRedirect("list");

    }





}
