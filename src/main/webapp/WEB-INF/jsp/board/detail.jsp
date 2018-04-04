<%--
  Created by IntelliJ IDEA.
  User: myhoem
  Date: 2017-12-18
  Time: 오전 3:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>게시판 상세</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<link href="/resources/css/common.css" rel="stylesheet" type="text/css">
<style type="text/css">
#deleteBtn {
  border-width:0px;
  width:100px;
  height:25px;
  background:inherit;
  background-color:rgba(255, 0, 0, 1);
  border:none;
  border-radius:5.66667175292969px;
  -moz-box-shadow:none;
  -webkit-box-shadow:none;
  box-shadow:none;
  font-family:'AppleSDGothicNeo-Bold', 'Apple SD Gothic Neo Bold', 'Apple SD Gothic Neo';
  font-weight:700;
  font-style:normal;
  font-size:14px;
  text-align: center;
  vertical-align: middle;
  color:#FFFFFF;
  cursor: pointer;
  float: left;
  padding-top: 2px;
}
#deleteBtn:hover {
  border-width:0px;
  width:100px;
  height:25px;
  background:inherit;
  background-color:rgba(255, 51, 153, 1);
  border:none;
  border-radius:5.66667175292969px;
  -moz-box-shadow:none;
  -webkit-box-shadow:none;
  box-shadow:none;
  font-family:'AppleSDGothicNeo-Bold', 'Apple SD Gothic Neo Bold', 'Apple SD Gothic Neo';
  font-weight:700;
  font-style:normal;
  font-size:14px;
  color:#FFFFFF;
  cursor: pointer;
  float: left;
}
#btn {
  font-family:'AppleSDGothicNeo-Bold', 'Apple SD Gothic Neo Bold', 'Apple SD Gothic Neo';
  font-style:normal;
  font-size:14px;
  color: #0099FF;  
  float: right;
  padding-left: 10px;
  padding-top: 2px;
}
</style>
</head>
<body class="center-body">

	<div style="width: 900px; padding-top: 20px;">
		<div style="border-bottom: 1px solid; height: 50px;">
			<div style="float: left; width: 300px;">
				<p style="font-size: 24pt">게시판 상세</p>
			</div>
			<div style="float: right; width: 150px;">로그아웃 회원가입</div>
		</div>
		<div style="width: 780px; margin-top: 10px; margin-left: 60px">
			<div style="height: 55px; border-bottom: 1px solid #bbb;">
				<div style="float: left; padding-top: 20px; font-size: 14pt;">게시글제목</div>
				<div style="float: right; padding-top: 20px; font-size: 11pt;">작성자이름</div>
			</div>
			<!-- 첨부파일영역 -->
			<div style="height: 88px; border-bottom: 1px solid #bbb; padding-top: 5px;">
				<table style="width: 100%; font-size: 10pt;">
					<c:forEach var="i" begin="0" end="4">
						<c:choose>
							<c:when test="${i%2==0}">
								<tr height="25">
									<td>파일${i+1}</td>
							</c:when>
							<c:otherwise>
								<td>파일${i+1}</td>
							</tr>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</table>
			</div>
			<!-- 본문영역 -->
			<div style="border-bottom: 1px solid #bbb; padding-top: 20px; padding-bottom: 20px;">
							
			본문!<br>본문!<br>본문!<br>본문!<br>본문!<br>본문!<br>본문!<br>본문!<br>본문!<br>
			
			</div>
			
			<div style="height: 50px; padding-top: 10px;">
				<div style="float: left;">조회수 등록날짜</div>
				<div style="float: right;">
					<div id="deleteBtn" onclick="deleteGo();">삭제하기</div>
						<script type="text/javascript">
							function deleteGo(){
							}
						</script>
					
					<div id="btn">답글쓰기 수정하기 목록보기</div>
				</div>
			</div>
			
			<!-- 댓글영역 -->
			<div>
				<jsp:include page="/WEB-INF/jsp/board/reply.jsp" flush="false">
					<jsp:param value="${boardNum}" name="boardNum"/>
					<jsp:param value="${memberNo}" name="memberNo"/>
					<jsp:param value="${write_date}" name="write_date"/>
				</jsp:include>
				<!-- <span style="float: left">1 댓글</span> -->
			</div>
		</div>
	</div>
</body>
</html>
