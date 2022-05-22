<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>CRUD TP02</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: blue">
			<div>
				<a  class="navbar-brand" style="color: white"> CRUD Produtos </a>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link"> Produtos </a></li>
			</ul>
			<ul class="navbar-nav">
                 <li><a href="<%=request.getContextPath()%>/creditosAlunos.jsp"
                     class="nav-link">Creditos da dupla</a></li>
            </ul>
		</nav>
	</header>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${user != null}">
					<form action="update" method="post">
				</c:if>
				<c:if test="${user == null}">
					<form action="insert" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${user != null}">
            			Editar Produto
            		</c:if>
						<c:if test="${user == null}">
            			Adicionar Produto
            		</c:if>
					</h2>
				</caption>

				<c:if test="${user != null}">
					<input type="hidden" name="id" value="<c:out value='${user.id}' />" />
				</c:if>

				<fieldset class="form-group">
					<label>Nome</label> <input type="text"
						value="<c:out value='${user.nome}' />" class="form-control"
						name="nome" >
				</fieldset>

				<fieldset class="form-group">
					<label>Unidade de Compra</label> <input type="text"
						value="<c:out value='${user.unidadeCompra}' />" class="form-control"
						name="unidadeCompra">
				</fieldset>

				<fieldset class="form-group">
					<label>Descrição</label> <input type="text"
						value="<c:out value='${user.descricao}' />" class="form-control"
						name="descricao">
				</fieldset>

				<fieldset class="form-group">
                     <label>Qtd Previsto por Mês</label>
                     <input type="text" value="<c:out value='${user.qtdPrevistoMes}' />" class="form-control"  name="qtdPrevistoMes">
                </fieldset>

                <fieldset class="form-group">
                        <label>Preço Maximo Comprado</label>
                        <input type="text" value="<c:out value='${user.precoMaxComprado}' />" class="form-control" name="precoMaxComprado" >
                 </fieldset>

				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
