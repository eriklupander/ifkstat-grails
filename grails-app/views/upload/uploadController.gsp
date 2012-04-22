<%@ page contentType="text/html;charset=ISO-8859-1" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
<meta name="layout" content="main"/>
<title>Insert title here</title>
</head>
<body>
 Upload Form: <br />
    <g:form action="upload" method="POST" enctype="multipart/form-data">
        <input type="file" name="myFile" />
        <input type="submit" />
    </g:form>
</body>
</html>