<td>${player.name}</td>
<c:if test="${! hideteam}">
    <td><a href="/team?name=${player.team}">${player.team}</a></td>
</c:if>
<td>${player.number}</td>
<td>${player.age}</td>