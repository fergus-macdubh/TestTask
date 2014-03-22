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
                <th style="width: 500px" class=""><a
                        href="/?group=${currentGroup}&sortField=name&sortDirection=${sortDirection == "asc" ? "desc" : "asc"}&page=${products.page}">Name</a>
                </th>
                <th style="width: 100px" class=""><a
                        href="/?group=${currentGroup}&sortField=price&sortDirection=${sortDirection == "asc" ? "desc" : "asc"}&page=${products.page}">Price</a>
                </th>
            </tr>
            <c:forEach items="${products.pageList}" var="product">
                <tr>
                    <td>${product.name}</td>
                    <td>${product.price}</td>
                </tr>
            </c:forEach>
        </table>

        <div class="pagination">
            <c:if test="${products.page != 0}">
                <a
                    class="inline-link-1"
                    href="/?group=${currentGroup}&sortField=${sortField}&sortDirection=${sortDirection}&page=${products.page - 1}">&lt; Prev</a>
            </c:if>
            <c:forEach begin="0" end="${products.pageCount - 1}" var="p">
                <c:choose>
                    <c:when test="${p != products.page}">
                        <a
                                class="inline-link-1"
                                href="/?group=${currentGroup}&sortField=${sortField}&sortDirection=${sortDirection}&page=${p}">${p+1}</a>
                    </c:when>
                    <c:otherwise>
                        ${p+1}
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            <c:if test="${products.page != products.pageCount - 1}">
            <a
                    class="inline-link-1"
                    href="/?group=${currentGroup}&sortField=${sortField}&sortDirection=${sortDirection}&page=${products.page + 1}">Next &gt;</a>
            </c:if>
        </div>
    </c:otherwise>
</c:choose>