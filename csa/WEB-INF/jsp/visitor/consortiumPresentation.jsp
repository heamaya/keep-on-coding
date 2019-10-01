	<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="ISO-8859-1" %>
	<%@ taglib uri="/struts-tags" prefix="s"%>
	<%@ taglib uri="/struts-jquery-tags" prefix="sj"%>
	 
	<h1 class="title">
		<s:text name="consortia" />
	</h1>
	
	<p class="paragraph">
		&nbsp; &nbsp; &nbsp; &nbsp;<s:text name="aboutConsortia" />
	</p>
	
	<table id="consortiumButtonsPane">
		<tr>
			<td id="firstGEButton" class="googleEarthButton"></td>
			<td id="previousGEButton" class="googleEarthButton"></td>
			<td id="nextGEButton" class="googleEarthButton"></td>
			<td id="lastGEButton" class="googleEarthButton"></td>
		</tr>
	</table>

	<div class="consortiumTitle">   
		<div class="title">
			<s:text name="name" />: <span id="name"></span> 
		</div>
		<div class="title">				
			<s:text name="creationDate" />: <span id="creationDate"></span>
		</div>
	</div>
	
	<sj:tabbedpanel id="consortiumTabbedPanel" animate="true" collapsible="false" useSelectedTabCookie="false" >
		<sj:tab id="mapTab" target="consortiumMap" label="%{getText('map')}" />
		<sj:tab id="imagesTab"  target="consortiumImages" label="%{getText('images')}" /> 
		<sj:tab id="descriptionTab" target="consortiumDescription" label="%{getText('description')}" />
		
      	<div id="consortiumMap">
			<div class="googleEarthContentPane">
				<div class="googleEarthTree">
					<div id="kmlTree" class="googleEarthKmlTree">
						<input type="checkbox" id="currentKmlLayers" checked="checked"><span id="currentKmlName"></span> 
					</div>
					<div id="displayOptionsTree" class="googleEarthKmlTree"></div>
					<div id="layersTree" class="googleEarthKmlTree"></div>
				</div>
				<div id="map3d" class="googleEarthMap"></div> 
	    	</div>
      	</div>
      	
      	<div id="consortiumImages">
			<div id="gallery" class="ui-corner-all consortiumGallery" >
				<div id="thumbnailsPaneHeader">
					<s:text name="images" />
				</div>
				<div id="thumbnailsPane">
		
			    </div>
			</div>
			 
		    <div id="photoPane">
		    	<img id="photoDisplay" src=""/>
		    	<div id="photoDescription"></div>
		    	<div id="currentPhoto"></div>
		    </div>
      	</div>

      	<div id="consortiumDescription" class="googleEarthDescription"></div>
	
	</sj:tabbedpanel>

	<script type="text/javascript">
		$("#consortiumTabbedPanel").hide();
		$(
			function() {
				var currentConsortiumIndex = 0;
				var next = "";
				var	previous = "";
				var first = "";
				var last = "";
				var googleEarthIE6 = "";
				
				language = '<s:property value="%{#language}"/>';
					
				if(language == "es") {
					next = 'Siguiente';
					previous = 'Previo';
					first = 'Primero';
					last = 'Último';
					googleEarthIE6 = "El complemento de Google Earth funciona en Internet Explorer 6, de todos modos te recomendamos que actualices tu versión de Internet Explorer o que uses Firefox, Chrome u Opera.";
					dialogTitle = "Fotos del Consorcio";
				} else if(language == "pt") {
					next = 'Seguinte';
					previous = 'Anterior';
					first = 'Primeiro';
					last = 'Último';
					googleEarthIE6 = "A adição do Google Earth funciona no Internet Explorer 6, no entanto, recomendamos que atualize a sua versão do Internet Explorer ou que use o Firefox , Chrome ou Opera.";
					dialogTitle = "Fotos do Consórcio";
				} else {
					next = 'Next';
					previous = 'Previous';
					first = 'First';
					last = 'Last';
					googleEarthIE6 = "The Google Earth Plugin works in Internet Explorer 6. Nevertheless, we recommend you to upgrade to a new version or to use Firefox, Chrome or Opera";
					dialogTitle = "Photos of the Consortium";
				}

				if($.browser.msie && ($.browser.version).substring(0, 1) == "6") {
					
					$("div.consortiumTitle").prepend(					
						'<div class="ui-widget">' +
							'<div class="ui-state-highlight ui-corner-all" style="margin-top: 20px; padding: 0 .7em;">' +
			  					'<p class="paragraph"><span class="ui-icon ui-icon-info" style="float: left; margin-right: .3em;"></span>' +
									'<strong>' + googleEarthIE6 + '</strong></p>' +
	  						'</div>' +
		  				'</div>'
					);
				}
				
				$("#photoPane").hide();
				
				$("#firstGEButton").button(
				{
					label: first,
					icons: { primary: 'ui-icon-seek-first' }
				}
				).click(
					function() {
						currentConsortiumIndex = 0;
						showConsortium(currentConsortiumIndex);
						showConsortiumPhotos(currentConsortiumIndex);
					}
				);
						
				$("#previousGEButton").button(
				{
					label: previous,
				    icons: { primary: 'ui-icon-seek-prev' }
				 }
				).click(
					function() {
						currentConsortiumIndex = (consortiumsIds.length + currentConsortiumIndex - 1) % consortiumsIds.length;
						showConsortium(currentConsortiumIndex);
						showConsortiumPhotos(currentConsortiumIndex);
					}
				);
					
				$("#nextGEButton").button(
				{
					label: next,
					icons: { secondary: 'ui-icon-seek-next' }
				}
				).click(
					function() {
						currentConsortiumIndex = (consortiumsIds.length + currentConsortiumIndex + 1) % consortiumsIds.length;
						showConsortium(currentConsortiumIndex);
						showConsortiumPhotos(currentConsortiumIndex);
					}
				);
						
				$("#lastGEButton").button(
				{
					label: last,
				    icons: { secondary: 'ui-icon-seek-end' }
				}
				).click(
					function() {
						currentConsortiumIndex = consortiumsIds.length - 1;
						showConsortium(currentConsortiumIndex);
						showConsortiumPhotos(currentConsortiumIndex);
					}
				);
				
				$("#currentKmlLayers").attr("disabled", "disabled");
				$("#currentKmlLayers").click(
					function(event) {
					
						if($(this).is(':checked')) {
							turnOnCurrentKmlObject();	
						} else {
							turnOffCurrentKmlObject();	
						}
					}
				);
						
				initConsortiums();
				$("#consortiumTabbedPanel").show();
			}				
		);
	</script>