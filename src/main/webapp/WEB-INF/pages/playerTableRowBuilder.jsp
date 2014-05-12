<c:forEach var="player" items="${playerList}" varStatus="playerCount">
    <tr id="player-${playerCount.count}">
        <%@include file="playerRowDetail.jsp" %>
        <c:if test="false">
            <td></td>
        </c:if>
    </tr>
</c:forEach>