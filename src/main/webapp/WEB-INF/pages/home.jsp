<%@include file="libraryHeader.jsp" %>
<c:set var="pageTitle" scope="request" value="Football League Manager"/>
<%@include file="header.jsp" %>

<div id="players">
    <c:set var="personType" scope="request" value="player"/>
    <h1>Players</h1>
    <%@include file="ageFilter.jsp" %>
    <%@include file="playerList.jsp" %>
    <%@include file="uploadField.jsp" %>
</div>

<div id="coaches">
    <c:set var="personType" scope="request" value="coach"/>
    <h1>Coaches</h1>
    <%@include file="coachList.jsp" %>
    <%@include file="uploadField.jsp" %>
</div>

<%@include file="footer.jsp" %>
