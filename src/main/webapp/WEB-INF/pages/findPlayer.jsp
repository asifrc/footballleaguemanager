<%@include file="header.jsp" %>
<h1>Find Player</h1>

<form action="/find" method="get">
    <label for="name">Name:</label> <input type="text" id="name" name="name">
    <label for="number">Number:</label> <input type="text" id="number" name="number">
    <input type="submit" name="Find" value="find" id="submit">
</form>
<c:if test="${foundPlayer != null}">
    <p id="playerText">#${foundPlayer.number}&nbsp;${foundPlayer.name}</p>
</c:if>
<p id="error">${error}</p>
<%@include file="footer.jsp" %>