<%@include file="partials/libraryHeader.jsp" %>
<c:set var="pageTitle" scope="request" value="Football League Manager"/>
<%@include file="partials/header.jsp" %>

<div id="players">
    <c:set var="personType" scope="request" value="player"/>
    <h1>Players</h1>
    <%@include file="partials/ageFilter.jsp" %>
    <%@include file="partials/playerList.jsp" %>
    <%@include file="partials/uploadField.jsp" %>
</div>

<div id="coaches">
    <c:set var="personType" scope="request" value="coach"/>
    <h1>Coaches</h1>
    <%@include file="partials/coachList.jsp" %>
    <%@include file="partials/uploadField.jsp" %>
</div>

<%@include file="partials/footer.jsp" %>
