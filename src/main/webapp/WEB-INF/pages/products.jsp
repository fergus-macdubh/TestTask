<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<h3>Products</h3>
<c:choose>
    <c:when test="${products == null}">Please, choose some group.</c:when>
    <c:when test="${fn:length(products.pageList) == 0}">There is no products in this group.</c:when>
    <c:otherwise>
        <table cellSpacing="0" class="products">
            <tr>
                <th style="width: 500px" class=""><a href="/?group=${currentGroup}&sortField=name&sortDirection=${sortDirection == "asc" ? "desc" : "asc"}&page=${products.page}">Name</a></th>
                <th style="width: 100px" class=""><a href="/?group=${currentGroup}&sortField=price&sortDirection=${sortDirection == "asc" ? "desc" : "asc"}&page=${products.page}">Price</a></th>
            </tr>
            <c:forEach items="${products.pageList}" var="product">
                <tr>
                    <td>${product.name}</td>
                    <td>${product.price}</td>
                </tr>
            </c:forEach>
        </table>

        <div class="pagination">
            <c:forEach begin="0" end="${products.pageCount - 1}" var="p">
                <c:if test="${p != products.page}">
                    <a href="/?group=${currentGroup}&sortField=${sortField}&sortDirection=${sortDirection}&page=${p}">${p+1}</a>
                </c:if>
                <c:otherwise>
                    ${p+1}
                </c:otherwise>
            </c:forEach>
        </div>
    </c:otherwise>
</c:choose>