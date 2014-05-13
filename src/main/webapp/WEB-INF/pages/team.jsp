<%@include file="partials/libraryHeader.jsp" %>
<c:set var="pageTitle" scope="request" value="${teamName}"/>
<%@include file="partials/header.jsp" %>

<h1 id="team-name">${teamName}</h1>

<div id="players">
    <h1>Players</h1>
    <c:set var="hideteam" scope="request" value="true"/>
    <%@include file="partials/playerTable.jsp" %>
</div>

<div id="coaches">
    <h1>Coaches</h1>
    <c:set var="hideteam" scope="request" value="true"/>
    <%@include file="partials/coachTable.jsp" %>
</div>

<br/>
<a href="/" class="menuItem">Return Home</a>

<%@include file="partials/footer.jsp" %>
