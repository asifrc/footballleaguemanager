<c:choose>
    <c:when test="${empty players}">
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
    </c:otherwise>
</c:choose>