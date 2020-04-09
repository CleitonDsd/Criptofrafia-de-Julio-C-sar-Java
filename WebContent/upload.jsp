<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br" dir="ltr">
<head>
<meta charset="UTF-8">
<title>Upload do Arquivo jSON</title>
</head>
<body>

	<form method="POST" enctype="multipart/form-data" action="fazUpload">
		<label> Selecionar o arquivo:</label> <input type="file" name="answer">

		<input type="submit" value="Enviar"> Upload do Arquivo!
	</form>

</body>
</html>