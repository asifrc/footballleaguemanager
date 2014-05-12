<c:forEach var="player" items="${playerList}" varStatus="playerCount">
    <tr id="player-${playerCount.count}">
        <%@include file="playerRowDetail.jsp" %>
        <c:if test="${ not empty teamList}">
            <td>
                <select class="team-dropdown">
                    <option> -- Trade --</option>
                    <c:forEach var="team" items="${teamList}" varStatus="teamCount">
                        <c:if test="${team ne player.team}">
                            <option>${team}</option>
                        </c:if>
                    </c:forEach>
                </select>
            </td>
        </c:if>
    </tr>
</c:forEach>