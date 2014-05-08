<c:forEach var="player" items="${playerList}" varStatus="playerCount">
    <tr id="player-${playerCount.count}">
        <%@include file="playerRowDetail.jsp" %>
    </tr>
</c:forEach>