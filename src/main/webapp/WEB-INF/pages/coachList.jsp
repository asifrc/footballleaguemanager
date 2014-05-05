<c:choose>
    <c:when test="${empty coachList}">
        <p id="empty-list-message">Please upload a list of coaches.</p>
    </c:when>
    <c:otherwise>
        <table id="coach-table">
            <thead>
            <tr>
                <th>Name</th>
                <th>Team</th>
                <th>Title</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="coach" items="${coachList}" varStatus="coachCount">
                <tr id="player-${coachCount.count}">
                    <td>${coach.getName()}</td>
                    <td>${coach.getTeam()}</td>
                    <td>${coach.getTitle()}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:otherwise>
</c:choose>