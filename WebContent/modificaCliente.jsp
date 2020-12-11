<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- Referenciamos a la librería core de JSTL -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="estilos/facilito.css" rel="stylesheet" type="text/css">
</head>
<body>
	<table>
		<tr>
			<!--  Aquí va la cabecera -->
			<td colspan="2" align="center"><jsp:include page="cabecera.jsp" />
			</td>
		</tr>
		<tr>
			<!--  Aquí va el menu -->
			<td><jsp:include page="menu.jsp" /></td>
			<!--  Aquí va el cuerpo -->
			<td class="control">
				<form action="mantenimiento" method="post">
					<input type="hidden" name="operacion" value="modificaCliente" />
					<table>
						<tr class="titulo">
							<td colspan="2" align="center">
								Actualizar Cliente
							</td>
						</tr>
						<tr class="control">
							<td>Nombre</td>
							<td><input type="text" name="nombre" size="25"
								value="${requestScope.clie.nombre}">
							</td>
						</tr>

						<tr class="control">
							<td>Dirección</td>
							<td><input type="text" name="direccion" size="25"
								value="${requestScope.clie.direccion}">
							</td>
						</tr>
						<tr class="control">
							<td>Sexo</td>
							<td><c:if test="${requestScope.clie.nombre=='Rosa'}">
									<select name="sexo">
										<option value="M">Masculino</option>
										<option value="F" selected>Femenino</option>
									</select>
								</c:if> <c:if test="${requestScope.clie.nombre!='Rosa'}">
									<select name="sexo">
										<option value="M">Masculino</option>
										<option value="F">Femenino</option>
									</select>
								</c:if>
							</td>
						</tr>
						<tr class="control">
							<td>Teléfono</td>
							<td><input type="text" name="telefono" size="25"
								value="${requestScope.clie.telefono}">
							</td>
						</tr>
						<tr class="control">
							<td>Email</td>
							<td><input type="text" name="email" size="25"
								value="${requestScope.clie.email}">
							</td>
						</tr>
						<tr class="control">
							<td>Fecha Nac.</td>
							<td><input type="text" name="fecnac" size="25"
								value="${requestScope.clie.fecnac}">
							</td>
							<td>dd/MM/yyyy</td>
						</tr>
						<tr class="control">
							<td>Clave</td>
							<td><input type="text" name="clave" size="25"
								value="${requestScope.clie.clave}">
							</td>
						</tr>
						<tr class="control">
							<td colspan="2" align="right"><input type="submit"
								name="boton01" value="Modificar"></td>
						</tr>
					</table>
				</form></td>
		</tr>
		<tr>
			<!--  Aquí va el pie -->
			<td colspan="2" align="center"><jsp:include page="pie.jsp" /></td>
		</tr>
	</table>
</body>
</html>