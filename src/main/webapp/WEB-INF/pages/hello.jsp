<%@include file="libraryHeader.jsp" %>
<c:set var="pageTitle" scope="request" value="Football League Manager"/>
<%@include file="header.jsp" %>

<div id="players">
    <h1>Players</h1>
    <%@include file="playerList.jsp" %>
    <c:set var="personType" scope="request" value="player"/>
    <%@include file="uploadField.jsp" %>
</div>

<div id="coaches">
    <h1>Coaches</h1>
    <%@include file="coachList.jsp" %>
    <c:set var="personType" scope="request" value="coach"/>
    <%@include file="uploadField.jsp" %>
</div>

<%@include file="footer.jsp" %>
