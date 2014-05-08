<%@include file="libraryHeader.jsp" %>
<c:set var="pageTitle" scope="request" value="Find Player"/>
<%@include file="header.jsp" %>

<h1>Find Player</h1>

<form action="/find" method="get">
    <label for="name">Name:</label> <input type="text" id="name" name="name">
    <label for="number">Number:</label> <input type="text" id="number" name="number" maxlength="3">
    <input type="submit" name="Find" value="find" id="submit">
</form>
<c:if test="${playerFound == true}">
    <table id="player-table">
        <thead>
            <%@include file="playerTableHeaderBuilder.jsp" %>
        </thead>
        <tbody>
            <%@include file="playerRowDetail.jsp" %>
        </tbody>
    </table>
</c:if>
<p id="error">${error}</p>
<%@include file="footer.jsp" %>