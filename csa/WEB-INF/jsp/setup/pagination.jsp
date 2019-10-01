	<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="ISO-8859-1" %>
	<%@ taglib uri="/struts-tags" prefix="s" %>

	<script type="text/javascript">
		var namespace = "/setup";
		var actionClass = '<s:property value="%{actionClass}"/>';
		var downloadFileAction = '<s:property value="%{downloadFileAction}"/>';
		
		$(".pageButton").unbind("click");
		
		if(downloadFileAction != null && downloadFileAction != '') {
			downloadFileAction = "&downloadFileAction=" + downloadFileAction;
		} else {
			downloadFileAction = "";
		}

		$("#first").button({icons:{primary:"ui-icon-seek-first"}})
			.click(function(event) {
				$.get(
					namespace + "/" + actionClass + "_list" + $("#first").attr("data") + downloadFileAction, 	
					function(response) {				
						$("#result").empty().html(response);			
					},
					"html"
				);					
			}
		);
			
		$("#next").button({
			icons:{primary:"ui-icon-seek-next"}}).
			click(function(event) {
				$.get(
					namespace + "/" + actionClass + "_list" + $("#next").attr("data")  + downloadFileAction, 	
					function(response) {
						$("#result").empty().html(response);				
					},
					"html"
				);					
			}
		);

		$("#previous").button({icons:{primary:"ui-icon-seek-prev"}})
			.click(function(event) {
				$.get(
					namespace + "/" + actionClass + "_list" + $("#previous").attr("data") + downloadFileAction, 	
					function(response) {
						$("#result").html(response);				
					},
					"html"
				);					
			}
		);

		$("#last").button({
			icons:{primary:"ui-icon-seek-end"}}).
			click(function(event) {
				$.get(
					namespace + "/" + actionClass + "_list" + $("#last").attr("data") + downloadFileAction, 	
					function(response) {
						$("#result").empty().html(response);				
					},
					"html"
				);					
			
		});
		
		
		$(".pageLinks").css({width:"100%"});
		$("[id^='goTo']").each(
			function(index, element) {
				$(this).button({label:$(this).attr("page")});	
			}
		);
		$(".pageButton").css({fontSize:"xx-small"});
		$("[data='currentPage']").button("disable");
		$("[id^='goTo']").not("[data='currentPage']").bind("click", 
			function(event) {
						
					$.get(
						namespace + "/" + actionClass + "_list" + $(this).attr("data")  + downloadFileAction, 	
						function(response) {
							$("#result").empty().html(response);				
						},
						"html"
					);				
			}
		);
	</script>