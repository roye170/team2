<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page import="bean.MedicalRecordBean" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>病歷資料</title>
</head>
<body style="background-color:#fdf5e6">
<div align="center">
    <h2>病歷資料</h2>
    <table border="1">
        <tr style="background-color:#a8fefa">
            <th>病歷編號</th>
            <th>預約編號</th>
            <th>病人編號</th>
            <th>員工編號</th>
            <th>診斷結果</th>
            <th>治療方式</th>
            <th>處方</th>
            <th>紀錄日期</th>
        </tr>
        <% 
            List<bean.MedicalRecordBean> medicalRecords = (List<bean.MedicalRecordBean>)request.getAttribute("medicalRecords");
            if (medicalRecords != null) {
                for(MedicalRecordBean record : medicalRecords) { 
        %>
            <tr>
                <td><%= record.getMrId() %></td>
                <td><%= record.getAppId() %></td>
                <td><%= record.getPtId() %></td>
                <td><%= record.getEmpId() %></td>
                <td><%= record.getRes() %></td>
                <td><%= record.getTtrc() %></td>
                <td><%= record.getPp() %></td>
                <td><%= record.getRecordDate() %></td>
            </tr>
        <% 
                }
            } else {
        %>
            <tr>
                <td colspan="8">沒有找到病歷資料</td>
            </tr>
        <% } %>
    </table>
    <h3>共 <%= medicalRecords != null ? medicalRecords.size() : 0 %> 筆病歷資料</h3>
</div>
</body>
</html>