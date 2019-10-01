
	<%@ taglib uri="/struts-tags" prefix="s"%>
	<%@ taglib uri="/struts-jquery-tags" prefix="sj"%>
	 
	<h1 class="title">
		<s:text name="whatDoWeDoTitle" />
	</h1>
	<div>
		<p class="paragraph">
			&nbsp; &nbsp; &nbsp; &nbsp;<s:text name="whatDoWeDoDescription" />
		</p>	
	</div>
	<h1 class="title">
		<s:text name="waterErosionTitle" />
	</h1>
	<div>
		<p class="paragraph">
			&nbsp; &nbsp; &nbsp; &nbsp;<s:text name="waterErosionDescription" />
		</p>	
	</div>
	<div id="gallery" class="ui-corner-all" >
		<div id="thumbnailsPaneHeader">
			<s:text name="images" />
		</div>
		<div id="thumbnailsPane">
		    <ul>
		    	<s:if test="#request.presentationPhotos != null">
					<s:iterator value="#request.presentationPhotos">
				        <li>
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
				        	
							<s:url id="url" action="ErosionPresentationPhotoDownloadFileAction_getFile" namespace="/Util" escapeAmp="false">
								<s:param name="fileName" value="%{photoName}" />
								<s:param name="contentType" value="%{photoContentType}" />					 
							</s:url>
							
				            <img src='<s:property value="%{#url}" />' width="96" height="72" class="ui-corner-all" title='<s:property value="%{#photoDescription}" escapeHtml="false" />' alt='<s:text name="photoAlt" />' />
				        </li>		    	
		    		</s:iterator>		    		
		    	</s:if>
	    	</ul>
	    </div>
	</div>
	
    <div id="photoPane">
    	<img id="photoDisplay" src=""/>
    	<div id="photoDescription"></div>
    	<div id="currentPhoto"></div>
    </div>
    
	<script type="text/javascript">
		$("#details, #photoPane").hide();
		$(
			function() {
				
				
				var language = '<s:property value="%{#language}"/>';
				
				if(language == "es") {
					photosDialogTitle="Fotos de Erosión";
	  			} else if(language == "pt") {
	  				photosDialogTitle = "Fotos de Erosão";
	  			} else {
	  				photosDialogTitle = "Water Erosion Photos";
	  			}
				
				if($("#thumbnailsPane img").size() > 0) {
					$("#thumbnailsPane img").rushSlideShow(
				     {
		        		hasDialog:true,
		        		dialogTitle:photosDialogTitle,
		        		language:language
					 });
				}
			}
		);
	</script>