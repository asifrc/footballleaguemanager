<c:choose>

    <c:when test="${empty playerList}">
        <p id="empty-list-message">Sorry, there are no players.</p>
    </c:when>
    <c:otherwise>

        <div class="content_items">
            <table id="player-table">
                <thead>
                    <%@ include file="playerTableHeaderBuilder.jsp" %>
                </thead>
                <tbody>
                <%@ include file="playerTableRowBuilder.jsp" %>
                </tbody>
            </table>
        </div>


        <%--<table id="player-table">--%>
            <%--<thead>--%>
            <%--<tr>--%>
                <%--<th>Name</th>--%>
                <%--<th>Team</th>--%>
                <%--<th>Number</th>--%>
                <%--<th>Age</th>--%>
            <%--</tr>--%>
            <%--</thead>--%>
            <%--<tbody>--%>
            <%--<c:forEach var="player" items="${playerList}" varStatus="playerCount">--%>
                <%--<tr id="player-${playerCount.count}">--%>
                    <%--<td>${player.getName()}</td>--%>
                    <%--<td>${player.getTeam()}</td>--%>
                    <%--<td>${player.getNumber()}</td>--%>
                    <%--<td>${player.getAge()}</td>--%>
                <%--</tr>--%>
            <%--</c:forEach>--%>
            <%--</tbody>--%>
        <%--</table>--%>
    </c:otherwise>
</c:choose>