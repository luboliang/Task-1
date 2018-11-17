<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<json:object escapeXml="false">

    <json:property name="code" value="${code}"/>
    <json:property name="message">
        <spring:message code="${code}"/>
    </json:property>
    <json:property name="mxmx" value="mxmx"/>
    <json:object name="student">
        <json:property name="id" value="${data.id}"/>
        <json:property name="name" value="${data.name}"/>
        <json:property name="profession" value="${data.profession}"/>
        <json:property name="position" value="${data.position}"/>
        <json:property name="overview" value="${data.overview}"/>
        <json:property name="salary" value="${data.salary}"/>
        <json:property name="state" value="${data.state}"/>
    </json:object>
</json:object>
