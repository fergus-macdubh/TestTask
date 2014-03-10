<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h3>Groups</h3>
<c:forEach items="${groups}" var="group">
    <a href="<c:url value="/?group=${group.id}"/>" class="inline-link-3" style="display: block">
        ${group.name} (${group.productCount})
    </a>
</c:forEach>
