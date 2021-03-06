<c:choose>
    <c:when test="${empty coaches}">
        <p id="empty-list-message">Sorry, there are no coaches.</p>
    </c:when>
    <c:otherwise>
        <table id="coach-table">
            <thead>
            <tr>
                <th>Name</th>
                <c:if test="${! hideteam}">
                    <th>Team</th>
                </c:if>
                <th>Title</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="coach" items="${coaches}" varStatus="coachLoop">
                <tr id="player-${coachLoop.count}">
                    <td>${coach.name}</td>
                    <c:if test="${! hideteam}">
                    <td>
                        <a href="/team?name=${coach.team}">${coach.team}</a>
                    </td>
                    </c:if>
                    <td>${coach.title}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:otherwise>
</c:choose>