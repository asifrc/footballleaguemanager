<c:forEach var="team" items="${teams}" varStatus="teamCount">
    <c:if test="${team ne player.team}">
        <option>${team}</option>
    </c:if>
</c:forEach>
