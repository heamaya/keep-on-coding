	<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="ISO-8859-1" %> 
	
	<%@ taglib uri="/struts-tags" prefix="s"%>
	<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
	<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="t"%>
	
	<html>
		<head>
			<title><t:insertAttribute name="title" /></title>
			<meta http-equiv="content-type" content="text/html; charset=UTF-8" > 
			
			<sj:head jqueryui="true" jquerytheme="south-street" loadAtOnce="true"/>
			
			<link id="mainStyle" rel="stylesheet" type="text/css" href='<s:url value="/css/csa.css" />' />
			<link rel="stylesheet" type="text/css" href='<s:url value="/js/fg-menu/fg.menu.css" />' media="screen"/>
			
			<style type="text/css">
				.ui-dialog-title {
	  				float:none !important;
	  				display: block;
	  				text-align: center;
	  				font-family: segoe ui, Arial, sans-serif;
	  				font-size: small;
				}
	
				.ui-dialog .ui-dialog-buttonpane button { 
					margin: .5em .4em .5em 0; 
					cursor: pointer;
					font-size: xx-small; 
					font-family: segoe ui, Arial, sans-serif;
				}
				
				.ui-dialog-content {
					height: 700px;
				}
				
			</style>
			
			<script type="text/javascript" src='<s:url value="/js/jquery.form.js" />' type="text/javascript"></script>
 			<script type="text/javascript" src='<s:url value="/js/jquery-validation-1.8.1/jquery.validate.js" />' type="text/javascript"></script>
 			<script type="text/javascript" src='<s:url value="/js/jquery-validation-1.8.1/localization/messages_es.js" />' type="text/javascript"></script>
			
			<script type="text/javascript" src='<s:url value="/js/fg-menu/fg.menu.js" />' ></script>
			<script type="text/javascript" src="https://www.google.com/recaptcha/api/js/recaptcha_ajax.js"></script>
	       
	        <script type="text/javascript">
				var contextPath = "<%=request.getContextPath()%>";
				
			    $(
			    	function() {
			    		$.validator.setDefaults({ 
			    		    lang: "es" 
			    		});
			    	}
			    	
			    );
        	</script> 
		</head>
		<body>
			<s:set var="language" value="%{locale.language}" scope="action"/>
			<t:insertAttribute name="actions" />
			<table class="layout">
				<tr>
					<td class="border ui-corner-all">
						&nbsp;
					</td>
					<td>
						<table class="center">
							<tr>
								<td class="header ui-corner-all" >
									<t:insertAttribute name="header" />		
								</td>
							</tr>
							<tr>
								<td class="menu">
									<t:insertAttribute name="menu" />	
								</td>
							</tr>
							<tr>
								<td class="content">
									<t:insertAttribute name="content" />	
								</td>
							</tr>
							<tr>
								<td class="footer ui-corner-all">
									<t:insertAttribute name="footer" />			
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			
			<t:insertAttribute name="loginDialog" />
		</body>
	</html>