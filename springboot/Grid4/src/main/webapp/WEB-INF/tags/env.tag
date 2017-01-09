<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<spring:eval expression="@environment.getProperty('path.ui-theme')" var="theme" scope="session"/>
<spring:eval expression="@environment.getProperty('path.font-awesome')" var="fonts" scope="session"/>
<spring:eval expression="@environment.getProperty('path.ionicons')" var="icons" scope="session"/>
<spring:eval expression="@environment.getProperty('path.moment')" var="moment" scope="session"/>
<spring:eval expression="@environment.getProperty('path.raphael')" var="raphael" scope="session"/>