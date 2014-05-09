<%@include file="libraryHeader.jsp" %>
<c:set var="pageTitle" scope="request" value="${teamName}"/>
<%@include file="header.jsp" %>

<h1 id="team-name">${teamName}</h1>

<div id="players">
    <h1>Players</h1>
    <%@include file="playerList.jsp" %>
</div>

<div id="coaches">
    <h1>Coaches</h1>
    <%@include file="coachList.jsp" %>
</div>

<a href="/" class="menuItem">Return Home</a>

<%@include file="footer.jsp" %>
