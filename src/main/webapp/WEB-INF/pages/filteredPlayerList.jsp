<%@include file="libraryHeader.jsp" %>
<c:set var="pageTitle" scope="request" value="Players Over ${minAge}"/>
<%@include file="header.jsp" %>

<div id="players">
    <c:set var="personType" scope="request" value="player"/>
    <h1>Players Over ${minAge}</h1>
    <%@include file="playerList.jsp" %>
</div>

<a href="/" class="menuItem">Clear Filter</a>

<%@include file="footer.jsp" %>
