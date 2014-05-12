<%@include file="partials/libraryHeader.jsp" %>
<c:set var="pageTitle" scope="request" value="Find Player"/>
<%@include file="partials/header.jsp" %>

<h1>Find Player</h1>

<form action="/find" method="GET">
    <label for="name">Name:</label> <input type="text" id="name" name="name">
    <label for="number">Number:</label> <input type="text" id="number" name="number" maxlength="3">
    <input type="submit" name="Find" value="find" id="submit">
</form>
<c:if test="${playerFound == true}">
    <table id="player-table">
        <thead>
            <%@include file="partials/playerTableHeaderBuilder.jsp" %>
        </thead>
        <tbody>
            <%@include file="partials/playerRowDetail.jsp" %>
        </tbody>
    </table>
</c:if>
<p id="error">${error}</p>

<%@include file="partials/footer.jsp" %>