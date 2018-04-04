<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

나와랏

<style>
    hr.hr-style {
        border: 0;
        border-bottom: 1px dashed #ccc;
        background: #999;
    }
</style>


<div style="margin:15px">

    <c:forEach items="${replyDTOList2}" var="reply">

        <!-- 댓글 하나 -->

        <div>

            <form>

            <hr class="hr-style">

            <!-- 아이디 / 날짜 -->
            <div>
                <div onclick="replyOfReply()" id="pNo" value="${reply.parents_no}">
                    <h4><b> ${reply.writer} </b></h4> ${reply.parents_no}
                </div>
                <div style="float:right; color:#adb7bd;"> ${reply.write_date} </div>
            </div>

            <!-- 내용 -->
            <div>
                <input type="hidden">
                    ${reply.content}
            </div>

            <!-- 내 댓글이라면 수정, 삭제 가능하게 함 (세션의 memberNo와 댓글의 writerNo비교) -->
            <c:if test="${reply.writer_no eq memberNo}">

                <!-- 수정 -->
                <div style="float:right; margin-left:15px">
                    수정
                </div>

                <!-- 삭제 -->
                <div style="float:right;">
                    삭제
                </div>

            </c:if>

            </form>
        </div>


    </c:forEach>

    <hr class="hr-style">

    <!-- 입력칸 -->
    <div>

        <form name="replyForm" action="/board/replyInsert.do" method="post">

            <!-- 나중에 지울거. 테스트용 -->
            <input type="text" name="board_no" value="일단은url에있는param값을치셈${boardNum}">

            <input type="text" id="content" name="content" size="85%">

            <input type="text" name="writer" value="${memberNo}">

            <input type="hidden" name="write_date" value="${write_date}">

            <!-- 나중에 지울거 -->
            <input type="hidden" name="parents_no" value="0">

            <button class="btn btn-info" onclick="submit()">등록</button>

        </form>

    </div>

</div>

<script src="/resources/js/jquery-3.2.1.min.js"></script>
<script src="/resources/js/bootstrap/bootstrap.min.js"></script>
<script src="/resources/js/app.min.js"></script>
<script src="/resources/summernote/summernote.js"></script>
<script src="/resources/js/stringBuffer.js"></script>
<script>

    function replyOfReply() {

      alert(document.getElementById('pNo').innerHTML);

      $('#content').focus();
      $('#content').innerHTML = '답글임';

    }

</script>