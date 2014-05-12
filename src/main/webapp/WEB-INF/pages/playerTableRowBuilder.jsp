<c:forEach var="player" items="${playerList}" varStatus="playerCount">
    <tr id="player-${playerCount.count}">
        <%@include file="playerRowDetail.jsp" %>
        <c:if test="${ not empty teamList}">
            <td><select class="team-dropdown">
                <c:forEach var="team" items="${teamList}" varStatus="teamCount">
                    <option>${team}</option>
                </c:forEach>
            </select></td>
        </c:if>
    </tr>
</c:forEach>