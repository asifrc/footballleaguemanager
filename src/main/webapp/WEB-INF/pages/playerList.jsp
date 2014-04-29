<ul id="player-list">
    <c:forEach var="player" items="${playerList}" varStatus="playerCount">
        <li id="player-${playerCount.count}">
            ${player.getName()}
        </li>
    </c:forEach>
</ul>

