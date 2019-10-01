	<%@ taglib uri="/struts-tags" prefix="s"%>
	<%@ taglib uri="/struts-jquery-tags" prefix="sj"%>
	
	 
	<s:div cssClass="title">
		<s:text name="homePresentationTitle" />
	</s:div>

	<div id="presentationContainer">
		<div id="presentationContent">
			<div id="presentationThumbnailsPane">
				<s:if test="#request.presentationPhotos != null">
			    		<s:iterator value="#request.presentationPhotos">
					        	<s:set name="photoName"><s:property value='name' /></s:set>
				        		
				        		<s:if test="locale.language == 'es'">
				        			<s:set name="photoDescription"><s:property value='descriptionSpanish'/></s:set>
				        		</s:if>
				        		<s:elseif test="locale.language == 'pt'">
				        			<s:set name="photoDescription"><s:property value='descriptionPortuguese'/></s:set>
				        		</s:elseif>
				        		<s:else>
				        			<s:set name="photoDescription"><s:property value='descriptionEnglish'/></s:set>
				        		</s:else>
				        		
				        		<s:set name="photoContentType"><s:property value='contentType'/></s:set>
				        	
					        	<s:url id="url" action="HomePresentationPhotoDownloadFileAction_getFile" namespace="/Util" escapeAmp="false">
									<s:param name="fileName" value="%{photoName}" />
									<s:param name="contentType" value="%{photoContentType}" />					 
								</s:url>

								<img src='<s:property value="%{#url}" />' width="160" height="106" class="ui-corner-all" title='<s:property value="%{#photoDescription}" escapeHtml="false" />' alt='<s:text name="photoAlt" />' />
				    	</s:iterator>		    		
				</s:if>
			</div>
		    <div id="presentationPhotoPane">
		    	<img id="presentationPhotoDisplay" src=""/>
		    	<div id="photoDescription"></div>
    			<div id="currentPhoto"></div>
				<div id="rushSlideShowButtonsPane">
					<div id="playButton"></div>
					<div id="firstButton"></div>
					<div id="previousButton"></div>
					<div id="nextButton"></div>
					<div id="lastButton"></div>
					<div id="helpButton"></div>
				</div>
		    </div>
	    </div>
    </div>
    
	<script type="text/javascript">
		$("#presentationPhotoPane").hide();
		$(
			function() {						
				if($("#presentationThumbnailsPane img").size() > 0) {
					$("#presentationThumbnailsPane").show();
					
					var language = '<s:property value="%{#language}"/>';
					
		        	$("#presentationThumbnailsPane img").rushSlideShow(
		        		{
		        			photoPane:"#presentationPhotoPane",
		        			photoElement:"#presentationPhotoDisplay",
		        	 		hasDialog:false,
		        	   		imageWidth:640,
		        	   		imageHeight:426,
		        	   		language:language
		        	   	}
		        	);
		    		$("#presentationPhotoPane").show();
				} else {
					$("#presentationThumbnailsPane").hide();
				}
			}
		);
	</script>