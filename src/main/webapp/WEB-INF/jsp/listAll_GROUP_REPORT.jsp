<%@page import="tw.com.cha102.groupreport.model.GroupReportVO"%>
<%@page import="java.util.List"%>
<%@page import="tw.com.cha102.groupreport.model.GroupReportDAOImpl"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
GroupReportDAOImpl dao = new GroupReportDAOImpl();
List<GroupReportVO> list = dao.getAll(); // 此行的list變數(物件)將提供page1.file的第11行取得查詢到的總筆數，再由page1.file進行分頁的需要
pageContext.setAttribute("list", list); // 將上一行的list變數(物件)存入當前頁面pageContext，再由底下的第83行由JSTL的forEach列印出結果
%>
<html>
<head>
<meta charset="BIG5">
<title>所有檢舉資料-listAll_GROUP_REPORT</title>
<style>
table {
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
}

table, th, td {
	border: 1px solid #CCCCFF;
}

th, td {
	padding: 5px;
	text-align: center;
}
</style>
</head>
<body>
	<div style="position: relative; left: 10%;">
		<h2>揪團檢舉審核-所有檢舉資料</h2>
		<table border="1">
			<tr>
				<th>揪團檢舉 ID</th>
				<th>檢舉人 ID</th>
				<th>被檢舉人 ID</th>
				<th>揪團 ID</th>
				<th>員工 ID</th>
				<th>揪團檢舉狀態</th>
				<th>Reject Reason</th>
				<th>Actions(如果審核未通過需填寫原因)</th>
			</tr>
			<%@ include file="page1.file"%>
			<c:forEach var="groupReportVO" items="${list}"
				begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
				<tr>
					<td>${groupReportVO.groupReportId}</td>
					<td>${groupReportVO.reportMemberId}</td>
					<td>${groupReportVO.reportedMemberId}</td>
					<td>${groupReportVO.groupId}</td>
					<td>${groupReportVO.employeeId}</td>
					<td>${groupReportVO.groupReportStatus}</td>
					<td><input placeholder="輸入拒絕原因"> <span></span></td>
					<td>
						<button style="background-color: red;float:left;">審核未通過</button>
						<button style="background-color: greenyellow;float:right;">審核通過</button>
					</td>
				</tr>
				</c:forEach>
		</table>
		<%@ include file="page2.file"%>
	</div>

</body>
</html>