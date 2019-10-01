	<%@ taglib uri="/struts-tags" prefix="s"%>
	<%@ taglib uri="/struts-jquery-tags" prefix="sj"%>
	
						 
	<s:div cssClass="title">
		<s:text name="videoPresentationTitle" />
	</s:div>
								
	<s:div id="presentationContainer">
		<s:div id="presentationContent">
			<s:div id="presentationThumbnailsPane">
				<s:if test="#request.videos != null">
		    		<s:iterator value="#request.videos" status="status">
			        	<s:set name="currentVideoKey"><s:property value='videoKey' /></s:set>
				        <s:set name="currentVideoName"><s:property value='name' /></s:set>
				        		
				        <s:if test="locale.language == 'es'">
				        	<s:set name="currentVideoName"><s:property value='nameSpanish'/></s:set>
				        	<s:set name="currentVideoDescription"><s:property value='descriptionSpanish'/></s:set>
				        </s:if>
				        <s:elseif test="locale.language == 'pt'">
				        	<s:set name="currentVideoName"><s:property value='namePortuguese'/></s:set>
				        	<s:set name="currentVideoDescription"><s:property value='descriptionPortuguese'/></s:set>
				        </s:elseif>
				        <s:else>
				        	<s:set name="currentVideoName"><s:property value='nameEnglish'/></s:set>
				        	<s:set name="currentVideoDescription"><s:property value='descriptionEnglish'/></s:set>
				        </s:else>
				        	
				        <s:set name="currentVideoThumbnail">http://img.youtube.com/vi/<s:property value='videoKey' />/hqdefault.jpg</s:set>
										        	
						<img id='<s:property value="%{#currentVideoKey}" />' src='<s:property value="%{#currentVideoThumbnail}" />' width="160" height="106" class="ui-corner-all" title="<s:property value="%{#currentVideoName}" escapeHtml="false" />" alt="<s:property value="%{#currentVideoDescription}" escapeHtml="false" />" />
			    	</s:iterator>		    		
				</s:if>
			</s:div>
			
		    <s:div id="videoPane">				
				<s:div id="videoDiv"></s:div>
				<s:div id="videoBar"><s:div id="videoTime"></s:div></s:div>
				<s:div id="errorConsole"></s:div>	
			    <s:div id="videoDescription"></s:div>
		   		<s:div id="currentVideo"></s:div>
		    </s:div>
	    </s:div>
	</s:div>	
	
	<script type="text/javascript">
		$(
			
			function() {				
				var language = '<s:property value="%{#language}"/>';

				if($("#presentationThumbnailsPane img").size() > 0) {
					

					if($.browser.msie && ($.browser.version).substring(0, 1) == "6") {
						
						if(language == "es") {
							ie6VideosMessage = "Los videos de YouTube no funcionan en Internet Explorer 6, te recomendamos que actualices tu versión de Internet Explorer o que uses otro navegador como Firefox, Chrome u Opera.";
			  			} else if(language == "pt") {
							ie6VideosMessage = "Os vídeos do YouTube não funcionam no Internet Explorer 6, recomendamos que atualize a sua versão do Internet Explorer ou que use outros navegadores como Firefox, Chrome ou Opera.";
			  			} else {
							ie6VideosMessage = "YouTube videos do not work in Internet Explorer 6, we recommend you to upgrade your Internet Explorer Version or to use another Browser like Firefox, Chrome or Opera";
			  			}
						
						$("#videoDiv").addClass("ui-widget").html(					
							'<div class="ui-state-highlight ui-corner-all" style="margin-top: 20px; padding: 0 .7em;">' +
						  		'<p class="paragraph"><span class="ui-icon ui-icon-info" style="float: left; margin-right: .3em;"></span>' +
									'<strong>' + ie6VideosMessage + '</strong></p>' +
		  					'</div>'
						);
					} else {
						loadPlayer();
						
						$("#presentationThumbnailsPane img").rushYouTubeVideoShow(
				    	{
			    			zIndex: 1,
			    			hasDialog: false,
			    			stack:false,
			    			dialogWidth:850,
				    		dialogPosition: "center",
				    		width: 640,
				    		height: 426,
				    		language:language
				        });
					}

				} else {
					$("#presentationThumbnailsPane, #videoPane").hide();
				}
			}
		);
	</script>