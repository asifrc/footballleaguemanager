<c:choose>
    <c:when test="${empty playerList}">
        <p id="empty-list-message">Sorry, there are no players.</p>
    </c:when>
    <c:otherwise>
        <table id="player-table">
            <thead>
            <tr>
                <th>Name</th>
                <th>Team</th>
                <th>Number</th>
                <th>Age</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="player" items="${playerList}" varStatus="playerCount">
                <tr id="player-${playerCount.count}">
                    <td>${player.getName()}</td>
                    <td>${player.getTeam()}</td>
                    <td>${player.getNumber()}</td>
                    <td>${player.getAge()}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:otherwise>
</c:choose>