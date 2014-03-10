<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link href="<c:url value="/css/dataart.css"/>" rel="stylesheet">
</head>
<body>
<div class="main">
    <div class="leftColumn">
        <jsp:include page="groups.jsp"/>
    </div>
    <div class="rightColumn drop-shadow">
        <jsp:include page="products.jsp"/>
    </div>
</div>
</body>
</html>