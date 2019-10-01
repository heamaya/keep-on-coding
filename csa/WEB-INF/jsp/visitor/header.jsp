	<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="ISO-8859-1" %>
	 
	<%@ taglib uri="/struts-tags" prefix="s"%>
	
	<h1 class="mainTitle">
		<s:text name="soilAndWaterConservation"></s:text>
	</h1>
	<div class="universalMenuButton ui-corner-all">	
		<s:text name="start" /> &gt; <s:a href="%{homePresentationActionUrl}"><s:text name="presentation" /></s:a> | <s:a href="%{videosPresentationActionUrl}"><s:text name="videos" /></s:a> | <s:a href="%{newsPresentationActionUrl}"><s:text name="news" /></s:a> | <s:a href="%{loginActionUrl}"><s:text name="login" /></s:a> ||
	    <s:text name="professionals" /> &gt; <s:a href="%{whatDoWeDoPresentationActionUrl}"><s:text name="whatDoWeDo" /></s:a> | <s:a href="%{whoAreWePresentationActionUrl}"><s:text name="whoAreWe" /></s:a> | <s:a href="%{contactUsPresentationActionUrl}"><s:text name="contactUs" /></s:a> ||
	  	<s:text name="language" /> &gt; <s:a href="%{changeLocaleToSpanishActionUrl}"><s:text name="spanish" /></s:a> | <s:a href="%{changeLocaleToPortugueseActionUrl}"><s:text name="portuguese" /></s:a> | <s:a href="%{changeLocaleToEnglishActionUrl}"><s:text name="english" /></s:a> ||
		<s:text name="technologies" /> &gt; <s:a href="%{terracePresentationActionUrl}"><s:text name="terraces" /></s:a> | <s:a href="%{dikePresentationActionUrl}"><s:text name="dikes" /></s:a> | <s:a href="%{channelPresentationActionUrl}"><s:text name="channels" /></s:a> | <s:a href="%{gullyRecoveryPresentationActionUrl}"><s:text name="gullyRecovery" /></s:a> ||
		<s:text name="conservationistFarmers" />&gt; <s:a href="%{consortiumPresentationActionUrl}"><s:text name="consortia" /></s:a> | <s:a href="%{systematizationPresentationActionUrl}"><s:text name="starredWorks" /></s:a> ||
		<s:text name="help" /> &gt; <s:a href="%{aboutWebApplicationPresentationActionUrl}"><s:text name="aboutWebApplication" /></s:a>
	</div>