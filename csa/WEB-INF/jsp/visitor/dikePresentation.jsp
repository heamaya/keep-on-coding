	<%@ taglib uri="/struts-tags" prefix="s"%>
	<%@ taglib uri="/struts-jquery-tags" prefix="sj"%>
	

	<h1 class="title">
		<s:text name="dikes" /> <s:text name="or" /> <s:text name="smallDams" />
	</h1>
	<div>
		<p class="paragraph">
			&nbsp; &nbsp; &nbsp; &nbsp;<s:text name="dikeDefinition" /> <a id="more" href="#"><s:text name="more" /></a>
		</p>	
	</div>
	<div id="details" >
		<p class="paragraph">
			&nbsp; &nbsp; &nbsp; &nbsp;<s:text name="dikeDetails" />
		</p>
	</div>
	<div id="outlet">
		<p class="paragraph">
			&nbsp; &nbsp; &nbsp; &nbsp;<s:text name="outletDetails" />
		</p>
	</div>
	<div id="spillway">
		<p class="paragraph">
			&nbsp; &nbsp; &nbsp; &nbsp;<s:text name="spillwayDetails" />
		</p>
	</div>
	<div id="gallery" class="ui-corner-all" >
		<div id="thumbnailsPaneHeader">
			<s:text name="images" />
		</div>
		<div id="thumbnailsPane">
		    <ul>
		    	<s:if test="#request.presentationPhotos != null">
					<s:iterator value="#request.presentationPhotos" status="status">
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
				        	
				        	<s:url id="url" action="DikePresentationPhotoDownloadFileAction_getFile" namespace="/Util" escapeAmp="false">
								<s:param name="fileName" value="%{photoName}" />
								<s:param name="contentType" value="%{photoContentType}" />					 
							</s:url>
							
				            <img src='<s:property value="%{#url}" />' width="96" height="72" class="ui-corner-all" title='<s:property value="%{#photoDescription}" escapeHtml="false" />' alt="<s:text name="photoAlt" />"  />
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
		$("#details, #outlet, #spillway, #photoPane").hide();
	
		$(
			function() {
				
				var language = '<s:property value="%{#language}"/>';
				
				if(language == "es") {
	  				photosDialogTitle = "Fotos de Diques";
	  				moreInfoDialogTitle = "Más Información Sobre Diques";
	  				outletDialogTitle = "Descargadores de Fondo";
	  				spillwayDialogTitle = "Vertederos de Emergencia";
	  			} else if(language == "pt") {
	  				photosDialogTitle = "Fotos de Barragens";
			  		moreInfoDialogTitle = "Mais Informaçao Sob Barragens";
	  				outletDialogTitle = "Descarregadores de Fundo";
	  				spillwayDialogTitle = "Aterros de Emergencia";
	  			} else {
	  				photosDialogTitle = "Dike Photos";
	  				moreInfoDialogTitle = "More Information About Dikes";
	  				outletDialogTitle = "Outlets";
	  				spillwayDialogTitle = "Emergency Spillways";
			  	}
				
				if($("#thumbnailsPane img").size() > 0) {
		        	$("#thumbnailsPane img").rushSlideShow({
		        		hasDialog:true,
		        		dialogTitle:photosDialogTitle,
						language:language
					});
				}
		  		
			    $("#more").click(
					function(event) {
		  				$("#details").dialog(
		  					{
		  						autoOpen:true, 
		  						width:800,
		  			            title:moreInfoDialogTitle,
		  			            resizable:true,
		  			            draggable:true,
		  			            modal:false,
		  			            stack:true,
		  			            buttons: { "Ok": function() { $(this).dialog("close"); } }
							}
		  				);
		  				
		  			}
		  		);
		  		
		  		$("#outletLink").click(
			  		function(event) {
			  			$("#outlet").dialog(
			  				{
			  					autoOpen:true, 
			  				    width:800,
			  			        title:outletDialogTitle,
			  			        resizable:true,
			  			        draggable:true,
			  			        modal:false,
			  			        stack:true,
			  			        buttons: { "Ok": function() { $(this).dialog("close"); } }
							}
			  			);
			  		}
			  	);
		  		
		  		$("#spillwayLink").click(
			  		function(event) {
			  			$("#spillway").dialog(
			  				{
			  					autoOpen:true, 
			  			        width:800,
			  			        title:spillwayDialogTitle,
			  			        resizable:true,
			  			        draggable:true,
			  			        modal:false,
			  			        stack:true,
			  			        buttons: { "Ok": function() { $(this).dialog("close"); } }
							}
			  			);
			  		}
			  	);
				}
		);
	</script>