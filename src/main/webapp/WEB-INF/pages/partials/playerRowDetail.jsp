<c:choose>
    <c:when test="${! hideteam}">
        <td><input name="name" form="trade-players-form" value=${player.name} hidden>${player.name}</td>
        <td><input name="team" form="trade-players-form" value=${player.team} hidden><a href="/team?name=${player.team}">${player.team}</a></td>
        <td><input name="number" form="trade-players-form" value=${player.number} hidden>${player.number}</td>
        <td><input name="age" form="trade-players-form" value=${player.age} hidden>${player.age}</td>
    </c:when>
    <c:otherwise>
        <c:choose>
            <c:when test="test=${ not empty teams}">
                <td><input name="name" form="trade-players-form" value=${player.name} hidden>${player.name}</td>
                <td><input name="team" form="trade-players-form" value=${player.team} hidden><a href="/team?name=${player.team}">${player.team}</a></td>
                <td><input name="number" form="trade-players-form" value=${player.number} hidden>${player.number}</td>
                <td><input name="age" form="trade-players-form" value=${player.age} hidden>${player.age}</td>
            </c:when>
            <c:otherwise>
                <c:if test="${hideteam}">
                    <td >${player.name}</td>
                    <td >${player.number}</td>
                    <td >${player.age}</td>
                </c:if>
            </c:otherwise>
        </c:choose>
    </c:otherwise>
</c:choose>
