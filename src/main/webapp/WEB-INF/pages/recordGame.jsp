<%@include file="partials/libraryHeader.jsp" %>
<c:set var="pageTitle" scope="request" value="Football League Manager"/>
<%@include file="partials/header.jsp" %>

<h1>Record Game Results</h1>

<select id="team1-dropdown">
    <%@include file="partials/teamOptionList.jsp" %>
</select>

<select id="team2-dropdown">
    <%@include file="partials/teamOptionList.jsp" %>
</select>

<%@include file="partials/footer.jsp" %>
