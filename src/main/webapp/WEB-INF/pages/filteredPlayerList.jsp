<%@include file="partials/libraryHeader.jsp" %>
<c:set var="pageTitle" scope="request" value="Players Over ${minAge}"/>
<%@include file="partials/header.jsp" %>

<div id="players">
    <c:set var="personType" scope="request" value="player"/>
    <h1>Players Over ${minAge}</h1>
    <%@include file="partials/playerList.jsp" %>
</div>

<br/>
<a href="/" class="menuItem">Clear Filter</a>

<%@include file="partials/footer.jsp" %>
