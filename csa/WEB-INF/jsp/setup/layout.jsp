<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="ISO-8859-1" %> 
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="t"%>

<html>
	<head>
		<title><t:insertAttribute name="title" /></title>
		<meta http-equiv="Content-type" content="text/html;charset=UTF-8">
		<sj:head jqueryui="true" jquerytheme="south-street" loadAtOnce="true" locale="es"/>
		<s:head />
		<link rel="stylesheet" type="text/css" href='<s:url value="/css/csa.css" />' />
		<link rel="stylesheet" type="text/css" href='<s:url value="/js/fg-menu/fg.menu.css" />' media="screen"/>
		<link rel="stylesheet" type="text/css" href='<s:url value="/js/fileinput/fileinput.css" />' />
		<link rel="stylesheet" type="text/css" href='<s:url value="/js/jquery.ui.selectmenu.css" />' media="screen" />
		
		<style type="text/css">
			.ui-widget-overlay { 
				background:#459e00; 
				url(images/ui-bg_flat_0_09299f_40x100.png) 50% 50% repeat-x; 
				opacity: .30;
				filter:Alpha(Opacity=30); 
			}
			
			.ui-widget-shadow { 
				margin: -8px 0 0 -8px; 
				padding: 8px; 
				background: #459e00; 
				url(images/ui-bg_flat_0_0d5ef8_40x100.png) 50% 50% repeat-x; 
				opacity: .30;
				filter:Alpha(Opacity=30);
			}
			
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
				font-size: x-small;
				font-family: segoe ui, Arial, sans-serif; 
			}
			
			.ui-datepicker { width: 17em; padding: .2em .2em 0; font-size: x-small; }
		</style>
		
 		<script type="text/javascript" src='<s:url value="/js/jquery.form.js" />'></script>
 		<script type="text/javascript" src='<s:url value="/js/jquery-validation-1.8.1/jquery.validate.js" />'></script>
 		<script type="text/javascript" src='<s:url value="/js/jquery-validation-1.8.1/localization/messages_es.js" />'></script>
		<script type="text/javascript" src='<s:url value="/js/fg-menu/fg.menu.js" />' ></script>
		<script type="text/javascript" src='<s:url value="/js/jquery-ui-dialog-ckeditor-patch.js" />' ></script>
		<script type="text/javascript" src='<s:url value="/js/fileinput/jquery.fileinput.js" />'></script>
		<script type="text/javascript" src="https://www.google.com/jsapi?key=ABQIAAAA8ZFKjN-99XcuCoHMAwn12RRk1sbbzhS8KJ1oGrTvXJpABgDXYxRwV0v724DXsPN9gaO7Ht4xJVzaIg"></script>
		<script type="text/javascript" src='<s:url value="/js/jquery.ui.selectmenu.js" />' ></script>
		<script type="text/javascript">
			var IE7_PNG_SUFFIX = ".png";
			var contextPath = "<%=request.getContextPath()%>";
			
		    $(	
		    	function() {
		        	$(".listButton").live("click",
		        		function(event) {
		        	  		$("#ajaxLoader").html('<img src="' + contextPath + '/icons/ajax-loader.gif" />');
				    		
		        	  		$.validator.setDefaults({ 
				    		    lang: "es" 
				    		});
		          		}	  
		        	);
		          
		        	$("#currentDialog").dialog("option", "stack", false);
	  	  		}	
		    );
		
	  		$.subscribe('closeCurrentDialog', function(event, data) {
		    	$('#currentDialog').dialog('close');
		    });
		    
		    $.subscribe('beforeOpenCurrentDialog', function(event, data) {
		    	$('#currentDialog.ui-dialog-titlebar-close').bind('click', 
		    		function(event) {
		    			$('#currentDialog').dialog('close');
		    		}
		    	);
		    });
		    
		    $.subscribe('openCurrentDialog', function(event, data) {
		    	$("#ajaxLoader").empty();
		    	$('#currentDialog').dialog('open');
		    });
		    
		    $.subscribe('completeRefresh', function(event, data) {
		    	$("#ajaxLoader").empty();
		    });
		</script>
	</head>
	<body>	
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
	</body>
</html>