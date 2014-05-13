<c:forEach var="player" items="${players}" varStatus="playerLoop">
    <tr id="player-${playerLoop.count}">
        <%@include file="playerRowDetail.jsp" %>
        <c:if test="${ not empty teams}">
            <td>
                <select class="team-dropdown">
                    <option> -- Trade --</option>
                    <c:forEach var="team" items="${teams}" varStatus="teamCount">
                        <c:if test="${team ne player.team}">
                            <option>${team}</option>
                        </c:if>
                    </c:forEach>
                </select>
            </td>
        </c:if>
    </tr>
</c:forEach>