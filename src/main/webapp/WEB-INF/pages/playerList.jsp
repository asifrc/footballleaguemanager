<c:choose>
    <c:when test="${empty playerList}">
        <p id="empty-list-message">Sorry, there are no players.</p>
    </c:when>
    <c:otherwise>
        <ul id="player-list">
            <c:forEach var="player" items="${playerList}" varStatus="playerCount">
                <li id="player-${playerCount.count}">
                        ${player.getName()}
                </li>
            </c:forEach>
        </ul>
    </c:otherwise>
</c:choose>