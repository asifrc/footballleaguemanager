<%@include file="libraryHeader.jsp" %>
<c:set var="pageTitle" scope="request" value="Football League Manager"/>
<%@include file="header.jsp" %>

<div id="players">
    <c:set var="personType" scope="request" value="player"/>
    <h1>Players</h1>
    <div id="player-filter-options">
        <form method="GET" action="/filterPlayers">
            <input type="hidden" name="minimum-age" value="18" />
            <input id="player-filter-button" value="Filter Players" type="submit" />
        </form>
    </div>
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
