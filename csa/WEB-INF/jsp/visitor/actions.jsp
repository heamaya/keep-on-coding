	<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="ISO-8859-1" %>
	 
	<%@ taglib uri="/struts-tags" prefix="s"%>
	
	<s:url var="homePresentationActionUrl" action="%{getText('homeAction')}" namespace="%{getText('startNamespace')}" />
	<s:url var="newsPresentationActionUrl" action="%{getText('newsAction')}" namespace="%{getText('startNamespace')}" />
	<s:url var="videosPresentationActionUrl" action="%{getText('videosAction')}" namespace="%{getText('startNamespace')}" />
	<s:url var="loginActionUrl" action="%{getText('loginAction')}" namespace="%{getText('usersNamespace')}" />
	<s:url var="whatDoWeDoPresentationActionUrl" action="%{getText('whatDoWeDoAction')}" namespace="%{getText('professionalsNamespace')}" />
	<s:url var="whoAreWePresentationActionUrl" action="%{getText('whoAreWeAction')}" namespace="%{getText('professionalsNamespace')}" />
	<s:url var="contactUsPresentationActionUrl" action="%{getText('contactUsAction')}" namespace="%{getText('professionalsNamespace')}" />
	<s:url action="ChangeLocaleToSpanishAction" namespace="/Util" var="changeLocaleToSpanishActionUrl" />
	<s:url action="ChangeLocaleToPortugueseAction" namespace="/Util" var="changeLocaleToPortugueseActionUrl" />	
	<s:url action="ChangeLocaleToEnglishAction" namespace="/Util" var="changeLocaleToEnglishActionUrl" />
	<s:url var="terracePresentationActionUrl" action="%{getText('terracesAction')}" namespace="%{getText('technologiesNamespace')}" />
	<s:url var="dikePresentationActionUrl" action="%{getText('dikesAction')}" namespace="%{getText('technologiesNamespace')}" />
	<s:url var="channelPresentationActionUrl" action="%{getText('channelsAction')}" namespace="%{getText('technologiesNamespace')}" />
	<s:url var="gullyRecoveryPresentationActionUrl" action="%{getText('gullyRecoveryAction')}" namespace="%{getText('technologiesNamespace')}" />
	<s:url var="consortiumPresentationActionUrl" action="%{getText('consortiaAction')}" namespace="%{getText('farmersNamespace')}" />
	<s:url var="systematizationPresentationActionUrl" action="%{getText('systematizationAction')}" namespace="%{getText('farmersNamespace')}" />
	<s:url var="aboutWebApplicationPresentationActionUrl" action="%{getText('aboutWebApplicationAction')}" namespace="%{getText('helpNamespace')}" />