	<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="ISO-8859-1" %>
	
	<%@ taglib uri="/struts-tags" prefix="s"%>
	<%@ taglib uri="/struts-jquery-tags" prefix="sj"%>
	<%@ taglib uri="http://displaytag.sf.net" prefix="d" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

	<d:table name="${news}"  class="newsList" uid="row" pagesize="1" sort="list" requestURI="" export="false">
		<d:column titleKey="date" sortable="true" class="newsDateColumn">
			
			<s:if test="locale.language == 'en'">
				<s:date name="%{#attr.row.created}" format="MM/dd/yyyy hh:mm:ss a" />
			</s:if>
			<s:else>
				<s:date name="%{#attr.row.created}" format="dd/MM/yyyy hh:mm:ss a" />
			</s:else>
			
		</d:column>
		<d:column titleKey="aNews" sortable="false" class="newsColumn">
			
			<s:if test="locale.language == 'es'">
				<s:set name="newsTitle"><s:property value="%{#attr.row.titleSpanish}" escapeHtml="false" /></s:set>
			    <s:set name="newsBody"><s:property value="%{#attr.row.bodySpanish}" escapeHtml="false"/></s:set>
			    <s:set name="newsImageDescription"><s:property value="%{#attr.row.imageDescriptionSpanish}" escapeHtml="false" /></s:set>
			</s:if>
			<s:elseif test="locale.language == 'pt'">
				<s:set name="newsTitle"><s:property value="%{#attr.row.titlePortuguese}" escapeHtml="false" /></s:set>
			    <s:set name="newsBody"><s:property value="%{#attr.row.bodyPortuguese}" escapeHtml="false" /></s:set>
			    <s:set name="newsImageDescription"><s:property value="%{#attr.row.imageDescriptionPortuguese}" escapeHtml="false" /></s:set>
			</s:elseif>
			<s:else>
				<s:set name="newsTitle"><s:property value="%{#attr.row.titleEnglish}" escapeHtml="false" /></s:set>
			    <s:set name="newsBody"><s:property value="%{#attr.row.bodyEnglish}" escapeHtml="false" /></s:set>
			    <s:set name="newsImageDescription"><s:property value="%{#attr.row.imageDescriptionEnglish}" escapeHtml="false" /></s:set>
			</s:else>
			
			<div id="aNews">				 			
				 <s:if test="#attr.row.imageFileName != null && #attr.row.imageFileName != ''">
	 				<div id="newsImage" >
	 					
				        <s:url id="url" action="NewsPresentationPhotoDownloadFileAction_getFile" namespace="/Util" escapeAmp="false">
							<s:param name="fileName" value="%{#attr.row.imageFileName}" />
							<s:param name="contentType" value="%{#attr.row.imageContentType}" />					 
						</s:url>
		 				
				 		<img src='<s:property value="%{#url}" />' class="ui-corner-all" />
					 		
				 		<div id="newsDescription">
			 				<s:property value="%{#newsImageDescription}" escapeHtml="false"/>
			 			</div>
			 		</div>
		 		</s:if>
		 		<s:else>
		 			&nbsp;
		 		</s:else>
					
	 		 	<div id="newsContent">
		 		 		
	 		 		<h1 id="newsTitle">
						<s:property value="%{#newsTitle}" escapeHtml="false"/>
					</h1>
					<div id="newsBody">
						<s:property value="%{#newsBody}" escapeHtml="false" />
	   				</div>
		   		</div>
			 </div>
	 	</d:column>
	</d:table>
	
	<script type="text/javascript">
		var language = '<s:property value="%{locale.language}" />';

		if(language == "es") {
			namespace = "";
			actionClass = "SiguienteNoticia";
		} else if(language == "pt") {
			namespace = "";
			actionClass = "SeguinteNoticia";
		} else {
			namespace = "";
			actionClass = "NextNews";
		}
							
		var downloadFileAction = "NewsPresentationPhotoDownloadFileAction_getFile";
		
		$(".pageButton").unbind("click");
		
		if(downloadFileAction != null && downloadFileAction != '') {
			downloadFileAction = "&downloadFileAction=" + downloadFileAction;
		} else {
			downloadFileAction = "";
		}

		$("#first").button({icons:{primary:"ui-icon-seek-first"}})
			.click(function(event) {
				$.get(
					namespace + "/" + actionClass + $("#first").attr("data") + downloadFileAction, 	
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
					namespace + "/" + actionClass + $("#next").attr("data")  + downloadFileAction, 	
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
					namespace + "/" + actionClass + $("#previous").attr("data") + downloadFileAction, 	
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
					namespace + "/" + actionClass + $("#last").attr("data") + downloadFileAction, 	
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
						namespace + "/" + actionClass + $(this).attr("data")  + downloadFileAction, 	
						function(response) {
							$("#result").empty().html(response);				
						},
						"html"
					);				
			}
		);
	</script>