<div id="player-filter-options">
    <form method="GET" action="/filterPlayers">
        <select id="age-filter-dropdown" name="minimum-age">
            <option value="0">Please select a minimum age</option>
            <c:forEach begin="15" end="30" varStatus="loop">
                <option value="${loop.index}">${loop.index}</option>
            </c:forEach>
        </select>
        <input id="player-filter-button" value="Filter Players" type="submit" disabled="true"/>
    </form>
</div>
