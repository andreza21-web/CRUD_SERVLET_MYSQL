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
					class="nav-link">Produtos</a></li>
			</ul>

			<ul class="navbar-nav">
            	<li><a href="<%=request.getContextPath()%>/creditosAlunos.jsp"
            		class="nav-link">Creditos da dupla</a></li>
            </ul>
		</nav>
	</header>
	<br>

	<div class="row">
		<div class="container">
			<h3 class="text-center">Lista de Produtos</h3>
			<hr>
			<div class="container text-left">

				<a href="<%=request.getContextPath()%>/newProd" class="btn btn-success">Add
					New </a>
			</div>
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>ID</th>
						<th>Nome</th>
						<th>Unidade de Compra</th>
						<th>Descricao</th>
						<th>Qtd Previsto Mes</th>
						<th>Preco Max Comprado</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
					<!--   for (Todo todo: todos) {  -->
					<c:forEach var="prod" items="${listUser}">

						<tr>
							<td><c:out value="${prod.id}" /></td>
							<td><c:out value="${prod.nome}" /></td>
							<td><c:out value="${prod.unidadeCompra}" /></td>
							<td><c:out value="${prod.descricao}" /></td>
							<td><c:out value="${prod.qtdPrevistoMes}" /></td>
							<td><c:out value="${prod.precoMaxComprado}" /></td>
							<td><a href="edit?id=<c:out value='${prod.id}' />">Edit</a>
								&nbsp;&nbsp;&nbsp;&nbsp; <a
								href="delete?id=<c:out value='${prod.id}' />">Delete</a></td>
						</tr>
					</c:forEach>
					<!-- } -->
				</tbody>

			</table>
		</div>
	</div>
</body>
</html>
