<%@include file="partials/libraryHeader.jsp" %>
<c:set var="pageTitle" scope="request" value="${teamName}"/>
<%@include file="partials/header.jsp" %>

<h1 id="team-name">${teamName}</h1>

<div id="players">
    <h1>Players</h1>
    <%@include file="partials/playerList.jsp" %>
</div>

<div id="coaches">
    <h1>Coaches</h1>
    <%@include file="partials/coachList.jsp" %>
</div>

<a href="/" class="menuItem">Return Home</a>

<%@include file="partials/footer.jsp" %>
